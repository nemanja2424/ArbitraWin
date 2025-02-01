package kalkulator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.arbet3.arbet3.App;
import org.json.JSONArray;
import org.json.JSONObject;
import rukomet.*;
import Fudbal.pozoviAPI;

public class Rukomet {
	String apiUrl = "https://x8ki-letl-twmt.n7.xano.io/api:etI0bpsP/obradjene_tekme";

	public static void main(String[] args) {
		Rukomet rukomet = new Rukomet();
		rukomet.izvrsiAnalizu();
	}

	public void izvrsiAnalizu() {
		JSONArray maxBetArray = MaxbetR.praviAPI();
		JSONArray oktagonBetArray = OktagonBetR.praviAPI();
		JSONArray merkurArray = MerkurR.praviAPI();
		JSONArray soccerBetArray = SoccerBetR.praviAPI();
		JSONArray betOleArray = BetOleR.praviAPI();

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

		for (List<JSONObject> matchGroup : groupedMatchesSve) {
			JSONObject firstMatch = matchGroup.get(0);
			double max1X = 1.0;
			String Kladionica_1X = "";
			double maxX2 = 1.0;
			String Kladionica_X2 = "";

			double max1P1X = 1.0;
			String Kladionica_1P1X = "";
			double max1PX2 = 1.0;
			String Kladionica_1PX2 = "";

			double max2P1X = 1.0;
			String Kladionica_2P1X = "";
			double max2PX2 = 1.0;
			String Kladionica_2PX2 = "";

			double maxW1 = 1.0;
			String Kladionica_W1 = "";
			double maxW2 = 1.0;
			String Kladionica_W2 = "";

			double max1PW1 = 1.0;
			String Kladionica_1PW1 = "";
			double max1PW2 = 1.0;
			String Kladionica_1PW2 = "";

			double max2PW1 = 1.0;
			String Kladionica_2PW1 = "";
			double max2PW2 = 1.0;
			String Kladionica_2PW2 = "";

			double maxPAR = 1.0;
			String Kladionica_PAR = "";
			double maxNEPAR = 1.0;
			String Kladionica_NEPAR = "";

			/*
			 * double max = 1.0; String Kladionica_ = ""; double max = 1.0; String
			 * Kladionica_ = "";
			 */
			for (JSONObject match : matchGroup) {
				/*
				 * if (match.has("") && match.getDouble("") > max) { max = match.getDouble("");
				 * Kladionica_ = match.getString("Kladionica"); }
				 */
				if (match.has("PAR") && match.getDouble("PAR") > maxPAR) {
					maxPAR = match.getDouble("PAR");
					Kladionica_PAR = match.getString("Kladionica");
				}
				if (match.has("NEPAR") && match.getDouble("NEPAR") > maxNEPAR) {
					maxNEPAR = match.getDouble("NEPAR");
					Kladionica_NEPAR = match.getString("Kladionica");
				}
				if (match.has("2PW2") && match.getDouble("2PW2") > max2PW2) {
					max2PW2 = match.getDouble("2PW2");
					Kladionica_2PW2 = match.getString("Kladionica");
				}
				if (match.has("2PW1") && match.getDouble("2PW1") > max2PW1) {
					max2PW1 = match.getDouble("2PW1");
					Kladionica_2PW1 = match.getString("Kladionica");
				}
				if (match.has("1PW2") && match.getDouble("1PW2") > max1PW2) {
					max1PW2 = match.getDouble("1PW2");
					Kladionica_1PW2 = match.getString("Kladionica");
				}
				if (match.has("1PW1") && match.getDouble("1PW1") > max1PW1) {
					max1PW1 = match.getDouble("1PW1");
					Kladionica_1PW1 = match.getString("Kladionica");
				}
				if (match.has("W2") && match.getDouble("W2") > maxW2) {
					maxW2 = match.getDouble("W2");
					Kladionica_W2 = match.getString("Kladionica");
				}
				if (match.has("W1") && match.getDouble("W1") > maxW1) {
					maxW1 = match.getDouble("W1");
					Kladionica_W1 = match.getString("Kladionica");
				}
				if (match.has("2PX2") && match.getDouble("2PX2") > max2PX2) {
					max2PX2 = match.getDouble("2PX2");
					Kladionica_2PX2 = match.getString("Kladionica");
				}
				if (match.has("2P1X") && match.getDouble("2P1X") > max2P1X) {
					max2P1X = match.getDouble("2P1X");
					Kladionica_2P1X = match.getString("Kladionica");
				}
				if (match.has("1PX2") && match.getDouble("1PX2") > max1PX2) {
					max1PX2 = match.getDouble("1PX2");
					Kladionica_1PX2 = match.getString("Kladionica");
				}
				if (match.has("1P1X") && match.getDouble("1P1X") > max1P1X) {
					max1P1X = match.getDouble("1P1X");
					Kladionica_1P1X = match.getString("Kladionica");
				}
				if (match.has("1X") && match.getDouble("1X") > max1X) {
					max1X = match.getDouble("1X");
					Kladionica_1X = match.getString("Kladionica");
				}
				if (match.has("X2") && match.getDouble("X2") > maxX2) {
					maxX2 = match.getDouble("X2");
					Kladionica_X2 = match.getString("Kladionica");
				}
			}

			brojUtakmica++;

			double x = 1000;
			double z = x * max1X;
			double y = z / maxX2;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * max1P1X;
			y = z / max1PX2;
			double Profit2 = z - (x + y);
			double Procenat2 = (Profit2 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * max2P1X;
			y = z / max2PX2;
			double Profit3 = z - (x + y);
			double Procenat3 = (Profit3 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxW1;
			y = z / maxW2;
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

			z = x * maxPAR;
			y = z / maxNEPAR;
			double Profit7 = z - (x + y);
			double Procenat7 = (Profit7 / (x + y)) * 100;
			brojObradjenihObjekata++;

			/*
			 * z = x * max; y = z / max; double Profit = z - (x + y); double Procenat =
			 * (Profit / (x + y)) * 100; brojObradjenihObjekata++;
			 */
			/*
			 * if (Profit > 0) { JSONObject objekat = new JSONObject(); objekat.put("home",
			 * firstMatch.getString("home")); objekat.put("away",
			 * firstMatch.getString("away")); objekat.put("Start",
			 * firstMatch.getLong("Start")); objekat.put("Liga",
			 * firstMatch.getString("Liga")); objekat.put("kladionica1", Kladionica_);//
			 * objekat.put("kladionica2", Kladionica_);// objekat.put("bet1", "bet");//
			 * objekat.put("bet2", "bet");// objekat.put("odd1", max);// objekat.put("odd2",
			 * max);// objekat.put("Procenat", Procenat);// objekat.put("Sport", "Rukomet");
			 * 
			 * objekat.put("Profit", Profit);// objekat.put("Ulog1", x);
			 * objekat.put("Ulog2", y); objekat.put("Prolaz", z);
			 * 
			 * profitabilneUtakmice++; utakmice.put(objekat);
			 * //pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString()); }
			 */
			if (Profit7 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_PAR);//
				objekat.put("kladionica2", Kladionica_NEPAR);//
				objekat.put("bet1", "PAR");//
				objekat.put("bet2", "NEPAR");//
				objekat.put("odd1", maxPAR);//
				objekat.put("odd2", maxNEPAR);//
				objekat.put("Procenat", Procenat7);//
				objekat.put("Sport", "Rukomet");

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
				objekat.put("Sport", "Rukomet");

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
				objekat.put("Sport", "Rukomet");

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
				objekat.put("kladionica1", Kladionica_W1);//
				objekat.put("kladionica2", Kladionica_W2);//
				objekat.put("bet1", "W1");//
				objekat.put("bet2", "W2");//
				objekat.put("odd1", maxW1);//
				objekat.put("odd2", maxW2);//
				objekat.put("Procenat", Procenat4);//
				objekat.put("Sport", "Rukomet");

				objekat.put("Profit", Profit4);//
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
				objekat.put("kladionica1", Kladionica_2P1X);//
				objekat.put("kladionica2", Kladionica_2PX2);//
				objekat.put("bet1", "2P1X");//
				objekat.put("bet2", "2PX2");//
				objekat.put("odd1", max2P1X);//
				objekat.put("odd2", max2PX2);//
				objekat.put("Procenat", Procenat3);//
				objekat.put("Sport", "Rukomet");

				objekat.put("Profit", Profit3);//
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
				objekat.put("kladionica1", Kladionica_1P1X);//
				objekat.put("kladionica2", Kladionica_1PX2);//
				objekat.put("bet1", "1P1X");//
				objekat.put("bet2", "1PX2");//
				objekat.put("odd1", max1P1X);//
				objekat.put("odd2", max1PX2);//
				objekat.put("Procenat", Procenat2);//
				objekat.put("Sport", "Rukomet");

				objekat.put("Profit", Profit2);//
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
				objekat.put("kladionica1", Kladionica_1X);//
				objekat.put("kladionica2", Kladionica_X2);//
				objekat.put("bet1", "1X");//
				objekat.put("bet2", "X2");//
				objekat.put("odd1", max1X);//
				objekat.put("odd2", maxX2);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Rukomet");

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}

		//// UKUPNO GOLOVA////

		JSONArray ukupnoGolovaUtakmice = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 6; i++) {
				String OTKey = "overUnderGr" + i;
				String OT_MINUSKey = "UG" + i + "_MINUS";
				String OT_PLUSKey = "UG" + i + "_PLUS";

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
					handicapDetails.put("UkupnoGolovaGr", OTValue);
					handicapDetails.put("UG_MINUS", OT_MINUSValue);
					handicapDetails.put("UG_PLUS", OT_PLUSValue);

					ukupnoGolovaUtakmice.put(handicapDetails);
				}
			}
		}
		List<JSONObject> ukupnoGolova = new ArrayList<>();
		ukupnoGolova.addAll(jsonArrayToList(ukupnoGolovaUtakmice));

