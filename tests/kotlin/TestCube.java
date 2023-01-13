package wildercoding.puzzlecube.Test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import wildercoding.puzzlecube.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Test whether a cube is a valid which means that it has the correct number of pieces, colors, sides, And that no pieces
 * contains opposite colors.
 *
 * @author Donovan J. Wilder
 * @version 1.0
 * @since 2017-03-27
 */
@RunWith(Parameterized.class)
public class TestCube {

    private Cube mTestCube;


    @Parameterized.Parameters
    public static Collection turnTest() {
        return Arrays.asList(new Object[][]{
                {new Cube()},
                {new Cube.Builder().turn_side(Side.FRONT, TurnDirection.CLOCKWISE)},
                {new Cube.Builder().turn_side(Side.FRONT, TurnDirection.COUNTERCLOCKWISE)},

                {new Cube.Builder().turn_side(Side.REAR, TurnDirection.CLOCKWISE)},
                {new Cube.Builder().turn_side(Side.REAR, TurnDirection.COUNTERCLOCKWISE)},

                {new Cube.Builder().turn_side(Side.LEFT, TurnDirection.CLOCKWISE)},

                {new Cube.Builder().turn_side(Side.TOP, TurnDirection.CLOCKWISE)},
                {new Cube.Builder().turn_side(Side.TOP, TurnDirection.COUNTERCLOCKWISE)},

                {new Cube.Builder().turn_side(Side.BOTTOM, TurnDirection.CLOCKWISE)},
                {new Cube.Builder().turn_side(Side.BOTTOM, TurnDirection.COUNTERCLOCKWISE)}});
        }
public TestCube(Cube cube) {
            mTestCube = cube;
        }

        @Test
        public void Given_Cube_AllColorsHaveNineFaces_Then_CubeIsValid () {
            Integer colorCount[] = {0, 0, 0, 0, 0, 0};

            for (int globalLayer = 0; globalLayer < 3; globalLayer++) {
                for (int layerPosition = 0; layerPosition < 9; layerPosition++) {
                    if (mTestCube.get_piece(globalLayer, layerPosition) == null) {
                        if (globalLayer == 1 && layerPosition == 4) {
                            continue;
                        }
                    }
                    Face faces[] = mTestCube.get_piece(globalLayer, layerPosition).getFaces();

                    for (Face face : faces) {
                        switch (face.getColor()) {

                            case RED:
                                colorCount[0]++;
                                break;
                            case ORANGE:
                                colorCount[1]++;
                                break;
                            case BLUE:
                                colorCount[2]++;
                                break;
                            case GREEN:
                                colorCount[3]++;
                                break;
                            case WHITE:
                                colorCount[4]++;
                                break;
                            case YELLOW:
                                colorCount[5]++;
                                break;

                        }
                    }
                }
            }

            for (Integer count : colorCount) {
                Assert.assertEquals(9, count.intValue());
            }

        }

        @Test
        public void Given_Cube_AllSidesHaveNineFaces_Then_CubeIsValid () {


            Integer sideCount[] = {0, 0, 0, 0, 0, 0};

            for (int globalLayer = 0; globalLayer < 3; globalLayer++) {
                for (int layerPosition = 0; layerPosition < 9; layerPosition++) {
                    if (mTestCube.get_piece(globalLayer, layerPosition) == null) {
                        if (globalLayer == 1 && layerPosition == 4) {
                            continue;
                        }
                    }
                    Face faces[] = mTestCube.get_piece(globalLayer, layerPosition).getFaces();

                    for (Face face : faces) {
                        switch (face.getGlobalOrientation()) {

                            case TOP:
                                sideCount[0]++;
                                break;
                            case BOTTOM:
                                sideCount[1]++;
                                break;
                            case RIGHT:
                                sideCount[2]++;
                                break;
                            case LEFT:
                                sideCount[3]++;
                                break;
                            case FRONT:
                                sideCount[4]++;
                                break;
                            case REAR:
                                sideCount[5]++;
                                break;

                        }
                    }
                }
            }

            for (Integer count : sideCount) {
                Assert.assertEquals(9, count.intValue());
            }
        }

        @Test
        public void Given_Cube_MustHave27Pieces_Then_CubeIsValid () {

            int numPieces = 0;
            for (int zLayer = 0; zLayer < 3; zLayer++) {
                for (int piece = 0; piece < 9; piece++) {
                    if (mTestCube.get_piece(zLayer, piece) != null) {
                        numPieces++;
                    }
                }
            }
            Assert.assertEquals(26, numPieces);
        }

        @Test
        public void Given_Cube_MustHaveEightCorners_Then_CubeIsValid () {

            int numPieces = 0;
            for (int zLayer = 0; zLayer < 3; zLayer++) {
                for (int piece = 0; piece < 9; piece++) {
                    if (mTestCube.get_piece(zLayer, piece) != null && mTestCube.get_piece(zLayer, piece).getPieceType() == PieceType.CORNER) {
                        numPieces++;
                    }
                }
            }
            Assert.assertEquals(8, numPieces);
        }

        @Test
        public void Given_Cube_MustHave12Edges_Then_CubeIsValid () {

            int numPieces = 0;
            for (int zLayer = 0; zLayer < 3; zLayer++) {
                for (int piece = 0; piece < 9; piece++) {
                    if (mTestCube.get_piece(zLayer, piece) != null && mTestCube.get_piece(zLayer, piece).getPieceType() == PieceType.EDGE) {
                        numPieces++;
                    }
                }
            }
            Assert.assertEquals(12, numPieces);
        }

        @Test
        public void Given_Cube_MustHave6Centers_Then_CubeIsValid () {

            int numPieces = 0;
            for (int zLayer = 0; zLayer < 3; zLayer++) {
                for (int piece = 0; piece < 9; piece++) {
                    if (mTestCube.get_piece(zLayer, piece) != null && mTestCube.get_piece(zLayer, piece).getPieceType() == PieceType.CENTER) {
                        numPieces++;
                    }
                }
            }
            Assert.assertEquals(6, numPieces);
        }

        @Test
        public void Given_Cube_NoPieceHasOppositeColoredFaces_Then_CubeIsValid () {


            for (int zLayer = 0; zLayer < 3; zLayer++) {
                for (int piece = 0; piece < 9; piece++) {
                    if (mTestCube.get_piece(zLayer, piece) != null) {
                        Assert.assertFalse("There are opposite colors on piece: (" + zLayer + "," + piece + ")", contain_opposite_colors(mTestCube.get_piece(zLayer, piece)));
                    }
                }
            }
        }

    public Color get_opposite_color(Color color) {
        switch (color) {
            case RED:
                return Color.ORANGE;
            case ORANGE:
                return Color.RED;
            case YELLOW:
                return Color.WHITE;
            case WHITE:
                return Color.YELLOW;
            case GREEN:
                return Color.BLUE;
            case BLUE:
                return Color.GREEN;
        }
        return null;
    }

    /**
     * Checks a piece to see if it contains opposite colors on the same piece if it does the puzzle is not valid
     *
     * @param piece
     * @return True if there are opposite colors on a piece False if there are no opposite collors on a piece
     */
    public boolean contain_opposite_colors(Piece piece) {
        Face[] faces = piece.getFaces();
        ArrayList<Color> facesColors = new ArrayList<>();

        for (Face face : faces) {
            facesColors.add(face.getColor());
        }

        for (Face face : faces) {
            if (facesColors.contains(get_opposite_color(face.getColor()))) {
                return true;
            }
        }
        return false;
    }

}
