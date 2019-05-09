package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Bitmap

/**
 * Clase abstracta que se encarga de
 */
abstract class Actor(
    height: Float,
    width: Float,
    velocidad: Float,
    direccion: Direccion,
    posicionInicial: Posicion,
    posicion: Posicion)
    : Objeto(height, width, posicionInicial, posicion) {

    var velocidad: Float = velocidad;
    var direccion: Direccion = direccion

    enum class Direccion {
        ARRIBA, ABAJO, DERECHA, IZQUIERDA
    }
    enum class Bando{
        Blanco,Negro
    }
    //Dirección en la que se mueve


    /**
     * Funcion abstracta que varia segun la implementación de cada actor
     * que se encarga de calcular la posicion del Actor en función de la velocidad y la dirección,
     * y otros parametros propios a la clase hija.
     */
    abstract fun mover(fps:Long)

    abstract override fun tratarColision(objeto: Objeto)

}