package com.example.mapsapp2;

import android.app.Activity;
import android.app.Dialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {
	
	
	String location="53.558 9.927";
	String status="I am here to get you!!!";
	String message=location+"~#######~"+status;

	
	
	 static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	  static final LatLng KIEL = new LatLng(53.551, 9.993);
	  private GoogleMap map2;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    map2 = ((MapFragment) getFragmentManager().findFragmentById(R.id.map2))
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
	    	 map2.setMyLocationEnabled(true);

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
//	         LatLng curLatLng=new LatLng(latitude, longitude);
	         
	         
	         if (map2!=null){
	   	      Marker hamburg = map2.addMarker(new MarkerOptions().position(HAMBURG)
	   	          .title("Hamburg"));
	   	      Marker kiel = map2.addMarker(new MarkerOptions()
	   	          .position(KIEL)
	   	          .title("Kiel")
	   	          .snippet("Kiel is cool")
	   	          .icon(BitmapDescriptorFactory
	   	              .fromResource(R.drawable.ic_launcher)));
	//   	      Marker curLoc=map.addMarker(new MarkerOptions().position(curLatLng).title("My Current Location").snippet("I am here. Do you know that? "));
	   	      
	   	 //Move the camera instantly to hamburg with a zoom of 15.
	   	   map2.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

	   	   // Zoom in, animating the camera.
	   	   map2.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null); 
	   	    }
	         
	    }
	    //new code ends here
	    
	  }
	  
}
