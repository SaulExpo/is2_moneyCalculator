package ulpgc.es.architecture.control;

import ulpgc.es.architecture.model.Currency;
import ulpgc.es.architecture.model.ExchangeRate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class CsvExchangeRateReader implements ExchangeRateReader{
    private final File source;
    private final CsvExchangeRateDeserializer deserializer;
    private final List<Currency> currencies;

    public CsvExchangeRateReader(File source, List<Currency> currencies) {
        this.source = source;
        this.deserializer = new CsvExchangeRateDeserializer();
        this.currencies = currencies;
    }

    @Override
    public Iterator<ExchangeRate> read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(source));
        readHeadersWith(reader);
        return readRateWith(reader);
    }

    private Iterator<ExchangeRate> readRateWith(BufferedReader reader) throws IOException {
        return new Iterator<ExchangeRate>() {
            String line = reader.readLine();
            @Override
            public boolean hasNext() {
                return line != null;
            }

            @Override
            public ExchangeRate next() {
                try {
                    ExchangeRate exchangeRate = line == null ? null : rateOf(line);
                    line = reader.readLine();
                    if (line == null) reader.close();
                    return exchangeRate;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

    }

    private ExchangeRate rateOf(String line) {
        return deserializer.deserialize(line, currencies);
    }

    private static void readHeadersWith(BufferedReader reader) throws IOException {
        reader.readLine();
    }
}
