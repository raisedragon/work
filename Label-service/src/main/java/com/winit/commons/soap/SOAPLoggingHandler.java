package com.winit.commons.soap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.TreeSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.soap.SOAPMessage;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winit.label.support.ConfigUtil;

/**
 * @author 
 */
public class SOAPLoggingHandler implements SOAPHandler<SOAPMessageContext>
{
	protected static Logger	log	= LoggerFactory.getLogger(SOAPLoggingHandler.class);

	public Set<QName> getHeaders()
	{
		return new TreeSet<QName>();
	}

	public boolean handleMessage(SOAPMessageContext context)
	{
		log(context);
		return true;
	}

	public boolean handleFault(SOAPMessageContext context)
	{
		log(context);
		return true;
	}

	public void close(MessageContext context)
	{
	}

	private void log(SOAPMessageContext context)
	{
		ByteArrayOutputStream os = null;
		try
		{
			log("timestamp: " + DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss.SS"));
			Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
			os = new ByteArrayOutputStream();
			SOAPMessage message = context.getMessage();

			if (outboundProperty.booleanValue())
			{
				log("Outbound message:");
			}
			else
			{
				log("Inbound message:");
			}

			message.writeTo(os);
			log(new String(os.toByteArray()));
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
		}
		finally
		{
			if (os != null)
			{
				try
				{
					os.close();
				}
				catch (IOException e)
				{
					log.error(e.getMessage());
				}
			}
		}
	}

	private static Level LOG_LEVEL()
	{
		String lev = ConfigUtil.getValue("USPS_INTL_SOAP_LOG_LEVEL", "FINEST");
		try
		{
			return Level.parse(lev);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			return Level.FINEST;
		}
	}

	private void log(Level level, String msg)
	{
		if (level.intValue() == Level.WARNING.intValue())
		{
			log.warn(msg);
		}
		else if (level.intValue() == Level.SEVERE.intValue())
		{
			log.error(msg);
		}
		else if (level.intValue() == Level.FINE.intValue())
		{
			log.info(msg);
		}
		else if (level.intValue() == Level.FINER.intValue())
		{
			log.info(msg);
		}
		else if (level.intValue() == Level.FINEST.intValue())
		{
			log.debug(msg);
		}
		else if (level.intValue() == Level.ALL.intValue())
		{
			log.info(msg);
		}
		else if (level.intValue() == Level.CONFIG.intValue())
		{
			log.warn(msg);
		}
		else if (level.intValue() == Level.OFF.intValue())
		{
			// log.error(msg);
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
			log.error(e.getMessage());
		}
	}

	private void log(MessageContext context)
	{
		log(getStringMessage(context));
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
					log.error(e1.getMessage());
				}
			}
		}

		return res;
	}

}
