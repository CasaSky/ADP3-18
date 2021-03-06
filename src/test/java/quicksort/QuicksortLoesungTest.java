package quicksort;

import common.SchluesselWertPaar;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michelbrueger on 23.06.18.
 */
public class QuicksortLoesungTest {
    Quicksort<Integer, String> qslMedian;
    PivotStrategie psMedian;
    PivotStrategie psRandom;
    PivotStrategie psLast;
    SchluesselWertPaar<Integer, String>[] daten5;
    SchluesselWertPaar<Integer, String>[] daten4;
    SchluesselWertPaar<Integer, String>[] daten1;
    SchluesselWertPaar<Integer, String>[] daten2;

    SchluesselWertPaar<Integer, String> swp1;
    SchluesselWertPaar<Integer, String> swp2;
    SchluesselWertPaar<Integer, String> swp3;
    SchluesselWertPaar<Integer, String> swp4;
    SchluesselWertPaar<Integer, String> swp5;

    @Before
    public void setUp() throws Exception {
        psMedian = new PivotStrategyMedian();
        psRandom = new PivotStrategyRandom();
        psLast = new PivotStrategyLast();
        qslMedian = new QuicksortLoesung<>(psMedian);
        swp1 = new SchluesselWertPaar<>(1, "Eins");
        swp2 = new SchluesselWertPaar<>(2, "Zwei");
        swp3 = new SchluesselWertPaar<>(3, "Drei");
        swp4 = new SchluesselWertPaar<>(4, "Vier");
        swp5 = new SchluesselWertPaar<>(5, "Fuenf");
        daten5 = new SchluesselWertPaar[5];
        daten5[0] = swp5;
        daten5[1] = swp4;
        daten5[2] = swp3;
        daten5[3] = swp2;
        daten5[4] = swp1;
        daten4 = new SchluesselWertPaar[4];
        daten4[0] = swp3;
        daten4[1] = swp1;
        daten4[2] = swp4;
        daten4[3] = swp2;
        daten1 = new SchluesselWertPaar[1];
        daten1[0] = swp1;
        daten2 = new SchluesselWertPaar[2];
        daten2[0] = swp1;
        daten2[1] = swp2;
    }

    @Test
    public void getPivotStrategy() throws Exception {
    }

    @Test
    public void sortiere() throws Exception {
        qslMedian.sortiere(daten5);
        assertEquals(swp1, daten5[0]);
        qslMedian.sortiere(daten1);
        assertEquals(swp1, daten1[0]);
        qslMedian.sortiere(daten2);
        assertEquals(swp1, daten2[0]);
        assertEquals(swp2, daten2[1]);

    }

    @Test
    public void quickSortTest() throws Exception {
        qslMedian.quickSort(daten5);
        assertEquals(swp1, daten5[0]);
        assertEquals(swp2, daten5[1]);
        assertEquals(swp3, daten5[2]);
        assertEquals(swp4, daten5[3]);
        assertEquals(swp5, daten5[4]);
    }
}