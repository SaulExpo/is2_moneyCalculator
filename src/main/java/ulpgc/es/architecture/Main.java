package ulpgc.es.architecture;

import ulpgc.es.architecture.control.CsvCurrecyReader;
import ulpgc.es.architecture.control.CurrencyReader;
import ulpgc.es.architecture.model.Currency;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;


public class Main {
    public static void main(String[] args) throws IOException {
        File csvFile = new File("./currencies.csv");
        CurrencyReader reader = new CsvCurrecyReader(csvFile);
        Iterator<Currency> currencies = reader.read();
        while (currencies.hasNext()){
            System.out.println(currencies.next());
        }
    }
}
