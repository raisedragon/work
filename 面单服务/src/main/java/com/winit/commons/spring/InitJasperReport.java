package com.winit.commons.spring;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.winit.exception.LabelException;
import com.winit.label.support.ConfigUtil;

public class InitJasperReport implements ApplicationListener<ContextRefreshedEvent>
{
	private static Logger		log									= LoggerFactory.getLogger(InitJasperReport.class);

	private static final String	KEY_BASE_JASPER_PATH				= "WT_JASPER_REPORT_DIR";

	private static final String	JASPER_REPORT_CLASS_PATH_LOCATION	= "jasperreports/*";

	public void onApplicationEvent(ContextRefreshedEvent event)
	{
		try
		{
			if (event.getApplicationContext().getParent() == null)
			{// root application context 没有parent，他就是老大.
				String jasperDir = ConfigUtil.getValue(KEY_BASE_JASPER_PATH);
				if (StringUtils.isEmpty(jasperDir))
				{
					throw new LabelException("Jasper Report temporary directory not set.[key:" + KEY_BASE_JASPER_PATH
							+ "]");
				}
				File file = new File(jasperDir);
				if (!file.exists())
				{
					log.warn("Jasper Report temporary directory not exists");
					log.warn("Create directory '" + jasperDir + "'......");
					if (!file.mkdirs())
					{
						log.warn("Create directory '" + jasperDir + "' failure");
						throw new LabelException("Jasper Report temporary directory not exists");
					}
				}
				else
				{
					if (!file.isDirectory())
					{
						throw new LabelException("'" + jasperDir
								+ "' is not a directory, which can not used as Jasper Report temporary directory.");
					}
				}

				FileUtils.cleanDirectory(file);

				ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
				Resource[] resources = resolver.getResources(JASPER_REPORT_CLASS_PATH_LOCATION);
				for (Resource resource : resources)
				{
					String filename = resource.getFilename();
					if (StringUtils.isNotEmpty(filename))
					{
						FileOutputStream fos = new FileOutputStream(new File(file, filename));
						IOUtils.copy(resource.getInputStream(), fos);
						fos.close();
					}
				}
			}
		}
		catch (Exception e)
		{
			throw new LabelException(e);
		}

	}
}