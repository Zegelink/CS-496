package zheng.studybuddy;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;

public class TimerActivity extends Activity {

    private Button startButton;
    private Button pauseButton;

    private TextView timerValue;

    private long startTime = 0L;

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerValue = (TextView) findViewById(R.id.timerValue);

        startButton = (Button) findViewById(R.id.bStart);

        startButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);

            }
        });

        pauseButton = (Button) findViewById(R.id.bStop);

        pauseButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);

                classDatabase Db = new classDatabase(TimerActivity.this);
                Log.d("1", "get");
                if (Db.insertTime(timeSwapBuff)){
                    Log.d("2", "get");
                    Response.Listener<String> rl = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success){
                                }
                                else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TimerActivity.this);
                                    builder.setMessage("Update failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    SharedPreferences prefs1 = getSharedPreferences("Session",     Context.MODE_PRIVATE);
                    String email = prefs1.getString("email", "");

                    if (email != null && !email.isEmpty()){
                        long TotalTime = Db.getTotalTime();
                        UpdateTime ut = new UpdateTime(email, TotalTime, rl);//response listener
                        RequestQueue queue = Volley.newRequestQueue(TimerActivity.this);
                        queue.add(ut);
                    }
                    Intent intent = new Intent(TimerActivity.this, MainActivity.class);

                    startActivity(intent);

                }
                else{
                    //display error message
                    //showMessage("Error","data is not inserted");
                }

            }
        });

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }

    };

}