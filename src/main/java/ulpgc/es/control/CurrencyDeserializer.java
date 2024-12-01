package ulpgc.es.control;


import ulpgc.es.model.Currency;

public interface CurrencyDeserializer {
    Currency deserialize(String value);
}
