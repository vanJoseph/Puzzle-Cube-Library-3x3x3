package wildercoding.puzzlecube;

/**
 * Created by dwilder1181 on 3/27/2017.
 */
public enum PieceType {
    CORNER,CENTER,EDGE;

    @Override
    public String toString() {
        switch (this) {
            case EDGE:
                return "Edge";
            case CENTER:
                return "Center";
            case CORNER:
                return "Corner";
        }
        return null;
    }
}
