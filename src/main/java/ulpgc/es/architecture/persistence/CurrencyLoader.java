package ulpgc.es.architecture.persistence;

import ulpgc.es.architecture.model.Currency;

import java.util.List;

public interface CurrencyLoader {
    List<Currency> load();
}
