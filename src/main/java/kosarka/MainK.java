package kosarka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainK {

	/*public static void main(String[] args) {
		JSONArray maxbetData = MaxbetT.praviAPI();
		JSONArray merkurData = MerkurT.praviAPI();
		JSONArray soccerbetData = SoccerBetT.praviAPI();
		JSONArray betoleData = BetOleT.praviAPI();
		JSONArray oktagonData = OktagonBetT.praviAPI();

		// Konekcija sa bazom podataka
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/arbetproba", "root",
				"")) {
			// Brisanje svih podataka iz tabele Fudbal
			obrisiSvePodatkeIzTabele(connection);

			dodajPodatkeUDB(connection, maxbetData);
			dodajPodatkeUDB(connection, merkurData);
			dodajPodatkeUDB(connection, soccerbetData);
			dodajPodatkeUDB(connection, betoleData);
			dodajPodatkeUDB(connection, oktagonData);

		} catch (SQLException e) {
			System.err.println("Došlo je do greške prilikom povezivanja sa bazom podataka: " + e.getMessage());
		}
	}

	// Metoda za brisanje svih podataka iz tabele Fudbal
	private static void obrisiSvePodatkeIzTabele(Connection connection) throws SQLException {
		String deleteQuery = "DELETE FROM Tenis";
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.executeUpdate();
			System.out.println("Svi podaci su uspešno obrisani iz tabele Tenis");
		} catch (SQLException e) {
			throw new SQLException("Došlo je do greške prilikom brisanja podataka iz tabele Kosarkasi: " + e.getMessage());
		}
	}
	
	private static void dodajPodatkeUDB(Connection connection, JSONArray jsonData) throws SQLException {
		
		for (int i = 0; i < jsonData.length(); i++) {
			Object obj = jsonData.get(i);
			if (obj instanceof JSONObject) {
				JSONObject jsonObject = (JSONObject) obj;
				
				String insertQuery = "INSERT INTO Tenis (Kladionica, Liga, Start, Igrac1, Igrac2, Igrac1W, Igrac2W, PS1, PS2, DS1, DS2, TS1, TS2, SS, NS, PTBDA, PTBNE, PARG, NEPARG, GranicaGemovi, G_PLUS, G_MINUS, GranicaGemovi2, G_PLUS2, G_MINUS2, GranicaGemovi3, G_PLUS3, G_MINUS3, GranicaGemovi4, G_PLUS4, G_MINUS4, GranicaGemovi5, G_PLUS5, G_MINUS5, GranicaGemovi6, G_PLUS6, G_MINUS6, GranicaGemovi7, G_PLUS7, G_MINUS7, GranicaGemovi8, G_PLUS8, G_MINUS8, GranicaGemovi9, G_PLUS9, G_MINUS9, HendikepGemGr1, HG1, HG2, HendikepGemGr2, HG1_2, HG2_2, HendikepGemGr3, HG1_3, HG2_3, HendikepGemGr4, HG1_4, HG2_4, HendikepGemGr5, HG1_5, HG2_5, HendikepGemGr6, HG1_6, HG2_6, HendikepGemGr7, HG1_7, HG2_7, HendikepGemGr8, HG1_8, HG2_8, HendikepGemGr9, HG1_9, HG2_9, HendikepGemGr10, HG1_10, HG2_10, HendikepGemGr11, HG1_11, HG2_11, HendikepGemGr12, HG1_12, HG2_12, HendikepGemGr13, HG1_13, HG2_13, HendikepGemGr14, HG1_14, HG2_14, HendikepGemGr15, HG1_15, HG2_15, HendikepGemGr16, HG1_16, HG2_16, HendikepSetGr1, HS1, HS2, HendikepSetGr2, HS1_2, HS2_2, HendikepSetGr3, HS1_3, HS2_3, HendikepSetGr4, HS1_4, HS2_4, HendikepSetGr5, HS1_5, HS2_5, HendikepSetGr6, HS1_6, HS2_6, UGPSGr1, UGPS_PLUS1, UGPS_MINUS1, UGPSGr2, UGPS_PLUS2, UGPS_MINUS2, UGPSGr3, UGPS_PLUS3, UGPS_MINUS3, UGPSGr4, UGPS_PLUS4, UGPS_MINUS4, UGPSGr5, UGPS_PLUS5, UGPS_MINUS5, UGDSGr1, UGDS_PLUS1, UGDS_MINUS1, UGDSGr2, UGDS_PLUS2, UGDS_MINUS2, UGDSGr3, UGDS_PLUS3, UGDS_MINUS3, UGDSGr4, UGDS_PLUS4, UGDS_MINUS4, UGDSGr5, UGDS_PLUS5, UGDS_MINUS5, UGTSGr1, UGTS_PLUS1, UGTS_MINUS1, UGTSGr2, UGTS_PLUS2, UGTS_MINUS2, UGTSGr3, UGTS_PLUS3, UGTS_MINUS3, UGTSGr4, UGTS_PLUS4, UGTS_MINUS4, UGTSGr5, UGTS_PLUS5, UGTS_MINUS5)  "
						+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
					
					
					preparedStatement.executeUpdate();
				}
				catch (SQLException e) {
					throw new SQLException(
							"Došlo je do greške prilikom dodavanja podataka u tabelu Tenis: " + e.getMessage());
				}
			} else {
				System.err.println("Element na poziciji " + i + " nije JSON objekat: " + obj.toString());
			}
		}
		System.out.println("Podaci su uspešno dodati u tabelu Tenis");
	}
	
	
	private static double getValueOrDefault(JSONObject jsonObject, String key, double defaultValue) {
		if (jsonObject.has(key)) {
			return jsonObject.getDouble(key);
		} else {
			return defaultValue;
		}
	}
	private static String getValueOrDefault(JSONObject jsonObject, String key, String defaultValue) {
		if (jsonObject.has(key)) {
			return jsonObject.getString(key);
		}
		else {
			return defaultValue;
		}
	}
	*/
	
	public static void main(String[] args) {
		//MaxbetK.praviAPI();
		//OktagonBetK.praviAPI();
		SoccerBetK.praviAPI();
		//MerkurK.praviAPI();
		//BetOleK.praviAPI();
		
	}

}
