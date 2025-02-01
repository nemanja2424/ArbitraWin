package Fudbal;

import org.json.JSONArray;
import org.json.JSONObject;

public class MobAnexInfo {
    public String izvuceniPodaci(String jsonString) {
        if (jsonString != null) {
            JSONObject jsonObject = new JSONObject(jsonString);

            if (jsonObject.has("esMatches")) {
                JSONArray esMatchesArray = jsonObject.getJSONArray("esMatches");
                JSONArray matchesDataArray = new JSONArray();

                for (int i = 0; i < esMatchesArray.length(); i++) {
                    JSONObject matchObject = esMatchesArray.getJSONObject(i);

                    if (matchObject.has("home") && matchObject.has("away") && matchObject.has("kickOffTime")
                            && matchObject.has("odds")) {
                        String homeTeam = matchObject.getString("home");
                        String awayTeam = matchObject.getString("away");
                        long kickOffTime = matchObject.getLong("kickOffTime");
                        String leagueName = matchObject.getString("leagueName");

                        JSONObject oddsObject = matchObject.getJSONObject("odds");
                        double kolona272 = oddsObject.optDouble("272", 1);
						double kolona273 = oddsObject.optDouble("273", 1);
						double kolona299 = oddsObject.optDouble("299", 1);
						double kolona300 = oddsObject.optDouble("300", 1);
						double kolona291 = oddsObject.optDouble("291", 1);
						double kolona292 = oddsObject.optDouble("292", 1);
						double kolona264 = oddsObject.optDouble("264", 1);
						double kolona265 = oddsObject.optDouble("265", 1);
						double kolona611 = oddsObject.optDouble("611", 1);
						double kolona612 = oddsObject.optDouble("612", 1);
						double kolona613 = oddsObject.optDouble("613", 1);
						double kolona614 = oddsObject.optDouble("614", 1);
						double kolona7 = oddsObject.optDouble("7", 1);
						double kolona9 = oddsObject.optDouble("9", 1);
						double kolona207 = oddsObject.optDouble("207", 1);
						double kolona208 = oddsObject.optDouble("208", 1);
						double kolona209 = oddsObject.optDouble("209", 1);
						double kolona211 = oddsObject.optDouble("211", 1);
						double kolona472 = oddsObject.optDouble("472", 1);
						double kolona213 = oddsObject.optDouble("213", 1);
						double kolona214 = oddsObject.optDouble("214", 1);
						double kolona215 = oddsObject.optDouble("215", 1);
						double kolona217 = oddsObject.optDouble("217", 1);
						double kolona474 = oddsObject.optDouble("474", 1);
						double kolona248 = oddsObject.optDouble("248", 1);
						double kolona551 = oddsObject.optDouble("551", 1);
						double kolona276 = oddsObject.optDouble("276", 1);
						double kolona250 = oddsObject.optDouble("250", 1);
						double kolona552 = oddsObject.optDouble("552", 1);
						double kolona277 = oddsObject.optDouble("277", 1);
						
						
						JSONObject matchDataObject = new JSONObject();
						matchDataObject.put("Utakmica", homeTeam + "-" + awayTeam);
						matchDataObject.put("home", homeTeam);
						matchDataObject.put("away", awayTeam);
						matchDataObject.put("Start", kickOffTime);
						matchDataObject.put("Liga", leagueName);
						 
						matchDataObject.put("GG", kolona272);//
						matchDataObject.put("NG", kolona273);	//
						matchDataObject.put("GG2", kolona299);//
						matchDataObject.put("NG2", kolona300);//
						matchDataObject.put("GG1", kolona291);//
						matchDataObject.put("NG1", kolona292);//
						matchDataObject.put("W1", kolona264);//
						matchDataObject.put("W2", kolona265);//
						matchDataObject.put("1PW1", kolona611);//
						matchDataObject.put("1PW2", kolona612);//
						matchDataObject.put("2PW1", kolona613);//
						matchDataObject.put("2PW2", kolona614);//
						matchDataObject.put("1X", kolona7);//
						matchDataObject.put("X2", kolona9);//
						
						matchDataObject.put("UG1P1+", kolona207);//
						matchDataObject.put("UG1P2+", kolona208);//
						matchDataObject.put("UG1P3+", kolona209);//
						matchDataObject.put("UG1P0-1", kolona211);//
						matchDataObject.put("UG1P0-2", kolona472);//
						
						matchDataObject.put("UG2P1+", kolona213);//
						matchDataObject.put("UG2P2+", kolona214);//
						matchDataObject.put("UG2P3+", kolona215);//
						matchDataObject.put("UG2P0-1", kolona217);//
						matchDataObject.put("UG2P0-2", kolona474);//
						
						matchDataObject.put("D2+", kolona248);
						matchDataObject.put("D0-2", kolona551);
						matchDataObject.put("D3+", kolona276);
						
						matchDataObject.put("G2+", kolona250);
						matchDataObject.put("G0-2", kolona552);
						matchDataObject.put("G3+", kolona277);

						matchesDataArray.put(matchDataObject);
					}
                }

                return matchesDataArray.toString();
            }
        }

        return null;
    }
}

