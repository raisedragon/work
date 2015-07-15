package com.winit.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;

import com.google.common.io.Files;
import com.winit.exception.LabelException;
import com.winit.label.model.RequestMessage;
import com.winit.label.model.RequestMessage.ClassifyProduct;
import com.winit.label.model.RequestMessage.Consignee;
import com.winit.label.model.RequestMessage.Product;
import com.winit.label.model.ResponseMessage;

public class RequestMessageUtils
{

	public static void main(String[] args) throws IOException
	{
		RequestMessageUtils excel = new RequestMessageUtils();
		// excel.write();

		FileInputStream fis = new FileInputStream("D:\\work\\MyWork\\面单服务\\DATA_UBI.xls");
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheet = wb.getSheet("sheet1");
		List<RequestMessage> requestMessages = excel.read(sheet);
	}

	public void write() throws IOException
	{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		createHeader(sheet);

		FileOutputStream fos = new FileOutputStream("D:\\temp\\template.xls");
		wb.write(fos);
		// sheet.createRow(rownum);
		fos.close();
	}
	public static List<RequestMessage> getRequestMessageFromExcel(String file,String sheetname) throws IOException{
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheet(sheetname);
			return read(sheet);
		}finally{
			if(fis!=null){
				fis.close();
			}
		}
	}
	
	public static List<RequestMessageWrapper> getRequestMessageWrapperFromExcel(String file,String sheetname) throws IOException{
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheet(sheetname);
			return readRequestMessageWrappers(sheet);
		}finally{
			if(fis!=null){
				fis.close();
			}
		}
	}
	

	public static List<RequestMessage> read(HSSFSheet sheet) throws IOException
	{
		
		List<RequestMessage> requestMessages = new ArrayList<RequestMessage>();
		
		int rowNum = 4;
		int documentColNum = 1;
		int consigneeNum = 2;
		int productColNum = 18;
		int classtifyColNum = 25;

		
		RequestMessage requestMessage = null;

		System.out.println("Row Number:" + rowNum);
		HSSFRow row = sheet.getRow(rowNum);
		HSSFCell cell = row.getCell(documentColNum);

		

		while ((row = sheet.getRow(rowNum++)) != null)
		{
			cell = row.getCell(0);
			String active = getStringCellValue(cell);
			if("0".equals(active)){
				continue;
			}

			cell = row.getCell(documentColNum);
			String documentNo = getStringCellValue(cell);

			cell = row.getCell(productColNum);

			String productName = getStringCellValue(cell);
			cell = row.getCell(classtifyColNum);
			String classifyCountryCode = getStringCellValue(cell);

			if (StringUtils.isNotEmpty(documentNo))
			{
				if(documentNo.equals("1457658")){
					System.out.println(documentNo);
					
				}
				if (requestMessage != null)
				{
					
					requestMessages.add(requestMessage);
				}
				requestMessage = new RequestMessage();
				{
					cell = row.getCell(documentColNum + 1);

					String logisticsCode = getStringCellValue(cell);
					cell = row.getCell(documentColNum + 2);

					double length = getNumericCellValue(cell);
					cell = row.getCell(documentColNum + 3);

					double width = getNumericCellValue(cell);
					cell = row.getCell(documentColNum + 4);

					double height = getNumericCellValue(cell);
					cell = row.getCell(documentColNum + 5);

					double weight = getNumericCellValue(cell);

					requestMessage.setDocumentNo(documentNo);
					requestMessage.setLogisticsCode(logisticsCode);
					requestMessage.setLength(length);
					requestMessage.setWeight(weight);
					requestMessage.setWidth(width);
					requestMessage.setHeight(height);
				}

				{
					cell = row.getCell(consigneeNum + 0);

					String name = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 1);

					String address1 = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 2);

					String address2 = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 3);

					String address3 = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 4);

					String email = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 5);

					String phone = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 6);

					String countryCode = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 7);

					String state = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 8);

					String city = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 9);

					String postcode = getStringCellValue(cell);
					
					cell = row.getCell(consigneeNum + 10);
					String houseNo = getStringCellValue(cell);

					Consignee consignee = new Consignee();
					consignee.setAddress1(address1);
					consignee.setAddress2(address2);
					consignee.setAddress3(address3);
					consignee.setCity(city);
					consignee.setCountryCode(countryCode);
					consignee.setEmail(email);
					consignee.setName(name);
					consignee.setPhone(phone);
					consignee.setPostcode(postcode);
					consignee.setState(state);
					consignee.setHouseNo(houseNo);
					requestMessage.setConsignee(consignee);

				}
			}

			if (StringUtils.isNotEmpty(productName))
			{
				Product product = new Product();
				cell = row.getCell(productColNum + 0);

				String name = getStringCellValue(cell);
				cell = row.getCell(productColNum + 1);

				double length = getNumericCellValue(cell);
				cell = row.getCell(productColNum + 2);

				double width = getNumericCellValue(cell);
				cell = row.getCell(productColNum + 3);

				double height = getNumericCellValue(cell);
				cell = row.getCell(productColNum + 4);

				double weight = getNumericCellValue(cell);
				cell = row.getCell(productColNum + 5);

				double qty = getNumericCellValue(cell);
				cell = row.getCell(productColNum + 6);

				String sku = getStringCellValue(cell);
				product.setHeight(height);
				product.setLength(length);
				product.setName(name);
				product.setQty((int) qty);
				product.setSku(sku);
				product.setWeight(weight);
				product.setWidth(width);
				requestMessage.getProducts().add(product);
			}
			if (StringUtils.isNotEmpty(classifyCountryCode))
			{
				cell = row.getCell(classtifyColNum + 0);

				String countryCode = getStringCellValue(cell);
				cell = row.getCell(classtifyColNum + 1);

				double priceImports = getNumericCellValue(cell);
				ClassifyProduct classifyProduct = new ClassifyProduct();
				classifyProduct.setCountryCode(countryCode);
				classifyProduct.setPriceImports(priceImports);

				int lastIdx = requestMessage.getProducts().size() - 1;
				requestMessage.getProducts().get(lastIdx).getClassifyProducts().add(classifyProduct);
			}
		}
		if(requestMessage!=null){
			requestMessages.add(requestMessage);
		}
		for(RequestMessage req:requestMessages){
			checkRequestMessage(req);
		}
		return requestMessages;
	}
	
	public static List<RequestMessageWrapper> readRequestMessageWrappers(HSSFSheet sheet) throws IOException
	{
		
		List<RequestMessageWrapper> requestMessages = new ArrayList<RequestMessageWrapper>();
		
		int rowNum = 4;
		int documentColNum = 1;
		int consigneeNum = 7;
		int productColNum = 18;
		int classtifyColNum = 25;

		
		RequestMessage requestMessage = null;

		System.out.println("Row Number:" + rowNum);
		HSSFRow row = sheet.getRow(rowNum);
		HSSFCell cell = row.getCell(documentColNum);

		int rn = 0;

		while ((row = sheet.getRow(rowNum++)) != null)
		{
			cell = row.getCell(0);
			String active = getStringCellValue(cell);
			if("0".equals(active)){
				continue;
			}

			cell = row.getCell(documentColNum);
			String documentNo = getStringCellValue(cell);

			cell = row.getCell(productColNum);

			String productName = getStringCellValue(cell);
			cell = row.getCell(classtifyColNum);
			String classifyCountryCode = getStringCellValue(cell);

			if (StringUtils.isNotEmpty(documentNo))
			{
				if (requestMessage != null)
				{
					
					requestMessages.add(new RequestMessageWrapper(sheet.getSheetName(), rn,  requestMessage));
				}
				requestMessage = new RequestMessage();
				rn = rowNum-1;
				{
					cell = row.getCell(documentColNum + 1);

					String logisticsCode = getStringCellValue(cell);
					cell = row.getCell(documentColNum + 2);

					double length = getNumericCellValue(cell);
					cell = row.getCell(documentColNum + 3);

					double width = getNumericCellValue(cell);
					cell = row.getCell(documentColNum + 4);

					double height = getNumericCellValue(cell);
					cell = row.getCell(documentColNum + 5);

					double weight = getNumericCellValue(cell);

					requestMessage.setDocumentNo(documentNo);
					requestMessage.setLogisticsCode(logisticsCode);
					requestMessage.setLength(length);
					requestMessage.setWeight(weight);
					requestMessage.setWidth(width);
					requestMessage.setHeight(height);
				}

				{
					cell = row.getCell(consigneeNum + 0);

					String name = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 1);

					String address1 = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 2);

					String address2 = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 3);

					String address3 = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 4);

					String email = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 5);

					String phone = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 6);

					String countryCode = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 7);

					String state = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 8);

					String city = getStringCellValue(cell);
					cell = row.getCell(consigneeNum + 9);

					String postcode = getStringCellValue(cell);
					
					
					cell = row.getCell(consigneeNum + 10);

					String houseNo = getStringCellValue(cell);

					Consignee consignee = new Consignee();
					consignee.setAddress1(address1);
					consignee.setAddress2(address2);
					consignee.setAddress3(address3);
					consignee.setCity(city);
					consignee.setCountryCode(countryCode);
					consignee.setEmail(email);
					consignee.setName(name);
					consignee.setPhone(phone);
					consignee.setPostcode(postcode);
					consignee.setState(state);
					consignee.setHouseNo(houseNo);
					requestMessage.setConsignee(consignee);

				}
			}

			if (StringUtils.isNotEmpty(productName))
			{
				Product product = new Product();
				cell = row.getCell(productColNum + 0);

				String name = getStringCellValue(cell);
				cell = row.getCell(productColNum + 1);

				double length = getNumericCellValue(cell);
				cell = row.getCell(productColNum + 2);

				double width = getNumericCellValue(cell);
				cell = row.getCell(productColNum + 3);

				double height = getNumericCellValue(cell);
				cell = row.getCell(productColNum + 4);

				double weight = getNumericCellValue(cell);
				cell = row.getCell(productColNum + 5);

				double qty = getNumericCellValue(cell);
				cell = row.getCell(productColNum + 6);

				String sku = getStringCellValue(cell);
				product.setHeight(height);
				product.setLength(length);
				product.setName(name);
				product.setQty((int) qty);
				product.setSku(sku);
				product.setWeight(weight);
				product.setWidth(width);
				requestMessage.getProducts().add(product);
			}
			if (StringUtils.isNotEmpty(classifyCountryCode))
			{
				cell = row.getCell(classtifyColNum + 0);

				String countryCode = getStringCellValue(cell);
				cell = row.getCell(classtifyColNum + 1);

				double priceImports = getNumericCellValue(cell);
				ClassifyProduct classifyProduct = new ClassifyProduct();
				classifyProduct.setCountryCode(countryCode);
				classifyProduct.setPriceImports(priceImports);

				int lastIdx = requestMessage.getProducts().size() - 1;
				requestMessage.getProducts().get(lastIdx).getClassifyProducts().add(classifyProduct);
			}
		}
		if(requestMessage!=null){
			requestMessages.add(new RequestMessageWrapper(sheet.getSheetName(),rn,requestMessage));
		}
		for(RequestMessageWrapper req:requestMessages){
			checkRequestMessage(req.getRequestMessage());
		}
		return requestMessages;
	}

	protected HSSFSheet createHeader(HSSFSheet sheet)
	{
		HSSFRow row0 = sheet.createRow(0);
		HSSFCell cell0006 = row0.createCell(6);
		HSSFRow row1 = sheet.createRow(1);
		HSSFCell cell0116 = row1.createCell(16);
		HSSFRow row2 = sheet.createRow(2);
		HSSFCell cell0223 = row2.createCell(23);
		HSSFRow row3 = sheet.createRow(3);

		HSSFCell cell0300 = row3.createCell(0);
		HSSFCell cell0301 = row3.createCell(1);
		HSSFCell cell0302 = row3.createCell(2);
		HSSFCell cell0303 = row3.createCell(3);
		HSSFCell cell0304 = row3.createCell(4);
		HSSFCell cell0305 = row3.createCell(5);
		HSSFCell cell0306 = row3.createCell(6);
		HSSFCell cell0307 = row3.createCell(7);
		HSSFCell cell0308 = row3.createCell(8);
		HSSFCell cell0309 = row3.createCell(9);
		HSSFCell cell0310 = row3.createCell(10);
		HSSFCell cell0311 = row3.createCell(11);
		HSSFCell cell0312 = row3.createCell(12);
		HSSFCell cell0313 = row3.createCell(13);
		HSSFCell cell0314 = row3.createCell(14);
		HSSFCell cell0315 = row3.createCell(15);
		HSSFCell cell0316 = row3.createCell(16);
		HSSFCell cell0317 = row3.createCell(17);
		HSSFCell cell0318 = row3.createCell(18);
		HSSFCell cell0319 = row3.createCell(19);
		HSSFCell cell0320 = row3.createCell(20);
		HSSFCell cell0321 = row3.createCell(21);
		HSSFCell cell0322 = row3.createCell(22);
		HSSFCell cell0323 = row3.createCell(23);
		HSSFCell cell0324 = row3.createCell(24);

		cell0006.setCellValue("consignee");
		cell0116.setCellValue("products");
		cell0223.setCellValue("classifyProducts");

		cell0300.setCellValue("documentNo");
		cell0301.setCellValue("logisticsCode");
		cell0302.setCellValue("length");
		cell0303.setCellValue("width");
		cell0304.setCellValue("height");
		cell0305.setCellValue("weight");
		cell0306.setCellValue("name");
		cell0307.setCellValue("address1");
		cell0308.setCellValue("address2");
		cell0309.setCellValue("address3");
		cell0310.setCellValue("email");
		cell0311.setCellValue("phone");
		cell0312.setCellValue("countryCode");
		cell0313.setCellValue("state");
		cell0314.setCellValue("city");
		cell0315.setCellValue("postcode");
		cell0316.setCellValue("name");
		cell0317.setCellValue("length");
		cell0318.setCellValue("width");
		cell0319.setCellValue("height");
		cell0320.setCellValue("weight");
		cell0321.setCellValue("qty");
		cell0322.setCellValue("sku");
		cell0323.setCellValue("countryCode");
		cell0324.setCellValue("priceImports");

		sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 5));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 24));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 15));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 16, 24));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 6, 15));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 16, 22));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 23, 24));

		return sheet;
	}

	static String  getStringCellValue(HSSFCell cell)
	{
		if(cell==null){
			return null;
		}
		String value = null;
		switch (cell.getCellType())
		{
			case Cell.CELL_TYPE_NUMERIC:
				cell.setCellType(Cell.CELL_TYPE_STRING);
//				double v = cell.getNumericCellValue();
//				value = String.valueOf(v);
				break;
			default:
				value = cell.getStringCellValue();
				break;
		}
		value = cell.getStringCellValue();
		if(StringUtils.isBlank(value)){
			value=null;
		}
		return value;
	}

	static double  getNumericCellValue(HSSFCell cell)
	{
		if(cell==null){
			return 0;
		}
		double value = 0;
		switch (cell.getCellType())
		{
			case Cell.CELL_TYPE_STRING:
				String v = cell.getStringCellValue();
				value = Double.valueOf(v);
				break;
			default:
				value = cell.getNumericCellValue();
				break;
		}
		return value;
	}
	
	public static void saveResponseMessages(List<ResponseMessage> responseMessages,String file,String sheetname) throws IOException{
		
		if(StringUtils.isEmpty(sheetname)){
			sheetname = "RESPONSE-"+System.currentTimeMillis();
		}
		HSSFWorkbook wb = null ;
		FileInputStream fis = null ;
		File output = new File(file);
		if(output.exists()){
			fis = new FileInputStream(output);
			wb = new HSSFWorkbook(fis);
		}else{
			wb = new HSSFWorkbook();
		}
		HSSFSheet sheet = wb.createSheet(sheetname);
		
		int rownum = 0;
		//header
		{
			int colnum=0;
			HSSFRow row = sheet.createRow(rownum++);
			HSSFCell cell = row.createCell(colnum++);
			cell.setCellValue("出库单号");
			cell = row.createCell(colnum++);
			cell.setCellValue("状态码");
			cell = row.createCell(colnum++);
			cell.setCellValue("快递单号");
			cell = row.createCell(colnum++);
			cell.setCellValue("文件路径");
			cell = row.createCell(colnum++);
			cell.setCellValue("错误消息");
			
			
		}
		
		
		for(ResponseMessage responseMessage:responseMessages){
			int colnum=0;
			HSSFRow row = sheet.createRow(rownum++);
			HSSFCell cell = row.createCell(colnum++);
			cell.setCellValue(responseMessage.getDocumentNo());
			cell = row.createCell(colnum++);
			cell.setCellValue(responseMessage.getStatusCode());
			cell = row.createCell(colnum++);
			
			if(responseMessage.getFilePath()!=null && responseMessage.getFilePath().length()>256){
				File f = new File(FilenameUtils.getFullPath(file),responseMessage.getDocumentNo()+".pdf");
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(Base64.decodeBase64(responseMessage.getFilePath()));
				fos.close();
				cell.setCellValue(f.getAbsolutePath());
			}else{
				cell.setCellValue(responseMessage.getFilePath());
			}
			
			cell = row.createCell(colnum++);
			cell.setCellValue(responseMessage.getTrackingNo());
			cell = row.createCell(colnum++);
			cell.setCellValue(responseMessage.getMessage());
		}
		if(fis!=null)
			fis.close();
		
		FileOutputStream fos = new FileOutputStream(file);
		try{
			
			wb.write(fos);
			fos.close();
		}finally{
			if(fos!=null){
				fos.close();
			}
		}
		
	}
	
	private static void checkRequestMessage(RequestMessage request)
	{
		String msg = "Request Message is invalid: ";
		if(request==null){
			new LabelException(msg+"request message can not be empty");
		}
		
		if(StringUtils.isEmpty(request.getDocumentNo())){
			new LabelException(msg+"documentNo can not be empty");
		}
		
		if(request.getLogisticsCode()==null){
			new LabelException(msg+"logistic code can not be empty");
		}
		
		if(request.getConsignee()==null){
			new LabelException(msg+"consignee can not be null");
		}
		
		if(request.getConsignee().getPostcode()==null){
			request.getConsignee().setPostcode("");
		}else{
			request.getConsignee().setPostcode(request.getConsignee().getPostcode().toUpperCase());
		}
		
		if(request.getConsignee().getCountryCode().equals("UK")) {
			request.getConsignee().setCountryCode("GB");
		}
		
		
		
		if(0==request.getWeight()){
			double pkgWeight = 0;
			for(Product product:request.getProducts()){
				pkgWeight = BigDecimal.valueOf(pkgWeight).add(BigDecimal.valueOf( product.getQty()).multiply(BigDecimal.valueOf(product.getWeight()))).doubleValue();
			}
			request.setWeight(pkgWeight);
		}
	}

	public static void saveResponseMessages(
			List<ResponseMessageWrapper> responseMessages, String file) throws IOException {
		HSSFWorkbook wb = null ;
		FileInputStream fis = null ;
		File output = new File(file);
		if(output.exists()){
			fis = new FileInputStream(output);
			wb = new HSSFWorkbook(fis);
		}else{
			wb = new HSSFWorkbook();
		}
		
		

		
		for(ResponseMessageWrapper responseMessage:responseMessages){
			HSSFSheet sheet = wb.getSheet(responseMessage.getSheetname());
			int colnum=27;
			HSSFRow row = sheet.getRow(responseMessage.getRownum());
			HSSFCell cell = row.createCell(colnum++);
			cell.setCellValue(responseMessage.getResponseMessage().getStatusCode());
			cell = row.createCell(colnum++);
			
			if(responseMessage.getResponseMessage().getFilePath()!=null && responseMessage.getResponseMessage().getFilePath().length()>256){
				File f = new File(FilenameUtils.getFullPath(file),responseMessage.getResponseMessage().getDocumentNo()+".pdf");
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(Base64.decodeBase64(responseMessage.getResponseMessage().getFilePath()));
				fos.close();
				cell.setCellValue(f.getAbsolutePath());
			}else{
				cell.setCellValue(responseMessage.getResponseMessage().getFilePath());
			}
			
			cell = row.createCell(colnum++);
			cell.setCellValue(responseMessage.getResponseMessage().getTrackingNo());
			cell = row.createCell(colnum++);
			cell.setCellValue(responseMessage.getResponseMessage().getMessage());
		}
		if(fis!=null)
			fis.close();
		
		FileOutputStream fos = new FileOutputStream(file);
		try{
			
			wb.write(fos);
			fos.close();
		}finally{
			if(fos!=null){
				fos.close();
			}
		}
		
	}
	
}
