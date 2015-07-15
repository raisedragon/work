package com.winit.label.manager.impl.de.dhl30;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.winit.label.service.impl.BaseServiceImpl;

@Component
public class Dhl30ConsignmentService  extends BaseServiceImpl
{
	
	protected static final String NAME_SPACE = Dhl30Consignment.class.getName();

	
	
	/**
	 * 查询起始邮编
	 * @param country
	 * @param postal
	 * @return
	 */
	public String getStartPostcode(String country, String postal){
		postal = postal.split("( |\u00A0)")[0];
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("country", country);
		parameters.put("postal", postal);
		
		String code = (String) this.single(NAME_SPACE+"getStartPostcode", parameters);
		return code;
		
//		postal = postal.split("( |\u00A0)")[0];
//		String sql = "select max(postal) from wt_consignment where country=? and postal<=? and length(postal)<=length(?)";
//		String result = DB.getSQLValueString(null, sql, country, postal, postal);
//		return result;
	}

	/**
	 * 根据国家，邮编，分区代码得到 routing area
	 * @param Country
	 * @param postal
	 * @param BranchCode
	 * @return
	 */
	public Dhl30Consignment getConsignmentByCountryAndPostcode(
			String country, 
			String postal, 
			String winitBranchCode, 
			String defaultBranchCode){
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("country", country);
		parameters.put("postal", postal);
		parameters.put("winitBranchCode", winitBranchCode);
		parameters.put("defaultBranchCode", defaultBranchCode);
		
		Dhl30Consignment cons = (Dhl30Consignment) this.single(NAME_SPACE+"getConsignmentByCountryAndPostcode", parameters);
		return cons;
		
		
//		String whereClause=" country=? and postal=? and length(postal) <= length(?) and BranchCode in ('" + winitBranchCode + "','" + defaultBranchCode + "')";
//		Consignment cons = new Query(Env.getCtx(), X_WT_Consignment.Table_Name, whereClause, null)
//				.setParameters(country, postal, postal)
//				.setOrderBy("postal desc, BranchCode desc")
//				.first();
//		return cons;
	}
	
	/**
	 * 根据国家，分区代码得到 routing area
	 * @param country
	 * @param branchCode
	 * @return
	 */
	public Dhl30Consignment getConsignmentByCountry(String country, String winitBranchCode, String defaultBranchCode){
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("country", country);
		parameters.put("winitBranchCode", winitBranchCode);
		parameters.put("defaultBranchCode", defaultBranchCode);
		
		Dhl30Consignment cons = (Dhl30Consignment) this.single(NAME_SPACE+"getConsignmentByCountry", parameters);
		return cons;
		
//		String whereClause=" country=? and BranchCode in ('" + winitBranchCode + "','" + defaultBranchCode + "')";
//		Dhl30Consignment cons = new Query(Env.getCtx(), X_WT_Consignment.Table_Name, whereClause, null)
//				.setParameters(country)
//				.setOrderBy("BranchCode desc")
//				.first();
//		return cons;
	}
}
