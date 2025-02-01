package Fudbal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainF {
	
		static JSONArray maxbetData = MaxbetF.praviAPI();
		static JSONArray merkurData = MerkurF.praviAPI();
		static JSONArray soccerBetData = SoccerBetF.praviAPI();
		static JSONArray betOleData = BetOleF.praviAPI();
		static JSONArray oktagonData = OktagonBetF.praviAPI();
		static String apiUrl = "https://x8ki-letl-twmt.n7.xano.io/api:etI0bpsP/obradjene_tekme";
		
	public static void main(String[] args) {
		// Konekcija sa bazom podataka
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/arbetproba", "root",
				"")) {
			// Brisanje svih podataka iz tabele Fudbal
			obrisiSvePodatkeIzTabele(connection);

			dodajPodatkeUDB(connection, maxbetData);
			dodajPodatkeUDB(connection, merkurData);
			dodajPodatkeUDB(connection, soccerBetData);
			dodajPodatkeUDB(connection, betOleData);
			dodajPodatkeUDB(connection, oktagonData);
		} catch (SQLException e) {
			System.err.println("Došlo je do greške prilikom povezivanja sa bazom podataka: " + e.getMessage());
		}
	}

	// Metoda za brisanje svih podataka iz tabele Fudbal
	public static void obrisiSvePodatkeIzTabele(Connection connection) throws SQLException {
		String deleteQuery = "DELETE FROM Fudbal";
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.executeUpdate();
			System.out.println("Svi podaci su uspešno obrisani iz tabele Fudbal");
		} catch (SQLException e) {
			throw new SQLException("Došlo je do greške prilikom brisanja podataka iz tabele Fudbal: " + e.getMessage());
		}
	}

	// Metoda za dinamičko dodavanje podataka u tabelu Fudbal
	// Metoda za dinamičko dodavanje podataka u tabelu Fudbal
	public static void dodajPodatkeUDB(Connection connection, JSONArray jsonData) throws SQLException {
		// Iteriranje kroz JSON niz i dodavanje svakog objekta u tabelu
		for (int i = 0; i < jsonData.length(); i++) {
			Object obj = jsonData.get(i);
			if (obj instanceof JSONObject) {
				JSONObject jsonObject = (JSONObject) obj;
				// System.out.println("Ključevi u JSON objektu: " + jsonObject.keySet()); //
				// Debug ispis

				String insertQuery = "INSERT INTO Fudbal (Kladionica, Utakmica, Liga, Start, home, away, GG, NG, GG2, NG2, GG1, NG1, W1, W2, 1PW1, 1PW2, 2PW1, 2PW2, 1X, X2, UG1P1_PLUS, UG1P2_PLUS, UG1P3_PLUS, UG1P0_1, UG1P0_2, UG2P1_PLUS, UG2P2_PLUS, UG2P3_PLUS, UG2P0_1, UG2P0_2, D2_PLUS, D0_2, D3_PLUS, G2_PLUS, G0_2, G3_PLUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

				try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
					preparedStatement.setString(1, jsonObject.getString("Kladionica"));
					preparedStatement.setString(2, jsonObject.getString("Utakmica"));
					preparedStatement.setString(3, jsonObject.getString("Liga"));
					preparedStatement.setLong(4, jsonObject.getLong("Start"));
					preparedStatement.setString(5, jsonObject.getString("home"));
					preparedStatement.setString(6, jsonObject.getString("away"));

					// Postavljanje podrazumevane vrednosti za sve kolone
					preparedStatement.setDouble(7, getValueOrDefault(jsonObject, "GG", 1));
					preparedStatement.setDouble(8, getValueOrDefault(jsonObject, "NG", 1));
					preparedStatement.setDouble(9, getValueOrDefault(jsonObject, "GG2", 1));
					preparedStatement.setDouble(10, getValueOrDefault(jsonObject, "NG2", 1));
					preparedStatement.setDouble(11, getValueOrDefault(jsonObject, "GG1", 1));
					preparedStatement.setDouble(12, getValueOrDefault(jsonObject, "NG1", 1));
					preparedStatement.setDouble(13, getValueOrDefault(jsonObject, "21", 1));
					preparedStatement.setDouble(14, getValueOrDefault(jsonObject, "W2", 1));
					preparedStatement.setDouble(15, getValueOrDefault(jsonObject, "1PW1", 1));
					preparedStatement.setDouble(16, getValueOrDefault(jsonObject, "1PW2", 1));
					preparedStatement.setDouble(17, getValueOrDefault(jsonObject, "2PW1", 1));
					preparedStatement.setDouble(18, getValueOrDefault(jsonObject, "2PW2", 1));
					preparedStatement.setDouble(19, getValueOrDefault(jsonObject, "1X", 1));
					preparedStatement.setDouble(20, getValueOrDefault(jsonObject, "X2", 1));
					preparedStatement.setDouble(21, getValueOrDefault(jsonObject, "UG1P1+", 1));
					preparedStatement.setDouble(22, getValueOrDefault(jsonObject, "UG1P2", 1));
					preparedStatement.setDouble(23, getValueOrDefault(jsonObject, "UG1P3", 1));
					preparedStatement.setDouble(24, getValueOrDefault(jsonObject, "UG1P0-1", 1));
					preparedStatement.setDouble(25, getValueOrDefault(jsonObject, "UG1P0-2", 1));
					preparedStatement.setDouble(26, getValueOrDefault(jsonObject, "UG2P1+", 1));
					preparedStatement.setDouble(27, getValueOrDefault(jsonObject, "UG2P2+", 1));
					preparedStatement.setDouble(28, getValueOrDefault(jsonObject, "UG2P3+", 1));
					preparedStatement.setDouble(29, getValueOrDefault(jsonObject, "UG2P0-1", 1));
					preparedStatement.setDouble(30, getValueOrDefault(jsonObject, "UG2P0-2", 1));
					preparedStatement.setDouble(31, getValueOrDefault(jsonObject, "D2+", 1));
					preparedStatement.setDouble(32, getValueOrDefault(jsonObject, "D0-2", 1));
					preparedStatement.setDouble(33, getValueOrDefault(jsonObject, "D3+", 1));
					preparedStatement.setDouble(34, getValueOrDefault(jsonObject, "G2+", 1));
					preparedStatement.setDouble(35, getValueOrDefault(jsonObject, "G0-2", 1));
					preparedStatement.setDouble(36, getValueOrDefault(jsonObject, "G3+", 1));

					// Izvršavanje SQL upita za dodavanje podataka u tabelu
					preparedStatement.executeUpdate();
				} catch (SQLException e) {
					throw new SQLException(
							"Došlo je do greške prilikom dodavanja podataka u tabelu Fudbal: " + e.getMessage());
				}
			} else {
				System.err.println("Element na poziciji " + i + " nije JSON objekat: " + obj.toString());
			}
		}
		System.out.println("Podaci su uspešno dodati u tabelu Fudbal");
	}

	// Metoda za dobijanje vrednosti ključa ili postavljanje podrazumevane vrednosti
	// ako ključ ne postoji
	private static double getValueOrDefault(JSONObject jsonObject, String key, double defaultValue) {
		if (jsonObject.has(key)) {
			return jsonObject.getDouble(key);
		} else {
			return defaultValue;
		}
	}


/*
	public static void main(String[] args) { 
		SoccerBetF.praviAPI();
		
		}
	*/ 
	
}
