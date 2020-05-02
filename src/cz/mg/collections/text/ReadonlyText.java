package cz.mg.collections.text;

import cz.mg.collections.array.Array;
import cz.mg.collections.list.List;


public class ReadonlyText implements ReadableText {
    protected final String string;

    public ReadonlyText(){
        this("");
    }

    public ReadonlyText(char ch){
        this("" + ch);
    }

    public ReadonlyText(String string) {
        this.string = string == null ? "" : string;
    }

    public ReadonlyText(ReadableText text){
        this(text.toString());
    }

    @Override
    public Character get(int i){
        if(i < 0 || i >= count()) return null;
        return string.charAt(i);
    }

    @Override
    public int count() {
        return string.length();
    }

    public ReadonlyText append(char s){
        return new ReadonlyText(toString() + s);
    }

    public ReadonlyText append(String s){
        return new ReadonlyText(toString() + s);
    }

    public ReadonlyText append(ReadonlyText s){
        return new ReadonlyText(toString() + s.toString());
    }

    public ReadonlyText replaceFirst(String what, String with){
        Integer index = findFirst(what);
        if(index == null) return this;
        ReadonlyText before = slice(null, index);
        ReadonlyText after = slice(index + what.length(), null);
        return before.append(with).append(after);
    }

    public ReadonlyText replaceFirst(ReadonlyText what, String with){
        return replaceFirst(what.toString(), with);
    }

    public ReadonlyText replaceFirst(String what, ReadonlyText with){
        return replaceFirst(what, with.toString());
    }

    public ReadonlyText replaceFirst(ReadonlyText what, ReadonlyText with){
        return replaceFirst(what.toString(), with.toString());
    }

    public ReadonlyText replaceLast(String what, String with){
        Integer index = findLast(what);
        if(index == null) return this;
        ReadonlyText before = slice(null, index);
        ReadonlyText after = slice(index + what.length(), null);
        return before.append(with).append(after);
    }

    public ReadonlyText replaceLast(ReadonlyText what, String with){
        return replaceLast(what.toString(), with);
    }

    public ReadonlyText replaceLast(String what, ReadonlyText with){
        return replaceLast(what, with.toString());
    }

    public ReadonlyText replaceLast(ReadonlyText what, ReadonlyText with){
        return replaceLast(what.toString(), with.toString());
    }

    public ReadonlyText replaceBegin(String what, String with){
        if(!startsWith(what)) return this;
        return replaceFirst(what, with);
    }

    public ReadonlyText replaceBegin(ReadonlyText what, String with){
        return replaceBegin(what.toString(), with);
    }

    public ReadonlyText replaceBegin(String what, ReadonlyText with){
        return replaceBegin(what, with.toString());
    }

    public ReadonlyText replaceBegin(ReadonlyText what, ReadonlyText with){
        return replaceBegin(what.toString(), with.toString());
    }

    public ReadonlyText replaceEnd(String what, String with){
        if(!endsWith(what)) return this;
        return replaceLast(what, with);
    }

    public ReadonlyText replaceEnd(ReadonlyText what, String with){
        return replaceEnd(what.toString(), with);
    }

    public ReadonlyText replaceEnd(String what, ReadonlyText with){
        return replaceEnd(what, with.toString());
    }

    public ReadonlyText replaceEnd(ReadonlyText what, ReadonlyText with){
        return replaceEnd(what.toString(), with.toString());
    }

    public ReadonlyText replace(String what, String with){
        return new ReadonlyText(toString().replace(what, with));
    }

    public ReadonlyText replace(ReadonlyText what, String with){
        return new ReadonlyText(toString().replace(what.toString(), with));
    }

    public ReadonlyText replace(String what, ReadonlyText with){
        return new ReadonlyText(toString().replace(what, with.toString()));
    }

    public ReadonlyText replace(ReadonlyText what, ReadonlyText with){
        return new ReadonlyText(toString().replace(what.toString(), with.toString()));
    }

    public ReadonlyText lowerCase(){
        return new ReadonlyText(toString().toLowerCase());
    }

    public ReadonlyText upperCase(){
        return new ReadonlyText(toString().toUpperCase());
    }

    public ReadonlyText lowerFirst(){
        return slice(null, 1).lowerCase().append(slice(1, null));
    }

    public ReadonlyText upperFirst(){
        return slice(null, 1).upperCase().append(slice(1, null));
    }

    public ReadonlyText upperToCamel(){
        String[] parts = toString().split("_");
        List<String> r = new List<>();
        for(String part : parts) r.addLast(new ReadonlyText(part.toLowerCase()).upperFirst().toString());
        return new ReadonlyText(r.toText(""));
    }

    public ReadonlyText camelToUpper(){
        return new ReadonlyText(new Array<>(splitByCammelCase()).toText("_")).upperCase();
    }

    @Override
    public ReadonlyText slice(Integer begin, Integer end) {
        if(begin == null) begin = 0;
        if(end == null) end = count();
        if(begin < 0) begin = 0;
        if(end < 0) end = 0;
        if(begin > count()) begin = count();
        if(end > count()) end = count();
        if(end - begin <= 0) return new ReadonlyText();
        return new ReadonlyText(string.substring(begin, end));
    }



    public ReadonlyText repeat(int count){
        StringBuilder result = new StringBuilder();
        String s = toString();
        for(int i = 0; i < count; i++) result.append(s);
        return new ReadonlyText(result.toString());
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
        return string;
    }
}
