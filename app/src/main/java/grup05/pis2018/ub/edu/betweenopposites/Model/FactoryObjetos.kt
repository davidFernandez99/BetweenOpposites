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

    /**
     * Crea un orbe automaticamente y le proporciona una posición posible en la sala
     * Parametros:
     *      avaliblePositions: lista de posibles posiciones donde se puede colocar el orbe
     */
    fun crearOrbe(dificultad: Dificultad, avaliblePositions: List<List<Int>>): Orbe {

        // Escojo una posición en la que pueda poner al orbe
        val posicion: Posicion = escogerPosicion(avaliblePositions)

        // La velocidad del orbe dependerá de la dificultad del nivel
        return Orbe()
    }

    /**
     * A partir de una lista de pares (x,y) esta función escoge una de estas parejas y devuelve un objeto Posicion
     */
    fun escogerPosicion(lista: List<List<Int>>): Posicion {

        // Escojo un numero random en la longitud de la lista
        // y extraigo los valores x,y para crear el objeto
        val randomInt: Int = (0..(lista.size - 1)).random()

        //Cojo lo que hay en esa posición
        val x_sala = lista[randomInt][0]
        val y_sala = lista[randomInt][1]

        //Devuelvo la posicion en la matriz a través de un objeto
        return Posicion(x_sala = x_sala, y_sala = y_sala)

    }

    /**
     * Dependiendo del nivel en el que estemos, se encontrará en una dificultad mas alta o mas baja,
     * Rangos:
     *      1-3 -> Dificultad baja
     *      4-6 -> Dificultad media
     *      7-8 -> Dificultad alta
     */
    fun getDificultad(dificultad: Int): Dificultad {
        when (dificultad) {
            1, 2, 3 -> return Dificultad.baja
            4, 5, 6 -> return Dificultad.media
            7, 8 -> return Dificultad.alta
            else -> throw Exception("EL valor entrado como dificultad no es posible")
        }
    }

}
