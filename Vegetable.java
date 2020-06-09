

public abstract class Vegetable  {
    protected String kind;
    protected int caloricContent;
    protected double mass;
    abstract double getCaloricContent();

    String getKind() {
        return this.kind;
    }

    double getMass() {
        return this.mass;
    }



}
