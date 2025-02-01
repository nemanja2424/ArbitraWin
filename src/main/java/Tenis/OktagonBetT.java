package Tenis;

import org.json.JSONArray;
import org.json.JSONObject;
import Fudbal.pozoviAPI;

public class OktagonBetT {

	public static JSONArray praviAPI() {
		String url = "https://www.oktagonbet.com/restapi/offer/sr/sport/T/mob?annex=1&mobileVersion=2.33&locale=sr";
		String odgovor = pozoviAPI.pozoviAPI(url);
		JSONArray sveUtakmice = new JSONArray();
		
		if (odgovor != null) {
            JSONArray utakmiceLige = new JSONArray(izvuceniPodaci(odgovor));

            // Dodajemo svaki objekat iz utakmiceLige direktno u sveUtakmice
            for (int i = 0; i < utakmiceLige.length(); i++) {
                sveUtakmice.put(utakmiceLige.get(i));
            }
        }
		//System.out.println(sveUtakmice);
		return sveUtakmice;
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
                            && matchObject.has("odds")) {
                    	JSONObject matchDataObject = new JSONObject();
                    	
                        String homeIgrac = matchObject.getString("home");
                        String awayIgrac = matchObject.getString("away");
                        long kickOffTime = matchObject.getLong("kickOffTime");
                        String Liga = matchObject.getString("leagueName");

                        JSONObject oddsObject = matchObject.getJSONObject("odds");
                        double homePobeda = oddsObject.optDouble("1", 1);
                        double awayPobeda = oddsObject.optDouble("3", 1);
                        double PS1 = oddsObject.optDouble("50510", 1);
                        double PS2 = oddsObject.optDouble("50511", 1);
                        double DS1 = oddsObject.optDouble("50512", 1);
                        double DS2 = oddsObject.optDouble("50513", 1);
                        double TS1 = oddsObject.optDouble("50514", 1);
                        double TS2 = oddsObject.optDouble("50515", 1);
                        double SS = oddsObject.optDouble("51658", 1);		//set-set = oba igraca osvajaju set
                        double NS = oddsObject.optDouble("51657", 1);		//jedan igrac nece da osvoji set                   
                        double PTBDA = oddsObject.optDouble("51196", 1);	//TIE BREAK CE DA BUDE NA MEČU
                        double PTBNE = oddsObject.optDouble("51197", 1); 	//TIE BREAK NEĆE DA BUDE NA MEČU
                        
                        double G_MINUS = oddsObject.optDouble("254", 1);	//ukupno gemova -
                        double G_PLUS = oddsObject.optDouble("256", 1);		//ukupno gemova +
                        double HS1 = oddsObject.optDouble("50538", 1);		//IGRAČ 1 POBEĐUJE SA HENDIKEPOM SETOVA
                        double HS2 = oddsObject.optDouble("50539", 1);		//IGRAČ 2 POBEĐUJE SA HENDIKEPOM SETOVA
                        double HG1 = oddsObject.optDouble("251", 1);		//IGRAČ 1 POBEĐUJE SA HENDIKEPOM GEMOVA
                        double HG2 = oddsObject.optDouble("253", 1);		//IGRAČ 2 POBEĐUJE SA HENDIKEPOM GEMOVA
                        double UGPS_MINUS1 = oddsObject.optDouble("257", 1);//UKUPNO GEMOVA PRVI SET PRVA GRANICA
                        double UGPS_PLUS1 = oddsObject.optDouble("259", 1);	//UKUPNO GEMOVA PRVI SET PRVA GRANICA
                        double UGPS_MINUS2 = oddsObject.optDouble("51370", 1);//UKUPNO GEMOVA PRVI SET DRUGA GRANICA
                        double UGPS_PLUS2 = oddsObject.optDouble("51372", 1);//UKUPNO GEMOVA PRVI SET DRUGA GRANICA
                        double UGPS_MINUS3 = oddsObject.optDouble("51373", 1);//...
                        double UGPS_PLUS3 = oddsObject.optDouble("51375", 1);
                        double UGPS_MINUS4 = oddsObject.optDouble("51376", 1);
                        double UGPS_PLUS4 = oddsObject.optDouble("51378", 1);
                        double UGPS_MINUS5 = oddsObject.optDouble("51379", 1);
                        double UGPS_PLUS5 = oddsObject.optDouble("51381", 1);
                        double UGDS_MINUS = oddsObject.optDouble("50520", 1);//UKUPNO GEMOVA DRUGI SET
                        double UGDS_PLUS = oddsObject.optDouble("50521", 1); //UKUPNO GEMOVA DRUGI SET
                        double UGTS_MINUS = oddsObject.optDouble("50522", 1);//UKUPNO GEMOVA TRECI SET
                        double UGTS_PLUS = oddsObject.optDouble("50523", 1); //UKUPNO GEMOVA TRECI SET
                        
                        if (matchObject.has("params")) {
    	                    JSONObject paramsObject = matchObject.getJSONObject("params");
    	                    String granicaGemovi = paramsObject.optString("overUnderGames", "1");
    	                    String hendikepSet1 = paramsObject.optString("hd2", "1");
    	                    String hendikepGem1 = paramsObject.optString("handicapGames", "1");
    	                    String ukupnoGemovaPrviSetGranica1 = paramsObject.optString("overUnderGamesFirstSet", "1");
    	                    String ukupnoGemovaPrviSetGranica2 = paramsObject.optString("overUnderGamesFirstSet2", "1");
    	                    String ukupnoGemovaPrviSetGranica3 = paramsObject.optString("overUnderGamesFirstSet3", "1");
    	                    String ukupnoGemovaPrviSetGranica4 = paramsObject.optString("overUnderGamesFirstSet4", "1");
    	                    String ukupnoGemovaPrviSetGranica5 = paramsObject.optString("overUnderGamesFirstSet5", "1");
    	                    String ukupnoGemovaDrugiSetGranica1 = paramsObject.optString("overUnderGamesSecondSet", "1");
    	                    //double ukupnoGemovaDrugiSetGranica2 = paramsObject.optDouble("overUnderGamesSecondSet2", 1);
    	                    //double ukupnoGemovaDrugiSetGranica3 = paramsObject.optDouble("overUnderGamesSecondSet3", 1);
    	                    //double ukupnoGemovaDrugiSetGranica4 = paramsObject.optDouble("overUnderGamesSecondSet4", 1);
    	                    String ukupnoGemovaTreciSetGranica1 = paramsObject.optString("overUnderGamesThirdSet", "1");
    	                    //double ukupnoGemovaTreciSetGranica2 = paramsObject.optDouble("overUnderGamesThirdSet2", 1);
    	                    //double ukupnoGemovaTreciSetGranica3 = paramsObject.optDouble("overUnderGamesThirdSet3", 1);
    	                    //double ukupnoGemovaTreciSetGranica4 = paramsObject.optDouble("overUnderGamesThirdSet4", 1);
    	                    
    	                    matchDataObject.put("GranicaGemovi", granicaGemovi);
    	                    matchDataObject.put("HendikepSetGr1", hendikepSet1);
    	                    matchDataObject.put("HendikepGemGr1", hendikepGem1);
    	                    matchDataObject.put("UGPSGr1", ukupnoGemovaPrviSetGranica1);
    	                    matchDataObject.put("UGPSGr2", ukupnoGemovaPrviSetGranica2);
    	                    matchDataObject.put("UGPSGr3", ukupnoGemovaPrviSetGranica3);
    	                    matchDataObject.put("UGPSGr4", ukupnoGemovaPrviSetGranica4);
    	                    matchDataObject.put("UGPSGr5", ukupnoGemovaPrviSetGranica5);
    	                    matchDataObject.put("UGDSGr1", ukupnoGemovaDrugiSetGranica1);
    	                    //matchDataObject.put("UGDSGr1", ukupnoGemovaDrugiSetGranica2);
    	                    //matchDataObject.put("UGDSGr1", ukupnoGemovaDrugiSetGranica3);
    	                    //matchDataObject.put("UGDSGr1", ukupnoGemovaDrugiSetGranica4);
    	                    matchDataObject.put("UGTSGr1", ukupnoGemovaTreciSetGranica1);
    	                    //matchDataObject.put("UGTSGr1", ukupnoGemovaTreciSetGranica2);
    	                    //matchDataObject.put("UGTSGr1", ukupnoGemovaTreciSetGranica3);
    	                    //matchDataObject.put("UGTSGr1", ukupnoGemovaTreciSetGranica4);
                    }
    	                    
                        
                        
                        matchDataObject.put("home", homeIgrac);
                        matchDataObject.put("away", awayIgrac);
                        matchDataObject.put("Liga", Liga);
                        matchDataObject.put("Start", kickOffTime);
                        matchDataObject.put("Kladionica", "OktagonBet");
                        
                        matchDataObject.put("Igrac1W", homePobeda);
                        matchDataObject.put("Igrac2W", awayPobeda);
                        matchDataObject.put("PS1", PS1);
                        matchDataObject.put("PS2", PS2);
                        matchDataObject.put("DS1", DS1);
                        matchDataObject.put("DS2", DS2);
                        matchDataObject.put("TS1", TS1);
                        matchDataObject.put("TS2", TS2);
                        matchDataObject.put("SS", SS);
                        matchDataObject.put("NS", NS);
                        matchDataObject.put("PTBDA", PTBDA);
                        matchDataObject.put("PTBNE", PTBNE);
                        
                        matchDataObject.put("G_MINUS", G_MINUS);
                        matchDataObject.put("G_PLUS", G_PLUS);
                        matchDataObject.put("HS1", HS1);
                        matchDataObject.put("HS2", HS2);
                        matchDataObject.put("HG1", HG1);
                        matchDataObject.put("HG2", HG2);
                        matchDataObject.put("UGPS_MINUS1", UGPS_MINUS1);
                        matchDataObject.put("UGPS_PLUS1", UGPS_PLUS1);
                        matchDataObject.put("UGPS_MINUS2", UGPS_MINUS2);
                        matchDataObject.put("UGPS_PLUS2", UGPS_PLUS2);
                        matchDataObject.put("UGPS_MINUS3", UGPS_MINUS3);
                        matchDataObject.put("UGPS_PLUS3", UGPS_PLUS3);
                        matchDataObject.put("UGPS_MINUS4", UGPS_MINUS4);
                        matchDataObject.put("UGPS_PLUS4", UGPS_PLUS4);
                        matchDataObject.put("UGPS_MINUS5", UGPS_MINUS5);
                        matchDataObject.put("UGPS_PLUS5", UGPS_PLUS5);
                        
                        matchDataObject.put("UGDS_MINUS1", UGDS_MINUS);
                        matchDataObject.put("UGDS_PLUS1", UGDS_PLUS);
                        matchDataObject.put("UGTS_MINUS1", UGTS_MINUS);
                        matchDataObject.put("UGTS_PLUS1", UGTS_PLUS);
                        

                        matchesDataArray.put(matchDataObject);
                    }
                }
            return matchesDataArray.toString();
            }
    	}   	
    	return "[]";
    }
	
}
