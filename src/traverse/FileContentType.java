package traverse;

public enum FileContentType {
    PESEL("pesel", 10),
    POSTCODE("kod pocztowy", 1),
    PHONE("telefon", 5),
    EMAIL("e-mail", 5),
    NAME("imię i nazwisko", 5);

    private final String label;
    private final int weight;
    FileContentType(String label, int weight) {
        this.label = label;
        this.weight = weight;
    }

    public String getLabel() {
        return label;
    }

    public int getWeight() {
        return weight;
    }
}
