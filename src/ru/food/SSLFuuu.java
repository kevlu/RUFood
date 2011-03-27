package ru.food;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

public class SSLFuuu 
{
	static String getRawData()
	{
		try 
		{
			trustEveryone();
			URL url = new URL(foodURL);
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String rawData = reader.readLine();
			reader.close();
			con.disconnect();
			return rawData;
		} 
		catch (IOException e) 
		{
			return e.getMessage();
		}
	}
	static private void trustEveryone() 
	{ 
		try 
		{ 
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier()
			{ 
				public boolean verify(String hostname, SSLSession session) 
				{ 
					return true; 
				}}); 
			SSLContext context = SSLContext.getInstance("TLS"); 
			context.init(null, new X509TrustManager[]{new X509TrustManager(){ 
				public void checkClientTrusted(X509Certificate[] chain, 
						String authType) throws CertificateException {} 
				public void checkServerTrusted(X509Certificate[] chain, 
						String authType) throws CertificateException {} 
				public X509Certificate[] getAcceptedIssuers() { 
					return new X509Certificate[0]; 
				}}}, new SecureRandom()); 
			HttpsURLConnection.setDefaultSSLSocketFactory( 
					context.getSocketFactory()); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace(); 
		} 
	}
	static private String foodURL = "https://mx.nbcs.test.rutgers.edu/mobile/dining/rutgers-dining.txt";
}
