package com.example.khgsm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context _context, Intent _intent) {
		
		if (_intent.getAction().equals(SMS_RECEIVER)) {
			SmsManager sms = SmsManager.getDefault();

			Bundle bundle = _intent.getExtras();
			if (bundle != null) {

				Object[] pdus = (Object[]) bundle.get("pdus");
				SmsMessage[] messages = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++)
					messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				for (SmsMessage message : messages) {
					String msg = message.getMessageBody();
					String to = message.getOriginatingAddress();
					Toast.makeText(_context, msg, Toast.LENGTH_LONG).show();
					Toast.makeText(_context, to, Toast.LENGTH_LONG).show();
					
					Intent intent=new Intent(_context,MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					_context.startActivity(intent);
					
					
					 
					
					if (msg.toLowerCase().startsWith(queryString)) {
						String out = msg.substring(queryString.length());
						sms.sendTextMessage(to, null, out, null, null);

						Toast.makeText(_context, "success", Toast.LENGTH_LONG).show();
					}
				}
			}
		}
	}
    
	private static final String queryString="Any";
	private static final String SMS_RECEIVER=
		"android.provider.Telephony.SMS_RECEIVED";

}
