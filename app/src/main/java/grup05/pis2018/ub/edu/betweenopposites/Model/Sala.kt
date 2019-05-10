package grup05.pis2018.ub.edu.betweenopposites.Model

abstract class Sala(idSala: Int, matrixSala: Array<Array<Objeto?>>) {

    //Teine un identificador dentro del Nivel
    var idSala: Int = idSala

    //Define una matriz de objetos donde mantiene la información de la sala y to*o lo que contiene (Objetos).
    // Crea inicialmente una matriz de nulls
    var matrixSala: Array<Array<Objeto?>> = Array<Array<Objeto?>>(10, { Array(20, { null }) })

    //Tiene un array de orbes que deben aparecer en la sala en el momento de crearse
    var orbes: ArrayList<Orbe> = ArrayList<Orbe>()

    //Tiene un acceso fàcil a las puertas que contiene
    var puertas: ArrayList<Puerta> = ArrayList()

    // Tiene un acceso facil a los objetos que puedes encontrar en la sala
    var objetos: ArrayList<Objeto> = ArrayList()

    /**
     * Devuelve un objeto guardado en una posición en concreto de la sala.
     */
    fun getObjetofromSala(i: Int, j: Int): Objeto {
        val objeto: Objeto = matrixSala[i][j]!!
        return objeto
    }

    /**
     * Posiciona un Objeto creado en la matriz.
     */
    fun setObjetoinSala(objeto: Objeto) {
    }

}