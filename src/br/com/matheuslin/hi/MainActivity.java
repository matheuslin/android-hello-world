package br.com.matheuslin.hi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void openTutorial(View view){
    	Intent intent = new Intent(this, TutorialApp.class);
    	startActivity(intent);
    }
    
    public void openLocator(View view){
    	Intent intent = new Intent(this, LocatorActivity.class);
    	startActivity(intent);
    }
}
