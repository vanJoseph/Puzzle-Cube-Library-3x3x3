package wildercoding.puzzlecube;

import java.util.List;

/**
 * The Cube class is the interface for the puzzle cube. It allows you to turn, and get the pieces for the puzzle.
 * The Global orientation model (GOM) is the way to be consistent with keeping piece to the puzzle in order, where the
 * cube is divided into a 3X9 array. The first diminsion represent the 3 layers facing you. the Front[0], the middle[1]
 * and the rear[2]. The second dimension reprsentent each piece of the layer, all labels being labled to the front
 * and. Position [1][4] is always thhe core of the puzzle and usually ahve a null value.
 *
 * @author Donovan J. Wilder
 * @version 1.0
 * @since 2017-03-26
 */
public class Cube {
    private Piece mGlobalOrientationModel[][] = new Piece[3][9];

    /**
     * Constructs a solved cube where Front is Red, Bottom is White, Right is Green, Left is Blue, Top is Yellow, and Rear
     * is Orange.
     */
    public Cube() {
        init_cube();
    }

    /**
     * This method returns the piece of the puzzle according to the Global Orientation model.
     * @param globalLayer
     * @param layerPosition
     * @return The piece from the GOM coordinates
     */
    public Piece get_piece(int globalLayer, int layerPosition) {
        return mGlobalOrientationModel[globalLayer][layerPosition];
    }

    /**
     * The method resets the cube to a solved cube where Front is read, Left is green, Right is blue, Bottom is White
     * Top is yellow, and Rear is orange.
     */
    public void init_cube() {
        //Global Layer [0]
        mGlobalOrientationModel[0][0] = new Corner(new Face(Color.RED, Side.FRONT), new Face(Color.YELLOW, Side.TOP), new Face(Color.BLUE, Side.LEFT));
        mGlobalOrientationModel[0][1] = new Edge(new Face(Color.RED, Side.FRONT), new Face(Color.YELLOW, Side.TOP));
        mGlobalOrientationModel[0][2] = new Corner(new Face(Color.RED, Side.FRONT), new Face(Color.YELLOW, Side.TOP), new Face(Color.GREEN, Side.RIGHT));
        mGlobalOrientationModel[0][3] = new Edge(new Face(Color.RED, Side.FRONT), new Face(Color.BLUE, Side.LEFT));
        mGlobalOrientationModel[0][4] = new Center(new Face(Color.RED, Side.FRONT));
        mGlobalOrientationModel[0][5] = new Edge(new Face(Color.RED, Side.FRONT), new Face(Color.GREEN, Side.RIGHT));
        mGlobalOrientationModel[0][6] = new Corner(new Face(Color.RED, Side.FRONT), new Face(Color.BLUE, Side.LEFT), new Face(Color.WHITE, Side.BOTTOM));
        mGlobalOrientationModel[0][7] = new Edge(new Face(Color.RED, Side.FRONT), new Face(Color.WHITE, Side.BOTTOM));
        mGlobalOrientationModel[0][8] = new Corner(new Face(Color.RED, Side.FRONT), new Face(Color.GREEN, Side.RIGHT), new Face(Color.WHITE, Side.BOTTOM));

        //Global Layer [1]
        mGlobalOrientationModel[1][0] = new Edge(new Face(Color.BLUE, Side.LEFT), new Face(Color.YELLOW, Side.TOP));
        mGlobalOrientationModel[1][1] = new Center(new Face(Color.YELLOW, Side.TOP));
        mGlobalOrientationModel[1][2] = new Edge(new Face(Color.YELLOW, Side.TOP), new Face(Color.GREEN, Side.RIGHT));
        mGlobalOrientationModel[1][3] = new Center(new Face(Color.BLUE, Side.LEFT));
        mGlobalOrientationModel[1][4] = null;
        mGlobalOrientationModel[1][5] = new Center(new Face(Color.GREEN, Side.RIGHT));
        mGlobalOrientationModel[1][6] = new Edge(new Face(Color.BLUE, Side.LEFT), new Face(Color.WHITE, Side.BOTTOM));
        mGlobalOrientationModel[1][7] = new Center(new Face(Color.WHITE, Side.BOTTOM));
        mGlobalOrientationModel[1][8] = new Edge(new Face(Color.WHITE, Side.BOTTOM), new Face(Color.GREEN, Side.RIGHT));

        //Global Layer[2]
        mGlobalOrientationModel[2][0] = new Corner(new Face(Color.BLUE, Side.LEFT), new Face(Color.YELLOW, Side.TOP), new Face(Color.ORANGE, Side.REAR));
        mGlobalOrientationModel[2][1] = new Edge(new Face(Color.YELLOW, Side.TOP), new Face(Color.ORANGE, Side.REAR));
        mGlobalOrientationModel[2][2] = new Corner(new Face(Color.YELLOW, Side.TOP), new Face(Color.GREEN, Side.RIGHT), new Face(Color.ORANGE, Side.REAR));
        mGlobalOrientationModel[2][3] = new Edge(new Face(Color.BLUE, Side.LEFT), new Face(Color.ORANGE, Side.REAR));
        mGlobalOrientationModel[2][4] = new Center(new Face(Color.ORANGE, Side.REAR));
        mGlobalOrientationModel[2][5] = new Edge(new Face(Color.GREEN, Side.RIGHT), new Face(Color.ORANGE, Side.REAR));
        mGlobalOrientationModel[2][6] = new Corner(new Face(Color.BLUE, Side.LEFT), new Face(Color.WHITE, Side.BOTTOM), new Face(Color.ORANGE, Side.REAR));
        mGlobalOrientationModel[2][7] = new Edge(new Face(Color.WHITE, Side.BOTTOM), new Face(Color.ORANGE, Side.REAR));
        mGlobalOrientationModel[2][8] = new Corner(new Face(Color.WHITE, Side.BOTTOM), new Face(Color.GREEN, Side.RIGHT), new Face(Color.ORANGE, Side.REAR));

    }


