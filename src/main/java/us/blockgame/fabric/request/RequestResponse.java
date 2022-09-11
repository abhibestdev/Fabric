package us.blockgame.fabric.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.simple.JSONObject;

@Getter
@AllArgsConstructor
public class RequestResponse {

    private boolean successful;
    private String errorMessage;
    private JSONObject response;
}
