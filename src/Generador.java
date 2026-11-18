import java.util.ArrayList;
import java.util.List;

public class Generador {
    //Va a generar una lista de enteros de tamaño size 
    // con numeros aleatorios de 0 a size
    public static List<Integer> generate(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add((int) (Math.random() * size));
        }
        return list;
    }
}
