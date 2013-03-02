package com.example.custom;

import java.io.Serializable;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.example.prueba.PrincipalActivity;
import com.example.util.WebServiceUtil;


public class DownloadTask extends AsyncTask<String, Void, Object> implements Serializable{
	
	private static final long serialVersionUID = -2537374909989113250L;
	String res = null;
	private Context contexto;
	
	protected void onPostExecute(Object result){
		PrincipalActivity.pd.dismiss();
		Toast.makeText(contexto, "Clima: " + res, Toast.LENGTH_LONG).show();
		super.onPostExecute(result);
	}

	@Override
	protected Object doInBackground(String... params) {
		res = WebServiceUtil.enviaDatosAlServidor();
		return 1;
	}
	
	public void setContexto(Context contexto){
		this.contexto = contexto;
	}
	
	public Context getContexto(){
		return contexto;
	}
}
