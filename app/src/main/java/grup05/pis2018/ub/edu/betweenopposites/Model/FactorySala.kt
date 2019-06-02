package grup05.pis2018.ub.edu.betweenopposites.Model

import android.app.ActivityOptions
import android.content.Context
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.lang.Math.pow
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue
import kotlin.math.ceil
import kotlin.math.sqrt

/**
 * Se encarga de crear las diferentes salas que hay en el juego
 */
object FactorySala {



    // MÉTODOS PARA LA CREACIÓN DE LAS SALAS

    /**
     * Crea una sala basica con algoritmos inteligentes a partir de la dificultad exigida.
     * Parametros:
     *  numero_puertas -> Numero de puertas que tiene la sala que queremos crear
     *  numero_de_caminos -> Numero de caminos que queremos crear dentro de la sala,
     *                          está relacionado con el numero de suelos que hay en la sala
     */
    fun crearSalaBasicaAutomaticamente(dificultad: Int, id_sala: Int, numero_puertas: Int): SalaBasica {

        /**
         * Queremos un algoritmo que cree una metrix para una SalaBasicaa, donde haya un camino,
         * minimamente, desde un punto inicial a un punto final a una mínima diatància entre ellos.
         * Para ello también imolemento una forma de hacer backtracking para poder, si és necesario,
         * volver a una posicion anterior que permita seguir creando el camino.
         * El backtracking se harà a una posición random de la pila, no sera retrocediendo al anterior,
         * ya que poede hacer que la sala sea muy lineal.
         * Como no queremos que La sala se llene de suelos, sino que haya muros en ella, paramos de crear el camino
         * en caso de que hayamos llegado a llenar 3/4 partes de la sala.
         */


        // Crearé una matriz que me indica las posiciones . Después podré assignarla a la sala,
        // para que no sea necesario que vuelva a realizarla. La inicializo con todas las posibles posiciones
        var list_available: ArrayList<List<Int>> = ArrayList()
        for (j in (0..(Dimension.sala.height_en_bloques-1))){
            for (i in (0..(Dimension.sala.width_en_bloques-1))){
                list_available.add(listOf(i,j))
            }
        }


        // Inicializo una matriz con las dimensiones de la sala. DOnde todas las posiciones son nulas excepto los bordes que son Muros
        // Para ello he hecho una funcón auxiliar que evalue cada caso y lo haga automaticamente.
        var matrix: Array<Array<Objeto?>> = Array<Array<Objeto?>>(Dimension.sala.height_en_bloques, { i -> crearArray2D(i, list_available) })


        // Inicializo una pila que usaremos para el backtracking. Nos permite volver a posiciones anteriores por las cuales ya hemos pasado.
        var pila: Stack<List<Int>> = Stack()

        /**
         * Escojo dos posiciones libres dentro de la matriz de forma random, pero que se encuantren a una cierta distancia,
         * para ello he decidido que las dos posiciones se encuantren en los tercios extremos de la sala.
         */

        var posicion_inicial: List<Int> = list_available.random()
        var posicion_final: List<Int> = list_available.random()
        while(distancia(posicion_inicial,posicion_final)<sqrt(list_available.size.toDouble()).toInt()) {
            posicion_inicial = list_available.random()
            posicion_final= list_available.random()
        }

        /**
         * Creo los caminos que can desde el incio al final
         */
        // Conocemos cuantos suelos hemos generado
        var suelos_generados: Int=0

        // ¿Hemos encontrado la posicion final?
        var found: Boolean=false

        // Definimos la posicion actual como la inicial
        var posicion_actual: List<Int> = posicion_inicial

        //Suelos a generar
        var suelos_a_generar=list_available.size*3/4
        var suelos_maximos=list_available.size*4/5

        // Mientras no hayamos enconterado el final y el numero de suelos generados sea pequeño, seguimos haciendo camino.
        while((!found || (suelos_generados < suelos_a_generar)) || !(suelos_generados>=suelos_maximos)){

            // Hago que esta posición sea un suelo en el caso de que no lo sea ya
            if(matrix[posicion_actual[1]][posicion_actual[0]] == null){
                matrix[posicion_actual[1]][posicion_actual[0]]= FactoryObjetos.crearSuelo(Posicion(x_sala=posicion_actual[0],y_sala=posicion_actual[1]))

                // Meto la posicion escogida en la pila
                pila.push(posicion_actual)

                // He generado un suelo
                suelos_generados++
            }

            // Escojo entre las posibles posiciones siguientes una.
            var posicion_escogida: List<Int>?= posiblesPosiciones(posicion_actual, list_available,matrix)

            // Si la posicion no tiene salidas, hacemos que la actal vuelva a alguna anterior
            if(posicion_escogida==null) {
                // Backtracking
                posicion_actual = pila.random()

            // Si la posicion és la final, lo apuntamos
            }else if(posicion_escogida== posicion_final){
                found=true
                posicion_actual=posicion_escogida

            }else{
                posicion_actual=posicion_escogida
            }

        }

        /**
         * Una vez creados los caminos creo muros en todos esos sitios donde se ha quedado en null
         */
        for (j in (0..(Dimension.sala.height_en_bloques-1))){
            for (i in (0..(Dimension.sala.width_en_bloques-1))){
                if(matrix[j][i]== null){
                    matrix[j][i]= FactoryObjetos.crearMuro(Posicion(x_sala=i,y_sala=j))
                    list_available.remove(listOf(i,j))
                }
            }
        }

        var salaBasica: SalaBasica = SalaBasica(id_sala,matrix)
        /**
         * Creo el numero de puertas que me piden y las meto en la sala
         */
        var posicion: List<Int>
        var posicion_anterior: List<Int> = listOf()
        for(i in (0..(numero_puertas-1))){

            if(i>0){
                posicion = list_available.random()
                while(distancia(posicion, posicion_anterior)<sqrt(list_available.size.toDouble()).toInt()/3){
                    posicion = list_available.random()
                }
            }else{
                posicion = list_available.random()
            }

            salaBasica.anadirPuerta(Puerta(Dimension.puerta.height,Dimension.puerta.width,Posicion(x_sala=posicion[0],y_sala=posicion[1])))
            posicion_anterior= posicion
        }

        /**
         * Tras haber creado los caminos, puertas y la sala le pongo los objetos que quiero meter en ella
         */

        // Creo los spawn_point y compruevo que tod0 este correctamente.
        salaBasica.syncPuertasyMuros()
        salaBasica.createAvalibleMatrix()

        list_available=salaBasica.getPosicionesLibres()

        //Meto los objetos
        salaBasica.anadirOrbes(FactoryObjetos.generarOrbes(dificultad,list_available))
        salaBasica.anadirObjetos(FactoryObjetos.generarObjetos(dificultad,list_available))

        return salaBasica
    }

