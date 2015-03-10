package com.smarthouseclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class ActivityMain extends Activity implements OnItemSelectedListener {

	private Client client;
	private EditText textField;
	private Button btnConnect, btnSend;
	private boolean isConnected = false;
	private String message;
	private ToggleButton btnLightB,btnAirB;
	private int sendData=0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.rooms_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		
		
		textField = (EditText) findViewById(R.id.editText1); // reference to the
																// text field
		btnConnect = (Button) findViewById(R.id.btnConnect); // reference to the
																// send
		btnConnect.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!isConnected) {
					message = textField.getText().toString(); // get the text
					textField.setText("");
					client=new Client(message);
					isConnected=true;
					btnConnect.setText("Close Connection");
				}
				else{
					client.close();
					btnConnect.setText("Start Connection");
					isConnected=false;
				}
			}
		});
		
		btnSend = (Button) findViewById(R.id.btnSend);
		btnSend.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
					message = textField.getText().toString(); // get the text
					textField.setText("");
					client.sendDataToServer(Integer.parseInt(message));
			}
		});
	
		
		btnLightB = (ToggleButton) findViewById(R.id.btnLightB);
		btnLightB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		            // The toggle is enabled
		        	sendData=sendData*100+11;
					client.sendDataToServer(sendData);
		        } else {
		            // The toggle is disabled
		        	sendData=sendData*100+12;
		        	client.sendDataToServer(sendData);
		        }
		    }
		});
		btnAirB = (ToggleButton) findViewById(R.id.btnAirB);
		btnAirB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		            // The toggle is enabled
		        	sendData=sendData*100+21;
					client.sendDataToServer(sendData);
		        } else {
		            // The toggle is disabled
		        	sendData=sendData*100+22;
		        	client.sendDataToServer(sendData);
		        }
		    }
		});
	}

	@Override
	 public void onItemSelected(AdapterView<?> parent, View view, 
	            int pos, long id) {
		String item=parent.getItemAtPosition(pos).toString();
		if(item.equals("Kitchen")){
			sendData=3;
		}
		else if(item.equals("Living Room")){
			sendData=2;
		}
		else if(item.equals("Bed Room")){
			sendData=1;
		}
		else{
			sendData=0;
		}
		
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		sendData=0;
	}
}