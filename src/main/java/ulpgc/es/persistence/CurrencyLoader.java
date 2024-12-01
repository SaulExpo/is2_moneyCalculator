package ulpgc.es.persistence;

import ulpgc.es.model.Currency;

import java.util.List;

public interface CurrencyLoader {
    List<Currency> load();
}
