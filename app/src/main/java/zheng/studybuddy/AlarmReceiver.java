package zheng.studybuddy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by zheng on 6/8/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private String mostStudiousUser="I ";
    private Long timeStudied = new Long(0);
    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar now = GregorianCalendar.getInstance();
        int dayOfWeek = now.get(Calendar.DATE);

        //get the most studious
        Response.Listener<String> rl = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success){
                        Log.d("1","success");
                        mostStudiousUser = jsonResponse.getString("1");
                        timeStudied = jsonResponse.getLong("1time");
                    }
                    else {
                        Log.d("1","failed");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        GetMostStudious ut = new GetMostStudious(rl);//response listener
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(ut);

        if(dayOfWeek != 1 && dayOfWeek != 7) {
            android.support.v4.app.NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(android.R.drawable.ic_dialog_email)
                            .setContentTitle("MostActiveUser")
                            .setContentText("The most active user is"+mostStudiousUser)
                            .setLights(0xff00ff00, 500, 500)
                            .setAutoCancel(true);
            Intent resultIntent = new Intent(context, MainActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
        }
    }

}
