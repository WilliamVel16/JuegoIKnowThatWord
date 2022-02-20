package myProject.Controller;

import javax.swing.*;
import myProject.Model.Diccionario;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used for create the methods that give logic to the game, how,
 * for example, to keep track of
 * errors, also of successes.
 * 
 * @autor William Velasco - 2042577 william.velasco@correounivalle.edu.co
 * @version v.1.0.0 date:08/02/2022
 */
public class ControlThatWord {
    private Diccionario diccionario;
    private ArrayList<String> palabrasMemorizar = new ArrayList<String>();
    private ArrayList<String> totalPalabras = new ArrayList<String>();
    int i = 0;
    public static Timer timerAdivinar = null;
    public static Timer timerEvaluar = null;
    /* HashMap Guarda Porcentaje de Aciertos de cada nivel */
    HashMap<Integer, Integer> Porcentajes = new HashMap<>();
    private int totalAciertos = 0;

    public ControlThatWord() {
        diccionario = new Diccionario();
    }

    String nuevaPalabra;

    /**
     * Muestra las palabras que el usuario debe memorizar
     * @param numeroPalabras
     */
    public ArrayList<String> mostrarPalabras(int numeroPalabras) {
        this.palabrasMemorizar = new ArrayList<>();

        while (numeroPalabras > 0) {
            nuevaPalabra = diccionario.getPalabra();
            while (existePalabra(nuevaPalabra, this.palabrasMemorizar)) {
                nuevaPalabra = diccionario.getPalabra();
            }
            palabrasMemorizar.add(nuevaPalabra);// Agregar Palabra
            numeroPalabras--;
        }

        //Ya tengo todas las palabras a MOSTRAR:
        for (String s : palabrasMemorizar) {
            JOptionPane.showMessageDialog(null, "" + "\n" + s, " ¡DEBES RECORDAR!",
                    JOptionPane.INFORMATION_MESSAGE);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return palabrasMemorizar;
    }

    /**
     * @bref Evaluar palabras
     * @param doblePalabras
     */
    public void mostrarEvaluacion(int doblePalabras) {
        this.totalPalabras = new ArrayList<>();
        for (int i = 0; i < palabrasMemorizar.size(); i++) { // Agregando palabras mostradas
            totalPalabras.add(palabrasMemorizar.get(i));
        }
        doblePalabras = doblePalabras - palabrasMemorizar.size();
        while (doblePalabras > 0) {
            nuevaPalabra = diccionario.getPalabra();
            while (existePalabra(nuevaPalabra, this.totalPalabras)) {
                nuevaPalabra = diccionario.getPalabra();
            }
            totalPalabras.add(nuevaPalabra);// Agregar Palabra
            doblePalabras--;
        }

        //Desordenar la Lista
        Diccionario dictonary = new Diccionario();
        totalPalabras = dictonary.getRandomListMostrar(totalPalabras);
        for (String s : totalPalabras) {
            int respuesta = JOptionPane.showConfirmDialog(null, s,"Responde", JOptionPane.YES_NO_OPTION);
            try {
                if(procesarRespuesta(respuesta, s)){
                    totalAciertos ++;
                }
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @bref Estudia si el usuario pasa al siguiente nivel
     * @param reglaNivel
     */
    public Boolean calificarEvaluacion(int reglaNivel) {
        /*Restauramos las Listas*/
        palabrasMemorizar = new ArrayList<>();
        totalPalabras = new ArrayList<>();
        if (totalAciertos >= reglaNivel) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Procesa la respuesta del usuario al responder si la palabra estaba o no
     * @param response
     * @param palabra
     * @return boolean
     */
    public boolean procesarRespuesta(int response, String palabra) {
        boolean flag = false;
        for (String s : palabrasMemorizar) {
            if (s.equals(palabra)) {
                flag = true;
            }
            if (s.equals(palabra) && response == 0) {
                return true;
            }
        }
        /* La palabra no está */
        if (flag == false && response == 1) {
            return true;
        }
        return false;
    }

    /**
     * Verifica si existe la palabra que se le muestra al usuario
     * @param palabra
     * @param lstAnalizar
     * @return boolean
     */
    public boolean existePalabra(String palabra, ArrayList<String> lstAnalizar) {
        for (int i = 0; i < lstAnalizar.size(); i++) {
            if (lstAnalizar.get(i).equals(palabra)) {
                return true;
            }
        }
        return false;
    }
}
