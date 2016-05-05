package zheng.studybuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView list;
    Button addButton;
    Button loginLink;
    Button registerLink;
    ArrayList<String> listitems;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //add the link to the login
        loginLink = (Button) findViewById(R.id.tvLogin);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(LoginIntent);
            }
        });
        registerLink = (Button) findViewById(R.id.Registerbutton);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });


        addButton = (Button) findViewById(R.id.addClassButton);
        ArrayList<String> listitems = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listitems);
        addButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this, AddClass.class);
                startActivity(myIntent);
            }

        });
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
        Bundle data = getIntent().getExtras();
        if (data != null) {

            // String str = data.getString("class");
            //   adapter.add(str);

        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