    /**
     * Returns an array of colors for the side that you choose.
     */
    public Color[] get_side_pieces_colors(Side side) {
        Color color[] = new Color[9];
        List<SideMapping> sideMapping = SideMapping.get(side);

        for (SideMapping coord : sideMapping)
        {
            Piece piece = get_piece(coord.getGlobalPosition().mZLayer, coord.getGlobalPosition().getListPos());
            for (Face face : piece.getFaces()) {
                if (face.getGlobalOrientation() == side) {
                    color[coord.getAbstractListPosition()]=face.getColor();
                }
            }
        }
        return color;
    }

    /**
     * Returns the colors of a side as an String.
     */
    public String get_side_colors_string(Side side) {
        Color[] colors = get_side_pieces_colors(side);
        StringBuilder colorString = new StringBuilder();
        for (Color nColor : colors) {
            colorString.append(nColor+ " ");
        }
        return colorString.toString();
    }
    /**
     *  This method changes the global orientation of the faces for a piece
     *
     * @param piece The piece whose face orientations that you want to change
     * @param turningSide The side that you are turning
     * @param direction The direction that you are turning the side
     */
    private void change_orientation(Piece piece, Side turningSide, TurnDirection direction) {
        for (Face face : piece.getFaces()) {
            switch (direction) {
                case CLOCKWISE:
                    face.setGlobalOrientation(get_clockwise_side(turningSide, face.getGlobalOrientation()));
                    break;
                case COUNTERCLOCKWISE:
                    face.setGlobalOrientation(get_counter_clockwise_side(turningSide, face.getGlobalOrientation()));
                    break;
            }
        }
    }

    /**
     * This method get the next side for a counter clockswise turn.
     *
     * @param turningSide Is the side that you want to turn from the GOM position
     * @param initalOrientation The initial GOM side the face was oriented
     * @return The next side of the counter clockwise turn
     */
    private Side get_counter_clockwise_side(Side turningSide, Side initalOrientation) {
        switch (turningSide) {
            case BOTTOM:
                switch (initalOrientation) {
                    case BOTTOM:
                        return Side.BOTTOM;
                    case LEFT:
                        return Side.REAR;
                    case REAR:
                        return Side.RIGHT;
                    case RIGHT:
                        return Side.FRONT;
                    case FRONT:
                        return Side.LEFT;
                }
            case REAR:
                switch (initalOrientation) {
                    case REAR:
                        return Side.REAR;
                    case TOP:
                        return Side.RIGHT;
                    case RIGHT:
                        return Side.BOTTOM;
                    case BOTTOM:
                        return Side.LEFT;
                    case LEFT:
                        return Side.TOP;
                }
            case TOP:
                switch (initalOrientation) {
                    case TOP:
                        return Side.TOP;
                    case FRONT:
                        return Side.RIGHT;
                    case RIGHT:
                        return Side.REAR;
                    case REAR:
                        return Side.LEFT;
                    case LEFT:
                        return Side.FRONT;
                }
            case LEFT:
                switch (initalOrientation) {
                    case LEFT:
                        return Side.LEFT;
                    case FRONT:
                        return Side.TOP;
                    case TOP:
                        return Side.REAR;
                    case REAR:
                        return Side.BOTTOM;
                    case BOTTOM:
                        return Side.FRONT;
                }
            case RIGHT:
                switch (initalOrientation) {
                    case RIGHT:
                        return Side.RIGHT;
                    case FRONT:
                        return Side.BOTTOM;
                    case BOTTOM:
                        return Side.REAR;
                    case REAR:
                        return Side.TOP;
                    case TOP:
                        return Side.FRONT;
                }
            case FRONT:
                switch (initalOrientation) {
                    case FRONT:
                        return Side.FRONT;
                    case TOP:
                        return Side.LEFT;
                    case LEFT:
                        return Side.BOTTOM;
                    case BOTTOM:
                        return Side.RIGHT;
                    case RIGHT:
                        return Side.TOP;
                }
        }
        return null;
    }

