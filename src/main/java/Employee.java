import com.fasterxml.jackson.databind.node.ArrayNode;

import java.math.BigDecimal;

class Employee {
    private long timeStamp;
    private String currencyPair;
    private BigDecimal last;
    private BigDecimal lowestAsk;
    private BigDecimal highestBid;
    private BigDecimal percentChange;
    private BigDecimal baseVolume;
    private BigDecimal quoteVolume;
    private boolean isFrozen;
    private BigDecimal dayHigh;
    private BigDecimal dayLow;

    Employee(Long timeStamp, ArrayNode test) {
        this.timeStamp = timeStamp;
        this.currencyPair = test.get(0).asText();
        this.last = BigDecimal.valueOf(test.get(1).asDouble());
        this.lowestAsk = BigDecimal.valueOf(test.get(2).asDouble());
        this.highestBid = BigDecimal.valueOf(test.get(3).asDouble());
        this.percentChange = BigDecimal.valueOf(test.get(4).asDouble());
        this.baseVolume = BigDecimal.valueOf(test.get(5).asDouble());
        this.quoteVolume = BigDecimal.valueOf(test.get(6).asDouble());
        this.isFrozen = test.get(7).isBoolean();
        this.dayHigh = BigDecimal.valueOf(test.get(8).asDouble());
        this.dayLow = BigDecimal.valueOf(test.get(9).asDouble());
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getCurrencyPair() {
        return currencyPair;
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