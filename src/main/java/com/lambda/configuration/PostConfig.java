package com.lambda.configuration;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class PostConfig {

	public PostConfig() {
		/*
		// these settings bypass the certificate check, need to be changed for production!
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[]{
			new X509TrustManager() {
		        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		        	return null;
		        }
		        public void checkClientTrusted(
		            java.security.cert.X509Certificate[] certs, String authType) {
		        }
		        public void checkServerTrusted(
		            java.security.cert.X509Certificate[] certs, String authType) {
		        }
		    }
		};

		// Install the all-trusting trust manager
		try {
		    SSLContext sc = SSLContext.getInstance("SSL");
		    sc.init(null, trustAllCerts, new java.security.SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (GeneralSecurityException e) {
			System.out.println(e.getMessage());
		}

		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// set the  allTrusting verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid); */
	}

	public void downloadConfig(String companyid, String processtype, String cfgname, String unixtime, String json) {

			// ...
			StringBuilder allJsons = new StringBuilder();

			try {
				// Crear FileWriter en modo "append"
				FileWriter file = new FileWriter("config.json", true);
				BufferedWriter writer = new BufferedWriter(file);
// Escribir el JSON
				writer.write(json);
				writer.newLine();
// Cerrar BufferedWriter
				writer.close();
// Agregar el JSON al StringBuilder
				allJsons.append(json).append("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}


	}

}