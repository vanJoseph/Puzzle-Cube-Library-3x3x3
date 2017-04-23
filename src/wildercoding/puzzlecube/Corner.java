package wildercoding.puzzlecube;

/**
 * The class Corner is a Piece that contains 3 faces.
 *
 * @author Donovan J. Wilder
 * @since 2017-03-26
 * @version 1.0
 */
 public class Corner extends Piece {

    Corner(Face...faces) {
        super(faces);
        mPieceType= PieceType.CORNER;
        if (faces.length > 3) {
            throw new InvalidFaceException();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
