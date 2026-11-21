# Investigación: Algoritmo Merge Sort (V2)

## 1. Identificación de la Implementación
* **Algoritmo:** Merge Sort (Ordenamiento por Mezcla) — Técnica de *Divide y Vencerás*.
* **Variante implementada:** V2 (Método investigado sobre arreglo primitivo `int[]` en orden ascendente)[cite: 2].
* **Archivo y ruta en el repositorio:** `src/MergeSort.java`
* **Clase y métodos:**
  * Clase: `MergeSort`
  * Método público de entrada: `public void MergeSort(int[] lista)`
  * Método privado recursivo de división: `private void dividir(int[] lista, int inicio, int fin)`
  * Método privado de combinación: `private void mezclar(int[] lista, int inicio, int medio, int fin)`
* **Adaptación realizada:** A diferencia de las implementaciones clásicas que instancian dos arreglos temporales (`izq[]` y `der[]`) en cada llamada a `mezclar`, esta versión utiliza un **único arreglo auxiliar `temp`** del tamaño exacto del subsegmento (`fin - inicio + 1`) y copia el resultado ordenado de vuelta al arreglo original mediante `System.arraycopy(...)`

---

## 2. Funcionamiento Paso a Paso y Recursión
1. **Validación y arranque (`MergeSort`):** Recibe el arreglo `int[] lista`. Verifica el caso base preventivo (`lista == null || lista.length <= 1`); si se cumple, retorna inmediatamente. En caso contrario, inicia la recursión invocando `dividir(lista, 0, lista.length - 1)`
2. **Parámetros de control (`dividir`):** Trabaja sobre el mismo arreglo `lista` delimitando el rango activo mediante los índices enteros `inicio` y `fin`
3. **Caso base de la recursión:** La condición `if (inicio < fin)` detiene las llamadas cuando `inicio == fin` (subarreglo de tamaño 1, que por definición ya está ordenado)
4. **Progreso de la recursión:** Se calcula el punto medio `int medio = (inicio + fin) / 2` y se realizan dos llamadas recursivas que reducen el problema a la mitad: `dividir(lista, inicio, medio)` (mitad izquierda) y `dividir(lista, medio + 1, fin)` (mitad derecha)
5. **Propiedad invariante que se conserva:** Antes de ejecutar `mezclar(lista, inicio, medio, fin)`, se garantiza por inducción que tanto el subarreglo `lista[inicio..medio]` como `lista[medio+1..fin]` ya se encuentran ordenados ascendentemente. Al finalizar `mezclar`, todo el rango `lista[inicio..fin]` queda ordenado.

---

## 3. Mecánica de División, Mezcla, Arreglo Auxiliar y Empates
* **División:** Se realiza de forma lógica mediante índices (`inicio`, `medio`, `fin`), sin crear copias físicas del arreglo durante la fase descendente del árbol recursivo
* **Mezcla y arreglo auxiliar (`temp`):** En `mezclar`, se reserva `int[] temp = new int[fin - inicio + 1]`. Se usan tres punteros: `i = inicio` (recorre la mitad izquierda), `j = medio + 1` (recorre la mitad derecha) y `k = 0` (posición de escritura en `temp`). El bucle `while (i <= medio && j <= fin)` compara `lista[i]` contra `lista[j]` y copia el menor en `temp[k++]`. Luego, los dos ciclos `while` residuales vacían los elementos sobrantes de la mitad que no haya terminado. Finalmente, `System.arraycopy(temp, 0, lista, inicio, temp.length)` sobrescribe el segmento en `lista`.
* **Tratamiento de empates:** En la línea 24 se evalúa `if (lista[i] <= lista[j])`. El uso del operador menor o igual (`<=`) garantiza que, ante dos elementos con el mismo valor, **siempre se tome primero el de la mitad izquierda (`lista[i]`)**, preservando su orden relativo original.

---

## 4. Traza Manual sobre `[7, 2, 7, 0, 4, 2]`

