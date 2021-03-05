package cz.mg.collections;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.Text;


public class ToTextBuilder<T> {
    private static final Converter DEFAULT_CONVERTER = object -> object == null ? "" : object.toString();

    private final Clump<T> clump;
    private String prefix = "";
    private String delim = "";
    private String postfix = "";
    private Converter converter = DEFAULT_CONVERTER;

    public ToTextBuilder(Clump<T> clump){
        this.clump = clump;
    }

    public ToTextBuilder<T> prefix(String prefix){
        this.prefix = nullAsEmpty(prefix);
        return this;
    }

    public ToTextBuilder<T> prefix(ReadableText prefix){
        this.prefix = nullAsEmpty(prefix);
        return this;
    }

    public ToTextBuilder<T> delim(String delim){
        this.delim = nullAsEmpty(delim);
        return this;
    }

    public ToTextBuilder<T> delim(ReadableText delim){
        this.delim = nullAsEmpty(delim);
        return this;
    }

    public ToTextBuilder<T> postfix(String postfix){
        this.postfix = nullAsEmpty(postfix);
        return this;
    }

    public ToTextBuilder<T> postfix(ReadableText postfix){
        this.postfix = nullAsEmpty(postfix);
        return this;
    }

    public ToTextBuilder<T> convert(Converter<T> converter){
        this.converter = converter;
        return this;
    }

    public Text build(){
        Text text = new Text();
        text.append(prefix);

        boolean first = true;
        for (T object : clump) {
            if (!first) text.append(delim);
            text.append(nullAsEmpty(converter.convert(object)));
            first = false;
        }

        text.append(postfix);
        return text;
    }

    @FunctionalInterface
    public interface Converter<T> {
        public Object convert(T object);
    }

    private static String nullAsEmpty(Object text){
        if(text instanceof String){
            return nullAsEmpty((String) text);
        }

        if(text instanceof ReadableText){
            return nullAsEmpty((ReadableText) text);
        }

        throw new IllegalArgumentException();
    }

    private static String nullAsEmpty(String text){
        return text == null ? "" : text;
    }

    private static String nullAsEmpty(ReadableText text){
        return text == null ? "" : text.toString();
    }
}
