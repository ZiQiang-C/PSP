public class Farm {
    public static void main(String[] args) {
        FoodContainer foodContainer = new FoodContainer();
        Animal[] animals = {
            new Animal("Pig", foodContainer),
            new Animal("Cow", foodContainer),
            new Animal("Sheep", foodContainer)
        };
        Thread caretaker = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                foodContainer.fill();
                System.out.println("Caretaker filled the food container");
            }
        });
        caretaker.setDaemon(true);
        caretaker.start();
        for (Animal animal : animals) {
            animal.start();
        }
        for (Animal animal : animals) {
            try {
                animal.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
