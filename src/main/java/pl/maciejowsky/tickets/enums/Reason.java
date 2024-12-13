package pl.maciejowsky.tickets.enums;

public enum Reason {
    SPEEDING("Speeding"),
    NO_PARKING("No Parking"),
    RED_LIGHT("Red Light"),
    OTHER("Other");

    private final String description;

    Reason(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
