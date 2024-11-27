import java.util.ArrayList;
import java.util.List;

public class Necklace {
    private List<Gemstone> gemstones;

    public Necklace() {
        this.gemstones = new ArrayList<>();
    }

    public void addGemstone(Gemstone gem) {
        gemstones.add(gem);
    }

    public double getTotalWeight() {
        return gemstones.stream().mapToDouble(Gemstone::getWeight).sum();
    }

    public double getTotalCost() {
        return gemstones.stream().mapToDouble(Gemstone::calculateTotalCost).sum();
    }

    public void display() {
        System.out.println("Necklace contains:");
        for (Gemstone gem : gemstones) {
            System.out.println(gem);
        }
        System.out.printf("Total weight: %.2f carats\n", getTotalWeight());
        System.out.printf("Total cost: %.2f\n", getTotalCost());
    }
}
