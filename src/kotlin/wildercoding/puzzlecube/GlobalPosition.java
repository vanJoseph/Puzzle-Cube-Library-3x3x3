package wildercoding.puzzlecube;

/**
 * The class GlobalPosition is a 2 dimensional representation of the coordinates of the Global Orientation Model.
 *
 * @author Donovan J. Wilder
 * @since 2017-03-26
 * @version 1.0
 */
 class GlobalPosition {
    private int mZPos;
    private int mListPos;

    public int getZPos() {
        return mZPos;
    }

    public void setZPos(int ZPos) {
        mZPos = ZPos;
    }

    public int getListPos() {
        return mListPos;
    }

    public void setListPos(int listPos) {
        mListPos = listPos;
    }

    GlobalPosition(int ZPos, int listPos) {
        mZPos = ZPos;
        mListPos = listPos;
    }
}
