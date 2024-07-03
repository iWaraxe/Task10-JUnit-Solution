package shop;

public class RealItem extends Item {

    private double weight;

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return super.toString() + "Weight: " + weight;
    }
}
