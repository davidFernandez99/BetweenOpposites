package grup05.pis2018.ub.edu.betweenopposites.Model

abstract class Sala(matrixSala: Array<Array<Objeto?>>) {

    //Define una matriz de objetos donde mantiene la información de la sala y to*o lo que contiene (Objetos).
    // Crea inicialmente una matriz de nulls
    var matrixSala: Array<Array<Objeto?>> = Array<Array<Objeto?>>(20, { Array(10, { null }) })

    //Tiene un array de orbes que deben aparecer en la sala en el momento de crearse
    var arrayObjetos: ArrayList<Orbe> = ArrayList<Orbe>()

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
        when (objeto) {

        }
    }

}