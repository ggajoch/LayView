public class Pair<F, S> {
    Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }
    public volatile F first;
    public volatile S second;
}