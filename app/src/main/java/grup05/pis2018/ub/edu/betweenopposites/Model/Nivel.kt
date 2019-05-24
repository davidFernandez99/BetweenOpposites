package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Esta clase se encarga de mantener la estructura de las salas en un nivel
 * También organiza las salas de forma que se comuniquen entre si.
 * Parámetros:
 *  arraySalas: Lista de salas que contiene en Nivel.
 *  matrizSalas: matriz que se ha generado para la creación de ese nivel, contiene la estructura que une a las salas.
 */
class Nivel(listaSalas: ArrayList<Sala>, matrizSalas: Array<Array<String>>? = null) {

    //Contiene una variable que le identifica como nivel
    var id_nivel: Int = 0

    //Mantiene las salas de ese nivel
    var arraySalas: ArrayList<Sala> =listaSalas

    // MÉTODOS GETTERS Y SETTERS
    /**
     * Devuelve una sala dentro de este nivel
     */
    fun getSala(id_sala:Int): Sala{

        try {
            if(id_sala == -1){
                return arraySalas.last()
            }
            val sala: Sala? = arraySalas.get(id_sala - 1)
            if (sala != null) {
                return sala
            } else {
                throw Exception("Sala = null")
            }
        } catch (e: Exception) {
            throw e
        }
    }


    // MÉTODOS PARA TESTING
    /**
     * Imprime la información de las salas que contiene
     */
    fun printNivel(){
        var i: Int=0
        for (sala: Sala in arraySalas){
            print("\n\n**************** SALA ${i+1} ***********************\n\n")
            sala.printMatriz()
            i++
        }
    }
}