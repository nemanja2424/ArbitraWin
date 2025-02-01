package Tenis;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import Fudbal.pozoviAPI;

public class SoccerBetT {
	private static List<String> idUtakmica = new ArrayList<>();

	public static JSONArray praviAPI() {
		String pocetakUrl = "https://www.soccerbet.rs/restapi/offer/sr/match/";
		String krajUrl = "?annex=0&mobileVersion=2.34.3.2&locale=sr";
		List<String> ids = uzmiIdUtakmica();
		JSONArray sveUtakmice = new JSONArray();

		for (String id : ids) {
			String stringUrl = pocetakUrl + id + krajUrl;
			String jsonResponse = pozoviAPI.pozoviAPI(stringUrl);
			String jsonText = "[" + jsonResponse + "]";
			JSONArray utakmice = new JSONArray(jsonText);
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
		String urlString = "https://www.soccerbet.rs/restapi/offer/sr/sport/T/mob?annex=0&desktopVersion=2.34.3.2&locale=sr";
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

	// Nema SS, NS, nista za drugi, treci... set ima par-nepar
	public static JSONObject izvuceniPodaci(JSONObject utakmica) {
		JSONObject izvuceniPodaci = new JSONObject();
		if (utakmica != null) {
			String homeIgrac = utakmica.getString("home");
			String awayIgrac = utakmica.getString("away");
			String leagueName = utakmica.optString("leagueName", "Unknown");
			long kickOffTime = utakmica.getLong("kickOffTime");

			JSONObject betMap = utakmica.optJSONObject("betMap");

			if (betMap != null) {
				int[] kljucevi = { 1, 3, 201046, 201047, 254, 256, 50538, 50539, 604, 605, 251, 253, 50512, 50513,
						50514, 50515, 50538, 50539, 201050, 201051 };
				String[] total = { "total=22.5", "total=21.5", "total=20.5", "total=19.5", "total=18.5", "total=17.5",
						"total=16.5", "total=23.5", "total=24.5" };
				String[] hendikep = { "hcp=-7.5", "hcp=-6.5", "hcp=-5.5", "hcp=-4.5", "hcp=-3.5", "hcp=-2.5",
						"hcp=-1.5", "hcp=-0.5", "hcp=0.5", "hcp=1.5", "hcp=2.5", "hcp=3.5", "hcp=4.5", "hcp=5.5",
						"hcp=6.5", "hcp=7.5" };
				String[] setUkupnoGemova = { "setnr=1,total=6.5", "setnr=1,total=7.5", "setnr=1,total=8.5",
						"setnr=1,total=9.5", "setnr=1,total=10.5", "setnr=2,total=6.5", "setnr=2,total=7.5",
						"setnr=2,total=8.5", "setnr=2,total=9.5", "setnr=2,total=10.5", "setnr=3,total=6.5",
						"setnr=3,total=7.5", "setnr=3,total=8.5", "setnr=3,total=9.5", "setnr=3,total=10.5" };

				for (int key : kljucevi) {
					String keyStr = String.valueOf(key);
					JSONObject kolona = betMap.optJSONObject(keyStr);
					double ovValue = 1;
					String svValue = "1";
					// double ttValue = 1;

					if (kolona != null) {
						switch (key) {
						case 1:
							ovValue = kolona.getJSONObject("NULL").getDouble("ov");
							// ttValue = kolona.getJSONObject("NULL").getDouble("tt");
							izvuceniPodaci.put("Igrac1W", ovValue);
							// izvuceniPodaci.put("ttIgrac1W", ttValue);
							break;
						case 3:
							ovValue = kolona.getJSONObject("NULL").getDouble("ov");
							// ttValue = kolona.getJSONObject("NULL").getDouble("tt");
							izvuceniPodaci.put("Igrac2W", ovValue);
							// izvuceniPodaci.put("ttIgrac2W", ttValue);
							break;
						case 201046:
							ovValue = kolona.getJSONObject("setnr=1").getDouble("ov");
							// ttValue = kolona.getJSONObject("setnr=1").getDouble("tt");
							izvuceniPodaci.put("PS1", ovValue);
							// izvuceniPodaci.put("ttPS1", ttValue);
							break;
						case 201047:
							ovValue = kolona.getJSONObject("setnr=1").getDouble("ov");
							izvuceniPodaci.put("PS2", ovValue);
							break;
						case 50512:
							ovValue = kolona.getJSONObject("NULL").getDouble("ov");
							izvuceniPodaci.put("DS1", ovValue);
							break;
						case 50513:
							ovValue = kolona.getJSONObject("NULL").getDouble("ov");
							izvuceniPodaci.put("DS2", ovValue);
							break;
						case 50514:
							ovValue = kolona.getJSONObject("NULL").getDouble("ov");
							izvuceniPodaci.put("TS1", ovValue);
							break;
						case 50515:
							ovValue = kolona.getJSONObject("NULL").getDouble("ov");
							izvuceniPodaci.put("TS2", ovValue);
							break;
						case 604:
							ovValue = kolona.getJSONObject("NULL").getDouble("ov");
							izvuceniPodaci.put("PARG", ovValue);
							break;
						case 605:
							ovValue = kolona.getJSONObject("NULL").getDouble("ov");
							izvuceniPodaci.put("NEPARG", ovValue);
							break;

							
						case 201050:
							for (String sug : setUkupnoGemova) {
								switch (sug) {
								case "setnr=1,total=6.5":
									if (kolona.has("setnr=1,total=6.5")) {
										ovValue = kolona.getJSONObject("setnr=1,total=6.5").getDouble("ov");
										izvuceniPodaci.put("UGPS_PLUS1", ovValue);
										izvuceniPodaci.put("UGPSGr1", "6.5");
									}
									else {
										izvuceniPodaci.put("UGPS_PLUS1", 1);
										izvuceniPodaci.put("UGPSGr1", "1");
									}
									break;
								case "setnr=1,total=7.5":
									if (kolona.has("setnr=1,total=7.5")) {
										ovValue = kolona.getJSONObject("setnr=1,total=7.5").getDouble("ov");
										izvuceniPodaci.put("UGPS_PLUS2", ovValue);
										izvuceniPodaci.put("UGPSGr2", "7.5");
									}
									else {
										izvuceniPodaci.put("UGPS_PLUS2", 1);
										izvuceniPodaci.put("UGPSGr2", "1");
									}
									break;
								case "setnr=1,total=8.5":
									if (kolona.has("setnr=1,total=8.5")) {
										ovValue = kolona.getJSONObject("setnr=1,total=8.5").getDouble("ov");
										izvuceniPodaci.put("UGPS_PLUS3", ovValue);
										izvuceniPodaci.put("UGPSGr3", "8.5");
									}
									else {
										izvuceniPodaci.put("UGPS_PLUS3", 1);
										izvuceniPodaci.put("UGPSGr3", "1");
									}
									break;
								case "setnr=1,total=9.5":
									if (kolona.has("setnr=1,total=9.5")) {
										ovValue = kolona.getJSONObject("setnr=1,total=9.5").getDouble("ov");
										izvuceniPodaci.put("UGPS_PLUS4", ovValue);
										izvuceniPodaci.put("UGPSGr4", "9.5");
									}
									else {
										izvuceniPodaci.put("UGPS_PLUS4", 1);
										izvuceniPodaci.put("UGPSGr4", "1");
									}
									break;
								case "setnr=1,total=10.5":
									if (kolona.has("setnr=1,total=10.5")) {
										ovValue = kolona.getJSONObject("setnr=1,total=10.5").getDouble("ov");
										izvuceniPodaci.put("UGPS_PLUS5", ovValue);
										izvuceniPodaci.put("UGPSGr5", "10.5");
									}
									else {
										izvuceniPodaci.put("UGPS_PLUS5", 1);
										izvuceniPodaci.put("UGPSGr5", "1");
									}
									break;
								case "setnr=2,total=6.5":
									if (kolona.has("setnr=2,total=6.5")) {
										ovValue = kolona.getJSONObject("setnr=2,total=6.5").getDouble("ov");
										izvuceniPodaci.put("UGDS_PLUS1", ovValue);
										izvuceniPodaci.put("UGDSGr1", "6.5");
									}
									else {
										izvuceniPodaci.put("UGDS_PLUS1", 1);
										izvuceniPodaci.put("UGDSGr1", "1");
									}
									break;
								case "setnr=2,total=7.5":
									if (kolona.has("setnr=2,total=7.5")) {
										ovValue = kolona.getJSONObject("setnr=2,total=7.5").getDouble("ov");
										izvuceniPodaci.put("UGDS_PLUS2", ovValue);
										izvuceniPodaci.put("UGDSGr2", "7.5");
									}
									else {
										izvuceniPodaci.put("UGDS_PLUS2", 1);
										izvuceniPodaci.put("UGDSGr2", "1");
									}
									break;
								case "setnr=2,total=8.5":
									if (kolona.has("setnr=2,total=8.5")) {
										ovValue = kolona.getJSONObject("setnr=2,total=8.5").getDouble("ov");
										izvuceniPodaci.put("UGDS_PLUS3", ovValue);
										izvuceniPodaci.put("UGDSGr3", "8.5");
									}
									else {
										izvuceniPodaci.put("UGDS_PLUS3", 1);
										izvuceniPodaci.put("UGDSGr3", "1");
									}
									break;
								case "setnr=2,total=9.5":
									if (kolona.has("setnr=2,total=9.5")) {
										ovValue = kolona.getJSONObject("setnr=2,total=9.5").getDouble("ov");
										izvuceniPodaci.put("UGDS_PLUS4", ovValue);
										izvuceniPodaci.put("UGDSGr4", "9.5");
									}
									else {
										izvuceniPodaci.put("UGDS_PLUS4", 1);
										izvuceniPodaci.put("UGDSGr4", "1");
									}
									break;
								case "setnr=2,total=10.5":
									if (kolona.has("setnr=2,total=10.5")) {
										ovValue = kolona.getJSONObject("setnr=2,total=10.5").getDouble("ov");
										izvuceniPodaci.put("UGDS_PLUS5", ovValue);
										izvuceniPodaci.put("UGDSGr5", "9.5");
									}
									else {
										izvuceniPodaci.put("UGDS_PLUS5", 1);
										izvuceniPodaci.put("UGDSGr5", "1");
									}
									break;
								case "setnr=3,total=6.5":
									if (kolona.has("setnr=3,total=6.5")) {
										ovValue = kolona.getJSONObject("setnr=3,total=6.5").getDouble("ov");
										izvuceniPodaci.put("UGTS_PLUS1", ovValue);
										izvuceniPodaci.put("UGTSGr1", "6.5");
									}
									else {
										izvuceniPodaci.put("UGTS_PLUS1", 1);
										izvuceniPodaci.put("UGTSGr1", "1");
									}
									break;
								case "setnr=3,total=7.5":
									if (kolona.has("setnr=3,total=7.5")) {
										ovValue = kolona.getJSONObject("setnr=3,total=7.5").getDouble("ov");
										izvuceniPodaci.put("UGTS_PLUS2", ovValue);
										izvuceniPodaci.put("UGTSGr2", "7.5");
									}
									else {
										izvuceniPodaci.put("UGTS_PLUS2", 1);
										izvuceniPodaci.put("UGTSGr2", "1");
									}
									break;
								case "setnr=3,total=8.5":
									if (kolona.has("setnr=3,total=8.5")) {
										ovValue = kolona.getJSONObject("setnr=3,total=8.5").getDouble("ov");
										izvuceniPodaci.put("UGTS_PLUS3", ovValue);
										izvuceniPodaci.put("UGTSGr3", "8.5");
									}
									else {
										izvuceniPodaci.put("UGTS_PLUS3", 1);
										izvuceniPodaci.put("UGTSGr3", "1");
									}
									break;
								case "setnr=3,total=9.5":
									if (kolona.has("setnr=3,total=9.5")) {
										ovValue = kolona.getJSONObject("setnr=3,total=9.5").getDouble("ov");
										izvuceniPodaci.put("UGTS_PLUS4", ovValue);
										izvuceniPodaci.put("UGTSGr4", "9.5");
									}
									else {
										izvuceniPodaci.put("UGTS_PLUS4", 1);
										izvuceniPodaci.put("UGTSGr4", "1");
									}
									break;
								case "setnr=3,total=10.5":
									if (kolona.has("setnr=3,total=10.5")) {
										ovValue = kolona.getJSONObject("setnr=3,total=10.5").getDouble("ov");
										izvuceniPodaci.put("UGTS_PLUS5", ovValue);
										izvuceniPodaci.put("UGTSGr5", "10.5");
									}
									else {
										izvuceniPodaci.put("UGTS_PLUS5", 1);
										izvuceniPodaci.put("UGTSGr5", "1");
									}
									break;
								}
							}
						case 201051:
							for (String sug : setUkupnoGemova) {
								switch (sug) {
								case "setnr=1,total=6.5":
									if (kolona.has("setnr=1,total=6.5")) {
										ovValue = kolona.getJSONObject("setnr=1,total=6.5").getDouble("ov");
										izvuceniPodaci.put("UGPS_MINUS1", ovValue);
									}
									else {
										izvuceniPodaci.put("UGPS_MINUS1", 1);
									}
									break;
								case "setnr=1,total=7.5":
									if (kolona.has("setnr=1,total=7.5")) {
										ovValue = kolona.getJSONObject("setnr=1,total=7.5").getDouble("ov");
										izvuceniPodaci.put("UGPS_MINUS2", ovValue);
									}
									else {
										izvuceniPodaci.put("UGPS_MINUS2", 1);
									}
									break;
								case "setnr=1,total=8.5":
									if (kolona.has("setnr=1,total=8.5")) {
										ovValue = kolona.getJSONObject("setnr=1,total=8.5").getDouble("ov");
										izvuceniPodaci.put("UGPS_MINUS3", ovValue);
									}
									else {
										izvuceniPodaci.put("UGPS_MINUS3", 1);
									}
									break;
								case "setnr=1,total=9.5":
									if (kolona.has("setnr=1,total=9.5")) {
										ovValue = kolona.getJSONObject("setnr=1,total=9.5").getDouble("ov");
										izvuceniPodaci.put("UGPS_MINUS4", ovValue);
									}
									else {
										izvuceniPodaci.put("UGPS_MINUS4", 1);
									}
									break;
								case "setnr=1,total=10.5":
									if (kolona.has("setnr=1,total=10.5")) {
										ovValue = kolona.getJSONObject("setnr=1,total=10.5").getDouble("ov");
										izvuceniPodaci.put("UGPS_MINUS5", ovValue);
									}
									else {
										izvuceniPodaci.put("UGPS_MINUS5", 1);
									}
									break;
								case "setnr=2,total=6.5":
									if (kolona.has("setnr=2,total=6.5")) {
										ovValue = kolona.getJSONObject("setnr=2,total=6.5").getDouble("ov");
										izvuceniPodaci.put("UGDS_MINUS1", ovValue);
									}
									else {
										izvuceniPodaci.put("UGDS_MINUS1", 1);
									}
									break;
								case "setnr=2,total=7.5":
									if (kolona.has("setnr=2,total=7.5")) {
										ovValue = kolona.getJSONObject("setnr=2,total=7.5").getDouble("ov");
										izvuceniPodaci.put("UGDS_MINUS2", ovValue);
									}
									else {
										izvuceniPodaci.put("UGDS_MINUS2", 1);
									}
									break;
								case "setnr=2,total=8.5":
									if (kolona.has("setnr=2,total=8.5")) {
										ovValue = kolona.getJSONObject("setnr=2,total=8.5").getDouble("ov");
										izvuceniPodaci.put("UGDS_MINUS3", ovValue);
									}
									else {
										izvuceniPodaci.put("UGDS_MINUS3", 1);
									}
									break;
								case "setnr=2,total=9.5":
									if (kolona.has("setnr=2,total=9.5")) {
										ovValue = kolona.getJSONObject("setnr=2,total=9.5").getDouble("ov");
										izvuceniPodaci.put("UGDS_MINUS4", ovValue);
									}
									else {
										izvuceniPodaci.put("UGDS_MINUS4", 1);
									}
									break;
								case "setnr=2,total=10.5":
									if (kolona.has("setnr=2,total=10.5")) {
										ovValue = kolona.getJSONObject("setnr=2,total=10.5").getDouble("ov");
										izvuceniPodaci.put("UGDS_MINUS5", ovValue);
									}
									else {
										izvuceniPodaci.put("UGDS_MINUS5", 1);
									}
									break;
								case "setnr=3,total=6.5":
									if (kolona.has("setnr=3,total=6.5")) {
										ovValue = kolona.getJSONObject("setnr=3,total=6.5").getDouble("ov");
										izvuceniPodaci.put("UGTS_MINUS1", ovValue);
									}
									else {
										izvuceniPodaci.put("UGTS_MINUS1", 1);
									}
									break;
								case "setnr=3,total=7.5":
									if (kolona.has("setnr=3,total=7.5")) {
										ovValue = kolona.getJSONObject("setnr=3,total=7.5").getDouble("ov");
										izvuceniPodaci.put("UGTS_MINUS2", ovValue);
									}
									else {
										izvuceniPodaci.put("UGTS_MINUS2", 1);
									}
									break;
								case "setnr=3,total=8.5":
									if (kolona.has("setnr=3,total=8.5")) {
										ovValue = kolona.getJSONObject("setnr=3,total=8.5").getDouble("ov");
										izvuceniPodaci.put("UGTS_MINUS3", ovValue);
									}
									else {
										izvuceniPodaci.put("UGTS_MINUS3", 1);
									}
									break;
								case "setnr=3,total=9.5":
									if (kolona.has("setnr=3,total=9.5")) {
										ovValue = kolona.getJSONObject("setnr=3,total=9.5").getDouble("ov");
										izvuceniPodaci.put("UGTS_MINUS4", ovValue);
									}
									else {
										izvuceniPodaci.put("UGTS_MINUS4", 1);
									}
									break;
								case "setnr=3,total=10.5":
									if (kolona.has("setnr=3,total=10.5")) {
										ovValue = kolona.getJSONObject("setnr=3,total=10.5").getDouble("ov");
										izvuceniPodaci.put("UGTS_MINUS5", ovValue);
									}
									else {
										izvuceniPodaci.put("UGTS_MINUS5", 1);
									}
									break;
								}
							}

							
						case 50538:
							for (String hcp : hendikep) {
								switch (hcp) {
								case "hcp=-2.5":
									if (kolona.has("hcp=2.5")) {
										ovValue = kolona.getJSONObject("hcp=-2.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=-2.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HS1", ovValue);
										izvuceniPodaci.put("HendikepSetGr1", svValue);
									} else {

										izvuceniPodaci.put("HS1", 1);

										izvuceniPodaci.put("HendikepSetGr1", "1");

									}
									break;
								case "hcp=-1.5":
									if (kolona.has("hcp=-1.5")) {
										ovValue = kolona.getJSONObject("hcp=-1.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=-1.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HS1_2", ovValue);
										izvuceniPodaci.put("HendikepSetGr2", svValue);
									} else {

										izvuceniPodaci.put("HS1_2", 1);

										izvuceniPodaci.put("HendikepSetGr2", "1");

									}
									break;
								case "hcp=-0.5":
									if (kolona.has("hcp=-0.5")) {
										ovValue = kolona.getJSONObject("hcp=-0.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=-0.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HS1_3", ovValue);
										izvuceniPodaci.put("HendikepSetGr3", svValue);
									} else {

										izvuceniPodaci.put("HS1_3", 1);

										izvuceniPodaci.put("HendikepSetGr3", "1");

									}
									break;
								case "hcp=0.5":
									if (kolona.has("hcp=0.5")) {
										ovValue = kolona.getJSONObject("hcp=0.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=0.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HS1_4", ovValue);
										izvuceniPodaci.put("HendikepSetGr4", svValue);
									} else {

										izvuceniPodaci.put("HS1_4", 1);

										izvuceniPodaci.put("HendikepSetGr4", "1");

									}
									break;
								case "hcp=1.5":
									if (kolona.has("hcp=1.5")) {
										ovValue = kolona.getJSONObject("hcp=1.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=1.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HS1_5", ovValue);
										izvuceniPodaci.put("HendikepSetGr5", svValue);
									} else {

										izvuceniPodaci.put("HS1_5", 1);

										izvuceniPodaci.put("HendikepSetGr5", "1");

									}
									break;
								case "hcp=2.5":
									if (kolona.has("hcp=2.5")) {
										ovValue = kolona.getJSONObject("hcp=2.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=2.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HS1_6", ovValue);
										izvuceniPodaci.put("HendikepSetGr6", svValue);
									} else {

										izvuceniPodaci.put("HS1_6", 1);
										izvuceniPodaci.put("HendikepSetGr6", "1");
									}
									break;
								}
							}

						case 50539:
							for (String hcp : hendikep) {
								switch (hcp) {
								case "hcp=-2.5":
									if (kolona.has("hcp=2.5")) {
										ovValue = kolona.getJSONObject("hcp=-2.5").optDouble("ov");
										izvuceniPodaci.put("HS2", ovValue);
									} else {
										izvuceniPodaci.put("HS2", 1);
									}
									break;
								case "hcp=-1.5":
									if (kolona.has("hcp=-1.5")) {
										ovValue = kolona.getJSONObject("hcp=-1.5").optDouble("ov");
										izvuceniPodaci.put("HS2_2", ovValue);
									} else {
										izvuceniPodaci.put("HS2_2", 1);
									}
									break;
								case "hcp=-0.5":
									if (kolona.has("hcp=-0.5")) {
										ovValue = kolona.getJSONObject("hcp=-0.5").optDouble("ov");
										izvuceniPodaci.put("HS2_3", ovValue);
									} else {
										izvuceniPodaci.put("HS2_3", 1);
									}
									break;
								case "hcp=0.5":
									if (kolona.has("hcp=0.5")) {
										ovValue = kolona.getJSONObject("hcp=0.5").optDouble("ov");
										izvuceniPodaci.put("HS2_4", ovValue);
									} else {
										izvuceniPodaci.put("HS2_4", 1);
									}
									break;
								case "hcp=1.5":
									if (kolona.has("hcp=1.5")) {
										ovValue = kolona.getJSONObject("hcp=1.5").optDouble("ov");
										izvuceniPodaci.put("HS2_5", ovValue);
									} else {
										izvuceniPodaci.put("HS2_5", 1);
									}
									break;
								case "hcp=2.5":
									if (kolona.has("hcp=2.5")) {
										ovValue = kolona.getJSONObject("hcp=2.5").optDouble("ov");
										izvuceniPodaci.put("HS2_6", ovValue);
									} else {
										izvuceniPodaci.put("HS2_6", 1);
									}
									break;
								}
							}

						case 251:
							for (String hcp : hendikep) {
								switch (hcp) {
								case "hcp=-7.5":
									if (kolona.has("hcp=-7.5")) {
										ovValue = kolona.getJSONObject("hcp=-7.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=-7.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1", ovValue);
										izvuceniPodaci.put("HendikepGemGr1", svValue);
									} else {
										izvuceniPodaci.put("HG1", 1);
										izvuceniPodaci.put("HendikepGemGr1", "1");
									}
									break;
								case "hcp=-6.5":
									if (kolona.has("hcp=-6.5")) {
										ovValue = kolona.getJSONObject("hcp=-6.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=-6.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_2", ovValue);
										izvuceniPodaci.put("HendikepGemGr2", svValue);
									} else {
										izvuceniPodaci.put("HG1_2", 1);
										izvuceniPodaci.put("HendikepGemGr2", "1");
									}
									break;
								case "hcp=-5.5":
									if (kolona.has("hcp=-5.5")) {
										ovValue = kolona.getJSONObject("hcp=-5.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=-5.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_3", ovValue);
										izvuceniPodaci.put("HendikepGemGr3", svValue);
									} else {
										izvuceniPodaci.put("HG1_3", 1);
										izvuceniPodaci.put("HendikepGemGr3", "1");
									}
									break;
								case "hcp=-4.5":
									if (kolona.has("hcp=-4.5")) {
										ovValue = kolona.getJSONObject("hcp=-4.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=-4.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_4", ovValue);
										izvuceniPodaci.put("HendikepGemGr4", svValue);
									} else {
										izvuceniPodaci.put("HG1_4", 1);
										izvuceniPodaci.put("HendikepGemGr4", "1");
									}
									break;
								case "hcp=-3.5":
									if (kolona.has("hcp=-3.5")) {
										ovValue = kolona.getJSONObject("hcp=-3.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=-3.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_5", ovValue);
										izvuceniPodaci.put("HendikepGemGr5", svValue);
									} else {
										izvuceniPodaci.put("HG1_5", 1);
										izvuceniPodaci.put("HendikepGemGr5", "1");
									}
									break;
								case "hcp=-2.5":
									if (kolona.has("hcp=-2.5")) {
										ovValue = kolona.getJSONObject("hcp=-2.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=-2.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_6", ovValue);
										izvuceniPodaci.put("HendikepGemGr6", svValue);
									} else {
										izvuceniPodaci.put("HG1_6", 1);
										izvuceniPodaci.put("HendikepGemGr6", "1");
									}
									break;
								case "hcp=-1.5":
									if (kolona.has("hcp=-1.5")) {
										ovValue = kolona.getJSONObject("hcp=-1.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=-1.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_7", ovValue);
										izvuceniPodaci.put("HendikepGemGr7", svValue);
									} else {
										izvuceniPodaci.put("HG1_7", 1);
										izvuceniPodaci.put("HendikepGemGr7", "1");
									}
									break;
								case "hcp=-0.5":
									if (kolona.has("hcp=-0.5")) {
										ovValue = kolona.getJSONObject("hcp=-0.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=-0.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_8", ovValue);
										izvuceniPodaci.put("HendikepGemGr8", svValue);
									} else {
										izvuceniPodaci.put("HG1_8", 1);
										izvuceniPodaci.put("HendikepGemGr8", "1");
									}
									break;
								case "hcp=0.5":
									if (kolona.has("hcp=0.5")) {
										ovValue = kolona.getJSONObject("hcp=0.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=0.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_9", ovValue);
										izvuceniPodaci.put("HendikepGemGr9", svValue);
									} else {
										izvuceniPodaci.put("HG1_9", 1);
										izvuceniPodaci.put("HendikepGemGr9", "1");
									}
									break;
								case "hcp=1.5":
									if (kolona.has("hcp=1.5")) {
										ovValue = kolona.getJSONObject("hcp=1.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=1.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_10", ovValue);
										izvuceniPodaci.put("HendikepGemGr10", svValue);
									} else {
										izvuceniPodaci.put("HG1_10", 1);
										izvuceniPodaci.put("HendikepGemGr10", "1");
									}
									break;
								case "hcp=2.5":
									if (kolona.has("hcp=2.5")) {
										ovValue = kolona.getJSONObject("hcp=2.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=2.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_11", ovValue);
										izvuceniPodaci.put("HendikepGemGr11", svValue);
									} else {
										izvuceniPodaci.put("HG1_11", 1);
										izvuceniPodaci.put("HendikepGemGr11", "1");
									}
									break;
								case "hcp=3.5":
									if (kolona.has("hcp=3.5")) {
										ovValue = kolona.getJSONObject("hcp=3.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=3.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_12", ovValue);
										izvuceniPodaci.put("HendikepGemGr12", svValue);
									} else {
										izvuceniPodaci.put("HG1_12", 1);
										izvuceniPodaci.put("HendikepGemGr12", "1");
									}
									break;
								case "hcp=4.5":
									if (kolona.has("hcp=4.5")) {
										ovValue = kolona.getJSONObject("hcp=4.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=4.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_13", ovValue);
										izvuceniPodaci.put("HendikepGemGr13", svValue);
									} else {
										izvuceniPodaci.put("HG1_13", 1);
										izvuceniPodaci.put("HendikepGemGr13", "1");
									}
									break;
								case "hcp=5.5":
									if (kolona.has("hcp=5.5")) {
										ovValue = kolona.getJSONObject("hcp=5.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=5.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_14", ovValue);
										izvuceniPodaci.put("HendikepGemGr14", svValue);
									} else {
										izvuceniPodaci.put("HG1_14", 1);
										izvuceniPodaci.put("HendikepGemGr14", "1");
									}
									break;
								case "hcp=6.5":
									if (kolona.has("hcp=6.5")) {
										ovValue = kolona.getJSONObject("hcp=6.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=6.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_15", ovValue);
										izvuceniPodaci.put("HendikepGemGr15", svValue);
									} else {
										izvuceniPodaci.put("HG1_15", 1);
										izvuceniPodaci.put("HendikepGemGr15", "1");
									}
									break;
								case "hcp=7.5":
									if (kolona.has("hcp=7.5")) {
										ovValue = kolona.getJSONObject("hcp=7.5").optDouble("ov");
										svValue = kolona.getJSONObject("hcp=7.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("HG1_16", ovValue);
										izvuceniPodaci.put("HendikepGemGr16", svValue);
									} else {
										izvuceniPodaci.put("HG1_16", 1);
										izvuceniPodaci.put("HendikepGemGr16", "1");
									}
									break;
								}
							}

						case 253:
							for (String hcp : hendikep) {
								switch (hcp) {
								case "hcp=-7.5":
									if (kolona.has("hcp=-7.5")) {
										ovValue = kolona.getJSONObject("hcp=-7.5").optDouble("ov");
										izvuceniPodaci.put("HG2", ovValue);
									} else {
										izvuceniPodaci.put("HG2", 1);
									}
									break;
								case "hcp=-6.5":
									if (kolona.has("hcp=-6.5")) {
										ovValue = kolona.getJSONObject("hcp=-6.5").optDouble("ov");
										izvuceniPodaci.put("HG2_2", ovValue);
									} else {
										izvuceniPodaci.put("HG2_2", 1);
									}
									break;
								case "hcp=-5.5":
									if (kolona.has("hcp=-5.5")) {
										ovValue = kolona.getJSONObject("hcp=-5.5").optDouble("ov");
										izvuceniPodaci.put("HG2_3", ovValue);
									} else {
										izvuceniPodaci.put("HG2_3", 1);
									}
									break;
								case "hcp=-4.5":
									if (kolona.has("hcp=-4.5")) {
										ovValue = kolona.getJSONObject("hcp=-4.5").optDouble("ov");
										izvuceniPodaci.put("HG2_4", ovValue);
									} else {
										izvuceniPodaci.put("HG2_4", 1);
									}
									break;
								case "hcp=-3.5":
									if (kolona.has("hcp=-3.5")) {
										ovValue = kolona.getJSONObject("hcp=-3.5").optDouble("ov");
										izvuceniPodaci.put("HG2_5", ovValue);
									} else {
										izvuceniPodaci.put("HG2_5", 1);
									}
									break;
								case "hcp=-2.5":
									if (kolona.has("hcp=-2.5")) {
										ovValue = kolona.getJSONObject("hcp=-2.5").optDouble("ov");
										izvuceniPodaci.put("HG2_6", ovValue);
									} else {
										izvuceniPodaci.put("HG2_6", 1);
									}
									break;
								case "hcp=-1.5":
									if (kolona.has("hcp=-1.5")) {
										ovValue = kolona.getJSONObject("hcp=-1.5").optDouble("ov");
										izvuceniPodaci.put("HG2_7", ovValue);
									} else {
										izvuceniPodaci.put("HG2_7", 1);
									}
									break;
								case "hcp=-0.5":
									if (kolona.has("hcp=-0.5")) {
										ovValue = kolona.getJSONObject("hcp=-0.5").optDouble("ov");
										izvuceniPodaci.put("HG2_8", ovValue);
									} else {
										izvuceniPodaci.put("HG2_8", 1);
									}
									break;
								case "hcp=0.5":
									if (kolona.has("hcp=0.5")) {
										ovValue = kolona.getJSONObject("hcp=0.5").optDouble("ov");
										izvuceniPodaci.put("HG2_9", ovValue);
									} else {
										izvuceniPodaci.put("HG2_9", 1);
									}
									break;
								case "hcp=1.5":
									if (kolona.has("hcp=1.5")) {
										ovValue = kolona.getJSONObject("hcp=1.5").optDouble("ov");
										izvuceniPodaci.put("HG2_10", ovValue);
									} else {
										izvuceniPodaci.put("HG2_10", 1);
									}
									break;
								case "hcp=2.5":
									if (kolona.has("hcp=2.5")) {
										ovValue = kolona.getJSONObject("hcp=2.5").optDouble("ov");
										izvuceniPodaci.put("HG2_11", ovValue);
									} else {
										izvuceniPodaci.put("HG2_11", 1);
									}
									break;
								case "hcp=3.5":
									if (kolona.has("hcp=3.5")) {
										ovValue = kolona.getJSONObject("hcp=3.5").optDouble("ov");
										izvuceniPodaci.put("HG2_12", ovValue);
									} else {
										izvuceniPodaci.put("HG2_12", 1);
									}
									break;
								case "hcp=4.5":
									if (kolona.has("hcp=4.5")) {
										ovValue = kolona.getJSONObject("hcp=4.5").optDouble("ov");
										izvuceniPodaci.put("HG2_13", ovValue);
									} else {
										izvuceniPodaci.put("HG2_13", 1);
									}
									break;
								case "hcp=5.5":
									if (kolona.has("hcp=5.5")) {
										ovValue = kolona.getJSONObject("hcp=5.5").optDouble("ov");
										izvuceniPodaci.put("HG2_14", ovValue);
									} else {
										izvuceniPodaci.put("HG2_14", 1);
									}
									break;
								case "hcp=6.5":
									if (kolona.has("hcp=6.5")) {
										ovValue = kolona.getJSONObject("hcp=6.5").optDouble("ov");
										izvuceniPodaci.put("HG2_15", ovValue);
									} else {
										izvuceniPodaci.put("HG2_15", 1);
									}
									break;
								case "hcp=7.5":
									if (kolona.has("hcp=7.5")) {
										ovValue = kolona.getJSONObject("hcp=7.5").optDouble("ov");
										izvuceniPodaci.put("HG2_16", ovValue);
									} else {
										izvuceniPodaci.put("HG2_16", 1);
									}
									break;
								}
							}

						case 254:
							for (String tot : total) {
								switch (tot) {
								case "total=22.5":
									if (kolona.has("total=22.5")) {
										ovValue = kolona.getJSONObject("total=22.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=22.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_MINUS", ovValue);
										izvuceniPodaci.put("GranicaGemovi", svValue);
									} else {
										izvuceniPodaci.put("G_MINUS", 1);
										izvuceniPodaci.put("GranicaGemovi", "1");
									}
									break;
								case "total=21.5":
									if (kolona.has("total=21.5")) {
										ovValue = kolona.getJSONObject("total=21.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=21.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_MINUS2", ovValue);
										izvuceniPodaci.put("GranicaGemovi2", svValue);
									} else {
										izvuceniPodaci.put("G_MINUS2", 1);
										izvuceniPodaci.put("GranicaGemovi2", "1");
									}
									break;
								case "total=20.5":
									if (kolona.has("total=20.5")) {
										ovValue = kolona.getJSONObject("total=20.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=20.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_MINUS3", ovValue);
										izvuceniPodaci.put("GranicaGemovi3", svValue);
									} else {
										izvuceniPodaci.put("G_MINUS3", 1);
										izvuceniPodaci.put("GranicaGemovi3", "1");
									}
									break;
								case "total=19.5":
									if (kolona.has("total=19.5")) {
										ovValue = kolona.getJSONObject("total=19.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=19.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_MINUS4", ovValue);
										izvuceniPodaci.put("GranicaGemovi4", svValue);
									} else {
										izvuceniPodaci.put("G_MINUS4", 1);
										izvuceniPodaci.put("GranicaGemovi4", "1");
									}
									break;
								case "total=18.5":
									if (kolona.has("total=18.5")) {
										ovValue = kolona.getJSONObject("total=18.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=18.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_MINUS5", ovValue);
										izvuceniPodaci.put("GranicaGemovi5", svValue);
									} else {
										izvuceniPodaci.put("G_MINUS5", 1);
										izvuceniPodaci.put("GranicaGemovi5", "1");
									}
									break;
								case "total=17.5":
									if (kolona.has("total=17.5")) {
										ovValue = kolona.getJSONObject("total=17.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=17.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_MINUS6", ovValue);
										izvuceniPodaci.put("GranicaGemovi6", svValue);
									} else {
										izvuceniPodaci.put("G_MINUS6", 1);
										izvuceniPodaci.put("GranicaGemovi6", "1");
									}
									break;
								case "total=16.5":
									if (kolona.has("total=16.5")) {
										ovValue = kolona.getJSONObject("total=16.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=16.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_MINUS7", ovValue);
										izvuceniPodaci.put("GranicaGemovi7", svValue);
									} else {
										izvuceniPodaci.put("G_MINUS7", 1);
										izvuceniPodaci.put("GranicaGemovi7", "1");
									}
									break;
								case "total=23.5":
									if (kolona.has("total=23.5")) {
										ovValue = kolona.getJSONObject("total=23.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=23.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_MINUS8", ovValue);
										izvuceniPodaci.put("GranicaGemovi8", svValue);
									} else {
										izvuceniPodaci.put("G_MINUS8", 1);
										izvuceniPodaci.put("GranicaGemovi8", "1");
									}
									break;
								case "total=24.5":
									if (kolona.has("total=24.5")) {
										ovValue = kolona.getJSONObject("total=24.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=24.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_MINUS9", ovValue);
										izvuceniPodaci.put("GranicaGemovi9", svValue);
									} else {
										izvuceniPodaci.put("G_MINUS9", 1);
										izvuceniPodaci.put("GranicaGemovi9", "1");
									}
									break;
								}
							}
						case 256:
							for (String tot : total) {
								switch (tot) {
								case "total=22.5":
									if (kolona.has("total=22.5")) {
										ovValue = kolona.getJSONObject("total=22.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=22.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_PLUS", ovValue);
									} else {
										izvuceniPodaci.put("G_PLUS", 1);
									}
									break;
								case "total=21.5":
									if (kolona.has("total=21.5")) {
										ovValue = kolona.getJSONObject("total=21.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=21.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_PLUS2", ovValue);
									} else {
										izvuceniPodaci.put("G_PLUS2", 1);
									}
									break;
								case "total=20.5":
									if (kolona.has("total=20.5")) {
										ovValue = kolona.getJSONObject("total=20.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=20.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_PLUS3", ovValue);
									} else {
										izvuceniPodaci.put("G_PLUS3", 1);
									}
									break;
								case "total=19.5":
									if (kolona.has("total=19.5")) {
										ovValue = kolona.getJSONObject("total=19.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=19.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_PLUS4", ovValue);
									} else {
										izvuceniPodaci.put("G_PLUS4", 1);
									}
									break;
								case "total=18.5":
									if (kolona.has("total=18.5")) {
										ovValue = kolona.getJSONObject("total=18.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=18.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_PLUS5", ovValue);
									} else {
										izvuceniPodaci.put("G_PLUS5", 1);
									}
									break;
								case "total=17.5":
									if (kolona.has("total=17.5")) {
										ovValue = kolona.getJSONObject("total=17.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=17.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_PLUS6", ovValue);
									} else {
										izvuceniPodaci.put("G_PLUS6", 1);
									}
									break;
								case "total=16.5":
									if (kolona.has("total=16.5")) {
										ovValue = kolona.getJSONObject("total=16.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=16.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_PLUS7", ovValue);
									} else {
										izvuceniPodaci.put("G_PLUS7", 1);
									}
									break;
								case "total=23.5":
									if (kolona.has("total=23.5")) {
										ovValue = kolona.getJSONObject("total=23.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=23.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_PLUS8", ovValue);
									} else {
										izvuceniPodaci.put("G_PLUS8", 1);
									}
									break;
								case "total=24.5":
									if (kolona.has("total=24.5")) {
										ovValue = kolona.getJSONObject("total=24.5").optDouble("ov");
										svValue = kolona.getJSONObject("total=24.5").getString("sv").split("=")[1];
										izvuceniPodaci.put("G_PLUS9", ovValue);
									} else {
										izvuceniPodaci.put("G_PLUS9", 1);
									}
									break;
								}
							}
						}
					}
				}
			}

			izvuceniPodaci.put("home", homeIgrac);
			izvuceniPodaci.put("away", awayIgrac);
			izvuceniPodaci.put("Liga", leagueName);
			izvuceniPodaci.put("Start", kickOffTime);
			izvuceniPodaci.put("Kladionica", "SoccerBet");
		}
		return izvuceniPodaci;
	}
}
