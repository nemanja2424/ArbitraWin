package kosarka;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import Fudbal.pozoviAPI;

public class BetOleK {
	
	private static final String POCETAK_URL_LEAGUE = "https://www.betole.com/restapi/offer/sr/sport/B/league/";
    private static final String KRAJ_URL_LEAGUE = "/mob?annex=0&mobileVersion=2.33.6.5&locale=sr";
    private static final String POCETAK_URL_MATCH = "https://www.betole.com/restapi/offer/sr/match/";
    private static final String KRAJ_URL_MATCH = "?annex=0&mobileVersion=2.33.6.5&locale=sr";
	
    //private static List<String> savedLeagueId;
   // private static String savedMatchId;
    
    public static JSONArray praviAPI() {
        List<String> savedMatchIds = uzmiIdUtakmica();

        JSONArray sveUtakmice = new JSONArray();

        for (String id : savedMatchIds) {
            String utakmicaUrl = POCETAK_URL_MATCH + id + KRAJ_URL_MATCH;
            String odgovor = pozoviAPI.pozoviAPI(utakmicaUrl);

            if (odgovor != null) {
                String jsonText = "[" + odgovor + "]";
                JSONArray utakmiceLige = new JSONArray(jsonText);
                //System.out.println(utakmicaUrl);
                
                // Izvučeni podaci se obrađuju pre dodavanja informacija o kladionici
                String processedMatches = izvuceniPodaci(utakmiceLige.toString());
                JSONArray processedMatchesArray = new JSONArray(processedMatches);
                //System.out.println(utakmiceLige);
                
                
                // Add each JSONObject from processedMatchesArray to sveUtakmice
                for (int i = 0; i < processedMatchesArray.length(); i++) {
                    sveUtakmice.put(processedMatchesArray.getJSONObject(i));
                }
                //System.out.println(processedMatchesArray);
            }
        }

        //System.out.println(sveUtakmice.toString());
		return sveUtakmice;
    }




