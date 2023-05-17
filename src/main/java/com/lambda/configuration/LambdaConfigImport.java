package com.lambda.configuration;

import org.xml.sax.SAXException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class LambdaConfigImport {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
		// Llamada a Redis para obtener los valores
		RedisClient redisClient = new RedisClient("192.168.65.10", 6379);
		List<String> nombre = redisClient.smembers("24:networktypes");
		System.out.println("Nombre: " + nombre);
		redisClient.close();

		DeleteConfig deleteConfig = new DeleteConfig("192.168.65.10", 6379);


//		if (args.length != 3) {
//			System.out.println( "Usage:");
//			System.out.println( "ConfigImport <companyId> <path> <loadtype>");
//			System.out.println( "where");
//			System.out.println( "		<companyId> is the company Id, as an integer");
//			System.out.println( "		<path> is the path where xml's are stored, such as '/home/configuration'");
//			System.out.println( "		<loadtype> can take one of two values: 'initial' or 'update'");
//			System.out.println( "			'initial' updates the modified configuration with changes effective on unixtime = 0,");
//			System.out.println( "					  and deletes previous records of the updated configuration");
//			System.out.println( "			'update' updates the modified configuration with changes effective on current time,");
//			System.out.println( "					 previous records of the configuration are kept with end time = current time");
//			return;
//		}

		String companyid = args[0];
		String path = args[1];
		String loadtype = args[2];

		com.lambda.configuration.FileWalker wk = new FileWalker();

		long unixTime;
		if (loadtype.equals("initial"))
			unixTime = 0;
		else
			unixTime = System.currentTimeMillis() / 1000L;

		String[] oparts = path.split("/");
		int n = oparts.length;

		long startTime = System.nanoTime();
		deleteConfig.delete(companyid, "networktypes", path + "/networktypes", unixTime );

		//Directorios raiz base
		wk.walk(companyid, "networktypes", path + "/networktypes", unixTime, n);
        wk.walk(companyid, "valuelists", path + "/valuelists", unixTime, n);
        wk.walk(companyid, "datasheets", path + "/datasheets", unixTime, n);
        wk.walk(companyid, "objecttypes", path + "/objecttypes", unixTime, n);
        wk.walk(companyid, "base", path + "/base", unixTime, n);

//        // Modulo FO
        wk.walk(companyid, "outsideplanttypes", path + "/fo/outsideplanttypes", unixTime, n);
        wk.walk(companyid, "outsideplantassets", path + "/fo/outsideplantassets", unixTime, n);
        wk.walk(companyid, "connectortypes", path + "/fo/connectortypes", unixTime, n);
        wk.walk(companyid, "splicetypes", path + "/fo/splicetypes", unixTime, n);
        wk.walk(companyid, "traytypes", path + "/fo/traytypes", unixTime, n);
        wk.walk(companyid, "cabletypes", path + "/fo/cabletypes", unixTime, n);
        wk.walk(companyid, "splittertypes", path + "/fo/splittertypes", unixTime, n);
        wk.walk(companyid, "fobase", path + "/fo/fobase", unixTime, n);

//        // Modulo IP
        wk.walk(companyid, "racktypes", path + "/fo/racktypes", unixTime, n);
        wk.walk(companyid, "moduletypes", path + "/fo/moduletypes", unixTime, n);
        wk.walk(companyid, "porttypes", path + "/fo/porttypes", unixTime, n);

        System.out.println( "***************************************");
        System.out.println( "Total bytes: " + wk.totallen);
        System.out.println(" Time: " + (System.nanoTime() - startTime) / 1000000 + "ms");
        System.out.println( "***************************************");


    }
}
