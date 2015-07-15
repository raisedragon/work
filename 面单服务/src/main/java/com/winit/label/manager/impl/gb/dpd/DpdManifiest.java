package com.winit.label.manager.impl.gb.dpd;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.xml.bind.JAXBException;

import org.apache.commons.httpclient.HttpException;

import com.winit.exception.LabelBusinessException;
import com.winit.label.manager.impl.gb.dpd.model.Error;
import com.winit.label.manager.impl.gb.dpd.model.GetManifestByIdOutput;

public class DpdManifiest
{
	public boolean generateReport(String wt_deliveryorder_id, String outFile) throws HttpException, IOException, JAXBException
	{
		Date currentTime = new Date();
		
		GeoService geoService = GeoService.getInstance();
		
//		CreateManifestParameters parameters = new CreateManifestParameters();
//		parameters.setCollectionDate(DateFormatUtils.format(currentTime, "yyyy-MM-dd"));
//		CreateManifestOutput createManifestOutput = geoService.createManifest(parameters);
//		checkError(createManifestOutput.getError());
//		
//		long manifestId = createManifestOutput.getData().getManifestId();
//		
		long manifestId = 23456;
		
		GetManifestByIdOutput getManifestByIdOutput = geoService.getManifestById(manifestId);
		String html = getManifestByIdOutput.getPrintString();
		
		//TODO
		FileOutputStream fos = new FileOutputStream(outFile);
		fos.write(html.getBytes());
		fos.close();
		
		
		return false;
	}
	
	protected void checkError(Error err){
		if(err==null){
			return;
		}
		String emsg =  err.getErrorMessage()+"";
		throw new LabelBusinessException(emsg);
	}
}
