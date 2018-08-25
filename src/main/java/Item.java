public class Item {
    public String ISBN10;
    public String name;
    public int price;
    public double weight;

    public Item(String ISBN10, String name, int price, double weight) {
        this.ISBN10 = ISBN10;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.name + "\t\t $" + this.price;
    }
}