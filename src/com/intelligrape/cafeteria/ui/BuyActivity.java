package com.intelligrape.cafeteria.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class BuyActivity extends Activity{
	Spinner spinnerItem, spinnerQuantity;
	String [] item={"Select Item","Chips","Samosa","Pea Nut","other"};
	String [] quantity={"Select quantity","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten"};
	RelativeLayout otherField;
	EditText etOtherItem,etOtherQuantity;
	int itemSelectedPostion,quantitySelectedPositin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buy_activity);
		spinnerItem=(Spinner)findViewById(R.id.spinnerItem);
		spinnerQuantity=(Spinner)findViewById(R.id.spinnerQuantity);
		otherField=(RelativeLayout)findViewById(R.id.otherField);
		etOtherItem=(EditText)findViewById(R.id.otherItem);
		etOtherQuantity=(EditText)findViewById(R.id.otherQuantity);

		 ArrayAdapter<String> adapterItem=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,item);
		 adapterItem.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		 spinnerItem.setAdapter(adapterItem);
		 spinnerItem.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					itemSelectedPostion=arg0.getSelectedItemPosition();
					//Toast.makeText(BuyActivity.this, "you have selected item::"+item[itemSelectedPostion], Toast.LENGTH_LONG).show();
					ItemSelected();
					
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			

	 ArrayAdapter<String> adapterQuantity=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,quantity);
	 adapterQuantity.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		spinnerQuantity.setAdapter(adapterQuantity);
		spinnerQuantity.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				quantitySelectedPositin=arg0.getSelectedItemPosition();
				Toast.makeText(BuyActivity.this, "you have selected item::"+quantity[quantitySelectedPositin], Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	void ItemSelected(){
		if(itemSelectedPostion==item.length-1){
			otherField.setVisibility(RelativeLayout.VISIBLE);
		}else{
			otherField.setVisibility(RelativeLayout.GONE);
		}
		
	}

}
