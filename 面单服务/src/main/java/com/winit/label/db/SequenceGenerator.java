package com.winit.label.db;

/**
 * 序列操作接口
 * @author longsheng.wang
 *
 */
public interface SequenceGenerator
{
	
	/**
	 * 获取序列的下一个值
	 * @param seqKey 序列名
	 * @return
	 */
	long nextVal(String seqKey);
	/**
	 * 获取序列的当前值
	 * @param seqKey 序列名
	 * @return
	 */
	long currentVal(String seqKey);
	/**
	 * 设置序列的当前值，设置成功后，返回设置后的序列的当前值
	 * @param seqKey 序列名
	 * @param value 序列当前值
	 * @return
	 */
	long setVal(String seqKey,long value);
}
