package ulpgc.es.control;


import ulpgc.es.model.Currency;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public interface CurrencyReader {
    List<Currency> read() throws IOException;
}
