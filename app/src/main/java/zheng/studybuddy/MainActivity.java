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
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button addButton;
    Button loginLink;
    Button registerLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Display the user email when he logs in
        final TextView welcomeMessage = (TextView)findViewById(R.id.tvWelcomeMsg);
        String message;
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        if (email!=null) {
            message = email + ", welcome to Study Buddy!";
        }
        else{
            message = "Login to study with your buddy!";
        }
        welcomeMessage.setText(message);

        //setLoginLink();
        //setRegisterLink();
        setAddButton();
    }

/*    public Button setLoginLink() {
        //add the link to the login
        loginLink = (Button) findViewById(R.id.tvLogin);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(LoginIntent);
            }
        });
        return loginLink;
    }
    public Button setRegisterLink() {
        registerLink = (Button) findViewById(R.id.RegisterButton);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });
        return registerLink;
    }*/
    public Button setAddButton() {
        addButton = (Button) findViewById(R.id.addClassButton);
       // ArrayList<String> listItems = new ArrayList<String>();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listItems);
        addButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent addIntent = new Intent(MainActivity.this, AddClass.class);
                startActivity(addIntent);
            }
        });
        return addButton;
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
        switch(item.getItemId()) {
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
