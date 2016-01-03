import g4p_controls.*;

import java.util.ArrayList;

public class ListView<T> {
    GDropList dropList;
    ListModel<T> model;

    ListView(GDropList dropList, ListModel<T> model) {
        this.dropList = dropList;
        this.model = model;
    }

    void reEnumerate() {
        model.preEnumerate();
        ArrayList<String> list = new ArrayList<String>();
        if (model.isEmpty()) {
            list.add(" ");
        } else {
            for (T element : model) {
                list.add(model.labelFor(element) );
            }
        }
        dropList.setItems(list, 0);
        model.postEnumerate();
    }

    void add(T element) {
        model.add(element);
        reEnumerate();
    }
    void remove() {
        String selected = dropList.getSelectedText();
        for(T element : model) {
            String elementLabel = model.labelFor(element);
            if( elementLabel.equals(selected) ) {
                model.remove(element);
                break;
            }
        }
        reEnumerate();
    }

    void clear() {
        model.clear();
        reEnumerate();
    }
}