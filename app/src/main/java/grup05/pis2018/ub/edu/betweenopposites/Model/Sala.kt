package grup05.pis2018.ub.edu.betweenopposites.Model

abstract class Sala(id_sala: Int, matrixSala: Array<Array<Objeto?>>) {

    // Mantiene un identificador de la sala en el nivel
    var id_sala: Int = id_sala

    //Define una matriz de objetos donde mantiene la información de la sala y to*o lo que contiene (Objetos).
    // Crea inicialmente una matriz de nulls
    var matrixSala: Array<Array<Objeto?>> = matrixSala

    //Tiene un array de orbes que deben aparecer en la sala en el momento de crearse
    var orbes: ArrayList<Orbe> = ArrayList<Orbe>()

    // Tiene un array de puertas para acceder mas facilmente a estas
    var puertas: ArrayList<Puerta> = ArrayList<Puerta>()

    // Tiene un array de objetos para acceder a estos
    var objetos: ArrayList<Objeto> = ArrayList<Objeto>()


    /**
     * Devuelve un objeto guardado en una posición en concreto de la sala.
     */
    fun getObjetofromSala(i: Int, j: Int): Objeto {
        val objeto: Objeto = matrixSala[i][j]!!
        return objeto
    }

    // MÉTODOS PARA AÑADIR COSAS A LA SALA

    //      AÑADIR PUERTAS
    /**
     * Añade una puerta al array de puertas
     */
    fun anadirPuerta(puerta: Puerta) {
        puertas.add(puerta)
    }

    /**
     * Coloca multiples puertas
     */
    fun anadirPuertas(lista_puertas: ArrayList<Puerta>) {
        this.puertas.addAll(lista_puertas)
    }

    //      AÑADIR ORBES
    /**
     * Añade una puerta al array de puertas
     */
    fun anadirOrbe(orbe: Orbe) {
        this.orbes.add(orbe)
    }

    /**
     * Coloca multiples puertas
     */
    fun anadirOrbes(lista_objetos: ArrayList<Orbe>) {
        this.orbes.addAll(lista_objetos)
    }

    //      AÑADIR OBJETOS
    /**
     * Añade una puerta al array de puertas
     */
    fun anadirObjeto(objeto: Orbe) {
        this.objetos.add(objeto)
    }

    /**
     * Coloca multiples puertas
     */
    fun anadirObjetos(lista_objetos: ArrayList<Objeto>) {
        this.objetos.addAll(lista_objetos)
    }

}