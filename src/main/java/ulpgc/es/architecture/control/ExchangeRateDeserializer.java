package ulpgc.es.architecture.control;

import ulpgc.es.architecture.model.Currency;
import ulpgc.es.architecture.model.ExchangeRate;

import java.util.Iterator;
import java.util.List;

public interface ExchangeRateDeserializer {
    ExchangeRate deserialize(String value, List<Currency> currencyList);
}
