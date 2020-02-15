package com.louis.mango.core.service;

import java.util.List;

import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;


//通用业务接口定义
/**
 * 通用CURD接口
 * @author AlanW
 * @date 12,23,2019
 *
 */
public interface CurdService<T> {

	/**
	 * 保存操作
	 * @param record
	 * @return
	 */
	int save(T record);
	
	/**
	 * 删除操作
	 * @param record
	 * @return
	 */
	int delete(T record);
	
	/**
	 * 批量删除操作
	 * @param records
	 * @return
	 */
	int delete(List<T> records);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	T findById(Long id);
	
	/**
	 * 分页查询
	 * 这里统一封装了分页请求和结果, 避免直接引入具体框架的分页对象,
	 * 
	 * @param pageRequest
	 * @return
	 */
	PageResult findPage(PageRequest pageRequest);
}
