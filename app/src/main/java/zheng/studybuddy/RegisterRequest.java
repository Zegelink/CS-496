package zheng.studybuddy;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chongxian Chen on 4/30/16.
 */
public class RegisterRequest extends StringRequest{
    private static final String REGISTER_REQUEST_URL = "http://web.engr.oregonstate.edu/~chencho/StudyBuddy/register.php";
    private Map<String, String> params;

    public RegisterRequest(String email, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
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
