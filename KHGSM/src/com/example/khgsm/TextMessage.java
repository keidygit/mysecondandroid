package com.example.khgsm;

import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager; //It will auto import android.telephony.gsm.SmsManager, you should delete "gsm" 
import android.telephony.SmsMessage;
import android.widget.Toast;

public class TextMessage extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
	}
	public boolean sendMessages(String phoneNo, String phoneMessage)
	{
		if (phoneNo.length() > 0 && phoneMessage.length() > 0) 
		{
			sendSMS(phoneNo, phoneMessage);
			return true;
		}
		else 
		{
		//	Toast.makeText(getBaseContext(), "Please enter phone number and message", Toast.LENGTH_LONG).show();
			return false;
		}
	}
	private void sendSMS(String phoneNumber,String message) 
	{
		// ---sends an SMS message to another device---
		SmsManager sms = SmsManager.getDefault();
	//	PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent("DELIVERED_SMS_ACTION"), 0);
		// if message's length more than 70 ,
		// then call divideMessage to dive message into several part ,and call
		// sendTextMessage()
		// else direct call sendTextMessage()
		if (message.length() > 70) 
		{
			ArrayList<String> msgs = sms.divideMessage(message);
			for (String msg : msgs) 
			{
				sms.sendTextMessage(phoneNumber, null, msg, null, null);
			}
		} else 
		{
			sms.sendTextMessage(phoneNumber, null, message, null, null);
		}
	//	Toast.makeText(getBaseContext(), "短信发送完成", Toast.LENGTH_LONG).show();
		
	}
		
		
}

