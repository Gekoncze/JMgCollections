package cz.mg.collections.text;

public class EditableText implements ReadableText, WriteableText {
    private final StringBuilder stringBuilder;

    public EditableText() {
        this.stringBuilder = new StringBuilder("");
    }

    public EditableText(String string) {
        this.stringBuilder = new StringBuilder(string);
    }

    public EditableText(ReadableText text) {
        this.stringBuilder = new StringBuilder(text.toString());
    }

    @Override
    public void set(int i, Character data) {
        stringBuilder.setCharAt(i, data);
    }

    @Override
    public void clear() {
        stringBuilder.delete(0, stringBuilder.length());
    }

    @Override
    public Character get(int i) {
        return stringBuilder.charAt(i);
    }

    @Override
    public int count() {
        return stringBuilder.length();
    }

    @Override
    public void append(char text) {
        stringBuilder.append(text);
    }

    @Override
    public void append(String text) {
        stringBuilder.append(text);
    }

    @Override
    public void append(ReadableText text) {
        stringBuilder.append(text.toString());
    }

    @Override
    public void prepend(char text) {
        stringBuilder.insert(0, text);
    }

    @Override
    public void prepend(String text) {
        stringBuilder.insert(0, text);
    }

    @Override
    public void prepend(ReadableText text) {
        stringBuilder.insert(0, text.toString());
    }

    @Override
    public void removeFirst(int count) {
        if(count >= count()){
            clear();
        } else {
            stringBuilder.delete(0, 0+count);
        }
    }

    @Override
    public void removeLast(int count) {
        if(count >= count()){
            clear();
        } else {
            stringBuilder.delete(count() - count, count());
        }
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o instanceof String) return toString().equals(o.toString());
        if(o instanceof ReadableText) return toString().equals(o.toString());
        return false;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
