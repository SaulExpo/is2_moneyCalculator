package ulpgc.es.persistence;


import ulpgc.es.model.Currency;
import ulpgc.es.model.ExchangeRate;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to, LocalDate date, List<ExchangeRate> exchangeRate);
}
