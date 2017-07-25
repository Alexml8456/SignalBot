class DataConverter {
    private final String[] arguments;
    private String timeStamp;
    private int currencyId;
    private Double last;

    String[] arguments() {
        return arguments;
    }

    DataConverter(String timeStamp, String arguments) {
        this.arguments = arguments.replaceAll("[]\\[]", "").split(",");
        this.currencyId = Integer.parseInt(this.arguments[2]);
        //this.last = Double.valueOf(this.arguments[3]);

    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public Double getLast() {
        return last;
    }
}