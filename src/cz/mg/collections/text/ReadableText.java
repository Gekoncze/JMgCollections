package cz.mg.collections.text;

import cz.mg.collections.array.ReadableArray;


public interface ReadableText extends ReadableArray<Character> {
    public default boolean containsAt(int i, Character ch){
        return get(i) == ch;
    }

    public default boolean containsAt(int i, String s){
        for(int ii = 0; ii < s.length(); ii++){
            if(!containsAt(i + ii, s.charAt(ii))){
                return false;
            }
        }
        return true;
    }

    public default boolean containsAt(int i, ReadableText t){
        for(int ii = 0; ii < t.count(); ii++){
            if(!containsAt(i + ii, t.get(ii))){
                return false;
            }
        }
        return true;
    }

    public default boolean startsWith(Character ch){
        return containsAt(0, ch);
    }

    public default boolean startsWith(String s){
        return containsAt(0, s);
    }

    public default boolean startsWith(ReadableText s){
        return containsAt(0, s);
    }

    public default boolean endsWith(Character ch){
        int i = count() - 1;
        return containsAt(i, ch);
    }

    public default boolean endsWith(String s){
        int i = count() - s.length();
        return containsAt(i, s);
    }

    public default boolean endsWith(ReadableText t){
        int i = count() - t.count();
        return containsAt(i, t);
    }

    @Override
    public default boolean contains(Character ch){
        return findFirst(ch) != null;
    }

    public default boolean contains(String s){
        return findFirst(s) != null;
    }

    public default boolean contains(ReadableText t){
        return findFirst(t) != null;
    }

    public default int count(Character ch){
        int count = 0;
        for(int i = 0; i < count(); i++) if(get(i) == ch) count++;
        return count;
    }

    public default Integer find(Character ch, int start){
        if(start < 0) start = 0;
        for(int i = start; i < count(); i++) if(get(i) == ch) return i;
        return null;
    }

    public default Integer find(String s, int start){
        if(start < 0) start = 0;
        if(s.length() <= 0) return 0;
        Character ch = s.charAt(0);
        for(int i = start; i < count(); i++) if(get(i) == ch) if(containsAt(i, s)) return i;
        return null;
    }

    public default Integer find(ReadableText t, int start){
        if(start < 0) start = 0;
        if(t.count() <= 0) return 0;
        Character ch = t.get(0);
        for(int i = start; i < count(); i++) if(get(i) == ch) if(containsAt(i, t)) return i;
        return null;
    }

    public default Integer findFirst(Character ch){
        return find(ch, 0);
    }

    public default Integer findFirst(String s){
        return find(s, 0);
    }

    public default Integer findFirst(ReadableText t){
        return find(t, 0);
    }

    public default Integer findLast(Character ch){
        for(int i = count() - 1; i >= 0; i--) if(get(i) == ch) return i;
        return null;
    }

    public default Integer findLast(String s){
        if(s.length() <= 0) return count() - 1;
        Character ch = s.charAt(0);
        for(int i = count() - 1; i >= 0; i--) if(get(i) == ch) if(containsAt(i, s)) return i;
        return null;
    }

    public default Integer findLast(ReadableText t){
        if(t.count() <= 0) return count() - 1;
        Character ch = t.get(0);
        for(int i = count() - 1; i >= 0; i--) if(get(i) == ch) if(containsAt(i, t)) return i;
        return null;
    }

    public default byte parseByte(){
        return Byte.parseByte(toString());
    }

    public default short parseShort(){
        return Short.parseShort(toString());
    }

    public default int parseInt(){
        return Integer.parseInt(toString());
    }

    public default long parseLong(){
        return Long.parseLong(toString());
    }

    public default float parseFloat(){
        return Float.parseFloat(toString());
    }

    public default double parseDouble(){
        return Double.parseDouble(toString());
    }

// Java does not support overriding object methods in interface
// This code snippet serves as a template for implementing classes
//
//    @Override
//    public default boolean equals(Object o) {
//        if(o == null) return false;
//        if(o instanceof String) return toString().equals(o.toString());
//        if(o instanceof ReadableText) return toString().equals(o.toString());
//        return false;
//    }
//
//    @Override
//    public default int hashCode() {
//        return toString().hashCode();
//    }
}
