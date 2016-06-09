package zheng.studybuddy;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chongxian Chen on 6/9/16.
 */
public class GetMostStudious extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://web.engr.oregonstate.edu/~chencho/StudyBuddy/getMostStudious.php";
    private Map<String, String> params;

    public GetMostStudious(Response.Listener<String> listener){
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        //null is error listener
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}