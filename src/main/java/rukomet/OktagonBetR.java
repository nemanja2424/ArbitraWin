package rukomet;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import Fudbal.pozoviAPI;

public class OktagonBetR {
	private static List<String> idUtakmica = new ArrayList<>();
	private static final String POCETAK_URL_MATCH = "https://www.oktagonbet.com/restapi/offer/sr/match/";
	private static final String KRAJ_URL_MATCH = "?annex=1&mobileVersion=2.33&locale=sr";

	
/////NE RADI-DODAJ SLANJE API REQ. ZA SVAKOU UTAKMICU
	public static JSONArray praviAPI() {
		List<String> savedMatchIds = uzmiIdUtakmica();

		JSONArray sveUtakmice = new JSONArray();

		for (String id : savedMatchIds) {
			String utakmicaUrl = POCETAK_URL_MATCH + id + KRAJ_URL_MATCH;
			String odgovor = pozoviAPI.pozoviAPI(utakmicaUrl);

			if (odgovor != null) {
				String jsonText = "[" + odgovor + "]";
				JSONArray utakmiceLige = new JSONArray(jsonText);
				// System.out.println(jsonText);

				// Izvučeni podaci se obrađuju pre dodavanja informacija o kladionici
				String processedMatches = izvuceniPodaci(utakmiceLige.toString());
				JSONArray processedMatchesArray = new JSONArray(processedMatches);
				// System.out.println(utakmiceLige);

				// Add each JSONObject from processedMatchesArray to sveUtakmice
				for (int i = 0; i < processedMatchesArray.length(); i++) {
					sveUtakmice.put(processedMatchesArray.getJSONObject(i));
				}
				// System.out.println(processedMatches);
				// System.out.println(utakmicaUrl);
			}
		}

		//System.out.println(sveUtakmice.toString());
		return sveUtakmice;
	}

