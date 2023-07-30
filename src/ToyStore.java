import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyStore {
    private final List<Toy> toys;
    private static final String FILE_NAME = "toys.txt";

    public ToyStore() {
        this.toys = new ArrayList<>();
        loadToys();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void changeFrequency(int toyId, float frequency) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setFrequency(frequency);
                break;
            }
        }
    }

    public Toy selectToy() {
        float totalFrequency = 0;

        for (Toy toy : toys) {
            totalFrequency += toy.getFrequency();
        }

        float randomNumber = new Random().nextFloat() * totalFrequency;

        for (Toy toy : toys) {
            randomNumber -= toy.getFrequency();

            if (randomNumber <= 0) {
                toy.setQuantity(toy.getQuantity() - 1);
                return toy;
            }
        }

        return null;
    }

    public void writeToFile(String fileName, Toy toy) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(toy.getId() + ", " + toy.getName() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Toy> getAllToys() {
        return toys;
    }

    public void loadToys() {
        toys.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                float frequency = Float.parseFloat(parts[3]);
                toys.add(new Toy(id, name, quantity, frequency));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToys() {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            for (Toy toy : toys) {
                writer.write(toy.getId() + "," + toy.getName() + "," + toy.getQuantity() + "," + toy.getFrequency() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(Toy toy) {
        toys.remove(toy);
    }
}
