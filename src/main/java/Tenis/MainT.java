package Tenis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainT {

	public static void main(String[] args) {
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
					preparedStatement.setString(1, jsonObject.getString("Kladionica"));
					preparedStatement.setString(2, jsonObject.getString("Liga"));
					preparedStatement.setLong(3, jsonObject.getLong("Start"));
					preparedStatement.setString(4, jsonObject.getString("Igrac1"));
					preparedStatement.setString(5, jsonObject.getString("Igrac2"));
					
					preparedStatement.setDouble(6, getValueOrDefault(jsonObject, "Igrac1W", 1));
					preparedStatement.setDouble(7, getValueOrDefault(jsonObject, "Igrac2W", 1));
					preparedStatement.setDouble(8, getValueOrDefault(jsonObject, "PS1", 1));
					preparedStatement.setDouble(9, getValueOrDefault(jsonObject, "PS2", 1));
					preparedStatement.setDouble(10, getValueOrDefault(jsonObject, "DS1", 1));
					preparedStatement.setDouble(11, getValueOrDefault(jsonObject, "DS2", 1));
					preparedStatement.setDouble(12, getValueOrDefault(jsonObject, "TS1", 1));
					preparedStatement.setDouble(13, getValueOrDefault(jsonObject, "TS2", 1));
					preparedStatement.setDouble(14, getValueOrDefault(jsonObject, "SS", 1));
					preparedStatement.setDouble(15, getValueOrDefault(jsonObject, "NS", 1));
					preparedStatement.setDouble(16, getValueOrDefault(jsonObject, "PTBDA", 1));
					preparedStatement.setDouble(17, getValueOrDefault(jsonObject, "PTBNE", 1));
					preparedStatement.setDouble(18, getValueOrDefault(jsonObject, "PARG", 1));
					preparedStatement.setDouble(19, getValueOrDefault(jsonObject, "NEPARG", 1));
//UKUPNO GEMOVA +-
					preparedStatement.setString(20, getValueOrDefault(jsonObject, "GranicaGemovi", "N/A"));
					preparedStatement.setDouble(21, getValueOrDefault(jsonObject, "G_PLUS", 1));
					preparedStatement.setDouble(22, getValueOrDefault(jsonObject, "G_MINUS", 1));
					preparedStatement.setString(23, getValueOrDefault(jsonObject, "GranicaGemovi2", "N/A"));
					preparedStatement.setDouble(24, getValueOrDefault(jsonObject, "G_PLUS2", 1));
					preparedStatement.setDouble(25, getValueOrDefault(jsonObject, "G_MINUS2", 1));
					preparedStatement.setString(26, getValueOrDefault(jsonObject, "GranicaGemovi3", "N/A"));
					preparedStatement.setDouble(27, getValueOrDefault(jsonObject, "G_PLUS3", 1));
					preparedStatement.setDouble(28, getValueOrDefault(jsonObject, "G_MINUS3", 1));
					preparedStatement.setString(29, getValueOrDefault(jsonObject, "GranicaGemovi4", "N/A"));
					preparedStatement.setDouble(30, getValueOrDefault(jsonObject, "G_PLUS4", 1));
					preparedStatement.setDouble(31, getValueOrDefault(jsonObject, "G_MINUS4", 1));
					preparedStatement.setString(32, getValueOrDefault(jsonObject, "GranicaGemovi5", "N/A"));
					preparedStatement.setDouble(33, getValueOrDefault(jsonObject, "G_PLUS5", 1));
					preparedStatement.setDouble(34, getValueOrDefault(jsonObject, "G_MINUS5", 1));
					preparedStatement.setString(35, getValueOrDefault(jsonObject, "GranicaGemovi6", "N/A"));
					preparedStatement.setDouble(36, getValueOrDefault(jsonObject, "G_PLUS6", 1));
					preparedStatement.setDouble(37, getValueOrDefault(jsonObject, "G_MINUS6", 1));
					preparedStatement.setString(38, getValueOrDefault(jsonObject, "GranicaGemovi7", "N/A"));
					preparedStatement.setDouble(39, getValueOrDefault(jsonObject, "G_PLUS7", 1));
					preparedStatement.setDouble(40, getValueOrDefault(jsonObject, "G_MINUS7", 1));
					preparedStatement.setString(41, getValueOrDefault(jsonObject, "GranicaGemovi8", "N/A"));
					preparedStatement.setDouble(42, getValueOrDefault(jsonObject, "G_PLUS8", 1));
					preparedStatement.setDouble(43, getValueOrDefault(jsonObject, "G_MINUS8", 1));
					preparedStatement.setString(44, getValueOrDefault(jsonObject, "GranicaGemovi9", "N/A"));
					preparedStatement.setDouble(45, getValueOrDefault(jsonObject, "G_PLUS9", 1));
					preparedStatement.setDouble(46, getValueOrDefault(jsonObject, "G_MINUS9", 1));
//HENDIKEP GEMOVI
					preparedStatement.setString(47, getValueOrDefault(jsonObject, "HendikepGemGr1", "N/A"));
					preparedStatement.setDouble(48, getValueOrDefault(jsonObject, "HG1", 1));
					preparedStatement.setDouble(49, getValueOrDefault(jsonObject, "HG2", 1));
					preparedStatement.setString(50, getValueOrDefault(jsonObject, "HendikepGemGr2", "N/A"));
					preparedStatement.setDouble(51, getValueOrDefault(jsonObject, "HG1_2", 1));
					preparedStatement.setDouble(52, getValueOrDefault(jsonObject, "HG2_2", 1));
					preparedStatement.setString(53, getValueOrDefault(jsonObject, "HendikepGemGr3", "N/A"));
					preparedStatement.setDouble(54, getValueOrDefault(jsonObject, "HG1_3", 1));
					preparedStatement.setDouble(55, getValueOrDefault(jsonObject, "HG2_3", 1));
					preparedStatement.setString(56, getValueOrDefault(jsonObject, "HendikepGemGr4", "N/A"));
					preparedStatement.setDouble(57, getValueOrDefault(jsonObject, "HG1_4", 1));
					preparedStatement.setDouble(58, getValueOrDefault(jsonObject, "HG2_4", 1));
					preparedStatement.setString(59, getValueOrDefault(jsonObject, "HendikepGemGr5", "N/A"));
					preparedStatement.setDouble(60, getValueOrDefault(jsonObject, "HG1_5", 1));
					preparedStatement.setDouble(61, getValueOrDefault(jsonObject, "HG2_5", 1));
					preparedStatement.setString(62, getValueOrDefault(jsonObject, "HendikepGemGr6", "N/A"));
					preparedStatement.setDouble(63, getValueOrDefault(jsonObject, "HG1_6", 1));
					preparedStatement.setDouble(64, getValueOrDefault(jsonObject, "HG2_6", 1));
					preparedStatement.setString(65, getValueOrDefault(jsonObject, "HendikepGemGr7", "N/A"));
					preparedStatement.setDouble(66, getValueOrDefault(jsonObject, "HG1_7", 1));
					preparedStatement.setDouble(67, getValueOrDefault(jsonObject, "HG2_7", 1));
					preparedStatement.setString(68, getValueOrDefault(jsonObject, "HendikepGemGr8", "N/A"));
					preparedStatement.setDouble(69, getValueOrDefault(jsonObject, "HG1_8", 1));
					preparedStatement.setDouble(70, getValueOrDefault(jsonObject, "HG2_8", 1));
					preparedStatement.setString(71, getValueOrDefault(jsonObject, "HendikepGemGr9", "N/A"));
					preparedStatement.setDouble(72, getValueOrDefault(jsonObject, "HG1_9", 1));
					preparedStatement.setDouble(73, getValueOrDefault(jsonObject, "HG2_9", 1));
					preparedStatement.setString(74, getValueOrDefault(jsonObject, "HendikepGemGr10", "N/A"));
					preparedStatement.setDouble(75, getValueOrDefault(jsonObject, "HG1_10", 1));
					preparedStatement.setDouble(76, getValueOrDefault(jsonObject, "HG2_10", 1));
					preparedStatement.setString(77, getValueOrDefault(jsonObject, "HendikepGemGr11", "N/A"));
					preparedStatement.setDouble(78, getValueOrDefault(jsonObject, "HG1_11", 1));
					preparedStatement.setDouble(79, getValueOrDefault(jsonObject, "HG2_11", 1));
					preparedStatement.setString(80, getValueOrDefault(jsonObject, "HendikepGemGr12", "N/A"));
					preparedStatement.setDouble(81, getValueOrDefault(jsonObject, "HG1_12", 1));
					preparedStatement.setDouble(82, getValueOrDefault(jsonObject, "HG2_12", 1));
					preparedStatement.setString(83, getValueOrDefault(jsonObject, "HendikepGemGr13", "N/A"));
					preparedStatement.setDouble(84, getValueOrDefault(jsonObject, "HG1_13", 1));
					preparedStatement.setDouble(85, getValueOrDefault(jsonObject, "HG2_13", 1));
					preparedStatement.setString(86, getValueOrDefault(jsonObject, "HendikepGemGr14", "N/A"));
					preparedStatement.setDouble(87, getValueOrDefault(jsonObject, "HG1_14", 1));
					preparedStatement.setDouble(88, getValueOrDefault(jsonObject, "HG2_14", 1));
					preparedStatement.setString(89, getValueOrDefault(jsonObject, "HendikepGemGr15", "N/A"));
					preparedStatement.setDouble(90, getValueOrDefault(jsonObject, "HG1_15", 1));
					preparedStatement.setDouble(91, getValueOrDefault(jsonObject, "HG2_15", 1));
					preparedStatement.setString(92, getValueOrDefault(jsonObject, "HendikepGemGr16", "N/A"));
					preparedStatement.setDouble(93, getValueOrDefault(jsonObject, "HG1_16", 1));
					preparedStatement.setDouble(94, getValueOrDefault(jsonObject, "HG2_16", 1));
//HENDIKEP SETOVA +-
					preparedStatement.setString(95, getValueOrDefault(jsonObject, "HendikepSetGr1", "N/A"));
					preparedStatement.setDouble(96, getValueOrDefault(jsonObject, "HS1", 1));
					preparedStatement.setDouble(97, getValueOrDefault(jsonObject, "HS2", 1));
					preparedStatement.setString(98, getValueOrDefault(jsonObject, "HendikepSetGr2", "N/A"));
					preparedStatement.setDouble(99, getValueOrDefault(jsonObject, "HS1_2", 1));
					preparedStatement.setDouble(100, getValueOrDefault(jsonObject, "HS2_2", 1));
					preparedStatement.setString(101, getValueOrDefault(jsonObject, "HendikepSetGr3", "N/A"));
					preparedStatement.setDouble(102, getValueOrDefault(jsonObject, "HS1_3", 1));
					preparedStatement.setDouble(103, getValueOrDefault(jsonObject, "HS2_3", 1));
					preparedStatement.setString(104, getValueOrDefault(jsonObject, "HendikepSetGr4", "N/A"));
					preparedStatement.setDouble(105, getValueOrDefault(jsonObject, "HS1_4", 1));
					preparedStatement.setDouble(106, getValueOrDefault(jsonObject, "HS2_4", 1));
					preparedStatement.setString(107, getValueOrDefault(jsonObject, "HendikepSetGr5", "N/A"));
					preparedStatement.setDouble(108, getValueOrDefault(jsonObject, "HS1_5", 1));
					preparedStatement.setDouble(109, getValueOrDefault(jsonObject, "HS2_5", 1));
					preparedStatement.setString(110, getValueOrDefault(jsonObject, "HendikepSetGr6", "N/A"));
					preparedStatement.setDouble(111, getValueOrDefault(jsonObject, "HS1_6", 1));
					preparedStatement.setDouble(112, getValueOrDefault(jsonObject, "HS2_6", 1));
//UKUPNO GEMOVA PO SETU
	//PRVI SET
					preparedStatement.setString(113, getValueOrDefault(jsonObject, "UGPSGr1", "N/A"));
					preparedStatement.setDouble(114, getValueOrDefault(jsonObject, "UGPS_PLUS1", 1));
					preparedStatement.setDouble(115, getValueOrDefault(jsonObject, "UGPS_MINUS1", 1));
					preparedStatement.setString(116, getValueOrDefault(jsonObject, "UGPSGr2", "N/A"));
					preparedStatement.setDouble(117, getValueOrDefault(jsonObject, "UGPS_PLUS2", 1));
					preparedStatement.setDouble(118, getValueOrDefault(jsonObject, "UGPS_MINUS2", 1));
					preparedStatement.setString(119, getValueOrDefault(jsonObject, "UGPSGr3", "N/A"));
					preparedStatement.setDouble(120, getValueOrDefault(jsonObject, "UGPS_PLUS3", 1));
					preparedStatement.setDouble(121, getValueOrDefault(jsonObject, "UGPS_MINUS3", 1));
					preparedStatement.setString(122, getValueOrDefault(jsonObject, "UGPSGr4", "N/A"));
					preparedStatement.setDouble(123, getValueOrDefault(jsonObject, "UGPS_PLUS4", 1));
					preparedStatement.setDouble(124, getValueOrDefault(jsonObject, "UGPS_MINUS4", 1));
					preparedStatement.setString(125, getValueOrDefault(jsonObject, "UGPSGr5", "N/A"));
					preparedStatement.setDouble(126, getValueOrDefault(jsonObject, "UGPS_PLUS5", 1));
					preparedStatement.setDouble(127, getValueOrDefault(jsonObject, "UGPS_MINUS5", 1));
	//DRUGI SET
					preparedStatement.setString(128, getValueOrDefault(jsonObject, "UGDSGr1", "N/A"));
					preparedStatement.setDouble(129, getValueOrDefault(jsonObject, "UGDS_PLUS1", 1));
					preparedStatement.setDouble(130, getValueOrDefault(jsonObject, "UGDS_MINUS1", 1));
					preparedStatement.setString(131, getValueOrDefault(jsonObject, "UGDSGr2", "N/A"));
					preparedStatement.setDouble(132, getValueOrDefault(jsonObject, "UGDS_PLUS2", 1));
					preparedStatement.setDouble(133, getValueOrDefault(jsonObject, "UGDS_MINUS2", 1));
					preparedStatement.setString(134, getValueOrDefault(jsonObject, "UGDSGr3", "N/A"));
					preparedStatement.setDouble(135, getValueOrDefault(jsonObject, "UGDS_PLUS3", 1));
					preparedStatement.setDouble(136, getValueOrDefault(jsonObject, "UGDS_MINUS3", 1));
					preparedStatement.setString(137, getValueOrDefault(jsonObject, "UGDSGr4", "N/A"));
					preparedStatement.setDouble(138, getValueOrDefault(jsonObject, "UGDS_PLUS4", 1));
					preparedStatement.setDouble(139, getValueOrDefault(jsonObject, "UGDS_MINUS4", 1));
					preparedStatement.setString(140, getValueOrDefault(jsonObject, "UGDSGr5", "N/A"));
					preparedStatement.setDouble(141, getValueOrDefault(jsonObject, "UGDS_PLUS5", 1));
					preparedStatement.setDouble(142, getValueOrDefault(jsonObject, "UGDS_MINUS5", 1));
	//TRECI SET
					preparedStatement.setString(143, getValueOrDefault(jsonObject, "UGTSGr1", "N/A"));
					preparedStatement.setDouble(144, getValueOrDefault(jsonObject, "UGTS_PLUS1", 1));
					preparedStatement.setDouble(145, getValueOrDefault(jsonObject, "UGTS_MINUS1", 1));
					preparedStatement.setString(146, getValueOrDefault(jsonObject, "UGTSGr2", "N/A"));
					preparedStatement.setDouble(147, getValueOrDefault(jsonObject, "UGTS_PLUS2", 1));
					preparedStatement.setDouble(148, getValueOrDefault(jsonObject, "UGTS_MINUS2", 1));
					preparedStatement.setString(149, getValueOrDefault(jsonObject, "UGTSGr3", "N/A"));
					preparedStatement.setDouble(150, getValueOrDefault(jsonObject, "UGTS_PLUS3", 1));
					preparedStatement.setDouble(151, getValueOrDefault(jsonObject, "UGTS_MINUS3", 1));
					preparedStatement.setString(152, getValueOrDefault(jsonObject, "UGTSGr4", "N/A"));
					preparedStatement.setDouble(153, getValueOrDefault(jsonObject, "UGTS_PLUS4", 1));
					preparedStatement.setDouble(154, getValueOrDefault(jsonObject, "UGTS_MINUS4", 1));
					preparedStatement.setString(155, getValueOrDefault(jsonObject, "UGTSGr5", "N/A"));
					preparedStatement.setDouble(156, getValueOrDefault(jsonObject, "UGTS_PLUS5", 1));
					preparedStatement.setDouble(157, getValueOrDefault(jsonObject, "UGTS_MINUS5", 1));
					
					
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
	
	/*
	public static void main(String[] args) {
	SoccerBetT.praviAPI();
	}
*/
}
