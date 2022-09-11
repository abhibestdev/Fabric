package us.blockgame.fabric.request;

import com.google.common.collect.ImmutableMap;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import us.blockgame.fabric.FabricPlugin;
import us.blockgame.fabric.util.HttpUtils;

import java.util.Map;

public class FabricRequestHandler {

    private String url;

    public FabricRequestHandler() {
        url = FabricPlugin.getInstance().getConfig().getString("api.url");
    }

    @SneakyThrows
    public RequestResponse get(String endpoint, Map queryParamaters) {
        Builder builder = new Builder();
        builder.get();
        builder.url(url + endpoint + (queryParamaters != null ? HttpUtils.generateQueryString(queryParamaters) : ""));

        Response response = FabricPlugin.getOkHttpClient().newCall(builder.build()).execute();
        if (response.code() > 500) {
            return new RequestResponse(false, "Could not connect to API", null);
        } else {
            String body = response.body().string();

            JSONObject object = (JSONObject) new JSONParser().parse(body);
            if (object.containsKey("success") && !(boolean) object.get("success")) {
                return new RequestResponse(false, (String) object.get("message"), object);
            }
            return new RequestResponse(true, null, object);
        }
    }

    @SneakyThrows
    public RequestResponse post(String endpoint, Map bodyParameters) {
        Builder builder = new Builder();
        String bodyJson = FabricPlugin.getGson().toJson(bodyParameters == null ? ImmutableMap.of() : bodyParameters);
        builder.post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), bodyJson));
        builder.url(url + endpoint);

        Response response = FabricPlugin.getOkHttpClient().newCall(builder.build()).execute();
        if (response.code() > 500) {
            return new RequestResponse(false, "Could not connect to API", null);
        } else {
            String body = response.body().string();

            JSONObject object = (JSONObject) new JSONParser().parse(body);
            if (object.containsKey("success") && !(boolean) object.get("success")) {
                return new RequestResponse(false, (String) object.get("message"), object);
            }
            return new RequestResponse(true, null, object);
        }
    }
}
