package com.louis.mango.backup.constants;

import java.io.File;

/**
 * 常用类
 * @author AlanW
 * @date 06 01 2020
 */
public class BackupConstants {

	public static final String BACIUP_FOLDER_NAME = "_mango_backup";
	
	public static final String BACKUP_FOLDER = System.getProperty("user.home") + File.separator
			+ BACIUP_FOLDER_NAME + File.separator;
	
	public static final String RESTORE_FOLDER = BACKUP_FOLDER;
	
	public static final String DATE_FORMAT = "yyyy-MM-dd_HHmmss";
	
	public static final String SQL_EXT = ".sql";
	
	public static final String BACKUP_FILE_NAME = "mango" + SQL_EXT;
	
	public static final String DEFAULT_BACKUP_NAME = "backup";
	
	public static final String DEFAULT_RESTORE_FILE = BACKUP_FOLDER + DEFAULT_BACKUP_NAME + File.separator + BACKUP_FILE_NAME;
}
