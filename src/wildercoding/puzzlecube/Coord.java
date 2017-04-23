package wildercoding.puzzlecube;

/**
 * The class Coord is a container for 2 dimension coordinate values
 *
 * @author Donovan J. Wilder
 * @since 2017-03-26
 * @version 1.0
 */
 class Coord {
    int x;
    int y;
    Coord(int x, int y) {
        this.x=x;
        this.y=y;
    }

    @Override
    public int hashCode() {
        return x*10+y;
    }
}
