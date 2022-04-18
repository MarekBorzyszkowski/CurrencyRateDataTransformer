package dev.marek.currencyratedatatransformer.models;

import lombok.Data;

@Data
public class NbpRate {

    private String no;
    private String effectiveDate;
    private String bid;
    private String ask;
}
