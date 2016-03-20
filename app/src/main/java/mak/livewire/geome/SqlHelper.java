package mak.livewire.geome;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Mak on 20-03-2016.
 */
public class SqlHelper extends SQLiteOpenHelper{
    public static final String dbName="mydb";
    public static final String tableName="reminders";
    Context appContext;
    public SqlHelper(Context context) {

        super(context, dbName, null, 1); //to create a db
    appContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + tableName + "("
                + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT," + "location" + " TEXT,"
                + "lat" + " REAL," +"lon" + " REAL," + "forr" + " TEXT," + "about" + " TEXT," + "before" + " REAL" +   ")";
        db.execSQL(CREATE_TABLE);
        Toast.makeText(appContext,"db created",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//currently not needed

    }

    public void putRecord(ReminderRecord record)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("location",record.location);
        values.put("lat",record.lat);
        values.put("lon",record.lon);
        values.put("forr",record.forr);
        values.put("about",record.about);
        values.put("before",record.before);
        db.insert(tableName,null,values);
        //Toast.makeText(appContext,values.toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(appContext,"Reminder set at "+record.location+" for "+record.forr,Toast.LENGTH_SHORT).show();
        db.close();
    }


}
