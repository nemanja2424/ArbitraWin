package kalkulator;

import org.arbet3.arbet3.App;
import org.json.JSONArray;
import org.json.JSONObject;

import Fudbal.pozoviAPI;
import kosarkasi.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Kosarkasi {

	String apiUrl = "https://x8ki-letl-twmt.n7.xano.io/api:etI0bpsP/obradjene_tekme";

	public static void main(String[] args) {
		Kosarkasi kosarkasi = new Kosarkasi();
		kosarkasi.izvrsiAnalizu();
	}

	public void izvrsiAnalizu() {
        JSONArray maxBetArray = MaxbetKS.praviAPI();
        JSONArray merkurArray = MerkurKS.praviAPI();
        JSONArray betOleArray = BetOleKS.praviAPI();

        List<JSONObject> combinedList = new ArrayList<>();
        for (int i = 0; i < maxBetArray.length(); i++) {
            combinedList.add(maxBetArray.getJSONObject(i));
        }
        for (int i = 0; i < merkurArray.length(); i++) {
            combinedList.add(merkurArray.getJSONObject(i));
        }
        for (int i = 0; i < betOleArray.length(); i++) {
            combinedList.add(betOleArray.getJSONObject(i));
        }

		// Filtriranje mečeva
    	List<List<JSONObject>> groupedMatchesSve = combinedList.stream()
				.collect(Collectors.groupingBy(
						obj -> obj.getLong("Start") + "_" + obj.getString("Igrac") + "_" + obj.getDouble("GranicaPoena"),
						Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());
    	
        List<List<JSONObject>> groupedMatchesPoeni = combinedList.stream()
				.collect(Collectors.groupingBy(
						obj -> obj.getLong("Start") + "_" + obj.getString("Igrac") + "_" + obj.getDouble("GranicaPoena"),
						Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());
		
		List<List<JSONObject>> groupedMatchesSkokovi = combinedList.stream()
				.collect(Collectors.groupingBy(
						obj -> obj.getLong("Start") + "_" + obj.getString("Igrac") + "_" + obj.getDouble("GranicaSkokova"),
						Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());
		
		List<List<JSONObject>> groupedMatchesAsistencije = combinedList.stream()
				.collect(Collectors.groupingBy(
						obj -> obj.getLong("Start") + "_" + obj.getString("Igrac") + "_" + obj.getDouble("GranicaAsistencija"),
						Collectors.toList()))
				.values().stream()
				.filter(list -> list.stream().map(obj -> obj.getString("Kladionica")).distinct().count() > 1)
				.collect(Collectors.toList());

        JSONArray utakmice = new JSONArray();
        int brojObradjenihObjekata = 0;
        int brojObradjenihObjekataPoeni = 0;
        int brojObradjenihObjekataAsistencije = 0;
        int brojObradjenihObjekataSkokovi = 0;

        int brojUtakmica = 0;
        int brojUtakmicaPoeni = 0;
        int brojUtakmicaAsistencije = 0;
        int brojUtakmicaSkokovi = 0;
        
        int profitabilneUtakmice = 0;
        int profitabilneUtakmicePoeni = 0;
        int profitabilneUtakmiceAsistencije = 0;
        int profitabilneUtakmiceSkokovi = 0;

        
        //System.out.println("Sve" + groupedMatchesSve);
        //System.out.println("Poeni" + groupedMatchesPoeni);
        //System.out.println("Skokovi" + groupedMatchesSkokovi);
       // System.out.println("Asistencije" + groupedMatchesAsistencije);

       //Poeni
       for (List<JSONObject> matchGroupPoeni : groupedMatchesPoeni) {
			JSONObject firstMatch = matchGroupPoeni.get(0);
    	   
        	double maxVisePoena = 0.0;
        	String Kladionica_VisePoena = "";
        	double maxManjePoena = 0.0;
        	String Kladionica_ManjePoena = "";
        	
        	for (JSONObject matchPoeni : matchGroupPoeni) {
        		if (matchPoeni.has("VisePoena") && matchPoeni.getDouble("VisePoena") > maxVisePoena) {
        			maxVisePoena = matchPoeni.getDouble("VisePoena");
        			Kladionica_VisePoena = matchPoeni.getString("Kladionica");
        		}
        		if (matchPoeni.has("ManjePoena") && matchPoeni.getDouble("ManjePoena") > maxManjePoena) {
        			maxManjePoena = matchPoeni.getDouble("ManjePoena");
        			Kladionica_ManjePoena = matchPoeni.getString("Kladionica");
        		}
        	}
        	
        	brojUtakmica++;
        	brojUtakmicaPoeni++;
        	
        	double x = 1000;
			double z = x * maxVisePoena;
			double y = z / maxManjePoena;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;
			brojObradjenihObjekataPoeni++;
			
			if (Profit1 > 0) {
				JSONObject objekat1 = new JSONObject();
				objekat1.put("home", firstMatch.getString("Igrac"));
				objekat1.put("away", firstMatch.getString("Tim"));
				objekat1.put("Start", firstMatch.getLong("Start"));
				objekat1.put("Liga", firstMatch.getString("Liga"));
				objekat1.put("Granica", firstMatch.getDouble("GranicaPoena"));
				objekat1.put("kladionica1", Kladionica_VisePoena);//
				objekat1.put("kladionica2", Kladionica_ManjePoena);//
				objekat1.put("bet1", "Više poena");//
				objekat1.put("bet2", "Manje poena");//
				objekat1.put("odd1", maxVisePoena);//
				objekat1.put("odd2", maxManjePoena);//
				objekat1.put("Procenat", Procenat1);//
				objekat1.put("Sport", "Specijal košarka");
				
				objekat1.put("Profit", Profit1);//
				objekat1.put("Ulog1", x);
				objekat1.put("Ulog2", y);
				objekat1.put("Prolaz", z);
								
				profitabilneUtakmice++;
				profitabilneUtakmicePoeni++;
				utakmice.put(objekat1);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat1.toString());
			}
        }
       
       //Asistencije
       for (List<JSONObject> matchGroupAsistencije : groupedMatchesAsistencije) {
			JSONObject firstMatch = matchGroupAsistencije.get(0);
   	   
       	double maxViseAsistencija = 0.0;
       	String Kladionica_ViseAsistencija = "";
       	double maxManjeAsistencija = 0.0;
       	String Kladionica_ManjeAsistencija = "";
       	
       	for (JSONObject matchAsistencije : matchGroupAsistencije) {
       		if (matchAsistencije.has("ViseAsistencija") && matchAsistencije.getDouble("ViseAsistencija") > maxViseAsistencija) {
       			maxViseAsistencija = matchAsistencije.getDouble("ViseAsistencija");
       			Kladionica_ViseAsistencija = matchAsistencije.getString("Kladionica");
       		}
       		if (matchAsistencije.has("ManjeAsistencija") && matchAsistencije.getDouble("ManjeAsistencija") > maxManjeAsistencija) {
       			maxManjeAsistencija = matchAsistencije.getDouble("ManjeAsistencija");
       			Kladionica_ManjeAsistencija = matchAsistencije.getString("Kladionica");
       		}
       	}
       	
       	brojUtakmica++;
       	brojUtakmicaAsistencije++;
       	
       	double x = 1000;
			double z = x * maxViseAsistencija;
			double y = z / maxManjeAsistencija;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;
			brojObradjenihObjekataAsistencije++;
			
			if (Profit1 > 0) {
				JSONObject objekat1 = new JSONObject();
				objekat1.put("home", firstMatch.getString("Igrac"));
				objekat1.put("away", firstMatch.getString("Tim"));
				objekat1.put("Start", firstMatch.getLong("Start"));
				objekat1.put("Liga", firstMatch.getString("Liga"));
				objekat1.put("Granica", firstMatch.getDouble("GranicaAsistencija"));
				objekat1.put("kladionica1", Kladionica_ViseAsistencija);//
				objekat1.put("kladionica2", Kladionica_ManjeAsistencija);//
				objekat1.put("bet1", "Više asistencija");//
				objekat1.put("bet2", "Manje asistencija");//
				objekat1.put("odd1", maxViseAsistencija);//
				objekat1.put("odd2", maxManjeAsistencija);//
				objekat1.put("Procenat", Procenat1);//
				objekat1.put("Sport", "Specijal košarka");
				
				objekat1.put("Profit", Profit1);//
				objekat1.put("Ulog1", x);
				objekat1.put("Ulog2", y);
				objekat1.put("Prolaz", z);
								
				profitabilneUtakmice++;
				profitabilneUtakmiceAsistencije++;
				utakmice.put(objekat1);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat1.toString());
			}
       }
       //Skokovi
       for (List<JSONObject> matchGroupSkokovi : groupedMatchesSkokovi) {
			JSONObject firstMatch = matchGroupSkokovi.get(0);
  	   
      	double maxViseSkokova = 0.0;
      	String Kladionica_ViseSkokova = "";
      	double maxManjeSkokova = 0.0;
      	String Kladionica_ManjeSkokova = "";
      	
      	for (JSONObject matchSkokovi : matchGroupSkokovi) {
      		if (matchSkokovi.has("ViseSkokova") && matchSkokovi.getDouble("ViseSkokova") > maxViseSkokova) {
      			maxViseSkokova = matchSkokovi.getDouble("ViseSkokova");
      			Kladionica_ViseSkokova = matchSkokovi.getString("Kladionica");
      		}
      		if (matchSkokovi.has("ManjeSkokova") && matchSkokovi.getDouble("ManjeSkokova") > maxManjeSkokova) {
      			maxManjeSkokova = matchSkokovi.getDouble("ManjeSkokova");
      			Kladionica_ManjeSkokova = matchSkokovi.getString("Kladionica");
      		}
      	}
      	
      	brojUtakmica++;
      	brojUtakmicaSkokovi++;
      	
      	double x = 1000;
			double z = x * maxViseSkokova;
			double y = z / maxManjeSkokova;
			double Profit1 = z - (x + y);
			double Procenat1 = (Profit1 / (x + y)) * 100;
			brojObradjenihObjekata++;
			brojObradjenihObjekataSkokovi++;
			
			if (Profit1 > 0) {
				JSONObject objekat1 = new JSONObject();
				objekat1.put("home", firstMatch.getString("Igrac"));
				objekat1.put("away", firstMatch.getString("Tim"));
				objekat1.put("Start", firstMatch.getLong("Start"));
				objekat1.put("Liga", firstMatch.getString("Liga"));
				objekat1.put("Granica", firstMatch.getDouble("GranicaSkokova"));
				objekat1.put("kladionica1", Kladionica_ViseSkokova);//
				objekat1.put("kladionica2", Kladionica_ManjeSkokova);//
				objekat1.put("bet1", "Više skokova");//
				objekat1.put("bet2", "Manje skokova");//
				objekat1.put("odd1", maxViseSkokova);//
				objekat1.put("odd2", maxManjeSkokova);//
				objekat1.put("Procenat", Procenat1);//
				objekat1.put("Sport", "Specijal košarka");
				
				objekat1.put("Profit", Profit1);//
				objekat1.put("Ulog1", x);
				objekat1.put("Ulog2", y);
				objekat1.put("Prolaz", z);
								
				profitabilneUtakmice++;
				profitabilneUtakmiceSkokovi++;
				utakmice.put(objekat1);
				pozoviAPI.posaljiPostZahtev(apiUrl, objekat1.toString());
			}
      }
       LocalDateTime now = LocalDateTime.now();
       
       System.out.println("------------------------------------------------------------------------------------------------------------------");
       System.out.println("Obradjeni objekti: " + brojObradjenihObjekata + " Poeni: " + brojObradjenihObjekataPoeni + " Asistencije: " + brojObradjenihObjekataAsistencije + " Skokovi: " + brojObradjenihObjekataSkokovi);
       System.out.println("Broj utakmica: " + brojUtakmica + " Poeni: " + brojUtakmicaPoeni + "Asistencije: " + brojUtakmicaAsistencije + " Skokovi: " + brojUtakmicaSkokovi);
       System.out.println("Profitabilne utakmice:"+ profitabilneUtakmice +  " Poeni:" + profitabilneUtakmicePoeni + " Asistencije: " + profitabilneUtakmiceAsistencije + " Skokovi: " + profitabilneUtakmiceSkokovi);
       //System.out.println("Profitabilno: " + utakmice);
       System.out.println("Vreme: " + now);
       System.out.println("------------------------------------------------------------------------------------------------------------------");
       
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
