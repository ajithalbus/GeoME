package mak.livewire.geome;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;


public class ShowReminders extends Activity {
LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reminders);
        ll = (LinearLayout)findViewById(R.id.ll);
       for(int i=0;i<100;i++) ll.addView(new CheckBox(getApplicationContext()));
    }



}
