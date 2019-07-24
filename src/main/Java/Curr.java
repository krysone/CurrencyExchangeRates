public class Curr {
    String currency;
    String code;
    String effectiveDate;
    double bid;
    double ask;

    public Curr(final String currency, final String code, final String effectiveDate, final double bid, final double ask) {
        this.currency = currency;
        this.code = code;
        this.effectiveDate = effectiveDate;
        this.bid = bid;
        this.ask = ask;
    }

    static class CurrBuilder {
        String currency;
        String code;
        String effectiveDate;
        double bid;
        double ask;


        public String getCurrency() {
            return currency;
        }

        public CurrBuilder setCurrency(final String currency) {
            this.currency = currency;
            return this;
        }

        public String getCode() {
            return code;
        }

        public CurrBuilder setCode(final String code) {
            this.code = code;
            return this;
        }

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public CurrBuilder setEffectiveDate(final String effectiveDate) {
            this.effectiveDate = effectiveDate;
            return this;
        }

        public double getBid() {
            return bid;
        }

        public CurrBuilder setBid(final double bid) {
            this.bid = bid;
            return this;
        }

        public double getAsk() {
            return ask;
        }

        public CurrBuilder setAsk(final double ask) {
            this.ask = ask;
            return this;
        }

        public Curr build() {
            return new Curr(getCurrency(), getCode(), getEffectiveDate(), getBid(), getAsk());
        }
    }

}
