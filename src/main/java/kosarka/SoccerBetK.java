package kosarka;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import Fudbal.pozoviAPI;

public class SoccerBetK {
	private static List<String> idUtakmica = new ArrayList<>();

	public static JSONArray praviAPI() {
		String pocetakUrl = "https://www.soccerbet.rs/restapi/offer/sr/match/";
		String krajUrl = "?annex=0&mobileVersion=2.31.7&locale=sr";
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
		String urlString = "https://www.soccerbet.rs/restapi/offer/sr/sport/B/mob?annex=0&mobileVersion=2.31.7&locale=sr";
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
	            int[] kljucevi = {50291, 50293, 7, 9, 397, 399, 608, 610, 611, 612, 613, 614, 50025, 50026, 50173, 50174, 50175, 50176, 50176, 50178, 50022, 50024,
	                    50179, 50181, 50182, 50184, 50185, 50187, 50459, 50485, 233, 234, 50200, 50201,/**/ 50430, 50431, 224, 226, 50136, 50138, 602, 603, 50139, 50140, 50141, 50142, 50142,
	                    50143, 50144, 50444, 50446, 50448, 50450, 50452, 50454, 50456, 50445, 50447, 50449, 50451, 50453, 50455, 50457, 50980, 50979, 50986, 50988, 599, 598,
	                    50165, 50166, 50167, 50168, 50169, 50170, 50446, 50466, 50467, 50478, 50479, 50982, 50983, 50989, 50990, 50027, 50028, 50984, 50985, 50991, 50992, 50029, 50030};
	            
	          
	            
	            for (int kljuc : kljucevi) {
	            	String keyStr = String.valueOf(kljuc);
	            	JSONObject kolona = betMap.optJSONObject(keyStr);
	            	double ovValue =1;
	            	String svValue = "1";
	            	
	            	if (kolona != null) {
	            		switch (kljuc) {
	            		case 50291:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("FTOT1", ovValue);
	            			break;
	            		case 50293:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("FTOT2", ovValue);
	            			break;
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
	            			izvuceniPodaci.put("P1X", ovValue);
	            			break;	
	            		case 399:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("PX2", ovValue);
	            			break;	
	            		case 608:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("2P1X", ovValue);
	            			break;	
	            		case 610:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("2PX2", ovValue);
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
	            		case 50025:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q1W1", ovValue);
	            			break;	
	            		case 50026:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q1W2", ovValue);
	            			break;	
	            		case 50173:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q2W1", ovValue);
	            			break;	
	            		case 50174:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q2W2", ovValue);
	            			break;	
	            		case 50175:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q3W1", ovValue);
	            			break;	
	            		case 50176:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q3W2", ovValue);
	            			break;	
	            		case 50177:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q4W1", ovValue);
	            			break;	
	            		case 50178:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q4W2", ovValue);
	            			break;	
	            		case 50022:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q11X", ovValue);
	            			break;	
	            		case 50024:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q1X2", ovValue);
	            			break;	
	            		case 50179:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q21X", ovValue);
	            			break;	
	            		case 50181:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q2X2", ovValue);
	            			break;	
	            		case 50182:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q31X", ovValue);
	            			break;	
	            		case 50184:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q3X2", ovValue);
	            			break;	
	            		case 50185:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q41X", ovValue);
	            			break;	
	            		case 50187:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("Q4X2", ovValue);
	            			break;	
	            		case 50459:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("PAR_OT", ovValue);
	            			break;	
	            		case 50485:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("NEPAR_OT", ovValue);
	            			break;	
	            		case 233:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("PPR", ovValue);
	            			break;	
	            		case 234:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("PNP", ovValue);
	            			break;	
	            		case 50200:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("2PPAR", ovValue);
	            			break;	
	            		case 50201:
	            			ovValue =  kolona.getJSONObject("NULL").getDouble("ov");
	            			izvuceniPodaci.put("2PNEPAR", ovValue);
	            			break;
	            		case 50430:
	            			int iHOT1 = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iHOT1++;
	            				ovValue = subObject.getDouble("ov");
	            				svValue = subObject.getString("sv").split("=")[1];
	            				String ovKey = "HOT" + iHOT1 + "1";
	            				String svKey = "hendikepGr" + iHOT1;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            				izvuceniPodaci.put(svKey, svValue);
	            			}
	            			break;
	            		case 50431:
	            			int iHOT2 = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iHOT2++;
	            				ovValue = subObject.getDouble("ov");
	            				String ovKey = "HOT" + iHOT2 + "2";
	            				izvuceniPodaci.put(ovKey, ovValue);
	            			}
	            			break;
	            		case 224:
	            			int iPH1 = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iPH1++;
	            				ovValue = subObject.getDouble("ov");
	            				svValue = subObject.getString("sv").split("=")[1];
	            				String ovKey = "PH" + iPH1 + "1";
	            				String svKey = "hendikepPrvoPoluvremeGr" + iPH1;
	            				izvuceniPodaci.put(ovKey, ovValue);
	            				izvuceniPodaci.put(svKey, svValue);
	            			}
	            			break;
	            		case 226:
	            			int iPH2 = 0;
	            			for (String key : kolona.keySet()) {
	            				JSONObject subObject = kolona.getJSONObject(key);
	            				iPH2++;
	            				ovValue = subObject.getDouble("ov");
	            				String ovKey = "PH" + iPH2 + "2";
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
		            				String ovKey = "2PH" + i2PH1 + "1";
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
		            				String ovKey = "2PH" + i2PH2 + "2";
		            				izvuceniPodaci.put(ovKey, ovValue);
		            			}
		            			break;
	            			case 602:
		            			int iQ1H1 = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ1H1++;
		            				ovValue = subObject.getDouble("ov");
		            				svValue = subObject.getString("sv").split("=")[1];
		            				String ovKey = "Q1H" + iQ1H1 + "1";
		            				String svKey = "hendikepPrviPeriodGr" + iQ1H1;
		            				izvuceniPodaci.put(ovKey, ovValue);
		            				izvuceniPodaci.put(svKey, svValue);
		            			}
		            			break;
	            			case 603:
		            			int iQ1H2 = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ1H2++;
		            				ovValue = subObject.getDouble("ov");
		            				String ovKey = "Q1H" + iQ1H2 + "2";
		            				izvuceniPodaci.put(ovKey, ovValue);
		            			}
		            			break;
		            		case 50139:
		            			int iQ2H1 = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ2H1++;
		            				ovValue = subObject.getDouble("ov");
		            				svValue = subObject.getString("sv").split("=")[1];
		            				String ovKey = "Q2H" + iQ2H1 + "1";
		            				String svKey = "hendikepDrugPeriodGr" + iQ2H1;
		            				izvuceniPodaci.put(ovKey, ovValue);
		            				izvuceniPodaci.put(svKey, svValue);
		            			}
		            			break;
	            			case 50140:
		            			int iQ2H2 = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ2H2++;
		            				ovValue = subObject.getDouble("ov");
		            				String ovKey = "Q2H" + iQ2H2 + "2";
		            				izvuceniPodaci.put(ovKey, ovValue);
		            			}
		            			break;
	            			case 50141:
		            			int iQ3H1 = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ3H1++;
		            				ovValue = subObject.getDouble("ov");
		            				svValue = subObject.getString("sv").split("=")[1];
		            				String ovKey = "Q2H" + iQ3H1 + "1";
		            				String svKey = "hendikepTreciPeriodGr" + iQ3H1;
		            				izvuceniPodaci.put(ovKey, ovValue);
		            				izvuceniPodaci.put(svKey, svValue);
		            			}
		            			break;
	            			case 50142:
		            			int iQ3H2 = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ3H2++;
		            				ovValue = subObject.getDouble("ov");
		            				String ovKey = "Q3H" + iQ3H2 + "2";
		            				izvuceniPodaci.put(ovKey, ovValue);
		            			}
		            			break;
	            			case 50143:
		            			int iQ4H1 = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ4H1++;
		            				ovValue = subObject.getDouble("ov");
		            				svValue = subObject.getString("sv").split("=")[1];
		            				String ovKey = "Q4H" + iQ4H1 + "1";
		            				String svKey = "hendikepCetvrtiPeriodGr" + iQ4H1;
		            				izvuceniPodaci.put(ovKey, ovValue);
		            				izvuceniPodaci.put(svKey, svValue);
		            			}
		            			break;
	            			case 50144:
		            			int iQ4H2 = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ4H2++;
		            				ovValue = subObject.getDouble("ov");
		            				String ovKey = "Q4H" + iQ4H2 + "2";
		            				izvuceniPodaci.put(ovKey, ovValue);
		            			}
		            			break;
	            			case 50444:
		            			int iOT_MINUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iOT_MINUS++;
		            				ovValue = subObject.getDouble("ov");
		            				svValue = subObject.getString("sv").split("=")[1];
		            				String ovKey = "OT" + iOT_MINUS + "_MINUS";
		            				String svKey = "overUnderOvertime" + iOT_MINUS;
		            				izvuceniPodaci.put(ovKey, ovValue);
		            				izvuceniPodaci.put(svKey, svValue);
		            			}
		            			break;
	            			case 50445:
		            			int iOT_PLUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iOT_PLUS++;
		            				ovValue = subObject.getDouble("ov");
		            				String ovKey = "OT" + iOT_PLUS + "_PLUS";
		            				izvuceniPodaci.put(ovKey, ovValue);
		            			}
		            			break;
	            			case 50980:
		            			int i1P_MINUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				i1P_MINUS++;
		            				ovValue = subObject.getDouble("ov");
		            				svValue = subObject.getString("sv").split("=")[1];
		            				String ovKey = "1P" + i1P_MINUS + "_MINUS";
		            				String svKey = "overUnderPrvoPoluvreme" + i1P_MINUS;
		            				izvuceniPodaci.put(ovKey, ovValue);
		            				izvuceniPodaci.put(svKey, svValue);
		            			}
		            			break;
	            			case 50979:
		            			int i1P_PLUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				i1P_PLUS++;
		            				ovValue = subObject.getDouble("ov");
		            				String ovKey = "1P" + i1P_PLUS + "_PLUS";
		            				izvuceniPodaci.put(ovKey, ovValue);
		            			}
	            			case 50986:
		            			int i2P_MINUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				i2P_MINUS++;
		            				ovValue = subObject.getDouble("ov");
		            				svValue = subObject.getString("sv").split("=")[1];
		            				String ovKey = "2P" + i2P_MINUS + "_MINUS";
		            				String svKey = "overUnderDrugoPoluvreme" + i2P_MINUS;
		            				izvuceniPodaci.put(ovKey, ovValue);
		            				izvuceniPodaci.put(svKey, svValue);
		            			}
		            			break;
	            			case 50988:
		            			int i2P_PLUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				i2P_PLUS++;
		            				ovValue = subObject.getDouble("ov");
		            				String ovKey = "2P" + i2P_PLUS + "_PLUS";
		            				izvuceniPodaci.put(ovKey, ovValue);
		            			}
	            			case 599:
		            			int iQ1_MINUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ1_MINUS++;
		            				ovValue = subObject.getDouble("ov");
		            				svValue = subObject.getString("sv").split("=")[1];
		            				String ovKey = "Q1" + iQ1_MINUS + "_MINUS";
		            				String svKey = "overUnderPrviPeriod" + iQ1_MINUS;
		            				izvuceniPodaci.put(ovKey, ovValue);
		            				izvuceniPodaci.put(svKey, svValue);
		            			}
		            			break;
	            			case 598:
		            			int iQ1_PLUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ1_PLUS++;
		            				ovValue = subObject.getDouble("ov");
		            				String ovKey = "Q1" + iQ1_PLUS + "_PLUS";
		            				izvuceniPodaci.put(ovKey, ovValue);
		            			}
		            			break;
	            			case 50165:
		            			int iQ2_MINUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ2_MINUS++;
		            				ovValue = subObject.getDouble("ov");
		            				svValue = subObject.getString("sv").split("=")[1];
		            				String ovKey = "Q2" + iQ2_MINUS + "_MINUS";
		            				String svKey = "overUnderDrugiPeriod" + iQ2_MINUS;
		            				izvuceniPodaci.put(ovKey, ovValue);
		            				izvuceniPodaci.put(svKey, svValue);
		            			}
		            			break;
	            			case 50166:
		            			int iQ2_PLUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ2_PLUS++;
		            				ovValue = subObject.getDouble("ov");
		            				String ovKey = "Q2" + iQ2_PLUS + "_PLUS";
		            				izvuceniPodaci.put(ovKey, ovValue);
		            			}
		            			break;
	            			case 50167:
		            			int iQ3_MINUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ3_MINUS++;
		            				ovValue = subObject.getDouble("ov");
		            				svValue = subObject.getString("sv").split("=")[1];
		            				String ovKey = "Q3" + iQ3_MINUS + "_MINUS";
		            				String svKey = "overUnderTreciPeriod" + iQ3_MINUS;
		            				izvuceniPodaci.put(ovKey, ovValue);
		            				izvuceniPodaci.put(svKey, svValue);
		            			}
		            			break;
	            			case 50168:
		            			int iQ3_PLUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ3_PLUS++;
		            				ovValue = subObject.getDouble("ov");
		            				String ovKey = "Q3" + iQ3_PLUS + "_PLUS";
		            				izvuceniPodaci.put(ovKey, ovValue);
		            			}
		            			break;
	            			case 50169:
		            			int iQ4_MINUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ4_MINUS++;
		            				ovValue = subObject.getDouble("ov");
		            				svValue = subObject.getString("sv").split("=")[1];
		            				String ovKey = "Q4" + iQ4_MINUS + "_MINUS";
		            				String svKey = "overUnderCetvrtiPeriod" + iQ4_MINUS;
		            				izvuceniPodaci.put(ovKey, ovValue);
		            				izvuceniPodaci.put(svKey, svValue);
		            			}
		            			break;
	            			case 50170:
		            			int iQ4_PLUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iQ4_PLUS++;
		            				ovValue = subObject.getDouble("ov");
		            				String ovKey = "Q4" + iQ4_PLUS + "_PLUS";
		            				izvuceniPodaci.put(ovKey, ovValue);
		            			}
		            			break;
	            			case 50466:
		            			int iOTH_MINUS = 0;
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iOTH_MINUS++;
		            				ovValue = subObject.getDouble("ov");
		            				svValue = subObject.getString("sv").split("=")[1];
		            				String ovKey = "OTH" + iOTH_MINUS + "_MINUS";
		            				String svKey = "homeOverUnderOvertime" + iOTH_MINUS;
		            				izvuceniPodaci.put(ovKey, ovValue);
		            				izvuceniPodaci.put(svKey, svValue);
		            			}
		            			break;
	            			case 50467:
		            			int iOTH_PLUS = 0;//
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iOTH_PLUS++;//
		            				ovValue = subObject.getDouble("ov");
		            				String ovKey = "OTH" + iOTH_PLUS + "_PLUS";////
		            				izvuceniPodaci.put(ovKey, ovValue);
		            			}
		            			break;
	            			case 50478:
		            			int iOTA_MINUS = 0;//
		            			for (String key : kolona.keySet()) {
		            				JSONObject subObject = kolona.getJSONObject(key);
		            				iOTA_MINUS++;//
		            				ovValue = subObject.getDouble("ov");
		            				svValue = subObject.getString("sv").split("=")[1];
		            				String ovKey = "OTH" + iOTA_MINUS + "_MINUS";////
		            				String svKey = "awayOverUnderOvertime" + iOTA_MINUS;////
		            				izvuceniPodaci.put(ovKey, ovValue);
		            				izvuceniPodaci.put(svKey, svValue);
		            			}
		            			break;
		            			case 50479:
			            			int iOTA_PLUS = 0;//
			            			for (String key : kolona.keySet()) {
			            				JSONObject subObject = kolona.getJSONObject(key);
			            				iOTA_PLUS++;//
			            				ovValue = subObject.getDouble("ov");
			            				String ovKey = "OTA" + iOTA_PLUS + "_PLUS";////
			            				izvuceniPodaci.put(ovKey, ovValue);
			            			}
			            			break;
		            			case 50982:
			            			int i1D_MINUS = 0;//
			            			for (String key : kolona.keySet()) {
			            				JSONObject subObject = kolona.getJSONObject(key);
			            				i1D_MINUS++;//
			            				ovValue = subObject.getDouble("ov");
			            				svValue = subObject.getString("sv").split("=")[1];
			            				String ovKey = "1D" + i1D_MINUS + "_MINUS";////
			            				String svKey = "homeOverUnderFirstHalf" + i1D_MINUS;////
			            				izvuceniPodaci.put(ovKey, ovValue);
			            				izvuceniPodaci.put(svKey, svValue);
			            			}
			            			break;
		            			case 50983:
			            			int i1D_PLUS = 0;//
			            			for (String key : kolona.keySet()) {
			            				JSONObject subObject = kolona.getJSONObject(key);
			            				i1D_PLUS++;//
			            				ovValue = subObject.getDouble("ov");
			            				String ovKey = "1D" + i1D_PLUS + "_PLUS";////
			            				izvuceniPodaci.put(ovKey, ovValue);
			            			}
			            			break;
		            			case 50989:
			            			int i2D_MINUS = 0;//
			            			for (String key : kolona.keySet()) {
			            				JSONObject subObject = kolona.getJSONObject(key);
			            				i2D_MINUS++;//
			            				ovValue = subObject.getDouble("ov");
			            				svValue = subObject.getString("sv").split("=")[1];
			            				String ovKey = "2D" + i2D_MINUS + "_MINUS";////
			            				String svKey = "homeOverUnderSecondHalf" + i2D_MINUS;////
			            				izvuceniPodaci.put(ovKey, ovValue);
			            				izvuceniPodaci.put(svKey, svValue);
			            			}
			            			break;
		            			case 50990:
			            			int i2D_PLUS = 0;//
			            			for (String key : kolona.keySet()) {
			            				JSONObject subObject = kolona.getJSONObject(key);
			            				i2D_PLUS++;//
			            				ovValue = subObject.getDouble("ov");
			            				String ovKey = "2D" + i2D_PLUS + "_PLUS";////
			            				izvuceniPodaci.put(ovKey, ovValue);
			            			}
			            			break;
		            			case 50027:
			            			int iQ1D_MINUS = 0;//
			            			for (String key : kolona.keySet()) {
			            				JSONObject subObject = kolona.getJSONObject(key);
			            				iQ1D_MINUS++;//
			            				ovValue = subObject.getDouble("ov");
			            				svValue = subObject.getString("sv").split("=")[1];
			            				String ovKey = "Q1D" + iQ1D_MINUS + "_MINUS";////
			            				String svKey = "homeOverUnderFirstPeriod" + iQ1D_MINUS;////
			            				izvuceniPodaci.put(ovKey, ovValue);
			            				izvuceniPodaci.put(svKey, svValue);
			            			}
			            			break;
		            			case 50028:
			            			int iQ1D_PLUS = 0;//
			            			for (String key : kolona.keySet()) {
			            				JSONObject subObject = kolona.getJSONObject(key);
			            				iQ1D_PLUS++;//
			            				ovValue = subObject.getDouble("ov");
			            				String ovKey = "Q1D" + iQ1D_PLUS + "_PLUS";////
			            				izvuceniPodaci.put(ovKey, ovValue);
			            			}
			            			break;
		            			case 50984:
			            			int i1G_MINUS = 0;//
			            			for (String key : kolona.keySet()) {
			            				JSONObject subObject = kolona.getJSONObject(key);
			            				i1G_MINUS++;//
			            				ovValue = subObject.getDouble("ov");
			            				svValue = subObject.getString("sv").split("=")[1];
			            				String ovKey = "1G" + i1G_MINUS + "_MINUS";////
			            				String svKey = "awayOverUnderFirstHalf" + i1G_MINUS;////
			            				izvuceniPodaci.put(ovKey, ovValue);
			            				izvuceniPodaci.put(svKey, svValue);
			            			}
			            			break;
		            			case 50985:
			            			int i1G_PLUS = 0;//
			            			for (String key : kolona.keySet()) {
			            				JSONObject subObject = kolona.getJSONObject(key);
			            				i1G_PLUS++;//
			            				ovValue = subObject.getDouble("ov");
			            				String ovKey = "1G" + i1G_PLUS + "_PLUS";////
			            				izvuceniPodaci.put(ovKey, ovValue);
			            			}
			            			break;
		            			case 50991:
			            			int i2G_MINUS = 0;//
			            			for (String key : kolona.keySet()) {
			            				JSONObject subObject = kolona.getJSONObject(key);
			            				i2G_MINUS++;//
			            				ovValue = subObject.getDouble("ov");
			            				svValue = subObject.getString("sv").split("=")[1];
			            				String ovKey = "2G" + i2G_MINUS + "_MINUS";////
			            				String svKey = "awayOverUnderSecondHalf" + i2G_MINUS;////
			            				izvuceniPodaci.put(ovKey, ovValue);
			            				izvuceniPodaci.put(svKey, svValue);
			            			}
			            			break;
		            			case 50992:
			            			int i2G_PLUS = 0;//
			            			for (String key : kolona.keySet()) {
			            				JSONObject subObject = kolona.getJSONObject(key);
			            				i2G_PLUS++;//
			            				ovValue = subObject.getDouble("ov");
			            				String ovKey = "2G" + i2G_PLUS + "_PLUS";////
			            				izvuceniPodaci.put(ovKey, ovValue);
			            			}
			            			break;
		            			case 50029:
			            			int iQ1G_MINUS = 0;//
			            			for (String key : kolona.keySet()) {
			            				JSONObject subObject = kolona.getJSONObject(key);
			            				iQ1G_MINUS++;//
			            				ovValue = subObject.getDouble("ov");
			            				svValue = subObject.getString("sv").split("=")[1];
			            				String ovKey = "Q1G" + iQ1G_MINUS + "_MINUS";////
			            				String svKey = "awayOverUnderFirstPeriod" + iQ1G_MINUS;////
			            				izvuceniPodaci.put(ovKey, ovValue);
			            				izvuceniPodaci.put(svKey, svValue);
			            			}
			            			break;
		            			case 50030:
			            			int iQ1G_PLUS = 0;//
			            			for (String key : kolona.keySet()) {
			            				JSONObject subObject = kolona.getJSONObject(key);
			            				iQ1G_PLUS++;//
			            				ovValue = subObject.getDouble("ov");
			            				String ovKey = "Q1G" + iQ1G_PLUS + "_PLUS";////
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
