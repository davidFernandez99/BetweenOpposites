package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Se encarga de crear las diferentes salas que hay en el juego
 */
object FactorySala {

    /**
     * Crea una sala basica con algoritmos inteligentes a partir de la dificultad exigida.
     */
    fun crearSalaBasica(dificultad: Int): Sala {
        //TODO: Crear sala Basica
    }

    /**
     * Crea una SalaBasica a partir de una matriz escrita en un archivo .TXT
     */
    fun crearSalaBasicadesdeTXT(dificultad: Int, filename: String) {

    }

    /**
     * Crea una SalaEspecial a partir de una matrix definida en un archivo .TXT
     * A diferencia de la creacion de la SalaFinal esta no tiene enemigos pero siempre una Maquina en la
     * posición que passamos por paràmetro.
     */
    fun crearSalaEspecial(posicion_maquina: Posicion, filename: String) {

    }

    /**
     * Crea una SalaFinal a partir de una matriz definida en un archivo .TXT
     */
    fun crearSalaFinal(filename: String) {

    }

    /**
     * Este mètodo interpreta el archivo .TXT y lo transforma en una matriz de objetos que és la sala
     */
    fun interpretarMatrizEnTXT(filename: String) {

    }
}