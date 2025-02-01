package kalkulator;

import Tenis.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.arbet3.arbet3.App;
import org.json.JSONArray;
import org.json.JSONObject;

import Fudbal.pozoviAPI;

public class Tenis {

	String apiUrl = "https://x8ki-letl-twmt.n7.xano.io/api:etI0bpsP/obradjene_tekme";

	public static void main(String[] args) {
		Tenis tenis = new Tenis();
		tenis.izvrsiAnalizu();
	}

	public void izvrsiAnalizu() {
		JSONArray maxBetArray = MaxbetT.praviAPI();
		JSONArray oktagonBetArray = OktagonBetT.praviAPI();
		JSONArray merkurArray = MerkurT.praviAPI();
		JSONArray soccerBetArray = SoccerBetT.praviAPI();
		JSONArray betOleArray = BetOleT.praviAPI();

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

			double max1 = 1.0;
			String Kladionica_1 = "";
			double max2 = 1.0;
			String Kladionica_2 = "";

			double maxPS1 = 1.0;
			String Kladionica_PS1 = "";
			double maxPS2 = 1.0;
			String Kladionica_PS2 = "";

			double maxDS1 = 1.0;
			String Kladionica_DS1 = "";
			double maxDS2 = 1.0;
			String Kladionica_DS2 = "";

			double maxTS1 = 1.0;
			String Kladionica_TS1 = "";
			double maxTS2 = 1.0;
			String Kladionica_TS2 = "";

			double maxSS = 1.0;
			String Kladionica_SS = "";
			double maxNS = 1.0;
			String Kladionica_NS = "";

			double maxPTBDA = 1.0;
			String Kladionica_PTBDA = "";
			double maxPTBNE = 1.0;
			String Kladionica_PTBNE = "";

			/*
			 * double max = 1.0; String Kladionica_ = ""; double max = 1.0; String
			 * Kladionica_ = "";
			 */
			for (JSONObject match : matchGroup) {
				/*
				 * if (match.has("") && match.getDouble("") > max) { max = match.getDouble("");
				 * Kladionica_ = match.getString("Kladionica"); }
				 */
				if (match.has("PTBDA") && match.getDouble("PTBDA") > maxPTBDA) {
					maxPTBDA = match.getDouble("PTBDA");
					Kladionica_PTBDA = match.getString("Kladionica");
				}
				if (match.has("PTBNE") && match.getDouble("PTBNE") > maxPTBNE) {
					maxPTBNE = match.getDouble("PTBNE");
					Kladionica_PTBNE = match.getString("Kladionica");
				}
				if (match.has("NS") && match.getDouble("NS") > maxNS) {
					maxNS = match.getDouble("NS");
					Kladionica_NS = match.getString("Kladionica");
				}
				if (match.has("SS") && match.getDouble("SS") > maxSS) {
					maxSS = match.getDouble("SS");
					Kladionica_SS = match.getString("Kladionica");
				}
				if (match.has("TS1") && match.getDouble("TS1") > maxTS1) {
					maxTS1 = match.getDouble("TS1");
					Kladionica_TS1 = match.getString("Kladionica");
				}
				if (match.has("TS2") && match.getDouble("TS2") > maxTS2) {
					maxTS2 = match.getDouble("TS2");
					Kladionica_TS2 = match.getString("Kladionica");
				}
				if (match.has("DS1") && match.getDouble("DS1") > maxDS1) {
					maxDS1 = match.getDouble("DS1");
					Kladionica_DS1 = match.getString("Kladionica");
				}
				if (match.has("DS2") && match.getDouble("DS2") > maxDS2) {
					maxDS2 = match.getDouble("DS2");
					Kladionica_DS2 = match.getString("Kladionica");
				}
				if (match.has("PS1") && match.getDouble("PS1") > maxPS1) {
					maxPS1 = match.getDouble("PS1");
					Kladionica_PS1 = match.getString("Kladionica");
				}
				if (match.has("PS2") && match.getDouble("PS2") > maxPS2) {
					maxPS2 = match.getDouble("PS2");
					Kladionica_PS2 = match.getString("Kladionica");
				}
				if (match.has("Igrac1W") && match.getDouble("Igrac1W") > max1) {
					max1 = match.getDouble("Igrac1W");
					Kladionica_1 = match.getString("Kladionica");
				}
				if (match.has("Igrac2W") && match.getDouble("Igrac2W") > max2) {
					max2 = match.getDouble("Igrac2W");
					Kladionica_2 = match.getString("Kladionica");
				}
			}
			brojUtakmica++;

			double x = 1000;
			double z = x * max1;
			double y = z / max2;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxPS1;
			y = z / maxPS2;
			double Profit2 = z - (x + y);
			double Procenat2 = (Profit2 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxDS1;
			y = z / maxDS2;
			double Profit3 = z - (x + y);
			double Procenat3 = (Profit3 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxTS1;
			y = z / maxTS2;
			double Profit4 = z - (x + y);
			double Procenat4 = (Profit4 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxSS;
			y = z / maxNS;
			double Profit5 = z - (x + y);
			double Procenat5 = (Profit5 / (x + y)) * 100;
			brojObradjenihObjekata++;

			z = x * maxPTBDA;
			y = z / maxPTBNE;
			double Profit6 = z - (x + y);
			double Procenat6 = (Profit6 / (x + y)) * 100;
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
			 * objekat.put("kladionica2", Kladionica_);// objekat.put("bet1", "");//
			 * objekat.put("bet2", "");// objekat.put("odd1", max);// objekat.put("odd2",
			 * max);// objekat.put("Procenat", Procenat);// objekat.put("Sport", "Tenis");
			 * 
			 * objekat.put("Profit", Profit);// objekat.put("Ulog1", x);
			 * objekat.put("Ulog2", y); objekat.put("Prolaz", z);
			 * 
			 * profitabilneUtakmice++; utakmice.put(objekat);
			 * pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString()); }
			 */
			if (Profit6 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_PTBDA);//
				objekat.put("kladionica2", Kladionica_PTBNE);//
				objekat.put("bet1", "PTBDA");//
				objekat.put("bet2", "PTBNE");//
				objekat.put("odd1", maxPTBDA);//
				objekat.put("odd2", maxPTBNE);//
				objekat.put("Procenat", Procenat6);//
				objekat.put("Sport", "Tenis");

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
				objekat.put("kladionica1", Kladionica_SS);//
				objekat.put("kladionica2", Kladionica_NS);//
				objekat.put("bet1", "SS");//
				objekat.put("bet2", "NS");//
				objekat.put("odd1", maxSS);//
				objekat.put("odd2", maxNS);//
				objekat.put("Procenat", Procenat5);//
				objekat.put("Sport", "Tenis");

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
				objekat.put("kladionica1", Kladionica_TS1);//
				objekat.put("kladionica2", Kladionica_TS2);//
				objekat.put("bet1", "TS1");//
				objekat.put("bet2", "TS2");//
				objekat.put("odd1", maxTS1);//
				objekat.put("odd2", maxTS2);//
				objekat.put("Procenat", Procenat4);//
				objekat.put("Sport", "Tenis");

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
				objekat.put("kladionica1", Kladionica_DS1);//
				objekat.put("kladionica2", Kladionica_DS2);//
				objekat.put("bet1", "DS1");//
				objekat.put("bet2", "DS2");//
				objekat.put("odd1", maxDS1);//
				objekat.put("odd2", maxDS2);//
				objekat.put("Procenat", Procenat3);//
				objekat.put("Sport", "Tenis");

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
				objekat.put("kladionica1", Kladionica_PS1);//
				objekat.put("kladionica2", Kladionica_PS2);//
				objekat.put("bet1", "PS1");//
				objekat.put("bet2", "PS2");//
				objekat.put("odd1", maxPS1);//
				objekat.put("odd2", maxPS1);//
				objekat.put("Procenat", Procenat2);//
				objekat.put("Sport", "Tenis");

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
				objekat.put("kladionica1", Kladionica_1);//
				objekat.put("kladionica2", Kladionica_2);//
				objekat.put("bet1", "1");//
				objekat.put("bet2", "2");//
				objekat.put("odd1", max1);//
				objekat.put("odd2", max2);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Tenis");

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}

