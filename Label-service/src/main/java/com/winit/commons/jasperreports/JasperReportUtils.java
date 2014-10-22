package com.winit.commons.jasperreports;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.export.JRGraphics2DExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winit.exception.LabelException;
import com.winit.label.support.ConfigUtil;



public class JasperReportUtils
{
	/** Logger */
	private static Logger log = LoggerFactory.getLogger(JasperReportUtils.class);
	private static String KEY_BASE_JASPER_PATH = "WT_JASPER_REPORT_DIR";
	private static JasperPrint jasperPrint;

	private static File REPORT_HOME(){
		String path = ConfigUtil.getValue(KEY_BASE_JASPER_PATH);
    	return new File(path);
	}



    public static JasperPrint getJasperPrint()
	{
		return jasperPrint;
	}




	/**
     * @author alinv
     * @param reportPath
     * @param reportType
     * @return the abstract file corresponding to typed report
     */
	protected static File getReportFile(String reportPath, String reportType) {

		if (reportType != null)
		{
			int cpos = reportPath.lastIndexOf('.');
			reportPath = reportPath.substring(0, cpos) + "_" + reportType + reportPath.substring(cpos, reportPath.length());
		}

		return getReportFile(reportPath);
	}

	/**
	 * @author alinv
	 * @param reportPath
	 * @return the abstract file corresponding to report
	 */
	protected static File getReportFile(String reportPath)
	{
		File reportFile = new File(REPORT_HOME(), reportPath);
		if(!reportFile.exists()){
			throw new LabelException("JasperReport template file ["+reportPath+"] not found");
		}
		return reportFile;
	}

	/**
     * @author rlemeill
     * @param reportFile
     * @return
     */
    protected static JasperData processReport( File reportFile) {
        log.info( "reportFile.getAbsolutePath() = "+reportFile.getAbsolutePath());
        JasperReport jasperReport = null;

        String jasperName = reportFile.getName();
        int pos = jasperName.indexOf('.');
        if (pos!=-1) jasperName = jasperName.substring(0, pos);
        File reportDir = reportFile.getParentFile();

        //test if the compiled report exists
        File jasperFile = new File( reportDir.getAbsolutePath(), jasperName+".jasper");
        if (jasperFile.exists()) { // test time
            if (reportFile.lastModified() == jasperFile.lastModified()) {
            	log.info(" no need to compile use "+jasperFile.getAbsolutePath());
                try {
                	
                    jasperReport = (JasperReport)JRLoader.loadObjectFromFile(jasperFile.getAbsolutePath());
                } catch (JRException e) {
                    jasperReport = null;
                    log.error("Can not load report - "+ e.getMessage());
                }
            } else {
                jasperReport = compileReport( reportFile, jasperFile);
            }
        } else { // create new jasper file
            jasperReport = compileReport( reportFile, jasperFile);
        }

        return new JasperData( jasperReport, reportDir, jasperName, jasperFile);
    }

    /**
     * @author rlemeill
     * Correct the class path if loaded from java web start
     */
    private static void JWScorrectClassPath()
    {
		URL jasperreportsAbsoluteURL = Thread.currentThread().getContextClassLoader().getResource("net/sf/jasperreports/engine");
		String jasperreportsAbsolutePath = "";

		if(jasperreportsAbsoluteURL.toString().startsWith("jar:http:") || jasperreportsAbsoluteURL.toString().startsWith("jar:https:"))
		{
			// Jasper classes are deployed to a webserver (Java Webstart)
			jasperreportsAbsolutePath = jasperreportsAbsoluteURL.toString().split("!")[0].split("jar:")[1];

			// Download the required jasper libraries if they are not already existing
			File reqLib = new File(System.getProperty("java.io.tmpdir"), "CompiereJasperReqs.jar");
			if(!reqLib.exists() && !(reqLib.length() > 0))
			{
				try{
					URL reqLibURL = new URL(jasperreportsAbsolutePath);
					InputStream in = reqLibURL.openStream();

					FileOutputStream fout = new FileOutputStream(reqLib);

					byte buf[] = new byte[1024];
					int s = 0;

					while((s = in.read(buf, 0, 1024)) > 0)
						fout.write(buf, 0, s);

					in.close();
					fout.flush();
					fout.close();
				} catch (FileNotFoundException e) {
					log.warn("Required library not found "+ e.getMessage());
					reqLib.delete();
					reqLib = null;
				} catch (IOException e) {
					log.error("I/O error downloading required library from server "+ e.getMessage());
					reqLib.delete();
					reqLib = null;
				}
			}

			jasperreportsAbsolutePath = reqLib.getAbsolutePath();
		}
		else
		{
			// Jasper classes are locally available (Local client)
			jasperreportsAbsolutePath = jasperreportsAbsoluteURL.toString().split("!")[0].split("file:")[1];
		}

		if(jasperreportsAbsolutePath != null && !jasperreportsAbsolutePath.trim().equals(""))
		{
			// Check whether the current CLASSPATH already contains our
			// jasper libraries and dependencies or not.
			if(System.getProperty("java.class.path").indexOf(jasperreportsAbsolutePath) < 0)
			{
			System.setProperty("java.class.path",
					System.getProperty("java.class.path") +
					System.getProperty("path.separator") +
					jasperreportsAbsolutePath);
			log.info("Classpath has been corrected to " + System.getProperty("java.class.path"));
			}
		}
    }

