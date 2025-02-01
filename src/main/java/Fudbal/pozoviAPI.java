package Fudbal;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import org.json.JSONObject;

public class pozoviAPI {

    public static String pozoviAPI(String apiUrl) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
            conn.setRequestProperty("Accept-Encoding", "gzip");

            conn.setReadTimeout(2000);
            
            InputStream inputStream = conn.getInputStream();

            if ("gzip".equals(conn.getContentEncoding())) {
                inputStream = new GZIPInputStream(inputStream);
            }

            // Čitamo odgovor
            Scanner scanner = new Scanner(inputStream);
            StringBuilder responseBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                responseBuilder.append(scanner.nextLine());
            }
            scanner.close();

            // Pretvaramo odgovor u string
            return responseBuilder.toString();

        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    public static String posaljiPostZahtev(String apiUrl, String requestBody) {
        try {
        	
        	Thread.sleep(2250);
        	
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            // Postavljamo potrebne parametre za POST zahtev
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json"); // Ako je JSON
            conn.setDoOutput(true);

            // Slanje tela zahteva
            OutputStream os = conn.getOutputStream();
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);

            // Čitanje odgovora
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder responseBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                responseBuilder.append(scanner.nextLine());
            }
            scanner.close();

            // Zatvaramo i oslobađamo resurse
            os.flush();
            os.close();

            // Vraćamo odgovor kao string
            return responseBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}