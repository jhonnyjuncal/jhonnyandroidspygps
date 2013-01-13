package com.example.custom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;


public class PosicionesItemizedOverlay extends ItemizedOverlay implements Serializable{
	
	private static final long serialVersionUID = -185733511512503723L;
	
	private List<OverlayItem> lista = new ArrayList<OverlayItem>();
	private Context mContext;
	
	
	public PosicionesItemizedOverlay(Drawable defaultMarker, Context ctx) {
		super(defaultMarker);
		mContext = ctx;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return lista.get(i);
	}

	@Override
	public int size() {
		return lista.size();
	}
	
	public void addOverlay(OverlayItem overlay) {
		try{
			lista.add(overlay);
			populate();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Override
	protected boolean onTap(int index) {
		try{
			OverlayItem item = lista.get(index);
			AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
			dialog.setTitle(item.getTitle());
			dialog.setMessage(item.getSnippet());
			dialog.show();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}
