package com.intelligrape.cafeteria.ui;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.intelligrape.cafeteria.database.SqliteDatabaseHelper;
import com.intelligrape.cafeteria.utils.NetWorkUtils;
import com.intelligrape.cafeteria.utils.User;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends Activity {
	private EditText etEmail,etPassword;
	private SqliteDatabaseHelper dbHelper;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        dbHelper=new SqliteDatabaseHelper(this);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuItem menuItem1=menu.add(0, 1, 1, "User");{
			menuItem1.setIcon(R.drawable.sync);
		}
		MenuItem menuItem2=menu.add(0, 2, 2, "Data");{
			menuItem2.setIcon(R.drawable.sync);
		}
		
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(LogInActivity.this, "item 1 selected", Toast.LENGTH_LONG).show();
			syncUser();
			break;

		case 2:
			Toast.makeText(LogInActivity.this, "item 2 selected", Toast.LENGTH_LONG).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	void syncUser(){
		String usersData=NetWorkUtils.DownloadText(NetWorkUtils.SYNC_USERS_URL);
		ArrayList<User> users=parseUsersData(usersData);
		int userCount=users.size();
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		try{
			db.beginTransaction();
			for(int i=0;i<userCount;i++){
				dbHelper.updateUser(users.get(i),db);
			}
			db.setTransactionSuccessful();
		}catch(Exception e){
			
		}
		finally{
			db.endTransaction();
		}
	}
	
	ArrayList<User> parseUsersData(String usersData){
		ArrayList<User> users=new ArrayList<User>();
		try{
			//JSONObject jsonObj=new JSONObject(usersData);
			JSONArray jsonArray=new JSONArray(usersData);
			int count=jsonArray.length();
			for(int i=0;i<count;i++){
				User user=new User();
				JSONObject  jsonObj=jsonArray.getJSONObject(i);
				user.setId(jsonObj.getInt("id"));
				user.setEmail(jsonObj.getString("email"));
				user.setPassword(jsonObj.getString("password"));
				//user.setAdmin(jsonObj.getInt("admin"));
				user.setAdmin(0);
				users.add(user);
			}
			
		}
		catch(Exception e){
			
		}
		
		
		
		
		return users;
		
	}
	
	
	public void loginAction(View view){
		String email=etEmail.getText().toString();
		String password=etPassword.getText().toString();
		//if(isUserAuthenticated(email, password)){
		if(dbHelper.authenticateUser("imran.ali@intelligrape.com", "a0btu9ZP4hFlY")){
			Intent intent=new Intent(LogInActivity.this,BuyActivity.class);
			startActivity(intent);
		}else{
			Toast.makeText(LogInActivity.this, "Authentication fail", Toast.LENGTH_LONG).show();
		}
	}
	
	private boolean isUserAuthenticated(String email, String password){
		//if(email.equals("imran.ali@intelligrape.com")&& password.equals("igdefault")){
		if(email.equals("abc")&& password.equals("abc")){
			return true;
		}else{
			return false;
		}
	}
}




