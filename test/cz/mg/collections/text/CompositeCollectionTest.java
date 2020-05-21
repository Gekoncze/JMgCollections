package cz.mg.collections.text;

import cz.mg.collections.list.List;
import cz.mg.collections.special.CompositeCollection;


public class CompositeCollectionTest {
    public static void main(String[] args) {
        test(new CompositeCollection<>());

        test(new CompositeCollection<>(
                null,
                null,
                null
        ));

        test(new CompositeCollection<>(
                new List<>(1, 2, 3),
                new List<>(4),
                new List<>(5, 6)
        ));

        test(new CompositeCollection<>(
                null,
                new List<>(1, 2, 3, 4),
                new List<>(5, 6)
        ));

        test(new CompositeCollection<>(
                new List<>(1, 2, 3, 4),
                new List<>(5, 6),
                null
        ));

        test(new CompositeCollection<>(
                new List<>(1, 2, 3),
                null,
                new List<>(4, 5, 6)
        ));

        test(new CompositeCollection<>(
                new List<>(1),
                new List<>(2),
                new List<>(3),
                new List<>(4),
                new List<>(5),
                new List<>(6)
        ));
    }

    private static int id = 0;
    private static void test(CompositeCollection<Integer> collection) {
        System.out.println("#### TEST " + (id++) + " ###");

        for(Integer i : collection){
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.println();
    }
}