    /**
     * Calcula la distancia que existe entre dos posiciones pasadas por paràmetro
     */
    private fun distancia(posicion_inicial: List<Int>, posicion_final: List<Int>): Int {

        //Calculo la distancia que hay entre las dos posiciones
        // Para ello calculo el vector en valor absoluto entre los dos puntos y devuelvo su modulo
        val vector: ArrayList<Double> = ArrayList(listOf(0.0,0.0))
        // Caclulo la x
        vector[0] = ceil((posicion_inicial[0]-posicion_final[0]).toDouble()).absoluteValue
        vector[1] = ceil((posicion_inicial[1]-posicion_final[1]).toDouble()).absoluteValue

        // Calculamos el modulo del vector
        val modulo: Int = (sqrt((pow(vector[0],2.0)+ pow(vector[1],2.0)))).toInt()

        return modulo
    }


    /**
     * Inicializa los valores de la matriz de dimension 1.
     */
    private fun muro(j: Int, i: Int, list_available: ArrayList<List<Int>>) : Muro?{
        if(j== 0 || j == Dimension.sala.height_en_bloques-1 || i == 0 || i == Dimension.sala.width_en_bloques-1){
            list_available.remove(listOf(i,j))
            return FactoryObjetos.crearMuro(Posicion(x_sala=i ,y_sala =j ))
        }else{
            return null
        }
    }

    /**
     * Inicialia los valores de la matriz de segunda dimension.
     */
    private fun crearArray2D(j: Int, list_available: ArrayList<List<Int>>): Array<Objeto?>{
        return Array(Dimension.sala.width_en_bloques, {i -> muro(j,i, list_available) })
    }

