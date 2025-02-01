package kosarkasi;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import Fudbal.pozoviAPI;

public class MaxbetKS {
	
	public static JSONArray praviAPI() {
	    List<String> idLiga = uzmiIdLiga();

	    String pocetakUrl = "https://www.maxbet.rs/restapi/offer/sr/sport/SK/league/";
	    String krajUrl = "/mob?annex=3&desktopVersion=2.30.10&locale=sr";

	    JSONArray sveUtakmice = new JSONArray();

	    for (String id : idLiga) {
	        String ligaUrl = pocetakUrl + id + krajUrl;
	        String odgovor = pozoviAPI.pozoviAPI(ligaUrl);

	        if (odgovor != null) {
	            JSONArray utakmiceLige = new JSONArray(izvuceniPodaci(odgovor));
	            // Dodajemo svaki objekat iz utakmiceLige direktno u sveUtakmice
	            for (int i = 0; i < utakmiceLige.length(); i++) {
	                sveUtakmice.put(utakmiceLige.get(i));
	            }
	        }
	    }

	    //System.out.println(sveUtakmice.toString());
	    return sveUtakmice;
	}
	
	public static List<String> uzmiIdLiga() {
        List<String> idLiga = new ArrayList<>();

        try {
            String urlString = "https://www.maxbet.rs/restapi/offer/sr/categories/sport/SK/l?annex=3&mobileVersion=2.30.12&locale=sr";
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
        //System.out.println(idLiga);
        return idLiga;
    }
	
	public static String izvuceniPodaci(String jsonString) {
        if (jsonString != null) {
            JSONObject jsonObject = new JSONObject(jsonString);

            if (jsonObject.has("esMatches")) {
                JSONArray esMatchesArray = jsonObject.getJSONArray("esMatches");
                JSONArray matchesDataArray = new JSONArray();

                for (int i = 0; i < esMatchesArray.length(); i++) {
                    JSONObject matchObject = esMatchesArray.getJSONObject(i);

                    if (matchObject.has("home") && matchObject.has("away") && matchObject.has("kickOffTime")
                            && matchObject.has("odds") && matchObject.has("params")) {
                        String homeTeam = matchObject.getString("home");
                        String awayTeam = matchObject.getString("away");
                        long kickOffTime = matchObject.getLong("kickOffTime");
                        String liga = matchObject.getString("leagueName");

                        JSONObject oddsObject = matchObject.getJSONObject("odds");
                        double visePoena = oddsObject.optDouble("51679", 1);
						double manjePoena = oddsObject.optDouble("51681", 1);
						double viseAsist = oddsObject.optDouble("51682", 1);
						double manjeAsist = oddsObject.optDouble("51684", 1);
						double viseSkok = oddsObject.optDouble("51685", 1);
						double manjeSkok = oddsObject.optDouble("51687", 1);
						
						JSONObject graniceObject = matchObject.getJSONObject("params");
						double granicaPoeni = graniceObject.optDouble("ouPlPoints", 1);
						double granicaAsist = graniceObject.optDouble("ouPlAssists", 1);
						double granicaSkok = graniceObject.optDouble("ouPlRebounds", 1);
						
						JSONObject matchDataObject = new JSONObject();
						matchDataObject.put("Igrac", homeTeam);
						matchDataObject.put("Start", kickOffTime);
                        matchDataObject.put("Liga", liga);
                        matchDataObject.put("Tim", awayTeam);
						
						matchDataObject.put("GranicaPoena", granicaPoeni);
						matchDataObject.put("VisePoena", visePoena);
						matchDataObject.put("ManjePoena", manjePoena);
						
						matchDataObject.put("GranicaAsistencija", granicaAsist);
						matchDataObject.put("ViseAsistencija", viseAsist);
						matchDataObject.put("ManjeAsistencija", manjeAsist);
						
						matchDataObject.put("GranicaSkokova", granicaSkok);
						matchDataObject.put("ViseSkokova", viseSkok);
						matchDataObject.put("ManjeSkokova", manjeSkok);
						
						matchDataObject.put("Kladionica", "MaxBet");
						
						matchesDataArray.put(matchDataObject);
					}
                }

                return matchesDataArray.toString();
            }
        }
        return izvuceniPodaci(null);
    }
}
	
