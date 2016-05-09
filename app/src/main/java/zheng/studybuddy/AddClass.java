package zheng.studybuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddClass extends AppCompatActivity {

    EditText schoolName;
    EditText className;
    Button addClass;
    String classString;
    String schoolString;
    Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addClass = (Button) findViewById(R.id.clicktoadd);
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                className = (EditText) findViewById(R.id.editText2);
                schoolName = (EditText) findViewById(R.id.editText3);
                classString = className.getText().toString();
                schoolString = schoolName.getText().toString();
                classDatabase Db = new classDatabase(ctx);
                if (Db.insertData(classString, schoolString)){
                    //showMessage("Success","data is inserted");
                    Intent addClassToList = new Intent(AddClass.this, MainActivity.class);
                    startActivity(addClassToList);


                }
                else{
                    showMessage("Error","data is not inserted");

                }
                //Db.displayTable();
              //  addClassToList.putExtra("class", className.getText().toString());
            }
        });
    }
    public void showMessage (String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
