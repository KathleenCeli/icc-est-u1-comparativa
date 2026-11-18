public class Benchmark {
    
    public static Results medir(Runnable accion, String nombre, String sample) {
        System.out.println("Función :" + nombre);
        //Tiempo actual
        long tiempoActual = System.nanoTime();
        //accion
        accion.run();
        //tiempo final
        long tiempoFinal = System.nanoTime();
        //retornar el tiempo de duración
        Results resultado = new Results(nombre, (tiempoFinal - tiempoActual), sample);
        return resultado;
    }

}
