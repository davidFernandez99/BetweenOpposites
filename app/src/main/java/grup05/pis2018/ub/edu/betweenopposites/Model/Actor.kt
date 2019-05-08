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
    posicion: Posicion,
    image: Bitmap?
) : Objeto(height, width, posicionInicial, posicion, image) {

    var velocidad: Float = 0f;

    enum class Direccion {
        ARRIBA, ABAJO, DERECHA, IZQUIERDA
    }

    //Dirección en la que se mueve
    lateinit var direccion: Direccion

    /**
     * Funcion abstracta que varia segun la implementación de cada actor
     * que se encarga de calcular la posicion del Actor en función de la velocidad y la dirección,
     * y otros parametros propios a la clase hija.
     */
    abstract fun mover(): Posicion

    abstract override fun tratarColision(objeto: Objeto)

}