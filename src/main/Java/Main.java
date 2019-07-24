import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Connection.getBig4ExchangeRatesUSD_EUR_GBP_CHF();
        System.out.println();
        Connection.hundredPLNToday();
        System.out.println();
        Connection.differenceHundredPLNTodayAndSomeTimeAgo("2019-06-24");

    }

}
