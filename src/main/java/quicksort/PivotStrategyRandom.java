package quicksort;

import common.SchluesselWertPaar;

import java.util.Random;

/**
 * Created by michelbrueger on 21.06.18.
 */
public class PivotStrategyRandom implements PivotStrategie {
    @Override
    public <T extends Comparable<T>, U> int getIndex(SchluesselWertPaar<T, U>[] a, int iLinks, int iRechts) {
        Random ran = new Random();
        int randomIndex = ran.nextInt(iRechts+1-iLinks)+iLinks;

        return randomIndex;
    }
}
