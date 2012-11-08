package com.example.prueba;

import java.io.InputStream;
import java.util.Properties;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class InicioActivity extends Activity {
	
	public static String USUARIO = null;
	public static String CONTRASENA = null;
	private static final String FICHERO_CONFIGURACION = "config.properties";
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_inicio, menu);
        return true;
    }
    
    
    public void nuevoUsuarioLink(View view){
    	
    }
    
    
    public void recordarContrasenaLink(View view){
    	
    }
    
    
    public void accesoUsuarioRegistrado(View view){
    	try{
    		EditText textoUsuario = (EditText) findViewById(R.id.editText1);
    		EditText passUsuario = (EditText) findViewById(R.id.editText2);
    		
    		if(textoUsuario == null || passUsuario == null){
    			String text = "debe introducir el usuario y la contraseņa";
    			Toast.makeText(InicioActivity.this, text, Toast.LENGTH_LONG).show();
    		}else{
    			Resources resources = this.getResources();
    			AssetManager assetManager = resources.getAssets();
    			
    			InputStream inputStream = assetManager.open(FICHERO_CONFIGURACION);
    		    Properties properties = new Properties();
    		    properties.load(inputStream);
    			
    		    if(properties != null){
    		    	String usuario = (String)properties.get("usuario");
    		    	if(usuario != null && usuario.length() > 0)
        				USUARIO = usuario;
    		    	
    		    	String contrasena = (String)properties.get("contrasena");
    		    	if(contrasena != null && contrasena.length() > 0)
        				CONTRASENA = contrasena;
    		    	
    		    	if(textoUsuario.getText().toString().equals(USUARIO) && passUsuario.getText().toString().equals(CONTRASENA)){
        				// usuario y contraseņa corrrectos
        				Intent intent = new Intent(InicioActivity.this, PrincipalActivity.class);
        				startActivity(intent);
        			}else{
        				String text = "Los datos introducidos son incorrectos";
            			Toast.makeText(InicioActivity.this, text, Toast.LENGTH_LONG).show();
            			passUsuario.setText("");
        			}
    		    }
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
}
