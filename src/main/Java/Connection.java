import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.simple.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class Connection {
//    private static String url1 = "https://api.nbp.pl/api/exchangerates/rates/a/";
    private static String url2 = "https://api.nbp.pl/api/exchangerates/tables/a";
    private static String url3 = "https://api.nbp.pl/api/exchangerates/rates/c/";


//    private static HttpsURLConnection getConnection(Currencies currencyCode) throws IOException {
//
//        String urlPath = url1 + currencyCode;
//        URL url = new URL(urlPath);
//
//        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//
//        String USER_AGENT = "Chrome";
//        connection.setRequestProperty("User-Agent", USER_AGENT);
//
//        int responseCode = connection.getResponseCode();
//        System.out.println("\nConnecting to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
//
//        return connection;
//    }


  /*  public static void getExchangeRate(Currencies currencyCode) throws IOException {

        HttpsURLConnection connection = getConnection(currencyCode);

        Scanner scanner = new Scanner(connection.getInputStream());

        StringBuffer stringBuffer = new StringBuffer();

        while (scanner.hasNextLine()) {
            stringBuffer.append(scanner.nextLine());
        }
        scanner.close();

        String response = stringBuffer.toString();

//        System.out.println(response);

        Gson gson = new Gson();

        Response responseFromJson = gson.fromJson(response, Response.class);

        System.out.println(responseFromJson.getCurrency()
                + " : date: "
                + responseFromJson.getRates()[0].getEffectiveDate()
                + " : NBP's FX mid rate: 1 "
                + responseFromJson.getCode()
                + " = "
                + responseFromJson.getRates()[0].getMid()
                + " PLN");

    }*/

    public static void getBig4ExchangeRatesUSD_EUR_GBP_CHF() throws IOException {

        String urlPath = url2;
        URL url = new URL(urlPath);

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        String USER_AGENT = "Chrome";
        connection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = connection.getResponseCode();
        System.out.println("\nConnecting to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        Scanner scanner = new Scanner(connection.getInputStream());

        StringBuffer stringBuffer = new StringBuffer();

        while (scanner.hasNextLine()) {
            stringBuffer.append(scanner.nextLine());
        }
        scanner.close();

        String response = stringBuffer.toString();

//        System.out.println(response);

//        Gson gson = new Gson();
//        Table responseFromJson = gson.fromJson(response, Table.class);

        ObjectMapper objectMapper = new ObjectMapper();
        //the below func will prevent the program to crash when the json input will be updated
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Table[] currenciesTableNBP = objectMapper.readValue(response, Table[].class);

        System.out.println("\nDATE: " + currenciesTableNBP[0].getEffectiveDate() + " | Rate: Mid\n");
        System.out.println(" 1 [" + currenciesTableNBP[0].getRates()[1].getCode() + "] = " + currenciesTableNBP[0].getRates()[1].getMid() + " [PLN]");
        System.out.println(" 1 [" + currenciesTableNBP[0].getRates()[7].getCode() + "] = " + currenciesTableNBP[0].getRates()[7].getMid() + " [PLN]");
        System.out.println(" 1 [" + currenciesTableNBP[0].getRates()[9].getCode() + "] = " + currenciesTableNBP[0].getRates()[9].getMid() + " [PLN]");
        System.out.println(" 1 [" + currenciesTableNBP[0].getRates()[10].getCode() + "] = " + currenciesTableNBP[0].getRates()[10].getMid() + " [PLN]");


    }

    public static void hundredPLNToday() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(date);
        System.out.println("DATE: " + today + " | Rate: Ask");
        for (Curr currency : currenciesBidAskToday(today)) {
            System.out.println("100 PLN = " + 100 / currency.ask + " " + currency.code);
        }
    }

    public static void differenceHundredPLNTodayAndSomeTimeAgo(String pastDate) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(date);
        System.out.println("Gain/Loss on 100 PLN between today and " + pastDate);
        for (int i = 0; i < currenciesBidAskToday(today).size(); i++) {
            System.out.print(currenciesBidAskToday(today).get(i).code + " : ");
            System.out.printf("%.3f", (100 / currenciesBidAskToday(today).get(i).ask - (100 / currenciesBidAskToday(pastDate).get(i).ask)));
            System.out.println();
        }
        System.out.println("\nFX rates on a day");
        System.out.println("DATE: " + today);
        for (Curr currency : currenciesBidAskToday(today)) {
            System.out.print(currency.code + " " + currency.ask + " | 100 PLN = ");
            System.out.printf("%.3f", 100 / currency.ask);
            System.out.print(" " + currency.code);
            System.out.println();
        }
        System.out.println("DATE: " + pastDate);
        for (Curr currency : currenciesBidAskToday(pastDate)) {
            System.out.print(currency.code + " " + currency.ask + " | 100 PLN = ");
            System.out.printf("%.3f", 100 / currency.ask);
            System.out.print(" " + currency.code);
            System.out.println();
        }

    }

    private static List<Curr> currenciesBidAskToday(String date) {


        String[] curArr = new String[]{"usd", "eur", "gbp", "chf"};
        List<String> currenciesFromApiJson = new ArrayList<>();
        List<Curr> currenciesObjects = new ArrayList<>();

        for (String currency : curArr) {

            String urlPath = url3 + "/" + currency + "/" + date + "/" + "?format=json";

            currenciesFromApiJson.add(getInputStream(urlPath));
        }
        for (String currencyFromAPIJson : currenciesFromApiJson) {
            try {
                currenciesObjects.add(parseJson(currencyFromAPIJson));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return currenciesObjects;
    }

    private static Curr parseJson(String currencyFromApiJson) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        CurrencyBidAsk currencyBidAsk = objectMapper.readValue(currencyFromApiJson, CurrencyBidAsk.class);

//        String name = currencyBidAsk.getCode().toString();
//        Curr myInstance = (Curr) Class.forName(name).newInstance();
        Curr myInstance = new Curr.CurrBuilder()
                .setEffectiveDate(currencyBidAsk.getRates()[0].getEffectiveDate())
                .setCurrency(currencyBidAsk.getCurrency())
                .setCode(currencyBidAsk.getCode())
                .setAsk(currencyBidAsk.getRates()[0].getAsk())
                .setBid(currencyBidAsk.getRates()[0].getAsk())
                .build();
        return myInstance;
    }

    private static String getInputStream(String urlPath) {

        String currencyRatesInJSON = "";
        try {
            URL url = new URL(urlPath);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            String USER_AGENT = "Chrome";
            connection.setRequestProperty("User-Agent", USER_AGENT);
            if (connection.getResponseCode() == 404) {
                try {
                    throw new Exception("Sorry no DATA on that day. Try another weekday.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Scanner scanner = new Scanner(connection.getInputStream());

            StringBuffer stringBuffer = new StringBuffer();
            while (scanner.hasNextLine()) {
                stringBuffer.append(scanner.nextLine());
            }
            scanner.close();
            currencyRatesInJSON = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currencyRatesInJSON;
    }
}
