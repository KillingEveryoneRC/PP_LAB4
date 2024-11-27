abstract class Gemstone {
    private String name;
    private double weight; // Weight in carats
    private double cost;   // Cost per carat
    private int transparency; // Transparency (0-100)

    public Gemstone(String name, double cost, int transparency) {
        this.name = name;
        this.cost = cost;
        this.transparency = transparency;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCost() {
        return cost;
    }

    public int getTransparency() {
        return transparency;
    }

    public double calculateTotalCost() {
        return weight * cost;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f carats, %.2f per carat, transparency: %d%%",
                name, weight, cost, transparency);
    }
}

class PreciousStone extends Gemstone {
    public PreciousStone(String name, double cost, int transparency) {
        super(name, cost, transparency);
    }
}

class SemiPreciousStone extends Gemstone {
    public SemiPreciousStone(String name, double cost, int transparency) {
        super(name, cost, transparency);
    }
}
