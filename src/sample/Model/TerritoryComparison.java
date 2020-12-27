package sample.Model;

import java.util.Comparator;

public class TerritoryComparison implements Comparator<territories> {
    @Override
    public int compare(territories o1, territories o2) {

        return Integer.compare(o1.getNoOfSoldiers(),o2.getNoOfSoldiers());
    }
}
