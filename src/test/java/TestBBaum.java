import bbaum.BBaum;
import bbaum.BBaumLoesung;
import common.SchluesselWertPaar;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testklasse für die BBaum-Implementierung.
 */
public class TestBBaum {

    /**
     * Mit dieser Instanz wird getestet.
     */
    // TODO: Legen Sie hier eine Instanz Ihrer Implementierung an.
    private BBaum<Integer, String> baum = //null;
            new BBaumLoesung<>(2);

    @Test
    public void testEinfuegen() {
        baum.leeren();
        baum.einfuegen(23, "23");

        System.out.println(baum);
        baum.einfuegen(42, "42");
        System.out.println(baum);
        baum.einfuegen(12, "12");
        System.out.println(baum);
        baum.einfuegen(25, "25");
        System.out.println(baum);
        baum.einfuegen(20, "20");
        System.out.println(baum);
        baum.einfuegen(11, "11");
        System.out.println(baum);
        baum.einfuegen(24, "24");
        System.out.println(baum);
        baum.einfuegen(43, "43");
        System.out.println(baum);
        assertTrue(baum.validieren());
        assertEquals(7, baum.getAnzahlSchluessel());
        List<SchluesselWertPaar<Integer, String>> preOrderElemente = baum.getPreOrder();
        assertEquals(7, preOrderElemente.size());
        for (int i = 0; i < preOrderElemente.size() - 1; i++) {
            assertTrue(preOrderElemente.get(i).getSchluessel().
                    compareTo(preOrderElemente.get(i + 1).getSchluessel()) <= 0);
        }
    }

    @Test
    /**
     * Testet das Finden von Elementen im Baum.
     */
    public void testFinden() {
        baum.leeren();
        baum.einfuegen(23, "23");
        baum.einfuegen(42, "42");
        baum.einfuegen(12, "12");
        baum.einfuegen(25, "25");
        baum.einfuegen(20, "20");
        baum.einfuegen(11, "11");
        baum.einfuegen(24, "24");

        assertEquals("23", baum.findeWertZuSchluessel(23));
        assertEquals("25", baum.findeWertZuSchluessel(25));
        assertEquals("42", baum.findeWertZuSchluessel(42));
        assertEquals(null, baum.findeWertZuSchluessel(1));
        assertEquals(null, baum.findeWertZuSchluessel(100));
        assertEquals(null, baum.findeWertZuSchluessel(26));
    }
}
