package dev.marek.currencyratedatatransformer.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NbpExchangeRatesResponse {

    private String code;
    private List<NbpRate> rates = new ArrayList<>();
}
