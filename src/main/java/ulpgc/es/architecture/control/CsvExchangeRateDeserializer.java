package ulpgc.es.architecture.control;

import ulpgc.es.architecture.model.Currency;
import ulpgc.es.architecture.model.ExchangeRate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvExchangeRateDeserializer implements ExchangeRateDeserializer {
    private Currency to;
    private Currency from;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Override
    public ExchangeRate deserialize(String value, List<Currency> currencyList) {
        String[] columns = value.split(",");
        for (Currency c:currencyList) {
            if(Objects.equals(c.toString(), "EUR")) {
                from = c;
            }
            if(Objects.equals(c.toString(), columns[2]))  {
                to = c;
            }
        }
        return new ExchangeRate(from, to, Double.parseDouble(columns[3]), LocalDate.parse(columns[4], formatter));

    }
}
