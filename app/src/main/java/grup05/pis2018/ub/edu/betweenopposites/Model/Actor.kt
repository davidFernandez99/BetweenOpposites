package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Clase abstracta que se encarga de determinar Objetos que se mueven como el Lobo y los Orbes
 */
abstract class Actor(
    height: Float,
    width: Float,
    velocidad: Float,
    direccion: Direccion,
    posicion: Posicion
) : Objeto(height, width, posicion) {

    var velocidad: Float = velocidad; //Velocidad en la que se moverá el actor
    var direccion: Direccion = direccion //Dirección de movimiento del actor

    enum class Direccion {  //Enum para determinar la dirección en la que se moverá el actor
        ARRIBA, ABAJO, DERECHA, IZQUIERDA, PARADO
    }

    enum class Bando { //Enum para determinar el bando de un actor
        Blanco, Negro
    }

    /**
     * Funcion abstracta que varia segun la implementación de cada actor
     * que se encarga de calcular la posicion del Actor en función de la velocidad y la dirección,
     * y otros parametros propios a la clase hija.
     */
    abstract fun mover(fps: Long) //Método para mover el actor según la dirección que tiene

    abstract override fun tratarColision(objeto: Objeto) //Método para tratar la colision entre un actor y otro obejto

}