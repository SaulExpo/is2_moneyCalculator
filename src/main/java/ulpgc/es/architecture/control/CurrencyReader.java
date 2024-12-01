package ulpgc.es.architecture.control;


import ulpgc.es.architecture.model.Currency;

import java.io.IOException;
import java.util.Iterator;

public interface CurrencyReader {
    Iterator<Currency> read() throws IOException;
}
