import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ToyStore toyStore = new ToyStore();

        toyStore.addToy(new Toy(1, "Teddy Bear", 10, 40));
        toyStore.addToy(new Toy(2, "Doll", 15, 30));
        toyStore.addToy(new Toy(3, "Car", 20, 30));

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Add new toy");
        System.out.println("2. Change toy frequency");
        System.out.println("3. Play toy selection game");
        System.out.println("4. Exit");

        while (true) {
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
            } else if (option == 2) {
                System.out.print("Enter toy id: ");
                int id = scanner.nextInt();

                System.out.print("Enter new toy frequency: ");
                float frequency = scanner.nextFloat();

                toyStore.changeFrequency(id, frequency);
                System.out.println("Toy frequency changed successfully");
            } else if (option == 3) {
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
            } else if (option == 4) {
                break;
            }
            System.out.println();
        }
    }
}