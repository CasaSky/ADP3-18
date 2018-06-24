package quicksort;

import common.SchluesselWertPaar;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by michelbrueger on 23.06.18.
 */
public class PivotStrategieTest {
    PivotStrategie psMedian;
    PivotStrategie psRandom;
    PivotStrategie psLast;
    SchluesselWertPaar<Integer, String>[] daten5;
    SchluesselWertPaar<Integer, String>[] daten4;
    SchluesselWertPaar<Integer, String>[] daten1;
    SchluesselWertPaar<Integer, String>[] daten0;


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
        swp1 = new SchluesselWertPaar<>(1, "Eins");
        swp2 = new SchluesselWertPaar<>(2, "Zwei");
        swp3 = new SchluesselWertPaar<>(3, "Drei");
        swp4 = new SchluesselWertPaar<>(4, "Vier");
        swp5 = new SchluesselWertPaar<>(5, "Fuenf");
        daten5 = new SchluesselWertPaar[5];
        daten5[0] = swp1;
        daten5[1] = swp2;
        daten5[2] = swp3;
        daten5[3] = swp4;
        daten5[4] = swp5;
        daten4 = new SchluesselWertPaar[4];
        daten4[0] = swp3;
        daten4[1] = swp1;
        daten4[2] = swp4;
        daten4[3] = swp2;
        daten1 = new SchluesselWertPaar[1];
        daten1[0] = swp1;
        daten0 = new SchluesselWertPaar[0];
    }

    @Test
    public void getIndex() throws Exception {
        /*
        Tests für PivotStrategyMedian
         */
        assertEquals(2, psMedian.getIndex(daten5, 0, daten5.length-1));
        daten5[0] = swp1;
        daten5[1] = swp2;
        daten5[2] = swp5;
        daten5[3] = swp4;
        daten5[4] = swp3;
        assertEquals(4, psMedian.getIndex(daten5, 0, daten5.length-1));
        assertEquals(3, psMedian.getIndex(daten4, 0, daten4.length-1));
        assertEquals(0, psMedian.getIndex(daten1, 0, daten1.length-1));
        assertEquals(0, psMedian.getIndex(daten1, 0, 0));


        /*
        Tests für PivotStrategyRandom
         */
        int randomIndex = psRandom.getIndex(daten5, 0, daten5.length-1);
        assertTrue(0 <= randomIndex && randomIndex <= 4);
        System.out.println("0-4 von daten5 = " + randomIndex);
        randomIndex = psRandom.getIndex(daten5, 4, daten5.length-1);
        System.out.println("4-4 von daten5 = " + randomIndex);
        randomIndex = psRandom.getIndex(daten5, 0, 1);
        System.out.println("0-1 von daten5 = " + randomIndex);

        /*
        Tests für PivotStrategyLast
         */
        assertEquals(4, psLast.getIndex(daten5, 0, daten5.length-1));
        assertEquals(3, psLast.getIndex(daten5, 0, daten5.length-2));
        assertEquals(2, psLast.getIndex(daten5, 0, daten5.length-3));
        assertEquals(1, psLast.getIndex(daten5, 0, daten5.length-4));
        assertEquals(0, psLast.getIndex(daten5, 0, daten5.length-5));
    }

}