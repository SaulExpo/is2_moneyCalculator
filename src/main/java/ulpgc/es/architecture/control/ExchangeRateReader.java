package ulpgc.es.architecture.control;
import ulpgc.es.architecture.model.ExchangeRate;

import java.io.IOException;
import java.util.Iterator;

public interface ExchangeRateReader {
    Iterator<ExchangeRate> read() throws IOException;
}
