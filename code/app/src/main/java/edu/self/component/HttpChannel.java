package edu.self.component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


public class HttpChannel extends TcpChannel {
	
	private URL url;
	private String requestType;
	private HttpURLConnection request;

	public HttpChannel(String urlPath, int timeout, String requestType) {
		super(urlPath, timeout, requestType);
		this.requestType = requestType;
		try {
			url = new URL(urlPath);
			request = (HttpURLConnection) url.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return request.getOutputStream();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		if (200 == request.getResponseCode()) {
			return request.getInputStream();
		}
		return null;
	}

	@Override
	public void connect(int timeout) throws AppException {
		request.setDoInput(true);
		request.setDoOutput(true);
		try {
			request.setRequestMethod(requestType);
			request.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
			request.setRequestProperty("Connection", "keep-alive");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
        
	}

	@Override
	public int send(OutputStream connection, byte[] inData) throws AppException {
		if (inData != null) {
			try {
				connection.write(inData);
				connection.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public byte[] receive(InputStream in) throws AppException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] buffer = new byte[2048];
		int len = 0;
		try {
			while((len = in.read(buffer)) != -1){
				os.write(buffer,0,len);
			}
			os.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return os.toByteArray();
	}

	@Override
	public boolean isConnected() {
		return false;
	}

	@Override
	public void close() {
		request.disconnect();
	}

}