### Fase 1: Subdivisiones (`dividir`)
* **Nivel 0:** `[7, 2, 7, 0, 4, 2]` (`inicio=0, fin=5, medio=2`)
  * **Nivel 1 (Izq):** `[7, 2, 7]` (`inicio=0, fin=2, medio=1`)
    * **Nivel 2 (Izq):** `[7, 2]` (`inicio=0, fin=1, medio=0`) -> Se divide en `[7]` (`0..0`) y `[2]` (`1..1`) *(casos base)*
    * **Nivel 2 (Der):** `[7]` (`inicio=2, fin=2`) *(caso base)*
  * **Nivel 1 (Der):** `[0, 4, 2]` (`inicio=3, fin=5, medio=4`)
    * **Nivel 2 (Izq):** `[0, 4]` (`inicio=3, fin=4, medio=3`) -> Se divide en `[0]` (`3..3`) y `[4]` (`4..4`) *(casos base)*
    * **Nivel 2 (Der):** `[2]` (`inicio=5, fin=5`) *(caso base)*

### Fase 2: Mezclas y Resultados Intermedios (`mezclar`)
1. `mezclar(0, 0, 1)` sobre `[7]` y `[2]`: `2 < 7` $\rightarrow$ `temp = [2, 7]`. Estado del arreglo: **`[2, 7, 7, 0, 4, 2]`**
2. `mezclar(0, 1, 2)` sobre `[2, 7]` y `[7]`: `2 <= 7`, `7 <= 7` (empate: pasa primero el `7` del índice 1) $\rightarrow$ `temp = [2, 7, 7]`. Estado: **`[2, 7, 7, 0, 4, 2]`**
3. `mezclar(3, 3, 4)` sobre `[0]` y `[4]`: `0 <= 4` $\rightarrow$ `temp = [0, 4]`. Estado: **`[2, 7, 7, 0, 4, 2]`**
4. `mezclar(3, 4, 5)` sobre `[0, 4]` y `[2]`: `0 <= 2`, `2 < 4`, sobra `4` $\rightarrow$ `temp = [0, 2, 4]`. Estado: **`[2, 7, 7, 0, 2, 4]`**
5. `mezclar(0, 2, 5)` sobre `[2, 7, 7]` y `[0, 2, 4]`:
   * `0 < 2` $\rightarrow$ `[0]`
   * `2 <= 2` (empate: pasa primero el `2` izquierdo del índice 0) $\rightarrow$ `[0, 2]`
   * `2 < 7` (pasa el `2` derecho del índice 4) $\rightarrow$ `[0, 2, 2]`
   * `4 < 7` $\rightarrow$ `[0, 2, 2, 4]`
   * Sobran `7, 7` de la izquierda $\rightarrow$ `temp = [0, 2, 2, 4, 7, 7]`

### Salida Final
* **Arreglo ordenado:** **`[0, 2, 2, 4, 7, 7]`**

---

## 5. Análisis de Complejidad y Estabilidad

| Métrica | Complejidad | Justificación técnica |
| :--- | :---: | :--- |
| **Tiempo — Mejor caso** | $O(n \log n)$ | El algoritmo divide siempre hasta subarreglos de tamaño 1 sin verificar si el arreglo ya estaba ordenado, ejecutando los $\log_2 n$ niveles y sus $n$ comparaciones[cite: 9]. |
| **Tiempo — Caso promedio** | $O(n \log n)$ | Bajo el supuesto de una distribución uniforme aleatoria de los elementos (sin sesgo en las comparaciones de `mezclar`), cada nivel realiza $\Theta(n)$ operaciones a lo largo de $\lceil \log_2 n \rceil$ niveles[cite: 10]. |
| **Tiempo — Peor caso** | $O(n \log n)$ | Ocurre cuando los elementos se intercalan alternadamente en cada mitad (maximizando las comparaciones en `while (i <= medio && j <= fin)`), manteniendo exactos los $n \log_2 n$ pasos[cite: 9, 10]. |
| **Memoria auxiliar** | $O(n)$ | En la última llamada a `mezclar(0, medio, n-1)`, `temp` reserva exactamente $n$ enteros en el *Heap*[cite: 9]. Los arreglos `temp` de niveles inferiores son liberados por el *Garbage Collector* al salir de cada invocación. |
| **Pila recursiva (*Call Stack*)** | $O(\log n)$ | Como la recursión avanza en profundidad primero por la rama izquierda y luego por la derecha, la altura máxima simultánea de marcos en la pila es $\lceil \log_2 n \rceil + 1$. |

