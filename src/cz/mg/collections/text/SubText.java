package cz.mg.collections.text;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;


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

    public SubText slice(Integer begin, Integer end){
        return new SubText(this, begin, end);
    }

    public SubText trim(){
        Integer begin = null;
        Integer end = null;
        boolean stop = false;
        for(int i = 0; i < count(); i++){
            if(!Character.isWhitespace(get(i))){
                begin = i;
                for(i = i + 1; i < count(); i++){
                    if(Character.isWhitespace(get(i))){
                        end = i;
                        stop = true;
                    }
                    if(stop) break;
                }
            }
            if(stop) break;
        }
        if(begin == null) return slice(0, 0);
        return slice(begin, end);
    }

    public Array<SubText> splitByEach(Character delim){
        if(delim == null) return new Array<SubText>(this);
        return splitByEach("" + delim);
    }

    public Array<SubText> splitByEach(String delims){
        if(delims == null) return new Array<SubText>(this);
        List<SubText> parts = new List<>();
        int begin = 0;
        int end = 0;
        for(int i = 0; i < count(); i++){
            char ch = get(i);
            for(int j = 0; j < delims.length(); j++){
                char d = delims.charAt(j);
                if(ch == d) {
                    end = i;
                    parts.addLast(slice(begin, end));
                    begin = end + 1;
                }
            }
        }
        if(begin <= count()) parts.addLast(slice(begin, null));
        return new Array<>(parts);
    }

    public Array<SubText> splitByEach(SubText delims){
        if(delims == null) return new Array<SubText>(this);
        return splitByEach(delims.toString());
    }

    public Array<SubText> splitByEachNoBlank(Character delim){
        if(delim == null) return new Array<SubText>(this);
        return splitByEachNoBlank("" + delim);
    }

    public Array<SubText> splitByEachNoBlank(String delims){
        if(delims == null) return new Array<SubText>(this);
        Array<SubText> parts = splitByEach(delims);
        List<SubText> partsNoBlank = new List<>();
        for(SubText part : parts) if(part != null) if(part.count() > 0) partsNoBlank.addLast(part);
        return new Array<>(partsNoBlank);
    }

    public Array<SubText> splitByEachNoBlank(SubText delims){
        if(delims == null) return new Array<SubText>(this);
        return splitByEachNoBlank(delims.toString());
    }

    public Array<SubText> splitByWhole(Character delim){
        return splitByWhole("" + delim);
    }

    public Array<SubText> splitByWhole(String delim){
        Integer index;
        SubText before = null;
        SubText after = this;
        List<SubText> parts = new List<>();
        while((index = after.findFirst(delim)) != null){
            before = after.slice(null, index);
            after = after.slice(index + delim.length(), null);
            parts.addLast(before);
        }
        parts.addLast(after);
        return new Array<>(parts);
    }

    public Array<SubText> splitByWhole(SubText delim){
        return splitByWhole(delim.toString());
    }

    public Array<SubText> splitByWholeNoBlank(Character delim){
        return splitByWholeNoBlank(delim + "");
    }

    public Array<SubText> splitByWholeNoBlank(String delim){
        Array<SubText> withBlank = splitByWhole(delim);
        List<SubText> withouthBlank = new List<>();
        for(SubText t : withBlank) if(t != null) if(t.count() > 0) withouthBlank.addLast(t);
        return new Array<>(withouthBlank);
    }

    public Array<SubText> splitByWholeNoBlank(SubText delim){
        return splitByWholeNoBlank(delim.toString());
    }

    public Array<SubText> splitByCamelCase(){
        throw new UnsupportedOperationException("Coming soon...");
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
