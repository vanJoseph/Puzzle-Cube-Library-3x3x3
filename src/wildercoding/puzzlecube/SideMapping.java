package wildercoding.puzzlecube;

import java.util.ArrayList;
import java.util.List;

/**
 * The class SideMapping give you the association from a side's GOM position and its postion
 * in a abstract layer array.
 *
 * @author Donovan J. Wilder
 * @since 2017-03-26
 * @version 1.0
 */

 class SideMapping {

    class  Association {
        int mZLayer;
        int mListPos;

        Association(int zLayer, int mListPos) {
            this.mListPos = mListPos;
            mZLayer=zLayer;
        }
        int getZLayer() {
            return mZLayer;
        }

        public void setZLayer(int ZLayer) {
            mZLayer = ZLayer;
        }

        int getListPos() {
            return mListPos;
        }

        public void setmListPos(int mlistPos) {
            this.mListPos = mlistPos;
        }
    }
    private  int  mAbstractListPosition;

    int getAbstractListPosition() {
        return mAbstractListPosition;
    }

    Association getGlobalPosition() {
        return mGlobalPosition;
    }

    private  Association mGlobalPosition;

     private SideMapping(int ZLayer, int listPos, int abstractListPosition) {
        mAbstractListPosition=abstractListPosition;
        mGlobalPosition=new Association(ZLayer,listPos);
    }

    static List<SideMapping> get(Side side) {
        List<SideMapping> list=new ArrayList<>();
        switch (side) {
            case TOP:
                list.add(new SideMapping(2,0,0));
                list.add(new SideMapping(2,1,1));
                list.add(new SideMapping(2,2,2));
                list.add(new SideMapping(1,0,3));
                list.add(new SideMapping(1,1,4));
                list.add(new SideMapping(1,2,5));
                list.add(new SideMapping(0,0,6));
                list.add(new SideMapping(0,1,7));
                list.add(new SideMapping(0,2,8));
                break;
            case LEFT:
                list.add(new SideMapping(2,0,0));
                list.add(new SideMapping(1,0,1));
                list.add(new SideMapping(0,0,2));
                list.add(new SideMapping(2,3,3));
                list.add(new SideMapping(1,3,4));
                list.add(new SideMapping(0,3,5));
                list.add(new SideMapping(2,6,6));
                list.add(new SideMapping(1,6,7));
                list.add(new SideMapping(0,6,8));
                break;
            case REAR:
                list.add(new SideMapping(2,2,0));
                list.add(new SideMapping(2,1,1));
                list.add(new SideMapping(2,0,2));
                list.add(new SideMapping(2,5,3));
                list.add(new SideMapping(2,4,4));
                list.add(new SideMapping(2,3,5));
                list.add(new SideMapping(2,8,6));
                list.add(new SideMapping(2,7,7));
                list.add(new SideMapping(2,6,8));
                break;
            case FRONT:
                list.add(new SideMapping(0,0,0));
                list.add(new SideMapping(0,1,1));
                list.add(new SideMapping(0,2,2));
                list.add(new SideMapping(0,3,3));
                list.add(new SideMapping(0,4,4));
                list.add(new SideMapping(0,5,5));
                list.add(new SideMapping(0,6,6));
                list.add(new SideMapping(0,7,7));
                list.add(new SideMapping(0,8,8));
                break;
            case RIGHT:
                list.add(new SideMapping(0,2,0));
                list.add(new SideMapping(1,2,1));
                list.add(new SideMapping(2,2,2));
                list.add(new SideMapping(0,5,3));
                list.add(new SideMapping(1,5,4));
                list.add(new SideMapping(2,5,5));
                list.add(new SideMapping(0,8,6));
                list.add(new SideMapping(1,8,7));
                list.add(new SideMapping(2,8,8));
                break;
            case BOTTOM:
                list.add(new SideMapping(0,6,0));
                list.add(new SideMapping(0,7,1));
                list.add(new SideMapping(0,8,2));
                list.add(new SideMapping(1,6,3));
                list.add(new SideMapping(1,7,4));
                list.add(new SideMapping(1,8,5));
                list.add(new SideMapping(2,6,6));
                list.add(new SideMapping(2,7,7));
                list.add(new SideMapping(2,8,8));
                break;
        }
        return list;
    }

}
