package com.intelligrape.cafeteria.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;

public class NetWorkUtils {

	public static String SYNC_USERS_URL="http://cafeteria.intelligrape.net/sync/users";
	public static String SYNC_ITEMS_URL="http://cafeteria.intelligrape.net/sync/users";
	public static String BUY_ITEM_URL="http://cafeteria.intelligrape.net/sync/buy/?";
	/**
	 * ************* Download data from server**********************
	 */
	private static InputStream OpenHttpConnection(String urlString) throws IOException {
		InputStream in = null;
		HttpURLConnection httpConn = null;

		int response = -1;
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");

		try {
			httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(true);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("POST");
			httpConn.setConnectTimeout(5000);
			httpConn.connect();
			
			response = httpConn.getResponseCode();
			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (java.net.SocketTimeoutException e) {
			throw new IOException("Error connecting");
		} catch (Exception ex) {
			throw new IOException("Error connecting");
		} finally {
		}
		return in;
	}

	public static String DownloadText(String URL) {
		int BUFFER_SIZE = 2000;
		InputStream in = null;
		try {
			in = OpenHttpConnection(URL);
		} catch (IOException e1) {
			Log.d("IOException", "IOException:-" + e1.getMessage());
			return "IOException";
		}

		String str = "";
		try {
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			int charRead;
			char[] inputBuffer = new char[BUFFER_SIZE];
			while ((charRead = isr.read(inputBuffer)) > 0) {
				// --- convert the chars to a String ---
				String readString = String.copyValueOf(inputBuffer, 0, charRead);
				str += readString;
				inputBuffer = new char[BUFFER_SIZE];
			}
			str = new String(str.getBytes(), "UTF-8");
			in.close();
		} catch (IOException e) {
			return "IOException";
		}
		Log.d("DATA_DOWNLOAD_FROM_SERVER **** ", str);
		return str;
	}
}
