package grup05.pis2018.ub.edu.betweenopposites.Model

import java.io.File
import java.io.FileNotFoundException

/**
 * Se encarga de crear las diferentes salas que hay en el juego
 */
object FactorySala {


    // MÉTODOS PARA LA CREACIÓN DE LAS SALAS

    /**
     * Crea una sala basica con algoritmos inteligentes a partir de la dificultad exigida.
     */
    /*
    fun crearSalaBasicaRandom(dificultad: Int): Sala {
        //TODO: Crear sala Basica *unimplemented*
    }
*/
    /**
     * Crea una SalaBasica a partir de una matriz escrita en un archivo .TXT
     */
    fun crearSalaBasicadesdeTXT(dificultad: Int, id_sala: Int, filename: String): SalaBasica {

        //Defino la matriz donde se van a cargar los datos
        var matrix: Array<Array<Objeto?>> = Array<Array<Objeto?>>(10, { Array(20, { null }) })

        val fitxer: File?

        try {
            // Defino y obtengo el fichero a través de el nombre
            fitxer = File(filename)

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

        //Devuelvo la sala
        return salaBasica
    }

    /**
     * Crea una SalaEspecial a partir de una matrix definida en un archivo .TXT
     * A diferencia de la creacion de la SalaFinal esta no tiene enemigos pero siempre una Maquina en la
     * posición que passamos por paràmetro.
     */

    fun crearSalaEspecial(id_sala: Int, filename: String): SalaEspecial {

        //Defino la matriz donde se van a cargar los datos
        var matrix: Array<Array<Objeto?>> = Array<Array<Objeto?>>(10, { Array(20, { null }) })

        var fitxer: File?

        try {
            // Defino y obtengo el fichero a través de el nombre
            fitxer = File(filename)

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
        return SalaEspecial(id_sala, matrix)
    }

    /**
     * Crea una SalaFinal a partir de una matriz definida en un archivo .TXT
     */
    fun crearSalaFinal(id_sala: Int, filename: String): SalaFinal {
        // TODO: CREAR SALA FINAL

        // Definimos la salaBasica para poder crearla al final de este método.
        val salaFinal: SalaFinal

        //Defino la matriz donde se van a cargar los datos
        var matrix: Array<Array<Objeto?>> = Array<Array<Objeto?>>(10, { Array(20, { null }) })

        var fitxer: File?

        try {
            // Defino y obtengo el fichero a través de el nombre
            fitxer = File(filename)

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
        return SalaFinal(id_sala, matrix)
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
            // TODO: PASAR LA POSICION EN MATRIZ PARA LA GENERACIÓN DE OBJETOS
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