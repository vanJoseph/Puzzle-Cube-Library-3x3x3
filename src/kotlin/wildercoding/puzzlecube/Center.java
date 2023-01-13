package wildercoding.puzzlecube;

/**
 * The class Center is a Piece that has one side.
 *
 * @author Donovan J. Wilder
 * @version 1.0
 * @since 2017-03-26
 */
public class Center extends Piece {

    Center(Face...faces) {
        super(faces);
        mPieceType=PieceType.CENTER;
        if (faces.length > 1) {
            throw new InvalidFaceException();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
