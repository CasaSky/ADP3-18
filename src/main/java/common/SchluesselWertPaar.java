package common;

/**
 * Repräsentation eines Schlüssel-Wert-Paares.
 */
public class SchluesselWertPaar<T extends Comparable<T>, U> {
    /**
     * Schlüssel.
     */
    private T schluessel;

    /**
     * Wert.
     */
    private U wert;

    public SchluesselWertPaar(T schluessel, U wert) {
        this.schluessel = schluessel;
        this.wert = wert;
    }

    public T getSchluessel() {
        return schluessel;
    }

    public U getWert() {
        return wert;
    }

    @Override
    public String toString() {
        return schluessel + "->" + wert;
    }
}
