import java.io.*;
import java.util.*;

public class GemstoneManager {
    private static List<Gemstone> gemstones = new ArrayList<>();
    private static Necklace necklace = new Necklace();

    public static void main(String[] args) throws IOException {
        loadGemstones("D:\\OI16\\Java\\PP_Lab4-8\\src\\gemstones.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Sort gemstones by cost per carat");
            System.out.println("2. Find gemstones by transparency");
            System.out.println("3. Display all gemstones");
            System.out.println("4. Add gemstone");
            System.out.println("5. Add gemstone to necklace");
            System.out.println("6. Display necklace");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> sortGemstones();
                case 2 -> findGemstones(scanner);
                case 3 -> displayGemstones();
                case 4 -> addGemstone(scanner);
                case 5 -> addToNecklace(scanner);
                case 6 -> necklace.display();
                case 0 -> {
                    System.out.println("Exiting program.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void loadGemstones(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String name = parts[1];
                double cost = Double.parseDouble(parts[2]);
                int transparency = Integer.parseInt(parts[3]);

                if (type.equalsIgnoreCase("Precious")) {
                    gemstones.add(new PreciousStone(name, cost, transparency));
                } else if (type.equalsIgnoreCase("SemiPrecious")) {
                    gemstones.add(new SemiPreciousStone(name, cost, transparency));
                }
            }
        }
    }

    private static void sortGemstones() {
        gemstones.sort(Comparator.comparingDouble(Gemstone::getCost));
        System.out.println("Gemstones sorted by cost per carat.");
    }

    private static void findGemstones(Scanner scanner) {
        System.out.print("Enter minimum transparency: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum transparency: ");
        int max = scanner.nextInt();

        System.out.println("Gemstones in the range:");
        gemstones.stream()
                .filter(gem -> gem.getTransparency() >= min && gem.getTransparency() <= max)
                .forEach(System.out::println);
    }

    private static void displayGemstones() {
        System.out.println("All gemstones:");
        gemstones.forEach(System.out::println);
    }

    private static void addGemstone(Scanner scanner) {
        System.out.print("Enter type (Precious/SemiPrecious): ");
        String type = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter cost per carat: ");
        double cost = scanner.nextDouble();
        System.out.print("Enter transparency (0-100): ");
        int transparency = scanner.nextInt();

        Gemstone gem;
        if (type.equalsIgnoreCase("Precious")) {
            gem = new PreciousStone(name, cost, transparency);
        } else {
            gem = new SemiPreciousStone(name, cost, transparency);
        }

        gemstones.add(gem);
        System.out.println("Gemstone added.");
    }

    private static void addToNecklace(Scanner scanner) {
        displayGemstones();
        System.out.print("Enter gemstone number to add to necklace: ");
        int index = scanner.nextInt() - 1;
        System.out.print("Enter weight in carats: ");
        double weight = scanner.nextDouble();

        if (index >= 0 && index < gemstones.size()) {
            Gemstone selectedGem = gemstones.get(index);
            Gemstone gemForNecklace = selectedGem instanceof PreciousStone
                    ? new PreciousStone(selectedGem.getName(), selectedGem.getCost(), selectedGem.getTransparency())
                    : new SemiPreciousStone(selectedGem.getName(), selectedGem.getCost(), selectedGem.getTransparency());

            gemForNecklace.setWeight(weight);
            necklace.addGemstone(gemForNecklace);
            System.out.println("Gemstone added to necklace.");
        } else {
            System.out.println("Invalid gemstone number.");
        }
    }
}
