package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Clase abstracta que se encarga de
 */
abstract class Actor(velocidad: Int, direccion: Direccion, posicionInicial: Posicion) : Objeto(posicionInicial) {

    var velocidad : Int = 0

    enum class Direccion {
        ARRIBA, ABAJO, DERECHA, IZQUIERDA
    }

    lateinit var direccion: Direccion

    /**
     * Funcion abstracta que varia segun la implementación de cada actor
     * que se encarga de calcular la posicion del Actor en función de la velocidad y la dirección,
     * y otros parametros propios a la clase hija.
     */
    abstract fun mover(): Posicion

    abstract override fun tratarColision()
}