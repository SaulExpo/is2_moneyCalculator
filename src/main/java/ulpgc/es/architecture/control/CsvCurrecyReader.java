package ulpgc.es.architecture.control;


import ulpgc.es.architecture.model.Currency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class CsvCurrecyReader implements CurrencyReader {
    private final File source;
    private final CsvCurrencyDeserializer deserializer;

    public CsvCurrecyReader(File source) {
        this.source = source;
        this.deserializer = new CsvCurrencyDeserializer();
    }

    @Override
    public Iterator<Currency> read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(source));
        readHeadersWith(reader);
        return readCurrencyWith(reader);
    }

    private Iterator<Currency> readCurrencyWith(BufferedReader reader) throws IOException {
        return new Iterator<Currency>() {
            String line = reader.readLine();
            @Override
            public boolean hasNext() {
                return line != null;
            }

            @Override
            public Currency next() {
                try {
                    Currency currency = line == null ? null : currencyOf(line);
                    line = reader.readLine();
                    if (line == null) reader.close();
                    return currency;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

    }

    private Currency currencyOf(String line) {
        return deserializer.deserialize(line);
    }

    private static void readHeadersWith(BufferedReader reader) throws IOException {
        reader.readLine();
    }
}
