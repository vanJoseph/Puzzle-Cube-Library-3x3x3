package wildercoding.puzzlecube;

/**
 * The Piece class is contains the faces of a piece. There are 3 types of pieces.
 * Corner which have 3 face, Edge which have 2 faces and a  Center which have 1 face.
 *
 * @author Donovan J. Wilder
 * @version 1.0
 * @since 2017-03-26
 */
public abstract class Piece {

    public PieceType getPieceType() {
        return mPieceType;
    }

    protected Face mFaces[];
    protected PieceType mPieceType;

    Piece(Face... face) {
        mFaces = face;
    }

    public Face[] getFaces() {
        return mFaces;
    }

    public void setFaces(Face[] faces) {
        mFaces = faces;
    }

    @Override
    public String toString() {
        String string = "wildercoding.puzzlecube.Piece: " + mPieceType + "\n";
        int index = 0;
        for (Face face : mFaces) {
            string += "\t\t\twildercoding.puzzlecube.Face: " + index + "\t" + face.toString() + "\n";
            index++;
        }
        return string;
    }
}
