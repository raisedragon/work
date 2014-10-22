package com.winit.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;


/**
 * 处理请求XML及XML解析公共类
 * 
 * @author jianfa.zhuang
 */
public class XmlHttpRequestHelper
{

	// 对应 XMLHttpRequest 的5种状态.
	public static final int				STATE_UNINITIALIZED		= 0;
	public static final int				STATE_LOADING			= 1;
	public static final int				STATE_LOADED			= 2;
	public static final int				STATE_INTERACTIVE		= 3;
	public static final int				STATE_COMPLETE			= 4;

	// 默认的 userAgent
	public static final String			DEFAULT_USERAGENT		= "shippergate_socket/1";

	// 默认的 编码
	public static final String			DEFAULT_AJAX_CHARSET	= "UTF-8";
	public static final String			DEFAULT_HTTP_CHARSET	= "ISO-8859-1";

	// 默认的 HTTP method
	public static final String			DEFAULT_REQUEST_METHOD	= "POST";

	private int							readyState;
	private int							status;
	private String						statusText;
	private String						responseHeaders;
	private byte[]						responseBytes;
	private Map<String, List<String>>	responseHeadersMap;
	private Map<String, Object>			requestHeadersMap;
	private ReadyStateChangeListener	readyStateChangeListener;

	private boolean						async;
	private boolean						sent;
	private URLConnection				connection;
	private String						userAgent				= DEFAULT_USERAGENT;
	private String						postCharset				= DEFAULT_AJAX_CHARSET;
	private Proxy						proxy;

	private URL							requestURL;
	protected String					requestMethod;
	protected String					requestUserName;
	protected String					requestPassword;

	/**
	 * 构造方法. 自动添加 XMLHttpRequest 的一些缺省头信息. 如果不需要这些头信息,可在创建 XmlHttpRequestHelper
	 * 实例后, 通过 setRequestHeader/removeRequestHeader/removeAllRequestHeaders 方法
	 * 进行修改或移除.
	 */
	public XmlHttpRequestHelper()
	{
		requestHeadersMap = new LinkedHashMap<String, Object>();
		setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		setRequestHeader("Accept", "*/*");
	}

	/**
	 * 类似 XMLHttpRequest 中的 readyState 属性.
	 */
	public  int getReadyState()
	{
		return this.readyState;
	}

	/**
	 * 类似 XMLHttpRequest 中的 status 属性.
	 */
	public  int getStatus()
	{
		return this.status;
	}

	/**
	 * 类似 XMLHttpRequest 中的 statusText 属性.
	 */
	public  String getStatusText()
	{
		return this.statusText;
	}

	/**
	 * 类似 XMLHttpRequest 中的 setRequestHeader 方法.
	 */
	public void setRequestHeader(String key, String value)
	{
		this.requestHeadersMap.put(key, value);
	}

	/**
	 * 类似 XMLHttpRequest 中的 open 方法.
	 */
	public void open(String method, String url, boolean async, String userName, String password) throws IOException
	{
		URL urlObj = createURL(null, url);
		open(method, urlObj, async, userName, password);
	}

	/**
	 * 类似 XMLHttpRequest 中的 open 方法.
	 */
	public void open(final String method, final URL url, boolean async, final String userName, final String password)
			throws IOException
	{
		this.abort();
		Proxy proxy = this.proxy;
		URLConnection c = proxy == null || proxy == Proxy.NO_PROXY ? url.openConnection() : url.openConnection(proxy);
		
			this.connection = c;
			this.async = async;
			this.requestMethod = method;
			this.requestURL = url;
			this.requestUserName = userName;
			this.requestPassword = password;
		
		this.changeState(XmlHttpRequestHelper.STATE_LOADING, 0, null, null);
	}

	/**
	 * 类似 XMLHttpRequest 中的 open 方法. 省略部分参数的形式.
	 */
	public void open(String url, boolean async) throws IOException
	{
		open(DEFAULT_REQUEST_METHOD, url, async, null, null);
	}

