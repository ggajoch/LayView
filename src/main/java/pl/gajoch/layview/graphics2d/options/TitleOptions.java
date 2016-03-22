package pl.gajoch.layview.graphics2d.options;

import java.awt.*;

public class TitleOptions {
    public RichString title;

    public TitleOptions() {
        title = new RichString();
    }

    public TitleOptions(TitleOptions second) {
        this.title = new RichString(second.title);
    }
}
