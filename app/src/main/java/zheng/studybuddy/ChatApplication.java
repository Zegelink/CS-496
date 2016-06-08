package zheng.studybuddy;
import com.firebase.client.Firebase;

/**
 * Created by Chongxian Chen on 6/8/16.
 */
public class ChatApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
