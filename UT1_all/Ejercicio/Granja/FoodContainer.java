public class FoodContainer {
    private boolean empty = true;

    public synchronized boolean isEmpty() {
        return empty;
    }

    public synchronized void setEmpty() {
        empty = true;
    }

    public synchronized void fill() {
        empty = false;
        notifyAll();
    }
}