package com.winit.commons.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于https请求的SocketFactory
 * @author temuser2
 *
 */
public class DefaultSecureProtocolSocketFactory implements SecureProtocolSocketFactory {

	private static Logger s_log = LoggerFactory.getLogger(DefaultSecureProtocolSocketFactory.class);
	
	private SSLContext context = null;

	private SSLContext createSSLContext() {
		SSLContext context = null;
		try {
			context = SSLContext.getInstance("SSL");
			context.init(null, new TrustManager[] { new TrustAllManager() }, new SecureRandom());
			
			List<String> limited = new LinkedList<String>();
			SSLSocket	s = (SSLSocket)context.getSocketFactory().createSocket();
			for(String suite : ((SSLSocket)s).getEnabledCipherSuites())
			{
//			    if(!suite.contains("_DHE_"))
//			    {
			        limited.add(suite);
//			    }
			}
			((SSLSocket)s).setEnabledCipherSuites(limited.toArray(
			    new String[limited.size()]));
		} catch (Exception e) {
			s_log.error(e.getMessage());
		}
		return context;
	}

	private SSLContext getSSLContext() {
		if (this.context == null) {
			this.context = createSSLContext();
		}
		return this.context;
	}

	public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
		return getSSLContext().getSocketFactory().createSocket(socket, host, port, autoClose);
	}

	public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
		return getSSLContext().getSocketFactory().createSocket(host, port);
	}

	public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort) throws IOException, UnknownHostException {
		return getSSLContext().getSocketFactory().createSocket(host, port, clientHost, clientPort);
	}

	public Socket createSocket(String host, int port, InetAddress localAddress, int localPort, HttpConnectionParams params) throws IOException, UnknownHostException, ConnectTimeoutException {
		if (params == null) {
			throw new IllegalArgumentException("Parameters may not be null");
		}
		int timeout = params.getConnectionTimeout();
		SocketFactory factory = getSSLContext().getSocketFactory();
		if (timeout == 0) {
			return factory.createSocket(host, port, localAddress, localPort);
		} else {
			Socket socket = factory.createSocket();
			SocketAddress localAddr = new InetSocketAddress(localAddress, localPort);
			SocketAddress remoteAddr = new InetSocketAddress(host, port);
			socket.bind(localAddr);
			socket.connect(remoteAddr, timeout);
			return socket;
		}
	}

	/**
	 * 信任所有站点的证书
	 * @author temuser2
	 *
	 */
	private static class TrustAllManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}
}
