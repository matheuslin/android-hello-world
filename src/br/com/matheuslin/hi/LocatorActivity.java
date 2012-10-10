package br.com.matheuslin.hi;

import br.com.matheuslin.hi.R.string;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class LocatorActivity extends Activity {

	TextView latitudeText;
	TextView longitudeText;
	TextView timeStampText;
	TextView providerNameText;
	TextView accuracyText;
	TextView altitudeText;
	TextView bearingText;
	TextView speedText;
	TextView bundleKeySetText;
	
	ToggleButton gpsButton;
	
	LocationManager locationManager;
	LocationListener locationListener;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        
        latitudeText = (TextView) findViewById(R.id.latitudeValue);
    	longitudeText = (TextView) findViewById(R.id.longitudeValue);
    	timeStampText = (TextView) findViewById(R.id.timeStampValue);
    	providerNameText = (TextView) findViewById(R.id.providerNameValue);
    	accuracyText = (TextView) findViewById(R.id.accuracyValue);
    	altitudeText = (TextView) findViewById(R.id.altitudeValue);
    	bearingText = (TextView) findViewById(R.id.bearingValue);
    	speedText = (TextView) findViewById(R.id.speedValue);
    	bundleKeySetText = (TextView) findViewById(R.id.bundleKeySetValue);
    	
    	gpsButton = (ToggleButton) findViewById(R.id.toggleButton);
    	
    	locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	
    	locationListener = new LocationListener(){

    		@Override
    		public void onLocationChanged(Location location) {
    			makeUseOfNewLocation(location);
    		}

    		@Override
    		public void onProviderDisabled(String provider) {
    			
    		}

    		@Override
    		public void onProviderEnabled(String provider) {
    			
    		}

    		@Override
    		public void onStatusChanged(String provider, int status, Bundle extras) {
    			
    		}
    		
    	};
    	
    	// Starts with NETWORK
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationListener);
    }

    protected void makeUseOfNewLocation(Location location) {
		latitudeText.setText(String.format("%.8f", location.getLatitude()));
		longitudeText.setText(String.format("%.8f", location.getLongitude()));
		timeStampText.setText(String.format("%d", location.getTime()));
    	providerNameText.setText( location.getProvider());
    	accuracyText.setText(location.hasAccuracy() ? String.format("%.8f", location.getAccuracy() ) : "-");
    	altitudeText.setText(location.hasAltitude() ? String.format("%.8f", location.getAltitude()) : "-");
    	bearingText.setText(location.hasBearing() ? String.format("%.8f", location.getBearing()) : "-");
    	speedText.setText(location.hasSpeed() ? String.format("%.8f", location.getSpeed()) : "-");
    	bundleKeySetText.setText(location.getExtras().keySet().isEmpty() ? "-" : location.getExtras().keySet().toString());
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_location, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void getLocation(View view){
    }
    
    public void toggleGPSButton(View view){
    	locationManager.removeUpdates(locationListener);
    	if(gpsButton.isChecked()){
    		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
    	}
    	else{
    		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationListener);
    	}
    }
    
    @Override
    protected void onPause(){
    	super.onPause();
    	locationManager.removeUpdates(locationListener);
    }
    
    @Override
    protected void onResume(){
    	super.onResume();
    	locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationListener);
    	gpsButton.setChecked(false); // always comes back off
    }

}
