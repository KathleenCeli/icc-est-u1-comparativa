import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Generador generador = new Generador();
        Benchmark benchmark = new Benchmark();

        List<Integer> lista = generador.generate(100000);
        //System.out.println("Lista generada: " + lista);

        Results benGenList = benchmark.medir(() -> generador.generate(100000),
                             "generación de listado", "100000");

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
