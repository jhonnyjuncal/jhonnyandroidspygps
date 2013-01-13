package com.example.util;

import java.io.Serializable;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class WebServiceUtil implements Serializable{
	
	private static final long serialVersionUID = -8259214176606491617L;
	
	
	public static String enviaDatosAlServidor(){
		String resultado = null;
		String targetNamespace = "http://www.webserviceX.NET";
		String method = "GetWeather";
		SoapObject rpc = new SoapObject(targetNamespace, method);
		
		rpc.addProperty("CityName", "madrid");
		rpc.addProperty("CountryName", "spain");
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		
		envelope.dotNet = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		
		HttpTransportSE androidHttpTransport = null;
		try{
			String endPoint = "http://www.webservicex.net/globalweather.asmx";
			androidHttpTransport = new HttpTransportSE(endPoint);
			androidHttpTransport.debug = true;
			
			String soapAction = "http://www.webserviceX.NET/GetWeather";
			androidHttpTransport.call(soapAction, envelope);
			
			resultado = envelope.getResponse().toString();
			System.out.println("*******************************************");
			System.out.println(resultado);
			System.out.println("*******************************************");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return resultado;
	}
}
