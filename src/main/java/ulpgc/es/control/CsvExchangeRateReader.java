package ulpgc.es.control;

import ulpgc.es.model.Currency;
import ulpgc.es.model.ExchangeRate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    public List<ExchangeRate> read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(source));
        readHeadersWith(reader);
        return readRateWith(reader);
    }

    private List<ExchangeRate> readRateWith(BufferedReader reader) throws IOException {
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        while (true) {
            String e = reader.readLine();
            if (e == null) break;
            ExchangeRate exchangeRate = new CsvExchangeRateDeserializer().deserialize(e, currencies);
            if (exchangeRate != null) exchangeRates.add(exchangeRate);
        }
        return exchangeRates;

    }

    private static void readHeadersWith(BufferedReader reader) throws IOException {
        reader.readLine();
    }
}
