package kosarkasi;

import org.json.JSONArray;
import org.json.JSONObject;

import Fudbal.pozoviAPI;

import java.util.ArrayList;
import java.util.List;

public class BetOleKS /* extends mobAnexInfo */ {

    private static final String POCETAK_URL_LEAGUE = "https://www.merkurxtip.rs/restapi/offer/sr/sport/SK/league/";
    private static final String KRAJ_URL_LEAGUE = "/mob?annex=0&mobileVersion=2.32.2.1&locale=sr";
    
    public static JSONArray praviAPI() {
        List<String> savedMatchIds = uzmiIdLiga();
        JSONArray sveUtakmice = new JSONArray();
        
        for (String id : savedMatchIds) {
            String utakmicaUrl = POCETAK_URL_LEAGUE + id + KRAJ_URL_LEAGUE;
            String odgovor = pozoviAPI.pozoviAPI(utakmicaUrl);

            if (odgovor != null) {
                String jsonText = "[" + odgovor + "]";
                JSONArray utakmiceLige = new JSONArray(jsonText);
                //System.out.println(jsonText);

                // Izvučeni podaci se obrađuju pre dodavanja informacija o kladionici
                String processedMatches = izvuceniPodaci(utakmiceLige.toString());
                JSONArray processedMatchesArray = new JSONArray(processedMatches);
                
                dodajKladionicu(processedMatchesArray);

                // Dodavanje svakog JSONObject-a iz processedMatchesArray u sveUtakmice
                for (int i = 0; i < processedMatchesArray.length(); i++) {
                    sveUtakmice.put(processedMatchesArray.getJSONObject(i));
                }
            }
        }

        //System.out.println(sveUtakmice.toString());
        return sveUtakmice;
    }

    public static List<String> uzmiIdLiga() {
        List<String> idLiga = new ArrayList<>();

        try {
            String urlString = "https://www.merkurxtip.rs/restapi/offer/sr/categories/gl/SK?annex=0&mobileVersion=2.32.2.1&locale=sr";
            String odgovor = pozoviAPI.pozoviAPI(urlString);

            if (odgovor != null) {
                JSONObject jsonResponse = new JSONObject(odgovor);
                JSONArray kategorije = jsonResponse.getJSONArray("categories");

                for (int i = 0; i < kategorije.length(); i++) {
                    JSONObject kategorija = kategorije.getJSONObject(i);

                    if (kategorija.has("children")) {
                        JSONArray children = kategorija.getJSONArray("children");

                        for (int j = 0; j < children.length(); j++) {
                            String leagueId = children.getJSONObject(j).getString("id");
                            idLiga.add(leagueId);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return idLiga;
    }

    public static void dodajKladionicu(JSONArray matchesArray) {
        for (int i = 0; i < matchesArray.length(); i++) {
            JSONObject matchData = matchesArray.getJSONObject(i);
            matchData.put("Kladionica", "BetOle");
        }
    }

    public static String izvuceniPodaci(String jsonString) {
        if (jsonString != null) {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONArray matchesDataArray = new JSONArray();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (jsonObject.has("esMatches")) {
                    JSONArray esMatchesArray = jsonObject.getJSONArray("esMatches");

                    for (int j = 0; j < esMatchesArray.length(); j++) {
                        JSONObject matchObject = esMatchesArray.getJSONObject(j);

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

                            matchDataObject.put("Kladionica", "BetOle");

                            matchesDataArray.put(matchDataObject);
                        }
                    }
                }
            }
            return matchesDataArray.toString();
        }
        return "[]";
    }
}
