package Fudbal;

import org.json.JSONArray;
import org.json.JSONObject;

public class OktagonBetF {

	public static JSONArray praviAPI() {
		String url = "https://www.oktagonbet.com/restapi/offer/sr/sport/S/mob?annex=1&mobileVersion=2.28.40&locale=sr";
		String odgovor = pozoviAPI.pozoviAPI(url);
		JSONArray sveUtakmice = new JSONArray();
		
		if (odgovor != null) {
            JSONArray utakmiceLige = new JSONArray(izvuceniPodaci(odgovor));
            dodajKladionicu(utakmiceLige); // Dodajemo informaciju o kladionici MaxBet

            // Dodajemo svaki objekat iz utakmiceLige direktno u sveUtakmice
            for (int i = 0; i < utakmiceLige.length(); i++) {
                sveUtakmice.put(utakmiceLige.get(i));
            }
        }
		//System.out.println(sveUtakmice);
		return sveUtakmice;
	}
	
	public static void dodajKladionicu(JSONArray matchesArray) {
        for (int i = 0; i < matchesArray.length(); i++) {
            JSONObject matchData = matchesArray.getJSONObject(i);
            matchData.put("Kladionica", "OktagonBet"); // Dodajemo informaciju o kladionici
        }
    }

    public static String izvuceniPodaci(String jsonString) {
        MobAnexInfo extractor = new MobAnexInfo();
        return extractor.izvuceniPodaci(jsonString);
    }
	
}
