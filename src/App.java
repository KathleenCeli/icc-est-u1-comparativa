import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        Generador generador = new Generador();
        Benchmark benchmark = new Benchmark();
        Metodos m = new Metodos();
        MergeSort mS = new MergeSort();

        //los 3 arreglos base
        //10000
        int[] lista1 = generador.generate(10000);
        //50000
        int[] lista2 = generador.generate(50000);
        //100000
        int[] lista3 = generador.generate(100000);

        int[] lista = generador.generate(100000);

        int[][] listas = {lista1, lista2, lista3};
        int[] lista1c1 = lista1.clone();
        int[] lista2c1 = lista2.clone();
        int[] lista3c1 = lista3.clone();

        int[] lista1c2 = lista1.clone();
        int[] lista2c2 = lista2.clone();
        int[] lista3c2 = lista3.clone();

        int[] lista1c3 = lista1.clone();
        int[] lista2c3 = lista2.clone();
        int[] lista3c3 = lista3.clone();

        int[] lista1c4 = lista1.clone();
        int[] lista2c4 = lista2.clone();
        int[] lista3c4 = lista3.clone();

        //Bubble sort
        Results resultaM1S1 = benchmark.medir(
                            () -> m.sortBubble(lista1c1),"bubble sort", "10.000");
        System.out.println("Resultado: " + resultaM1S1);

        Results resultaM1S2 = benchmark.medir(
                            () -> m.sortBubble(lista2c1),"bubble sort", "50.000");
        System.out.println("Resultado: " + resultaM1S2);

        Results resultaM1S3 = benchmark.medir(
                            () -> m.sortBubble(lista3c1),"bubble sort", "100.000");  
        System.out.println("Resultado: " + resultaM1S3);                  

        //Investigación: Merge Sort
        Results resultaM2S1 = benchmark.medir(
                            () -> mS.MergeSort(lista1c2), "Merge Sort", "10.000" );
        System.out.println("Resultado:" + resultaM2S1);

        Results resultaM2S2 = benchmark.medir(
                            ()-> mS.MergeSort(lista2c2), "Merge Sort", "50.000" );
        System.out.println("Resultado:" + resultaM2S1);

        Results resultaM2S3 = benchmark.medir(
                            ()-> mS.MergeSort(lista3c2), "Merge Sort", "100.000" );
        System.out.println("Resultado:" + resultaM2S3);

        //Arrays
        Results resultaM3S1 = benchmark.medir(
                            ()-> Arrays.sort(lista1c3), "Arrays", "10.000" );
        System.out.println("Resultado:" + resultaM3S1);

        Results resultaM3S2 = benchmark.medir(
                            ()-> Arrays.sort(lista2c3), "Arrays", "50.000" );
        System.out.println("Resultado:" + resultaM3S2);
        
        Results resultaM3S3 = benchmark.medir(
                            ()-> Arrays.sort(lista3c3), "Arrays", "10.000" );
        System.out.println("Resultado:" + resultaM3S3);
        
        //Arrays.sort con Comparator
        
        


        Results benGenList = benchmark.medir(
                            () -> generador.generate(100000), "generación de listado", "100.000");
        System.out.println("Resultado: " + benGenList);



        //long nanoTime = System.nanoTime();
        //long currentTimeMillis = System.currentTimeMillis();
        //System.out.println("Nano time: " + nanoTime);
        //System.out.println("Current time millis: " + currentTimeMillis);

        //en runable se guarda una función que va a ejecutar una acción
        //runable no devuelve nada, solo ejecuta la acción
        //Runnable funcion = () -> System.out.println("Ejecutando tarea");

        //System.out.println("Ejecución de la función runnable");
        //funcion.run();
    }
}