    /**
     * Devuelve las posiciones permitidas cerca colindantes a la posición pasada por parámetro.
     */
    private fun posiblesPosiciones(posicion_actual:List<Int>,list_available: ArrayList<List<Int>>, matrix: Array<Array<Objeto?>>): List<Int>?{
        // Extraigo cuales son las posiciones alrededor de la puerta
        val i: Int = posicion_actual[0]
        val j: Int = posicion_actual[1]
        //Cojo solo aquellas que tienen la posibilidad de ocuparse
        var posibles: ArrayList<List<Int>> = ArrayList<List<Int>>()

        try {
            val iz : List<Int> = listOf(i,j-1)
            if(iz in list_available && matrix[iz[1]][iz[0]] == null){ posibles.add(iz)}
        } catch (e: Exception) {
        }

        try {
            val der : List<Int> = listOf(i,j+1)
            if(der in list_available && matrix[der[1]][der[0]] == null){ posibles.add(der)}
        } catch (e: Exception) {
        }

        try {
            val arriba : List<Int> = listOf(i+1,j)
            if(arriba in list_available && matrix[arriba[1]][arriba[0]]==null){ posibles.add(arriba) }
        } catch (e: Exception) {
        }

        try {
            val abajo : List<Int> = listOf(i-1,j)
            if(abajo in list_available && matrix[abajo[1]][abajo[0]] == null){ posibles.add(abajo)}
        } catch (e: Exception) {
        }

        // Escojo una posición random para el spawn
        if(posibles.size==0){
            return null
        }
        val opcion: List<Int> = posibles.random()
        return opcion
    }


