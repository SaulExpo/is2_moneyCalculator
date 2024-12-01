package ulpgc.es.control;

import ulpgc.es.model.Currency;
import ulpgc.es.model.ExchangeRate;
import ulpgc.es.persistence.ExchangeRateLoader;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class WindowsExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to, LocalDate date, List<ExchangeRate> exchangeRates) {
        Double rate = getRate(from, to, exchangeRates);
        return new ExchangeRate(from,to, rate, LocalDate.now());
    }

    private static Double getRate(Currency from, Currency to, List<ExchangeRate> exchangeRates) {
        Optional<ExchangeRate> exchangeRateFrom = exchangeRates.stream()
                .filter(e-> e.to().code().equals(from.toString()) && e.from().code().equals("EUR"))
                .findFirst();
        Optional<ExchangeRate> exchangeRateTo = exchangeRates.stream()
                .filter(e-> e.to().code().equals(to.toString()) && e.from().code().equals("EUR"))
                .findFirst();
        return (1/exchangeRateFrom.get().rate()) * exchangeRateTo.get().rate();
    }
}
