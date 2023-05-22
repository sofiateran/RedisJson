package com.lambda.configuration;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.stream.Collectors;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static java.lang.Long.valueOf;

public class FileWalker {
	private static final int PRETTY_PRINT_INDENT_FACTOR = 0;

	public int totallen = 0;
	private PostConfig pc = null;
	private DeleteConfig dc = null;
	private RedisJson rj = null;

	public FileWalker() {
		pc = new PostConfig();
		rj = new RedisJson();
	}

	public void walk(String companyid, String processtype, String path, long unixtime, int n) throws ParserConfigurationException, IOException, SAXException {

		File root = new File(path);
		if (!root.exists()) return;

	    File[] list = root.listFiles();
	    if (list == null) return;

	    for (File f : list) {
	    	if ( f.isDirectory() ) {
	    		System.out.println( "***************************************");
	    		System.out.println( "***************************************");
	    		System.out.println( "***************************************");
	    		System.out.println( "Dir:" + f.getAbsoluteFile());
	    		walk(companyid, processtype, f.getAbsolutePath(), unixtime, n);
	    	}
	        else {
	        	System.out.println( "***************************************");
	        	System.out.println( "File:" + f.getAbsoluteFile());

	        	String XML_STRING = null;
	            try {
	                XML_STRING = Files.lines(f.getAbsoluteFile().toPath()).collect(Collectors.joining("\n"));
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            JSONObject xmlJSONObj = XML.toJSONObject(XML_STRING);
	            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);



	            String fname = f.getName();
	            String fdir = f.getParent();

	            String[] nparts = fname.split("\\.");

	    		String cfgname = nparts[0];

				String redisJson = "SADD " + companyid + ":" + processtype + " '...:" + cfgname + rj.jsonForRedis(path,jsonPrettyPrintString + "'");


	    		if (processtype.equals("objecttypes")) {
	    			String prefix = "";
	    			String[] dparts = fdir.split("/");
	    			for (int i = n + 1; i < dparts.length; i++)
	    				prefix = prefix + dparts[i] + "/";
	    			cfgname = prefix + cfgname;
	    		}
				System.out.println( "Directorio: " + fdir );
				System.out.println( "Elemento: " + fname + "\n");
	    		System.out.println( "companyid: " + companyid + " - type: " + processtype + " - cfgname: " + cfgname + " - unixtime: " + unixtime + " - bytes: " + jsonPrettyPrintString.length() + "\n");
	            System.out.println( "json:" + "\n" + redisJson);

	            totallen = totallen + redisJson.length();

	            dc.delete(companyid, processtype, cfgname, valueOf(unixtime) );
	        }
	    }
	}
}
