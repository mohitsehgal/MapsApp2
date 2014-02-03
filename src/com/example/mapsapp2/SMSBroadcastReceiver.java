package com.example.mapsapp2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSBroadcastReceiver extends BroadcastReceiver {
	public static final String IS_ERROR_PARSING_MESSAGE="isErrorParsingMessage";
	public static final String LOCATION_STRING="locationString";
	public static final String STATUS_STRING="statusString";
	public static final String SENDER_STRING="sender";
@Override
public void onReceive(Context context, Intent intent) {
	
       Bundle extras = intent.getExtras();
       if (extras == null)
       return;
      
      // To display a Toast whenever there is an SMS.
      Toast.makeText(context,"Received",Toast.LENGTH_LONG).show();

       Object[] pdus = (Object[]) extras.get("pdus");
       for (int i = 0; i < pdus.length; i++) {
          SmsMessage smessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
          String sender = smessage.getOriginatingAddress();
          String body = smessage.getMessageBody().toString();

         // A custom Intent that will used as another Broadcast
         Intent in = new Intent(context,SmsDisplayActivity.class);
         String locationString,statusString;
         Log.d("body now",body);
         String messageArray[]=body.split("~#######~",2);
         for(String ms:messageArray)
         {
        	 Log.d("Log Message Frm WA", ms);
        	 
         }
         if(messageArray.length==2)
         {
        	 locationString=messageArray[0];
        	 statusString=messageArray[1];
        	 in.putExtra(IS_ERROR_PARSING_MESSAGE, false);
        	 in.putExtra(LOCATION_STRING, locationString);
        	 in.putExtra(STATUS_STRING, statusString);
         }
         else
         {
        	in.putExtra(IS_ERROR_PARSING_MESSAGE, true); 
         }
         	in.putExtra(SENDER_STRING, sender);
         	in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
         	context.startActivity(in);
         	this.abortBroadcast();
         
         //You can place your check conditions here(on the SMS or the sender)            
         //and then send another broadcast 
         //context.sendBroadcast(in);

        // This is used to abort the broadcast and can be used to silently
        // process incoming message and prevent it from further being 
        // broadcasted. Avoid this, as this is not the way to program an app.
        // this.abortBroadcast();
        }
     }
 }