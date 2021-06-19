package cz.mg.collections;

import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.Text;

import java.util.function.Function;

import static cz.mg.collections.ToStringBuilder.nullAsEmpty;


public class ToTextBuilder<T> {
    private final ToStringBuilder<T> toStringBuilder;

    public ToTextBuilder(Clump<T> clump){
        this.toStringBuilder = new ToStringBuilder<>(clump);
    }

    public ToTextBuilder<T> prefix(String prefix){
        this.toStringBuilder.prefix(prefix);
        return this;
    }

    public ToTextBuilder<T> prefix(ReadableText prefix){
        this.toStringBuilder.prefix(nullAsEmpty(prefix));
        return this;
    }

    public ToTextBuilder<T> delim(String delim){
        this.toStringBuilder.delim(delim);
        return this;
    }

    public ToTextBuilder<T> delim(ReadableText delim){
        this.toStringBuilder.delim(nullAsEmpty(delim));
        return this;
    }

    public ToTextBuilder<T> postfix(String postfix){
        this.toStringBuilder.postfix(postfix);
        return this;
    }

    public ToTextBuilder<T> postfix(ReadableText postfix){
        this.toStringBuilder.postfix(nullAsEmpty(postfix));
        return this;
    }

    public <R> ToTextBuilder<T> convert(Function<T, R> converter){
        this.toStringBuilder.convert(converter);
        return this;
    }

    public Text build(){
        return new Text(toStringBuilder.build());
    }
}
