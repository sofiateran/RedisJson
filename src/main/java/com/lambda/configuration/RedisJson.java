package com.lambda.configuration;

import java.util.Map;

public class RedisJson {
    public RedisJson() {
    }

    public String jsonForRedis(String path, String jsonStr) {
        // Convertir el JSON a un objeto Map<String, Object>
        var redisJsonStr = jsonStr;

        // Agregar las barras, llaves y puntos necesarios
        redisJsonStr = redisJsonStr.replace("\"", "\\\"");
        redisJsonStr = redisJsonStr.replace("{", "{");
        redisJsonStr = redisJsonStr.replace("}", "}");
        redisJsonStr = redisJsonStr.replace(":", "\\:");

        int lastIndex = path.lastIndexOf("/");
        String fileName = path.substring(lastIndex + 1);

        if (fileName.equals("az.xml")) {
            return ("...:az:" + redisJsonStr);
        } else if (fileName.equals("cli.xml")) {
            return ("...:cli:" + redisJsonStr);
        } else if (fileName.equals("dis.xml")) {
            return ("...:dis:" + redisJsonStr);
        } else if (fileName.equals("fo.xml")) {
            return ("...:fo:" + redisJsonStr);
        } else if (fileName.equals("i.xml")) {
            return ("...:i:" + redisJsonStr);
        } else if (fileName.equals("io.xml")) {
            return ("...:io:" + redisJsonStr);
        }else{
            return redisJsonStr;
        }

    }
    }
