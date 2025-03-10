package Fudbal;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class SoccerBetF {
    private static List<String> idUtakmica = new ArrayList<>();

    public static JSONArray praviAPI() {
        String pocetakUrl = "https://www.soccerbet.rs/restapi/offer/sr/match/";
        String krajUrl = "?annex=0&mobileVersion=2.31.7&locale=sr";
        List<String> ids = uzmiIdUtakmica();
        JSONArray sveUtakmice = new JSONArray();

        for (String id : ids) {
            try {
                String stringUrl = pocetakUrl + id + krajUrl;
                String jsonResponse = pozoviAPI.pozoviAPI(stringUrl);
                String jsonText = "[" + jsonResponse + "]";
                JSONArray utakmice = new JSONArray(jsonText);
                for (int i = 0; i < utakmice.length(); i++) {
                    JSONObject utakmica = utakmice.getJSONObject(i);
                    JSONObject izvuceniPodaci = izvuceniPodaci(utakmica);
                    dodajKladionicu(izvuceniPodaci);
                    sveUtakmice.put(izvuceniPodaci);
                }
            } catch (Exception e) {
                // Logujte izuzetak i nastavite dalje
                System.err.println("Greska prilikom obrade utakmice sa id: " + id);
                e.printStackTrace();
            }
        }
        //System.out.println(sveUtakmice);
        return sveUtakmice;
    }

    public static List<String> uzmiIdUtakmica() {
        String urlString = "https://www.soccerbet.rs/restapi/offer/sr/sport/S/mob?annex=0&mobileVersion=2.31.7&locale=sr";
        String jsonResponse = pozoviAPI.pozoviAPI(urlString);

        if (jsonResponse != null) {
            JSONObject jsonResponseObj = new JSONObject(jsonResponse);
            JSONArray esMatches = jsonResponseObj.getJSONArray("esMatches");

            for (int i = 0; i < esMatches.length(); i++) {
                JSONObject match = esMatches.getJSONObject(i);
                int matchId = match.getInt("id");
                idUtakmica.add(String.valueOf(matchId));
            }
        }

        return idUtakmica;
    }

    public static JSONObject izvuceniPodaci(JSONObject utakmica) {
        JSONObject izvuceniPodaci = new JSONObject();
        if (utakmica != null) {
            String home = utakmica.getString("home");
            String away = utakmica.getString("away");
            String leagueName = utakmica.optString("leagueName", "Unknown");
            long kickOffTime = utakmica.getLong("kickOffTime");

            JSONObject betMap = utakmica.optJSONObject("betMap");

            if (betMap != null) {
                int[] kljucevi = {272, 273, 299, 300, 291, 292, 264, 265, 611, 612, 613, 614, 7, 9, 207, 208, 209, 211, 472, 213, 214, 215, 217, 474, 248, 551, 276, 250, 552, 277};

                for (int key : kljucevi) {
                    String keyStr = String.valueOf(key);
                    JSONObject kolona = betMap.optJSONObject(keyStr);
                    double ovValue = 1; // Postavljamo podrazumevanu vrednost na 1

                    if (kolona != null) {
                        JSONObject nullObject = kolona.optJSONObject("NULL");
                        if (nullObject != null && nullObject.has("ov")) {
                            ovValue = nullObject.getDouble("ov");
                        }
                    }

                    switch (key) {
                        case 272:
                            izvuceniPodaci.put("GG", ovValue);
                            break;
                        case 273:
                            izvuceniPodaci.put("NG", ovValue);
                            break;
                        case 299:
                            izvuceniPodaci.put("GG2", ovValue);
                            break;
                        case 300:
                            izvuceniPodaci.put("NG2", ovValue);
                            break;
                        case 291:
                            izvuceniPodaci.put("GG1", ovValue);
                            break;
                        case 292:
                            izvuceniPodaci.put("NG1", ovValue);
                            break;
                        case 264:
                            izvuceniPodaci.put("W1", ovValue);
                            break;
                        case 265:
                            izvuceniPodaci.put("W2", ovValue);
                            break;
                        case 611:
                            izvuceniPodaci.put("1PW1", ovValue);
                            break;
                        case 612:
                            izvuceniPodaci.put("1PW2", ovValue);
                            break;
                        case 613:
                            izvuceniPodaci.put("2PW1", ovValue);
                            break;
                        case 614:
                            izvuceniPodaci.put("2PW2", ovValue);
                            break;
                        case 7:
                            izvuceniPodaci.put("1X", ovValue);
                            break;
                        case 9:
                            izvuceniPodaci.put("X2", ovValue);
                            break;
                        case 207:
                            izvuceniPodaci.put("UG1P1+", ovValue);
                            break;
                        case 208:
                            izvuceniPodaci.put("UG1P2+", ovValue);
                            break;
                        case 209:
                            izvuceniPodaci.put("UG1P3+", ovValue);
                            break;
                        case 211:
                            izvuceniPodaci.put("UG1P0-1", ovValue);
                            break;
                        case 472:
                            izvuceniPodaci.put("UG1P0-2", ovValue);
                            break;
                        case 213:
                            izvuceniPodaci.put("UG2P1+", ovValue);
                            break;
                        case 214:
                            izvuceniPodaci.put("UG2P2+", ovValue);
                            break;
                        case 215:
                            izvuceniPodaci.put("UG2P3+", ovValue);
                            break;
                        case 217:
                            izvuceniPodaci.put("UG2P0-1", ovValue);
                            break;
                        case 474:
                            izvuceniPodaci.put("UG2P0-2", ovValue);
                            break;
                        case 248:
                            izvuceniPodaci.put("D2+", ovValue);
                            break;
                        case 551:
                            izvuceniPodaci.put("D0-2", ovValue);
                            break;
                        case 276:
                            izvuceniPodaci.put("D3+", ovValue);
                            break;
                        case 250:
                            izvuceniPodaci.put("G2+", ovValue);
                            break;
                        case 552:
                            izvuceniPodaci.put("G0-2", ovValue);
                            break;
                        case 277:
                            izvuceniPodaci.put("G3+", ovValue);
                            break;
                        default:
                            break;
                    }
                }
            }

            izvuceniPodaci.put("home", home);
            izvuceniPodaci.put("away", away);
            izvuceniPodaci.put("Liga", leagueName);
            izvuceniPodaci.put("Start", kickOffTime);
            izvuceniPodaci.put("Utakmica", home + " - " + away);
        }
        return izvuceniPodaci;
    }

    public static void dodajKladionicu(JSONObject matchData) {
        matchData.put("Kladionica", "SoccerBet"); // Dodajemo informaciju o kladionici
    }
}
