package edu.self.component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class TcpChannel {

	public String urlPath = "";
	public String requestType = "";
	public int timeout;

	public abstract OutputStream getOutputStream() throws IOException;

	public abstract InputStream getInputStream() throws IOException;

	public abstract void connect(int timeout) throws AppException;

	public abstract int send(OutputStream connection, byte[] inData)
			throws AppException;

	public abstract byte[] receive(InputStream in) throws AppException;

	public abstract boolean isConnected();

	public abstract void close();

	public TcpChannel(String urlPath, int timeout, String requestType) {
		this.urlPath = urlPath;
		this.timeout = timeout;
		this.requestType = requestType;
	}

	@Override
	protected void finalize() {
		close();
	}
}