    /**
     * This method get the next side for a  clockswise turn.
     *
     * @param turningSide Is the side that you want to turn from the GOM position
     * @param initalOrientation The initial GOM side the face was oriented
     * @return The next side of the clockwise turn
     */
    private Side get_clockwise_side(Side turningSide, Side initalOrientation) {
        switch (turningSide) {
            case TOP:
                switch (initalOrientation) {
                    case TOP:
                        return Side.TOP;
                    case LEFT:
                        return Side.REAR;
                    case REAR:
                        return Side.RIGHT;
                    case RIGHT:
                        return Side.FRONT;
                    case FRONT:
                        return Side.LEFT;
                }
            case FRONT:
                switch (initalOrientation) {
                    case FRONT:
                        return Side.FRONT;
                    case TOP:
                        return Side.RIGHT;
                    case RIGHT:
                        return Side.BOTTOM;
                    case BOTTOM:
                        return Side.LEFT;
                    case LEFT:
                        return Side.TOP;
                }
            case BOTTOM:
                switch (initalOrientation) {
                    case BOTTOM:
                        return Side.BOTTOM;
                    case FRONT:
                        return Side.RIGHT;
                    case RIGHT:
                        return Side.REAR;
                    case REAR:
                        return Side.LEFT;
                    case LEFT:
                        return Side.FRONT;
                }
            case RIGHT:
                switch (initalOrientation) {
                    case RIGHT:
                        return Side.RIGHT;
                    case FRONT:
                        return Side.TOP;
                    case TOP:
                        return Side.REAR;
                    case REAR:
                        return Side.BOTTOM;
                    case BOTTOM:
                        return Side.FRONT;
                }
            case LEFT:
                switch (initalOrientation) {
                    case LEFT:
                        return Side.LEFT;
                    case FRONT:
                        return Side.BOTTOM;
                    case BOTTOM:
                        return Side.REAR;
                    case REAR:
                        return Side.TOP;
                    case TOP:
                        return Side.FRONT;
                }
            case REAR:
                switch (initalOrientation) {
                    case REAR:
                        return Side.REAR;
                    case TOP:
                        return Side.LEFT;
                    case LEFT:
                        return Side.BOTTOM;
                    case BOTTOM:
                        return Side.RIGHT;
                    case RIGHT:
                        return Side.TOP;
                }
        }
        return null;
    }

    /**
     * This method is used for the abstract layer grid to get the coordinate of a position for a counter clockwise turn
     *
     * @param x The x value for the position that you want to change
     * @param y The y value for the position that you want to change
     * @return A Coord object of the ccounter clockwise turn
     */
    private Coord get_counter_clockwise_coord(int x, int y) {

        switch (new Coord(x, y).hashCode()) {
            //Corners
            case 0:
                return new Coord(0, 2);
            case 2:
                return new Coord(2, 2);
            case 22:
                return new Coord(2, 0);
            case 20:
                return new Coord(0, 0);
            //Edges
            case 12:
                return new Coord(2, 1);
            case 21:
                return new Coord(1, 0);
            case 10:
                return new Coord(0, 1);
            case 1:
                return new Coord(1, 2);
            case 11:
                return new Coord(1, 1);
        }
        return null;
    }

    /**
     * This method is used for the abstract layer grid to get the coordinate of a position for a clockwise turn.
     *
     * @param x The x value for the position that you want to change
     * @param y The y value for the position that you want to change
     * @return A Coor object of the clockwise turn
     */
    private Coord get_clockwise_coord(int x, int y) {

        switch (new Coord(x, y).hashCode()) {
            //Corners
            case 0:
                return new Coord(2, 0);
            case 20:
                return new Coord(2, 2);
            case 22:
                return new Coord(0, 2);
            case 2:
                return new Coord(0, 0);
            //Edges
            case 12:
                return new Coord(0, 1);
            case 1:
                return new Coord(1, 0);
            case 10:
                return new Coord(2, 1);
            case 21:
                return new Coord(1, 2);
            case 11:
                return new Coord(1, 1);
        }
        return null;
    }

