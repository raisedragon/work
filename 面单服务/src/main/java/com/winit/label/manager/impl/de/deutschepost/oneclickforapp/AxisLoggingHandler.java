package com.winit.label.manager.impl.de.deutschepost.oneclickforapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.GenericHandler;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winit.label.support.ConfigUtil;

public class AxisLoggingHandler extends GenericHandler
{

	private String				timeStamp;

	private static Logger		s_log				= LoggerFactory.getLogger(AxisLoggingHandler.class);

	private static Level LOG_LEVEL()
	{
		String lev = ConfigUtil.getValue("USPS_INTL_SOAP_LOG_LEVEL", "FINE");
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

	private void log(String msg)
	{
		try
		{
			if (msg != null)
			{
				if(LOG_LEVEL()==Level.INFO||LOG_LEVEL()==Level.CONFIG)
					s_log.info(msg);
				else if(LOG_LEVEL()==Level.ALL||LOG_LEVEL()==Level.FINE||LOG_LEVEL()==Level.FINER||LOG_LEVEL()==Level.FINEST)
					s_log.debug(msg);
				else if(LOG_LEVEL()==Level.WARNING)
					s_log.debug(msg);
				else if(LOG_LEVEL()==Level.SEVERE)
					s_log.debug(msg);
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
