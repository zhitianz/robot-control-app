package com.dfrobot.angelo.blunobasicdemo;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity  extends BlunoLibrary {
	private Button buttonScan;
	private Button buttonSerialSend;
	private EditText serialSendText;
	private TextView serialReceivedText;
	private Button buttonF;
	private Button buttonB;
	private Button buttonL;
	private Button buttonR;
	private Button buttonU;
	private Button buttonD;
	private Button buttonbreak;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        onCreateProcess();														//onCreate Process by BlunoLibrary


        serialBegin(115200);													//set the Uart Baudrate on BLE chip to 115200

        serialReceivedText=(TextView) findViewById(R.id.serialReveicedText);	//initial the EditText of the received data
        serialSendText=(EditText) findViewById(R.id.serialSendText);			//initial the EditText of the sending data

        buttonSerialSend = (Button) findViewById(R.id.buttonSerialSend);		//initial the button for sending the data
        buttonSerialSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				serialSend(serialSendText.getText().toString());				//send the data to the BLUNO
			}
		});
		//direction button
		buttonF = (Button) findViewById(R.id.buttonF);
		buttonB = (Button) findViewById(R.id.buttonB);
		buttonL = (Button) findViewById(R.id.buttonL);
		buttonR = (Button) findViewById(R.id.buttonR);
		buttonU = (Button) findViewById(R.id.buttonU);
		buttonD = (Button) findViewById(R.id.buttonD);
		buttonbreak = (Button) findViewById(R.id.buttonbreak);


        //ImageView imageView = (ImageView) findViewById(R.id.imageView);
        //imageView.setImageResource(R.drawable.ipfw2);


        buttonF.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN){
					serialSend("11,130");
				} else if (event.getAction() == MotionEvent.ACTION_UP){
					serialSend("5,0");
				}
				return false;
			}
		});

		buttonB.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN){
					serialSend("12,130");
				} else if (event.getAction() == MotionEvent.ACTION_UP){
					serialSend("5,0");
				}
				return false;
			}
		});
		buttonL.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN){
					serialSend("13,130");
				} else if (event.getAction() == MotionEvent.ACTION_UP){
					serialSend("5,0");
				}
				return false;
			}
		});
		buttonR.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN){
					serialSend("14,130");
				} else if (event.getAction() == MotionEvent.ACTION_UP){
					serialSend("5,0");
				}
				return false;
			}
		});
		/*buttonU.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN){
					serialSend("15,130");
				} else if (event.getAction() == MotionEvent.ACTION_UP){
					serialSend("6,0");
				}
				return false;
			}
		});
		buttonD.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN){
					serialSend("16,130");
				} else if (event.getAction() == MotionEvent.ACTION_UP){
					serialSend("6,0");
				}
				return false;
			}
		});*/
		buttonU.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v){
				serialSend("7,130");
			}
		});
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                serialSend("8,130");
            }
        });
		buttonbreak.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v){
				serialSend("5,0");
			}
		});




		buttonScan = (Button) findViewById(R.id.buttonScan);					//initial the button for scanning the BLE device
        buttonScan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				buttonScanOnClickProcess();										//Alert Dialog for selecting the BLE device
			}
		});
	}

	protected void onResume(){
		super.onResume();
		System.out.println("BlUNOActivity onResume");
		onResumeProcess();														//onResume Process by BlunoLibrary
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		onActivityResultProcess(requestCode, resultCode, data);					//onActivityResult Process by BlunoLibrary
		super.onActivityResult(requestCode, resultCode, data);
	}
	
    @Override
    protected void onPause() {
        super.onPause();
        onPauseProcess();														//onPause Process by BlunoLibrary
    }
	
	protected void onStop() {
		super.onStop();
		onStopProcess();														//onStop Process by BlunoLibrary
	}
    
	@Override
    protected void onDestroy() {
        super.onDestroy();	
        onDestroyProcess();														//onDestroy Process by BlunoLibrary
    }

	@Override
	public void onConectionStateChange(connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called
		switch (theConnectionState) {											//Four connection state
		case isConnected:
			buttonScan.setText("Connected");
			break;
		case isConnecting:
			buttonScan.setText("Connecting");
			break;
		case isToScan:
			buttonScan.setText("Scan");
			break;
		case isScanning:
			buttonScan.setText("Scanning");
			break;
		case isDisconnecting:
			buttonScan.setText("isDisconnecting");
			break;
		default:
			break;
		}
	}

	@Override
	public void onSerialReceived(String theString) {							//Once connection data received, this function will be called
		// TODO Auto-generated method stub
		serialReceivedText.append(theString);							//append the text into the EditText
		//The Serial data from the BLUNO may be sub-packaged, so using a buffer to hold the String is a good choice.
		((ScrollView)serialReceivedText.getParent()).fullScroll(View.FOCUS_DOWN);
	}

}