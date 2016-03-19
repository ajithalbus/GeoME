package mak.livewire.geome;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class tasks extends Activity {
TextView greetings;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE); //to get i/p name and home addresss

        greetings=(TextView)findViewById(R.id.greetings);
        Intent i=getIntent();
       Address homeAddress= i.getParcelableExtra("home"); // gets address recieved
        greetings.setText("Hello "+ pref.getString("name","")+" :) ");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tasks, menu);
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
