package com.example.prueba;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import com.example.custom.ObjetoPosicion;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;


public class MapaActivity extends MapActivity {
	
	MapView mapView;
	List<OverlayItem> listaPuntos = new ArrayList<OverlayItem>();
	List<ObjetoPosicion> listaPosiciones = new ArrayList<ObjetoPosicion>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_mapa);
	    
	    mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	}
	
	@Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}
