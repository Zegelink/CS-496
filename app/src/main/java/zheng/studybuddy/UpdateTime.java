package zheng.studybuddy;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by georgechen on 6/9/16.
 */
public class UpdateTime extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://web.engr.oregonstate.edu/~chencho/StudyBuddy/updateTime.php";
    private Map<String, String> params;

    public UpdateTime(String email, long time, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        //null is error listener
        params = new HashMap<>();
        params.put("email", email);
        params.put("timeStudied", Long.toString(time));
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}