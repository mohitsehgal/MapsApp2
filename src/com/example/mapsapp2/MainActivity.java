package com.example.mapsapp2;

import android.app.Activity;
import android.app.Dialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {


	
	 static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	  static final LatLng KIEL = new LatLng(53.551, 9.993);
	  private GoogleMap map;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
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

	        
	        // Enabling MyLocation Layer of Google Map
	     map.setMyLocationEnabled(true);

	        // Getting LocationManager object from System Service LOCATION_SERVICE
	        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

	        // Creating a criteria object to retrieve provider
	        Criteria criteria = new Criteria();

	        // Getting the name of the best provider
	        String provider = locationManager.getBestProvider(criteria, true);

	        // Getting Current Location
	        Location location = locationManager.getLastKnownLocation(provider);
	         double latitude=location.getLatitude();
	         double longitude=location.getLongitude();
	         LatLng curLatLng=new LatLng(latitude, longitude);
	         
	         
	         if (map!=null){
	   	      Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
	   	          .title("Hamburg"));
	   	      Marker kiel = map.addMarker(new MarkerOptions()
	   	          .position(KIEL)
	   	          .title("Kiel")
	   	          .snippet("Kiel is cool")
	   	          .icon(BitmapDescriptorFactory
	   	              .fromResource(R.drawable.ic_launcher)));
	   	      Marker mohit=map.addMarker(new MarkerOptions().position(curLatLng).title("My Current Location").snippet("I am here. Do you know that? "));
	   	      
	   	      
	   	    }
	         
	    }
	    //new code ends here
	    
	  }
	  
}
