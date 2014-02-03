package com.example.mapsapp2;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class SmsDisplayActivity extends Activity {
	 static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	  static final LatLng KIEL = new LatLng(53.551, 9.993);
	
	
	String sender;
	String body;
	String locationString;
	boolean isErrorParsingMessage;
	private GoogleMap map;
	double recLongitude,recLatitude;
	private LatLng recLatLng;
	public void initializeReceivedLoc()
	{
		String locArray[]=locationString.split(" ");
		recLatitude=Double.parseDouble(locArray[0]);
		recLongitude=Double.parseDouble(locArray[1]);
		recLatLng=new LatLng(recLatitude, recLongitude);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms_display);
		TextView sender=(TextView)findViewById(R.id.senderName);
		TextView bodyTextView=(TextView)findViewById(R.id.statusMessageReceived);
		TextView locationTextView=(TextView)findViewById(R.id.location);
		Intent receivedIntent=getIntent();
		this.sender=receivedIntent.getStringExtra(SMSBroadcastReceiver.SENDER_STRING);
		this.body=receivedIntent.getStringExtra(SMSBroadcastReceiver.STATUS_STRING);
		this.locationString=receivedIntent.getStringExtra(SMSBroadcastReceiver.LOCATION_STRING);
	//	initializeReceivedLoc();
		this.isErrorParsingMessage=receivedIntent.getBooleanExtra(SMSBroadcastReceiver.IS_ERROR_PARSING_MESSAGE,false);
		if(isErrorParsingMessage)
		{
			locationTextView.setText("Errror Retreiving Message");
		}
		else
		{
			this.sender="9646084419";
			this.body="Hey I am here!!";
			this.locationString="53.558 9.927";
			initializeReceivedLoc();
			
			sender.setText(this.sender);
			bodyTextView.setText(this.body);
			locationTextView.setText(this.locationString);
			
		}
		   map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
			        .getMap();
			    LocationManager lManager=(LocationManager)this.getSystemService(LOCATION_SERVICE);
			    
			    
			    
			    
			   
			    
			    //new code here
			    int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

			    // Showing status
			    if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

			        int requestCode = 10;
			        Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
			        dialog.show();

			    }else { // Google Play Services are available
			    	 map.setMyLocationEnabled(true);

			        /*
			        // Enabling MyLocation Layer of Google Map
			    
			        // Getting LocationManager object from System Service LOCATION_SERVICE
			        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

			        // Creating a criteria object to retrieve provider
			        Criteria criteria = new Criteria();

			        // Getting the name of the best provider
			        String provider = locationManager.getBestProvider(criteria, true);

			        // Getting Current Location
			        Location location = locationManager.getLastKnownLocation(provider);
			         double latitude=location.getLatitude();
			         double longitude=location.getLongitude(); */
//			         LatLng curLatLng=new LatLng(latitude, longitude);
			         
			         
			         if (map!=null){
			 //  	      Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)   .title("Hamburg"));
			   	      Marker kiel = map.addMarker(new MarkerOptions()
			   	          .position(KIEL)
			   	          .title("Kiel")
			   	          .snippet("Kiel is cool")
			   	          .icon(BitmapDescriptorFactory
			   	              .fromResource(R.drawable.ic_launcher)));
			   	      Marker recLoc=map.addMarker(new MarkerOptions().position(recLatLng).title("This Person is here").snippet(""+this.body));
			   	      
			   	 //Move the camera instantly to hamburg with a zoom of 15.
			   	   map.moveCamera(CameraUpdateFactory.newLatLngZoom(recLatLng, 15));

			   	   // Zoom in, animating the camera.
			   	   map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null); 
			   	    }
			         
			    }
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sms_display, menu);
		return true;
	}

}