    /**
     * Crea una SalaBasica a partir de una matriz escrita en un archivo .TXT
     */
    fun crearSalaBasicadesdeTXT(dificultad: Int, id_sala: Int, filename: String, contexto: Context): SalaBasica {

        //Defino la matriz donde se van a cargar los datos
        var matrix: Array<Array<Objeto?>> = Array<Array<Objeto?>>(Dimension.sala.height_en_bloques, { Array(Dimension.sala.width_en_bloques, { null }) })

        val fitxer: BufferedReader?

        try {
            // Defino y obtengo el fichero a través de el nombre
            fitxer = contexto.assets.open(filename).bufferedReader()

            // Cargo las linias en una variable
            val lineas_leidas: List<String> = fitxer.readLines()

            //Leo las lineas para crear la matriz
            for (j: Int in lineas_leidas.indices) {
                var linea: String = lineas_leidas[j]
                //Para cada linea la descifro y creo un array de Objetos
                var linia_objetos: Array<Objeto?> = descifrarLinia(linea, j)
                matrix[j] = linia_objetos
            }

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            throw Exception(
                String.format(
                    "El fitxer %s no existeix", filename
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }catch(e: IOException){
            e.printStackTrace()
        }

        //Creo la sala
        var salaBasica: SalaBasica=SalaBasica(id_sala, matrix)

        // Consigo una lista de posiciones donde es posible colocar objetos u orbes
        var avaliblePositions: ArrayList<List<Int>> = salaBasica.getPosicionesLibres()

        //Creo las listas de orbes y objetos y posteriormente los añado a la sala
        var orbes:ArrayList<Orbe> = FactoryObjetos.generarOrbes(dificultad,avaliblePositions)
        var objetos: ArrayList<Objeto> = FactoryObjetos.generarObjetos(dificultad,avaliblePositions)

        //Lo añado a la sala
        salaBasica.anadirOrbes(orbes)
        salaBasica.anadirObjetos(objetos)

        // Print de la sala
        //salaBasica.printMatriz()

        //Devuelvo la sala
        return salaBasica
    }

    /**
     * Crea una SalaEspecial a partir de una matrix definida en un archivo .TXT
     * A diferencia de la creacion de la SalaFinal esta no tiene enemigos pero siempre una Maquina en la
     * posición que passamos por paràmetro.
     */

    fun crearSalaEspecial(id_sala: Int, filename: String, contexto: Context): SalaEspecial {

        //Defino la matriz donde se van a cargar los datos
        var matrix: Array<Array<Objeto?>> = Array<Array<Objeto?>>(Dimension.sala.height_en_bloques, { Array(Dimension.sala.width_en_bloques, { null }) })

        var fitxer: BufferedReader?

        try {
            // Defino y obtengo el fichero a través de el nombre
            fitxer = contexto.assets.open(filename).bufferedReader()

            // Cargo las linias en una variable
            val lineas_leidas: List<String> = fitxer.readLines()

            //Leo las lineas para crear la matriz
            for (j: Int in lineas_leidas.indices) {
                var linea: String = lineas_leidas[j]
                //Para cada linea la descifro y creo un array de Objetos
                var linia_objetos: Array<Objeto?> = descifrarLinia(linea, j)
                matrix[j] = linia_objetos
            }

        } catch (e: FileNotFoundException) {
            throw IllegalArgumentException(
                String.format(
                    "El fitxer %s no existeix", filename
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        //Devolvemos la sala

        var salaEspecial: SalaEspecial=SalaEspecial(id_sala, matrix)

        // Print de la sala
        //salaEspecial.printMatriz()

        return salaEspecial
    }

    /**
     * Crea una SalaFinal a partir de una matriz definida en un archivo .TXT
     */
    fun crearSalaFinal(dificultad:Int,id_sala: Int, filename: String,contexto:Context): SalaFinal {

        //Defino la matriz donde se van a cargar los datos
        var matrix: Array<Array<Objeto?>> = Array<Array<Objeto?>>(Dimension.sala.height_en_bloques, { Array(Dimension.sala.width_en_bloques, { null }) })

        var fitxer: BufferedReader?

        try {
            // Defino y obtengo el fichero a través de el nombre
            fitxer = contexto.assets.open(filename).bufferedReader()

            // Cargo las linias en una variable
            val lineas_leidas: List<String> = fitxer.readLines()

            //Leo las lineas para crear la matriz
            for (j: Int in lineas_leidas.indices) {
                var linea: String = lineas_leidas[j]
                //Para cada linea la descifro y creo un array de Objetos
                var linia_objetos: Array<Objeto?> = descifrarLinia(linea, j)
                matrix[j] = linia_objetos
            }

        } catch (e: FileNotFoundException) {
            throw IllegalArgumentException(
                String.format(
                    "El fitxer %s no existeix", filename
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Devolvemos la sala
        val salaFinal: SalaFinal= SalaFinal(id_sala, matrix)

        // Consigo una lista de posiciones donde es posible colocar objetos u orbes
        var avaliblePositions: ArrayList<List<Int>> = salaFinal.getPosicionesLibres()

        //Creo las listas de orbes y objetos y posteriormente los añado a la sala
        var orbes:ArrayList<Orbe> = FactoryObjetos.generarOrbes(dificultad,avaliblePositions)
        var objetos: ArrayList<Objeto> = FactoryObjetos.generarObjetos(dificultad,avaliblePositions)

        //Lo añado a la sala
        salaFinal.anadirOrbes(orbes)
        salaFinal.anadirObjetos(objetos)

        // Print de la sala
        //salaFinal.printMatriz()

        return salaFinal
    }


    // MÉTODOS DE SOPORTE:


    /**
     *  Mètodo para leer la matriz
     */
    //TODO EXTRAER EL METODO PARA LA LECTURA DE LA MATRIZ

    /**
     * Método para descifrar una linia de Strings en una linia de objetos
     */
    private fun descifrarLinia(linea: String, j: Int): Array<Objeto?> {
        //Defino el array de Objeto
        var array_objetos: Array<Objeto?>
        //Defino antes una lista donde meta todos los objetos
        var lista_objetos: ArrayList<Objeto> = ArrayList()

        //Cargo un array dividido
        val arr = linea.split(Descifrar.separacion.char.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        //Leo cara caracter de forma ordenada para crear un objeto a partir de este
        for (i: Int in arr.indices) {
            //Añadimos a las lista de objetos los objetos
            lista_objetos.add(descifrarCaracter(arr[i], Posicion(x_sala = i, y_sala = j)))
        }
        // Paso los valores de la lista al array
        array_objetos = Array(lista_objetos.size, { null })

        //Lo cargo al array
        for (i in lista_objetos.indices) {
            array_objetos[i] = lista_objetos[i]
        }

        return array_objetos


    }

    /**
     * Método que descifra un caracter de la linia
     */
    private fun descifrarCaracter(char: String, posicionMatriz: Posicion): Objeto {
        when (char) {
            Descifrar.muro.char -> return FactoryObjetos.crearMuro(posicionMatriz)
            Descifrar.suelo.char -> return FactoryObjetos.crearSuelo(posicionMatriz)
            Descifrar.puerta.char -> return FactoryObjetos.crearPuerta(posicionMatriz)
            else -> throw Exception("Hay un char en la matriz que no se ha podido identificar")
        }

    }

}