	public static List<String> uzmiIdUtakmica() {
		String urlString = "https://www.oktagonbet.com/restapi/offer/sr/sport/HB/mob?annex=1&mobileVersion=2.33&locale=sr";
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
	

	public static String izvuceniPodaci(String jsonString) {
        if (jsonString != null) {
            JSONArray matchesArray = new JSONArray(jsonString);
            JSONArray matchesDataArray = new JSONArray();

            for (int i = 0; i < matchesArray.length(); i++) {
                JSONObject matchObject = matchesArray.getJSONObject(i);

                if (matchObject.has("home") && matchObject.has("away") && matchObject.has("kickOffTime")
                        && matchObject.has("odds")) {
                	JSONObject matchDataObject = new JSONObject();

					String homeTeam = matchObject.getString("home");
					String awayTeam = matchObject.getString("away");
					long kickOffTime = matchObject.getLong("kickOffTime");
					String leagueName = matchObject.getString("leagueName");

					JSONObject oddsObject = matchObject.getJSONObject("odds");
					double JEDAN_X = oddsObject.optDouble("7", 1);
					double X2 = oddsObject.optDouble("9", 1);
					double JEDAN_P1X = oddsObject.optDouble("397", 1);
					double JEDAN_PX2 = oddsObject.optDouble("399", 1);
					double DVA_P1X = oddsObject.optDouble("608", 1);
					double DVA_PX2 = oddsObject.optDouble("610", 1);
					double W1 = oddsObject.optDouble("264", 1);
					double W2 = oddsObject.optDouble("265", 1);
					double JEDAN_PW1 = oddsObject.optDouble("611", 1);
					double JEDAN_PW2 = oddsObject.optDouble("612", 1);
					double DVA_PW1 = oddsObject.optDouble("613", 1);
					double DVA_PW2 = oddsObject.optDouble("614", 1);
					double PAR = oddsObject.optDouble("231", 1);
					double NEPAR = oddsObject.optDouble("232", 1);

					double UG1_MINUS = oddsObject.optDouble("228", 1);
					double UG2_MINUS = oddsObject.optDouble("427", 1);
					double UG3_MINUS = oddsObject.optDouble("430", 1);
					double UG4_MINUS = oddsObject.optDouble("50076", 1);
					double UG5_MINUS = oddsObject.optDouble("50079", 1);
					double UG1_PLUS = oddsObject.optDouble("227", 1);
					double UG2_PLUS = oddsObject.optDouble("429", 1);
					double UG3_PLUS = oddsObject.optDouble("432", 1);
					double UG4_PLUS = oddsObject.optDouble("50078", 1);
					double UG5_PLUS = oddsObject.optDouble("50081", 1);//
					double UGP_MINUS = oddsObject.optDouble("230", 1);
					double UGP_PLUS = oddsObject.optDouble("229", 1);
					double UG2P_MINUS = oddsObject.optDouble("390", 1);
					double UG2P_PLUS = oddsObject.optDouble("392", 1);
					double D_MINUS = oddsObject.optDouble("355", 1);
					double JEDAN_D_MINUS = oddsObject.optDouble("371", 1);
					double DVA_PD_MINUS = oddsObject.optDouble("393", 1);
					double D_PLUS = oddsObject.optDouble("356", 1);
					double JEDAN_D_PLUS = oddsObject.optDouble("372", 1);
					double DVA_PD_PLUS = oddsObject.optDouble("394", 1);
					double G_MINUS = oddsObject.optDouble("357", 1);
					double JEDAN_G_MINUS = oddsObject.optDouble("373", 1);
					double DVA_PG_MINUS = oddsObject.optDouble("395", 1);
					double G_PLUS = oddsObject.optDouble("358", 1);
					double JEDAN_G_PLUS = oddsObject.optDouble("374", 1);
					double DVA_PG_PLUS = oddsObject.optDouble("396", 1);
					double H11 = oddsObject.optDouble("201", 1);
					double H12 = oddsObject.optDouble("203", 1);
					double H21 = oddsObject.optDouble("421", 1);
					double H22 = oddsObject.optDouble("423", 1);
					double H31 = oddsObject.optDouble("424", 1);
					double H32 = oddsObject.optDouble("426", 1);
					double DVA_PH1 = oddsObject.optDouble("50136", 1);
					double DVA_PH2 = oddsObject.optDouble("50138", 1);
					double BJTDM = oddsObject.optDouble("53096", 1);
					double OTDV = oddsObject.optDouble("53095", 1);

					if (matchObject.has("params")) {
						JSONObject paramsObject = matchObject.getJSONObject("params");
						String overUnder = paramsObject.optString("overUnder", "1");
						String overUnder2 = paramsObject.optString("overUnder2", "1");
						String overUnder3 = paramsObject.optString("overUnder3", "1");
						String overUnder4 = paramsObject.optString("overUnder4", "1");
						String overUnder5 = paramsObject.optString("overUnder5", "1");
						String bothTeamScore = paramsObject.optString("bothTeamScore", "1");
						String overUnderP = paramsObject.optString("overUnderP", "1");
						String overUnderSecondHalf = paramsObject.optString("overUnderSecondHalf", "1");
						String homeOverUnder = paramsObject.optString("homeOverUnder", "1");
						String homeOverUnderFirstHalf = paramsObject.optString("homeOverUnderFirstHalf", "1");
						String homeOverUnderSecondHalf = paramsObject.optString("homeOverUnderSecondHalf", "1");
						String awayOverUnder = paramsObject.optString("awayOverUnder", "1");
						String awayOverUnderFirstHalf = paramsObject.optString("awayOverUnderFirstHalf", "1");
						String awayOverUnderSecondHalf = paramsObject.optString("awayOverUnderSecondHalf", "1");
						String hendikep1 = paramsObject.optString("hd2", "1");
						String hendikep2 = paramsObject.optString("handicap2", "1");
						String hendikep3 = paramsObject.optString("handicap3", "1");
						String hendikepDrugoPoluvreme = paramsObject.optString("hd2p", "1");
						
						matchDataObject.put("overUnderGr1", overUnder);
						matchDataObject.put("overUnderGr2", overUnder2);
						matchDataObject.put("overUnderGr3", overUnder3);
						matchDataObject.put("overUnderGr4", overUnder4);
						matchDataObject.put("overUnderGr5", overUnder5);
						matchDataObject.put("bothTeamScoreGr1", bothTeamScore);
						matchDataObject.put("overUnderPGr1", overUnderP);
						matchDataObject.put("overUnderSecondHalfGr1", overUnderSecondHalf);
						matchDataObject.put("homeOverUnderGr1", homeOverUnder);
						matchDataObject.put("homeOverUnderFirstHalfGr1", homeOverUnderFirstHalf);
						matchDataObject.put("homeOverUnderSecondHalfGr1", homeOverUnderSecondHalf);
						matchDataObject.put("awayOverUnderGr1", awayOverUnder);
						matchDataObject.put("awayOverUnderFirstHalfGr1", awayOverUnderFirstHalf);
						matchDataObject.put("awayOverUnderSecondHalfGr1", awayOverUnderSecondHalf);
						matchDataObject.put("hendikepGr1", hendikep1);
						matchDataObject.put("hendikepGr2", hendikep2);
						matchDataObject.put("hendikepGr3", hendikep3);
						matchDataObject.put("hendikepDrugoPoluvremeGr1", hendikepDrugoPoluvreme);
					}

					matchDataObject.put("home", homeTeam);
					matchDataObject.put("away", awayTeam);
					matchDataObject.put("Start", kickOffTime);
					matchDataObject.put("Liga", leagueName);
					matchDataObject.put("Kladionica", "Maxbet");
					
					matchDataObject.put("1X", JEDAN_X);
					matchDataObject.put("X2", X2);
					matchDataObject.put("1P1X", JEDAN_P1X);
					matchDataObject.put("1PX2", JEDAN_PX2);
					matchDataObject.put("2P1X", DVA_P1X);
					matchDataObject.put("2PX2", DVA_PX2);
					matchDataObject.put("W1", W1);
					matchDataObject.put("W2", W2);
					matchDataObject.put("1PW1", JEDAN_PW1);
					matchDataObject.put("1PW2", JEDAN_PW2);
					matchDataObject.put("2PW1", DVA_PW1);
					matchDataObject.put("2PW2", DVA_PW2);
					matchDataObject.put("PAR", PAR);
					matchDataObject.put("NEPAR", NEPAR);
					
					matchDataObject.put("UG1_MINUS", UG1_MINUS);//
					matchDataObject.put("UG2_MINUS", UG2_MINUS);//
					matchDataObject.put("UG3_MINUS", UG3_MINUS);//
					matchDataObject.put("UG4_MINUS", UG4_MINUS);//
					matchDataObject.put("UG5_MINUS", UG5_MINUS);//
					matchDataObject.put("UG1_PLUS", UG1_PLUS);//
					matchDataObject.put("UG2_PLUS", UG2_PLUS);//
					matchDataObject.put("UG3_PLUS", UG3_PLUS);//
					matchDataObject.put("UG4_PLUS", UG4_PLUS);//
					matchDataObject.put("UG5_PLUS", UG5_PLUS);//
					matchDataObject.put("UGP1_MINUS", UGP_MINUS);//
					matchDataObject.put("UGP1_PLUS", UGP_PLUS);//
					matchDataObject.put("UG2P1_MINUS", UG2P_MINUS);//
					matchDataObject.put("UG2P1_PLUS", UG2P_PLUS);//
					matchDataObject.put("D1_MINUS", D_MINUS);//
					matchDataObject.put("D1_PLUS", D_PLUS);//
					matchDataObject.put("1D1_MINUS", JEDAN_D_MINUS);//
					matchDataObject.put("1D1_PLUS", JEDAN_D_PLUS);//
					matchDataObject.put("2PD1_MINUS", DVA_PD_MINUS);
					matchDataObject.put("2PD1_PLUS", DVA_PD_PLUS);
					matchDataObject.put("G1_MINUS", G_MINUS);//
					matchDataObject.put("G1_PLUS", G_PLUS);//
					matchDataObject.put("1G1_MINUS", JEDAN_G_MINUS);
					matchDataObject.put("1G1_PLUS", JEDAN_G_PLUS);
					matchDataObject.put("2PG1_MINUS", DVA_PG_MINUS);
					matchDataObject.put("2PG1_PLUS", DVA_PG_PLUS);
					matchDataObject.put("H11", H11);//
					matchDataObject.put("H12", H12);//
					matchDataObject.put("H21", H21);//
					matchDataObject.put("H22", H22);//
					matchDataObject.put("H31", H31);//
					matchDataObject.put("H32", H32);//
					matchDataObject.put("2PH1_1", DVA_PH1);
					matchDataObject.put("2PH2_1", DVA_PH2);
					matchDataObject.put("BJTDM1", BJTDM);//
					matchDataObject.put("OTDV1", OTDV);//

					matchesDataArray.put(matchDataObject);
					}
             }

             return matchesDataArray.toString();
        }
        return "[]";
    }
	
}