	/**
	 * 类似 XMLHttpRequest 中的 open 方法. 省略部分参数的形式.
	 */
	public void open(String method, String url, boolean async) throws IOException
	{
		open(method, url, async, null, null);
	}

	/**
	 * 类似 XMLHttpRequest 中的 send 方法. 支持发送 key-value 形式的数据集合(Map). 传入map参数,
	 * 自动转换成string形式 并调用 send(String) 方法发送.
	 */
	public void send(Map<String, Object> parameters) throws IOException
	{
		Iterator<String> keyItr = parameters.keySet().iterator();
		StringBuffer strb = new StringBuffer();
		while (keyItr.hasNext())
		{
			Object key = keyItr.next();
			String keyStr = encode(key);
			String valueStr = encode(parameters.get(key));
			strb.append(keyStr).append("=").append(valueStr);
			strb.append("&");
		}
		//打印参数
//		System.out.println(strb.toString());
		send(strb.toString());
	}

	/**
	 * 类似 XMLHttpRequest 中的 send 方法.
	 */
	public void send(final String content) throws IOException
	{
		final URL url = this.requestURL;
		if (url == null)
		{
			throw new IOException("No URL has been provided.");
		}
		if (this.isAsync())
		{
			new Thread("XmlHttpRequestHelper-" + url.getHost()) {
				public void run()
				{
					try
					{
						sendSync(content);
					}
					catch (Throwable thrown)
					{
						log(Level.WARNING, "send(): Error in asynchronous request on " + url, thrown);
					}
				}
			}.start();
		}
		else
		{
			sendSync(content);
		}
	}

	/**
	 * 类似 XMLHttpRequest 中的 getResponseHeader 方法.
	 */
	public  List<String> getResponseHeader(String headerName)
	{
		return this.responseHeadersMap == null ? null : this.responseHeadersMap.get(headerName);
	}

	/**
	 * 类似 XMLHttpRequest 中的 getAllResponseHeaders 方法.
	 */
	public  String getAllResponseHeaders()
	{
		return this.responseHeaders;
	}

	/**
	 * 类似 XMLHttpRequest 中的 responseText 属性.
	 */
	public  String getResponseText()
	{
		byte[] bytes = this.responseBytes;
		String encoding = getCharset(this.connection);
		if (encoding == null)
		{
			encoding = getPostCharset();
		}
		if (encoding == null)
		{
			encoding = DEFAULT_HTTP_CHARSET;
		}
		try
		{
			return bytes == null ? null : new String(bytes, encoding);
		}
		catch (UnsupportedEncodingException uee)
		{
			log(Level.WARNING, "getResponseText(): Charset '" + encoding + "' did not work. Retrying with "
					+ DEFAULT_HTTP_CHARSET + ".", uee);
			try
			{
				return new String(bytes, DEFAULT_HTTP_CHARSET);
			}
			catch (UnsupportedEncodingException uee2)
			{
				// Ignore this time
				return null;
			}
		}
	}

	/**
	 * 类似 XMLHttpRequest 中的 responseBody 属性.
	 * 
	 * @deprecated 这个方法命名源自XMLHttpRequest中的responseBody属性. 不过这个名字并不是好名字.建议使用
	 *             getResponseBytes 方法代替之.
	 */
	public  byte[] getResponseBody()
	{
		return this.getResponseBytes();
	}

	/**
	 * 类似 XMLHttpRequest 中的 responseBody 属性. 建议使用此方法代替 getResponseBody 方法.
	 */
	public  byte[] getResponseBytes()
	{
		return this.responseBytes;
	}

	/**
	 * 与 XMLHttpRequest 中的 responseStream 属性对应的方法 暂不提供. 因为1 不常用 2 通常可用
	 * getResponseBytes 方法代替
	 */

