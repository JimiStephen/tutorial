import java.util.ListResourceBundle;

public class StatsBundle_fr_FR extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
        { "GDP", new Integer(2300) },
        { "Population", new Integer(125403) },
        { "Literacy", new Double(5.99) },
    };
}