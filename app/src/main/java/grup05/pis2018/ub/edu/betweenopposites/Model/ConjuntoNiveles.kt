package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Clase que contiene principalmente un Array<Nivel> donde contiene todos los niveles de una partida.
 * Se encarga de tratar con los niveles almacenados y llamar al creador de niveles para obtenerlos.
 */
class ConjuntoNiveles {

    // CONSTANTES:
    val NUMERO_NIVELES_POR_JUEGO: Int = 8
    val NUMERO_DE_SALAS_POR_NIVEL: Int = 10
    val NUMERO_DE_SALAS_ESPECIALES_POR_NIVEL = 1

    //Array de Nivel
    var arrayNiveles: Array<Nivel?> = Array(NUMERO_NIVELES_POR_JUEGO, { null })

    /**
     * Al crearse esta clase pide todos los niveles al generador de niveles.
     */
    fun onCreate() {

        // Pedimos la generación de todos los niveles y los guardamos en los niveles
        for (i in 0..9) {
            //Pedimos el nivel y lo metemos en el array.
            arrayNiveles[i + 1] =
                FactoryNiveles.crearNivel(i + 1, NUMERO_DE_SALAS_POR_NIVEL, NUMERO_DE_SALAS_ESPECIALES_POR_NIVEL)
        }
    }

    /**
     * Getter de niveles almacenados. Donde la idNivel del primer nivel seria 1.
     */
    fun getNivel(idNivel: Int): Nivel {

        val nivel: Nivel? = arrayNiveles[idNivel + 1]
        if (nivel != null) {
            return nivel
        } else {
            throw Exception("El nivel no se ha creado aún.")
        }
    }

    /**
     * Setter de un Nivel dentro del Array.
     * EL primer nivel seria el 1.
     */
    fun setNivel(posicion: Int, nivel: Nivel) {
        arrayNiveles.set(posicion - 1, nivel)
    }
}