		/////// UKUPNO GEMOVA////////////

		JSONArray ukupnoGemovaUtakmice = new JSONArray();
		for (JSONObject match : combinedList) {
			// int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");

			if (match.has("GranicaGemovi")) {
				double UG_MINUSValue = match.getDouble("G_MINUS");
				double UG_PLUSValue = match.getDouble("G_PLUS");
				String UGValue = match.getString("GranicaGemovi");

				JSONObject handicapDetails = new JSONObject();
				handicapDetails.put("home", home);
				handicapDetails.put("away", away);
				handicapDetails.put("Kladionica", kladionica);
				handicapDetails.put("Start", start);
				handicapDetails.put("Liga", liga);
				handicapDetails.put("ukupnoGemovaGr", UGValue);
				handicapDetails.put("UG_MINUS", UG_MINUSValue);
				handicapDetails.put("UG_PLUS", UG_PLUSValue);

				ukupnoGemovaUtakmice.put(handicapDetails);
			}
		}
		List<JSONObject> hendikepOT = new ArrayList<>();
		hendikepOT.addAll(jsonArrayToList(ukupnoGemovaUtakmice));

		List<List<JSONObject>> groupedMatchesHOT = hendikepOT.stream()
				.collect(
						Collectors
								.groupingBy(
										obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
												+ obj.getString("away") + "_" + obj.getString("ukupnoGemovaGr"),
										Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());

		for (List<JSONObject> matchGroupHOT : groupedMatchesHOT) {
			JSONObject firstMatch = matchGroupHOT.get(0);
			double maxUG_MINUS = 1.0;
			String Kladionica_UG_MINUS = "";
			double maxUG_PLUS = 1.0;
			String Kladionica_UG_PLUS = "";

			// System.out.println("tekma " + groupedMatchesHOT);
			for (JSONObject matchHOT : matchGroupHOT) {
				if (matchHOT.has("UG_PLUS") && matchHOT.getDouble("UG_PLUS") > maxUG_PLUS) {
					maxUG_PLUS = matchHOT.getDouble("UG_PLUS");
					Kladionica_UG_PLUS = matchHOT.getString("Kladionica");
				}
				if (matchHOT.has("UG_MINUS") && matchHOT.getDouble("UG_MINUS") > maxUG_MINUS) {
					maxUG_MINUS = matchHOT.getDouble("UG_MINUS");
					Kladionica_UG_MINUS = matchHOT.getString("Kladionica");
				}
				// System.out.println("tekma " + matchHOT);
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
				objekat.put("Sport", "Tenis");
				objekat.put("Granica", firstMatch.getDouble("ukupnoGemovaGr"));

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}

		///// HENDIKEP SET/////

		JSONArray hendikepSetUtakmice = new JSONArray();
		for (JSONObject match : combinedList) {
			// int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");

			if (match.has("HendikepSetGr1")) {
				double HS1Value = match.getDouble("HS1");
				double HS2Value = match.getDouble("HS2");
				String HSValue = match.getString("HendikepSetGr1");

				JSONObject handicapDetails = new JSONObject();
				handicapDetails.put("home", home);
				handicapDetails.put("away", away);
				handicapDetails.put("Kladionica", kladionica);
				handicapDetails.put("Start", start);
				handicapDetails.put("Liga", liga);
				handicapDetails.put("HendikepSetGr", HSValue);
				handicapDetails.put("HS1", HS1Value);
				handicapDetails.put("HS2", HS2Value);

				hendikepSetUtakmice.put(handicapDetails);
			}
		}
		List<JSONObject> hendikepSet = new ArrayList<>();
		hendikepSet.addAll(jsonArrayToList(hendikepSetUtakmice));

		List<List<JSONObject>> groupedMatchesHS = hendikepSet.stream()
				.collect(
						Collectors
								.groupingBy(
										obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
												+ obj.getString("away") + "_" + obj.getString("HendikepSetGr"),
										Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());

		for (List<JSONObject> matchGroupHS : groupedMatchesHS) {
			JSONObject firstMatch = matchGroupHS.get(0);
			double maxHS1 = 1.0;
			String Kladionica_HS1 = "";
			double maxHS2 = 1.0;
			String Kladionica_HS2 = "";

			// System.out.println("tekma " + groupedMatchesHOT);
			for (JSONObject matchHS : matchGroupHS) {
				if (matchHS.has("HS2") && matchHS.getDouble("HS2") > maxHS2) {
					maxHS2 = matchHS.getDouble("HS2");
					Kladionica_HS2 = matchHS.getString("Kladionica");
				}
				if (matchHS.has("HS1") && matchHS.getDouble("HS1") > maxHS1) {
					maxHS1 = matchHS.getDouble("HS1");
					Kladionica_HS1 = matchHS.getString("Kladionica");
				}
				// System.out.println("tekma " + matchHOT);
			}

			double x = 1000;
			double z = x * maxHS1;
			double y = z / maxHS2;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			if (Profit1 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_HS1);//
				objekat.put("kladionica2", Kladionica_HS2);//
				objekat.put("bet1", "HS1");//
				objekat.put("bet2", "HS2");//
				objekat.put("odd1", maxHS1);//
				objekat.put("odd2", maxHS2);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Tenis");
				objekat.put("Granica", firstMatch.getDouble("HendikepSetGr"));
				objekat.put("Rizik", "true");

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				// pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}

		///// HENDIKEP GEM///////////

		JSONArray hendikepGemUtakmice = new JSONArray();
		for (JSONObject match : combinedList) {
			// int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");

			if (match.has("HendikepGemGr1")) {
				double HG1Value = match.getDouble("HG1");
				double HG2Value = match.getDouble("HG2");
				String HGValue = match.getString("HendikepGemGr1");

				JSONObject handicapDetails = new JSONObject();
				handicapDetails.put("home", home);
				handicapDetails.put("away", away);
				handicapDetails.put("Kladionica", kladionica);
				handicapDetails.put("Start", start);
				handicapDetails.put("Liga", liga);
				handicapDetails.put("HendikepGemGr", HGValue);
				handicapDetails.put("HG1", HG1Value);
				handicapDetails.put("HG2", HG2Value);

				hendikepGemUtakmice.put(handicapDetails);
			}
		}
		List<JSONObject> hendikepGem = new ArrayList<>();
		hendikepGem.addAll(jsonArrayToList(hendikepGemUtakmice));

		List<List<JSONObject>> groupedMatchesHG = hendikepGem.stream()
				.collect(
						Collectors
								.groupingBy(
										obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
												+ obj.getString("away") + "_" + obj.getString("HendikepGemGr"),
										Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());

		for (List<JSONObject> matchGroupHG : groupedMatchesHG) {
			JSONObject firstMatch = matchGroupHG.get(0);
			double maxHG1 = 1.0;
			String Kladionica_HG1 = "";
			double maxHG2 = 1.0;
			String Kladionica_HG2 = "";

			// System.out.println("tekma " + groupedMatchesHOT);
			for (JSONObject matchHG : matchGroupHG) {
				if (matchHG.has("HG2") && matchHG.getDouble("HG2") > maxHG2) {
					maxHG2 = matchHG.getDouble("HG2");
					Kladionica_HG2 = matchHG.getString("Kladionica");
				}
				if (matchHG.has("HG1") && matchHG.getDouble("HG1") > maxHG1) {
					maxHG1 = matchHG.getDouble("HG1");
					Kladionica_HG1 = matchHG.getString("Kladionica");
				}
				// System.out.println("tekma " + matchHOT);
			}

			double x = 1000;
			double z = x * maxHG1;
			double y = z / maxHG2;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			if (Profit1 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_HG1);//
				objekat.put("kladionica2", Kladionica_HG2);//
				objekat.put("bet1", "HG1");//
				objekat.put("bet2", "HG2");//
				objekat.put("odd1", maxHG1);//
				objekat.put("odd2", maxHG2);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Tenis");
				objekat.put("Granica", firstMatch.getDouble("HendikepGemGr"));
				objekat.put("Rizik", "true");

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				// pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}

		//// UKUPNO GEMOVA PRVI SET////

		JSONArray UGPSUtakmice = new JSONArray();
		for (JSONObject match : combinedList) {
			int i = 1;
			String home = match.getString("home");
			String away = match.getString("away");
			String kladionica = match.getString("Kladionica");
			long start = match.getLong("Start");
			String liga = match.getString("Liga");
			for (i = 1; i <= 5; i++) {
				String UGPSKey = "UGPSGr" + i;
				String UGPS_MINUSKey = "UGPS_MINUS" + i;
				String UGPS_PLUSKey = "UGPS_PLUS" + i;

				if (match.has(UGPSKey) && match.has(UGPS_MINUSKey) && match.has(UGPS_PLUSKey)) {
					double UGPSValue = match.getDouble(UGPSKey);
					double UGPS_MINUSValue = match.getDouble(UGPS_MINUSKey);
					double UGPS_PLUSValue = match.getDouble(UGPS_PLUSKey);

					JSONObject handicapDetails = new JSONObject();
					handicapDetails.put("home", home);
					handicapDetails.put("away", away);
					handicapDetails.put("Kladionica", kladionica);
					handicapDetails.put("Start", start);
					handicapDetails.put("Liga", liga);
					handicapDetails.put("UGPSGr", UGPSValue);
					handicapDetails.put("UGPS_MINUS", UGPS_MINUSValue);
					handicapDetails.put("UGPS_PLUS", UGPS_PLUSValue);

					UGPSUtakmice.put(handicapDetails);
				}
			}
		}
		List<JSONObject> UGPS = new ArrayList<>();
		UGPS.addAll(jsonArrayToList(UGPSUtakmice));

		List<List<JSONObject>> groupedMatchesUGPS = UGPS.stream()
				.collect(
						Collectors
								.groupingBy(
										obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
												+ obj.getString("away") + "_" + obj.getDouble("UGPSGr"),
										Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());

		for (List<JSONObject> matchGroupUGPS : groupedMatchesUGPS) {
			JSONObject firstMatch = matchGroupUGPS.get(0);
			double maxUGPS_MINUS = 1.0;
			String Kladionica_UGPS_MINUS = "";
			double maxUGPS_PLUS = 1.0;
			String Kladionica_UGPS_PLUS = "";

			// System.out.println("tekma " + groupedMatchesQ4H);
			for (JSONObject matchUGPS : matchGroupUGPS) {
				if (matchUGPS.has("UGPS_MINUS") && matchUGPS.getDouble("UGPS_MINUS") > maxUGPS_MINUS) {
					maxUGPS_MINUS = matchUGPS.getDouble("UGPS_MINUS");
					Kladionica_UGPS_MINUS = matchUGPS.getString("Kladionica");
				}
				if (matchUGPS.has("UGPS_PLUS") && matchUGPS.getDouble("UGPS_PLUS") > maxUGPS_PLUS) {
					maxUGPS_PLUS = matchUGPS.getDouble("UGPS_PLUS");
					Kladionica_UGPS_PLUS = matchUGPS.getString("Kladionica");
				}
				// System.out.println("tekma " + matchQ4H);
			}

			double x = 1000;
			double z = x * maxUGPS_MINUS;
			double y = z / maxUGPS_PLUS;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;

			if (Profit1 > 0) {
				JSONObject objekat = new JSONObject();
				objekat.put("home", firstMatch.getString("home"));
				objekat.put("away", firstMatch.getString("away"));
				objekat.put("Start", firstMatch.getLong("Start"));
				objekat.put("Liga", firstMatch.getString("Liga"));
				objekat.put("kladionica1", Kladionica_UGPS_MINUS);//
				objekat.put("kladionica2", Kladionica_UGPS_PLUS);//
				objekat.put("bet1", "UGPS_MINUS");//
				objekat.put("bet2", "UGPS_PLUS");//
				objekat.put("odd1", maxUGPS_MINUS);//
				objekat.put("odd2", maxUGPS_PLUS);//
				objekat.put("Procenat", Procenat1);//
				objekat.put("Sport", "Košarka");
				objekat.put("Granica", firstMatch.getDouble("UGPSGr"));

				objekat.put("Profit", Profit1);//
				objekat.put("Ulog1", x);
				objekat.put("Ulog2", y);
				objekat.put("Prolaz", z);

				profitabilneUtakmice++;
				utakmice.put(objekat);
				// pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
			}
		}
		
	/////// UKUPNO GEMOVA DRUGI SET////////////

			JSONArray UGDSUtakmice = new JSONArray();
			for (JSONObject match : combinedList) {
				int i = 1;
				String home = match.getString("home");
				String away = match.getString("away");
				String kladionica = match.getString("Kladionica");
				long start = match.getLong("Start");
				String liga = match.getString("Liga");

				for (i = 1; i <= 5; i++) {
					String UGDSKey = "UGDSGr" + i;
					String UGDS_MINUSKey = "UGDS_MINUS" + i;
					String UGDS_PLUSKey = "UGDS_PLUS" + i;

					if (match.has(UGDSKey) && match.has(UGDS_MINUSKey) && match.has(UGDS_PLUSKey)) {
						String UGDSValue = match.getString(UGDSKey);
						double UGDS_MINUSValue = match.getDouble(UGDS_MINUSKey);
						double UGDS_PLUSValue = match.getDouble(UGDS_PLUSKey);

						JSONObject handicapDetails = new JSONObject();
						handicapDetails.put("home", home);
						handicapDetails.put("away", away);
						handicapDetails.put("Kladionica", kladionica);
						handicapDetails.put("Start", start);
						handicapDetails.put("Liga", liga);
						handicapDetails.put("UGDSGr", UGDSValue);
						handicapDetails.put("UGDS_MINUS", UGDS_MINUSValue);
						handicapDetails.put("UGDS_PLUS", UGDS_PLUSValue);

						UGDSUtakmice.put(handicapDetails);
					}
				}
			}
			List<JSONObject> UGDS = new ArrayList<>();
			UGDS.addAll(jsonArrayToList(UGDSUtakmice));

			List<List<JSONObject>> groupedMatchesUGDS = UGDS.stream()
					.collect(
							Collectors
									.groupingBy(
											obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
													+ obj.getString("away") + "_" + obj.getString("UGDSGr"),
											Collectors.toList()))
					.values().stream()
					.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
					.collect(Collectors.toList());

			for (List<JSONObject> matchGroupUGDS : groupedMatchesUGDS) {
				JSONObject firstMatch = matchGroupUGDS.get(0);
				double maxUGDS_MINUS = 1.0;
				String Kladionica_UGDS_MINUS = "";
				double maxUGDS_PLUS = 1.0;
				String Kladionica_UGDS_PLUS = "";

				// System.out.println("tekma " + groupedMatchesHOT);
				for (JSONObject matchUGDS : matchGroupUGDS) {
					if (matchUGDS.has("UGDS_PLUS") && matchUGDS.getDouble("UGDS_PLUS") > maxUGDS_PLUS) {
						maxUGDS_PLUS = matchUGDS.getDouble("UG_PLUS");
						Kladionica_UGDS_PLUS = matchUGDS.getString("Kladionica");
					}
					if (matchUGDS.has("UGDS_MINUS") && matchUGDS.getDouble("UGDS_MINUS") > maxUGDS_MINUS) {
						maxUGDS_MINUS = matchUGDS.getDouble("UGDS_MINUS");
						Kladionica_UGDS_MINUS = matchUGDS.getString("Kladionica");
					}
					// System.out.println("tekma " + matchHOT);
				}

				double x = 1000;
				double z = x * maxUGDS_MINUS;
				double y = z / maxUGDS_PLUS;
				double Profit1 = z - (x + y);
				double Procenat1 = (Profit1 / (x + y)) * 100;
				brojObradjenihObjekata++;

				if (Profit1 > 0) {
					JSONObject objekat = new JSONObject();
					objekat.put("home", firstMatch.getString("home"));
					objekat.put("away", firstMatch.getString("away"));
					objekat.put("Start", firstMatch.getLong("Start"));
					objekat.put("Liga", firstMatch.getString("Liga"));
					objekat.put("kladionica1", Kladionica_UGDS_MINUS);//
					objekat.put("kladionica2", Kladionica_UGDS_PLUS);//
					objekat.put("bet1", "UGDS_MINUS");//
					objekat.put("bet2", "UGDS_PLUS");//
					objekat.put("odd1", maxUGDS_MINUS);//
					objekat.put("odd2", maxUGDS_PLUS);//
					objekat.put("Procenat", Procenat1);//
					objekat.put("Sport", "Tenis");
					objekat.put("Granica", firstMatch.getDouble("UGDSGr"));

					objekat.put("Profit", Profit1);//
					objekat.put("Ulog1", x);
					objekat.put("Ulog2", y);
					objekat.put("Prolaz", z);

					profitabilneUtakmice++;
					utakmice.put(objekat);
					pozoviAPI.posaljiPostZahtev(apiUrl, objekat.toString());
				}
			}
			
			////UKUPNO GEMOVA TRECI SET////
			
			JSONArray UGTSUtakmice = new JSONArray();
			for (JSONObject match : combinedList) {
				int i = 1;
				String home = match.getString("home");
				String away = match.getString("away");
				String kladionica = match.getString("Kladionica");
				long start = match.getLong("Start");
				String liga = match.getString("Liga");

				for (i = 1; i <= 5; i++) {
					String UGTSKey = "UGTSGr" + i;
					String UGTS_MINUSKey = "UGTS_MINUS" + i;
					String UGTS_PLUSKey = "UGTS_PLUS" + i;

					if (match.has(UGTSKey) && match.has(UGTS_MINUSKey) && match.has(UGTS_PLUSKey)) {
						String UGTSValue = match.getString(UGTSKey);
						double UGTS_MINUSValue = match.getDouble(UGTS_MINUSKey);
						double UGTS_PLUSValue = match.getDouble(UGTS_PLUSKey);

						JSONObject handicapDetails = new JSONObject();
						handicapDetails.put("home", home);
						handicapDetails.put("away", away);
						handicapDetails.put("Kladionica", kladionica);
						handicapDetails.put("Start", start);
						handicapDetails.put("Liga", liga);
						handicapDetails.put("UGTSGr", UGTSValue);
						handicapDetails.put("UGTS_MINUS", UGTS_MINUSValue);
						handicapDetails.put("UGTS_PLUS", UGTS_PLUSValue);

						UGTSUtakmice.put(handicapDetails);
					}
				}
			}
			List<JSONObject> UGTS = new ArrayList<>();
			UGTS.addAll(jsonArrayToList(UGTSUtakmice));

			List<List<JSONObject>> groupedMatchesUGTS = UGTS.stream()
					.collect(
							Collectors
									.groupingBy(
											obj -> obj.getLong("Start") + "_" + obj.getString("home") + "_"
													+ obj.getString("away") + "_" + obj.getString("UGTSGr"),
											Collectors.toList()))
					.values().stream()
					.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
					.collect(Collectors.toList());

			for (List<JSONObject> matchGroupUGTS : groupedMatchesUGTS) {
				JSONObject firstMatch = matchGroupUGTS.get(0);
				double maxUGTS_MINUS = 1.0;
				String Kladionica_UGTS_MINUS = "";
				double maxUGTS_PLUS = 1.0;
				String Kladionica_UGTS_PLUS = "";

				// System.out.println("tekma " + groupedMatchesHOT);
				for (JSONObject matchUGTS : matchGroupUGTS) {
					if (matchUGTS.has("UGTS_PLUS") && matchUGTS.getDouble("UGTS_PLUS") > maxUGTS_PLUS) {
						maxUGTS_PLUS = matchUGTS.getDouble("UG_PLUS");
						Kladionica_UGTS_PLUS = matchUGTS.getString("Kladionica");
					}
					if (matchUGTS.has("UGTS_MINUS") && matchUGTS.getDouble("UGTS_MINUS") > maxUGTS_MINUS) {
						maxUGTS_MINUS = matchUGTS.getDouble("UGTS_MINUS");
						Kladionica_UGTS_MINUS = matchUGTS.getString("Kladionica");
					}
					// System.out.println("tekma " + matchHOT);
				}

				double x = 1000;
				double z = x * maxUGTS_MINUS;
				double y = z / maxUGTS_PLUS;
				double Profit1 = z - (x + y);
				double Procenat1 = (Profit1 / (x + y)) * 100;
				brojObradjenihObjekata++;

				if (Profit1 > 0) {
					JSONObject objekat = new JSONObject();
					objekat.put("home", firstMatch.getString("home"));
					objekat.put("away", firstMatch.getString("away"));
					objekat.put("Start", firstMatch.getLong("Start"));
					objekat.put("Liga", firstMatch.getString("Liga"));
					objekat.put("kladionica1", Kladionica_UGTS_MINUS);//
					objekat.put("kladionica2", Kladionica_UGTS_PLUS);//
					objekat.put("bet1", "UGTS_MINUS");//
					objekat.put("bet2", "UGTS_PLUS");//
					objekat.put("odd1", maxUGTS_MINUS);//
					objekat.put("odd2", maxUGTS_PLUS);//
					objekat.put("Procenat", Procenat1);//
					objekat.put("Sport", "Tenis");
					objekat.put("Granica", firstMatch.getDouble("UGTSGr"));

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
