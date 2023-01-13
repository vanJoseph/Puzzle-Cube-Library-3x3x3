package wildercoding.puzzlecube;

/**
 * This enum gives the option of either a clockwise turn or a counter-clockwise turn.
 *
 * @author Donovan J. Wilder
 * @since 2017-03-26
 * @version 1.0
 */
public enum  TurnDirection {
    CLOCKWISE,COUNTERCLOCKWISE;

    @Override
    public String toString() {
        switch (this) {
            case CLOCKWISE:
                return "CLOCKWISE";
            case COUNTERCLOCKWISE:
                return "COUNTERCLOCKWISE";
        }
        return null;
    }
}
