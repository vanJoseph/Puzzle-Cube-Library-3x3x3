package wildercoding.puzzlecube;

/**
 * The class Face contains the color and its side's global orientati
 *
 * @author Donovan J. Wilder
 * @since 2017-03-26
 * @version 1.0
 * */
public class Face  {
    private Color mColor;
    private Side mGlobalOrientation;

    public Color getColor() {
        return mColor;
    }

    public void setColor(Color color) {
        mColor = color;
    }

    public Side getGlobalOrientation() {
        return mGlobalOrientation;
    }

    void setGlobalOrientation(Side globalOrientation) {
        mGlobalOrientation = globalOrientation;
    }

    Face(Color color, Side globalOrientation) {
        mColor=color;
        mGlobalOrientation = globalOrientation;
    }

    @Override
    public String toString() {
        return "color: "+ mColor.toString()+"\t orientation: " + mGlobalOrientation.toString();
    }
}
