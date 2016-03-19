package mak.livewire.geome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class SetReminders extends Activity {
EditText at,forr,about,before;
    CheckBox checkBox;
    Button set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminders);
        at=(EditText)findViewById(R.id.editText3);
        forr=(EditText)findViewById(R.id.editText4);
        about=(EditText)findViewById(R.id.editText5);
        before=(EditText)findViewById(R.id.editText6);
        checkBox=(CheckBox)findViewById(R.id.checkBox2);
        set=(Button)findViewById(R.id.set);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked())
                {
                    at.setEnabled(false);

                }
                else at.setEnabled(true);
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }




}
