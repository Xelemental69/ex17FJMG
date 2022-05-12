/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio3;

/**
 *
 * @author fcoj
 */
import java.util.*;
import ejercicio1.*;
import java.io.*;

//Con este import tendremos acceso a las
//funciones LeerPalo y LeerValor:
import static ejercicio2.LeerCartas.*;

public class completaEscaleraDeColor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        ArrayList<ArrayList<Carta>> mano = CrearMapDesdeFichero();
        //
        ArrayList<String> valoresParaEscalera = new ArrayList<>();
        int contadorP = 0; //Contará las picas por mano.
        int contadorR = 0; //Contará los diamantes/rombos por mano.
        int contadorT = 0; //Contará los tréboles por mano.
        int contadorC = 0; //Contará los corazones por mano.
        String letra = "";

        for (ArrayList<Carta> r : mano) {

            for (Carta c : r) {

                switch (c.getTipo()) {

                    case PICAS:
                        contadorP++;

                    case ROMBOS: //Rombos = Diamantes
                        contadorR++;

                    case TREBOLES:
                        contadorT++;

                    default://Corazones
                        contadorC++;

                }

                letra = transformarPalo(c.getTipo());

            }

            if (contadorP == 4 || contadorR == 4 || contadorT == 4 || contadorC == 4) {

                if (valoresCercanos(r) != -1) {

                    valoresParaEscalera.add(valoresCercanos(r) + " " + letra);

                }else {

                    valoresParaEscalera.add("NADA");

                }

            } else {

                valoresParaEscalera.add("NADA");

            }
            
            contadorP = 0;
            contadorR = 0;
            contadorT = 0;
            contadorC = 0;

        }
        
        escribirEscalera(valoresParaEscalera);

    }

    public static int valoresCercanos(ArrayList<Carta> fila) {

        int contadorCercanos = 0;
        int aux = 0;

        Map<Integer, String> valores = new TreeMap<>(); //Lo usaremos solo para ordenar los valores

        for (Carta c : fila) {

            valores.put(c.getValor(), c.cartaRedux());

        }

        for (Integer key : valores.keySet()) {

            if (key == (aux + 1)) {

                contadorCercanos++;

            }

            System.out.println("Key = " + key);
            System.out.println("Aux = " + aux);

            aux = key;

        }

        System.out.println("CC = " + contadorCercanos);

        return (contadorCercanos == 3) ? aux : -1;

    }

    public static String transformarPalo(Palos recipiente) {

        switch (recipiente) {

            case PICAS:
                return "P";

            case ROMBOS: //Rombos = Diamantes
                return "D";

            case TREBOLES:
                return "T";

            default://Corazones
                return "C";

        }

    }

    public static void escribirEscalera(ArrayList<String> valores) throws IOException {

        // Fichero a crear. Ruta relativa a la carpeta raíz del proyecto
        String idFichero = "ultimoValorParaEscalera.txt";
        String tmp;

        try ( BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {
            
            for (int i = 0; i < valores.size(); i++) {

                flujo.write(valores.get(i));
                flujo.newLine();

            }

            flujo.flush();

            System.out.println("Fichero " + idFichero + " creado correctamente.");

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }

    }

    public static ArrayList<ArrayList<Carta>> CrearMapDesdeFichero() {
        //Es el ejercicio 2 copiado y pegado, pero devolvemos la lista ahora.

        ArrayList<ArrayList<Carta>> rondas = new ArrayList<>();

        // Fichero a leer:
        String idFichero = "escaleraColor.txt";

        // Variables para guardar cada carta:
        String[] tokens;
        String[] aux = new String[2];
        String linea;
        boolean tipoCarta = false;
        ArrayList<Carta> tmp = new ArrayList<>();
        int contador = 1; //Para mostrar las rondas al final

        System.out.println("Leyendo el fichero: " + idFichero);

        //Iniciamos el flujo...
        try ( Scanner datosFichero = new Scanner(new File(idFichero), "us-ascii")) {

            while (datosFichero.hasNextLine()) {

                linea = datosFichero.nextLine();

                tokens = linea.split(" ");

                for (String string : tokens) {
                    //System.out.print(string + " ");

                    if (!tipoCarta) {

                        aux[0] = string;
                        tipoCarta = true;

                    } else {

                        aux[1] = string;
                        tmp.add(new Carta(leerPalo(aux[1]),
                                leerValor(aux[0])));

                        tipoCarta = false;

                    }

                }
                //System.out.println();

                rondas.add(tmp);
                tmp = new ArrayList<>();

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        for (ArrayList<Carta> r : rondas) {

            System.out.printf("Ronda " + contador + ": ");
            for (Carta c : r) {

                System.out.printf("%s, ", c);

            }

            System.out.println("");
            contador++;

        }

        return rondas;

    }

}
