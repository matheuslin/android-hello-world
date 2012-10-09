package br.com.matheuslin.hi;

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
import android.widget.EditText;
import android.widget.TextView;

public class LocatorActivity extends Activity {

	TextView latitudeText;
	TextView longitudeText;
	
	LocationManager locationManager;
	LocationListener locationListener;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        
        latitudeText = (TextView) findViewById(R.id.latitudeValue);
    	longitudeText = (TextView) findViewById(R.id.longitudeValue);
    	
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
    	
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationListener);
    }

    protected void makeUseOfNewLocation(Location location) {
		latitudeText.setText(String.format("%.8f", location.getLatitude()));
		longitudeText.setText(String.format("%.8f", location.getLongitude()));
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

}
