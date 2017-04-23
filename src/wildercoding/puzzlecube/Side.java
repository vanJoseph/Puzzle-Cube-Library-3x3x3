package wildercoding.puzzlecube;

/**
 * The enum Side gives you choices between the side that you can use.
 *
 * @author Donovan J. Wilder
 * @version 1.0
 * @since 2017-03-26
 */
public enum Side {
    FRONT, REAR, TOP, BOTTOM, RIGHT, LEFT;

    //TODO add a string to enum method public static
    @Override
    public String toString() {
        switch (this) {
            case BOTTOM:
                return "BOTTOM";
            case RIGHT:
                return "RIGHT";
            case FRONT:
                return "FRONT";
            case REAR:
                return "REAR";
            case LEFT:
                return "LEFT";
            case TOP:
                return "TOP";
            default:
                return null;
        }
    }
}
