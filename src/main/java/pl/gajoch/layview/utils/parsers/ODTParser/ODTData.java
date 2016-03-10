package pl.gajoch.layview.utils.parsers.ODTParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ODTData {
    public String title;
    public List<Column> dataColumns;

    public ODTData() {
        dataColumns = new ArrayList<>();
    }

    public ODTData(String title, List<Column> dataColumns) {
        this.title = title;
        this.dataColumns = dataColumns;
    }

    public void registerUnits(List<String> units) {
        while( dataColumns.size() < units.size() ) {
            dataColumns.add(new Column());
        }
        Iterator from = units.iterator(), to = dataColumns.iterator();
        while( from.hasNext() ) {
            String unit = (String)from.next();
            Column dest = (Column) to.next();
            dest.unit = unit;
        }
    }

    public void registerDescriptions(List<String> descriptions) {
        while( dataColumns.size() < descriptions.size() ) {
            dataColumns.add(new Column());
        }
        Iterator from = descriptions.iterator(), to = dataColumns.iterator();
        while( from.hasNext() ) {
            String description = (String)from.next();
            Column dest = (Column) to.next();
            dest.name = description;
        }
    }

    public void registerDataLine(List<Double> values) {
        if( dataColumns.size() != values.size() ) {
            System.out.println("Columns number in data not equal units number!");
            return;
        }
        Iterator from = values.iterator(), to = dataColumns.iterator();
        while( from.hasNext() ) {
            Double value = (Double) from.next();
            Column dest = (Column) to.next();
            dest.values.add(value);
        }
    }
}
