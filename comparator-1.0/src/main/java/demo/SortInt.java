package demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class SortInt {
    public static final void main(final String[] args) {
        List<Integer> lst = new ArrayList<Integer>();
        lst.add(23);
        lst.add(13);
        lst.add(47);
        lst.add(7);

        Collections.sort(lst);
        System.out.println("Integers in ascending order -  sorted using Comparable (natural-ordering implemented by Integer)");
        for (int i : lst) {
            System.out.println(i);
        }

        System.out.println();
        System.out.println("Integers in descending order -  sorted using IntDescendingComparator");
        Collections.sort(lst, new IntDescendingComparator());
        for (int i : lst) {
            System.out.println(i);
        }

        System.out.println();
        System.out.println();
        System.out.println("Integers in descending order -  sorted using Comparator.comparingInt, reversed");
        Collections.sort(lst, Comparator.comparingInt(Integer::intValue)
        		                         .reversed());
        for (int i : lst) {
            System.out.println(i);
        }
    }
}
