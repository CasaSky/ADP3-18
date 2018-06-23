package quicksort;

import common.SchluesselWertPaar;
import quicksort.PivotStrategie;

/**
 * Created by michelbrueger on 21.06.18.
 */
public class PivotStrategyMedian implements PivotStrategie {
    @Override
    public <T extends Comparable<T>, U> int getIndex(SchluesselWertPaar<T, U>[] a, int iLinks, int iRechts) {
        if(iLinks >= a.length || iRechts >= a.length) {
            throw new IllegalArgumentException("Beide Indizes müssen sich innerhalb des Arrays befinden!");
        }
        return computeMedian(a, iLinks, iRechts);
    }

    private <T extends Comparable<T>, U> int computeMedian(SchluesselWertPaar<T, U>[] a, int iLinks, int iRechts) {
        int medianIndex = -1;
        int middleIndex = (iRechts+iLinks)/2;
        T left =  a[iLinks].getSchluessel();
        T right = a[iRechts].getSchluessel();
        T middle = a[middleIndex].getSchluessel();

//        int median = left+right+middle - (Math.max(Math.max(left, right),middle)) - (Math.min(Math.min(left,right),middle));
//        T median;
        if(left.compareTo(right) < 0) {
            if(right.compareTo(middle) < 0) return iRechts;
            else if(left.compareTo(middle) < 0 ) return middleIndex;
            else return iLinks;
        } else {
            if(left.compareTo(middle) < 0) return iLinks;
            else if(middle.compareTo(right) > 0) return middleIndex;
            else return iRechts;
        }

//
//        if(median == left) medianIndex = iLinks;
//        if(median == right) medianIndex = iRechts;
//        if(median == middle) medianIndex = (iRechts+iLinks)/2;
//
//        return medianIndex;
    }
}
