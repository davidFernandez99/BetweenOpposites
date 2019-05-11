package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Se encarga de generar objetos que se piden desde otras clases
 */
object FactoryObjetos {

    /**
     * Crea un muro a partir de los parametros pasados
     */
    fun crearMuro(posicionMatriz: Posicion): Muro {
        return Muro(Dimension.muro.height, Dimension.muro.width, posicionMatriz)
    }

    /**
     * Crea una puerta a partir de los parametros pasados
     */
    fun crearPuerta(posicionMatriz: Posicion): Puerta {
        return Puerta(Dimension.puerta.height, Dimension.puerta.width, posicionMatriz)
    }

    /**
     * Crea un suelo a partir de los parametros dados.
     */
    fun crarSuelo(posicionMatriz: Posicion): Suelo {
        return Suelo(Dimension.suelo.height, Dimension.suelo.width, posicionMatriz)
    }

}
