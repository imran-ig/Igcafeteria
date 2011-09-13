package com.intelligrape.cafeteria.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class BuyActivity extends Activity{
	int []quantityArray;
	Spinner spinnerItem, spinnerQuantity;
	String [] test={"test1","test2","test3"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		quantityArray=getResources().getIntArray(R.array.quantity_array);
		spinnerQuantity=(Spinner)findViewById(R.id.spinnerQuantity);
	 ArrayAdapter<String> adapterQuantity=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,test);
		spinnerQuantity.setAdapter(adapterQuantity);
		spinnerQuantity.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				int position=arg0.getSelectedItemPosition();
				Toast.makeText(BuyActivity.this, "you have selected item::"+test[position], Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