    /**
     * @author rlemeill
     * @param reportFile
     * @param jasperFile
     * @return compiled JasperReport
     */
    private static JasperReport compileReport( File reportFile, File jasperFile)
    {
    	JWScorrectClassPath();
        JasperReport compiledJasperReport = null;
        try {
          	JasperCompileManager.compileReportToFile ( reportFile.getAbsolutePath(), jasperFile.getAbsolutePath() );
            jasperFile.setLastModified( reportFile.lastModified()); //Synchronize Dates
            compiledJasperReport =  (JasperReport)JRLoader.loadObject(jasperFile);
        } catch (JRException e) {
            log.error("Error", e);
        }
        return compiledJasperReport;
    }

  


    
    public static String generateImageAsBase64(String reportFile, Map param) throws Exception {
	    	File file = getReportFile(reportFile);
			JasperData jasperData = processReport(file);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperData.getJasperReport(), param, new JREmptyDataSource());
			BufferedImage pageImage = new BufferedImage(jasperPrint.getPageWidth(), jasperPrint.getPageHeight(), BufferedImage.TYPE_INT_RGB);
	        JRGraphics2DExporter exporter = new JRGraphics2DExporter();
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporter.setParameter(JRGraphics2DExporterParameter.GRAPHICS_2D, pageImage.getGraphics());
	        exporter.setParameter(JRExporterParameter.PAGE_INDEX, new Integer(0));
	        exporter.exportReport();
	        
	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	        ImageIO.write(pageImage, "png", os);
	        byte[] data = Base64.encodeBase64(os.toByteArray());
	        String base64 = new String(data, "utf-8");
	        os.close();
			
			return base64;
    }
    
    public static String generatePdfAsBase64(String reportFile, Map param) throws Exception {
	    	ByteArrayOutputStream os = new ByteArrayOutputStream();
	    	
	    	File file = getReportFile(reportFile);
			JasperData jasperData = processReport(file);
	    	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperData.getJasperReport(), param, new JREmptyDataSource());
	
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, os);
			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.exportReport();
			
			byte[] data = Base64.encodeBase64(os.toByteArray());
	        String base64 = new String(data, "utf-8");
	        os.close();
			
			return base64;
    }
    
    public void generatePDF(String reportFile, Map param, String pdfFile) throws Exception {
    	FileOutputStream fos=null;
    	try 
    	{
	    	File file = getReportFile(reportFile);
			JasperData jasperData = processReport(file);
	    	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperData.getJasperReport(), param, new JREmptyDataSource());
	    	fos = new FileOutputStream(pdfFile);
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.exportReport();
	    }
    	finally
    	{
    		if(fos!=null){
    			fos.close();
    		}
    	}
    }
    
  

    class ReportData {
        private String reportFilePath;
        private boolean directPrint;
        private boolean Subscription;

        public ReportData(String reportFilePath, boolean directPrint, boolean Subscription) {
            this.reportFilePath = reportFilePath;
            this.directPrint = directPrint;
            this.Subscription = Subscription;
        }

        public String getReportFilePath() {
            return reportFilePath;
        }

        public boolean isDirectPrint() {
            return directPrint;
        }
        
        public boolean isSubscription() {
            return Subscription;
        }
    }

    public static class JasperData
    implements Serializable
    {
		/**
		 *
		 */
		private static final long serialVersionUID = 4375195020654531202L;
		private JasperReport jasperReport;
        private File reportDir;
        private String jasperName;
        private File jasperFile;

        public JasperData(JasperReport jasperReport, File reportDir, String jasperName, File jasperFile) {
            this.jasperReport = jasperReport;
            this.reportDir = reportDir;
            this.jasperName = jasperName;
            this.jasperFile = jasperFile;
        }

        public JasperReport getJasperReport() {
            return jasperReport;
        }

        public File getReportDir() {
            return reportDir;
        }

        public String getJasperName() {
            return jasperName;
        }

        public File getJasperFile() {
            return jasperFile;
        }
    }

    class FileFilter implements FilenameFilter {
        private String reportStart;
        private File directory;
        private String extension;

        public FileFilter(String reportStart, File directory, String extension) {
            this.reportStart = reportStart;
            this.directory = directory;
            this.extension = extension;
        }

        public boolean accept(File file, String name) {
            if (file.equals( directory)) {
                if (name.startsWith( reportStart)) {
                    int pos = name.lastIndexOf( extension);
                    if ( (pos!=-1) && (pos==(name.length()-extension.length()))) return true;
                }
            }
            return false;
        }
    }

}