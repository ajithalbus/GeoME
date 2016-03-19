package mak.livewire.geome;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.*;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.location.Geocoder;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends Activity {
Button next,done;

    EditText t1,t2;
    Geocoder geocoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final TextView tv=(TextView)findViewById(R.id.textView2);
        geocoder=new Geocoder(getApplicationContext());
         next=(Button)findViewById(R.id.next);

        t1=(EditText)findViewById((R.id.editText));
        t2=(EditText)findViewById((R.id.editText2));
//initialize
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final Editor editor = pref.edit();

        String name=pref.getString("name",null);
        String home=pref.getString("home",null);
        if(name!=null&&home!=null) {
          //  Toast.makeText(getApplicationContext(), name + " " + home, Toast.LENGTH_SHORT).show();
            Intent i;
        }  // go to task activity


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Address> list=new LinkedList<Address>();
                Address address;
                String name =t1.getText().toString();
                String home =t2.getText().toString();
                if(name.equalsIgnoreCase("")||home.equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter Proper Name/Home",Toast.LENGTH_SHORT).show();
                    return;
                }
                editor.putString("name",name);
                editor.putString("home",home);
                editor.commit();


                Toast.makeText(getApplicationContext(),name+" "+home,Toast.LENGTH_SHORT).show();
                try {
                    list=geocoder.getFromLocationName(home, 3);
                    address=list.get(0);
                    tv.setText(address.toString()); //for debug
                    //Toast.makeText(getApplicationContext(),address.toString(),Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                setContentView(R.layout.selecthome);
                setRadio(list);


            }
        });

    }

void setRadio(List<Address> list)
{
done.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(getApplicationContext(),tasks.class);
        
    }
});
    RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup);


Toast.makeText(getApplicationContext(),list.size()+"",Toast.LENGTH_SHORT).show();
    for(int i=0;i<list.size();i++)
    {RadioButton rb=new RadioButton(getApplicationContext());
        rb.setTextColor(Color.BLACK);
        rb.setText(list.get(i).getFeatureName() + "," + list.get(i).getAdminArea() + "," + list.get(i).getPostalCode());
        rg.addView(rb,i);
    }
   // rg.addView(rb.setText(list.get(0).getFeatureName() + "," + list.get(0).getAdminArea() + "," + list.get(0).getPostalCode()));
   /* rb.setText(list.get(1).getFeatureName()+","+list.get(1).getAdminArea()+","+list.get(1).getPostalCode());
    rb.setText(list.get(2).getFeatureName()+","+list.get(2).getAdminArea()+","+list.get(2).getPostalCode());*/
}
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
