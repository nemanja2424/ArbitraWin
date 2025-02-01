package Fudbal;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaxbetF /*extends mobAnexInfo*/ {

	public static JSONArray praviAPI() {
	    List<String> idLiga = uzmiIdLiga();

	    String pocetakUrl = "https://www.maxbet.rs/restapi/offer/sr/sport/S/league/";
	    String krajUrl = "/mob?annex=3&desktopVersion=2.30.10&locale=sr";

	    JSONArray sveUtakmice = new JSONArray();

	    for (String id : idLiga) {
	        String ligaUrl = pocetakUrl + id + krajUrl;
	        String odgovor = pozoviAPI.pozoviAPI(ligaUrl);

	        if (odgovor != null) {
	            JSONArray utakmiceLige = new JSONArray(izvuceniPodaci(odgovor));
	            dodajKladionicu(utakmiceLige); // Dodajemo informaciju o kladionici MaxBet

	            // Dodajemo svaki objekat iz utakmiceLige direktno u sveUtakmice
	            for (int i = 0; i < utakmiceLige.length(); i++) {
	                sveUtakmice.put(utakmiceLige.get(i));
	            }
	        }
	    }

	    // Ispis rezultata kao JSON string na konzolu
	    //System.out.println(sveUtakmice.toString());
	    return sveUtakmice;
	}


    public static List<String> uzmiIdLiga() {
        List<String> idLiga = new ArrayList<>();

        try {
            String urlString = "https://www.maxbet.rs/restapi/offer/sr/categories/sport/S/l?annex=3&mobileVersion=2.30.12&locale=sr";
            Scanner scanner = new Scanner(new URL(urlString).openStream());
            StringBuilder responseBuilder = new StringBuilder();

            while (scanner.hasNextLine()) {
                responseBuilder.append(scanner.nextLine());
            }
            scanner.close();

            JSONObject jsonResponse = new JSONObject(responseBuilder.toString());
            JSONArray kategorije = jsonResponse.getJSONArray("categories");

            for (int i = 0; i < kategorije.length(); i++) {
                JSONObject kategorija = kategorije.getJSONObject(i);
                String id = kategorija.getString("id");
                idLiga.add(id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return idLiga;
    }
    
    
    public static void dodajKladionicu(JSONArray matchesArray) {
        for (int i = 0; i < matchesArray.length(); i++) {
            JSONObject matchData = matchesArray.getJSONObject(i);
            matchData.put("Kladionica", "MaxBet"); // Dodajemo informaciju o kladionici
        }
    }

    public static String izvuceniPodaci(String jsonString) {
        MobAnexInfo extractor = new MobAnexInfo();
        return extractor.izvuceniPodaci(jsonString);
    }
}

