import java.util.Random;

public class Animal extends Thread {
    private String name;
    private FoodContainer foodContainer;
    private Random random = new Random();

    public Animal(String name, FoodContainer foodContainer) {
        this.name = name;
        this.foodContainer = foodContainer;
    }

    @Override
    public void run() {
        int moves = 5 + random.nextInt(6);
        for (int i = 0; i < moves; i++) {
            System.out.println(name + " is moving");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int sounds = 2 + random.nextInt(3);
        for (int i = 0; i < sounds; i++) {
            System.out.println(name + " is making sound");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // try to eat
        synchronized (foodContainer) {
            while (foodContainer.isEmpty()) {
                System.out.println(name + " says: Está vacío");
                try {
                    foodContainer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + " is eating");
            foodContainer.setEmpty();
        }
    }
}