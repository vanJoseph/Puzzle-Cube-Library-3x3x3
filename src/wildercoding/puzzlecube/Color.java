package wildercoding.puzzlecube;

/**
 * The enum Color give the choices of color that you can use and be used
 *
 * @author Donovan J. Wilder
 * @since 2017-03-26
 * @version 1.0
 */
public enum Color {
    WHITE, YELLOW, GREEN, BLUE, RED, ORANGE;

    @Override
    public String toString() {
        switch (this) {
            case WHITE:
                return "WHITE";
            case BLUE:
                return "BLUE";
            case RED:
                return "RED";
            case GREEN:
                return "GREEN";
            case ORANGE:
                return "ORANGE";
            case YELLOW:
                return "YELLOW";
            default:
                return null;
        }
    }
}
