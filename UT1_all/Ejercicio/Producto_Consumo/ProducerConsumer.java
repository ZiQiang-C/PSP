import java.util.Queue;
import java.util.LinkedList;

public class ProducerConsumer {
    private static final int MAX_SIZE = 1; // 队列的最大长度
    private static Queue<Integer> queue = new LinkedList<Integer>(); // 存储随机数的队列

    public static void main(String[] args) {
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());
        producerThread.start(); // 启动生产者线程
        consumerThread.start(); // 启动消费者线程
    }

    static class Producer implements Runnable {
        public void run() {
            while (true) {
                //synchronized 可以用来保证共享资源在同一时刻只能被一个线程访问，
                    //从而避免多个线程同时访问共享资源而导致数据不一致或者其他并发问题。
                synchronized (queue) {
                    while (queue.size() == MAX_SIZE) {
                        try {
                            queue.wait(); // 队列已满，等待消费者取出数据
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int number = (int) (Math.random() * 100);
                    queue.add(number);
                    System.out.println("Produced: " + number);
                    queue.notifyAll(); // 通知消费者可以取出数据了
                }
            }
        }
    }

    static class Consumer implements Runnable {
        public void run() {
            while (true) {
                synchronized (queue) { 
                    //synchronized 可以用来保证共享资源在同一时刻只能被一个线程访问，
                    //从而避免多个线程同时访问共享资源而导致数据不一致或者其他并发问题。
                    while (queue.isEmpty()) {
                        try {
                            queue.wait(); // 队列为空，等待生产者生成数据
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int number = queue.poll();
                    System.out.println("Consumed: " + number);
                    queue.notifyAll(); // 通知生产者可以继续生成数据了
                }
            }
        }
    }
}
