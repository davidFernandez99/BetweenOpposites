package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Esta clase se encarga de mantener la estructura de las salas en un nivel
 * También organiza las salas de forma que se comuniquen entre si.
 * Parámetros:
 *  arraySalas: Lista de salas que contiene en Nivel.
 *  matrizSalas: matriz que se ha generado para la creación de ese nivel, contiene la estructura que une a las salas.
 */
class Nivel(listaSalas: ArrayList<Sala>, matrizSalas: Array<Array<String>>? = null) {

    //TODO
    //Contiene una variable que le identifica como nivel
    var numNivel: Int = 0

    //Mantiene las salas de ese nivel
    lateinit var arraySalas: ArrayList<Sala>

    /**
     * Se encarga de crear la estructura entre las salas, cual se comunica con que otra ...
     */
    fun organizarSala() {
        TODO("not implemented yet")
    }
}