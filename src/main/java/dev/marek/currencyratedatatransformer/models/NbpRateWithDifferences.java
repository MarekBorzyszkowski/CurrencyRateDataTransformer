package dev.marek.currencyratedatatransformer.models;

import lombok.Data;

@Data
public class NbpRateWithDifferences {

    private String effectiveDate;
    private String bid;
    private String ask;
    private String bidDifference;
    private String askDifference;

    public NbpRateWithDifferences(String effectiveDate, String bid, String ask) {
        this.effectiveDate = effectiveDate;
        this.bid = bid;
        this.ask = ask;
        this.bidDifference = "0";
        this.askDifference = "0";
    }
}
