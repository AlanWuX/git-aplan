package com.louis.mango.backup.service.Impl;

import org.springframework.stereotype.Service;

import com.louis.mango.backup.service.MysqlBackupService;
import com.louis.mango.backup.util.MySqlBackRestoreUtils;
@Service
public class MysqlBackupServiceImpl implements MysqlBackupService {

	@Override
	public boolean backup(String host, String userName, String password, String backupFolderPath, String fileName,
			String database) throws Exception {
		return MySqlBackRestoreUtils.backup(host, userName, password, backupFolderPath, fileName, database);
	}

	@Override
	public boolean restore(String restoreFilePath, String host, String userName, String password, String database)
			throws Exception {
		return MySqlBackRestoreUtils.restore(restoreFilePath, host, userName, password, database);
	}

}
