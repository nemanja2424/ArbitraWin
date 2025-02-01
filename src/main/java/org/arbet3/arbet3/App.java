package org.arbet3.arbet3;

import kalkulator.*;
import org.json.JSONArray;
import org.json.JSONObject;
import Fudbal.pozoviAPI;



public class App 
{
    public static String[] konvertovaniBrojevi;

    public static void main(String[] args) {
    	
    	System.out.println("Pocetak rada...");
    	
    	String endpoint = "https://x8ki-letl-twmt.n7.xano.io/api:etI0bpsP/uzmiBrojeve";
    	String response = pozoviAPI.pozoviAPI(endpoint);
    	JSONArray brojevi = new JSONArray(response);
    	
    	konvertovaniBrojevi = new String[brojevi.length()];

    	for (int i = 0; i < brojevi.length(); i++) {
        	JSONObject broj = brojevi.getJSONObject(i);
        	String originalBroj = broj.getString("brTel");
        	
        	if (originalBroj.startsWith("0")) {
            	konvertovaniBrojevi[i] = "+381" + originalBroj.substring(1);
        	} else {
            	konvertovaniBrojevi[i] = originalBroj;
        	}
    	}
    	//System.out.println(Arrays.toString(konvertovaniBrojevi));
    	
    	
        while (true) {
        	Kosarka.main(args);
        	Kosarkasi.main(args);
            Fudbal.main(args);
            Tenis.main(args);
            Rukomet.main(args);
        }
    }
}
