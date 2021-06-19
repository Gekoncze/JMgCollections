package cz.mg.collections;

import java.util.function.Function;


public class ToStringBuilder<T> {
    private static final Function DEFAULT_CONVERTER = object -> object;

    private final Clump<T> clump;
    private String prefix = "";
    private String delim = "";
    private String postfix = "";
    private Function converter = DEFAULT_CONVERTER;

    public ToStringBuilder(Clump<T> clump){
        this.clump = clump;
    }

    public ToStringBuilder<T> prefix(String prefix){
        this.prefix = nullAsEmpty(prefix);
        return this;
    }

    public ToStringBuilder<T> delim(String delim){
        this.delim = nullAsEmpty(delim);
        return this;
    }

    public ToStringBuilder<T> postfix(String postfix){
        this.postfix = nullAsEmpty(postfix);
        return this;
    }

    public <R> ToStringBuilder<T> convert(Function<T, R> converter){
        this.converter = converter;
        return this;
    }

    public String build(){
        StringBuilder text = new StringBuilder();
        text.append(prefix);

        boolean first = true;
        for (T object : clump) {
            if (!first) text.append(delim);
            text.append(nullAsEmpty(converter.apply(object)));
            first = false;
        }

        text.append(postfix);
        return text.toString();
    }

    protected static String nullAsEmpty(Object object){
        return object == null ? "" : object.toString();
    }
}
