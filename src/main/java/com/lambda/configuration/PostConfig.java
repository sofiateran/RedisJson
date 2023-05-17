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