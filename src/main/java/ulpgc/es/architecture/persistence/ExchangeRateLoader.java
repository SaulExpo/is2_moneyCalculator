package ulpgc.es.architecture.persistence;


import ulpgc.es.architecture.model.Currency;
import ulpgc.es.architecture.model.ExchangeRate;

import java.time.LocalDate;

public interface ExchangeRateLoader {
    ExchangeRate load(Currency from, Currency to, LocalDate date);
}
