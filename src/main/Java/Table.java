import java.util.List;

public class Table {

    private String table;

    private String no;

    private String effectiveDate;

    private SingleRates[] rates;

    public String getTable() {
        return table;
    }

    public void setTable(final String table) {
        this.table = table;
    }

    public String getNo() {
        return no;
    }

    public void setNo(final String no) {
        this.no = no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(final String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public SingleRates[] getRates() {
        return rates;
    }

    public void setRates(final SingleRates[] rates) {
        this.rates = rates;
    }


}
