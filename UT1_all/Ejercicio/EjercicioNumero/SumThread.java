class SumThread implements Runnable {
    private final int[] array;
    private final int startIndex;
    private final int endIndex;
    private int sum;

    public SumThread(int[] array, int startIndex, int endIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.sum = 0;
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            sum += array[i];
        }
    }

    public int getSum() {
        return sum;
    }
}