* **Justificación de estabilidad:** La implementación entregada es **estrictamente estable** porque: (1) divide el arreglo de forma contigua sin realizar intercambios a larga distancia, y (2) en la comparación `if (lista[i] <= lista[j])` (línea 24), ante claves iguales (`lista[i] == lista[j]`), siempre se selecciona el elemento perteneciente al subarreglo izquierdo (`inicio..medio`), que ocupaba una posición anterior en el arreglo original.

---

## 6. Caso Desfavorable y Validación en Pruebas
* **Caso desfavorable identificado:** Un arreglo ordenado de forma intercalada (por ejemplo, elementos pares en la primera mitad e impares consecutivos en la segunda: `[0, 2, 4, 6, 1, 3, 5, 7]`), ya que obliga al bucle principal de `mezclar` a realizar el **número máximo teórico de comparaciones** ($n - 1$ comparaciones en cada mezcla) sin que ningún subarreglo se agote anticipadamente.
* **Relación con la prueba real y control de fallos:**
  * En nuestra ejecución con **$n = 100\,000$ elementos**, el algoritmo completó el ordenamiento en **17.8806 ms** sin desbordamiento de pila (*StackOverflowError*), ya que $\log_2(100\,000) \approx 17$ niveles de recursión.
  * Nota sobre cálculo de índices: En la línea 11 (`int medio = (inicio + fin) / 2;`), para tamaños de muestra de hasta `100.000` no hay riesgo de desbordamiento de entero (`int` soporta hasta 2<sup>31</sup> - 1, es decir, `2.147.483.647`). No obstante, en arreglos cercanos a 2 × 10<sup>9</sup> elementos, `inicio + fin` provocaría *integer overflow* (índice negativo); la corrección preventiva estándar es `int medio = inicio + (fin - inicio) / 2;`.

---

## 7. Relación con Resultados del Benchmarking y Fuentes Bibliográficas

### Observaciones a partir de la tabla de Benchmarking
1. **Brecha entre crecimiento cuadrático $O(n^2)$ y lineal-logarítmico $O(n \log n)$:** Al escalar el tamaño de muestra de `10.000` a `100.000` (un factor de $10\times$), el método básico V1 (`Bubble Sort`, $O(n^2)$) incrementó su tiempo aproximadamente en un factor de $100\times$ (pasando de `78.053` ms a `10283.62` ms), mientras que `Merge Sort` (V2, $O(n \log n)$) creció de forma casi lineal, pasando de `1.3551` ms a `17.8806` ms.
2. **Comparación de V2 frente a los métodos nativos de Java (V3 y V4):** Aunque `Merge Sort` (V2) comparte la misma clase de complejidad asintótica $O(n \log n)$ que `Arrays.sort(int[])` (V3), V3 resultó más rápido (`8.3353` ms frente a `17.8806` ms) debido a que el JDK utiliza *Dual-Pivot Quicksort* ordenando *in-place* (sin el costo de reservar memoria dinámica `new int[...]` y ejecutar `System.arraycopy` en cada nivel recursivo). Asimismo, V2 superó o compitió directamente contra V4 (`47.9614` ms ), ya que V4 trabaja sobre objetos `Integer[]` y sufre penalización por *unboxing* en cada invocación al `Comparator`.

### Fuentes Bibliográficas Consultadas
1. **Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C.** (2022). *Introduction to Algorithms* (4.ª ed., Capítulo 2: *Getting Started — Merge Sort*, pp. 34–44). MIT Press. (Consultado el 20 de noviembre de 2026).
2. **Sedgewick, R., & Wayne, K.** (2011). *Algorithms* (4.ª ed., Sección 2.2: *Mergesort*, pp. 270–285). Addison-Wesley Professional. Disponible en: https://algs4.cs.princeton.edu/22mergesort/ (Consultado el 20 de noviembre de 2026).
3. **Oracle Corporation.** (2024). *Java Platform, Standard Edition & Java Development Kit Version 21 API Specification: Class `java.util.Arrays` y `java.lang.System#arraycopy`*. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Arrays.html (Consultado el 20 de noviembre de 2026).