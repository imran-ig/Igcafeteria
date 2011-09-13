package com.intelligrape.cafeteria.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends Activity {
	private EditText etEmail,etPassword;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
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
			break;

		case 2:
			Toast.makeText(LogInActivity.this, "item 2 selected", Toast.LENGTH_LONG).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	
	public void loginAction(View view){
		String email=etEmail.getText().toString();
		String password=etPassword.getText().toString();
		if(isUserAuthenticated(email, password)){
			//Toast.makeText(LogInActivity.this, "Wel Come", Toast.LENGTH_LONG).show();
			Intent intent=new Intent(LogInActivity.this,BuyActivity.class);
			startActivity(intent);
		}else{
			Toast.makeText(LogInActivity.this, "Authentication fail", Toast.LENGTH_LONG).show();
		}
	}
	
	private boolean isUserAuthenticated(String email, String password){
		if(email.equals("imran.ali@intelligrape.com")&& password.equals("igdefault")){
			return true;
		}else{
			return false;
		}
	}
}




