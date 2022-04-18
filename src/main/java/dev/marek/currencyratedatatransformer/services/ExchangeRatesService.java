package dev.marek.currencyratedatatransformer.services;

import dev.marek.currencyratedatatransformer.models.NbpRateWithDifferences;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRatesService {
    List<NbpRateWithDifferences> getRatesBetweenDates(LocalDate start, LocalDate end);
}
