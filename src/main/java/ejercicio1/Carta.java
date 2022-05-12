/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio1;

/**
 *
 * @author fcoj
 */
import java.util.*;

public class Carta {

    private Palos tipo;
    private int valor;
    private Random rng = new Random();

    public Carta(Palos tipo, int valor) {

        this.tipo = tipo;
        if (valor > 1 && valor < 15) {

            this.valor = valor;

        } else {

            System.out.println("Valor incorrecto");
            this.valor = rng.nextInt(12) + 2;

        }

    }

    public Palos getTipo() {

        return tipo;

    }

    public void setTipo(Palos tipo) {

        this.tipo = tipo;

    }

    public int getValor() {

        return valor;

    }

    public void setValor(int valor) {

        this.valor = valor;

    }

    private char getLetra() {

        switch (valor) {

            case 11:
                return 'J';

            case 12:
                return 'Q';

            case 13:
                return 'K';

            default://As
                return 'A';

        }

    }

    private char paloRedux() {

        switch (tipo) {

            case PICAS:
                return 'P';

            case ROMBOS: //Rombos = Diamantes
                return 'D';

            case TREBOLES:
                return 'T';

            default://Corazones
                return 'C';

        }

    }

    public String cartaRedux() {//Nos servirá para el ejercicio 3

        return ((valor >= 2 && valor <= 10) ? (valor + "") : ((char) this.getLetra() + ""))
                + paloRedux();

    }

    @Override
    public String toString() {

        return "Carta{" + "palo=" + tipo + ", valor="
                + ((valor >= 2 && valor <= 10) ? valor : ((char) this.getLetra() + "")) + '}';

    }

}
