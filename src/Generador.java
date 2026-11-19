
public class Generador {
    //Va a generar una lista de enteros de tamaño size 
    // con numeros aleatorios de 0 a size
    public static int[] generate(int size) {
        int[] list = new int[size];
        for (int i = 0; i < size; i++) {
            list[i] = (int) (Math.random() * size);
        }
        return list;
    }
}
