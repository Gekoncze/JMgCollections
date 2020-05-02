package cz.mg.collections.text;

public class ReadonlyTextTest {
    public static void main(String[] args) {
        testTrim();
    }

    public static void testTrim(){
        verify(
                new ReadonlyText("a"),
                new ReadonlyText[]{
                        new ReadonlyText("a"),
                        new ReadonlyText(" a"),
                        new ReadonlyText("  a"),
                        new ReadonlyText("a "),
                        new ReadonlyText("a  "),
                        new ReadonlyText(" a "),
                        new ReadonlyText("  a  "),
                }
        );

        verify(
                new ReadonlyText("abc"),
                new ReadonlyText[]{
                        new ReadonlyText("abc"),
                        new ReadonlyText(" abc"),
                        new ReadonlyText("  abc"),
                        new ReadonlyText("abc "),
                        new ReadonlyText("abc  "),
                        new ReadonlyText(" abc "),
                        new ReadonlyText("  abc  "),
                }
        );

        verify(
                new ReadonlyText("a b c"),
                new ReadonlyText[]{
                        new ReadonlyText("a b c"),
                        new ReadonlyText(" a b c"),
                        new ReadonlyText("  a b c"),
                        new ReadonlyText("a b c "),
                        new ReadonlyText("a b c  "),
                        new ReadonlyText(" a b c "),
                        new ReadonlyText("  a b c  "),
                }
        );


        verify(
                new ReadonlyText(""),
                new ReadonlyText[]{
                        new ReadonlyText(""),
                        new ReadonlyText(" "),
                        new ReadonlyText("  "),
                        new ReadonlyText(" "),
                        new ReadonlyText("  "),
                        new ReadonlyText("  "),
                        new ReadonlyText("    "),
                }
        );

        System.out.println("OK");
    }

    private static void verify(ReadonlyText expectation, ReadonlyText[] texts){
        for(ReadonlyText text : texts){
            if(!text.trim().equals(expectation)){
                throw new RuntimeException("Expected '" + expectation + "but got'" + text.trim() + "' from '" + text + "'.");
            }
        }
    }
}
