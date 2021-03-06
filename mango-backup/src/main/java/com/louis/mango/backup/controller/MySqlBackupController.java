package com.louis.mango.backup.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.backup.constants.BackupConstants;
import com.louis.mango.backup.datasource.BackupDataSourceProperties;
import com.louis.mango.backup.service.MysqlBackupService;
import com.louis.mango.backup.util.HttpResult;
import com.louis.mango.common.utils.FileUtils;

/**
 * 系统数据备份还原
 * @author AlanW
 * @date 06 01 2020
 */
@RestController
@RequestMapping("/backup")
public class MySqlBackupController {
	
	@Autowired
	private MysqlBackupService mysqlBackupService;
	@Autowired
	BackupDataSourceProperties properties;
	
	/**
	 * 数据备份接口
	 * 读取数据源信息及备份信息生成数据备份
	 * @return
	 */
	@GetMapping("/backup")
	public HttpResult backup() {
		String backupFolderName = BackupConstants.DEFAULT_BACKUP_NAME + "_" + (new 
				SimpleDateFormat(BackupConstants.DATE_FORMAT)).format(new Date());
		System.out.println("``````````````````````````主目录: " + backupFolderName );
		return backup(backupFolderName);
	}

	private HttpResult backup(String backupFolderName) {
		System.out.println(111);
		String host = properties.getHost();
		String userName = properties.getUserName();
		String password = properties.getPassword();
		String database = properties.getDatabase();
		String backupFolderPath = BackupConstants.BACKUP_FOLDER + backupFolderName + File.separator;
		String fileName = BackupConstants.BACKUP_FILE_NAME;
		try {
			boolean success = mysqlBackupService.backup(host, userName, password, backupFolderPath, fileName, database);
			if(!success) {
				HttpResult.error("数据备份失败");
			}
		} catch (Exception e) {
			return HttpResult.error(500, e.getMessage());
		}
		return HttpResult.ok();
	}
	
	/**
	 * 数据还原接口
	 * 读取数据源信息和还原版本进行数据还原
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/restore")
	public HttpResult restore(@RequestParam String name) throws IOException {
		String host = properties.getHost();
		String userName = properties.getUserName();
		String password = properties.getPassword();
		String database = properties.getDatabase();
		String restoreFilePath = BackupConstants.BACKUP_FOLDER + name;
		try {
			boolean success = mysqlBackupService.restore(restoreFilePath, host, userName, password, database);
		} catch (Exception e) {
			return HttpResult.error(500, e.getMessage());
		}
		return HttpResult.ok();
	}
	
	/**
	 * 备份查找接口
	 * 查找和向客户展示数据备份版本
	 * @return
	 */
	@GetMapping("/findRecords")
	public HttpResult findBackupRecords() {
		if(!new File(BackupConstants.DEFAULT_RESTORE_FILE).exists()) {
			//初始默认备份文件
			backup(BackupConstants.DEFAULT_BACKUP_NAME);
		}
		
		List<Map<String, String>> backupRecords = new ArrayList<>();
		File restoreFolderFile = new File(BackupConstants.RESTORE_FOLDER);
		if(restoreFolderFile.exists()) {
			for(File file:restoreFolderFile.listFiles()) {
				Map<String, String> backup = new HashMap<>();
				backup.put("name", file.getName());
				backup.put("title", file.getName());
				if(BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(file.getName())) {
					backup.put("title", "系统默认备份");
				}
				backupRecords.add(backup);
			}
		}
		//排序, 默认备份最前, 然后按是按戳排序, 新备份在前面
		backupRecords.sort((o1, o2) -> BackupConstants.DEFAULT_BACKUP_NAME.
				equalsIgnoreCase(o1.get("name")) ? -1 : BackupConstants.DEFAULT_BACKUP_NAME.
						equalsIgnoreCase(o2.get("name")) ? 1 : o2.get("name").compareTo(o1.get("name")));
		System.out.println(backupRecords.toString());
		return HttpResult.ok(backupRecords);
		
	}
	
	/**
	 * 删除备份接口
	 * 通过备份还原管理界面删除数据备份版本
	 * @param name
	 * @return
	 */
	@GetMapping("/delete")
	public HttpResult deleteBackupRecord(@RequestParam String name) {
		if(BackupConstants.DEFAULT_BACKUP_NAME.equals(name)) {
			return HttpResult.error("系统默认备份无法删除");
		}
		String restoreFilePath = BackupConstants.BACKUP_FOLDER + name;
		
		try {
			FileUtils.deleteFile(new File(restoreFilePath));
		} catch (Exception e) {
			return HttpResult.error(500, e.getMessage());
		}
		return HttpResult.ok();
	}
	
	
}
