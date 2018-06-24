package quicksort;

import common.SchluesselWertPaar;
import quicksort.PivotStrategie;
import quicksort.Quicksort;

/**
 * Created by michelbrueger on 21.06.18.
 */
public class QuicksortLoesung<T extends Comparable<T>, U> extends Quicksort {


    /**
     * Konstruktor
     * @param pivotStrategy
     */
    public QuicksortLoesung(PivotStrategie pivotStrategy) {
//        this.pivotStrategy = pivotStrategy;
        super(pivotStrategy);
    }

    public PivotStrategie getPivotStrategy(){
        return super.pivotStrategy;
    }

    /**
     * Sortiert das Array a in-situ
     * @param a Array mit Schlüssel-Wert-Paaren, die nach den Schlüsseln aufsteigend sortiert werden sollen.
     */
    @Override
    public void sortiere(SchluesselWertPaar[] a) {
        _quickSort(0, a.length-1, a);
    }

    private <T extends Comparable<T>, U> int partition(SchluesselWertPaar<T, U>[] a, int links, int rechts, T pivot) {
        int l, r, help;
        l = links;
        r = rechts-1;
        while(l<=r) {
            while((a[l].getSchluessel().compareTo(pivot) <= 0) && (l < rechts)){
                l++;
            }
            while((links <= r) && (a[r].getSchluessel().compareTo(pivot) > 0)) {
                r--;
            }
            if (l<r) {
                swap(a, l, r);
            }
        }
        swap(a, l, rechts);
        return l;
    }

    /**
     * Hilfsfunktion für rekursion in sortiere(SchluesselWertPaar[] a)
     * @param a Array mit Schlüssel-Wert-Paaren, die nach den Schlüsseln aufsteigend sortiert werden sollen.
     * @param links Index ab dem sortiert werden soll
     * @param rechts Index bis zu dem sortiert werden soll
     */
    private <T extends Comparable<T>, U> void _sortiere(SchluesselWertPaar<T, U>[] a, int links, int rechts) {
        int pivotIndex = pivotStrategy.getIndex(a, links, rechts);
        T pivot = a[pivotIndex].getSchluessel();
        swap(a, pivotIndex, rechts);

        int i = partition(a, links, rechts, pivot);
        _sortiere(a, links, i-1);
        _sortiere(a, i+1, rechts);


 /*       if(a.length-1 < links || a.length-1 < rechts) throw new IllegalArgumentException("Beide Indizes müssen innerhalb des Arrays liegen (_sortiere())");
        if(links > rechts) throw new IllegalArgumentException("Index rechts muss größer sein als Index links.");
        if(a.length <= 1) return;
        int pivotIndex = pivotStrategy.getIndex(a, links, rechts);
//        T pivotKey = (T) a[pivotIndex].getSchluessel();
//        if(a.length <= 1){ return; }

        while(links < rechts){
            T pivotKey = (T) a[pivotIndex].getSchluessel();                             // Nochmal drüber nachdenken wo man das plazieren könnte
            while(a[links].getSchluessel().compareTo(pivotKey) < 0) links += 1;
            while(a[rechts].getSchluessel().compareTo(pivotKey) > 0) rechts -= 1;
            if(links<rechts) swap(a,links,rechts);
        }
        if(a[links].getSchluessel().compareTo(a[pivotIndex].getSchluessel()) > 0) swap(a, links, pivotIndex);
        _sortiere(a, links, pivotIndex);
        _sortiere(a,pivotIndex+1, a.length-1);
*/

    }


    /**
     * 2 Elemente eines Arrays wechseln ihre Positionen (Indizes)
     * @param a Array welches an genannten Indizes Elemente enthält
     * @param links Index von Element1
     * @param rechts Index von Element2
     */
    private void swap(SchluesselWertPaar[] a, int links, int rechts) {
        if(a.length-1 < links || a.length-1 < rechts) throw new IllegalArgumentException("Beide Indizes müssen innerhalb des Arrays liegen (swap())");
        SchluesselWertPaar temp = a[links];
        a[links] = a[rechts];
        a[rechts] = temp;
    }

//    @Override
//    public  <T extends Comparable<T>, U> void quickSort(SchluesselWertPaar<T,U>[] arr){
//        _quickSort(0, arr.length-1, arr);
//    }

    @Override
    public void quickSort(SchluesselWertPaar[] arr) {
        _quickSort(0, arr.length-1, arr);
    }

    private  <T extends  Comparable<T>, U> void _quickSort(int leftIndex, int rightIndex, SchluesselWertPaar<T, U>[] arr){
        if(leftIndex >= rightIndex) {
            return;
        }

        int i = leftIndex;
        int k = rightIndex-1;
        T pivot = arr[rightIndex].getSchluessel();

        do{
            while((arr[i].getSchluessel().compareTo(pivot) <= 0) && (i < rightIndex)){
                i++;
            }
            while((arr[k].getSchluessel().compareTo(pivot) >= 0) && (k > leftIndex)){
                k--;
            }
            if(i < k){
                swap(arr, i, k);
            }
        }while(i < k);

        if(arr[i].getSchluessel().compareTo(pivot) >= 0){
            swap(arr, i, rightIndex);
        }

        _quickSort(leftIndex, i-1, arr);
        _quickSort(i+1, rightIndex, arr);

    }

}
