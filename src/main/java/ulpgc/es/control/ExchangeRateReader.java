package ulpgc.es.control;
import ulpgc.es.model.ExchangeRate;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public interface ExchangeRateReader {
    List<ExchangeRate> read() throws IOException;
}
