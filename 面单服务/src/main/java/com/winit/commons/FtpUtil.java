package com.winit.commons;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import com.winit.exception.LabelSystemException;


/**
 * FTP工具
 * @author bin.zheng
 *
 */
public class FtpUtil
{

	private FTPClient		ftpClient;
	 

	private static Logger logger = Logger.getLogger(FtpUtil.class);  

	public FTPClient getFtpClient()
	{
		return ftpClient;
	}

	public void setFtpClient(FTPClient ftpClient)
	{
		this.ftpClient = ftpClient;
	}

	/**
	 * init ftp servere
	 * @throws Exception 
	 */
	public FtpUtil(String ip,int  port,String  userName,String  userPwd,String  path) throws Exception
	{
		this.reSet(ip, port, userName, userPwd, path);
	}

	public void reSet(String ip,int  port,String  userName,String  userPwd,String  path) throws Exception
	{
		this.connectServer(ip, port, userName, userPwd, path);

	}

	/**
	 * @param ip
	 * @param port
	 * @param userName
	 * @param userPwd
	 * @param path
	 * @throws Exception 
	 * @throws SocketException
	 * @throws IOException function:连接到服务器
	 */
	public void connectServer(String ip, int port, String userName, String userPwd, String path) throws Exception
	{
		ftpClient = new FTPClient();
		try
		{
			// 连接
			ftpClient.setDataTimeout(30000);
			ftpClient.connect(ip, port);
			// 登录
			ftpClient.login(userName, userPwd);

			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.makeDirectory(path);
			if (path != null && path.length() > 0&&!" ".equals(path))
			{
				// 跳转到指定目录
				ftpClient.changeWorkingDirectory(path);
				
			}
			ftpClient.enterLocalPassiveMode();
			
		}
		catch (Exception e)
		{
			logger.error(e);
			throw new LabelSystemException("FTP error: " + e.getMessage());
			
		}
	}

	/**
	 * @throws Exception 
	 * @throws IOException function:关闭连接
	 */
	public void closeServer() throws Exception
	{
		try
		{
		if (ftpClient.isConnected())
		{
				ftpClient.logout();
				ftpClient.disconnect();
			}
		}
		catch (Exception e)
		{
			logger.error(e);
			throw new LabelSystemException("FTP error: " + e.getMessage());
		}
	}

	/** 得到ftp客户端的输出流 */
	public boolean uploadLable(String uploadFile, byte[] byt) throws IOException
	{
		// 因为已经进入到了服务的目录，只要在目录下建一个文件就行，所以只要文件名，不要路径
		// FileInputStream input = new FileInputStream(file2);
		InputStream input = new ByteArrayInputStream(byt);
		boolean  bb=ftpClient.storeFile(uploadFile, input);
		input.close();
		return bb;
	}
	
	/**
	 * @param path newpath
	 * @return function:备份方法
	 * @throws IOException
	 */
	public boolean rename(String path,String newpath) throws IOException
	{
		// 备份方法
		return ftpClient.rename(path, newpath);
	}

	
	/**
	 * @param path
	 * @return function:读取指定目录下的文件名
	 * @throws IOException
	 */
	public List<String> getFileList(String path)
	{
		List<String> fileLists = new ArrayList<String>();
		// 获得指定目录下所有文件名
		FTPFile[] ftpFiles = null;
		try
		{
			ftpClient.enterLocalPassiveMode();
			ftpFiles = ftpClient.listFiles(path);
		}
		catch (IOException e)
		{
			logger.error(e);
		}
		for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++)
		{
			FTPFile file = ftpFiles[i];
			if (file.isFile())
			{
				fileLists.add(file.getName());
			}
		}
		return fileLists;
	}
	
	public static void main(String[] args)
	{
		try
		{
			FtpUtil ftp=new FtpUtil("192.168.130.219", 21, "winit", "Winit2014", "bPost/DATAIN/");
			ftp.rename("11.txt", "../../RoyalMail/backup/22.txt");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}