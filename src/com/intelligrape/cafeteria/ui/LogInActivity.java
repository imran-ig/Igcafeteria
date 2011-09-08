package com.intelligrape.cafeteria.ui;

import android.app.Activity;
import android.os.Bundle;
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
	
	public void loginAction(View view){
		String email=etEmail.getText().toString();
		String password=etPassword.getText().toString();
		if(isUserAuthenticated(email, password)){
			Toast.makeText(LogInActivity.this, "Wel Come", Toast.LENGTH_LONG).show();
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




