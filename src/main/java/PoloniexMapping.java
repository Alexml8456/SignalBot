import java.math.BigDecimal;

class PoloniexMapping {
    private String timeStamp;
    private int currencyId;
    private BigDecimal last;
    private BigDecimal lowestAsk;
    private BigDecimal highestBid;
    private BigDecimal percentChange;
    private BigDecimal baseVolume;
    private BigDecimal quoteVolume;
    private boolean isFrozen;
    private BigDecimal dayHigh;
    private BigDecimal dayLow;

    PoloniexMapping(String timeStamp, String[] test) {
        this.timeStamp = timeStamp;
        this.currencyId = Integer.parseInt(test[0]);
        this.last = BigDecimal.valueOf(Double.parseDouble(test[1]));
        this.lowestAsk = BigDecimal.valueOf(Double.parseDouble(test[2]));
        this.highestBid = BigDecimal.valueOf(Double.parseDouble(test[3]));
        this.percentChange = BigDecimal.valueOf(Double.parseDouble(test[4]));
        this.baseVolume = BigDecimal.valueOf(Double.parseDouble(test[5]));
        this.quoteVolume = BigDecimal.valueOf(Double.parseDouble(test[6]));
        this.isFrozen = Boolean.parseBoolean(test[7]);
        this.dayHigh = BigDecimal.valueOf(Double.parseDouble(test[8]));
        this.dayLow = BigDecimal.valueOf(Double.parseDouble(test[9]));
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public BigDecimal getLast() {
        return last;
    }

    public BigDecimal getLowestAsk() {
        return lowestAsk;
    }

    public BigDecimal getHighestBid() {
        return highestBid;
    }

    public BigDecimal getPercentChange() {
        return percentChange;
    }

    public BigDecimal getQuoteVolume() {
        return quoteVolume;
    }

    public BigDecimal getBaseVolume() {
        return baseVolume;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public BigDecimal getDayHigh() {
        return dayHigh;
    }

    public BigDecimal getDayLow() {
        return dayLow;
    }
}