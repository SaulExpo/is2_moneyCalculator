package ulpgc.es.control;

import ulpgc.es.model.Currency;
import ulpgc.es.model.ExchangeRate;

import java.util.List;

public interface ExchangeRateDeserializer {
    ExchangeRate deserialize(String value, List<Currency> currencyList);
}
