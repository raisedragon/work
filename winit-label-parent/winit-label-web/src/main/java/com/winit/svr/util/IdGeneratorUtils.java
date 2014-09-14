package com.winit.svr.util;

import com.raise.commons.id.IdGenerator;
import com.raise.commons.id.impl.DefaultIdGeneratorImpl;

/**
 * 生成唯一ID的工具类
 * @author RaiseDragon
 * 
 */
public abstract class IdGeneratorUtils {
	
	public static IdGenerator idGenerator;
	
	/**
	 * 生成唯一ID
	 * @return
	 */
	public static String nextId(){
		ensureInitial();
		long id = idGenerator.nextId();
		return ""+id;
	}
	
	/**
	 * 查看IdGenerator是否已初始化。如果未初始化，进行初始化。
	 */
	private static void ensureInitial(){
		//TODO
		long workerId = 1;
		long datacenterId = 1;
		if(idGenerator==null){
			idGenerator = new DefaultIdGeneratorImpl(workerId, datacenterId);
		}
	}
}
