package zheng.studybuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddClass extends AppCompatActivity {

    EditText schoolname;
    EditText classname;
    Button addclass;
    String classstring;
    String schoolstring;
    Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addclass = (Button) findViewById(R.id.clicktoadd);
        addclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classname = (EditText) findViewById(R.id.editText2);
                schoolname = (EditText) findViewById(R.id.editText3);
                classstring = classname.getText().toString();
                schoolstring = schoolname.getText().toString();
                classDatabase Db = new classDatabase(ctx);
                Db.fillTable(Db, classstring, schoolstring);
                Intent addClassToList = new Intent(AddClass.this, MainActivity.class);
              //  addClassToList.putExtra("class", classname.getText().toString());
                startActivity(addClassToList);
            }
        });
    }

}
