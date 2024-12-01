package ulpgc.es.architecture.control;


import ulpgc.es.architecture.model.Currency;

public class CsvCurrencyDeserializer implements CurrencyDeserializer {
    @Override
    public Currency deserialize(String value) {
        String[] columns = value.split(",");
        return new Currency(columns[2], columns[1], columns[3]);
    }

}
