package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Clase que contiene principalmente un Array<Nivel> donde contiene todos los niveles de una partida.
 * Se encarga de tratar con los niveles almacenados y llamar al creador de niveles para obtenerlos.
 */
class ConjuntoNiveles(
    NUMERO_NIVELES_POR_JUEGO: Int = 8,
    NUMERO_SALAS_BASICAS_POR_NIVEL: Int = 10,
    NUMERO_DE_SALAS_ESPECIALES_POR_NIVEL: Int = 1
) {

    // CONSTANTES:
    public val NUMERO_NIVELES_POR_JUEGO: Int = NUMERO_NIVELES_POR_JUEGO
    public val NUMERO_DE_SALAS_BASICAS_POR_NIVEL: Int = NUMERO_SALAS_BASICAS_POR_NIVEL
    public val NUMERO_DE_SALAS_ESPECIALES_POR_NIVEL: Int = NUMERO_DE_SALAS_ESPECIALES_POR_NIVEL

    //Array de Nivel
    private var arrayNiveles: Array<Nivel?> = Array(NUMERO_NIVELES_POR_JUEGO, { null })

    /**
     * AL crear el objeto de esta clase se generan directamente los niveles,
     *
     */
    init {

        // Pedimos la generación de todos los niveles y los guardamos en los niveles
        for (i in 1..NUMERO_NIVELES_POR_JUEGO) {
            //Pedimos el nivel y lo metemos en el array.
            setNivel(
                i,
                FactoryNiveles.crearNivelfromTXT(
                    i,
                    NUMERO_DE_SALAS_BASICAS_POR_NIVEL,
                    NUMERO_DE_SALAS_ESPECIALES_POR_NIVEL,
                    Plantilla.salaBasica.listaPlantillas,
                    Plantilla.salaEspecial.listaPlantillas,
                    Plantilla.salaFinal.listaPlantillas
                )
            )
        }
    }

    /**
     * Getter de niveles almacenados. Donde la id_nivel del primer nivel seria 1.
     */
    fun getNivel(id_nivel: Int): Nivel {

        try {
            if(id_nivel == -1){
                return arrayNiveles.last()!!
            }

            val nivel: Nivel? = arrayNiveles.get(id_nivel - 1)
            if (nivel != null) {
                return nivel
            } else {
                throw Exception("Nivel = null")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    /**
     * Setter de un Nivel dentro del Array.
     * EL primer nivel seria el 1.
     */
    fun setNivel(posicion: Int, nivel: Nivel?) {

        if (nivel != null) {
            arrayNiveles.set(posicion - 1, nivel)
        } else {
            throw Exception("nivel = null")
        }
    }
}