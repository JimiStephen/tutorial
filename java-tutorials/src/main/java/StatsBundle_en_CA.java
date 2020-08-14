import java.util.ListResourceBundle;

public class StatsBundle_en_CA extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
        { "GDP", new Integer(121300) },
        { "Population", new Integer(125449703) },
        { "Literacy", new Double(1.99) },
    };
}