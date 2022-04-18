package dev.marek.currencyratedatatransformer.services;

import dev.marek.currencyratedatatransformer.clients.NbpExchangeRatesClient;
import dev.marek.currencyratedatatransformer.models.NbpExchangeRatesResponse;
import dev.marek.currencyratedatatransformer.models.NbpRate;
import dev.marek.currencyratedatatransformer.models.NbpRateWithDifferences;
import dev.marek.currencyratedatatransformer.utils.Pair;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NbpExchangeRatesService implements ExchangeRatesService {

    @Value("${rates.precision}")
    private int precision;
    private final NbpExchangeRatesClient nbpExchangeRatesClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .target(NbpExchangeRatesClient.class, "https://api.nbp.pl/api/exchangerates");

    @Override
    public List<NbpRateWithDifferences> getRatesBetweenDates(LocalDate start, LocalDate end) {
        List<Pair<String, String>> datePeriods = calculateDatesPeriods(start, end);
        NbpExchangeRatesResponse response = new NbpExchangeRatesResponse();
        response.setRates(createResponse(datePeriods));
        if (response.getRates().isEmpty()) {
            return new ArrayList<>();
        }

        List<NbpRateWithDifferences> result = parseNbpResponseToNbpRateWithDifference(response);

        return CollectionUtils.isNotEmpty(result) ? result : Collections.emptyList();
    }

    private List<NbpRate> createResponse(List<Pair<String, String>> datePeriods) {
        List<NbpRate> result = new ArrayList<>();
        NbpExchangeRatesResponse response;
        for (Pair<String, String> pair : datePeriods) {
            try {
                response = this.nbpExchangeRatesClient.getExchangeRatesBetweenDates(pair.left(), pair.right());
            } catch (Exception e) {
                response = new NbpExchangeRatesResponse();
            }
            result.addAll(response.getRates());
        }
        return result;
    }

    private List<NbpRateWithDifferences> parseNbpResponseToNbpRateWithDifference(NbpExchangeRatesResponse nbpResponse) {
        List<NbpRateWithDifferences> result = nbpResponse.getRates()
                .stream()
                .map(nbp -> new NbpRateWithDifferences(nbp.getEffectiveDate(), nbp.getBid(), nbp.getAsk()))
                .collect(Collectors.toList());

        NbpRateWithDifferences previous = result.get(0);
        for (NbpRateWithDifferences current : result) {
            current.setBidDifference(String.format("%." + precision + "f", Double.parseDouble(current.getBid()) - Double.parseDouble(previous.getBid())));
            current.setAskDifference(String.format("%." + precision + "f", Double.parseDouble(current.getAsk()) - Double.parseDouble(previous.getAsk())));
            previous = current;
        }
        return result;
    }

    private List<Pair<String, String>> calculateDatesPeriods(LocalDate start, LocalDate end) {
        List<Pair<String, String>> datesPeriods = new ArrayList<>();

        if (end.getYear() - start.getYear() == 0) {
            datesPeriods.add(new Pair<>(start.toString(), end.toString()));
            return datesPeriods;
        }

        datesPeriods.add(new Pair<>(start.toString(), start.getYear() + "-12-31"));
        for (int i = 1; i < end.getYear() - start.getYear(); i++) {
            int year = start.getYear() + i;
            datesPeriods.add(new Pair<>(year + "-01-01", year + "-12-31"));
        }
        datesPeriods.add(new Pair<>(end.getYear() + "-01-01", end.toString()));

        return datesPeriods;
    }

}