    public static List<String> uzmiIdLiga() {
        List<String> idLiga = new ArrayList<>();

        try {
            String urlString = "https://www.betole.com/restapi/offer/sr/categories/top/l?annex=0&offset=10&sport=B&mobileVersion=2.33.6.5&locale=sr";
            String odgovor = pozoviAPI.pozoviAPI(urlString);

            if (odgovor != null) {
                JSONObject jsonResponse = new JSONObject(odgovor);
                JSONArray kategorije = jsonResponse.getJSONArray("categories");

                for (int i = 0; i < kategorije.length(); i++) {
                    JSONObject kategorija = kategorije.getJSONObject(i);
                    String leagueId = kategorija.getString("id");
                    idLiga.add(leagueId);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return idLiga;
    }

    public static List<String> uzmiIdUtakmica() {
        List<String> savedMatchIds = new ArrayList<>();
        List<String> leagueIds = uzmiIdLiga();

        for (String leagueId : leagueIds) {
            String leagueUrl = POCETAK_URL_LEAGUE + leagueId + KRAJ_URL_LEAGUE;
            String odgovor = pozoviAPI.pozoviAPI(leagueUrl);

            if (odgovor != null) {
                JSONObject jsonResponse = new JSONObject(odgovor);
                if (jsonResponse.has("esMatches")) {
                    JSONArray esMatches = jsonResponse.getJSONArray("esMatches");
                    if (esMatches.length() > 0) {
                        for (int i = 0; i < esMatches.length(); i++) {
                            String savedMatchId = String.valueOf(esMatches.getJSONObject(i).getInt("id"));
                            savedMatchIds.add(savedMatchId);
                            //System.out.println(savedMatchId);
                        }
                    }
                }
            }
        }

        return savedMatchIds;
    }


    /*public static String izvuceniPodaci(String jsonString) {
        MobAnexInfo extractor = new MobAnexInfo();
        return extractor.izvuceniPodaci(jsonString);
    }*/
    
    public static String izvuceniPodaci(String jsonString) {
        if (jsonString != null) {
            JSONArray matchesArray = new JSONArray(jsonString);
            JSONArray matchesDataArray = new JSONArray();

            for (int i = 0; i < matchesArray.length(); i++) {
                JSONObject matchObject = matchesArray.getJSONObject(i);

                if (matchObject.has("home") && matchObject.has("away") && matchObject.has("kickOffTime")
                        && matchObject.has("odds")) {
                	String homeTeam = matchObject.getString("home");
                    String awayTeam = matchObject.getString("away");
                    long kickOffTime = matchObject.getLong("kickOffTime");
                    String leagueName = matchObject.getString("leagueName");

                    JSONObject oddsObject = matchObject.getJSONObject("odds");
                    double FTOT1 = oddsObject.optDouble("50291", 1);
                    double FTOT2 = oddsObject.optDouble("50293", 1);
                    double JEDAN_X = oddsObject.optDouble("7", 1);
                    double X2 = oddsObject.optDouble("9", 1);
                    double P1X = oddsObject.optDouble("397", 1);
                    double PX2 = oddsObject.optDouble("399", 1);
                    double DVA_P1X = oddsObject.optDouble("608", 1);
                    double DVA_PX2 = oddsObject.optDouble("610", 1);
                    double JEDAN_PW1 = oddsObject.optDouble("611", 1);
                    double JEDAN_PW2 = oddsObject.optDouble("612", 1);
                    double DVA_PW1 = oddsObject.optDouble("613", 1);
                    double DVA_PW2 = oddsObject.optDouble("614", 1);
                    double Q1W1 = oddsObject.optDouble("50025", 1);
                    double Q1W2 = oddsObject.optDouble("50026", 1);
                    double Q2W1 = oddsObject.optDouble("50173", 1);
                    double Q2W2 = oddsObject.optDouble("50174", 1);
                    double Q3W1 = oddsObject.optDouble("50175", 1);
                    double Q3W2 = oddsObject.optDouble("50176", 1);
                    double Q4W1 = oddsObject.optDouble("50177", 1);
                    double Q4W2 = oddsObject.optDouble("50178", 1);
                    double Q11X = oddsObject.optDouble("50022", 1);
                    double Q1X2 = oddsObject.optDouble("50024", 1);
                    double Q21X = oddsObject.optDouble("50179", 1);
                    double Q2X2 = oddsObject.optDouble("50181", 1);
                    double Q31X = oddsObject.optDouble("50182", 1);
                    double Q3X2 = oddsObject.optDouble("50184", 1);
                    double Q41X = oddsObject.optDouble("50185", 1);
                    double Q4X2 = oddsObject.optDouble("50187", 1);
                    double PAR_OT = oddsObject.optDouble("50459", 1);
                    double NEPAR_OT = oddsObject.optDouble("50485", 1);
                    double PPR = oddsObject.optDouble("233", 1);	//PAR PRVO POLUVREME
                    double PNP = oddsObject.optDouble("234", 1);	//NEPAR PRVO POLUVREME
                    double DVA_P_PAR = oddsObject.optDouble("50200", 1);
                    double DVA_P_NEPAR = oddsObject.optDouble("50201", 1);

                    double HOT1 = oddsObject.optDouble("50430", 1);
                    double HOT2 = oddsObject.optDouble("50431", 1);
                    double HOT21 = oddsObject.optDouble("50432", 1);
                    double HOT22 = oddsObject.optDouble("50433", 1);
                    double HOT31 = oddsObject.optDouble("50434", 1);
                    double HOT32 = oddsObject.optDouble("50435", 1);
                    double HOT41 = oddsObject.optDouble("50436", 1);
                    double HOT42 = oddsObject.optDouble("50437", 1);
                    double HOT51 = oddsObject.optDouble("50438", 1);
                    double HOT52 = oddsObject.optDouble("50439", 1);
                    double HOT61 = oddsObject.optDouble("50440", 1);
                    double HOT62 = oddsObject.optDouble("50441", 1);
                    double HOT71 = oddsObject.optDouble("50442", 1);
                    double HOT72 = oddsObject.optDouble("50443", 1);
                    double HOT81 = oddsObject.optDouble("51624", 1);
                    double HOT82 = oddsObject.optDouble("51625", 1);
                    double HOT91 = oddsObject.optDouble("51626", 1);
                    double HOT92 = oddsObject.optDouble("51627", 1);
                    double HOT101 = oddsObject.optDouble("51628", 1);
                    double HOT102 = oddsObject.optDouble("51629", 1);
                    double HOT111 = oddsObject.optDouble("51630", 1);
                    double HOT112 = oddsObject.optDouble("51631", 1);
                    double PH1 = oddsObject.optDouble("224", 1);
                    double PH2 = oddsObject.optDouble("226", 1);
                    double DVA_PH1 = oddsObject.optDouble("50136", 1);
                    double DVA_PH2 = oddsObject.optDouble("50138", 1);
                    double Q1H1 = oddsObject.optDouble("602", 1);
                    double Q1H2 = oddsObject.optDouble("603", 1);
                    double Q2H1 = oddsObject.optDouble("50139", 1);
                    double Q2H2 = oddsObject.optDouble("50140", 1);
                    double Q3H1 = oddsObject.optDouble("50141", 1);
                    double Q3H2 = oddsObject.optDouble("50142", 1);
                    double Q4H1 = oddsObject.optDouble("50143", 1);
                    double Q4H2 = oddsObject.optDouble("50144", 1);
                    double OT_MINUS = oddsObject.optDouble("50444", 1);
                    double OT2_MINUS = oddsObject.optDouble("50446", 1);
                    double OT3_MINUS = oddsObject.optDouble("50448", 1);
                    double OT4_MINUS = oddsObject.optDouble("50450", 1);
                    double OT5_MINUS = oddsObject.optDouble("50452", 1);
                    double OT6_MINUS = oddsObject.optDouble("50454", 1);
                    double OT7_MINUS = oddsObject.optDouble("50456", 1);
                    double OT_PLUS = oddsObject.optDouble("50445", 1);
                    double OT2_PLUS = oddsObject.optDouble("50447", 1);
                    double OT3_PLUS = oddsObject.optDouble("50449", 1);
                    double OT4_PLUS = oddsObject.optDouble("50451", 1);
                    double OT5_PLUS = oddsObject.optDouble("50453", 1);
                    double OT6_PLUS = oddsObject.optDouble("50455", 1);
                    double OT7_PLUS = oddsObject.optDouble("50457", 1);
                    double JEDAN_P_MINUS = oddsObject.optDouble("50980", 1);
                    double JEDAN_P_PLUS = oddsObject.optDouble("50979", 1);
                    double DVA_P_MINUS = oddsObject.optDouble("50986", 1);
                    double DVA_P_PLUS = oddsObject.optDouble("50988", 1);
                    double Q1_MINUS = oddsObject.optDouble("599", 1);
                    double Q1_PLUS = oddsObject.optDouble("598", 1);
                    double Q2_MINUS = oddsObject.optDouble("50165", 1);
                    double Q2_PLUS = oddsObject.optDouble("50166", 1);
                    double Q3_MINUS = oddsObject.optDouble("50167", 1);
                    double Q3_PLUS = oddsObject.optDouble("50168", 1);
                    double Q4_MINUS = oddsObject.optDouble("50169", 1);
                    double Q4_PLUS = oddsObject.optDouble("50170", 1);
                    double OTH_MINUS = oddsObject.optDouble("50466", 1);
                    double OTH_PLUS = oddsObject.optDouble("50467", 1);
                    double OTA_MINUS = oddsObject.optDouble("50478", 1);
                    double OTA_PLUS = oddsObject.optDouble("50479", 1);
                    double JEDAN_D_MINUS = oddsObject.optDouble("50982", 1);
                    double JEDAN_D_PLUS = oddsObject.optDouble("50983", 1);
                    double DVA_D_MINUS = oddsObject.optDouble("50989", 1);
                    double DVA_D_PLUS = oddsObject.optDouble("50990", 1);
                    double Q1D_MINUS = oddsObject.optDouble("50027", 1);
                    double Q1D_PLUS = oddsObject.optDouble("50028", 1);
                    double JEDAN_G_MINUS = oddsObject.optDouble("50984", 1);
                    double JEDAN_G_PLUS = oddsObject.optDouble("50985", 1);
                    double DVA_G_MINUS = oddsObject.optDouble("50991", 1);
                    double DVA_G_PLUS = oddsObject.optDouble("50992", 1);
                    double Q1G_MINUS = oddsObject.optDouble("50029", 1);
                    double Q1G_PLUS = oddsObject.optDouble("50030", 1);
				 						
						JSONObject matchDataObject = new JSONObject();
						
						if (matchObject.has("params")) {
							JSONObject paramsObject = matchObject.getJSONObject("params");
							
							String hendikepGr1 = paramsObject.optString("handicapOvertime", "1");
							String hendikepGr2 = paramsObject.optString("handicapOvertime2", "1");
							String hendikepGr3 = paramsObject.optString("handicapOvertime3", "1");
							String hendikepGr4 = paramsObject.optString("handicapOvertime4", "1");
							String hendikepGr5 = paramsObject.optString("handicapOvertime5", "1");
							String hendikepGr6 = paramsObject.optString("handicapOvertime6", "1");
							String hendikepGr7 = paramsObject.optString("handicapOvertime7", "1");
							String hendikepGr8 = paramsObject.optString("handicapOvertime8", "1");
							String hendikepGr9 = paramsObject.optString("handicapOvertime9", "1");
							String hendikepGr10 = paramsObject.optString("handicapOvertime10", "1");
							String hendikepGr11 = paramsObject.optString("handicapOvertime11", "1");
							String hendikepPrvoPoluvremeGr = paramsObject.optString("hdp", "1");
							String hendikepDrugoPoluvremeGr = paramsObject.optString("hd2p", "1");
							String hendikepPrvaCetGr = paramsObject.optString("handicapFirstPeriod", "1");
							String hendikepDrugaCetGr = paramsObject.optString("handicapSecondPeriod", "1");
							String hendikepTrecaCetGr = paramsObject.optString("handicapThirdPeriod", "1");
							String hendikepCetvrtaCetGr = paramsObject.optString("handicapFourthPeriod", "1");
							String overUnderOvertime = paramsObject.optString("overUnderOvertime", "1");
							String overUnderOvertime2 = paramsObject.optString("overUnderOvertime2", "1");
							String overUnderOvertime3 = paramsObject.optString("overUnderOvertime3", "1");
							String overUnderOvertime4 = paramsObject.optString("overUnderOvertime4", "1");
							String overUnderOvertime5 = paramsObject.optString("overUnderOvertime5", "1");
							String overUnderOvertime6 = paramsObject.optString("overUnderOvertime6", "1");
							String overUnderOvertime7 = paramsObject.optString("overUnderOvertime7", "1");
							String overUnderP = paramsObject.optString("overUnderP", "1");
							String overUnderSecondHalf = paramsObject.optString("overUnderSecondHalf", "1");
							String overUnderFirstPeriod = paramsObject.optString("overUnderFirstPeriod", "1");
							String overUnderSecondPeriod = paramsObject.optString("overUnderSecondPeriod", "1");
							String overUnderThirdPeriod = paramsObject.optString("overUnderThirdPeriod", "1");
							String overUnderFourthPeriod = paramsObject.optString("overUnderFourthPeriod", "1");
							String homeOverUnderOvertime = paramsObject.optString("homeOverUnderOvertime", "1");
							String awayOverUnderOvertime = paramsObject.optString("awayOverUnderOvertime", "1");
							String homeOverUnderFirstHalf = paramsObject.optString("homeOverUnderFirstHalf", "1");
							String homeOverUnderSecondHalf = paramsObject.optString("homeOverUnderSecondHalf", "1");
							String homeOverUnderFirstPeriod = paramsObject.optString("homeOverUnderFirstPeriod", "1");
							String awayOverUnderFirstHalf = paramsObject.optString("awayOverUnderFirstHalf", "1");
							String awayOverUnderSecondHalf = paramsObject.optString("awayOverUnderSecondHalf", "1");
							String awayOverUnderFirstPeriod = paramsObject.optString("awayOverUnderFirstPeriod", "1");

							matchDataObject.put("hendikepGr1", hendikepGr1);
							matchDataObject.put("hendikepGr2", hendikepGr2);
							matchDataObject.put("hendikepGr3", hendikepGr3);
							matchDataObject.put("hendikepGr4", hendikepGr4);
							matchDataObject.put("hendikepGr5", hendikepGr5);
							matchDataObject.put("hendikepGr6", hendikepGr6);
							matchDataObject.put("hendikepGr7", hendikepGr7);
							matchDataObject.put("hendikepGr8", hendikepGr8);
							matchDataObject.put("hendikepGr9", hendikepGr9);
							matchDataObject.put("hendikepGr10", hendikepGr10);
							matchDataObject.put("hendikepGr11", hendikepGr11);
							matchDataObject.put("hendikepPrvoPoluvremeGr1", hendikepPrvoPoluvremeGr);
							matchDataObject.put("hendikepDrugoPoluvremeGr1", hendikepDrugoPoluvremeGr);
							matchDataObject.put("hendikepPrviPeriodGr1", hendikepPrvaCetGr);
							matchDataObject.put("hendikepDrugPeriodGr1", hendikepDrugaCetGr);
							matchDataObject.put("hendikepTreciPeriodGr1", hendikepTrecaCetGr);
							matchDataObject.put("hendikepCetvrtiPeriodGr1", hendikepCetvrtaCetGr);
							matchDataObject.put("overUnderOvertime1", overUnderOvertime);
							matchDataObject.put("overUnderOvertime2", overUnderOvertime2);
							matchDataObject.put("overUnderOvertime3", overUnderOvertime3);
							matchDataObject.put("overUnderOvertime4", overUnderOvertime4);
							matchDataObject.put("overUnderOvertime5", overUnderOvertime5);
							matchDataObject.put("overUnderOvertime6", overUnderOvertime6);
							matchDataObject.put("overUnderOvertime7", overUnderOvertime7);
							matchDataObject.put("overUnderPrvoPoluvreme1", overUnderP);
							matchDataObject.put("overUnderDrugoPoluvreme1", overUnderSecondHalf);
							matchDataObject.put("overUnderPrviPeriod1", overUnderFirstPeriod);
							matchDataObject.put("overUnderDrugiPeriod1", overUnderSecondPeriod);
							matchDataObject.put("overUnderTreciPeriod1", overUnderThirdPeriod);
							matchDataObject.put("overUnderCetvrtiPeriod1", overUnderFourthPeriod);
							matchDataObject.put("homeOverUnderOvertime1", homeOverUnderOvertime);
							matchDataObject.put("awayOverUnderOvertime1", awayOverUnderOvertime);
							matchDataObject.put("homeOverUnderFirstHalf1", homeOverUnderFirstHalf);
							matchDataObject.put("homeOverUnderSecondHalf1", homeOverUnderSecondHalf);
							matchDataObject.put("homeOverUnderFirstPeriod1", homeOverUnderFirstPeriod);
							matchDataObject.put("awayOverUnderFirstHalf1", awayOverUnderFirstHalf);
							matchDataObject.put("awayOverUnderSecondHalf1", awayOverUnderSecondHalf);
							matchDataObject.put("awayOverUnderFirstPeriod1", awayOverUnderFirstPeriod);
						}
						
						matchDataObject.put("home", homeTeam);
						matchDataObject.put("away", awayTeam);
						matchDataObject.put("Start", kickOffTime);
						matchDataObject.put("Liga", leagueName);
						matchDataObject.put("Kladionica", "BetOle");

						matchDataObject.put("FTOT1", FTOT1);
						matchDataObject.put("FTOT2", FTOT2);
						matchDataObject.put("1X", JEDAN_X);
						matchDataObject.put("X2", X2);
						matchDataObject.put("FTOT1", FTOT1);
						matchDataObject.put("P1X", P1X);
						matchDataObject.put("PX2", PX2);
						matchDataObject.put("2P1X", DVA_P1X);
						matchDataObject.put("2PX2", DVA_PX2);
						matchDataObject.put("1PW1", JEDAN_PW1);
						matchDataObject.put("1PW2", JEDAN_PW2);
						matchDataObject.put("2PW1", DVA_PW1);
						matchDataObject.put("2PW2", DVA_PW2);
						matchDataObject.put("Q1W1", Q1W1);
						matchDataObject.put("Q1W2", Q1W2);
						matchDataObject.put("Q2W1", Q2W1);
						matchDataObject.put("Q2W2", Q2W2);
						matchDataObject.put("Q3W1", Q3W1);
						matchDataObject.put("Q3W2", Q3W2);
						matchDataObject.put("Q4W1", Q4W1);
						matchDataObject.put("Q4W2", Q4W2);
						matchDataObject.put("Q11X", Q11X);
						matchDataObject.put("Q1X2", Q1X2);
						matchDataObject.put("Q21X", Q21X);
						matchDataObject.put("Q2X2", Q2X2);
						matchDataObject.put("Q31X", Q31X);
						matchDataObject.put("Q3X2", Q3X2);
						matchDataObject.put("Q41X", Q41X);
						matchDataObject.put("Q4X2", Q4X2);
						matchDataObject.put("PAR_OT", PAR_OT);
						matchDataObject.put("NEPAR_OT", NEPAR_OT);
						matchDataObject.put("PPR", PPR);
						matchDataObject.put("PNP", PNP);
						matchDataObject.put("2PPAR", DVA_P_PAR);
						matchDataObject.put("2PNEPAR", DVA_P_NEPAR);
						
						matchDataObject.put("HOT11", HOT1);
						matchDataObject.put("HOT12", HOT2);
						matchDataObject.put("HOT21", HOT21);
						matchDataObject.put("HOT22", HOT22);
						matchDataObject.put("HOT31", HOT31);
						matchDataObject.put("HOT32", HOT32);
						matchDataObject.put("HOT41", HOT41);
						matchDataObject.put("HOT42", HOT42);
						matchDataObject.put("HOT51", HOT51);
						matchDataObject.put("HOT52", HOT52);
						matchDataObject.put("HOT61", HOT61);
						matchDataObject.put("HOT62", HOT62);
						matchDataObject.put("HOT71", HOT71);
						matchDataObject.put("HOT72", HOT72);
						matchDataObject.put("HOT81", HOT81);
						matchDataObject.put("HOT82", HOT82);
						matchDataObject.put("HOT91", HOT91);
						matchDataObject.put("HOT92", HOT92);
						matchDataObject.put("HOT101", HOT101);
						matchDataObject.put("HOT102", HOT102);
						matchDataObject.put("HOT111", HOT111);
						matchDataObject.put("HOT112", HOT112);
						matchDataObject.put("Q1H11", Q1H1);
						matchDataObject.put("Q1H12", Q1H2);
						matchDataObject.put("Q2H11", Q2H1);
						matchDataObject.put("Q2H12", Q2H2);
						matchDataObject.put("Q3H11", Q3H1);
						matchDataObject.put("Q3H12", Q3H2);
						matchDataObject.put("Q4H11", Q4H1);
						matchDataObject.put("Q4H12", Q4H2);
						matchDataObject.put("PH11", PH1);
						matchDataObject.put("PH12", PH2);
						matchDataObject.put("2PH11", DVA_PH1);
						matchDataObject.put("2PH12", DVA_PH2);
						matchDataObject.put("OT1_MINUS", OT_MINUS);//----------------------
						matchDataObject.put("OT2_MINUS", OT2_MINUS);
						matchDataObject.put("OT3_MINUS", OT3_MINUS);
						matchDataObject.put("OT4_MINUS", OT4_MINUS);
						matchDataObject.put("OT5_MINUS", OT5_MINUS);
						matchDataObject.put("OT6_MINUS", OT6_MINUS);
						matchDataObject.put("OT7_MINUS", OT7_MINUS);
						matchDataObject.put("1P1_MINUS", JEDAN_P_MINUS);
						matchDataObject.put("2P1_MINUS", DVA_P_MINUS);
						matchDataObject.put("Q11_MINUS", Q1_MINUS);
						matchDataObject.put("Q21_MINUS", Q2_MINUS);
						matchDataObject.put("Q31_MINUS", Q3_MINUS);
						matchDataObject.put("Q41_MINUS", Q4_MINUS);
						matchDataObject.put("OTH1_MINUS", OTH_MINUS);
						matchDataObject.put("OTA1_MINUS", OTA_MINUS);
						matchDataObject.put("1D1_MINUS", JEDAN_D_MINUS);
						matchDataObject.put("2D1_MINUS", DVA_D_MINUS);
						matchDataObject.put("Q1D1_MINUS", Q1D_MINUS);
						matchDataObject.put("1G1_MINUS", JEDAN_G_MINUS);
						matchDataObject.put("2G1_MINUS", DVA_G_MINUS);
						matchDataObject.put("Q1G1_MINUS", Q1G_MINUS);
						matchDataObject.put("OT1_PLUS", OT_PLUS);//----------------------
						matchDataObject.put("OT2_PLUS", OT2_PLUS);
						matchDataObject.put("OT3_PLUS", OT3_PLUS);
						matchDataObject.put("OT4_PLUS", OT4_PLUS);
						matchDataObject.put("OT5_PLUS", OT5_PLUS);
						matchDataObject.put("OT6_PLUS", OT6_PLUS);
						matchDataObject.put("OT7_PLUS", OT7_PLUS);
						matchDataObject.put("1P1_PLUS", JEDAN_P_PLUS);
						matchDataObject.put("2P1_PLUS", DVA_P_PLUS);
						matchDataObject.put("Q11_PLUS", Q1_PLUS);
						matchDataObject.put("Q21_PLUS", Q2_PLUS);
						matchDataObject.put("Q31_PLUS", Q3_PLUS);
						matchDataObject.put("Q41_PLUS", Q4_PLUS);
						matchDataObject.put("OTH1_PLUS", OTH_PLUS);
						matchDataObject.put("OTA1_PLUS", OTA_PLUS);
						matchDataObject.put("1D1_PLUS", JEDAN_D_PLUS);
						matchDataObject.put("2D1_PLUS", DVA_D_PLUS);
						matchDataObject.put("Q1D1_PLUS", Q1D_PLUS);
						matchDataObject.put("1G1_PLUS", JEDAN_G_PLUS);
						matchDataObject.put("2G1_PLUS", DVA_G_PLUS);
						matchDataObject.put("Q1G1_PLUS", Q1G_PLUS);

						matchesDataArray.put(matchDataObject);
					}
             }

             return matchesDataArray.toString();
        }

        return null;
    }


}


