package us.blockgame.fabric.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public class HttpUtils {

    public static String generateQueryString(Map parameters) {
        StringBuilder queryBuilder = new StringBuilder("?");
        Iterator var2 = parameters.entrySet().iterator();

        while (var2.hasNext()) {
            Map.Entry entry = (Map.Entry) var2.next();
            if (queryBuilder.length() > 1) {
                queryBuilder.append("&");
            }

            try {
                queryBuilder.append((String) entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
            } catch (UnsupportedEncodingException var5) {
            }
        }
        return queryBuilder.toString();
    }
}
