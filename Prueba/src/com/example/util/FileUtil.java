package com.example.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import com.example.custom.ObjetoPosiciones;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.widget.Toast;


public class FileUtil implements Serializable{
	
	private static final long serialVersionUID = -3721769765641235234L;
	
	
	/**
	 * Metodo que se le pasa un objeto de tipo Resources (clase que extiende a Activity) y devuelve
	 * el fichero de propiedades "config.properties"
	 * @param Resources
	 * @return Properties
	 */
	public static Properties getFicheroAssetConfiguracion(Resources recurso) throws IOException{	
		Properties properties = new Properties();
		Resources resources = recurso;
		AssetManager assetManager = resources.getAssets();
		
		InputStream inputStream = assetManager.open(Constantes.FICHERO_CONFIGURACION);
		properties.load(inputStream);
		
		return properties;
	}
	
	
	
	/**
	 * 
	 * @param recurso
	 * @return
	 * @throws IOException
	 */
	public static List<ObjetoPosiciones> getListaAssetPosiciones(Resources recurso, Context ctx) throws IOException{
		List<ObjetoPosiciones> listaPosiciones = null;
//		Resources resources = recurso;
//		AssetManager assetManager = resources.getAssets();
		
//		System.out.println(assetManager.getLocales().toString());
		
//		for(String fichero : assetManager.getLocales()){
//			if(fichero.toString().endsWith(Constantes.FICHERO_POSICIONES)){
//				InputStream input = assetManager.open(Constantes.FICHERO_POSICIONES);
//				
//				if(input != null){
//					ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
//					byte[] bytes = new byte[4096];
//					int len = 0;
//					while((len = input.read(bytes)) > 0){
//						byteStream.write(bytes, 0, len);
//					
//						String linea = new String(byteStream.toByteArray(), "UTF8");
//						ObjetoPosiciones pos = new ObjetoPosiciones();
//						pos.setFecha(null);
//						pos.setLatitud(null);
//						pos.setLongitud(null);
//						listaPosiciones.add(pos);
//					}
//				}
//			}
//		}
		
		
		
		try{
			// open the file for reading
//			InputStream instream = ctx.openFileInput(Constantes.FICHERO_POSICIONES);
			InputStream instream = recurso.getAssets().open(Constantes.FICHERO_POSICIONES);
			
			// if file the available for reading
			if(instream != null){
				// prepare the file for reading
				InputStreamReader inputreader = new InputStreamReader(instream);
				BufferedReader buffreader = new BufferedReader(inputreader);
				
				// read every line of the file into the line-variable, on line at the time
				String line = buffreader.readLine();
				while(line != null){
					line = buffreader.readLine();
				}
		    }
		     
			// close the file again      
			instream.close();
		}catch(FileNotFoundException e){
			// do something if the myfilename.txt does not exits
			e.printStackTrace();
		}
		return listaPosiciones;
	}
}