		List<List<JSONObject>> groupedMatchesUG = ukupnoGolova.stream()
				.collect(
						Collectors
								.groupingBy(
										obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
												+ obj.getString("away") + "_" + obj.getDouble("UkupnoGolovaGr"),
										Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());

		for (List<JSONObject> matchGroupUG : groupedMatchesUG) {
			JSONObject firstMatch = matchGroupUG.get(0);
			double maxUG_MINUS = 1.0;
			String Kladionica_UG_MINUS = "";
			double maxUG_PLUS = 1.0;
			String Kladionica_UG_PLUS = "";

			// System.out.println("tekma " + groupedMatchesQ4H);
			for (JSONObject matchUG : matchGroupUG) {
				if (matchUG.has("UG_MINUS") && matchUG.getDouble("UG_MINUS") > maxUG_MINUS) {
					maxUG_MINUS = matchUG.getDouble("UG_MINUS");
					Kladionica_UG_MINUS = matchUG.getString("Kladionica");
				}
				if (matchUG.has("UG_PLUS") && matchUG.getDouble("UG_PLUS") > maxUG_PLUS) {
					maxUG_PLUS = matchUG.getDouble("UG_PLUS");
					Kladionica_UG_PLUS = matchUG.getString("Kladionica");
				}
				// System.out.println("tekma " + matchQ4H);
			}

			double x = 1000;
			double z = x * maxUG_MINUS;
			double y = z / maxUG_PLUS;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			if (Profit1 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_UG_MINUS);//
				objekat.put("kladionica2", Kladionica_UG_PLUS);//
				objekat.put("bet1", "UG_MINUS");//
				objekat.put("bet2", "UG_PLUS");//
				objekat.put("odd1", maxUG_MINUS);//
				objekat.put("odd2", maxUG_PLUS);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Rukomet");
				objekat.put("Granica", firstMatch.getDouble("UkupnoGolovaGr"));

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}

		//// UKUPNO GOLOVA PRVO POLUVREME////

		JSONArray ukupnoGolovaPrvoPoluvremeUtakmice = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 3; i++) {
				String OTKey = "overUnderPGr" + i;
				String OT_MINUSKey = "UGP" + i + "_MINUS";
				String OT_PLUSKey = "UGP" + i + "_PLUS";

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
					handicapDetails.put("UkupnoGolovaPrvoPoluvremeGr", OTValue);
					handicapDetails.put("UGP_MINUS", OT_MINUSValue);
					handicapDetails.put("UGP_PLUS", OT_PLUSValue);

					ukupnoGolovaPrvoPoluvremeUtakmice.put(handicapDetails);
				}
			}
		}
		List<JSONObject> ukupnoGolovaPrvoPoluvreme = new ArrayList<>();
		ukupnoGolovaPrvoPoluvreme.addAll(jsonArrayToList(ukupnoGolovaPrvoPoluvremeUtakmice));

		List<List<JSONObject>> groupedMatchesUGP = ukupnoGolovaPrvoPoluvreme.stream()
				.collect(Collectors.groupingBy(obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
						+ obj.getString("away") + "_" + obj.getDouble("UkupnoGolovaPrvoPoluvremeGr"),
						Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());

		for (List<JSONObject> matchGroupUGP : groupedMatchesUGP) {
			JSONObject firstMatch = matchGroupUGP.get(0);
			double maxUGP_MINUS = 1.0;
			String Kladionica_UGP_MINUS = "";
			double maxUGP_PLUS = 1.0;
			String Kladionica_UGP_PLUS = "";

			// System.out.println("tekma " + groupedMatchesQ4H);
			for (JSONObject matchUGP : matchGroupUGP) {
				if (matchUGP.has("UGP_MINUS") && matchUGP.getDouble("UGP_MINUS") > maxUGP_MINUS) {
					maxUGP_MINUS = matchUGP.getDouble("UGP_MINUS");
					Kladionica_UGP_MINUS = matchUGP.getString("Kladionica");
				}
				if (matchUGP.has("UGP_PLUS") && matchUGP.getDouble("UGP_PLUS") > maxUGP_PLUS) {
					maxUGP_PLUS = matchUGP.getDouble("UGP_PLUS");
					Kladionica_UGP_PLUS = matchUGP.getString("Kladionica");
				}
				// System.out.println("tekma " + matchQ4H);
			}

			double x = 1000;
			double z = x * maxUGP_MINUS;
			double y = z / maxUGP_PLUS;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			if (Profit1 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_UGP_MINUS);//
				objekat.put("kladionica2", Kladionica_UGP_PLUS);//
				objekat.put("bet1", "UGP_MINUS");//
				objekat.put("bet2", "UGP_PLUS");//
				objekat.put("odd1", maxUGP_MINUS);//
				objekat.put("odd2", maxUGP_PLUS);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Rukomet");
				objekat.put("Granica", firstMatch.getDouble("UkupnoGolovaPrvoPoluvremeGr"));

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}

		//// UKUPNO GOLOVA DRUGO POLUVREME////

		JSONArray ukupnoGolovaDrugoPoluvremeUtakmice = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 3; i++) {
				String OTKey = "overUnderSecondHalfGr" + i;
				String OT_MINUSKey = "UG2P" + i + "_MINUS";
				String OT_PLUSKey = "UG2P" + i + "_PLUS";

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
					handicapDetails.put("UkupnoGolovaDrugoPoluvremeGr", OTValue);
					handicapDetails.put("UG2P_MINUS", OT_MINUSValue);
					handicapDetails.put("UG2P_PLUS", OT_PLUSValue);

					ukupnoGolovaDrugoPoluvremeUtakmice.put(handicapDetails);
				}
			}
		}
		List<JSONObject> ukupnoGolovaDrugoPoluvreme = new ArrayList<>();
		ukupnoGolovaDrugoPoluvreme.addAll(jsonArrayToList(ukupnoGolovaDrugoPoluvremeUtakmice));

		List<List<JSONObject>> groupedMatchesUG2P = ukupnoGolovaDrugoPoluvreme.stream()
				.collect(Collectors.groupingBy(obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
						+ obj.getString("away") + "_" + obj.getDouble("UkupnoGolovaDrugoPoluvremeGr"),
						Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());

		for (List<JSONObject> matchGroupUG2P : groupedMatchesUG2P) {
			JSONObject firstMatch = matchGroupUG2P.get(0);
			double maxUG2P_MINUS = 1.0;
			String Kladionica_UG2P_MINUS = "";
			double maxUG2P_PLUS = 1.0;
			String Kladionica_UG2P_PLUS = "";

			// System.out.println("tekma " + groupedMatchesQ4H);
			for (JSONObject matchUG2P : matchGroupUG2P) {
				if (matchUG2P.has("UG2P_MINUS") && matchUG2P.getDouble("UG2P_MINUS") > maxUG2P_MINUS) {
					maxUG2P_MINUS = matchUG2P.getDouble("UG2P_MINUS");
					Kladionica_UG2P_MINUS = matchUG2P.getString("Kladionica");
				}
				if (matchUG2P.has("UG2P_PLUS") && matchUG2P.getDouble("UG2P_PLUS") > maxUG2P_PLUS) {
					maxUG2P_PLUS = matchUG2P.getDouble("UG2P_PLUS");
					Kladionica_UG2P_PLUS = matchUG2P.getString("Kladionica");
				}
				// System.out.println("tekma " + matchQ4H);
			}

			double x = 1000;
			double z = x * maxUG2P_MINUS;
			double y = z / maxUG2P_PLUS;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			if (Profit1 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_UG2P_MINUS);//
				objekat.put("kladionica2", Kladionica_UG2P_PLUS);//
				objekat.put("bet1", "UG2P_MINUS");//
				objekat.put("bet2", "UG2P_PLUS");//
				objekat.put("odd1", maxUG2P_MINUS);//
				objekat.put("odd2", maxUG2P_PLUS);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Rukomet");
				objekat.put("Granica", firstMatch.getDouble("UkupnoGolovaDrugoPoluvremeGr"));

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}

		//// UKUPNO GOLOVA DOMACIN////

		JSONArray UkupnoGolovaDomacinUtakmice = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 3; i++) {
				String D_Key = "homeOverUnderGr" + i;
				String D_MINUSKey = "D" + i + "_MINUS";
				String D_PLUSKey = "D" + i + "_PLUS";

				if (match.has(D_Key) && match.has(D_MINUSKey) && match.has(D_PLUSKey)) {
					double D_Value = match.getDouble(D_Key);
					double D_MINUSValue = match.getDouble(D_MINUSKey);
					double D_PLUSValue = match.getDouble(D_PLUSKey);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("UkupnoGolovaDomacinGr", D_Value);
					handicapDetails.put("D_MINUS", D_MINUSValue);
					handicapDetails.put("D_PLUS", D_PLUSValue);

					UkupnoGolovaDomacinUtakmice.put(handicapDetails);
				}
			}
		}
		List<JSONObject> UkupnoGolovaDomacin = new ArrayList<>();
		UkupnoGolovaDomacin.addAll(jsonArrayToList(UkupnoGolovaDomacinUtakmice));

		List<List<JSONObject>> groupedMatchesD = UkupnoGolovaDomacin.stream()
				.collect(
						Collectors
								.groupingBy(
										obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
												+ obj.getString("away") + "_" + obj.getDouble("UkupnoGolovaDomacinGr"),
										Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());

		for (List<JSONObject> matchGroupD : groupedMatchesD) {
			JSONObject firstMatch = matchGroupD.get(0);
			double maxD_MINUS = 1.0;
			String Kladionica_D_MINUS = "";
			double maxD_PLUS = 1.0;
			String Kladionica_D_PLUS = "";

			// System.out.println("tekma " + groupedMatchesQ4H);
			for (JSONObject matchD : matchGroupD) {
				if (matchD.has("D_MINUS") && matchD.getDouble("D_MINUS") > maxD_MINUS) {
					maxD_MINUS = matchD.getDouble("D_MINUS");
					Kladionica_D_MINUS = matchD.getString("Kladionica");
				}
				if (matchD.has("D_PLUS") && matchD.getDouble("D_PLUS") > maxD_PLUS) {
					maxD_PLUS = matchD.getDouble("D_PLUS");
					Kladionica_D_PLUS = matchD.getString("Kladionica");
				}
				// System.out.println("tekma " + matchQ4H);
			}

			double x = 1000;
			double z = x * maxD_MINUS;
			double y = z / maxD_PLUS;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			if (Profit1 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_D_MINUS);//
				objekat.put("kladionica2", Kladionica_D_PLUS);//
				objekat.put("bet1", "D_MINUS");//
				objekat.put("bet2", "D_PLUS");//
				objekat.put("odd1", maxD_MINUS);//
				objekat.put("odd2", maxD_PLUS);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Rukomet");
				objekat.put("Granica", firstMatch.getDouble("UkupnoGolovaDomacinGr"));

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}

		//// UKUPNO GOLOVA DOMACIN PRVO POLUVREME////

		JSONArray UkupnoGolovaDomacinPrvoPoluvremeUtakmice = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 3; i++) {
				String JEDAN_D_Key = "homeOverUnderFirstHalfGr" + i;
				String JEDAN_D_MINUSKey = "1D" + i + "_MINUS";
				String JEDAN_D_PLUSKey = "1D" + i + "_PLUS";

				if (match.has(JEDAN_D_Key) && match.has(JEDAN_D_MINUSKey) && match.has(JEDAN_D_PLUSKey)) {
					double JEDAN_D_Value = match.getDouble(JEDAN_D_Key);
					double JEDAN_D_MINUSValue = match.getDouble(JEDAN_D_MINUSKey);
					double JEDAN_D_PLUSValue = match.getDouble(JEDAN_D_PLUSKey);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("UkupnoGolovaDomacinPrvoPoluvremeGr", JEDAN_D_Value);
					handicapDetails.put("1D_MINUS", JEDAN_D_MINUSValue);
					handicapDetails.put("1D_PLUS", JEDAN_D_PLUSValue);

					UkupnoGolovaDomacinPrvoPoluvremeUtakmice.put(handicapDetails);
				}
			}
		}
		List<JSONObject> UkupnoGolovaDomacinPrvoPoluvreme = new ArrayList<>();
		UkupnoGolovaDomacinPrvoPoluvreme.addAll(jsonArrayToList(UkupnoGolovaDomacinPrvoPoluvremeUtakmice));

		List<List<JSONObject>> groupedMatches1D = UkupnoGolovaDomacinPrvoPoluvreme.stream()
				.collect(
						Collectors.groupingBy(
								obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_" + obj.getString("away")
										+ "_" + obj.getDouble("UkupnoGolovaDomacinPrvoPoluvremeGr"),
								Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());

		for (List<JSONObject> matchGroup1D : groupedMatches1D) {
			JSONObject firstMatch = matchGroup1D.get(0);
			double max1D_MINUS = 1.0;
			String Kladionica_1D_MINUS = "";
			double max1D_PLUS = 1.0;
			String Kladionica_1D_PLUS = "";

			// System.out.println("tekma " + groupedMatchesQ4H);
			for (JSONObject match1D : matchGroup1D) {
				if (match1D.has("1D_MINUS") && match1D.getDouble("1D_MINUS") > max1D_MINUS) {
					max1D_MINUS = match1D.getDouble("1D_MINUS");
					Kladionica_1D_MINUS = match1D.getString("Kladionica");
				}
				if (match1D.has("1D_PLUS") && match1D.getDouble("1D_PLUS") > max1D_PLUS) {
					max1D_PLUS = match1D.getDouble("1D_PLUS");
					Kladionica_1D_PLUS = match1D.getString("Kladionica");
				}
				// System.out.println("tekma " + matchQ4H);
			}

			double x = 1000;
			double z = x * max1D_MINUS;
			double y = z / max1D_PLUS;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			if (Profit1 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_1D_MINUS);//
				objekat.put("kladionica2", Kladionica_1D_PLUS);//
				objekat.put("bet1", "1D_MINUS");//
				objekat.put("bet2", "1D_PLUS");//
				objekat.put("odd1", max1D_MINUS);//
				objekat.put("odd2", max1D_PLUS);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Rukomet");
				objekat.put("Granica", firstMatch.getDouble("UkupnoGolovaDomacinPrvoPoluvremeGr"));

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}

////UKUPNO GOLOVA DOMACIN DRUGO POLUVREME////

		JSONArray UkupnoGolovaDomacinDrugoPoluvremeUtakmice = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 3; i++) {
				String DVA_PD_Key = "homeOverUnderSecondHalfGr" + i;
				String DVA_PD_MINUSKey = "2PD" + i + "_MINUS";
				String DVA_PD_PLUSKey = "2PD" + i + "_PLUS";

				if (match.has(DVA_PD_Key) && match.has(DVA_PD_MINUSKey) && match.has(DVA_PD_PLUSKey)) {
					double DVA_PD_Value = match.getDouble(DVA_PD_Key);
					double DVA_PD_MINUSValue = match.getDouble(DVA_PD_MINUSKey);
					double DVA_PD_PLUSValue = match.getDouble(DVA_PD_PLUSKey);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("UkupnoGolovaDomacinDrugoPoluvremeGr", DVA_PD_Value);
					handicapDetails.put("2PD_MINUS", DVA_PD_MINUSValue);
					handicapDetails.put("2PD_PLUS", DVA_PD_PLUSValue);

					UkupnoGolovaDomacinDrugoPoluvremeUtakmice.put(handicapDetails);
				}
			}
		}
		List<JSONObject> UkupnoGolovaDomacinDrugoPoluvreme = new ArrayList<>();
		UkupnoGolovaDomacinDrugoPoluvreme.addAll(jsonArrayToList(UkupnoGolovaDomacinDrugoPoluvremeUtakmice));

		List<List<JSONObject>> groupedMatches2PD = UkupnoGolovaDomacinDrugoPoluvreme.stream()
				.collect(
						Collectors.groupingBy(
								obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_" + obj.getString("away")
										+ "_" + obj.getDouble("UkupnoGolovaDomacinDrugoPoluvremeGr"),
								Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());

		for (List<JSONObject> matchGroup2PD : groupedMatches2PD) {
			JSONObject firstMatch = matchGroup2PD.get(0);
			double max2PD_MINUS = 1.0;
			String Kladionica_2PD_MINUS = "";
			double max2PD_PLUS = 1.0;
			String Kladionica_2PD_PLUS = "";

			// System.out.println("tekma " + groupedMatchesQ4H);
			for (JSONObject match2PD : matchGroup2PD) {
				if (match2PD.has("2PD_MINUS") && match2PD.getDouble("2PD_MINUS") > max2PD_MINUS) {
					max2PD_MINUS = match2PD.getDouble("2PD_MINUS");
					Kladionica_2PD_MINUS = match2PD.getString("Kladionica");
				}
				if (match2PD.has("2PD_PLUS") && match2PD.getDouble("2PD_PLUS") > max2PD_PLUS) {
					max2PD_PLUS = match2PD.getDouble("2PD_PLUS");
					Kladionica_2PD_PLUS = match2PD.getString("Kladionica");
				}
				// System.out.println("tekma " + matchQ4H);
			}

			double x = 1000;
			double z = x * max2PD_MINUS;
			double y = z / max2PD_PLUS;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			if (Profit1 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_2PD_MINUS);//
				objekat.put("kladionica2", Kladionica_2PD_PLUS);//
				objekat.put("bet1", "2PD_MINUS");//
				objekat.put("bet2", "2PD_PLUS");//
				objekat.put("odd1", max2PD_MINUS);//
				objekat.put("odd2", max2PD_PLUS);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Rukomet");
				objekat.put("Granica", firstMatch.getDouble("UkupnoGolovaDomacinDrugoPoluvremeGr"));

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}

		//// UKUPNO GOLOVA GOST////

		JSONArray UkupnoGolovaGostUtakmice = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 3; i++) {
				String DKey = "awayOverUnderGr" + i;
				String G_MINUSKey = "G" + i + "_MINUS";
				String G_PLUSKey = "G" + i + "_PLUS";

				if (match.has(DKey) && match.has(G_MINUSKey) && match.has(G_PLUSKey)) {
					double G_Value = match.getDouble(DKey);
					double G_MINUSValue = match.getDouble(G_MINUSKey);
					double G_PLUSValue = match.getDouble(G_PLUSKey);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("UkupnoGolovaGostGr", G_Value);
					handicapDetails.put("G_MINUS", G_MINUSValue);
					handicapDetails.put("G_PLUS", G_PLUSValue);

					UkupnoGolovaGostUtakmice.put(handicapDetails);
				}
			}
		}
		List<JSONObject> UkupnoGolovaGost = new ArrayList<>();
		UkupnoGolovaGost.addAll(jsonArrayToList(UkupnoGolovaGostUtakmice));

		List<List<JSONObject>> groupedMatchesG = UkupnoGolovaGost.stream()
				.collect(
						Collectors
								.groupingBy(
										obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
												+ obj.getString("away") + "_" + obj.getDouble("UkupnoGolovaGostGr"),
										Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());

		for (List<JSONObject> matchGroupG : groupedMatchesG) {
			JSONObject firstMatch = matchGroupG.get(0);
			double maxG_MINUS = 1.0;
			String Kladionica_G_MINUS = "";
			double maxG_PLUS = 1.0;
			String Kladionica_G_PLUS = "";

			// System.out.println("tekma " + groupedMatchesQ4H);
			for (JSONObject matchG : matchGroupG) {
				if (matchG.has("G_MINUS") && matchG.getDouble("G_MINUS") > maxG_MINUS) {
					maxG_MINUS = matchG.getDouble("G_MINUS");
					Kladionica_G_MINUS = matchG.getString("Kladionica");
				}
				if (matchG.has("G_PLUS") && matchG.getDouble("G_PLUS") > maxG_PLUS) {
					maxG_PLUS = matchG.getDouble("G_PLUS");
					Kladionica_G_PLUS = matchG.getString("Kladionica");
				}
				// System.out.println("tekma " + matchQ4H);
			}

			double x = 1000;
			double z = x * maxG_MINUS;
			double y = z / maxG_PLUS;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			if (Profit1 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_G_MINUS);//
				objekat.put("kladionica2", Kladionica_G_PLUS);//
				objekat.put("bet1", "G_MINUS");//
				objekat.put("bet2", "G_PLUS");//
				objekat.put("odd1", maxG_MINUS);//
				objekat.put("odd2", maxG_PLUS);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Rukomet");
				objekat.put("Granica", firstMatch.getDouble("UkupnoGolovaGostGr"));

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}

		//// UKUPNO GOLOVA GOST PRVO POLUVREME////

		JSONArray UkupnoGolovaGostPrvoPoluvremeUtakmice = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 3; i++) {
				String JEDAN_G_Key = "awayOverUnderFirstHalfGr" + i;
				String JEDAN_G_MINUSKey = "1G" + i + "_MINUS";
				String JEDAN_G_PLUSKey = "1G" + i + "_PLUS";

				if (match.has(JEDAN_G_Key) && match.has(JEDAN_G_MINUSKey) && match.has(JEDAN_G_PLUSKey)) {
					double JEDAN_G_Value = match.getDouble(JEDAN_G_Key);
					double JEDAN_G_MINUSValue = match.getDouble(JEDAN_G_MINUSKey);
					double JEDAN_G_PLUSValue = match.getDouble(JEDAN_G_PLUSKey);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("UkupnoGolovaGostPrvoPoluvremeGr", JEDAN_G_Value);
					handicapDetails.put("1G_MINUS", JEDAN_G_MINUSValue);
					handicapDetails.put("1G_PLUS", JEDAN_G_PLUSValue);

					UkupnoGolovaGostPrvoPoluvremeUtakmice.put(handicapDetails);
				}
			}
		}
		List<JSONObject> UkupnoGolovaGostPrvoPoluvreme = new ArrayList<>();
		UkupnoGolovaGostPrvoPoluvreme.addAll(jsonArrayToList(UkupnoGolovaGostPrvoPoluvremeUtakmice));

		List<List<JSONObject>> groupedMatches1G = UkupnoGolovaGostPrvoPoluvreme.stream()
				.collect(Collectors.groupingBy(obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
						+ obj.getString("away") + "_" + obj.getDouble("UkupnoGolovaGostPrvoPoluvremeGr"),
						Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());

		for (List<JSONObject> matchGroup1G : groupedMatches1G) {
			JSONObject firstMatch = matchGroup1G.get(0);
			double max1G_MINUS = 1.0;
			String Kladionica_1G_MINUS = "";
			double max1G_PLUS = 1.0;
			String Kladionica_1G_PLUS = "";

			// System.out.println("tekma " + groupedMatchesQ4H);
			for (JSONObject match1G : matchGroup1G) {
				if (match1G.has("1G_MINUS") && match1G.getDouble("1G_MINUS") > max1G_MINUS) {
					max1G_MINUS = match1G.getDouble("1G_MINUS");
					Kladionica_1G_MINUS = match1G.getString("Kladionica");
				}
				if (match1G.has("1G_PLUS") && match1G.getDouble("1G_PLUS") > max1G_PLUS) {
					max1G_PLUS = match1G.getDouble("1G_PLUS");
					Kladionica_1G_PLUS = match1G.getString("Kladionica");
				}
				// System.out.println("tekma " + matchQ4H);
			}

			double x = 1000;
			double z = x * max1G_MINUS;
			double y = z / max1G_PLUS;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			if (Profit1 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_1G_MINUS);//
				objekat.put("kladionica2", Kladionica_1G_PLUS);//
				objekat.put("bet1", "1G_MINUS");//
				objekat.put("bet2", "1G_PLUS");//
				objekat.put("odd1", max1G_MINUS);//
				objekat.put("odd2", max1G_PLUS);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Rukomet");
				objekat.put("Granica", firstMatch.getDouble("UkupnoGolovaGostPrvoPoluvremeGr"));

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}

		//// UKUPNO GOLOVA Gost DRUGO POLUVREME////

		JSONArray UkupnoGolovaGostDrugoPoluvremeUtakmice = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 3; i++) {
				String DVA_PG_Key = "awayOverUnderSecondHalfGr" + i;
				String DVA_PG_MINUSKey = "2PG" + i + "_MINUS";
				String DVA_PG_PLUSKey = "2PG" + i + "_PLUS";

				if (match.has(DVA_PG_Key) && match.has(DVA_PG_MINUSKey) && match.has(DVA_PG_PLUSKey)) {
					double DVA_PG_Value = match.getDouble(DVA_PG_Key);
					double DVA_PG_MINUSValue = match.getDouble(DVA_PG_MINUSKey);
					double DVA_PG_PLUSValue = match.getDouble(DVA_PG_PLUSKey);

					JSONObject handicaPGetails = new JSONObject();
					handicaPGetails.put("home", home);
					handicaPGetails.put("away", away);
					handicaPGetails.put("Kladionica", kladionica);
					handicaPGetails.put("Start", start);
					handicaPGetails.put("Liga", liga);
					handicaPGetails.put("UkupnoGolovaGostDrugoPoluvremeGr", DVA_PG_Value);
					handicaPGetails.put("2PG_MINUS", DVA_PG_MINUSValue);
					handicaPGetails.put("2PG_PLUS", DVA_PG_PLUSValue);

					UkupnoGolovaGostDrugoPoluvremeUtakmice.put(handicaPGetails);
				}
			}
		}

		List<JSONObject> UkupnoGolovaGostDrugoPoluvreme = new ArrayList<>();
		UkupnoGolovaGostDrugoPoluvreme.addAll(jsonArrayToList(UkupnoGolovaGostDrugoPoluvremeUtakmice));

		List<List<JSONObject>> groupedMatches2PG = UkupnoGolovaGostDrugoPoluvreme.stream()
				.collect(Collectors.groupingBy(obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
						+ obj.getString("away") + "_" + obj.getDouble("UkupnoGolovaGostDrugoPoluvremeGr"),
						Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());
		for (List<JSONObject> matchGroup2PG : groupedMatches2PG) {
			JSONObject firstMatch = matchGroup2PG.get(0);
			double max2PG_MINUS = 1.0;
			String Kladionica_2PG_MINUS = "";
			double max2PG_PLUS = 1.0;
			String Kladionica_2PG_PLUS = "";

			for (JSONObject match2PG : matchGroup2PG) {
				if (match2PG.has("2PG_MINUS") && match2PG.getDouble("2PG_MINUS") > max2PG_MINUS) {
					max2PG_MINUS = match2PG.getDouble("2PG_MINUS");
					Kladionica_2PG_MINUS = match2PG.getString("Kladionica");
				}
				if (match2PG.has("2PG_PLUS") && match2PG.getDouble("2PG_PLUS") > max2PG_PLUS) {
					max2PG_PLUS = match2PG.getDouble("2PG_PLUS");
					Kladionica_2PG_PLUS = match2PG.getString("Kladionica");
				}
			}

			double x = 1000;
			double z = x * max2PG_MINUS;
			double y = z / max2PG_PLUS;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			if (Profit1 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_2PG_MINUS);//
				objekat.put("kladionica2", Kladionica_2PG_PLUS);//
				objekat.put("bet1", "2PG_MINUS");//
				objekat.put("bet2", "2PG_PLUS");//
				objekat.put("odd1", max2PG_MINUS);//
				objekat.put("odd2", max2PG_PLUS);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Rukomet");
				objekat.put("Granica", firstMatch.getDouble("UkupnoGolovaGostDrugoPoluvremeGr"));

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}
		
	////HENDIKEP OT - ne radi////
/*
				JSONArray hendikepUtakmice = new JSONArray();
				for (JSONObject match : combinedList) {
					int i = 1;
					String home = match.getString("home");
					String away = match.getString("away");
					String kladionica = match.getString("Kladionica");
					long start = match.getLong("Start");
					String liga = match.getString("Liga");
					for (i = 1; i <= 3; i++) {
						String H_Key = "hendikepGr" + i;
						String H_MINUSKey = "H" + i + "1";
						String H_PLUSKey = "H" + i + "2";

						if (match.has(H_Key) && match.has(H_MINUSKey) && match.has(H_PLUSKey)) {
							double DVA_PG_Value = match.getDouble(H_Key);
							double DVA_PG_MINUSValue = match.getDouble(H_MINUSKey);
							double DVA_PG_PLUSValue = match.getDouble(H_PLUSKey);

							JSONObject handicaPGetails = new JSONObject();
							handicaPGetails.put("home", home);
							handicaPGetails.put("away", away);
							handicaPGetails.put("Kladionica", kladionica);
							handicaPGetails.put("Start", start);
							handicaPGetails.put("Liga", liga);
							handicaPGetails.put("hendikepGr", DVA_PG_Value);
							handicaPGetails.put("H1", DVA_PG_MINUSValue);
							handicaPGetails.put("H2", DVA_PG_PLUSValue);

							hendikepUtakmice.put(handicaPGetails);
						}
					}
				}
				List<JSONObject> hendikep = new ArrayList<>();
				hendikep.addAll(jsonArrayToList(hendikepUtakmice));
				List<List<JSONObject>> groupedMatchesH = hendikep.stream()
						.collect(Collectors.groupingBy(obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
								+ obj.getString("away") + "_" + obj.getDouble("hendikepGr"),
								Collectors.toList()))
						.values().stream()
						.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
						.collect(Collectors.toList());
				for (List<JSONObject> matchGroupH : groupedMatchesH) {
					JSONObject firstMatch = matchGroupH.get(0);
					double maxH1 = 1.0;
					String Kladionica_H1 = "";
					double maxH2 = 1.0;
					String Kladionica_H2 = "";

					
					for (JSONObject matchH : matchGroupH) {
						if (matchH.has("H1") && matchH.getDouble("H1") > maxH1) {
							maxH1 = matchH.getDouble("H1");
							Kladionica_H1 = matchH.getString("Kladionica");
						}
						if (matchH.has("H2") && matchH.getDouble("H2") > maxH2) {
							maxH2 = matchH.getDouble("H2");
							Kladionica_H2 = matchH.getString("Kladionica");
						}
					}

					double x = 1000;
					double z = x * maxH1;
					double y = z / maxH2;
					double Profit1 = z - (x + y);
					double Procenat1 = (Profit1 / (x + y)) * 100;
					brojObradjenihObjekata++;

					if (Profit1 > 0) {
						JSONObject objekat = new JSONObject();
						objekat.put("home", firstMatch.getString("home"));
						objekat.put("away", firstMatch.getString("away"));
						objekat.put("Start", firstMatch.getLong("Start"));
						objekat.put("Liga", firstMatch.getString("Liga"));
						objekat.put("kladionica1", Kladionica_H1);//
						objekat.put("kladionica2", Kladionica_H2);//
						objekat.put("bet1", "H1");//
						objekat.put("bet2", "H2");//
						objekat.put("odd1", maxH1);//
						objekat.put("odd2", maxH2);//
						objekat.put("Procenat", Procenat1);//
						objekat.put("Sport", "Rukomet");
						objekat.put("Granica", firstMatch.getDouble("hendikepGr"));

						objekat.put("Profit", Profit1);//
						objekat.put("Ulog1", x);
						objekat.put("Ulog2", y);
						objekat.put("Prolaz", z);

						profitabilneUtakmice++;
						utakmice.put(objekat);
						pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
					}
				}
*/
		LocalDateTime now = LocalDateTime.now();

		System.out.println("---------------------------------------------------------------------------------------------------------------------");
		//System.out.println(utakmice.toString());
		System.out.println("Profitabilne utakmice: " + profitabilneUtakmice + ", Broj utakmica: " + brojUtakmica
				+ ", Obradjen broj objekata: " + brojObradjenihObjekata);
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

	private List<JSONObject> jsonArrayToList(JSONArray array) {
		List<JSONObject> list = new ArrayList<>();
		for (int i = 0; i < array.length(); i++) {
			list.add(array.getJSONObject(i));
		}
		return list;
	}
}
