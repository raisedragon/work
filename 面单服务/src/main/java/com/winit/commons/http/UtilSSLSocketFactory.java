package com.winit.commons.http;


import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedList;
import java.util.List;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Utility for doing various useful things to an SSL socket factory.
 */
public class UtilSSLSocketFactory extends SSLSocketFactory {
	protected SSLSocketFactory wrappedFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
	protected boolean trustingAllCertificates = false;
	protected boolean diffieHellmanDisabled = false;
	protected boolean wrappedFactoryChanged = false;

	/**
	 * By default, trust ALL certificates. <b>This is <i>very</i> insecure.</b>
	 * It also defeats one of the points of SSL: Making sure your connecting to
	 * the right server. Cannot be combined with {@link #disableDiffieHellman(javax.net.ssl.SSLSocketFactory)
	 * } since this overwrites the wrapped factory and will throw an exception!
	 *
	 * @return The current UtilSSLSocketFactory instance
	 */
	public UtilSSLSocketFactory trustAllCertificates() {
		if (wrappedFactoryChanged)
			throw new RuntimeException("Cannot combine trustAllCertificates() and disableDiffieHellman(SSLSocketFactory)");
		if (trustingAllCertificates)
			//Already doing this, no need to do it again
			return this;
		trustingAllCertificates = true;
		try {
			TrustManager[] tm = new TrustManager[]{new TrustingX509TrustManager()};
			SSLContext context = SSLContext.getInstance("SSL");
			context.init(new KeyManager[0], tm, new SecureRandom());
			wrappedFactory = context.getSocketFactory();
		} catch (Exception e) {
			throw new RuntimeException("Can't recreate socket factory that trusts all certificates", e);
		}
		return this;
	}

	/**
	 * Disable the Diffie Hellman key exchange algorithm. This is useful to work
	 * around JDK bug #6521495 which throws an Exception when prime sizes are
	 * above 1024 bits.
	 * <p>
	 * Note that this requires that the server supports other key exchange
	 * algorithms. This socket factory (nor any other built in Socket Factory)
	 * cannot connect to a server that only supports Diffie Hellman key exchange
	 * with prime sizes larger than 1024 bits.
	 * <p>
	 * Also see PircBotX Issue #34
	 *
	 * @return The current UtilSSLSocketFactory instance
	 */
	public UtilSSLSocketFactory disableDiffieHellman() {
		diffieHellmanDisabled = true;
		return this;
	}

	/**
	 * Disable the Diffie Hellman key exchange algorithm using the provided SSL
	 * socket factory. Cannot be combined with {@link #disableDiffieHellman(javax.net.ssl.SSLSocketFactory)
	 * } since this overwrites the wrapped factory and will throw an exception!
	 *
	 * @see #disableDiffieHellman()
	 * @param sourceSocketFactory
	 * @return The current UtilSSLSocketFactory instance
	 */
	public UtilSSLSocketFactory disableDiffieHellman(SSLSocketFactory sourceSocketFactory) {
		if (trustingAllCertificates)
			throw new RuntimeException("Cannot combine trustAllCertificates() and disableDiffieHellman(SSLSocketFactory)");
		wrappedFactory = sourceSocketFactory;
		wrappedFactoryChanged = true;
		return disableDiffieHellman();
	}

	protected SSLSocket prepare(Socket socket) {
		SSLSocket sslSocket = (SSLSocket) socket;
		if (diffieHellmanDisabled) {
			List<String> limited = new LinkedList<String>();
			for (String suite : sslSocket.getEnabledCipherSuites())
				if (!suite.contains("_DHE_"))
					limited.add(suite);
			sslSocket.setEnabledCipherSuites(limited.toArray(new String[limited.size()]));
		}
		return sslSocket;
	}

	@Override
	public SSLSocket createSocket(String host, int port) throws IOException, UnknownHostException {
		return prepare(wrappedFactory.createSocket(host, port));
	}

	@Override
	public SSLSocket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
		return prepare(wrappedFactory.createSocket(host, port, localHost, localPort));
	}

	@Override
	public SSLSocket createSocket(InetAddress address, int port) throws IOException {
		return prepare(wrappedFactory.createSocket(address, port));
	}

	@Override
	public SSLSocket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
		return prepare(wrappedFactory.createSocket(address, port, localAddress, localPort));
	}

	@Override
	public SSLSocket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
		return prepare(wrappedFactory.createSocket(s, host, port, autoClose));
	}

//	@Override
//	public SSLSocket createSocket(Socket socket, InputStream in, boolean bln) throws IOException {
//		return prepare(wrappedFactory.createSocket(socket, in, bln));
//	}

	@Override
	public SSLSocket createSocket() throws IOException {
		return prepare(wrappedFactory.createSocket());
	}

	@Override
	public String[] getDefaultCipherSuites() {
		return wrappedFactory.getDefaultCipherSuites();
	}

	@Override
	public String[] getSupportedCipherSuites() {
		return wrappedFactory.getSupportedCipherSuites();
	}

	/**
	 * X509TrustManager that trusts all certificates. <b>This is very
	 * insecure</b>
	 */
	public static class TrustingX509TrustManager implements X509TrustManager {
		/**
		 * Doesn't throw an exception, so this is how it approves a certificate.
		 *
		 * @see
		 * javax.net.ssl.X509TrustManager#checkClientTrusted(java.security.cert.X509Certificate[],
		 * String)
		 *
		 */
		public void checkClientTrusted(X509Certificate[] cert, String authType) throws CertificateException {
		}

		/**
		 * Doesn't throw an exception, so this is how it approves a certificate.
		 *
		 * @see
		 * javax.net.ssl.X509TrustManager#checkServerTrusted(java.security.cert.X509Certificate[],
		 * String)
		 *
		 */
		public void checkServerTrusted(X509Certificate[] cert, String authType) throws CertificateException {
		}

		/**
		 * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
		 *
		 */
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}
	}
}
