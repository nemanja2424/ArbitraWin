package rukomet;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import Fudbal.pozoviAPI;

public class SoccerBetR {
	private static List<String> idUtakmica = new ArrayList<>();

	public static JSONArray praviAPI() {
		String pocetakUrl = "https://www.soccerbet.rs/restapi/offer/sr/match/";
		String krajUrl = "?annex=0&mobileVersion=2.34.3.11&locale=sr";
		List<String> ids = uzmiIdUtakmica();
		JSONArray sveUtakmice = new JSONArray();

		for (String id : ids) {
			String stringUrl = pocetakUrl + id + krajUrl;
			String jsonResponse = pozoviAPI.pozoviAPI(stringUrl);
			String jsonText = "[" + jsonResponse + "]";
			JSONArray utakmice = new JSONArray(jsonText);
			//System.out.println(jsonText);
			for (int i = 0; i < utakmice.length(); i++) {
				JSONObject utakmica = utakmice.getJSONObject(i);
				JSONObject izvuceniPodaci = izvuceniPodaci(utakmica);
				
				sveUtakmice.put(izvuceniPodaci);
			}
		}
		//System.out.println(sveUtakmice);
		return sveUtakmice;
	}

	public static List<String> uzmiIdUtakmica() {
		String urlString = "https://www.soccerbet.rs/restapi/offer/sr/sport/HB/mob?annex=0&mobileVersion=2.34.3.11&locale=sr";
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
	            int[] kljucevi = {
	            		7, 9, 397, 399, 608, 610, 264, 265, 611, 612, 613, 614, 227, 228, 427, 429, 430, 432, 50076, 50078, 50079, 50081, 53096, 53095, 229, 230, 390, 392, 355, 356,
	            		371, 372, 357, 358, 373, 374, 395, 396, 393, 394, 231, 232, 201, 203, 421, 423, 424, 426, 50136, 50138
	            };
	            
	          
	            
	            for (int kljuc : kljucevi) {
	            	String keyStr = String.valueOf(kljuc);
	            	JSONObject kolona = betMap.optJSONObject(keyStr);
	            	double ovValue =1;
	            	String svValue = "1";
	            	
	            	if (kolona != null) {
	            		switch (kljuc) {
	            		case 7:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("1X", ovValue);
	            			break;
	            		case 9:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("X2", ovValue);
	            			break;
	            		case 397:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("1P1X", ovValue);
	            			break;
	            		case 399:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("1PX2", ovValue);
	            			break;
	            		case 608:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("2P1X", ovValue);
	            			break;
	            		case 610:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("2PX2", ovValue);
	            			break;
	            		case 264:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("W1", ovValue);
	            			break;
	            		case 265:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("W2", ovValue);
	            			break;
	            		case 611:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("1PW1", ovValue);
	            			break;
	            		case 612:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("1PW2", ovValue);
	            			break;
	            		case 613:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("2PW1", ovValue);
	            			break;
	            		case 614:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("2PW2", ovValue);
	            			break;
	            		case 231:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("PAR", ovValue);
	            			break;
	            		case 232:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("NEPAR", ovValue);
	            			break;
	            		
	            		case 228:
	            			int iUG_MINUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iUG_MINUS++;
	            				ovValue = subObject.getDouble("ov");
	            				svValue = subObject.getString("sv").split("=")[1];
	            				String ovKey = "UG" + iUG_MINUS + "_MINUS";
	            				String svKey = "overUnderGr" + iUG_MINUS;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            				izvuceniPodaci.put(svKey, svValue);
	            			}
	            			break;
	            		case 227:
	            			int iUG_PLUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iUG_PLUS++;
	            				ovValue = subObject.getDouble("ov");
	            				String ovKey = "UG" + iUG_PLUS + "_PLUS";
	            				izvuceniPodaci.put(ovKey, ovValue);
	            			}
	            			break;
	            			
	            		case 53096:
	            			int iBJTDM = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iBJTDM++;
	            				ovValue = subObject.getDouble("ov");
	            				svValue = subObject.getString("sv").split("=")[1];
	            				String ovKey = "BJTDM" + iBJTDM;
	            				String svKey = "bothTeamScoreGr" + iBJTDM;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            				izvuceniPodaci.put(svKey, svValue);
	            			}
	            			break;
	            		case 53095:
	            			int iOTDV = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iOTDV++;
	            				ovValue = subObject.getDouble("ov");
	            				String ovKey = "OTDV" + iOTDV;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            			}
	            			break;
	            		case 201:
	            			int iH1 = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iH1++;
	            				ovValue = subObject.getDouble("ov");
	            				svValue = subObject.getString("sv").split("=")[1];
	            				String ovKey = "H1" + iH1;
	            				String svKey = "overUnderGr" + iH1;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            				izvuceniPodaci.put(svKey, svValue);
	            			}
	            			break;
	            		case 203:
	            			int iH2 = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iH2++;
	            				ovValue = subObject.getDouble("ov");
	            				String ovKey = "H2" + iH2;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            			}
	            			break;
	            		case 50136:
	            			int i2PH1 = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				i2PH1++;
	            				ovValue = subObject.getDouble("ov");
	            				svValue = subObject.getString("sv").split("=")[1];
	            				String ovKey = "2PH1" + i2PH1;
	            				String svKey = "hendikepDrugoPoluvremeGr" + i2PH1;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            				izvuceniPodaci.put(svKey, svValue);
	            			}
	            			break;
	            		case 50138:
	            			int i2PH2 = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				i2PH2++;
	            				ovValue = subObject.getDouble("ov");
	            				String ovKey = "2PH2" + i2PH2;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            			}
	            			break;
	            		case 230:
	            			int iUGP_MINUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iUGP_MINUS++;
	            				ovValue = subObject.getDouble("ov");
	            				svValue = subObject.getString("sv").split("=")[1];
	            				String ovKey = "UGP" + iUGP_MINUS + "_MINUS";
	            				String svKey = "overUnderPGr" + iUGP_MINUS;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            				izvuceniPodaci.put(svKey, svValue);
	            			}
	            			break;
	            		case 229:
	            			int iUGP_PLUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iUGP_PLUS++;
	            				ovValue = subObject.getDouble("ov");
	            				String ovKey = "UGP" + iUGP_PLUS + "_PLUS";
	            				izvuceniPodaci.put(ovKey, ovValue);
	            			}
	            			break;
	            		case 390:
	            			int iUG2P_MINUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iUG2P_MINUS++;
	            				ovValue = subObject.getDouble("ov");
	            				svValue = subObject.getString("sv").split("=")[1];
	            				String ovKey = "UG2P" + iUG2P_MINUS + "_MINUS";
	            				String svKey = "overUnderSecondHalfGr" + iUG2P_MINUS;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            				izvuceniPodaci.put(svKey, svValue);
	            			}
	            			break;
	            		case 392:
	            			int iUG2P_PLUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iUG2P_PLUS++;
	            				ovValue = subObject.getDouble("ov");
	            				String ovKey = "UG2P" + iUG2P_PLUS + "_PLUS";
	            				izvuceniPodaci.put(ovKey, ovValue);
	            			}
	            			break;
	            		case 355:
	            			int iD_MINUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iD_MINUS++;
	            				ovValue = subObject.getDouble("ov");
	            				svValue = subObject.getString("sv").split("=")[1];
	            				String ovKey = "D" + iD_MINUS + "_MINUS";
	            				String svKey = "homeOverUnderGr" + iD_MINUS;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            				izvuceniPodaci.put(svKey, svValue);
	            			}
	            			break;
	            		case 356:
	            			int iD_PLUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iD_PLUS++;
	            				ovValue = subObject.getDouble("ov");
	            				String ovKey = "D" + iD_PLUS + "_PLUS";
	            				izvuceniPodaci.put(ovKey, ovValue);
	            			}
	            			break;
	            		case 357:
	            			int iG_MINUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iG_MINUS++;
	            				ovValue = subObject.getDouble("ov");
	            				svValue = subObject.getString("sv").split("=")[1];
	            				String ovKey = "G" + iG_MINUS + "_MINUS";
	            				String svKey = "awayOverUnderGr" + iG_MINUS;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            				izvuceniPodaci.put(svKey, svValue);
	            			}
	            			break;
	            		case 358:
	            			int iG_PLUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iG_PLUS++;
	            				ovValue = subObject.getDouble("ov");
	            				String ovKey = "G" + iG_PLUS + "_PLUS";
	            				izvuceniPodaci.put(ovKey, ovValue);
	            			}
	            			break;
	            		case 371:
	            			int i1D_MINUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				i1D_MINUS++;
	            				ovValue = subObject.getDouble("ov");
	            				svValue = subObject.getString("sv").split("=")[1];
	            				String ovKey = "1D" + i1D_MINUS + "_MINUS";
	            				String svKey = "homeOverUnderFirstHalfGr" + i1D_MINUS;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            				izvuceniPodaci.put(svKey, svValue);
	            			}
	            			break;
	            		case 372:
	            			int i1D_PLUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				i1D_PLUS++;
	            				ovValue = subObject.getDouble("ov");
	            				String ovKey = "1D" + i1D_PLUS + "_PLUS";
	            				izvuceniPodaci.put(ovKey, ovValue);
	            			}
	            			break;
	            		case 393:
	            			int i2PD_MINUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				i2PD_MINUS++;
	            				ovValue = subObject.getDouble("ov");
	            				svValue = subObject.getString("sv").split("=")[1];
	            				String ovKey = "2PD" + i2PD_MINUS + "_MINUS";
	            				String svKey = "homeOverUnderSecondHalfGr" + i2PD_MINUS;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            				izvuceniPodaci.put(svKey, svValue);
	            			}
	            			break;
	            		case 394:
	            			int i2PD_PLUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				i2PD_PLUS++;
	            				ovValue = subObject.getDouble("ov");
	            				String ovKey = "2PD" + i2PD_PLUS + "_PLUS";
	            				izvuceniPodaci.put(ovKey, ovValue);
	            			}
	            			break;
	            		case 373:
	            			int i1G_MINUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				i1G_MINUS++;
	            				ovValue = subObject.getDouble("ov");
	            				svValue = subObject.getString("sv").split("=")[1];
	            				String ovKey = "1G" + i1G_MINUS + "_MINUS";
	            				String svKey = "awayOverUnderFirstHalfGr" + i1G_MINUS;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            				izvuceniPodaci.put(svKey, svValue);
	            			}
	            			break;
	            		case 374:
	            			int i1G_PLUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				i1G_PLUS++;
	            				ovValue = subObject.getDouble("ov");
	            				String ovKey = "1G" + i1G_PLUS + "_PLUS";
	            				izvuceniPodaci.put(ovKey, ovValue);
	            			}
	            			break;
	            		case 395:
	            			int i2PG_MINUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				i2PG_MINUS++;
	            				ovValue = subObject.getDouble("ov");
	            				svValue = subObject.getString("sv").split("=")[1];
	            				String ovKey = "2PG" + i2PG_MINUS + "_MINUS";
	            				String svKey = "awayOverUnderSecondHalfGr" + i2PG_MINUS;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            				izvuceniPodaci.put(svKey, svValue);
	            			}
	            			break;
	            		case 396:
	            			int i2PG_PLUS = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				i2PG_PLUS++;
	            				ovValue = subObject.getDouble("ov");
	            				String ovKey = "2PG" + i2PG_PLUS + "_PLUS";
	            				izvuceniPodaci.put(ovKey, ovValue);
	            			}
	            			break;
	            		
	            			
	            		}
	            	}
	            }
	        }
	        izvuceniPodaci.put("home", home);
	        izvuceniPodaci.put("away", away);
	        izvuceniPodaci.put("Liga", leagueName);
	        izvuceniPodaci.put("Start", kickOffTime);
	        izvuceniPodaci.put("Kladionica", "SoccerBet");
	    }
	    return izvuceniPodaci;
	}


}
