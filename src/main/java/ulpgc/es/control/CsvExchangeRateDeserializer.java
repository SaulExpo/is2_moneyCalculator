package ulpgc.es.control;

import ulpgc.es.model.Currency;
import ulpgc.es.model.ExchangeRate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

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
        if (to == null){
            return null;
        }
        return new ExchangeRate(from, to, Double.parseDouble(columns[3]), LocalDate.parse(columns[4], formatter));
    }
}
