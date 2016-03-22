package pl.gajoch.layview.utils.parsers.ODTParser;

public enum ConfigRowTypes {
    TITLE("Title"),
    TABLESTART("Table Start"),
    TABLEEND("Table End"),
    VERSION("ODT"),
    COLUMNS("Columns"),
    UNIT("Units");

    public final String name;

    ConfigRowTypes(String name) {
        this.name = name;
    }

    Boolean checkType(String line) {
        String l = line.substring(2);
        if (l.subSequence(0, this.name.length()).equals(this.name)) {
            return true;
        }
        return false;
    }
}
