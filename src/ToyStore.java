import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyStore {
    private final List<Toy> toys;

    public ToyStore() {
        this.toys = new ArrayList<>();
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
            writer.write(toy.getId() + "," + toy.getName() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
