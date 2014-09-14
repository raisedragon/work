package com.winit.svr.label;

import java.io.Serializable;


import com.winit.svr.query.Query;

public interface LogisticTypeQuery extends Query<LogisticTypeQuery, LogisticType>
{
	LogisticTypeQuery logisticTypeId(String id);
	
	LogisticTypeQuery logisticTypeNameLike(String name);
	
	LogisticTypeQuery logisticTypeCode(String code);
	
	
	/**
	   * Only select LogisticTypes which have a local LogisticType variable with the given name
	   * set to the given value.
	   */
	  LogisticTypeQuery LogisticTypeVariableValueEquals(String variableName, Object variableValue);
	  
	  /**
	   * Only select LogisticTypes which have at least one local LogisticType variable with the given value.
	   */
	  LogisticTypeQuery LogisticTypeVariableValueEquals(Object variableValue);
	  
	  /**
	   * Only select LogisticTypes which have a local string variable with the given value, 
	   * case insensitive.
	   * <p>
	   * This method only works if your database has encoding/collation that supports case-sensitive
	   * queries. For example, use "collate UTF-8" on MySQL and for MSSQL, select one of the case-sensitive Collations 
	   * available (<a href="http://msdn.microsoft.com/en-us/library/ms144250(v=sql.105).aspx">MSDN Server Collation Reference</a>).
	   * </p>
	   */
	  LogisticTypeQuery LogisticTypeVariableValueEqualsIgnoreCase(String name, String value);
	  
	  /** 
	   * Only select LogisticTypes which have a local LogisticType variable with the given name, but
	   * with a different value than the passed value.
	   * Byte-arrays and {@link Serializable} objects (which are not primitive type wrappers)
	   * are not supported.
	   */
	  LogisticTypeQuery LogisticTypeVariableValueNotEquals(String variableName, Object variableValue);    
	  
	  /**
	   * Only select LogisticTypes which have a local string variable with is not the given value, 
	   * case insensitive.
	   * <p>
	   * This method only works if your database has encoding/collation that supports case-sensitive
	   * queries. For example, use "collate UTF-8" on MySQL and for MSSQL, select one of the case-sensitive Collations 
	   * available (<a href="http://msdn.microsoft.com/en-us/library/ms144250(v=sql.105).aspx">MSDN Server Collation Reference</a>).
	   * </p>
	   */
	  LogisticTypeQuery LogisticTypeVariableValueNotEqualsIgnoreCase(String name, String value);
	  
	  /** Only select LogisticTypes which have a local variable value greater than the
	   * passed value when they ended. Booleans, Byte-arrays and
	   * {@link Serializable} objects (which are not primitive type wrappers) are
	   * not supported.
	   * @param name cannot be null.
	   * @param value cannot be null. */
	  LogisticTypeQuery LogisticTypeVariableValueGreaterThan(String name, Object value);

	  /** Only select LogisticTypes which have a local variable value greater than or
	   * equal to the passed value when they ended. Booleans, Byte-arrays and
	   * {@link Serializable} objects (which are not primitive type wrappers) are
	   * not supported.
	   * @param name cannot be null.
	   * @param value cannot be null. */
	  LogisticTypeQuery LogisticTypeVariableValueGreaterThanOrEqual(String name, Object value);

	  /** Only select LogisticTypes which have a local variable value less than the
	   * passed value when the ended.Booleans,
	   * Byte-arrays and {@link Serializable} objects (which are not primitive type
	   * wrappers) are not supported.
	   * @param name cannot be null.
	   * @param value cannot be null. */
	  LogisticTypeQuery LogisticTypeVariableValueLessThan(String name, Object value);

	  /** Only select LogisticTypes which have a local variable value less than or equal
	   * to the passed value when they ended. Booleans,
	   * Byte-arrays and {@link Serializable} objects (which are not primitive type
	   * wrappers) are not supported.
	   * @param name cannot be null.
	   * @param value cannot be null. */
	  LogisticTypeQuery LogisticTypeVariableValueLessThanOrEqual(String name, Object value);

	  /** Only select LogisticTypes which have a local variable value like the given value
	   * when they ended. This can be used on string variables only.
	   * @param name cannot be null.
	   * @param value cannot be null. The string can include the
	   *          wildcard character '%' to express like-strategy: starts with
	   *          (string%), ends with (%string) or contains (%string%). */
	  LogisticTypeQuery LogisticTypeVariableValueLike(String name, String value);
	
}
