package ulpgc.es;

import ulpgc.es.control.*;
import ulpgc.es.model.Currency;
import ulpgc.es.model.ExchangeRate;
import ulpgc.es.view.MainFrame;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;


public class Main {
    public static void main(String[] args) throws IOException {
        File currencyFile = new File("./currencies.csv");
        File rateFile = new File("./exchange_rates.csv");
        CurrencyReader currencyReader = new CsvCurrencyReader(currencyFile);
        List<Currency> currencies = currencyReader.read();
        ExchangeRateReader exchangeRateReader = new CsvExchangeRateReader(rateFile, currencies);
        List<ExchangeRate> exchangeRates = exchangeRateReader.read();
        MainFrame mainFrame = new MainFrame(currencies);
        ExchangeCommand exchangeCommand = new ExchangeCommand(
                mainFrame.moneyDialog(),
                mainFrame.currencyDialog(),
                new WindowsExchangeRateLoader(),
                mainFrame.moneyDisplay(),
                exchangeRates
        );
        mainFrame.add("exchange", exchangeCommand);
        mainFrame.setVisible(true);
    }
}
