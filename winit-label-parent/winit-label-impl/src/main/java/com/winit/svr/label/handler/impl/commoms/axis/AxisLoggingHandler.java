package com.winit.svr.label.handler.impl.commoms.axis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.GenericHandler;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winit.svr.label.context.ContextUtils;


public class AxisLoggingHandler extends GenericHandler
{

	private String				timeStamp;

	private static Logger		s_log				= LoggerFactory.getLogger(AxisLoggingHandler.class);

	private static Level LOG_LEVEL()
	{
		String lev = ContextUtils.getValue("USPS_INTL_SOAP_LOG_LEVEL", "FINE");
		try
		{
			return Level.parse(lev);
		}
		catch (Exception e)
		{
			s_log.error(e.getMessage());
			return Level.FINE;
		}
	}
	
	private void log(Level level,String msg){
		if(level.intValue()==Level.WARNING.intValue()){
			s_log.warn(msg);
		}else if(level.intValue()==Level.SEVERE.intValue()){
			s_log.error(msg);
		}else if(level.intValue()==Level.FINE.intValue()){
			s_log.info(msg);
		}else if(level.intValue()==Level.FINER.intValue()){
			s_log.info(msg);
		}else if(level.intValue()==Level.FINEST.intValue()){
			s_log.info(msg);
		}else if(level.intValue()==Level.ALL.intValue()){
			s_log.info(msg);
		}else if(level.intValue()==Level.CONFIG.intValue()){
			s_log.warn(msg);
		}else if(level.intValue()==Level.OFF.intValue()){
//			s_log.error(msg);
		}
	}

	private void log(String msg)
	{
		try
		{
			if (msg != null)
			{
				log(LOG_LEVEL(), msg);
			}
		}
		catch (Exception e)
		{
			s_log.error(e.getMessage());
		}
	}

	private void log(MessageContext context)
	{
		log(getStringMessage(context));
	}

	public AxisLoggingHandler()
	{
		timeStamp = "timestamp: " + DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss.SS");
	}

	@Override
	public QName[] getHeaders()
	{
		return null;
	}

	private String getStringMessage(MessageContext context)
	{
		String res = null;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try
		{
			SOAPMessageContext ctx = (SOAPMessageContext) context;

			ctx.getMessage().writeTo(stream);
			byte[] items = stream.toByteArray();
			res = new String(items);
		}
		catch (Exception e)
		{
			if (stream != null)
			{
				try
				{
					stream.close();
				}
				catch (IOException e1)
				{
					s_log.error(e1.getMessage());
				}
			}
		}

		return res;
	}

	@Override
	public boolean handleRequest(MessageContext context)
	{
		log(timeStamp + "_request: \n" + getStringMessage(context));

		return super.handleRequest(context);
	}

	@Override
	public boolean handleResponse(MessageContext context)
	{
		log(timeStamp + "_response: \n" + getStringMessage(context));

		return super.handleResponse(context);
	}

	@Override
	public boolean handleFault(MessageContext context)
	{
		log(timeStamp + "_fault: \n" + getStringMessage(context));
		log(context);
		return super.handleFault(context);
	}

}
