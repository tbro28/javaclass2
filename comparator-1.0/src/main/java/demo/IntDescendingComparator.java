package demo;
import java.util.Comparator;

public class IntDescendingComparator implements Comparator<Integer> {
    public int compare (final Integer i1, final Integer i2) {
        int diff = (i1 < i2) ? 1 : ((i1 > i2) ? -1 : 0);
        // int diff = Integer.compare(i1, i2);
        // int diff = i1.compareTo(i2);
        return diff;
    } 
}
