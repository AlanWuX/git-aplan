package com.louis.mango.backup.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * 配置back数据源
 * @author AlanW
 * @date 06 01 2020
 */
//@component （把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>
@Component  
@ConfigurationProperties(prefix = "mango.backup.datasource")  
public class BackupDataSourceProperties {

	private String host;
	private String userName;
	private String password;
	private String database;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	
	
}
