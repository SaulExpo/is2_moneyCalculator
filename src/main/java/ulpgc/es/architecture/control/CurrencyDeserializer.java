package ulpgc.es.architecture.control;


import ulpgc.es.architecture.model.Currency;

public interface CurrencyDeserializer {
    Currency deserialize(String value);
}
