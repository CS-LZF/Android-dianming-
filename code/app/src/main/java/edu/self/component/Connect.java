package edu.self.component;

import java.io.IOException;
import java.util.Vector;

import android.util.Log;

/**
 * 网络连接
 * @author mzba
 *
 */
public class Connect {

	private static Vector<TcpChannel> vector = new Vector<TcpChannel>();

	private int timeout;
	private TcpChannel channel;
	private String urlPath;
	private String requestType;

	public Connect(String urlPath, String requestType) {
		Log.i(Connect.class.getCanonicalName(), urlPath);
		this.urlPath = urlPath;
		this.requestType = requestType;
		timeout = 10000;
	}

	public static void stopConnectAll() {
		for (TcpChannel con : vector) {
			con.close();
		}
		vector.removeAllElements();
	}

	public static void stopConnect(TcpChannel connect) {
		if (connect == null)
			return;
		connect.close();
		vector.remove(connect);
	}

	public TcpChannel openChannel() {
		channel = null;
		channel = new HttpChannel(urlPath, timeout, requestType);
		vector.addElement(channel);
		return channel;
	}

	public int getTimeout() {
		return timeout;
	}

	public final byte[] queryServer(byte[] inData) throws AppException {
		channel = openChannel();
		try {
			channel.connect(channel.timeout);
			channel.send(channel.getOutputStream(), inData);
			inData = channel.receive(channel.getInputStream());
			return inData;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	public void close() {
		stopConnect(channel);
	}
}
