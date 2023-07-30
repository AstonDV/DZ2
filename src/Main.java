import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ToyStore toyStore = new ToyStore();

        toyStore.addToy(new Toy(1, "Teddy Bear", 10, 40));
        toyStore.addToy(new Toy(2, "Doll", 15, 30));
        toyStore.addToy(new Toy(3, "Car", 20, 30));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add new toy");
            System.out.println("2. Show all available toys");
            System.out.println("3. Change toy frequency");
            System.out.println("4. Play toy selection game");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();

            if (option == 1) {
                System.out.print("Enter tou id: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter toy name: ");
                String name = scanner.nextLine();

                System.out.print("Enter toy quantity: ");
                int quantity = scanner.nextInt();

                System.out.print("Enter toy frequency: ");
                float frequency = scanner.nextFloat();

                toyStore.addToy(new Toy(id, name, quantity, frequency));
                System.out.println("Toy added successfully");

                toyStore.saveToys();
            } else if (option == 2) {
                List<Toy> allToys = toyStore.getAllToys();

                if (allToys.isEmpty()) {
                    System.out.println("No toys available");
                } else {
                    System.out.println("Available toys:");

                    for (Toy toy : allToys) {
                        System.out.println("- " + toy.getName() + " (id: " + toy.getId() + ", quantity: " + toy.getQuantity() + ")");
                    }
                }

                toyStore.saveToys();
                toyStore.loadToys();
            } else if (option == 3) {
                System.out.print("Enter toy id: ");
                int id = scanner.nextInt();

                System.out.print("Enter new toy frequency: ");
                float frequency = scanner.nextFloat();

                toyStore.changeFrequency(id, frequency);
                System.out.println("Toy frequency changed successfully");

                toyStore.saveToys();
            } else if (option == 4) {
                Toy selectedToy = toyStore.selectToy();

                if (selectedToy != null) {
                    toyStore.writeToFile("prize_toys.txt", selectedToy);
                    System.out.println("You won a " + selectedToy.getName());
                    System.out.println("Congratulations!!!");

                    if (selectedToy.getQuantity() == 0) {
                        toyStore.remove(selectedToy);
                    }
                } else {
                    System.out.println("No toys available");
                }

                toyStore.saveToys();
            } else if (option == 5) {
                toyStore.saveToys();
                break;
            }
            System.out.println();
        }
    }
}