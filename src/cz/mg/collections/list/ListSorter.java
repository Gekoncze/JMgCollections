package cz.mg.collections.list;

import cz.mg.collections.array.Array;

import java.util.Arrays;
import java.util.Comparator;


public class ListSorter {
    public static <T> List<T> sort(ReadableList<T> list){
        Array<T> array = new Array<>(list);
        Arrays.sort(array.getJavaArray());
        return new List<>(array);
    }

    public static <T> List<T> sort(ReadableList<T> list, Comparator<T> comparator){
        Array<T> array = new Array<>(list);
        Arrays.sort((T[])array.getJavaArray(), comparator);
        return new List<>(array);
    }

    public static <T> void sortInPlace(List<T> list){
        Array<T> array = new Array<>(list);
        Arrays.sort(array.getJavaArray());
        list.clear();
        list.addCollectionLast(array);
    }

    public static <T> void sortInPlace(List<T> list, Comparator<T> comparator){
        Array<T> array = new Array<>(list);
        Arrays.sort((T[])array.getJavaArray(), comparator);
        list.clear();
        list.addCollectionLast(array);
    }
}
