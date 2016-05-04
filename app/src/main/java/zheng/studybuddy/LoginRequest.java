package zheng.studybuddy;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chongxian Chen on 4/30/16.
 */
public class LoginRequest extends StringRequest{
    private static final String Login_REQUEST_URL = "http://web.engr.oregonstate.edu/~chencho/StudyBuddy/login.php";
    private Map<String, String> params;

    public LoginRequest(String email, String password, Response.Listener<String> listener){
        super(Request.Method.POST, Login_REQUEST_URL, listener, null);
        //null is error listener
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
