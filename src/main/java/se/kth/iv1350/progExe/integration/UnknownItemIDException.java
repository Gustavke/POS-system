package se.kth.iv1350.progExe.integration;

public class UnknownItemIDException extends Exception {
    private int itemIDThatIsUnknown;

    public UnknownItemIDException(int itemIDThatIsUnknown) {
        super("Unknown item ID: " + itemIDThatIsUnknown);
        this.itemIDThatIsUnknown = itemIDThatIsUnknown;
    }

    public int getItemIDThatIsUnknown() {
        return itemIDThatIsUnknown;
    }
}
    