	/**
	 * 类似 XMLHttpRequest 中的 onreadystatechange 属性. 设置一个监听器,用来跟踪HttpRequest状态变化.
	 * 参数是一个 ReadyStateChangeListener 对象. ReadyStateChangeListener 是一个抽象类. 只需 实现
	 * onReadyStateChange方法即可.
	 */

	public void setReadyStateChangeListener(ReadyStateChangeListener listener)
	{
		this.readyStateChangeListener = listener;
	}

	/**
	 * 中断 Request 请求. 类似 XMLHttpRequest 中的 abort.
	 */
	public void abort()
	{
		URLConnection c = null;
		 
			c = this.getConnection();
		if (c instanceof HttpURLConnection)
		{
			((HttpURLConnection) c).disconnect();
		}
		else if (c != null)
		{
			try
			{
				c.getInputStream().close();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}

	// /////////////////////////////////////////////
	// // 以上 为 模拟 XMLHttpRequest 对象 相关的方法 ////
	// /////////////////////////////////////////////
	// /////////////////////////////////////////////

	/**
	 * 返回 此次HttpRequest是否是"异步"的.
	 */
	public boolean isAsync()
	{
		return async;
	}

	/**
	 * 返回 此次HttpRequest是否已经发送. 已经发送 且还没有完全处理完此次发送的返回信息时,是不能再次发送的. 如果需要联系发送请求,
	 * 请再另行创建一个 XmlHttpRequestHelper对象.
	 */
	public boolean hasSent()
	{
		return sent;
	}

	protected void setSent(boolean sent)
	{
		this.sent = sent;
	}

	/**
	 * 设置/取得 伪造的 userAgent 信息.通常不用理会. 很少有http服务会对此做严格的判断.
	 */
	public void setUserAgent(String userAgent)
	{
		this.userAgent = userAgent;
	}

	public String getUserAgent()
	{
		return this.userAgent;
	}

	/**
	 * 取得/设置 默认的 AJAX编码.AJAX请求都是UTF-8编码 此属性通常无需改变.
	 */
	public String getPostCharset()
	{
		return this.postCharset;
	}

	public void setPostCharset(String postCharset)
	{
		this.postCharset = postCharset;
	}

	/**
	 * 实现发送数据功能的方法,是整个类的核心. 我(fins)借鉴了 cobra 组件的
	 * org.lobobrowser.html.test.SimpleHttpRequest 类中的同名方法. 略作改动.
	 */
	protected void sendSync(String content) throws IOException
	{
		if (hasSent())
		{
			log(Level.WARNING, "This XmlHttpRequestHelper Object has sent", null);
			return;
		}
		try
		{
			URLConnection c;
			c = this.connection;
			
			if (c == null)
			{
				log(Level.WARNING, "Please open XmlHttpRequestHelper first.", null);
				return;
			}
			setSent(true);
			initConnectionRequestHeader(c);
			int istatus;
			String istatusText;
			InputStream err;
			if (c instanceof HttpURLConnection)
			{
				HttpURLConnection hc = (HttpURLConnection) c;
				String method = this.requestMethod == null ? DEFAULT_REQUEST_METHOD : this.requestMethod;

				method = method.toUpperCase();
				hc.setRequestMethod(method);
				if ("POST".equals(method) && content != null)
				{
					hc.setDoOutput(true);
					byte[] contentBytes = content.getBytes(this.getPostCharset());
					hc.setFixedLengthStreamingMode(contentBytes.length);
					OutputStream out = hc.getOutputStream();
					try
					{
						out.write(contentBytes);
					}
					finally
					{
						out.flush();
					}
				}
				istatus = hc.getResponseCode();
				istatusText = hc.getResponseMessage();
				err = hc.getErrorStream();
			}
			else
			{
				istatus = 0;
				istatusText = "";
				err = null;
			}
			
				this.responseHeaders = getConnectionResponseHeaders(c);
				this.responseHeadersMap = c.getHeaderFields();
			
			this.changeState(XmlHttpRequestHelper.STATE_LOADED, istatus, istatusText, null);
			InputStream in = err == null ? c.getInputStream() : err;
			int contentLength = c.getContentLength();

			this.changeState(XmlHttpRequestHelper.STATE_INTERACTIVE, istatus, istatusText, null);
			byte[] bytes = loadStream(in, contentLength == -1 ? 4096 : contentLength);
			this.changeState(XmlHttpRequestHelper.STATE_COMPLETE, istatus, istatusText, bytes);
		}
		finally
		{
			
				this.connection = null;
				setSent(false);
			
		}
	}

	/**
	 * 当状态变化时 重新设置各种状态值,并触发状态变化监听器.
	 */
	protected void changeState(int readyState, int status, String statusMessage, byte[] bytes)
	{
		
			this.readyState = readyState;
			this.status = status;
			this.statusText = statusMessage;
			this.responseBytes = bytes;
		if (this.readyStateChangeListener != null)
		{
			this.readyStateChangeListener.onReadyStateChange();

		}
	}

	/**
	 * 对字符串进行 URLEncoder 编码.
	 */
	protected String encode(Object str)
	{
		try
		{
			return URLEncoder.encode(String.valueOf(str), getPostCharset());
		}
		catch (UnsupportedEncodingException e)
		{
			return String.valueOf(str);
		}
	}

	/**
	 * 将设置的 RequestHeader 真正的设置到链接请求中.
	 */
	protected void initConnectionRequestHeader(URLConnection c)
	{
		c.setRequestProperty("User-Agent", this.getUserAgent());
		Iterator<String> keyItor = this.requestHeadersMap.keySet().iterator();
		while (keyItor.hasNext())
		{
			String key = (String) keyItor.next();
			String value = (String) this.requestHeadersMap.get(key);
			c.setRequestProperty(key, value);
		}
	}

	/**
	 * 以下 4个 方法 负责处理 requestHeader信息.
	 */
	public String getRequestHeader(String key)
	{
		return (String) this.requestHeadersMap.get(key);
	}

	public String removeRequestHeader(String key)
	{
		return (String) this.requestHeadersMap.remove(key);
	}

	public void removeAllRequestHeaders()
	{
		this.requestHeadersMap.clear();
	}

	public Map<String, Object> getAllRequestHeaders()
	{
		return this.requestHeadersMap;
	}

	public URLConnection getConnection()
	{
		return connection;
	}

	public void setConnection(URLConnection connection)
	{
		this.connection = connection;
	}

	public Proxy getProxy()
	{
		return proxy;
	}

	public void setProxy(Proxy proxy)
	{
		this.proxy = proxy;
	}

	// ///////////////////////////////////////////////////////////////////
	// 以下是 Static Method //////////////////////////////////////////////
	// 这些静态方法其实可以(应该)单独提取出去的, ///////////////////////////////
	// 不过为了让这个程序结构简单些 , 我(fins)就全部 all in one 了.///////////////////
	// 这些方法也不都是我(fins)自己写的 很多是copy 借鉴来的 功能都挺简单的 就不详细说明了 //
	// ///////////////////////////////////////////////////////////////////

	public static void log(Level level, String msg, Throwable thrown)
	{
		System.out.println(level.getName() + " : " + thrown.getMessage() + " ----- " + msg);
	}

	public static String getConnectionResponseHeaders(URLConnection c)
	{
		int idx = 0;
		String value;
		StringBuffer buf = new StringBuffer();
		while ((value = c.getHeaderField(idx)) != null)
		{
			String key = c.getHeaderFieldKey(idx);
			buf.append(key);
			buf.append(": ");
			buf.append(value);
			idx++;
		}
		return buf.toString();
	}

	public static String getCharset(URLConnection connection)
	{
		String contentType = connection == null ? null : connection.getContentType();
		if (contentType != null)
		{
			StringTokenizer tok = new StringTokenizer(contentType, ";");
			if (tok.hasMoreTokens())
			{
				tok.nextToken();
				while (tok.hasMoreTokens())
				{
					String assignment = tok.nextToken().trim();
					int eqIdx = assignment.indexOf('=');
					if (eqIdx != -1)
					{
						String varName = assignment.substring(0, eqIdx).trim();
						if ("charset".equalsIgnoreCase(varName))
						{
							String varValue = assignment.substring(eqIdx + 1);
							return unquote(varValue.trim());
						}
					}
				}
			}
		}
		return null;
	}

	public static String unquote(String text)
	{
		if (text.startsWith("\"") && text.endsWith("\""))
		{
			return text.substring(1, text.length() - 2);
		}
		return text;
	}

	protected static URL createURL(URL baseUrl, String relativeUrl) throws MalformedURLException
	{
		return new URL(baseUrl, relativeUrl);
	}

	protected static byte[] loadStream(InputStream in, int initialBufferSize) throws IOException
	{
		if (initialBufferSize == 0)
		{
			initialBufferSize = 1;
		}
		byte[] buffer = new byte[initialBufferSize];
		int offset = 0;
		for (;;)
		{
			int remain = buffer.length - offset;
			if (remain <= 0)
			{
				int newSize = buffer.length * 2;
				byte[] newBuffer = new byte[newSize];
				System.arraycopy(buffer, 0, newBuffer, 0, offset);
				buffer = newBuffer;
				remain = buffer.length - offset;
			}
			int numRead = in.read(buffer, offset, remain);
			if (numRead == -1)
			{
				break;
			}
			offset += numRead;
		}
		if (offset < buffer.length)
		{
			byte[] newBuffer = new byte[offset];
			System.arraycopy(buffer, 0, newBuffer, 0, offset);
			buffer = newBuffer;
		}
		return buffer;
	}

	/**
	 * 一个用来监听 HttpRequest状态 的监听器. 是一个内部静态抽象类. 可以根据实际情况来自行重构(如 增加方法、变为独立的外部类等).
	 */
	public static abstract class ReadyStateChangeListener
	{
		public abstract void onReadyStateChange();
	}

	/**
	 * 用于执行xmlrequest方法，获取各种返回值，返回值由各自实现类处理器
	 * 
	 * @param map xmlrequest中要请求的参数
	 * @throws IOException
	 */
	public static void getXmlRequestResult(final Map<String, Object> map, String url,Map<String,String> headerMap,String method) throws IOException
	{
		// 创建 XmlHttpRequestHelper对象 相当于 创建 XHR对象.
		// 这里的final 主要是为了"使监听器内部能够调用ajax对象"而加的.
		final XmlHttpRequestHelper ajax = new XmlHttpRequestHelper();
		if(null!=headerMap && headerMap.size() > 0){
			Iterator<?> it = headerMap.keySet().iterator();
			while(it.hasNext()){
				String key = it.next().toString();
				ajax.setRequestHeader(key, headerMap.get(key).toString());
			}
		}
		// 设置状态监听器,类似 XHR对象的 onreadystatechange 属性.
		// 由于js 和java的本质区别 导致这里可能和 xhr有点不一样,但是应该不难理解.
		ajax.setReadyStateChangeListener(
		// 监听器, 根据需求 实现onReadyStateChange方法即可.
		new XmlHttpRequestHelper.ReadyStateChangeListener() {
			public void onReadyStateChange()
			{
				int readyState = ajax.getReadyState();
				// 判断状态 然后做出相应处理.
				if (readyState == XmlHttpRequestHelper.STATE_COMPLETE)
				{
					// 获取返回值
					map.put("result", ajax.getResponseText());
				}
			}
		});
		// 这里就和 xhr 几乎完全一样了
		ajax.open(method, url, false);
		// 这里这个send方法接受的参数是一个map ,当然XmlHttpRequestHelper类也提供了string的方法
		ajax.send(map);

	}
}