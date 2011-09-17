package com.intelligrape.cafeteria.database;

import com.intelligrape.cafeteria.utils.User;

import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.SweepGradient;

public class SqliteDatabaseHelper extends SQLiteOpenHelper{
	private Context context;
	private final static String DATABASE_NAME="IgCafeteria.db";
	private final String TABLE_USERS="tblUser";
	private final String USER_ID="UserId";
	private final String EMAIL="Email";
	private final String PASSWORD="Password";
	private final String ADMIN="Admin";

	final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USERS + " (" + USER_ID + " INTEGER PRIMARY KEY, "
	+ EMAIL + " TEXT, " + PASSWORD + " TEXT, " + ADMIN + " INTEGER );";
	
	
	public SqliteDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 33);
		this.context=context;
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_USER);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateUser(User user, SQLiteDatabase db){
		ContentValues cv=new ContentValues();
		cv.put(USER_ID, user.getId());
		cv.put(EMAIL, user.getEmail());
		cv.put(PASSWORD, user.getPassword());
		cv.put(ADMIN, user.getAdmin());
		db.replace(TABLE_USERS, null, cv);
	}
	
	public boolean authenticateUser(String email, String password){
		boolean retval=false;
		String query="SELECT * FROM "+TABLE_USERS+" WHERE "+EMAIL+"=\""+email+"\" AND "+PASSWORD+"=\""+password+"\"";
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.rawQuery(query, null);
		if(cursor.moveToFirst()){
			retval=true;
		}
		cursor.close();
		db.close();
		return retval;
	}

}
