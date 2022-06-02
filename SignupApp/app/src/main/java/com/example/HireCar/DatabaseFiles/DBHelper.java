package com.example.HireCar.DatabaseFiles;

import static com.example.HireCar.DatabaseFiles.Constants.COL_LOCATIONANME;
import static com.example.HireCar.DatabaseFiles.Constants.LOCATION_TABLE;
import static com.example.HireCar.DatabaseFiles.Constants.USER_TABLE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.HireCar.LocationModel;
import com.google.firebase.firestore.auth.User;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "CarRental";
    public String loc[] = new String[0];
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String USER_TABLE_QUERY = "CREATE TABLE "+ Constants.USER_TABLE + "(" +
                Constants.COL_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Constants.COL_NAME + " TEXT," +
                Constants.COL_EMAIL + " TEXT," +
                Constants.COL_PHONE + " TEXT," +
                Constants.COL_DLNUMBER + " TEXT," +
                Constants.COL_DLIMAGE + " BLOB," +
                Constants.COL_ISADMIN + " BOOLEAN DEFAULT 'false');";
        sqLiteDatabase.execSQL(USER_TABLE_QUERY);

        String COMPANY_TABLE_QUERY = "CREATE TABLE "+ Constants.COMPANY_TABLE + "(" +
                Constants.COL_COMPID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Constants.COL_COMPNAME + " TEXT);";
        sqLiteDatabase.execSQL(COMPANY_TABLE_QUERY);


        String CAR_TABLE_QUERY = "CREATE TABLE " + Constants.CAR_TABLE + "(" +
                Constants.COL_CARID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Constants.COL_MODELNAME + " TEXT," +
                Constants.COL_REGNUMBER + " TEXT," +
                Constants.COL_CARIMAGE + " BLOB," +
                Constants.COL_CARINSURANCEIMAGAE + " BLOB," +
                Constants.COL_CARPUCIMAGE + " BLOB," +
                Constants.COL_CARRCIMAGE + " BLOB," +
                Constants.COL_BODYTYPE + " TEXT," +
                Constants.COL_FUELTYPE + " TEXT," +
                Constants.COL_COST + " INTEGER," +
                Constants.COL_CAPACITY + " INTEGER," +
                Constants.COL_MILEAGE + " INTEGER," +
                Constants.COL_TRANSMISSION + " TEXT," +
                Constants.COL_AVAILFLAG + " BOOLEAN," +
                Constants.COL_COMPANY_ID_FK + " INTEGER);";
        sqLiteDatabase.execSQL(CAR_TABLE_QUERY);


        String LOCATION_TABLE_QUERY = "CREATE TABLE "+ LOCATION_TABLE + "(" +
                COL_LOCATIONANME + " TEXT);";
        sqLiteDatabase.execSQL(LOCATION_TABLE_QUERY);


        String BOOKING_TABLE_QUERY = "CREATE TABLE "+ Constants.BOOKING_TABLE + "(" +
                Constants.COL_BOOKINGID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Constants.COL_STARTDATE + " DATE," +
                Constants.COL_ENDDATE + " DATE," +
                Constants.COL_PICKUPLOC + " TEXT," +
                Constants.COL_DROPPLOC + " TEXT," +
                Constants.COL_PAYMENTSTATUS + " TEXT," +
                Constants.COL_AMOUNT + " INTEGER," +
                Constants.COL_UID_FK + " INTEGER," +
                Constants.COL_CARID_FK + " INTEGER," +
                Constants.COL_REFUNDALBEDEPOSIT + " INTEGER);";
        sqLiteDatabase.execSQL(BOOKING_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.USER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.COMPANY_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.CAR_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LOCATION_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.BOOKING_TABLE);
    }

    public Boolean InsertUserData(String name, String email, String phone, String dlnumber, byte[] dlimage, boolean isadmin){
        SQLiteDatabase userDB = this.getWritableDatabase();
        ContentValues userValues = new ContentValues();

        userValues.put("Name", name);
        userValues.put("Email", email);
        userValues.put("MobileNo", phone);
        userValues.put("DlNo", dlnumber);
        userValues.put("DLImage", dlimage);
        userValues.put("isAdmin", isadmin);

        long insert_result = userDB.insert(Constants.USER_TABLE, null,userValues);
        if(insert_result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public String getUserName(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from User where Name = ?", new String[] {name});
        cursor.moveToFirst();
        return cursor.getString(0);
    }

//    public  void getLoc(){
//        ArrayList<String> location=new ArrayList<>();

//        SQLiteDatabase MyDB=this.getWritableDatabase();
//        Cursor cursor=MyDB.rawQuery("SELECT * FROM Location",null);
////        loc=new String[cursor.getCount()];
//        int p=cursor.getCount();
//        cursor.moveToFirst();
//        for(int i=0;i<p;i++){
//            loc[i]=cursor.getString(0);
//        }
//        String loc1[] = {"Paldi Cross Roads, Kocharab, Paldi, Ahmedabad, Gujarat",
//                "Shivranjani Cross Road, Jodhpur Village, Ahmedabad, Gujarat",
//                "Shyamal Cross Road, Shyamal, Ahmedabad, Gujarat",
//                "Panchvati Cir, Ellisbridge, Ahmedabad, Gujarat"};
//        loc= new String[]{"Paldi Cross Roads, Kocharab, Paldi, Ahmedabad, Gujarat",
//                "Shivranjani Cross Road, Jodhpur Village, Ahmedabad, Gujarat"};
//    }


    public Cursor getplant(){
        SQLiteDatabase bdq=this.getWritableDatabase();
        Cursor res=bdq.rawQuery("SELECT * FROM " + LOCATION_TABLE,null);
        return res;
    }

//    public ArrayList<LocationModel> readLocations(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM "+ LOCATION_TABLE, null);
//        ArrayList<LocationModel> locationModelList = new ArrayList<LocationModel>();
//
//        if(c.moveToFirst()){
//            do{
//                locationModelList.add(new LocationModel(c.getString(0)));
//            }while(c.moveToNext());
//        }
//        c.close();
//        return locationModelList;
//    }

}