    /**
     * This method alows you to make concrete changes to the cube
     *
     * @param side The side that you want to change
     * @param direction The direction to turn the side when facing you
     */
    public void turn_cube(Side side, TurnDirection direction) {
        Piece[] currentAbstractLayer = get_abstract_layer_array(side);
        Piece[] newAbstractLayer = get_abstract_turn_array(currentAbstractLayer, side, direction);
        set_abstract_change_to_global_model(side, newAbstractLayer);
    }

    /**
     * Tis method gets the abstract layer array for a side of the GOM
     *
     * @param side The side of the GOM
     * @return An array of the pieces of for the side
     */
    private Piece[] get_abstract_layer_array(Side side) {
        Piece[] abstractLayerList = new Piece[9];

        for (SideMapping map : SideMapping.get(side)) {
            abstractLayerList[map.getAbstractListPosition()] = mGlobalOrientationModel[map.getGlobalPosition().getZLayer()][map.getGlobalPosition().getListPos()];
        }
        return abstractLayerList;
    }

    /**
     * This method takes an abstract array of a side and makes a turn
     *
     * @param lastAbstractArray The array that you are trying to change
     * @param side The side that you want to turn
     * @param turnDirection The direction of the turn
     * @return The abstract array list after the turn
     */
    private Piece[] get_abstract_turn_array(Piece[] lastAbstractArray, Side side, TurnDirection turnDirection) {
        Piece[][] abstractGrid = new Piece[3][3];

        int index = 0;
        // Puts the lastAbstractArray into a 2D grid array called abstractGrid
        for (int y = 0; y <= 2; y++) {
            for (int x = 0; x <= 2; x++) {
                abstractGrid[y][x] = lastAbstractArray[index];
                index++;
            }
        }
        Piece[][] newAbstractGrid = new Piece[3][3];

        //Takes the abstractGrid and maps it to the newAbstractGrid while changing
        //the face orientation
        for (int y = 0; y <= 2; y++) {
            for (int x = 0; x <= 2; x++) {

                Coord turnCoord = turnDirection == TurnDirection.CLOCKWISE ? get_clockwise_coord(x, y) : get_counter_clockwise_coord(x, y);
                change_orientation(abstractGrid[x][y], side, turnDirection);
                newAbstractGrid[turnCoord.y][turnCoord.x] = abstractGrid[y][x];

            }
        }

        Piece[] newAbstractArray = new Piece[9];
        index = 0;
        //Takes the newAbstractGrid and puts it into a 1D array called newAbstractArray
        for (int y = 0; y <= 2; y++) {
            for (int x = 0; x <= 2; x++) {
                newAbstractArray[index] = newAbstractGrid[y][x];
                index++;
            }
        }

        //Retun the results of the Turn
        return newAbstractArray;
    }

    /**
     * This method allows you to swap values from an abstract layer to the Global orientation model
     *
     * @param side The side of the GOM that you want to swap
     * @param abstractLayerList The values of the changed abstract layer
     */
    private void set_abstract_change_to_global_model(Side side, Piece[] abstractLayerList) {
        for (SideMapping map : SideMapping.get(side)) {
            mGlobalOrientationModel[map.getGlobalPosition().getZLayer()][map.getGlobalPosition().getListPos()] = abstractLayerList[map.getAbstractListPosition()];
        }
    }
// TODO Need to make sure that Builder works properly
    public static class Builder {
        private Cube mCube;

        public Builder() {
            mCube= new Cube();
        }

        public Cube turn_side(Side side, TurnDirection direction) {
            mCube.turn_cube(side, direction);
            return mCube;
        }

        public Cube reset_cube(){
            mCube.init_cube();

            return mCube;
        }


    }

    public Cube get_cube() {
        return this;
    }
    @Override
    public String toString() {
        String cubeString = "wildercoding.puzzlecube.Cube: \n";
        for (int y = 0; y < 3; y++) {
            cubeString += "\t Z-Layer: " + y + "\n";
            for (int x = 0; x < 9; x++) {
                if (mGlobalOrientationModel[y][x] != null)
                    cubeString += "\t\t Pos: " + x + " " + mGlobalOrientationModel[y][x].toString() + "\n";
                else
                    cubeString += "\t\t Pos: " + x + "core\n\n";
            }
        }

        return cubeString;
    }


}
