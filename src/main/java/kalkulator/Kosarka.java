package kalkulator;

import kosarka.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.arbet3.arbet3.App;

import org.json.JSONArray;
import org.json.JSONObject;

import Fudbal.pozoviAPI;

public class Kosarka {

	String apiUrl = "https://x8ki-letl-twmt.n7.xano.io/api:etI0bpsP/obradjene_tekme";

	public static void main(String[] args) {
		Kosarka kosarka = new Kosarka();
		kosarka.izvrsiAnalizu();
	}

	public void izvrsiAnalizu() {
		JSONArray maxBetArray = MaxbetK.praviAPI();
		JSONArray oktagonBetArray = OktagonBetK.praviAPI();
		JSONArray merkurArray = MerkurK.praviAPI();
		JSONArray soccerBetArray = SoccerBetK.praviAPI();
		JSONArray betOleArray = BetOleK.praviAPI();

		List<JSONObject> combinedList = new ArrayList<>();
		combinedList.addAll(jsonArrayToList(maxBetArray));
		combinedList.addAll(jsonArrayToList(oktagonBetArray));
		combinedList.addAll(jsonArrayToList(merkurArray));
		combinedList.addAll(jsonArrayToList(soccerBetArray));
		combinedList.addAll(jsonArrayToList(betOleArray));

		List<List<JSONObject>> groupedMatchesSve = combinedList.stream()
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

		// String home = new String();
		// String away = new String();

		for (List<JSONObject> matchGroup : groupedMatchesSve) {
			JSONObject firstMatch = matchGroup.get(0);
			// home = firstMatch.getString("home");
			/// away = firstMatch.getString("away");

			double maxFTOT1 = 1.0;
			String Kladionica_FTOT1 = "";
			double maxFTOT2 = 1.0;
			String Kladionica_FTOT2 = "";

			double max1X = 1.0;
			String Kladionica_1X = "";
			double maxX2 = 1.0;
			String Kladionica_X2 = "";

			double maxP1X = 1.0;
			String Kladionica_P1X = "";
			double maxPX2 = 1.0;
			String Kladionica_PX2 = "";

			double max2P1X = 1.0;
			String Kladionica_2P1X = "";
			double max2PX2 = 1.0;
			String Kladionica_2PX2 = "";

			double max1PW1 = 1.0;
			String Kladionica_1PW1 = "";
			double max1PW2 = 1.0;
			String Kladionica_1PW2 = "";

			double max2PW1 = 1.0;
			String Kladionica_2PW1 = "";
			double max2PW2 = 1.0;
			String Kladionica_2PW2 = "";

			double maxQ1W1 = 1.0;
			String Kladionica_Q1W1 = "";
			double maxQ1W2 = 1.0;
			String Kladionica_Q1W2 = "";

			double maxQ2W1 = 1.0;
			String Kladionica_Q2W1 = "";
			double maxQ2W2 = 1.0;
			String Kladionica_Q2W2 = "";

			double maxQ3W1 = 1.0;
			String Kladionica_Q3W1 = "";
			double maxQ3W2 = 1.0;
			String Kladionica_Q3W2 = "";

			double maxQ4W1 = 1.0;
			String Kladionica_Q4W1 = "";
			double maxQ4W2 = 1.0;
			String Kladionica_Q4W2 = "";

			double maxQ11X = 1.0;
			String Kladionica_Q11X = "";
			double maxQ1X2 = 1.0;
			String Kladionica_Q1X2 = "";

			double maxQ21X = 1.0;
			String Kladionica_Q21X = "";
			double maxQ2X2 = 1.0;
			String Kladionica_Q2X2 = "";

			double maxQ31X = 1.0;
			String Kladionica_Q31X = "";
			double maxQ3X2 = 1.0;
			String Kladionica_Q3X2 = "";

			double maxQ41X = 1.0;
			String Kladionica_Q41X = "";
			double maxQ4X2 = 1.0;
			String Kladionica_Q4X2 = "";

			double maxPAROT = 1.0;
			String Kladionica_PAROT = "";
			double maxNEPAROT = 1.0;
			String Kladionica_NEPAROT = "";

			double maxPPR = 1.0;
			String Kladionica_PPR = "";
			double maxPNP = 1.0;
			String Kladionica_PNP = "";

			double max2PPAR = 1.0;
			String Kladionica_2PPAR = "";
			double max2PNEPAR = 1.0;
			String Kladionica_2PNEPAR = "";

			for (JSONObject match : matchGroup) {
				if (match.has("2PPAR") && match.getDouble("2PPAR") > max2PPAR) {
					max2PPAR = match.getDouble("2PPAR");
					Kladionica_2PPAR = match.getString("Kladionica");
				}
				if (match.has("2PNEPAR ") && match.getDouble("2PNEPAR ") > max2PNEPAR) {
					max2PNEPAR = match.getDouble("2PNEPAR ");
					Kladionica_2PNEPAR = match.getString("Kladionica");
				}
				if (match.has("PPR") && match.getDouble("PPR") > maxPPR) {
					maxPPR = match.getDouble("PPR");
					Kladionica_PPR = match.getString("Kladionica");
				}
				if (match.has("PNP") && match.getDouble("PNP") > maxPNP) {
					maxPNP = match.getDouble("PNP");
					Kladionica_PNP = match.getString("Kladionica");
				}
				if (match.has("PAROT") && match.getDouble("PAROT") > maxPAROT) {
					maxPAROT = match.getDouble("PAROT");
					Kladionica_PAROT = match.getString("Kladionica");
				}
				if (match.has("NEPAROT") && match.getDouble("NEPAROT") > maxNEPAROT) {
					maxNEPAROT = match.getDouble("NEPAROT");
					Kladionica_NEPAROT = match.getString("Kladionica");
				}
				if (match.has("Q41X") && match.getDouble("Q41X") > maxQ41X) {
					maxQ41X = match.getDouble("Q41X");
					Kladionica_Q41X = match.getString("Kladionica");
				}
				if (match.has("Q4X2") && match.getDouble("Q4X2") > maxQ4X2) {
					maxQ4X2 = match.getDouble("Q4X2");
					Kladionica_Q4X2 = match.getString("Kladionica");
				}
				if (match.has("Q31X") && match.getDouble("Q31X") > maxQ31X) {
					maxQ31X = match.getDouble("Q31X");
					Kladionica_Q31X = match.getString("Kladionica");
				}
				if (match.has("Q3X2") && match.getDouble("Q3X2") > maxQ3X2) {
					maxQ3X2 = match.getDouble("Q3X2");
					Kladionica_Q3X2 = match.getString("Kladionica");
				}
				if (match.has("Q21X") && match.getDouble("Q21X") > maxQ21X) {
					maxQ21X = match.getDouble("Q21X");
					Kladionica_Q21X = match.getString("Kladionica");
				}
				if (match.has("Q2X2") && match.getDouble("Q2X2") > maxQ2X2) {
					maxQ2X2 = match.getDouble("Q2X2");
					Kladionica_Q2X2 = match.getString("Kladionica");
				}
				if (match.has("Q11X") && match.getDouble("Q11X") > maxQ11X) {
					maxQ11X = match.getDouble("Q11X");
					Kladionica_Q11X = match.getString("Kladionica");
				}
				if (match.has("Q1X2") && match.getDouble("Q1X2") > maxQ1X2) {
					maxQ1X2 = match.getDouble("Q1X2");
					Kladionica_Q1X2 = match.getString("Kladionica");
				}
				if (match.has("Q4W1") && match.getDouble("Q4W1") > maxQ4W1) {
					maxQ4W1 = match.getDouble("Q4W1");
					Kladionica_Q4W1 = match.getString("Kladionica");
				}
				if (match.has("Q4W2") && match.getDouble("Q4W2") > maxQ4W2) {
					maxQ4W2 = match.getDouble("Q4W2");
					Kladionica_Q4W2 = match.getString("Kladionica");
				}
				if (match.has("Q3W1") && match.getDouble("Q3W1") > maxQ3W1) {
					maxQ3W1 = match.getDouble("Q3W1");
					Kladionica_Q3W1 = match.getString("Kladionica");
				}
				if (match.has("Q3W2") && match.getDouble("Q3W2") > maxQ3W2) {
					maxQ3W2 = match.getDouble("Q3W2");
					Kladionica_Q3W2 = match.getString("Kladionica");
				}
				if (match.has("Q2W1") && match.getDouble("Q2W1") > maxQ2W1) {
					maxQ2W1 = match.getDouble("Q2W1");
					Kladionica_Q2W1 = match.getString("Kladionica");
				}
				if (match.has("Q2W2") && match.getDouble("Q2W2") > maxQ2W2) {
					maxQ2W2 = match.getDouble("Q2W2");
					Kladionica_Q2W2 = match.getString("Kladionica");
				}
				if (match.has("Q1W1") && match.getDouble("Q1W1") > maxQ1W1) {
					maxQ1W1 = match.getDouble("Q1W1");
					Kladionica_Q1W1 = match.getString("Kladionica");
				}
				if (match.has("Q1W2") && match.getDouble("Q1W2") > maxQ1W2) {
					maxQ1W2 = match.getDouble("Q1W2");
					Kladionica_Q1W2 = match.getString("Kladionica");
				}
				if (match.has("2PW1") && match.getDouble("2PW1") > max2PW1) {
					max2PW1 = match.getDouble("2PW1");
					Kladionica_2PW1 = match.getString("Kladionica");
				}
				if (match.has("2PW2") && match.getDouble("2PW2") > max2PW2) {
					max2PW2 = match.getDouble("2PW2");
					Kladionica_2PW2 = match.getString("Kladionica");
				}
				if (match.has("1PW1") && match.getDouble("1PW1") > max1PW1) {
					max1PW1 = match.getDouble("1PW1");
					Kladionica_1PW1 = match.getString("Kladionica");
				}
				if (match.has("1PW2") && match.getDouble("1PW2") > max1PW2) {
					max1PW2 = match.getDouble("1PW2");
					Kladionica_1PW2 = match.getString("Kladionica");
				}
				if (match.has("FTOT1") && match.getDouble("FTOT1") > maxFTOT1) {
					maxFTOT1 = match.getDouble("FTOT1");
					Kladionica_FTOT1 = match.getString("Kladionica");
				}
				if (match.has("FTOT2") && match.getDouble("FTOT2") > maxFTOT2) {
					maxFTOT2 = match.getDouble("FTOT2");
					Kladionica_FTOT2 = match.getString("Kladionica");
				}
				if (match.has("1X") && match.getDouble("1X") > max1X) {
					max1X = match.getDouble("1X");
					Kladionica_1X = match.getString("Kladionica");
				}
				if (match.has("X2") && match.getDouble("X2") > maxX2) {
					maxX2 = match.getDouble("X2");
					Kladionica_X2 = match.getString("Kladionica");
				}
				if (match.has("P1X") && match.getDouble("P1X") > maxP1X) {
					maxP1X = match.getDouble("P1X");
					Kladionica_P1X = match.getString("Kladionica");
				}
				if (match.has("PX2") && match.getDouble("PX2") > maxPX2) {
					maxPX2 = match.getDouble("PX2");
					Kladionica_PX2 = match.getString("Kladionica");
				}
				if (match.has("2P1X") && match.getDouble("2P1X") > max2P1X) {
					max2P1X = match.getDouble("2P1X");
					Kladionica_2P1X = match.getString("Kladionica");
				}
				if (match.has("2PX2") && match.getDouble("2PX2") > max2PX2) {
					max2PX2 = match.getDouble("2PX2");
					Kladionica_2PX2 = match.getString("Kladionica");
				}
			}
			/*
			 System.out.println("KladionicaFTOT1: " + Kladionica_FTOT1 + " FTOT1: " +
			 maxFTOT1 + " Kladionica2: " + Kladionica_FTOT2 + " FTOT2" + maxFTOT2);
			 System.out.println("Utakmica: " + home + "//" + away); System.out.println(
			 "---------------------------------------------------------------------");
			 */

			brojUtakmica++;

			double x = 1000;
			double z = x * maxFTOT1;
			double y = z / maxFTOT2;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * max1X;
			y = z / maxX2;
			double Profit2 = z - (x + y);
			double Procenat2 = (Profit2 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxP1X;
			y = z / maxPX2;
			double Profit3 = z - (x + y);
			double Procenat3 = (Profit3 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * max2P1X;
			y = z / max2PX2;
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

			z = x * maxQ1W1;
			y = z / maxQ1W2;
			double Profit7 = z - (x + y);
			double Procenat7 = (Profit7 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxQ2W1;
			y = z / maxQ2W2;
			double Profit8 = z - (x + y);
			double Procenat8 = (Profit8 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxQ3W1;
			y = z / maxQ3W2;
			double Profit9 = z - (x + y);
			double Procenat9 = (Profit9 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxQ4W1;
			y = z / maxQ4W2;
			double Profit10 = z - (x + y);
			double Procenat10 = (Profit10 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxQ11X;
			y = z / maxQ1X2;
			double Profit11 = z - (x + y);
			double Procenat11 = (Profit11 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxQ21X;
			y = z / maxQ2X2;
			double Profit12 = z - (x + y);
			double Procenat12 = (Profit12 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxQ31X;
			y = z / maxQ3X2;
			double Profit13 = z - (x + y);
			double Procenat13 = (Profit13 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxQ41X;
			y = z / maxQ4X2;
			double Profit14 = z - (x + y);
			double Procenat14 = (Profit14 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxPAROT;
			y = z / maxNEPAROT;
			double Profit15 = z - (x + y);
			double Procenat15 = (Profit15 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxPPR;
			y = z / maxPNP;
			double Profit16 = z - (x + y);
			double Procenat16 = (Profit16 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * max2PPAR;
			y = z / max2PNEPAR;
			double Profit17 = z - (x + y);
			double Procenat17 = (Profit17 / (x + y)) * 100;
			brojObradjenihObjekata++;

			if (Profit17 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_2PPAR);//
				objekat.put("kladionica2", Kladionica_2PNEPAR);//
				objekat.put("bet1", "2PPAR");//
				objekat.put("bet2", "2PNEPAR ");//
				objekat.put("odd1", max2PPAR);//
				objekat.put("odd2", max2PNEPAR);//
				objekat.put("Procenat", Procenat17);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit17);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit16 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_PPR);//
				objekat.put("kladionica2", Kladionica_PNP);//
				objekat.put("bet1", "PPR");//
				objekat.put("bet2", "PNP");//
				objekat.put("odd1", maxPPR);//
				objekat.put("odd2", maxPNP);//
				objekat.put("Procenat", Procenat16);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit16);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit15 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_PAROT);//
				objekat.put("kladionica2", Kladionica_NEPAROT);//
				objekat.put("bet1", "PAR_OT");//
				objekat.put("bet2", "NEPAR_OT");//
				objekat.put("odd1", maxPAROT);//
				objekat.put("odd2", maxNEPAROT);//
				objekat.put("Procenat", Procenat15);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit15);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit14 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_Q41X);//
				objekat.put("kladionica2", Kladionica_Q4X2);//
				objekat.put("bet1", "Q41X");//
				objekat.put("bet2", "Q4X2");//
				objekat.put("odd1", maxQ41X);//
				objekat.put("odd2", maxQ4X2);//
				objekat.put("Procenat", Procenat14);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit14);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit13 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_Q31X);//
				objekat.put("kladionica2", Kladionica_Q3X2);//
				objekat.put("bet1", "Q31X");//
				objekat.put("bet2", "Q3X2");//
				objekat.put("odd1", maxQ31X);//
				objekat.put("odd2", maxQ3X2);//
				objekat.put("Procenat", Procenat13);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit13);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit12 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_Q21X);//
				objekat.put("kladionica2", Kladionica_Q2X2);//
				objekat.put("bet1", "Q21X");//
				objekat.put("bet2", "Q2X2");//
				objekat.put("odd1", maxQ21X);//
				objekat.put("odd2", maxQ2X2);//
				objekat.put("Procenat", Procenat12);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit12);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit11 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_Q11X);//
				objekat.put("kladionica2", Kladionica_Q1X2);//
				objekat.put("bet1", "Q11X");//
				objekat.put("bet2", "Q1X2");//
				objekat.put("odd1", maxQ11X);//
				objekat.put("odd2", maxQ1X2);//
				objekat.put("Procenat", Procenat11);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit11);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit10 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_Q4W1);//
				objekat.put("kladionica2", Kladionica_Q4W2);//
				objekat.put("bet1", "Q4W1");//
				objekat.put("bet2", "Q4W2");//
				objekat.put("odd1", maxQ4W1);//
				objekat.put("odd2", maxQ4W2);//
				objekat.put("Procenat", Procenat10);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit10);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit9 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_Q3W1);//
				objekat.put("kladionica2", Kladionica_Q3W2);//
				objekat.put("bet1", "Q3W1");//
				objekat.put("bet2", "Q3W2");//
				objekat.put("odd1", maxQ3W1);//
				objekat.put("odd2", maxQ3W2);//
				objekat.put("Procenat", Procenat9);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit9);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit8 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_Q2W1);//
				objekat.put("kladionica2", Kladionica_Q2W2);//
				objekat.put("bet1", "Q2W1");//
				objekat.put("bet2", "Q2W2");//
				objekat.put("odd1", maxQ2W1);//
				objekat.put("odd2", maxQ2W2);//
				objekat.put("Procenat", Procenat8);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit8);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit7 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_Q1W1);//
				objekat.put("kladionica2", Kladionica_Q1W2);//
				objekat.put("bet1", "Q1W1");//
				objekat.put("bet2", "Q1W2");//
				objekat.put("odd1", maxQ1W1);//
				objekat.put("odd2", maxQ1W2);//
				objekat.put("Procenat", Procenat7);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit7);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit6 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_2PW1);//
				objekat.put("kladionica2", Kladionica_2PW2);//
				objekat.put("bet1", "2PW1");//
				objekat.put("bet2", "2PW2");//
				objekat.put("odd1", max2PW1);//
				objekat.put("odd2", max2PW2);//
				objekat.put("Procenat", Procenat6);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit6);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit5 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_1PW1);//
				objekat.put("kladionica2", Kladionica_1PW2);//
				objekat.put("bet1", "1PW1");//
				objekat.put("bet2", "1PW2");//
				objekat.put("odd1", max1PW1);//
				objekat.put("odd2", max1PW2);//
				objekat.put("Procenat", Procenat5);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit5);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit4 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_2P1X);//
				objekat.put("kladionica2", Kladionica_2PX2);//
				objekat.put("bet1", "2P1X");//
				objekat.put("bet2", "2PX2");//
				objekat.put("odd1", max2P1X);//
				objekat.put("odd2", max2PX2);//
				objekat.put("Procenat", Procenat4);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit4);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}

			if (Profit1 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_FTOT1);//
				objekat.put("kladionica2", Kladionica_FTOT2);//
				objekat.put("bet1", "FTOT1");//
				objekat.put("bet2", "FTOT2");//
				objekat.put("odd1", maxFTOT1);//
				objekat.put("odd2", maxFTOT2);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit2 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_1X);//
				objekat.put("kladionica2", Kladionica_X2);//
				objekat.put("bet1", "1X");//
				objekat.put("bet2", "X2");//
				objekat.put("odd1", max1X);//
				objekat.put("odd2", maxX2);//
				objekat.put("Procenat", Procenat2);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit2);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
			if (Profit3 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_P1X);//
				objekat.put("kladionica2", Kladionica_PX2);//
				objekat.put("bet1", "P1X");//
				objekat.put("bet2", "PX2");//
				objekat.put("odd1", maxP1X);//
				objekat.put("odd2", maxPX2);//
				objekat.put("Procenat", Procenat3);//
				objekat.put("Sport", "Košarka");

				objekat.put("Profit", Profit3);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}

		}

		////// HENDIKEP OT//////

		JSONArray hendikepUtakmiceOT = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 11; i++) {
				String handicapKey = "hendikepGr" + i;
				String hot1Key = "HOT" + i + "1";
				String hot2Key = "HOT" + i + "2";

				if (match.has(handicapKey) && match.has(hot1Key) && match.has(hot2Key)) {
					double handicapValue = match.getDouble(handicapKey);
					double hot1Value = match.getDouble(hot1Key);
					double hot2Value = match.getDouble(hot2Key);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("hendikepGr", handicapValue);
					handicapDetails.put("HOT1", hot1Value);
					handicapDetails.put("HOT2", hot2Value);

					hendikepUtakmiceOT.put(handicapDetails);
				}
			}
		}
			List<JSONObject> hendikepOT = new ArrayList<>();
			hendikepOT.addAll(jsonArrayToList(hendikepUtakmiceOT));

			List<List<JSONObject>> groupedMatchesHOT = hendikepOT.stream()
					.collect(
							Collectors.groupingBy(
									obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
											+ obj.getString("away") + "_" + obj.getDouble("hendikepGr"),
									Collectors.toList()))
					.values().stream()
					.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
					.collect(Collectors.toList());

			for (List<JSONObject> matchGroupHOT : groupedMatchesHOT) {
				JSONObject firstMatch = matchGroupHOT.get(0);
				double maxHOT1 = 1.0;
				String Kladionica_HOT1 = "";
				double maxHOT2 = 1.0;
				String Kladionica_HOT2 = "";

				// System.out.println("tekma " + groupedMatchesHOT);
				for (JSONObject matchHOT : matchGroupHOT) {
					if (matchHOT.has("HOT1") && matchHOT.getDouble("HOT1") > maxHOT1) {
						maxHOT1 = matchHOT.getDouble("HOT1");
						Kladionica_HOT1 = matchHOT.getString("Kladionica");
					}
					if (matchHOT.has("HOT2") && matchHOT.getDouble("HOT2") > maxHOT2) {
						maxHOT2 = matchHOT.getDouble("HOT2");
						Kladionica_HOT2 = matchHOT.getString("Kladionica");
					}
					// System.out.println("tekma " + matchHOT);
				}

				double x = 1000;
				double z = x * maxHOT1;
				double y = z / maxHOT2;
				double Profit1 = z - (x + y);
				double Procenat1 = (Profit1 / (x + y)) * 100;
				brojObradjenihObjekata++;

				if (Profit1 > 0) {
					JSONObject objekat = new JSONObject();
					objekat.put("home", firstMatch.getString("home"));
					objekat.put("away", firstMatch.getString("away"));
					objekat.put("Start", firstMatch.getLong("Start"));
					objekat.put("Liga", firstMatch.getString("Liga"));
					objekat.put("kladionica1", Kladionica_HOT1);//
					objekat.put("kladionica2", Kladionica_HOT2);//
					objekat.put("bet1", "HOT1");//
					objekat.put("bet2", "HOT2");//
					objekat.put("odd1", maxHOT1);//
					objekat.put("odd2", maxHOT2);//
					objekat.put("Procenat", Procenat1);//
					objekat.put("Sport", "Košarka");
					objekat.put("Granica", firstMatch.getDouble("hendikepGr"));
					objekat.put("Rizik", "true");

					objekat.put("Profit", Profit1);//
					objekat.put("Ulog1", x);
					objekat.put("Ulog2", y);
					objekat.put("Prolaz", z);

					profitabilneUtakmice++;
					utakmice.put(objekat);
					pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
				}

			}

		
		// System.out.println(hendikepUtakmiceOT);

		////// HENDIKEP PRVO POLUVREME////////

		JSONArray hendikepUtakmicePH = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 2; i++) {
				String handicapKey = "hendikepPrvoPoluvremeGr" + i;
				String PH1Key = "PH" + i + "1";
				String PH2Key = "PH" + i + "2";

				if (match.has(handicapKey) && match.has(PH1Key) && match.has(PH2Key)) {
					double handicapValue = match.getDouble(handicapKey);
					double PH1Value = match.getDouble(PH1Key);
					double PH2Value = match.getDouble(PH2Key);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("hendikepGr", handicapValue);
					handicapDetails.put("PH1", PH1Value);
					handicapDetails.put("PH2", PH2Value);

					hendikepUtakmicePH.put(handicapDetails);
				}
			}
		}
			List<JSONObject> hendikepPH = new ArrayList<>();
			hendikepPH.addAll(jsonArrayToList(hendikepUtakmicePH));

			List<List<JSONObject>> groupedMatchesPH = hendikepPH.stream()
					.collect(
							Collectors.groupingBy(
									obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
											+ obj.getString("away") + "_" + obj.getDouble("hendikepGr"),
									Collectors.toList()))
					.values().stream()
					.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
					.collect(Collectors.toList());

			for (List<JSONObject> matchGroupPH : groupedMatchesPH) {
				JSONObject firstMatch = matchGroupPH.get(0);
				double maxPH1 = 1.0;
				String Kladionica_PH1 = "";
				double maxPH2 = 1.0;
				String Kladionica_PH2 = "";

				// System.out.println("tekma " + groupedMatchesPH);
				for (JSONObject matchPH : matchGroupPH) {
					if (matchPH.has("PH1") && matchPH.getDouble("PH1") > maxPH1) {
						maxPH1 = matchPH.getDouble("PH1");
						Kladionica_PH1 = matchPH.getString("Kladionica");
					}
					if (matchPH.has("PH2") && matchPH.getDouble("PH2") > maxPH2) {
						maxPH2 = matchPH.getDouble("PH2");
						Kladionica_PH2 = matchPH.getString("Kladionica");
					}
					// System.out.println("tekma " + matchPH);
				}

				double x = 1000;
				double z = x * maxPH1;
				double y = z / maxPH2;
				double Profit1 = z - (x + y);
				double Procenat1 = (Profit1 / (x + y)) * 100;
				brojObradjenihObjekata++;

				if (Profit1 > 0) {
					JSONObject objekat = new JSONObject();
					objekat.put("home", firstMatch.getString("home"));
					objekat.put("away", firstMatch.getString("away"));
					objekat.put("Start", firstMatch.getLong("Start"));
					objekat.put("Liga", firstMatch.getString("Liga"));
					objekat.put("kladionica1", Kladionica_PH1);//
					objekat.put("kladionica2", Kladionica_PH2);//
					objekat.put("bet1", "PH1");//
					objekat.put("bet2", "PH2");//
					objekat.put("odd1", maxPH1);//
					objekat.put("odd2", maxPH2);//
					objekat.put("Procenat", Procenat1);//
					objekat.put("Sport", "Košarka");
					objekat.put("Granica", firstMatch.getDouble("hendikepGr"));
					objekat.put("Rizik", "true");

					objekat.put("Profit", Profit1);//
					objekat.put("Ulog1", x);
					objekat.put("Ulog2", y);
					objekat.put("Prolaz", z);

					profitabilneUtakmice++;
					utakmice.put(objekat);
					pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
				}
			}


		////// HENDIKEP DRUGO POLUVREME//////

		JSONArray hendikepUtakmice2PH = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 2; i++) {
				String handicapKey = "hendikepDrugoPoluvremeGr" + i;
				String DVA_PH1Key = "2PH" + i + "1";
				String DVA_PH2Key = "2PH" + i + "2";

				if (match.has(handicapKey) && match.has(DVA_PH1Key) && match.has(DVA_PH2Key)) {
					double handicapValue = match.getDouble(handicapKey);
					double DVA_PH1Value = match.getDouble(DVA_PH1Key);
					double DVA_PH2Value = match.getDouble(DVA_PH2Key);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("hendikepGr", handicapValue);
					handicapDetails.put("2PH1", DVA_PH1Value);
					handicapDetails.put("2PH2", DVA_PH2Value);

					hendikepUtakmice2PH.put(handicapDetails);
				}
			}
		}
			List<JSONObject> hendikep2PH = new ArrayList<>();
			hendikep2PH.addAll(jsonArrayToList(hendikepUtakmice2PH));

			List<List<JSONObject>> groupedMatches2PH = hendikep2PH.stream()
					.collect(
							Collectors.groupingBy(
									obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
											+ obj.getString("away") + "_" + obj.getDouble("hendikepGr"),
									Collectors.toList()))
					.values().stream()
					.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
					.collect(Collectors.toList());

			for (List<JSONObject> matchGroup2PH : groupedMatches2PH) {
				JSONObject firstMatch = matchGroup2PH.get(0);
				double max2PH1 = 1.0;
				String Kladionica_2PH1 = "";
				double max2PH2 = 1.0;
				String Kladionica_2PH2 = "";

				// System.out.println("tekma " + groupedMatches2PH);
				for (JSONObject match2PH : matchGroup2PH) {
					if (match2PH.has("2PH1") && match2PH.getDouble("2PH1") > max2PH1) {
						max2PH1 = match2PH.getDouble("2PH1");
						Kladionica_2PH1 = match2PH.getString("Kladionica");
					}
					if (match2PH.has("2PH2") && match2PH.getDouble("2PH2") > max2PH2) {
						max2PH2 = match2PH.getDouble("2PH2");
						Kladionica_2PH2 = match2PH.getString("Kladionica");
					}
					// System.out.println("tekma " + match2PH);
				}

				double x = 1000;
				double z = x * max2PH1;
				double y = z / max2PH2;
				double Profit1 = z - (x + y);
				double Procenat1 = (Profit1 / (x + y)) * 100;
				brojObradjenihObjekata++;

				if (Profit1 > 0) {
					JSONObject objekat = new JSONObject();
					objekat.put("home", firstMatch.getString("home"));
					objekat.put("away", firstMatch.getString("away"));
					objekat.put("Start", firstMatch.getLong("Start"));
					objekat.put("Liga", firstMatch.getString("Liga"));
					objekat.put("kladionica1", Kladionica_2PH1);//
					objekat.put("kladionica2", Kladionica_2PH2);//
					objekat.put("bet1", "2PH1");//
					objekat.put("bet2", "2PH2");//
					objekat.put("odd1", max2PH1);//
					objekat.put("odd2", max2PH2);//
					objekat.put("Procenat", Procenat1);//
					objekat.put("Sport", "Košarka");
					objekat.put("Granica", firstMatch.getDouble("hendikepGr"));
					objekat.put("Rizik", "true");

					objekat.put("Profit", Profit1);//
					objekat.put("Ulog1", x);
					objekat.put("Ulog2", y);
					objekat.put("Prolaz", z);

					profitabilneUtakmice++;
					utakmice.put(objekat);
					pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
				}
			}
		

		////// HENDIKEP PRVA CETVRTINA//////

		JSONArray hendikepUtakmiceQ1H = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 2; i++) {
				String handicapKey = "hendikepDrugoPoluvremeGr" + i;
				String Q1H1Key = "Q1H" + i + "1";
				String Q1H2Key = "Q1H" + i + "2";

				if (match.has(handicapKey) && match.has(Q1H1Key) && match.has(Q1H2Key)) {
					double handicapValue = match.getDouble(handicapKey);
					double Q1H1Value = match.getDouble(Q1H1Key);
					double Q1H2Value = match.getDouble(Q1H2Key);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("hendikepGr", handicapValue);
					handicapDetails.put("Q1H1", Q1H1Value);
					handicapDetails.put("Q1H2", Q1H2Value);

					hendikepUtakmiceQ1H.put(handicapDetails);
				}
			}
		}
			List<JSONObject> hendikepQ1H = new ArrayList<>();
			hendikepQ1H.addAll(jsonArrayToList(hendikepUtakmiceQ1H));

			List<List<JSONObject>> groupedMatchesQ1H = hendikepQ1H.stream()
					.collect(
							Collectors.groupingBy(
									obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
											+ obj.getString("away") + "_" + obj.getDouble("hendikepGr"),
									Collectors.toList()))
					.values().stream()
					.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
					.collect(Collectors.toList());

			for (List<JSONObject> matchGroupQ1H : groupedMatchesQ1H) {
				JSONObject firstMatch = matchGroupQ1H.get(0);
				double maxQ1H1 = 1.0;
				String Kladionica_Q1H1 = "";
				double maxQ1H2 = 1.0;
				String Kladionica_Q1H2 = "";

				// System.out.println("tekma " + groupedMatchesQ1H);
				for (JSONObject matchQ1H : matchGroupQ1H) {
					if (matchQ1H.has("Q1H1") && matchQ1H.getDouble("Q1H1") > maxQ1H1) {
						maxQ1H1 = matchQ1H.getDouble("Q1H1");
						Kladionica_Q1H1 = matchQ1H.getString("Kladionica");
					}
					if (matchQ1H.has("Q1H2") && matchQ1H.getDouble("Q1H2") > maxQ1H2) {
						maxQ1H2 = matchQ1H.getDouble("Q1H2");
						Kladionica_Q1H2 = matchQ1H.getString("Kladionica");
					}
					// System.out.println("tekma " + matchQ1H);
				}

				double x = 1000;
				double z = x * maxQ1H1;
				double y = z / maxQ1H2;
				double Profit1 = z - (x + y);
				double Procenat1 = (Profit1 / (x + y)) * 100;
				brojObradjenihObjekata++;

				if (Profit1 > 0) {
					JSONObject objekat = new JSONObject();
					objekat.put("home", firstMatch.getString("home"));
					objekat.put("away", firstMatch.getString("away"));
					objekat.put("Start", firstMatch.getLong("Start"));
					objekat.put("Liga", firstMatch.getString("Liga"));
					objekat.put("kladionica1", Kladionica_Q1H1);//
					objekat.put("kladionica2", Kladionica_Q1H2);//
					objekat.put("bet1", "Q1H1");//
					objekat.put("bet2", "Q1H2");//
					objekat.put("odd1", maxQ1H1);//
					objekat.put("odd2", maxQ1H2);//
					objekat.put("Procenat", Procenat1);//
					objekat.put("Sport", "Košarka");
					objekat.put("Granica", firstMatch.getDouble("hendikepGr"));
					objekat.put("Rizik", "true");

					objekat.put("Profit", Profit1);//
					objekat.put("Ulog1", x);
					objekat.put("Ulog2", y);
					objekat.put("Prolaz", z);

					profitabilneUtakmice++;
					utakmice.put(objekat);
					pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
				}
			}
		

		////// HENDIKEP DRUGA CETVRTINA//////

		JSONArray hendikepUtakmiceQ2H = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 2; i++) {
				String handicapKey = "hendikepDrugPeriodGr" + i;
				String Q2H1Key = "Q2H" + i + "1";
				String Q2H2Key = "Q2H" + i + "2";

				if (match.has(handicapKey) && match.has(Q2H1Key) && match.has(Q2H2Key)) {
					double handicapValue = match.getDouble(handicapKey);
					double Q2H1Value = match.getDouble(Q2H1Key);
					double Q2H2Value = match.getDouble(Q2H2Key);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("hendikepGr", handicapValue);
					handicapDetails.put("Q2H1", Q2H1Value);
					handicapDetails.put("Q2H2", Q2H2Value);

					hendikepUtakmiceQ2H.put(handicapDetails);
				}
			}
		}
			List<JSONObject> hendikepQ2H = new ArrayList<>();
			hendikepQ2H.addAll(jsonArrayToList(hendikepUtakmiceQ2H));

			List<List<JSONObject>> groupedMatchesQ2H = hendikepQ2H.stream()
					.collect(
							Collectors.groupingBy(
									obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
											+ obj.getString("away") + "_" + obj.getDouble("hendikepGr"),
									Collectors.toList()))
					.values().stream()
					.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
					.collect(Collectors.toList());

			for (List<JSONObject> matchGroupQ2H : groupedMatchesQ2H) {
				JSONObject firstMatch = matchGroupQ2H.get(0);
				double maxQ2H1 = 1.0;
				String Kladionica_Q2H1 = "";
				double maxQ2H2 = 1.0;
				String Kladionica_Q2H2 = "";

				// System.out.println("tekma " + groupedMatchesQ2H);
				for (JSONObject matchQ2H : matchGroupQ2H) {
					if (matchQ2H.has("Q2H1") && matchQ2H.getDouble("Q2H1") > maxQ2H1) {
						maxQ2H1 = matchQ2H.getDouble("Q2H1");
						Kladionica_Q2H1 = matchQ2H.getString("Kladionica");
					}
					if (matchQ2H.has("Q2H2") && matchQ2H.getDouble("Q2H2") > maxQ2H2) {
						maxQ2H2 = matchQ2H.getDouble("Q2H2");
						Kladionica_Q2H2 = matchQ2H.getString("Kladionica");
					}
					// System.out.println("tekma " + matchQ2H);
				}

				double x = 1000;
				double z = x * maxQ2H1;
				double y = z / maxQ2H2;
				double Profit1 = z - (x + y);
				double Procenat1 = (Profit1 / (x + y)) * 100;
				brojObradjenihObjekata++;

				if (Profit1 > 0) {
					JSONObject objekat = new JSONObject();
					objekat.put("home", firstMatch.getString("home"));
					objekat.put("away", firstMatch.getString("away"));
					objekat.put("Start", firstMatch.getLong("Start"));
					objekat.put("Liga", firstMatch.getString("Liga"));
					objekat.put("kladionica1", Kladionica_Q2H1);//
					objekat.put("kladionica2", Kladionica_Q2H2);//
					objekat.put("bet1", "Q2H1");//
					objekat.put("bet2", "Q2H2");//
					objekat.put("odd1", maxQ2H1);//
					objekat.put("odd2", maxQ2H2);//
					objekat.put("Procenat", Procenat1);//
					objekat.put("Sport", "Košarka");
					objekat.put("Granica", firstMatch.getDouble("hendikepGr"));
					objekat.put("Rizik", "true");

					objekat.put("Profit", Profit1);//
					objekat.put("Ulog1", x);
					objekat.put("Ulog2", y);
					objekat.put("Prolaz", z);

					profitabilneUtakmice++;
					utakmice.put(objekat);
					pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
				}
			}
		

		////// HENDIKEP TRECA CETVRTINA//////

		JSONArray hendikepUtakmiceQ3H = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 2; i++) {
				String handicapKey = "hendikepTreciPeriodGr" + i;
				String Q3H1Key = "Q3H" + i + "1";
				String Q3H2Key = "Q3H" + i + "2";

				if (match.has(handicapKey) && match.has(Q3H1Key) && match.has(Q3H2Key)) {
					double handicapValue = match.getDouble(handicapKey);
					double Q3H1Value = match.getDouble(Q3H1Key);
					double Q3H2Value = match.getDouble(Q3H2Key);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("hendikepGr", handicapValue);
					handicapDetails.put("Q3H1", Q3H1Value);
					handicapDetails.put("Q3H2", Q3H2Value);

					hendikepUtakmiceQ3H.put(handicapDetails);
				}
			}
		}
			List<JSONObject> hendikepQ3H = new ArrayList<>();
			hendikepQ3H.addAll(jsonArrayToList(hendikepUtakmiceQ3H));

			List<List<JSONObject>> groupedMatchesQ3H = hendikepQ3H.stream()
					.collect(
							Collectors.groupingBy(
									obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
											+ obj.getString("away") + "_" + obj.getDouble("hendikepGr"),
									Collectors.toList()))
					.values().stream()
					.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
					.collect(Collectors.toList());

			for (List<JSONObject> matchGroupQ3H : groupedMatchesQ3H) {
				JSONObject firstMatch = matchGroupQ3H.get(0);
				double maxQ3H1 = 1.0;
				String Kladionica_Q3H1 = "";
				double maxQ3H2 = 1.0;
				String Kladionica_Q3H2 = "";

				// System.out.println("tekma " + groupedMatchesQ3H);
				for (JSONObject matchQ3H : matchGroupQ3H) {
					if (matchQ3H.has("Q3H1") && matchQ3H.getDouble("Q3H1") > maxQ3H1) {
						maxQ3H1 = matchQ3H.getDouble("Q3H1");
						Kladionica_Q3H1 = matchQ3H.getString("Kladionica");
					}
					if (matchQ3H.has("Q3H2") && matchQ3H.getDouble("Q3H2") > maxQ3H2) {
						maxQ3H2 = matchQ3H.getDouble("Q3H2");
						Kladionica_Q3H2 = matchQ3H.getString("Kladionica");
					}
					// System.out.println("tekma " + matchQ3H);
				}

				double x = 1000;
				double z = x * maxQ3H1;
				double y = z / maxQ3H2;
				double Profit1 = z - (x + y);
				double Procenat1 = (Profit1 / (x + y)) * 100;
				brojObradjenihObjekata++;

				if (Profit1 > 0) {
					JSONObject objekat = new JSONObject();
					objekat.put("home", firstMatch.getString("home"));
					objekat.put("away", firstMatch.getString("away"));
					objekat.put("Start", firstMatch.getLong("Start"));
					objekat.put("Liga", firstMatch.getString("Liga"));
					objekat.put("kladionica1", Kladionica_Q3H1);//
					objekat.put("kladionica2", Kladionica_Q3H2);//
					objekat.put("bet1", "Q3H1");//
					objekat.put("bet2", "Q3H2");//
					objekat.put("odd1", maxQ3H1);//
					objekat.put("odd2", maxQ3H2);//
					objekat.put("Procenat", Procenat1);//
					objekat.put("Sport", "Košarka");
					objekat.put("Granica", firstMatch.getDouble("hendikepGr"));
					objekat.put("Rizik", "true");

					objekat.put("Profit", Profit1);//
					objekat.put("Ulog1", x);
					objekat.put("Ulog2", y);
					objekat.put("Prolaz", z);

					profitabilneUtakmice++;
					utakmice.put(objekat);
					pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
				}
			}
		

		////// HENDIKEP CETVRTA CETVRTINA//////

		JSONArray hendikepUtakmiceQ4H = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 2; i++) {
				String handicapKey = "hendikepCetvrtiPeriodGr" + i;
				String Q4H1Key = "Q4H" + i + "1";
				String Q4H2Key = "Q4H" + i + "2";

				if (match.has(handicapKey) && match.has(Q4H1Key) && match.has(Q4H2Key)) {
					double handicapValue = match.getDouble(handicapKey);
					double Q4H1Value = match.getDouble(Q4H1Key);
					double Q4H2Value = match.getDouble(Q4H2Key);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("hendikepGr", handicapValue);
					handicapDetails.put("Q4H1", Q4H1Value);
					handicapDetails.put("Q4H2", Q4H2Value);

					hendikepUtakmiceQ4H.put(handicapDetails);
				}
			}
		}
			List<JSONObject> hendikepQ4H = new ArrayList<>();
			hendikepQ4H.addAll(jsonArrayToList(hendikepUtakmiceQ4H));

			List<List<JSONObject>> groupedMatchesQ4H = hendikepQ4H.stream()
					.collect(
							Collectors.groupingBy(
									obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
											+ obj.getString("away") + "_" + obj.getDouble("hendikepGr"),
									Collectors.toList()))
					.values().stream()
					.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
					.collect(Collectors.toList());

			for (List<JSONObject> matchGroupQ4H : groupedMatchesQ4H) {
				JSONObject firstMatch = matchGroupQ4H.get(0);
				double maxQ4H1 = 1.0;
				String Kladionica_Q4H1 = "";
				double maxQ4H2 = 1.0;
				String Kladionica_Q4H2 = "";

				// System.out.println("tekma " + groupedMatchesQ4H);
				for (JSONObject matchQ4H : matchGroupQ4H) {
					if (matchQ4H.has("Q4H1") && matchQ4H.getDouble("Q4H1") > maxQ4H1) {
						maxQ4H1 = matchQ4H.getDouble("Q4H1");
						Kladionica_Q4H1 = matchQ4H.getString("Kladionica");
					}
					if (matchQ4H.has("Q4H2") && matchQ4H.getDouble("Q4H2") > maxQ4H2) {
						maxQ4H2 = matchQ4H.getDouble("Q4H2");
						Kladionica_Q4H2 = matchQ4H.getString("Kladionica");
					}
					// System.out.println("tekma " + matchQ4H);
				}

				double x = 1000;
				double z = x * maxQ4H1;
				double y = z / maxQ4H2;
				double Profit1 = z - (x + y);
				double Procenat1 = (Profit1 / (x + y)) * 100;
				brojObradjenihObjekata++;

				if (Profit1 > 0) {
					JSONObject objekat = new JSONObject();
					objekat.put("home", firstMatch.getString("home"));
					objekat.put("away", firstMatch.getString("away"));
					objekat.put("Start", firstMatch.getLong("Start"));
					objekat.put("Liga", firstMatch.getString("Liga"));
					objekat.put("kladionica1", Kladionica_Q4H1);//
					objekat.put("kladionica2", Kladionica_Q4H2);//
					objekat.put("bet1", "Q4H1");//
					objekat.put("bet2", "Q4H2");//
					objekat.put("odd1", maxQ4H1);//
					objekat.put("odd2", maxQ4H2);//
					objekat.put("Procenat", Procenat1);//
					objekat.put("Sport", "Košarka");
					objekat.put("Granica", firstMatch.getDouble("hendikepGr"));
					objekat.put("Rizik", "true");

					objekat.put("Profit", Profit1);//
					objekat.put("Ulog1", x);
					objekat.put("Ulog2", y);
					objekat.put("Prolaz", z);

					profitabilneUtakmice++;
					utakmice.put(objekat);
					pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
				}
			}
		

		////// UKUPNO POENA OT//////

		JSONArray ukupnoPoenaUtakmice = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 7; i++) {
				String OTKey = "overUnderOvertime" + i;
				String OT_MINUSKey = "OT" + i + "_MINUS";
				String OT_PLUSKey = "OT" + i + "_PLUS";

				if (match.has(OTKey) && match.has(OT_MINUSKey) && match.has(OT_PLUSKey)) {
					double OTValue = match.getDouble(OTKey);
					double OT_MINUSValue = match.getDouble(OT_MINUSKey);
					double OT_PLUSValue = match.getDouble(OT_PLUSKey);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("UkupnoPoenaGr", OTValue);
					handicapDetails.put("OT_MINUS", OT_MINUSValue);
					handicapDetails.put("OT_PLUS", OT_PLUSValue);

					ukupnoPoenaUtakmice.put(handicapDetails);
				}
			}
		}
			List<JSONObject> ukupnoPoenaOT = new ArrayList<>();
			ukupnoPoenaOT.addAll(jsonArrayToList(ukupnoPoenaUtakmice));

			List<List<JSONObject>> groupedMatchesOT = ukupnoPoenaOT.stream()
					.collect(
							Collectors.groupingBy(
									obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
											+ obj.getString("away") + "_" + obj.getDouble("UkupnoPoenaGr"),
									Collectors.toList()))
					.values().stream()
					.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
					.collect(Collectors.toList());

			for (List<JSONObject> matchGroupOT : groupedMatchesOT) {
				JSONObject firstMatch = matchGroupOT.get(0);
				double maxOT_MINUS = 1.0;
				String Kladionica_OT_MINUS = "";
				double maxOT_PLUS = 1.0;
				String Kladionica_OT_PLUS = "";

				// System.out.println("tekma " + groupedMatchesQ4H);
				for (JSONObject matchOT : matchGroupOT) {
					if (matchOT.has("OT_MINUS") && matchOT.getDouble("OT_MINUS") > maxOT_MINUS) {
						maxOT_MINUS = matchOT.getDouble("OT_MINUS");
						Kladionica_OT_MINUS = matchOT.getString("Kladionica");
					}
					if (matchOT.has("OT_PLUS") && matchOT.getDouble("OT_PLUS") > maxOT_PLUS) {
						maxOT_PLUS = matchOT.getDouble("OT_PLUS");
						Kladionica_OT_PLUS = matchOT.getString("Kladionica");
					}
					// System.out.println("tekma " + matchQ4H);
				}

				double x = 1000;
				double z = x * maxOT_MINUS;
				double y = z / maxOT_PLUS;
				double Profit1 = z - (x + y);
				double Procenat1 = (Profit1 / (x + y)) * 100;
				brojObradjenihObjekata++;

				if (Profit1 > 0) {
					JSONObject objekat = new JSONObject();
					objekat.put("home", firstMatch.getString("home"));
					objekat.put("away", firstMatch.getString("away"));
					objekat.put("Start", firstMatch.getLong("Start"));
					objekat.put("Liga", firstMatch.getString("Liga"));
					objekat.put("kladionica1", Kladionica_OT_MINUS);//
					objekat.put("kladionica2", Kladionica_OT_PLUS);//
					objekat.put("bet1", "OT_MINUS");//
					objekat.put("bet2", "OT_PLUS");//
					objekat.put("odd1", maxOT_MINUS);//
					objekat.put("odd2", maxOT_PLUS);//
					objekat.put("Procenat", Procenat1);//
					objekat.put("Sport", "Košarka");
					objekat.put("Granica", firstMatch.getDouble("UkupnoPoenaGr"));

					objekat.put("Profit", Profit1);//
					objekat.put("Ulog1", x);
					objekat.put("Ulog2", y);
					objekat.put("Prolaz", z);

					profitabilneUtakmice++;
					utakmice.put(objekat);
					pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
				}
			}
		

		////// UKUPNO POENA 1P//////

		JSONArray ukupnoPoenaUtakmice1P = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 3; i++) {
				String JEDAN_PKey = "overUnderPrvoPoluvreme" + i;
				String JEDAN_P_MINUSKey = "1P" + i + "_MINUS";
				String JEDAN_P_PLUSKey = "1P" + i + "_PLUS";

				if (match.has(JEDAN_PKey) && match.has(JEDAN_P_MINUSKey) && match.has(JEDAN_P_PLUSKey)) {
					double JEDAN_PValue = match.getDouble(JEDAN_PKey);
					double JEDAN_P_MINUSValue = match.getDouble(JEDAN_P_MINUSKey);
					double JEDAN_P_PLUSValue = match.getDouble(JEDAN_P_PLUSKey);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("UkupnoPoenaGr", JEDAN_PValue);
					handicapDetails.put("1P_MINUS", JEDAN_P_MINUSValue);
					handicapDetails.put("1P_PLUS", JEDAN_P_PLUSValue);

					ukupnoPoenaUtakmice1P.put(handicapDetails);
				}
			}
		}
			List<JSONObject> ukupnoPoena1P = new ArrayList<>();
			ukupnoPoena1P.addAll(jsonArrayToList(ukupnoPoenaUtakmice1P));

			List<List<JSONObject>> groupedMatches1P = ukupnoPoena1P.stream()
					.collect(
							Collectors.groupingBy(
									obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
											+ obj.getString("away") + "_" + obj.getDouble("UkupnoPoenaGr"),
									Collectors.toList()))
					.values().stream()
					.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
					.collect(Collectors.toList());

			for (List<JSONObject> matchGroup1P : groupedMatches1P) {
				JSONObject firstMatch = matchGroup1P.get(0);
				double max1P_MINUS = 1.0;
				String Kladionica_1P_MINUS = "";
				double max1P_PLUS = 1.0;
				String Kladionica_1P_PLUS = "";

				// System.out.println("tekma " + groupedMatchesQ4H);
				for (JSONObject match1P : matchGroup1P) {
					if (match1P.has("1P_MINUS") && match1P.getDouble("1P_MINUS") > max1P_MINUS) {
						max1P_MINUS = match1P.getDouble("1P_MINUS");
						Kladionica_1P_MINUS = match1P.getString("Kladionica");
					}
					if (match1P.has("1P_PLUS") && match1P.getDouble("1P_PLUS") > max1P_PLUS) {
						max1P_PLUS = match1P.getDouble("1P_PLUS");
						Kladionica_1P_PLUS = match1P.getString("Kladionica");
					}
					// System.out.println("tekma " + matchQ4H);
				}

				double x = 1000;
				double z = x * max1P_MINUS;
				double y = z / max1P_PLUS;
				double Profit1 = z - (x + y);
				double Procenat1 = (Profit1 / (x + y)) * 100;
				brojObradjenihObjekata++;

				if (Profit1 > 0) {
					JSONObject objekat = new JSONObject();
					objekat.put("home", firstMatch.getString("home"));
					objekat.put("away", firstMatch.getString("away"));
					objekat.put("Start", firstMatch.getLong("Start"));
					objekat.put("Liga", firstMatch.getString("Liga"));
					objekat.put("kladionica1", Kladionica_1P_MINUS);//
					objekat.put("kladionica2", Kladionica_1P_PLUS);//
					objekat.put("bet1", "1P_MINUS");//
					objekat.put("bet2", "1P_PLUS");//
					objekat.put("odd1", max1P_MINUS);//
					objekat.put("odd2", max1P_PLUS);//
					objekat.put("Procenat", Procenat1);//
					objekat.put("Sport", "Košarka");
					objekat.put("Granica", firstMatch.getDouble("UkupnoPoenaGr"));

					objekat.put("Profit", Profit1);//
					objekat.put("Ulog1", x);
					objekat.put("Ulog2", y);
					objekat.put("Prolaz", z);

					profitabilneUtakmice++;
					utakmice.put(objekat);
					pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
				}
			}
		

	////// UKUPNO POENA 2P  -  NE RADI KAKO TREBA, 2P__MINUS, 2P_PLUS i GRANICA imaju vrednosti 1//////

			JSONArray ukupnoPoenaUtakmice2P = new JSONArray();
			for (JSONObject match : combinedList) {
				int i = 1;
				String home = match.getString("home");
				String away = match.getString("away");
				String kladionica = match.getString("Kladionica");
				long start = match.getLong("Start");
				String liga = match.getString("Liga");
				for (i = 1; i <= 3; i++) {
					String DVA_PKey = "overUnderDrugoPoluvreme" + i;
					String DVA_P_MINUSKey = "2P" + i + "_MINUS";
					String DVA_P_PLUSKey = "2P" + i + "_PLUS";

					if (match.has(DVA_PKey) && match.has(DVA_P_MINUSKey) && match.has(DVA_P_PLUSKey)) {
						double DVA_PValue = match.getDouble(DVA_PKey);
						double DVA_P_MINUSValue = match.getDouble(DVA_P_MINUSKey);
						double DVA_P_PLUSValue = match.getDouble(DVA_P_PLUSKey);

						JSONObject handicapDetails = new JSONObject();
						handicapDetails.put("home", home);
						handicapDetails.put("away", away);
						handicapDetails.put("Kladionica", kladionica);
						handicapDetails.put("Start", start);
						handicapDetails.put("Liga", liga);
						handicapDetails.put("UkupnoPoenaGr", DVA_PValue);
						handicapDetails.put("2P_MINUS", DVA_P_MINUSValue);
						handicapDetails.put("2P_PLUS", DVA_P_PLUSValue);

						ukupnoPoenaUtakmice2P.put(handicapDetails);
					}
				}
			}
				List<JSONObject> ukupnoPoena2P = new ArrayList<>();
				ukupnoPoena2P.addAll(jsonArrayToList(ukupnoPoenaUtakmice2P));

				List<List<JSONObject>> groupedMatches2P = ukupnoPoena2P.stream()
						.collect(
								Collectors.groupingBy(
										obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
												+ obj.getString("away") + "_" + obj.getDouble("UkupnoPoenaGr"),
										Collectors.toList()))
						.values().stream()
						.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
						.collect(Collectors.toList());

				for (List<JSONObject> matchGroup2P : groupedMatches2P) {
					JSONObject firstMatch = matchGroup2P.get(0);
					double max2P_MINUS = 1.0;
					String Kladionica_2P_MINUS = "";
					double max2P_PLUS = 1.0;
					String Kladionica_2P_PLUS = "";

					// System.out.println("tekma " + groupedMatchesQ4H);
					for (JSONObject match2P : matchGroup2P) {
						if (match2P.has("2P_MINUS") && match2P.getDouble("2P_MINUS") > max2P_MINUS) {
							max2P_MINUS = match2P.getDouble("2P_MINUS");
							Kladionica_2P_MINUS = match2P.getString("Kladionica");
						}
						if (match2P.has("2P_PLUS") && match2P.getDouble("2P_PLUS") > max2P_PLUS) {
							max2P_PLUS = match2P.getDouble("2P_PLUS");
							Kladionica_2P_PLUS = match2P.getString("Kladionica");
						}
						// System.out.println("tekma " + matchQ4H);
					}

					double x = 1000;
					double z = x * max2P_MINUS;
					double y = z / max2P_PLUS;
					double Profit1 = z - (x + y);
					double Procenat1 = (Profit1 / (x + y)) * 100;
					brojObradjenihObjekata++;

					if (Profit1 > 0) {
						JSONObject objekat = new JSONObject();
						objekat.put("home", firstMatch.getString("home"));
						objekat.put("away", firstMatch.getString("away"));
						objekat.put("Start", firstMatch.getLong("Start"));
						objekat.put("Liga", firstMatch.getString("Liga"));
						objekat.put("kladionica1", Kladionica_2P_MINUS);//
						objekat.put("kladionica2", Kladionica_2P_PLUS);//
						objekat.put("bet1", "2P_MINUS");//
						objekat.put("bet2", "2P_PLUS");//
						objekat.put("odd1", max2P_MINUS);//
						objekat.put("odd2", max2P_PLUS);//
						objekat.put("Procenat", Procenat1);//
						objekat.put("Sport", "Košarka");
						objekat.put("Granica", firstMatch.getDouble("UkupnoPoenaGr"));

						objekat.put("Profit", Profit1);//
						objekat.put("Ulog1", x);
						objekat.put("Ulog2", y);
						objekat.put("Prolaz", z);

						profitabilneUtakmice++;
						utakmice.put(objekat);
						pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
					}
				}
			
			
		////// UKUPNO POENA Q1  -  NE RADI KAKO TREBA, Q1_MINUS, 2P_PLUS i GRANICA imaju vrednosti 1//////

			JSONArray ukupnoPoenaUtakmiceQ1 = new JSONArray();
			for (JSONObject match : combinedList) {
				int i = 1;
				String home = match.getString("home");
				String away = match.getString("away");
				String kladionica = match.getString("Kladionica");
				long start = match.getLong("Start");
				String liga = match.getString("Liga");
				for (i = 1; i <= 3; i++) {
					String Q1Key = "overUnderPrviPeriod" + i;
					String Q1_MINUSKey = "Q1" + i + "_MINUS";
					String Q1_PLUSKey = "Q1" + i + "_PLUS";

					if (match.has(Q1Key) && match.has(Q1_MINUSKey) && match.has(Q1_PLUSKey)) {
						double Q1Value = match.getDouble(Q1Key);
						double Q1_MINUSValue = match.getDouble(Q1_MINUSKey);
						double Q1_PLUSValue = match.getDouble(Q1_PLUSKey);

						JSONObject handicapDetails = new JSONObject();
						handicapDetails.put("home", home);
						handicapDetails.put("away", away);
						handicapDetails.put("Kladionica", kladionica);
						handicapDetails.put("Start", start);
						handicapDetails.put("Liga", liga);
						handicapDetails.put("UkupnoPoenaGr", Q1Value);
						handicapDetails.put("Q1_MINUS", Q1_MINUSValue);
						handicapDetails.put("Q1_PLUS", Q1_PLUSValue);

						ukupnoPoenaUtakmiceQ1.put(handicapDetails);
					}
				}
			}
				List<JSONObject> ukupnoPoenaQ1 = new ArrayList<>();
				ukupnoPoenaQ1.addAll(jsonArrayToList(ukupnoPoenaUtakmiceQ1));

				List<List<JSONObject>> groupedMatchesQ1 = ukupnoPoenaQ1.stream()
						.collect(
								Collectors.groupingBy(
										obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
												+ obj.getString("away") + "_" + obj.getDouble("UkupnoPoenaGr"),
										Collectors.toList()))
						.values().stream()
						.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
						.collect(Collectors.toList());

				for (List<JSONObject> matchGroupQ1 : groupedMatchesQ1) {
					JSONObject firstMatch = matchGroupQ1.get(0);
					double maxQ1_MINUS = 1.0;
					String Kladionica_Q1_MINUS = "";
					double maxQ1_PLUS = 1.0;
					String Kladionica_Q1_PLUS = "";

					// System.out.println("tekma " + groupedMatchesQ4H);
					for (JSONObject matchQ1 : matchGroupQ1) {
						if (matchQ1.has("Q1_MINUS") && matchQ1.getDouble("Q1_MINUS") > maxQ1_MINUS) {
							maxQ1_MINUS = matchQ1.getDouble("Q1_MINUS");
							Kladionica_Q1_MINUS = matchQ1.getString("Kladionica");
						}
						if (matchQ1.has("Q1_PLUS") && matchQ1.getDouble("Q1_PLUS") > maxQ1_PLUS) {
							maxQ1_PLUS = matchQ1.getDouble("Q1_PLUS");
							Kladionica_Q1_PLUS = matchQ1.getString("Kladionica");
						}
						// System.out.println("tekma " + matchQ4H);
					}

					double x = 1000;
					double z = x * maxQ1_MINUS;
					double y = z / maxQ1_PLUS;
					double Profit1 = z - (x + y);
					double Procenat1 = (Profit1 / (x + y)) * 100;
					brojObradjenihObjekata++;

					if (Profit1 > 0) {
						JSONObject objekat = new JSONObject();
						objekat.put("home", firstMatch.getString("home"));
						objekat.put("away", firstMatch.getString("away"));
						objekat.put("Start", firstMatch.getLong("Start"));
						objekat.put("Liga", firstMatch.getString("Liga"));
						objekat.put("kladionica1", Kladionica_Q1_MINUS);//
						objekat.put("kladionica2", Kladionica_Q1_PLUS);//
						objekat.put("bet1", "Q1_MINUS");//
						objekat.put("bet2", "Q1_PLUS");//
						objekat.put("odd1", maxQ1_MINUS);//
						objekat.put("odd2", maxQ1_PLUS);//
						objekat.put("Procenat", Procenat1);//
						objekat.put("Sport", "Košarka");
						objekat.put("Granica", firstMatch.getDouble("UkupnoPoenaGr"));

						objekat.put("Profit", Profit1);//
						objekat.put("Ulog1", x);
						objekat.put("Ulog2", y);
						objekat.put("Prolaz", z);

						profitabilneUtakmice++;
						utakmice.put(objekat);
						pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
					}
				}
			
		
		

		LocalDateTime now = LocalDateTime.now();

		// Pretpostavljam da se ovo nalazi u metodi unutar klase koja analizira utakmice
		System.out.println("---------------------------------------------------------------------------------------------------------------------");
		//System.out.println(utakmice.toString());
		System.out.println("Profitabilne utakmice: " + profitabilneUtakmice + ", Broj utakmica: " + brojUtakmica + ", Obradjen broj objekata: " + brojObradjenihObjekata);
		System.out.println("Vreme: " + now);
		System.out.println("---------------------------------------------------------------------------------------------------------------------");

		String[] recipientNumbers = App.konvertovaniBrojevi;
        //System.out.println(Arrays.toString(recipientNumbers)); // Prikaz brojeva
        WaSender waSender = new WaSender(recipientNumbers);


		boolean hasSafeMatch = false; // Za uslov 1
		boolean hasLowRiskMatch = false; // Za uslov 2

		for (int i = 0; i < utakmice.length(); i++) {
		    JSONObject utakmica = utakmice.getJSONObject(i);

		    if (utakmica.getDouble("Procenat") > 4 && !utakmica.has("Rizik")) {
		        hasSafeMatch = true; 
		    }

		    if (utakmica.getDouble("Procenat") > 18 && utakmica.has("Rizik") && utakmica.getBoolean("Rizik")) {
		        hasLowRiskMatch = true; 
		        //System.out.println("IMA 18!!");
		    }
		}

		if (hasSafeMatch) {
		    String message = "Sigurna utakmica sa 4 ili više procenata profita. Proveri litu na sajtu http://arbitrawin.com/kvote.html";
		    waSender.sendWhatsAppMessage(message);
		}

		/*if (hasLowRiskMatch) {
		    String message = "Utakmica sa minimalnim rizikom sa 18 ili više procenata. Proveri litu na sajtu http://arbitrawin.xyz/kvote.html";
		    waSender.sendWhatsAppMessage(message);
		}*/


    }
	

	private List<JSONObject> jsonArrayToList(JSONArray array) {
		List<JSONObject> list = new ArrayList<>();
		for (int i = 0; i < array.length(); i++) {
			list.add(array.getJSONObject(i));
		}
		return list;
	}
}
