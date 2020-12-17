package cz.mg.collections;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.collections.text.Text;


public interface Clump<T> extends Iterable<T> {
    public default boolean contains(T wanted){
        for(T data : this){
            if(data == wanted) return true;
        }
        return false;
    }

    public default int indexOf(T wanted){
        int i = 0;
        for(T data : this){
            if(data == wanted) return i;
            i++;
        }
        return -1;
    }

    public default ReadableText toText(ReadableText delim) {
        return toText("", delim.toString(), "", DEFAULT_CONVERTER);
    }

    public default ReadableText toText(String delim) {
        return toText("", delim.toString(), "", DEFAULT_CONVERTER);
    }

    public default ReadableText toText(ReadableText delim, Converter<T> converter) {
        return toText("", delim.toString(), "", converter);
    }

    public default ReadableText toText(String delim, Converter<T> converter) {
        return toText("", delim.toString(), "", converter);
    }

    public default ReadableText toText(ReadableText prefix, ReadableText delim, ReadableText postfix) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), DEFAULT_CONVERTER);
    }

    public default ReadableText toText(String prefix, ReadableText delim, ReadableText postfix) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), DEFAULT_CONVERTER);
    }

    public default ReadableText toText(ReadableText prefix, String delim, ReadableText postfix) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), DEFAULT_CONVERTER);
    }

    public default ReadableText toText(ReadableText prefix, ReadableText delim, String postfix) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), DEFAULT_CONVERTER);
    }

    public default ReadableText toText(ReadableText prefix, String delim, String postfix) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), DEFAULT_CONVERTER);
    }

    public default ReadableText toText(String prefix, ReadableText delim, String postfix) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), DEFAULT_CONVERTER);
    }

    public default ReadableText toText(String prefix, String delim, ReadableText postfix) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), DEFAULT_CONVERTER);
    }

    public default ReadableText toText(String prefix, String delim, String postfix) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), DEFAULT_CONVERTER);
    }

    public default ReadableText toText(ReadableText prefix, ReadableText delim, ReadableText postfix, Converter<T> converter) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), converter);
    }

    public default ReadableText toText(String prefix, ReadableText delim, ReadableText postfix, Converter<T> converter) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), converter);
    }

    public default ReadableText toText(ReadableText prefix, String delim, ReadableText postfix, Converter<T> converter) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), converter);
    }

    public default ReadableText toText(ReadableText prefix, ReadableText delim, String postfix, Converter<T> converter) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), converter);
    }

    public default ReadableText toText(ReadableText prefix, String delim, String postfix, Converter<T> converter) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), converter);
    }

    public default ReadableText toText(String prefix, ReadableText delim, String postfix, Converter<T> converter) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), converter);
    }

    public default ReadableText toText(String prefix, String delim, ReadableText postfix, Converter<T> converter) {
        return toText(prefix.toString(), delim.toString(), postfix.toString(), converter);
    }

    public default ReadableText toText(String prefix, String delim, String postfix, Converter<T> converter) {
        Text text = new Text();
        text.append(prefix);

        boolean first = true;
        for (T t : this) {
            if (!first) text.append(delim);
            text.append(converter.convert(t).toString());
            first = false;
        }

        text.append(postfix);
        return text;
    }

    @FunctionalInterface
    public interface Converter<T> {
        public ReadableText convert(T t);
    }

    public static Converter DEFAULT_CONVERTER = new Converter() {
        @Override
        public ReadableText convert(Object t) {
            return new ReadonlyText("" + t);
        }
    };

    @Override
    public Pass<T> iterator();

    public static int count(Clump clump){
        int count = 0;
        for(Object o : clump) count++;
        return count;
    }

    public static boolean contains(Clump clump, Object wanted){
        for(Object object : clump){
            if(object == wanted) return true;
        }
        return false;
    }
}
