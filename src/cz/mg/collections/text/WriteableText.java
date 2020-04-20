package cz.mg.collections.text;

import cz.mg.collections.array.WriteableArray;


public interface WriteableText extends WriteableArray<Character> {
    public void append(char text);
    public void append(String text);
    public void append(ReadableText text);
    public void prepend(char text);
    public void prepend(String text);
    public void prepend(ReadableText text);
    public void removeFirst(int count);
    public void removeLast(int count);
}
