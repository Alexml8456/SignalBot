public class Employee {
    private long timeStamp;
    private String currencyPair;
    private String last;
    private String lowestAsk;
    private String highestBid;
    private String percentChange;
    private String baseVolume;
    private String quoteVolume;
    private int isFrozen;
    private String dayHigh;
    private String dayLow;

    public Employee() {
    }

    public Employee(Long timeStamp, String currencyPair, String last, String lowestAsk, String highestBid, String percentChange, String baseVolume, String quoteVolume, int isFrozen, String dayHigh, String dayLow) {
        this.currencyPair = currencyPair;
        this.timeStamp = timeStamp;
        this.last = last;
        this.lowestAsk = lowestAsk;
        this.highestBid = highestBid;
        this.percentChange = percentChange;
        this.baseVolume = baseVolume;
        this.quoteVolume = quoteVolume;
        this.isFrozen = isFrozen;
        this.dayHigh = dayHigh;
        this.dayLow = dayLow;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getLast() {
        return last;
    }

    public String getLowestAsk() {
        return lowestAsk;
    }

    public String getHighestBid() {
        return highestBid;
    }

    public String getPercentChange() {
        return percentChange;
    }

    public String getBaseVolume() {
        return baseVolume;
    }

    public String getQuoteVolume() {
        return quoteVolume;
    }

    public int getIsFrozen() {
        return isFrozen;
    }

    public String getDayHigh() {
        return dayHigh;
    }

    public String getDayLow() {
        return dayLow;
    }
}