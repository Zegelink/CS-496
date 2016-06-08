package zheng.studybuddy;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.firebase.client.Firebase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView list;
    classDatabase db;
    Button goToChat;
    Button addButton;
    Button studyNow;
    List<Classes> classList;
    private ListViewAdapter adapter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);
        listView();
        String message;

        //Display the user email when he logs in
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        if (email != null) {
            message = email + ", Welcome to Study Buddy!";
        } else {
            message = "Login to study with your buddy!";
        }
        long time = db.getTotalTime();
        message = message +"\n"+"You've been studying for "+time/1000+" seconds!";
        welcomeMessage.setText(message);

        //receive firebase message
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }

        setAddButton();
        getStudyNow();
        reloadingDatabase();
    }

    //  enterChat();
    /*public void enterChat(){
        goToChat = (Button) findViewById(R.id.chatButton);
        goToChat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(MainActivity.this, chatActivity.class);
                startActivity(chatIntent);
            }
        });


    }*/
    public void listView() {
        list = (ListView) findViewById(R.id.lvClass);
        db = new classDatabase(this);
        classList = new ArrayList<>();
    }

    public Button setAddButton() {
        addButton = (Button) findViewById(R.id.addClassButton);
        addButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ChatRoom.class);
                startActivity(myIntent);
            }
        });
        return addButton;
    }

    public Button getStudyNow() {
        studyNow = (Button) findViewById(R.id.bStartStudy);
        studyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent timerIntent = new Intent(MainActivity.this, TimerActivity.class);
                MainActivity.this.startActivity(timerIntent);
            }
        });
        return studyNow;
    }

    @Override
    public void onResume() {
        super.onResume();
        listView();
        reloadingDatabase();
    }

    //  public void onResume() {
    //       super.onResume();
    // ArrayList<String> listitems = new ArrayList<String>();
    // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listitems);
    //       list = (ListView) findViewById(R.id.listView);
    //      list.setAdapter(adapter);
    //      Bundle data = getIntent().getExtras();
    //      if (data != null) {

    //          String str = data.getString("class");
    //           adapter.add(str);
    //       }
    //   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(item.getItemId()){
            case R.id.action_settings:
                try {
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                } catch (ActivityNotFoundException ignored){}
                return true;
            case R.id.bLogin:
                try {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } catch (ActivityNotFoundException ignored){}
                return true;
            case R.id.bRegister:
                try {
                    startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                } catch (ActivityNotFoundException ignored){}
                return true;
            case R.id.addClassButton:
                try {
                    startActivity(new Intent(MainActivity.this, AddClass.class));
                } catch (ActivityNotFoundException ignored){}
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void reloadingDatabase() {
        classList = db.getAllClass();
        if (classList.size() == 0) {
            Toast.makeText(this, "No record found in database!", Toast.LENGTH_SHORT).show();
            //title.setVisibility(View.GONE);
        }
        adapter = new ListViewAdapter(this, R.layout.item_listview, classList, db);
        list.setAdapter(adapter);
        //title.setVisibility(View.VISIBLE);
        //title.setText("Total records: " + databaseHelper.getContactsCount());
    }



}
