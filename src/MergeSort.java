public class MergeSort {
    public void MergeSort(int[] lista) {
        if (lista == null || lista.length <= 1) {
            return;
        }
        dividir(lista, 0, lista.length - 1);
    }

    private void dividir(int[] lista, int inicio, int fin) {
        if (inicio < fin) {
            int medio = (inicio + fin) / 2;
            dividir(lista, inicio, medio);
            dividir(lista, medio + 1, fin);
            mezclar(lista, inicio, medio, fin);
        }
    }
    private void mezclar(int[] lista, int inicio, int medio, int fin) {
        int[] temp = new int[fin - inicio + 1];
        int i = inicio;
        int j = medio + 1;
        int k = 0;

        while (i <= medio && j <= fin) {
            if (lista[i] <= lista[j]) {
                temp[k++] = lista[i++];
            } else {
                temp[k++] = lista[j++];
            }
        }

        while (i <= medio) {
            temp[k++] = lista[i++];
        }

        while (j <= fin) {
            temp[k++] = lista[j++];
        }

        System.arraycopy(temp, 0, lista, inicio, temp.length);
    }

}
