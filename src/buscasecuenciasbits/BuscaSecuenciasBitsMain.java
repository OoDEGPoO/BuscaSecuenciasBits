/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscasecuenciasbits;

/**
 *
 * @author diego
 */
public class BuscaSecuenciasBitsMain {

    /**
     * @param args the command line arguments
     */
    
    private static void imprimeMatrices(int[][] matriz){
        for (int x=0; x < matriz.length; x++) {
            
            for (int y=0; y < matriz[x].length; y++) {
                System.out.print (matriz[x][y]);
                
                if (y!=matriz[x].length-1) System.out.print("\t");
            }
            
            System.out.println("\n");
        }
    }
    
    private static void imprimeArray(int[] array){
        System.out.print("{");
        for (int i = 0; i < array.length; i++){
            if (i == array.length-1) System.out.print(array[i] + "}");
            else System.out.print(array[i] + ", ");
        }
        System.out.println();
    }
    
    private static int[] buscaSecuencias5001(int[] A, int[] B){
        int[] out = null;//puede no haber secuencia coincidente
        int[][] matrizBinaria = new int[A.length + 1][B.length + 1];
        //Le daremos 1 más que A y B para poder tener las primeras columna y filas libres.
        int i, j, s;
        
        imprimeMatrices(matrizBinaria);
        System.out.println("\n\n");
        
        //rellenaremos la matriz de forma diagonal buscando coincidencias
        for (i = 1; i < matrizBinaria.length; i++){
            for (j = 1; j < matrizBinaria[i].length; j++){
                if (A[i-1] == B[j-1]) //si coinciden, tomamos el anterior diagonalmente y le sumamos uno.
                    matrizBinaria[i][j] = matrizBinaria[i-1][j-1] + 1;
                else if (matrizBinaria[i-1][j] >= matrizBinaria[i][j-1])
                    //Si no, ahora compara con los anteriores vertical y horizontalmente
                    matrizBinaria[i][j] = matrizBinaria[i-1][j];
                else matrizBinaria[i][j] = matrizBinaria[i][j-1];
                    //lo mismo
            }
        }
        
        imprimeMatrices(matrizBinaria);
        
        //Ahora que tenemos el tamaño de la secuencia coincidente, podemos inicializar el vector de salida
        out = new int[matrizBinaria[A.length][B.length]];
        i = 1; j = 1; s = 0;//reinicio los contadores necesarios para la operación de busqueda
        
        //a continuacion recorremos la matriz en diagonal, buscando continuidad en el crecimiento
        while (s < matrizBinaria[A.length][B.length]){
            //Buscamos la sucesión de valores
            if (matrizBinaria[i][j] == matrizBinaria[i-1][j-1]+1){ out[s] = A[i-1]; s++;}
            //esto es para avanzar por la matriz hasta llegar al limite
            if ((A.length > i) && (B.length > j)) {i++; j++;}
        }
        //Con esto obtenemos solo una de las posibles secuencias de bits coincidentes, pero puede haber más
        //De lo que nos aseguramos es de tomar una solución
        
        return out;
    }
    
    public static void main(String[] args) {
        int[] A = {0, 1, 1, 0, 1, 0, 1, 0}, B = {1, 0, 1, 0, 0, 1, 0, 0, 1}, X;
        
        X = buscaSecuencias5001(A, B);
        imprimeArray(X);
        
        int[] C = {0, 1, 0, 1, 0, 1, 0}, D = {1, 0, 1, 0, 0, 1, 0, 0, 1}, Y;
        
        Y = buscaSecuencias5001(C, D);
        imprimeArray(Y);
    }
    
}
