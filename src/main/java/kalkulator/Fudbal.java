package kalkulator;

import org.arbet3.arbet3.App;
import org.json.JSONArray;
import org.json.JSONObject;
import Fudbal.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fudbal {

	String apiUrl = "https://x8ki-letl-twmt.n7.xano.io/api:etI0bpsP/obradjene_tekme";

	 public static void main(String[] args) {
	     // Kreiramo instancu klase Fudbal
	     Fudbal fudbal = new Fudbal();
	     fudbal.izvrsiAnalizu();
	    }
		
	public void izvrsiAnalizu() {	
	
		JSONArray maxBetArray = MaxbetF.praviAPI();
		JSONArray oktagonBetArray = OktagonBetF.praviAPI();
		JSONArray merkurArray = MerkurF.praviAPI();
		JSONArray soccerBetArray = SoccerBetF.praviAPI();
		JSONArray betOleArray = BetOleF.praviAPI();
			
		
		// Kombinovanje JSON nizova u jednu listu
		List<JSONObject> combinedList = new ArrayList<>();
		for (int i = 0; i < maxBetArray.length(); i++) {
			combinedList.add(maxBetArray.getJSONObject(i));
		}
		for (int i = 0; i < oktagonBetArray.length(); i++) {
			combinedList.add(oktagonBetArray.getJSONObject(i));
		}
		for (int i = 0; i < merkurArray.length(); i++) {
			combinedList.add(merkurArray.getJSONObject(i));
		}
		for (int i = 0; i < soccerBetArray.length(); i++) {
			combinedList.add(soccerBetArray.getJSONObject(i));
		}
		for (int i = 0; i < betOleArray.length(); i++) {
			combinedList.add(betOleArray.getJSONObject(i));
		}

		// Filtriranje mečeva
		List<List<JSONObject>> groupedMatches = combinedList.stream()
				.collect(Collectors.groupingBy(
						obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_" + obj.getString("away"),
						Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());

		JSONArray utakmice = new JSONArray();
		int brojObradjenihObjekata = 0;
		int brojUtakmica = 0;
		int profitabilneUtakmice = 0;

		// Pronalaženje i kreiranje JSON objekata za svaki meč koji zadovoljava uslov
		for (List<JSONObject> matchGroup : groupedMatches) {
			JSONObject firstMatch = matchGroup.get(0);

			/*double max = 0.0;
			String Kladionica_ = "";
			double max = 0.0;
			String Kladionica_ = "";*/
			
			double maxGG = 0.0;
			String ggKladionica = "";
			double maxNG = 0.0;
			String ngKladionica = "";

			double maxW1 = 0.0;
			String w1Kladionica = "";
			double maxW2 = 0.0;
			String w2Kladionica = "";
			
			double maxGG1 = 0.0;
			String gg1Kladionica = "";
			double maxNG1 = 0.0;
			String ng1Kladionica = "";
			
			double maxGG2 = 0.0;
			String gg2Kladionica = "";
			double maxNG2 = 0.0;
			String ng2Kladionica = "";
			
			double max1PW1 = 0.0;
			String Kladionica_1PW1 = "";
			double max1PW2 = 0.0;
			String Kladionica_1PW2 = "";
			
			double max2PW1 = 0.0;
			String Kladionica_2PW1 = "";
			double max2PW2 = 0.0;
			String Kladionica_2PW2 = "";
			
			double max1X = 0.0;
			String Kladionica_1X = "";
			double maxX2 = 0.0;
			String Kladionica_X2 = "";
			
			double maxUG1P1_PLUS = 0.0;
			String Kladionica_UG1P1_PLUS = "";
			double maxUG1P2_PLUS = 0.0;
			String Kladionica_UG1P2_PLUS = "";
			double maxUG1P3_PLUS = 0.0;
			String Kladionica_UG1P3_PLUS = "";
			double maxUG1P0_1 = 0.0;
			String Kladionica_UG1P0_1 = "";
			double maxUG1P0_2 = 0.0;
			String Kladionica_UG1P0_2 = "";
			
			double maxUG2P1_PLUS = 0.0;
			String Kladionica_UG2P1_PLUS = "";
			double maxUG2P2_PLUS = 0.0;
			String Kladionica_UG2P2_PLUS = "";
			double maxUG2P3_PLUS = 0.0;
			String Kladionica_UG2P3_PLUS = "";
			double maxUG2P0_1 = 0.0;
			String Kladionica_UG2P0_1 = "";
			double maxUG2P0_2 = 0.0;
			String Kladionica_UG2P0_2 = "";
			
			double maxD2_PLUS = 0.0;
			String Kladionica_D2_PLUS = "";
			double maxD0_2 = 0.0;
			String Kladionica_D0_2 = "";
			double maxD3_PLUS = 0.0;
			String Kladionica_D3_PLUS = "";
			
			double maxG2_PLUS = 0.0;
			String Kladionica_G2_PLUS = "";
			double maxG0_2 = 0.0;
			String Kladionica_G0_2 = "";
			double maxG3_PLUS = 0.0;
			String Kladionica_G3_PLUS = "";
			
			for (JSONObject match : matchGroup) {
				/*if (match.has("") && match.getDouble("") > max) {
				max = match.getDouble("");
				Kladionica_ = match.getString("Kladionica");
				}*/
				if (match.has("GG") && match.getDouble("GG") > maxGG) {
					maxGG = match.getDouble("GG");
					ggKladionica = match.getString("Kladionica");
				}
				if (match.has("NG") && match.getDouble("NG") > maxNG) {
					maxNG = match.getDouble("NG");
					ngKladionica = match.getString("Kladionica");
				}
				if (match.has("W1") && match.getDouble("W1") > maxW1) {
					maxW1 = match.getDouble("W1");
					w1Kladionica = match.getString("Kladionica");
				}
				if (match.has("W2") && match.getDouble("W2") > maxW2) {
					maxW2 = match.getDouble("W2");
					w2Kladionica = match.getString("Kladionica");
				}
				if (match.has("GG1") && match.getDouble("GG1") > maxGG1) {
					maxGG1 = match.getDouble("GG1");
					gg1Kladionica = match.getString("Kladionica");
				}
				if (match.has("NG1") && match.getDouble("NG1") > maxNG1) {
					maxNG1 = match.getDouble("NG1");
					ng1Kladionica = match.getString("Kladionica");
				}
				if (match.has("GG2") && match.getDouble("GG2") > maxGG2) {
					maxGG2 = match.getDouble("GG2");
					ng1Kladionica = match.getString("Kladionica");
				}
				if (match.has("NG2") && match.getDouble("NG2") > maxNG2) {
					maxNG2 = match.getDouble("NG2");
					ng2Kladionica = match.getString("Kladionica");
				}
				if (match.has("1PW1") && match.getDouble("1PW1") > max1PW1) {
					max1PW1 = match.getDouble("1PW1");
					Kladionica_1PW1 = match.getString("Kladionica");
				}
				if (match.has("1PW2") && match.getDouble("1PW2") > max1PW2) {
					max1PW2 = match.getDouble("1PW2");
					Kladionica_1PW2 = match.getString("Kladionica");
				}
				if (match.has("2PW1") && match.getDouble("2PW1") > max2PW1) {
					max2PW1 = match.getDouble("2PW1");
					Kladionica_2PW1 = match.getString("Kladionica");
				}
				if (match.has("2PW2") && match.getDouble("2PW2") > max2PW2) {
					max2PW2 = match.getDouble("2PW2");
					Kladionica_2PW2 = match.getString("Kladionica");
				}
				if (match.has("1X") && match.getDouble("1X") > max1X) {
					max1X = match.getDouble("1X");
					Kladionica_1X = match.getString("Kladionica");
				}
				if (match.has("X2") && match.getDouble("X2") > maxX2) {
					maxX2 = match.getDouble("X2");
					Kladionica_X2 = match.getString("Kladionica");
				}
				if (match.has("UG1P1+") && match.getDouble("UG1P1+") > maxUG1P1_PLUS) {
					maxUG1P1_PLUS = match.getDouble("UG1P1+");
					Kladionica_UG1P1_PLUS = match.getString("Kladionica");
				}
				if (match.has("UG1P2+") && match.getDouble("UG1P2+") > maxUG1P2_PLUS) {
					maxUG1P2_PLUS = match.getDouble("UG1P2+");
					Kladionica_UG1P2_PLUS = match.getString("Kladionica");
				}
				if (match.has("UG1P3+") && match.getDouble("UG1P3+") > maxUG1P3_PLUS) {
					maxUG1P3_PLUS = match.getDouble("UG1P3+");
					Kladionica_UG1P3_PLUS = match.getString("Kladionica");
				}
				if (match.has("UG1P0-1") && match.getDouble("UG1P0-1") > maxUG1P0_1) {
					maxUG1P0_1 = match.getDouble("UG1P0-1");
					Kladionica_UG1P0_1 = match.getString("Kladionica");
				}
				if (match.has("UG1P0-2") && match.getDouble("UG1P0-2") > maxUG1P0_2) {
					maxUG1P0_2 = match.getDouble("UG1P0-2");
					Kladionica_UG1P0_2 = match.getString("Kladionica");
				}
				if (match.has("UG2P1+") && match.getDouble("UG2P1+") > maxUG2P1_PLUS) {
					maxUG2P1_PLUS = match.getDouble("UG2P1+");
					Kladionica_UG2P1_PLUS = match.getString("Kladionica");
				}
				if (match.has("UG2P2+") && match.getDouble("UG2P2+") > maxUG2P2_PLUS) {
					maxUG2P2_PLUS = match.getDouble("UG2P2+");
					Kladionica_UG2P2_PLUS = match.getString("Kladionica");
				}
				if (match.has("UG2P3+") && match.getDouble("UG2P3+") > maxUG2P3_PLUS) {
					maxUG2P3_PLUS = match.getDouble("UG2P3+");
					Kladionica_UG2P3_PLUS = match.getString("Kladionica");
				}
				if (match.has("UG2P0-1") && match.getDouble("UG2P0-1") > maxUG2P0_1) {
					maxUG2P0_1 = match.getDouble("UG2P0-1");
					Kladionica_UG2P0_1 = match.getString("Kladionica");
				}
				if (match.has("UG2P0-2") && match.getDouble("UG2P0-2") > maxUG2P0_2) {
					maxUG2P0_2 = match.getDouble("UG2P0-2");
					Kladionica_UG2P0_2 = match.getString("Kladionica");
				}
				if (match.has("D2+") && match.getDouble("D2+") > maxD2_PLUS) {
					maxD2_PLUS = match.getDouble("D2+");
					Kladionica_D2_PLUS = match.getString("Kladionica");
				}
				if (match.has("D0-2") && match.getDouble("D0-2") > maxD0_2) {
					maxD0_2 = match.getDouble("D0-2");
					Kladionica_D0_2 = match.getString("Kladionica");
				}
				if (match.has("D3+") && match.getDouble("D3+") > maxD3_PLUS) {
					maxD3_PLUS = match.getDouble("D3+");
					Kladionica_D3_PLUS = match.getString("Kladionica");
				}
				if (match.has("G2+") && match.getDouble("G2+") > maxG2_PLUS) {
					maxG2_PLUS = match.getDouble("G2+");
					Kladionica_G2_PLUS = match.getString("Kladionica");
				}
				if (match.has("G0-2") && match.getDouble("G0-2") > maxG0_2) {
					maxG0_2 = match.getDouble("G0-2");
					Kladionica_G0_2 = match.getString("Kladionica");
				}
				if (match.has("G3+") && match.getDouble("G3+") > maxG3_PLUS) {
					maxG3_PLUS = match.getDouble("G3+");
					Kladionica_G3_PLUS = match.getString("Kladionica");
				}
			}
			
			brojUtakmica++;
			
			//Racunanje profita po kvotama
			
			/*z = x * max;
			y = z / max;
			double Profit = z - (x + y);
			double Procenat = (Profit / (x + y)) * 100;
			brojObradjenihObjekata++;*/
			
			double x = 1000;
			double z = x * maxGG;
			double y = z / maxNG;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxW1;
			y = z / maxW2;
			double Profit2 = z - (x + y);
			double Procenat2 = (Profit2 / (x + y)) * 100;
			brojObradjenihObjekata++;
			
			z = x * maxGG1;
			y = z / maxNG1;
			double Profit3 = z - (x + y);
			double Procenat3 = (Profit3 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxGG2;
			y = z / maxNG2;
			double Profit4 = z - (x + y);
			double Procenat4 = (Profit4 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * max1PW1;
			y = z / max1PW2;
			double Profit5 = z - (x + y);
			double Procenat5 = (Profit5 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * max2PW1;
			y = z / max2PW2;
			double Profit6 = z - (x + y);
			double Procenat6 = (Profit6 / (x + y)) * 100;
			brojObradjenihObjekata++;
			
			z = x * max1X;
			y = z / maxX2;
			double Profit7 = z - (x + y);
			double Procenat7 = (Profit7 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxUG1P1_PLUS;
			y = z / maxUG1P0_1;
			double Profit8 = z - (x + y);
			double Procenat8 = (Profit8 / (x + y)) * 100;
			brojObradjenihObjekata++;
			
			z = x * maxUG1P0_1;
			y = z / maxUG1P2_PLUS;
			double Profit9 = z - (x + y);
			double Procenat9 = (Profit9 / (x + y)) * 100;
			brojObradjenihObjekata++;
			
			z = x * maxUG1P2_PLUS;
			y = z / maxUG1P0_2;
			double Profit10 = z - (x + y);
			double Procenat10 = (Profit10 / (x + y)) * 100;
			brojObradjenihObjekata++;
			
			z = x * maxUG1P0_2;
			y = z / maxUG1P3_PLUS;
			double Profit11 = z - (x + y);
			double Procenat11 = (Profit11 / (x + y)) * 100;
			brojObradjenihObjekata++;
			
			z = x * maxUG2P1_PLUS;
			y = z / maxUG2P0_1;
			double Profit12 = z - (x + y);
			double Procenat12 = (Profit12 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxUG2P0_1;
			y = z / maxUG2P2_PLUS;
			double Profit13 = z - (x + y);
			double Procenat13 = (Profit13 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxUG2P2_PLUS;
			y = z / maxUG2P0_2;
			double Profit14 = z - (x + y);
			double Procenat14 = (Profit14 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxUG2P0_2;
			y = z / maxUG2P3_PLUS;
			double Profit15 = z - (x + y);
			double Procenat15 = (Profit15 / (x + y)) * 100;
			brojObradjenihObjekata++;
			
			z = x * maxD2_PLUS;
			y = z / maxD0_2;
			double Profit16 = z - (x + y);
			double Procenat16 = (Profit16 / (x + y)) * 100;
			brojObradjenihObjekata++;
			
			z = x * maxD0_2;
			y = z / maxD3_PLUS;
			double Profit17 = z - (x + y);
			double Procenat17 = (Profit17 / (x + y)) * 100;
			brojObradjenihObjekata++;
			
			z = x * maxG2_PLUS;
			y = z / maxG0_2;
			double Profit18 = z - (x + y);
			double Procenat18 = (Profit18 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxG0_2;
			y = z / maxG3_PLUS;
			double Profit19 = z - (x + y);
			double Procenat19 = (Profit19 / (x + y)) * 100;
			brojObradjenihObjekata++;
			
			//System.out.println("Home: " + firstMatch.getString("home") + ", Away: " + firstMatch.getString("away") + ", Profit1: " + GG_NG_Profit + ", Profit2: " + W1_W2_Profit);
			
			//int[] slucaj = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
			/*if (Profit > 0) {
				JSONObject objekat = new JSONObject();
				objekat1.put("home", firstMatch.getString("home"));
				objekat1.put("away", firstMatch.getString("away"));
				objekat1.put("Start", firstMatch.getLong("Start"));
				objekat1.put("Liga", firstMatch.getString("Liga"));
				objekat1.put("kladionica1", Kladionica_);//
				objekat1.put("kladionica2", Kladionica_);//
				objekat1.put("bet1", "bet");//
				objekat1.put("bet2", "bet");//
				objekat1.put("odd1", max);//
				objekat1.put("odd2", max);//
				objekat1.put("Procenat", Procenat);//
				objekat1.put("Sport", "Fudbal");
				
				objekat1.put("Profit", Profit);//
				objekat1.put("Ulog1", x);
				objekat1.put("Ulog2", y);
				objekat1.put("Prolaz", z);
								
				profitabilneUtakmice++;
				utakmice.put(objekat1);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat1.toString());
			}*/
			
			if (Profit1 > 0) {				
				JSONObject gg_ng_objekat = new JSONObject();
				gg_ng_objekat.put("home", firstMatch.getString("home"));
				gg_ng_objekat.put("away", firstMatch.getString("away"));
				gg_ng_objekat.put("Start", firstMatch.getLong("Start"));
				gg_ng_objekat.put("Liga", firstMatch.getString("Liga"));
				gg_ng_objekat.put("kladionica1", ggKladionica);
				gg_ng_objekat.put("kladionica2", ngKladionica);
				gg_ng_objekat.put("bet1", "GG");
				gg_ng_objekat.put("bet2", "NG");
				gg_ng_objekat.put("odd1", maxGG);
				gg_ng_objekat.put("odd2", maxNG);
				gg_ng_objekat.put("Procenat", Procenat1);
				gg_ng_objekat.put("Sport", "Fudbal");

				gg_ng_objekat.put("Profit", Profit1);
				gg_ng_objekat.put("Ulog1", x);
				gg_ng_objekat.put("Ulog2", y);
				gg_ng_objekat.put("Prolaz", z);
				
				profitabilneUtakmice++;
				utakmice.put(gg_ng_objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, gg_ng_objekat.toString());
			}

			if (Profit2 > 0) {
				JSONObject w1_w2_objekat = new JSONObject();
				w1_w2_objekat.put("home", firstMatch.getString("home"));
				w1_w2_objekat.put("away", firstMatch.getString("away"));
				w1_w2_objekat.put("Start", firstMatch.getLong("Start"));
				w1_w2_objekat.put("Liga", firstMatch.getString("Liga"));
				w1_w2_objekat.put("kladionica1", w1Kladionica);
				w1_w2_objekat.put("kladionica2", w2Kladionica);
				w1_w2_objekat.put("bet1", "W1");
				w1_w2_objekat.put("bet2", "W2");
				w1_w2_objekat.put("odd1", maxW1);
				w1_w2_objekat.put("odd2", maxW2);
				w1_w2_objekat.put("Procenat", Procenat2);
				w1_w2_objekat.put("Sport", "Fudbal");

				w1_w2_objekat.put("Profit", Profit2);
				w1_w2_objekat.put("Ulog1", x);
				w1_w2_objekat.put("Ulog2", y);
				w1_w2_objekat.put("Prolaz", z);
				
				profitabilneUtakmice++;
				utakmice.put(w1_w2_objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, w1_w2_objekat.toString());
			}
			
			if (Profit3 > 0) {
				JSONObject gg1_ng1_objekat = new JSONObject();
				gg1_ng1_objekat.put("home", firstMatch.getString("home"));
				gg1_ng1_objekat.put("away", firstMatch.getString("away"));
				gg1_ng1_objekat.put("Start", firstMatch.getLong("Start"));
				gg1_ng1_objekat.put("Liga", firstMatch.getString("Liga"));
				gg1_ng1_objekat.put("kladionica1", gg1Kladionica);//
				gg1_ng1_objekat.put("kladionica2", ng1Kladionica);//
				gg1_ng1_objekat.put("bet1", "GG1");//
				gg1_ng1_objekat.put("bet2", "NG1");//
				gg1_ng1_objekat.put("odd1", maxGG1);//
				gg1_ng1_objekat.put("odd2", maxNG1);//
				gg1_ng1_objekat.put("Procenat", Procenat3);//
				gg1_ng1_objekat.put("Sport", "Fudbal");

				gg1_ng1_objekat.put("Profit", Profit3);//
				gg1_ng1_objekat.put("Ulog1", x);
				gg1_ng1_objekat.put("Ulog2", y);
				gg1_ng1_objekat.put("Prolaz", z);
				
				profitabilneUtakmice++;
				utakmice.put(gg1_ng1_objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, gg1_ng1_objekat.toString());
			}
			
			if (Profit4 > 0) {
				JSONObject gg2_ng2_objekat = new JSONObject();
				gg2_ng2_objekat.put("home", firstMatch.getString("home"));
				gg2_ng2_objekat.put("away", firstMatch.getString("away"));
				gg2_ng2_objekat.put("Start", firstMatch.getLong("Start"));
				gg2_ng2_objekat.put("Liga", firstMatch.getString("Liga"));
				gg2_ng2_objekat.put("kladionica1", gg2Kladionica);//
				gg2_ng2_objekat.put("kladionica2", ng2Kladionica);//
				gg2_ng2_objekat.put("bet1", "GG2");//
				gg2_ng2_objekat.put("bet2", "NG2");//
				gg2_ng2_objekat.put("odd1", maxGG2);//
				gg2_ng2_objekat.put("odd2", maxNG2);//
				gg2_ng2_objekat.put("Procenat", Procenat4);//
				gg2_ng2_objekat.put("Sport", "Fudbal");

				gg2_ng2_objekat.put("Profit", Profit4);//
				gg2_ng2_objekat.put("Ulog1", x);
				gg2_ng2_objekat.put("Ulog2", y);
				gg2_ng2_objekat.put("Prolaz", z);
				
				profitabilneUtakmice++;
				utakmice.put(gg2_ng2_objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, gg2_ng2_objekat.toString());
			}
			
			if (Profit5 > 0) {
				JSONObject objekat5 = new JSONObject();
				objekat5.put("home", firstMatch.getString("home"));
				objekat5.put("away", firstMatch.getString("away"));
				objekat5.put("Start", firstMatch.getLong("Start"));
				objekat5.put("Liga", firstMatch.getString("Liga"));
				objekat5.put("kladionica1", Kladionica_1PW1);//
				objekat5.put("kladionica2", Kladionica_1PW2);//
				objekat5.put("bet1", "1PW1");//
				objekat5.put("bet2", "1PW2");//
				objekat5.put("odd1", max1PW1);//
				objekat5.put("odd2", max1PW2);//
				objekat5.put("Procenat", Procenat5);//
				objekat5.put("Sport", "Fudbal");

				objekat5.put("Profit", Profit5);//
				objekat5.put("Ulog1", x);
				objekat5.put("Ulog2", y);
				objekat5.put("Prolaz", z);
				
				profitabilneUtakmice++;
				utakmice.put(objekat5);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat5.toString());
			}
			
			if (Profit6 > 0) {
				JSONObject objekat6 = new JSONObject();
				objekat6.put("home", firstMatch.getString("home"));
				objekat6.put("away", firstMatch.getString("away"));
				objekat6.put("Start", firstMatch.getLong("Start"));
				objekat6.put("Liga", firstMatch.getString("Liga"));
				objekat6.put("kladionica1", Kladionica_2PW1);//
				objekat6.put("kladionica2", Kladionica_2PW2);//
				objekat6.put("bet1", "2PW1");//
				objekat6.put("bet2", "2PW2");//
				objekat6.put("odd1", max2PW1);//
				objekat6.put("odd2", max2PW2);//
				objekat6.put("Procenat", Procenat6);//
				objekat6.put("Sport", "Fudbal");

				objekat6.put("Profit", Profit6);//
				objekat6.put("Ulog1", x);
				objekat6.put("Ulog2", y);
				objekat6.put("Prolaz", z);
				
				profitabilneUtakmice++;
				utakmice.put(objekat6);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat6.toString());
			}
			
			if (Profit7 > 0) {
				JSONObject objekat7 = new JSONObject();
				objekat7.put("home", firstMatch.getString("home"));
				objekat7.put("away", firstMatch.getString("away"));
				objekat7.put("Start", firstMatch.getLong("Start"));
				objekat7.put("Liga", firstMatch.getString("Liga"));
				objekat7.put("kladionica1", Kladionica_1X);//
				objekat7.put("kladionica2", Kladionica_X2);//
				objekat7.put("bet1", "1X");//
				objekat7.put("bet2", "X2");//
				objekat7.put("odd1", max1X);//
				objekat7.put("odd2", maxX2);//
				objekat7.put("Procenat", Procenat7);//
				objekat7.put("Sport", "Fudbal");

				objekat7.put("Profit", Profit7);//
				objekat7.put("Ulog1", x);
				objekat7.put("Ulog2", y);
				objekat7.put("Prolaz", z);
								
				profitabilneUtakmice++;
				utakmice.put(objekat7);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat7.toString());
			}
			
			if (Profit8 > 0) {
				JSONObject objekat8 = new JSONObject();
				objekat8.put("home", firstMatch.getString("home"));
				objekat8.put("away", firstMatch.getString("away"));
				objekat8.put("Start", firstMatch.getLong("Start"));
				objekat8.put("Liga", firstMatch.getString("Liga"));
				objekat8.put("kladionica1", Kladionica_UG1P1_PLUS);//
				objekat8.put("kladionica2", Kladionica_UG1P0_1);//
				objekat8.put("bet1", "UG1P1+");//
				objekat8.put("bet2", "UG1P0-1");//
				objekat8.put("odd1", maxUG1P1_PLUS);//
				objekat8.put("odd2", maxUG1P0_1);//
				objekat8.put("Procenat", Procenat8);//
				objekat8.put("Sport", "Fudbal");

				objekat8.put("Profit", Profit8);//
				objekat8.put("Ulog1", x);
				objekat8.put("Ulog2", y);
				objekat8.put("Prolaz", z);
								
				profitabilneUtakmice++;
				utakmice.put(objekat8);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat8.toString());
			}
			
			if (Profit9 > 0) {
				JSONObject objekat9 = new JSONObject();
				objekat9.put("home", firstMatch.getString("home"));
				objekat9.put("away", firstMatch.getString("away"));
				objekat9.put("Start", firstMatch.getLong("Start"));
				objekat9.put("Liga", firstMatch.getString("Liga"));
				objekat9.put("kladionica1", Kladionica_UG1P0_1);//
				objekat9.put("kladionica2", Kladionica_UG1P2_PLUS);//
				objekat9.put("bet1", "UG1P0_1");//
				objekat9.put("bet2", "UG1P2_PLUS");//
				objekat9.put("odd1", maxUG1P0_1);//
				objekat9.put("odd2", maxUG1P2_PLUS);//
				objekat9.put("Procenat", Procenat9);//
				objekat9.put("Sport", "Fudbal");

				objekat9.put("Profit", Profit9);//
				objekat9.put("Ulog1", x);
				objekat9.put("Ulog2", y);
				objekat9.put("Prolaz", z);
								
				profitabilneUtakmice++;
				utakmice.put(objekat9);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat9.toString());
			}
			
			if (Profit10 > 0) {
				JSONObject objekat10 = new JSONObject();
				objekat10.put("home", firstMatch.getString("home"));
				objekat10.put("away", firstMatch.getString("away"));
				objekat10.put("Start", firstMatch.getLong("Start"));
				objekat10.put("Liga", firstMatch.getString("Liga"));
				objekat10.put("kladionica1", Kladionica_UG1P2_PLUS);//
				objekat10.put("kladionica2", Kladionica_UG1P0_2);//
				objekat10.put("bet1", "UG1P2_PLUS");//
				objekat10.put("bet2", "UG1P0_2");//
				objekat10.put("odd1", maxUG1P2_PLUS);//
				objekat10.put("odd2", maxUG1P2_PLUS);//
				objekat10.put("Procenat", Procenat10);//
				objekat10.put("Sport", "Fudbal");

				objekat10.put("Profit", Profit10);//
				objekat10.put("Ulog1", x);
				objekat10.put("Ulog2", y);
				objekat10.put("Prolaz", z);
								
				profitabilneUtakmice++;
				utakmice.put(objekat10);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat10.toString());
			}
			
			if (Profit11 > 0) {
				JSONObject objekat11 = new JSONObject();
				objekat11.put("home", firstMatch.getString("home"));
				objekat11.put("away", firstMatch.getString("away"));
				objekat11.put("Start", firstMatch.getLong("Start"));
				objekat11.put("Liga", firstMatch.getString("Liga"));
				objekat11.put("kladionica1", Kladionica_UG1P0_2);//
				objekat11.put("kladionica2", Kladionica_UG1P3_PLUS);//
				objekat11.put("bet1", "UG1P0_2");//
				objekat11.put("bet2", "UG1P3+");//
				objekat11.put("odd1", maxUG1P0_2);//
				objekat11.put("odd2", maxUG1P3_PLUS);//
				objekat11.put("Procenat", Procenat11);//
				objekat11.put("Sport", "Fudbal");

				objekat11.put("Profit", Profit11);//
				objekat11.put("Ulog1", x);
				objekat11.put("Ulog2", y);
				objekat11.put("Prolaz", z);
								
				profitabilneUtakmice++;
				utakmice.put(objekat11);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat11.toString());
			}
			
			if (Profit12 > 0) {
				JSONObject objekat12 = new JSONObject();
				objekat12.put("home", firstMatch.getString("home"));
				objekat12.put("away", firstMatch.getString("away"));
				objekat12.put("Start", firstMatch.getLong("Start"));
				objekat12.put("Liga", firstMatch.getString("Liga"));
				objekat12.put("kladionica1", Kladionica_UG2P1_PLUS);//
				objekat12.put("kladionica2", Kladionica_UG2P0_1);//
				objekat12.put("bet1", "UG2P1+");//
				objekat12.put("bet2", "UG2P0-1");//
				objekat12.put("odd1", maxUG2P1_PLUS);//
				objekat12.put("odd2", maxUG2P0_1);//
				objekat12.put("Procenat", Procenat12);//
				objekat12.put("Sport", "Fudbal");

				objekat12.put("Profit", Profit12);//
				objekat12.put("Ulog1", x);
				objekat12.put("Ulog2", y);
				objekat12.put("Prolaz", z);
								
				profitabilneUtakmice++;
				utakmice.put(objekat12);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat12.toString());
			}

			if (Profit13 > 0) {
				JSONObject objekat13 = new JSONObject();
				objekat13.put("home", firstMatch.getString("home"));
				objekat13.put("away", firstMatch.getString("away"));
				objekat13.put("Start", firstMatch.getLong("Start"));
				objekat13.put("Liga", firstMatch.getString("Liga"));
				objekat13.put("kladionica1", Kladionica_UG2P0_1);//
				objekat13.put("kladionica2", Kladionica_UG2P2_PLUS);//
				objekat13.put("bet1", "UG2P0_1");//
				objekat13.put("bet2", "UG2P2_PLUS");//
				objekat13.put("odd1", maxUG2P0_1);//
				objekat13.put("odd2", maxUG2P2_PLUS);//
				objekat13.put("Procenat", Procenat13);//
				objekat13.put("Sport", "Fudbal");

				objekat13.put("Profit", Profit13);//
				objekat13.put("Ulog1", x);
				objekat13.put("Ulog2", y);
				objekat13.put("Prolaz", z);
								
				profitabilneUtakmice++;
				utakmice.put(objekat13);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat13.toString());
			}

			if (Profit14 > 0) {
				JSONObject objekat14 = new JSONObject();
				objekat14.put("home", firstMatch.getString("home"));
				objekat14.put("away", firstMatch.getString("away"));
				objekat14.put("Start", firstMatch.getLong("Start"));
				objekat14.put("Liga", firstMatch.getString("Liga"));
				objekat14.put("kladionica1", Kladionica_UG2P2_PLUS);//
				objekat14.put("kladionica2", Kladionica_UG2P0_2);//
				objekat14.put("bet1", "UG2P2_PLUS");//
				objekat14.put("bet2", "UG2P0_2");//
				objekat14.put("odd1", maxUG2P2_PLUS);//
				objekat14.put("odd2", maxUG2P2_PLUS);//
				objekat14.put("Procenat", Procenat14);//
				objekat14.put("Sport", "Fudbal");

				objekat14.put("Profit", Profit14);//
				objekat14.put("Ulog1", x);
				objekat14.put("Ulog2", y);
				objekat14.put("Prolaz", z);
								
				profitabilneUtakmice++;
				utakmice.put(objekat14);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat14.toString());
			}

			if (Profit15 > 0) {
				JSONObject objekat15 = new JSONObject();
				objekat15.put("home", firstMatch.getString("home"));
				objekat15.put("away", firstMatch.getString("away"));
				objekat15.put("Start", firstMatch.getLong("Start"));
				objekat15.put("Liga", firstMatch.getString("Liga"));
				objekat15.put("kladionica1", Kladionica_UG2P0_2);//
				objekat15.put("kladionica2", Kladionica_UG2P3_PLUS);//
				objekat15.put("bet1", "UG2P0_2");//
				objekat15.put("bet2", "UG2P3+");//
				objekat15.put("odd1", maxUG2P0_2);//
				objekat15.put("odd2", maxUG2P3_PLUS);//
				objekat15.put("Procenat", Procenat15);//
				objekat15.put("Sport", "Fudbal");

				objekat15.put("Profit", Profit15);//
				objekat15.put("Ulog1", x);
				objekat15.put("Ulog2", y);
				objekat15.put("Prolaz", z);
								
				profitabilneUtakmice++;
				utakmice.put(objekat15);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat15.toString());
			}
			if (Profit16 > 0) {
				JSONObject objekat16 = new JSONObject();
				objekat16.put("home", firstMatch.getString("home"));
				objekat16.put("away", firstMatch.getString("away"));
				objekat16.put("Start", firstMatch.getLong("Start"));
				objekat16.put("Liga", firstMatch.getString("Liga"));
				objekat16.put("kladionica1", Kladionica_D2_PLUS);//
				objekat16.put("kladionica2", Kladionica_D0_2);//
				objekat16.put("bet1", "D2+");//
				objekat16.put("bet2", "D0-2");//
				objekat16.put("odd1", maxD2_PLUS);//
				objekat16.put("odd2", maxD0_2);//
				objekat16.put("Procenat", Procenat16);//
				objekat16.put("Sport", "Fudbal");

				objekat16.put("Profit", Profit16);//
				objekat16.put("Ulog1", x);
				objekat16.put("Ulog2", y);
				objekat16.put("Prolaz", z);
								
				profitabilneUtakmice++;
				utakmice.put(objekat16);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat16.toString());
			}
			
			if (Profit17 > 0) {
				JSONObject objekat17 = new JSONObject();
				objekat17.put("home", firstMatch.getString("home"));
				objekat17.put("away", firstMatch.getString("away"));
				objekat17.put("Start", firstMatch.getLong("Start"));
				objekat17.put("Liga", firstMatch.getString("Liga"));
				objekat17.put("kladionica1", Kladionica_D0_2);//
				objekat17.put("kladionica2", Kladionica_D3_PLUS);//
				objekat17.put("bet1", "D0-2");//
				objekat17.put("bet2", "D3+");//
				objekat17.put("odd1", maxD0_2);//
				objekat17.put("odd2", maxD3_PLUS);//
				objekat17.put("Procenat", Procenat17);//
				objekat17.put("Sport", "Fudbal");

				objekat17.put("Profit", Profit17);//
				objekat17.put("Ulog1", x);
				objekat17.put("Ulog2", y);
				objekat17.put("Prolaz", z);
								
				profitabilneUtakmice++;
				utakmice.put(objekat17);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat17.toString());
			}
			
			if (Profit18 > 0) {
				JSONObject objekat18 = new JSONObject();
				objekat18.put("home", firstMatch.getString("home"));
				objekat18.put("away", firstMatch.getString("away"));
				objekat18.put("Start", firstMatch.getLong("Start"));
				objekat18.put("Liga", firstMatch.getString("Liga"));
				objekat18.put("kladionica1", Kladionica_G2_PLUS);//
				objekat18.put("kladionica2", Kladionica_G0_2);//
				objekat18.put("bet1", "G2+");//
				objekat18.put("bet2", "G0-2");//
				objekat18.put("odd1", maxG2_PLUS);//
				objekat18.put("odd2", maxG0_2);//
				objekat18.put("Procenat", Procenat18);//
				objekat18.put("Sport", "Fudbal");

				objekat18.put("Profit", Profit18);//
				objekat18.put("Ulog1", x);
				objekat18.put("Ulog2", y);
				objekat18.put("Prolaz", z);
								
				profitabilneUtakmice++;
				utakmice.put(objekat18);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat18.toString());
			}
			
			if (Profit19 > 0) {
				JSONObject objekat19 = new JSONObject();
				objekat19.put("home", firstMatch.getString("home"));
				objekat19.put("away", firstMatch.getString("away"));
				objekat19.put("Start", firstMatch.getLong("Start"));
				objekat19.put("Liga", firstMatch.getString("Liga"));
				objekat19.put("kladionica1", Kladionica_G3_PLUS);//
				objekat19.put("kladionica2", Kladionica_G0_2);//
				objekat19.put("bet1", "G0-2");//
				objekat19.put("bet2", "G3+");//
				objekat19.put("odd1", maxG3_PLUS);//
				objekat19.put("odd2", maxG0_2);//
				objekat19.put("Procenat", Procenat19);//
				objekat19.put("Sport", "Fudbal");

				objekat19.put("Profit", Profit19);//
				objekat19.put("Ulog1", x);
				objekat19.put("Ulog2", y);
				objekat19.put("Prolaz", z);
								
				profitabilneUtakmice++;
				utakmice.put(objekat19);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat19.toString());
			}
		}
		LocalDateTime now = LocalDateTime.now();
		
		System.out.println("---------------------------------------------------------------------------------------------------------------------");
		//System.out.println(utakmice.toString());
		System.out.println("Profitabilne utakmice: " + profitabilneUtakmice + ", Broj utakmica: " + brojUtakmica + ", Obradjen broj objekata: " + brojObradjenihObjekata);
		System.out.println("Vreme: " + now);
		System.out.println("---------------------------------------------------------------------------------------------------------------------");
		
		String[] recipientNumbers = App.konvertovaniBrojevi;
        //System.out.println(Arrays.toString(recipientNumbers)); // Prikaz brojeva
        WaSender waSender = new WaSender(recipientNumbers);
		boolean hasSafeMatch = false; // Za uslov 1

		for (int i = 0; i < utakmice.length(); i++) {
		    JSONObject utakmica = utakmice.getJSONObject(i);

		    if (utakmica.getDouble("Procenat") > 4 && !utakmica.has("Rizik")) {
		        hasSafeMatch = true; 
		    }
		}

		if (hasSafeMatch) {
		    String message = "Sigurna utakmica sa 4 ili više procenata profita. Proveri litu na sajtu http://arbitrawin.com/kvote.html";
		    waSender.sendWhatsAppMessage(message);
		}
	}
}