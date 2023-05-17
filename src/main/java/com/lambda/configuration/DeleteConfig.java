package com.lambda.configuration;

import redis.clients.jedis.Jedis;
import java.util.List;

public class DeleteConfig {
    private Jedis jedis;

    public DeleteConfig(String host, int port) {
        jedis = new Jedis(host, port);
    }


    public void delete(String companyid, String processtype, String cfgname, Long unixtime) {
        String key = companyid + ":" + processtype + ":" + cfgname + ":" + unixtime;

        List<String> jsonList = (List<String>) jedis.smembers(key);
        StringBuilder allJsons = new StringBuilder();

        for (String json : jsonList) {
            // Procesar el JSON o realizar las operaciones necesarias
            // ...

            // Agregar el JSON al StringBuilder
            allJsons.append(json).append("\n");

            // Eliminar el JSON de Redis
            jedis.srem(key, json);
        }
    }

    public void close() {
        jedis.close();
    }
}
