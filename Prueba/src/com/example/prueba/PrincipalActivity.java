package com.example.prueba;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class PrincipalActivity extends Activity {
	
	public static Integer DISTANCIA_MINIMA_PARA_ACTUALIZACIONES = 1; // in Meters
	public static Integer TIEMPO_MINIMO_ENTRE_ACTUALIZACIONES = 1000; // in Milliseconds
	private static final String FICHERO_CONFIGURACION = "config.properties";
	protected LocationManager locationManager;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	try{
    		super.onCreate(savedInstanceState);
    		setContentView(R.layout.activity_principal);
        
    		// carga los datos de la configuracion
    		cargaDatosConfiguracion();
		
    		// estado actual de la wifi
    		informaEstadoActualGPS();
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	try{
	        getMenuInflater().inflate(R.menu.activity_principal, menu);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return true;
    }
    
    
    @Override
    public void onDestroy(){
    	try{
    		guardaDatosConfiguracion();
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    /**
     * boton solo wifi
     * @param view
     */
    public void soloWifi(View view){
    	try{
	    	if(R.id.button1 == view.getId()){
	    		TextView texto1 = (TextView) findViewById(R.id.textView2);
	    		texto1.setText("boton Solo wifi");
	    	}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    /**
     * boton solo 3g
     * @param view
     */
    public void solo3g(View view){
    	try{
	    	if(R.id.button2 == view.getId()){
	    		TextView texto2 = (TextView) findViewById(R.id.textView4);
	    		texto2.setText("boton Solo 3G");
	    	}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    /**
     * boton enviar datos
     * @param view
     */
    public void enviarDatos(View view){
    	try{
	    	if(R.id.button3 == view.getId()){
	    		TextView texto3 = (TextView) findViewById(R.id.textView6);
	    		texto3.setText("boton Enviar Datos");
	    		setContentView(R.layout.activity_posiciones);
	    	}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    /**
     * muestra el estado actual del GPS
     */
    private void informaEstadoActualGPS(){
    	try{
    		// obtiene el objeto GPS
    		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    		boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    		if(enabled == false){
    			 Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    			 startActivity(intent);
    		}else{
    			TextView textoStatus = (TextView) findViewById(R.id.textView2);
    			textoStatus.setText("encendido");
    		}
    		
    		// listener del GPS
    		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 
    				TIEMPO_MINIMO_ENTRE_ACTUALIZACIONES,
    				DISTANCIA_MINIMA_PARA_ACTUALIZACIONES, 
    				new MyLocationListener());
    		
    		// localizador del GPS
    		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    		if(location != null){
    			String message = String.format("Current Location \n Longitude: %1$s \n Latitude: %2$s",
    					location.getLongitude(), location.getLatitude());
    			Toast.makeText(PrincipalActivity.this, message, Toast.LENGTH_LONG).show();
    		}
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    /**
     * Carga el fichero de configuracion "config.properties"
     */
    private void cargaDatosConfiguracion(){
    	try{
    		// lectura del fichero de configuracion
    		SharedPreferences prefs = getSharedPreferences(FICHERO_CONFIGURACION, Context.MODE_PRIVATE);
    		
    		if(prefs != null){
    			Integer tiempo = prefs.getInt("tiempoMinimoActualizacion", TIEMPO_MINIMO_ENTRE_ACTUALIZACIONES);
    			if(tiempo != null && tiempo > 0)
    				TIEMPO_MINIMO_ENTRE_ACTUALIZACIONES = tiempo;
    			
    			Integer distancia = prefs.getInt("distanciaMinimaActualizacion", DISTANCIA_MINIMA_PARA_ACTUALIZACIONES);
    			if(distancia != null && distancia > 0)
    				DISTANCIA_MINIMA_PARA_ACTUALIZACIONES = distancia;
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    private void guardaDatosConfiguracion(){
    	try{
    		// lectura del fichero de configuracion
    		SharedPreferences prefs = getSharedPreferences(FICHERO_CONFIGURACION, Context.MODE_WORLD_WRITEABLE);
    		SharedPreferences.Editor editor = prefs.edit();
    		
    		if(prefs != null){
    			// hay que recoger los datos introducidos por el usuario en la configuracion
    			editor.putInt("tiempoMinimoActualizacion", TIEMPO_MINIMO_ENTRE_ACTUALIZACIONES);
    			editor.putInt("distanciaMinimaActualizacion", DISTANCIA_MINIMA_PARA_ACTUALIZACIONES);
    			editor.commit();
    			finish();
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    public class MyLocationListener implements LocationListener{
    	public void onLocationChanged(Location location) {
    		String message = String.format("New Location \n Longitude: %1$s \n Latitude: %2$s", location.getLongitude(), location.getLatitude());
    		Toast.makeText(PrincipalActivity.this, message, Toast.LENGTH_LONG).show();
    	}
    	public void onStatusChanged(String s, int i, Bundle b) {
    		Toast.makeText(PrincipalActivity.this, "Provider status changed", Toast.LENGTH_LONG).show();
    	}
    	public void onProviderDisabled(String s) {
    		Toast.makeText(PrincipalActivity.this, "Provider disabled by the user. GPS turned off", Toast.LENGTH_LONG).show();
    	}
    	public void onProviderEnabled(String s) {
    		Toast.makeText(PrincipalActivity.this, "Provider enabled by the user. GPS turned on", Toast.LENGTH_LONG).show();
    	}
    }
}
