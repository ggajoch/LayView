public class FrameText {
    public final String text;
    public final FontDescriptor font;
    public final color col;
    public final int frame;

    public FrameText(String text, FontDescriptor font, color col, int frame) {
        this.text = text;
        this.font = font;
        this.col = col;
        this.frame = frame;
    }
}