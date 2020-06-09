public class Cucumber extends Vegetable {
    private double length;

    public Cucumber(String kind, int caloricContent, double mass) {
        this.kind = kind;
        this.caloricContent = caloricContent;
        this.mass = mass;
    }

    public Cucumber(String kind, int caloricContent, double mass, double length) {
        this.kind = kind;
        this.caloricContent = caloricContent;
        this.mass = mass;
        this.length = length;
    }

    public double getLength() {
        return this.length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    double getCaloricContent() {
        return this.caloricContent * this.mass / 100;
    }

    @Override
    public String toString() {
        return "Cucumber { kind: \" " + String.valueOf(this.kind) + "\" caloricContent: " +
                String.valueOf(caloricContent) + " (kalories/100 grams) mass: " +
                String.valueOf(this.mass) + " (grams) }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cucumber cucumber = (Cucumber) o;
        return kind.equals(cucumber.kind) &&
                mass == cucumber.mass &&
                caloricContent == cucumber.caloricContent &&
                length == cucumber.length;
    }
}
