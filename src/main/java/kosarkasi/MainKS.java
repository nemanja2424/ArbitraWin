package kosarkasi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainKS {
	
	/*public static void main(String[] args) {
		JSONArray maxbetData = MaxbetKS.praviAPI();
		JSONArray merkurData = MerkurKS.praviAPI();
		JSONArray betoleData = BetOleKS.praviAPI();
		
		// Konekcija sa bazom podataka
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/arbetproba", "root",
				"")) {
			// Brisanje svih podataka iz tabele Fudbal
			obrisiSvePodatkeIzTabele(connection);

			dodajPodatkeUDB(connection, maxbetData);
			dodajPodatkeUDB(connection, merkurData);
			dodajPodatkeUDB(connection, betoleData);
		} catch (SQLException e) {
			System.err.println("Došlo je do greške prilikom povezivanja sa bazom podataka: " + e.getMessage());
		}
	}

	// Metoda za brisanje svih podataka iz tabele Fudbal
	private static void obrisiSvePodatkeIzTabele(Connection connection) throws SQLException {
		String deleteQuery = "DELETE FROM Kosarkasi";
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.executeUpdate();
			System.out.println("Svi podaci su uspešno obrisani iz tabele Kosarkasi");
		} catch (SQLException e) {
			throw new SQLException("Došlo je do greške prilikom brisanja podataka iz tabele Kosarkasi: " + e.getMessage());
		}
	}

	// Metoda za dinamičko dodavanje podataka u tabelu Fudbal
	// Metoda za dinamičko dodavanje podataka u tabelu Fudbal
	private static void dodajPodatkeUDB(Connection connection, JSONArray jsonData) throws SQLException {
		// Iteriranje kroz JSON niz i dodavanje svakog objekta u tabelu
		for (int i = 0; i < jsonData.length(); i++) {
			Object obj = jsonData.get(i);
			if (obj instanceof JSONObject) {
				JSONObject jsonObject = (JSONObject) obj;
				// System.out.println("Ključevi u JSON objektu: " + jsonObject.keySet()); //
				// Debug ispis

				String insertQuery = "INSERT INTO Kosarkasi (Kladionica, Start, Igrac, GranicaPoena, VisePoena, ManjePoena, GranicaAsistencija, ViseAsistencija, ManjeAsistencija, GranicaSkokova, ViseSkokova, ManjeSkokova) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

				try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
					preparedStatement.setString(1, jsonObject.getString("Kladionica"));
					preparedStatement.setLong(2, jsonObject.getLong("Start"));
					preparedStatement.setString(3, jsonObject.getString("Igrac"));
					
					preparedStatement.setDouble(4, getValueOrDefault(jsonObject, "GranicaPoena", 1));
					preparedStatement.setDouble(5, getValueOrDefault(jsonObject, "VisePoena", 1));
					preparedStatement.setDouble(6, getValueOrDefault(jsonObject, "ManjePoena", 1));
					preparedStatement.setDouble(7, getValueOrDefault(jsonObject, "GranicaAsistencija", 1));
					preparedStatement.setDouble(8, getValueOrDefault(jsonObject, "ViseAsistencija", 1));
					preparedStatement.setDouble(9, getValueOrDefault(jsonObject, "ManjeAsistencija", 1));
					preparedStatement.setDouble(10, getValueOrDefault(jsonObject, "GranicaSkokova", 1));
					preparedStatement.setDouble(11, getValueOrDefault(jsonObject, "ViseSkokova", 1));
					preparedStatement.setDouble(12, getValueOrDefault(jsonObject, "ManjeSkokova", 1));
					

					// Izvršavanje SQL upita za dodavanje podataka u tabelu
					preparedStatement.executeUpdate();
				} catch (SQLException e) {
					throw new SQLException(
							"Došlo je do greške prilikom dodavanja podataka u tabelu Kosarkasi: " + e.getMessage());
				}
			} else {
				System.err.println("Element na poziciji " + i + " nije JSON objekat: " + obj.toString());
			}
		}
		System.out.println("Podaci su uspešno dodati u tabelu Kosarkasi");
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
	*/
	
	
	public static void main(String[] args) { 
		BetOleKS.praviAPI();
		MaxbetKS.praviAPI();
		MerkurKS.praviAPI();
	}
	
		
}
