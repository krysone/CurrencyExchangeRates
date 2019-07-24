import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Response {

    /*
    {"table":"A",
    "currency":"frank szwajcarski",
    "code":"CHF",
    "rates":[{"no":"139/A/NBP/2019","effectiveDate":"2019-07-19","mid":3.8480}]}
     */

    private String table;

    private String currency;

    private String code;

    private Rates[] rates;


    public String getTable() {
        return table;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public Rates[] getRates() {
        return rates;
    }
}

