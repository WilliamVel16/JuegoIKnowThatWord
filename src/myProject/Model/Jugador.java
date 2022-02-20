package myProject.Model;

import javax.swing.*;
import myProject.Utils.FileManager;
import java.util.HashMap;

/**
 * This class is used for check inside a .txt file if the user has already
 * played before or not, also
 * to add the new players to the .txt file.
 * @autor William Velasco - 2042577 william.velasco@correounivalle.edu.co
 * @version v.1.0.0 date:08/02/2022
 */
public class Jugador {
    //Path Jugadores ESTÁTICO
    public static final String PATHJUGADORES = "src/myProject/files/jugadoresGuardados.txt";
    //Atributos
    private String nombreJugador;
    private int nivel = 1;
    /*Constructor*/
    public Jugador() {
    }
    /*Métodos*/

    /**
     * @bref Verifica si un jugador ya ha estado en el juego.
     * @param nombreJugador
     * @return
     */
    public Boolean getSiJugo(String nombreJugador) {
        FileManager MyManager = FileManager.getInstance(); //Obtener instancia única de la clase FileManager
        HashMap<String, Integer> Players = new HashMap<>();//Jugadores del Archivo
        Players = MyManager.getPlayers();
        if (Players.get(nombreJugador) != null) {
            this.nivel = Players.get(nombreJugador);
            return true;
        } else {
            
            return false;
        }
    }

    /**
     * @brief Solicita datos al usuario
     */
    public void pedirDatos() {
        String userNameInput = JOptionPane.showInputDialog("Ingrese su nombre: ");
        this.nombreJugador = userNameInput;
    }

    /*Getters and Setters*/
    public String getNombre() {
        return nombreJugador;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

}
