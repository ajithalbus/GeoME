package mak.livewire.geome;
import android.os.Handler;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.widget.*;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.content.Intent;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mak on 19-03-2016.
 */
public class ReminderService extends Service {
    static int id=0;
    SqlHelper sqlHelper;
    AppLocationService appLocationService;
    NotificationManager nm;
    Notification notify;
    Handler handler;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //Toast.makeText(this, "Service was Created", Toast.LENGTH_LONG).show();
        sqlHelper=new SqlHelper(this);
        appLocationService=new AppLocationService(this);
        handler=new Handler();
        nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE); // notification service new
        notify = new Notification(R.drawable.ic_launcher,"Reminder",System.currentTimeMillis());
        notify.defaults|=Notification.DEFAULT_SOUND;
        notify.defaults|=Notification.DEFAULT_VIBRATE;


    }

    @Override
    public void onStart(Intent intent, int startId) {
        // longs
        super.onStart(intent, startId);
        //Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {




runOnUiThread(new Runnable(){
    @Override
    public void run() {
        withinBoundry();
    }
});



            }
        }, 0,20000);



    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    public void withinBoundry()
    {
        Location gpsLocation = appLocationService
                .getLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation == null)  {showSettingsAlert(); return;}
List<ReminderRecord> list=sqlHelper.getAllRecords();

        for(int i=0;i<list.size();i++)
        {
            if(distance(gpsLocation.getLatitude(),gpsLocation.getLongitude(),list.get(i).lat,list.get(i).lon)<=list.get(i).before)
            {notifyThis(list.get(i));}
        }


    }// not yet implemented


    public void notifyThis(ReminderRecord rr)
    {Context con =this;
        String title="Reminder - Reached "+rr.location;
        String detail=rr.forr+"\n"+rr.about;
        PendingIntent pending = PendingIntent.getActivity(con,0,new Intent(getApplicationContext(),ShowReminders.class),0);
        notify.setLatestEventInfo(con,title,detail,pending);
        nm.notify(rr.id,notify);


    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

            dist = dist * 1.609344;


        return (dist);
    }


    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }


    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    public void runOnUiThread(Runnable runnable)
    {
        handler.post(runnable);
    }
}

