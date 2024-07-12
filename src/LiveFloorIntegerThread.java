public class LiveFloorIntegerThread extends Thread{
    private int start;
    private int end;
    private int[] array;

    public LiveFloorIntegerThread(int start, int end, int[] array) {
        this.start = start;
        this.end = end;
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            array[i] = (int) Math.floor(array[i]);

        }
    }
}
