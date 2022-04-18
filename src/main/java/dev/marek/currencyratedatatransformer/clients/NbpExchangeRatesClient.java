package dev.marek.currencyratedatatransformer.clients;

import dev.marek.currencyratedatatransformer.models.NbpExchangeRatesResponse;
import feign.Param;
import feign.RequestLine;
import lombok.NonNull;

public interface NbpExchangeRatesClient {

    @RequestLine("GET /rates/c/usd/{startDate}/{endDate}/?format=json")
    NbpExchangeRatesResponse getExchangeRatesBetweenDates(@NonNull @Param("startDate") String startDate, @NonNull @Param("endDate") String endDate);
}
