package ulpgc.es.control;


import ulpgc.es.model.Currency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvCurrencyReader implements CurrencyReader {
    private final File source;
    private final CsvCurrencyDeserializer deserializer;

    public CsvCurrencyReader(File source) {
        this.source = source;
        this.deserializer = new CsvCurrencyDeserializer();
    }

    @Override
    public List<Currency> read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(source));
        readHeadersWith(reader);
        return readCurrencyWith(reader);
    }

    private List<Currency> readCurrencyWith(BufferedReader reader) throws IOException {
        List<Currency> currencies = new ArrayList<>();
        while (true) {
            String c = reader.readLine();
            if (c == null) break;
            currencies.add(new CsvCurrencyDeserializer().deserialize(c));
        }
        return currencies;

    }


    private static void readHeadersWith(BufferedReader reader) throws IOException {
        reader.readLine();
    }
}
