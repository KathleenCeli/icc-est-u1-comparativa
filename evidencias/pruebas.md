# Evidencias y Casos de Aceptación

Las salidas reales registradas a continuación corresponden a la ejecución capturada en `evidencias/consola1.png` (ajusta el nombre si tu imagen se llama distinto).

| Caso de Prueba | Entrada / Escenario | Salida Esperada | Salida Real Obtenida | Estado |
| :--- | :--- | :--- | :--- | :---: |
| **CP-01: Ordenamiento V1 (Bubble Sort)** | Arreglos clonados de `10.000`, `50.000` y `100.000` enteros | Arreglo ordenado ascendentemente y registro de tiempo en consola | Ejecutado en `78.053 ms`, `2557.913 ms` y `10283.62 ms` respectivamente | OK |
| **CP-02: Ordenamiento V2 (Merge Sort)** | Arreglos clonados de `10.000`, `50.000` y `100.000` enteros | Orden ascendente estable sin mutar los arreglos base originales | Ejecutado en `1.3551 ms`, `9.8386 ms` y `17.8806 ms` sin alterar las muestras base | OK |
| **CP-03: Ordenamiento V3 (`Arrays.sort`)** | Arreglos primitivos `int[]` clonados de los 3 tamaños | Referencia nativa en orden ascendente mediante Dual-Pivot Quicksort | Ejecutado en `5.7176 ms`, `3.7645 ms` y `8.3353 ms` | OK |
| **CP-04: Ordenamiento V4 (`Comparator`)** | Arreglos `Integer[]` con `(a, b) -> Integer.compare(a, b)` | Orden ascendente sin riesgo de desbordamiento por resta | Ejecutado en `5.8206 ms`, `16.2769 ms` y `47.9624 ms` | OK |
| **CP-05: Casos borde en `MergeSort`** | Arreglo vacío `new int[0]` y arreglo de 1 elemento `new int[]{5}` | Retorno inmediato sin lanzar excepciones (`NullPointerException` o índices) | Ejecución limpia sin errores en tiempo de ejecución | OK |
| **CP-06: Integridad de muestras base** | Ejecución secuencial de V1, V2, V3 y V4 sobre el mismo lote | Cada variante recibe una copia desordenada idéntica mediante `.clone()` | Las 4 variantes procesaron arreglos desordenados sin afectarse entre sí | OK |