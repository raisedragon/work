package com.winit.commons.http;


import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import javax.net.ssl.SSLSocketFactory;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;

/**
 * 
 * <p>
 * Wraps a SSLSocketFactory with a SecureProtocolSocketFactory.
 * <p>
 * This was designed to make HttpClient work in situations where an application is being deployed by
 * Java Web Start. In these cases, SSL connections are negotiated by webstart implementations of the
 * KeyManager and TrustManager. Wrapping the socket factory obtained from
 * HttpsURLConnection.getDefaultSocketFactory allows the use of HttpClient while still leveraging
 * Java Web Start's handling of SSL certificates
 */
public class SocketFactoryWrapper implements SecureProtocolSocketFactory {

    private SSLSocketFactory socketFactory;

    public SocketFactoryWrapper(SSLSocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
        return socketFactory.createSocket(host, port);
    }

    public Socket createSocket(String host, int port, InetAddress localAddress, int localPort)
            throws IOException, UnknownHostException {
        return socketFactory.createSocket(host, port, localAddress, localPort);
    }

    public Socket createSocket(
            String host, 
            int port, InetAddress localAddress, int localPort,
            HttpConnectionParams params) throws IOException, UnknownHostException,
            ConnectTimeoutException {
        // Based on code from EasySSLProtocolSocketFactory.java
        Socket rval;
        if (params == null) {
            throw new IllegalArgumentException("Parameters may not be null");
        }
        int timeout = params.getConnectionTimeout();
        if (timeout == 0) {
            rval = socketFactory.createSocket(host, port, localAddress, localPort);
        } else {
            rval = socketFactory.createSocket();
            SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
            SocketAddress remoteaddr = new InetSocketAddress(host, port);
            rval.bind(localaddr);
            rval.connect(remoteaddr, timeout);
        }
        return rval;
    }

    public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
            throws IOException, UnknownHostException {
        return socketFactory.createSocket(socket, host, port, autoClose);
    }

}