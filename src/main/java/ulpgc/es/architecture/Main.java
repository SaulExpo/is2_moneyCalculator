package ulpgc.es.architecture;

import ulpgc.es.architecture.control.CsvCurrencyReader;
import ulpgc.es.architecture.control.CsvExchangeRateReader;
import ulpgc.es.architecture.control.CurrencyReader;
import ulpgc.es.architecture.control.ExchangeRateReader;
import ulpgc.es.architecture.model.Currency;
import ulpgc.es.architecture.model.ExchangeRate;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class Main {
    public static void main(String[] args) throws IOException {
        File currencyFile = new File("./currencies.csv");
        File rateFile = new File("./exchange_rates.csv");
        CurrencyReader currencyReader = new CsvCurrencyReader(currencyFile);
        Iterator<Currency> currencies = currencyReader.read();
        List<Currency> currencyList = StreamSupport.stream(Spliterators.spliteratorUnknownSize(currencies, Spliterator.ORDERED), false).toList();
        currencies = currencyList.iterator();
        ExchangeRateReader exchangeRateReader = new CsvExchangeRateReader(rateFile, currencyList);
        Iterator<ExchangeRate> exchangeRates = exchangeRateReader.read();
        while (currencies.hasNext()){
            System.out.println(currencies.next());
        }
        while (exchangeRates.hasNext()){
            System.out.println(exchangeRates.next());
        }
    }
}
