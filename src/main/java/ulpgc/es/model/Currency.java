package ulpgc.es.model;

public record Currency(String code, String name, String symbol) {

    @Override
    public String toString() {
        return code;
    }
}
