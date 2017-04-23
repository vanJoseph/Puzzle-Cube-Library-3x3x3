package wildercoding.puzzlecube;

/**
 * The class Edge is a Piecce that contains 2 faces
 *
 * @author Donovan J. Wilder
 * @since  2017-03-26
 * @version 1.0
 */
 public class Edge extends Piece {

    Edge(Face...faces) {
        super(faces);
        mPieceType=PieceType.EDGE;
        if (faces.length > 3) {
            throw new InvalidFaceException();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
