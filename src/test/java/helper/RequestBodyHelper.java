package helper;

import java.util.HashMap;
import java.util.Map;

public class RequestBodyHelper {

    public static Map<String, String> createLoginBody (String email, String password) {
        Map<String, String> body = new HashMap<>();

        body.put("email", email);
        body.put("password", password);

        return body;
    }

}
