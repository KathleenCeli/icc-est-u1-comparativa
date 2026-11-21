# PL 1.1 Análisis de Algoritmos

* **Autor:** Kathleen Celi
* **Objetivo:** Comparar empíricamente el rendimiento temporal de algoritmos de ordenamiento básicos ($O(n^2)$) frente a algoritmos de dividir y vencerás ($O(n \log n)$) y los métodos nativos de la biblioteca estándar de Java (`Arrays.sort`) sobre distintos tamaños de muestra.
* **Entorno:** Java 21 (LTS) — Visual Studio Code

## 1. Organización del Proyecto y Responsabilidades
* `src/App.java`: Clase principal que instancia los arreglos base, realiza la clonación fuera del cronómetro y coordina las 12 ejecuciones del benchmark.
* `src/Generador.java`: Genera los arreglos aleatorios base de `10.000`, `50.000` y `100.000` elementos.
* `src/Benchmark.java`: Ejecuta las tareas recibidas mediante expresiones lambda y mide su duración exacta con `System.nanoTime()`.
* `src/Results.java`: Modelo de datos que almacena el nombre del método, la duración medida y el tamaño de muestra (`sample`), formateando la salida.
* `src/MetodosOrdenamiento.java`: Contiene la variante básica **V1 (Bubble Sort)**.
* `src/MergeSort.java`: Contiene la variante investigada **V2 (Merge Sort)** implementada de forma recursiva con arreglo auxiliar único.

## 2. Comandos de Compilación y Ejecución
* **Compilación desde terminal:**
  ```bash
  javac -d bin src/*.java



## Explicación del Mecanismo de Benchmarking
Interfaz funcional y Lambdas: El método benchmark.medir(...) recibe una interfaz funcional Runnable mediante expresiones lambda () -> ..., permitiendo encapsular cualquier llamada de ordenamiento sin acoplar el cronómetro a un algoritmo específico. Cronómetro y unidades: Antes de invocar la lambda se captura el tiempo inicial con System.nanoTime() y al finalizar se resta del tiempo final para obtener la duración en nanosegundos, convirtiéndola luego a milisegundos para su reporte. Significado de sample: Etiqueta textual ("10.000", "50.000", "100.000") que identifica el tamaño $N$ del arreglo evaluado en cada prueba. 
Operaciones excluidas y tratamiento de errores: Tanto la generación de los arreglos aleatorios, la conversión de int[] a Integer[] (autoboxing para V4), como la clonación .clone() de cada muestra se ejecutan fuera del bloque cronometrado para evitar alterar los datos originales y garantizar que solo se mida el tiempo puro de ordenamiento. Almacenamiento e impresión final: Cada ejecución retorna un objeto Results que se guarda en una lista/estructura de resultados y se imprime de forma ordenada en consola al concluir todas las variantes.

## TABLA DE RESULTADOS

| Método | Sample | Tiempo (ms) |
| :--- | :---: | :---: |
| V1 - Bubble Sort | 10.000 | 78.053 ms |
| V1 - Bubble Sort | 50.000 | 2557.913 ms |
| V1 - Bubble Sort | 100.000 | 10283.62 ms |
| V2 - Merge Sort | 10.000 | 1.3551 ms |
| V2 - Merge Sort | 50.000 | 9.8386 ms |
| V2 - Merge Sort | 100.000 | 17.8806 ms |
| V3 - Arrays | 10.000 | 5.7176 ms |
| V3 - Arrays | 50.000 | 3.7645 ms |
| V3 - Arrays | 100.000 | 8.3353 ms |
| V4 - Arrays.sort Comparator | 10.000 | 5.8206 ms |
| V4 - Arrays.sort Comparator | 50.000 | 16.2769 ms |
| V4 - Arrays.sort Comparator | 100.000 | 47.9624 ms |

## EVIDENCIA
![alt text](image.png)

## CONCLUSIONES
## 5. Conclusiones

1. Al comparar el método básico V1 (`Bubble Sort`) con el método investigado V2 (`Merge Sort`) en el tamaño de muestra de `100.000` elementos, se observa una diferencia demasiado grande en los tiempos de ejecución (`10283.62` ms frente a `17.8806` ms). Esto confirma en la práctica el impacto de la complejidad asintótica: mientras `Bubble Sort` tiene un crecimiento cuadrático `O(n²)` debido a sus bucles anidados que multiplican las comparaciones al aumentar los datos, `Merge Sort` mantiene una complejidad `O(n log n)` al dividir el arreglo en mitades, haciendo que el tiempo crezca de forma mucho más controlada.

2. Al contrastar mi implementación de `Merge Sort` (V2) frente al método nativo `Arrays.sort(int[])` (V3), los resultados muestran que V3 fue más rápido tanto en `50.000` elementos (`3.7645` ms frente a `9.8386` ms) como en `100.000` elementos (`8.3353` ms frente a `17.8806` ms). Aunque ambos algoritmos pertenecen a la misma clase de complejidad `O(n log n)`, `Arrays.sort(int[])` utiliza internamente una variante optimizada de *Dual-Pivot Quicksort* que ordena directamente sobre el arreglo original (*in-place*), evitando el costo adicional de reservar arreglos auxiliares y copiar elementos en memoria como ocurre en `Merge Sort`.

3. Al comparar las dos variantes de la biblioteca estándar de Java, V3 (`Arrays.sort(int[])`) y V4 (`Arrays.sort(Integer[], Comparator)`), se evidencia que en `100.000` elementos la versión para primitivos V3 fue más veloz (`8.3353` ms frente a `47.9624` ms). Esta diferencia se debe a que V3 opera directamente sobre valores primitivos en memoria con *Dual-Pivot Quicksort*, mientras que V4 trabaja con objetos de la clase envolvente `Integer` mediante *TimSort*, asumiendo además el costo extra de ejecutar el método `compare(a, b)` del `Comparator` en cada comparación.