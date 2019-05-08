package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Esta clase se encarga de mantener la estructura de las salas en un nivel
 * También organiza las salas de forma que se comuniquen entre si.
 */
class Nivel {
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

//TODO: MIRAR COMO SE DEBE ORGANIZAR LAS SALAS EN LOS NIVELES, QUE TIPO DE ESTRUCTURA FORMAN Y COMO TRATAR CON ELLA