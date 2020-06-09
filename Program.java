import java.util.Iterator;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        Cabbage cabbage = new Cabbage("White cabbage",25,1000);
        Cucumber cucumber1 = new Cucumber("Eropheus", 16, 100);
        Cucumber cucumber2 = new Cucumber("Ant",16,80);
        Cucumber cucumber3 = new Cucumber("Moscow evenings",16,120);
        Carrot carrot1 = new Carrot("Amsterdam", 32,50);
        Carrot carrot2 = new Carrot("Tuchon", 32, 80);
        Salad salad = new Salad(cabbage,cucumber1,cucumber2,cucumber3,carrot1,carrot2);
        List<Vegetable> vList = salad.subList(0,3);
        System.out.println(vList);

        Iterator<Vegetable > it = vList.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
