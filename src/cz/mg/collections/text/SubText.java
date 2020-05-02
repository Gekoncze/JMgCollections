package cz.mg.collections.text;

public class SubText implements ReadableText {
    protected final String string;
    protected final int begin;
    protected final int end;

    public SubText() {
        string = "";
        begin = 0;
        end = 0;
    }

    public SubText(char ch) {
        this.string = "" + ch;
        this.begin = 0;
        this.end = 1;
    }

    public SubText(String string) {
        this.string = string;
        this.begin = 0;
        this.end = string.length();
    }

    public SubText(SubText text) {
        this.string = text.string;
        this.begin = text.begin;
        this.end = text.end;
    }

    public SubText(String s, Integer begin, Integer end) {
        if(begin == null) begin = 0;
        if(end == null) end = s.length();
        if(begin < 0) begin = 0;
        if(end < 0) end = 0;
        if(begin > s.length()) begin = s.length();
        if(end > s.length()) end = s.length();
        if(end < begin) end = begin;

        this.begin = begin;
        this.end = end;
        this.string = s;
    }

    public SubText(SubText text, Integer begin, Integer end) {
        if(begin == null) begin = 0;
        if(end == null) end = text.count();
        if(begin < 0) begin = 0;
        if(end < 0) end = 0;
        if(begin > text.count()) begin = text.count();
        if(end > text.count()) end = text.count();
        if(end < begin) end = begin;

        this.begin = begin + text.begin;
        this.end = end + text.begin;
        this.string = text.string;
    }

    public String getReferencedString(){
        return string;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public int count() {
        return end - begin;
    }

    @Override
    public Character get(int i) {
        if(i < 0 || i >= count()) return null;
        return string.charAt(begin + i);
    }

    @Override
    public SubText slice(Integer begin, Integer end){
        return new SubText(this, begin, end);
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
        if(end <= begin) return "";
        return string.substring(begin, end);
    }
}
