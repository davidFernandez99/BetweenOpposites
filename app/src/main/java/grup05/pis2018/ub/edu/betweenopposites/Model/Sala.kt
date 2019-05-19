package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Canvas

abstract class Sala(id_sala: Int, matrixSala: Array<Array<Objeto?>>) {

    // Mantiene un identificador de la sala en el nivel
    var id_sala: Int = id_sala

    //Define una matriz de objetos donde mantiene la información de la sala y to*o lo que contiene (Objetos).
    // Crea inicialmente una matriz de nulls
    private var matrixSala: Array<Array<Objeto?>> = matrixSala

    //Tiene un array de orbes que deben aparecer en la sala en el momento de crearse
    private var orbes: ArrayList<Orbe> = ArrayList<Orbe>()

    // Tiene un array de puertas para acceder mas facilmente a estas
    private var puertas: ArrayList<Puerta> = ArrayList<Puerta>()

    // Tiene un array de objetos para acceder a estos
    private var objetos: ArrayList<Objeto> = ArrayList<Objeto>()

    //Mantiene una matriz que nos dice si esa posición està ocupada
    private var matrixAvalible: Array<Array<Boolean>> = Array<Array<Boolean>>(matrixSala.size,{Array(matrixSala[0].size,{false})})

    init{

        // Al crear la sala se crea la matriz de lugares desocupados
        createAvalibleMatrix()
        // Al crear la sala tenemos que meter las puertas de la matriz en la lista
        // También hay que crear el spawnpoint de las puertas
        syncPuertas()
    }

    // MÉTODOS PPARA COGER Y MODIFICAR POSICIONES EN LA MATRIZ
    /**
     * Devuelve un objeto guardado en una posición en concreto de la sala.
     */
    fun getObjetofromSala(i: Int, j: Int): Objeto {
        val objeto: Objeto = matrixSala[j][i]!!
        return objeto
    }

    /**
     * Introduce un objeto a la sala.
     */
    fun setObjetoinSala(objeto: Objeto) {
        // Distribuye las diferentes formas de guardar un objeto
        if (objeto is Muro || objeto is Suelo) {
            matrixSala[objeto.posicion.x_sala][objeto.posicion.y_sala] = objeto
            bloquearPosicion(objeto.posicion.x_sala,objeto.posicion.y_sala)

        } else if (objeto is Puerta) {
            anadirPuerta(objeto)
        } else if (objeto is Orbe) {
            anadirOrbe(objeto)
        } else {
            anadirObjeto(objeto)
        }
    }

    // MÉTODOS PARA AÑADIR COSAS A LA SALA

    //      AÑADIR PUERTAS
    /**
     * Añade una puerta al array de puertas
     */
    fun anadirPuerta(puerta: Puerta) {
        // No solo se añada a la lista sino también a la matriz
        puertas.add(puerta)
        // Pongo la puerta en la matriz
        matrixSala[puerta.posicion.x_sala][puerta.posicion.y_sala] = puerta
        bloquearPosicion(puerta.posicion.x_sala,puerta.posicion.y_sala)

    }

    /**
     * Coloca multiples puertas
     */
    fun anadirPuertas(lista_puertas: ArrayList<Puerta>) {
        // Coloco todas las puertas
        for (puerta: Puerta in lista_puertas) {
            anadirPuerta(puerta)
        }
    }

    /**
     * Se encarga de sincronizar las puertas que hay en matriz con las que hay en la lista
     */
    private fun syncPuertas(){
        // Recogemos las puertas que hay en la matriz

        var puertasRecogidas: ArrayList<Puerta> = ArrayList()

        for (j in 0..matrixSala.size-1) {
            for (i in 0..matrixSala[j].size-1) {
                var objetoRecogido : Objeto= matrixSala[j][i]!!
                if (objetoRecogido is Puerta) {
                    puertasRecogidas.add(objetoRecogido)
                }
            }
        }

        // PASAMOS LA LISTA DE PUERTAS RECOGIDAS A LA LISTA
        this.puertas= puertasRecogidas

        // CREO LOS SPAWNPOINT DE LAS PUERTAS
        for(puerta: Puerta in puertas){

            // Extraigo cuales son las posiciones alrededor de la puerta
            val i: Int = puerta.posicion.x_sala
            val j: Int = puerta.posicion.y_sala

            //Cojo solo aquellas que tienen la posibilidad de ocuparse
            var spawnsPosibles: ArrayList<List<Int>> = ArrayList<List<Int>>()

            try {
                val iz : List<Int> = listOf(j,i-1)
                if(matrixAvalible[iz[0]][iz[1]]){ spawnsPosibles.add(iz)}
            } catch (e: Exception) {
            }


            try {
                val der : List<Int> = listOf(j,i+1)
                if(matrixAvalible[der[0]][der[1]]){ spawnsPosibles.add(der)}
            } catch (e: Exception) {
            }

            try {
                val arriba : List<Int> = listOf(j+1,i)
                if(matrixAvalible[arriba[0]][arriba[1]]){ spawnsPosibles.add(arriba)}
            } catch (e: Exception) {
            }

            try {
                val abajo : List<Int> = listOf(j-1,i)
                if(matrixAvalible[abajo[0]][abajo[1]]){ spawnsPosibles.add(abajo)}
            } catch (e: Exception) {
            }

            // Escojo una posición random para el spawn // TODO SERIA MEJOR ESCOGER AQUELLA QUE SE ENCUENTRE MAS AL CENTRO DE LA SALA
            val opcion: List<Int> = spawnsPosibles.random()
            // Creo la posición y la pongo como spawnpoint, y bloqueo esta posición en la sala
            puerta.spawn_point= Posicion(x_sala=opcion[1],y_sala=opcion[0])
            bloquearPosicion(opcion[0],opcion[1])
        }

    }

    //      AÑADIR ORBES
    /**
     * Añade una puerta al array de puertas
     */
    fun anadirOrbe(orbe: Orbe) {
        this.orbes.add(orbe)
        bloquearPosicion(orbe.posicion.x_sala,orbe.posicion.y_sala)
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
    fun anadirObjeto(objeto: Objeto) {
        this.objetos.add(objeto)
        bloquearPosicion(objeto.posicion.x_sala,objeto.posicion.y_sala)
    }

    /**
     * Coloca multiples puertas
     */
    fun anadirObjetos(lista_objetos: ArrayList<Objeto>) {
        this.objetos.addAll(lista_objetos)
    }

    // MÉTODOS PARA ELIMINAR OBJETOS, ORBES Y PUERTAS

    // ELIMINACION DE OBJETOS

    /**
     * ELimina un objeto de array que lo almazena
     */
    fun eliminarObjeto(objeto: Objeto) {
        this.objetos.remove(objeto)
        liberarPosicion(objeto.posicion.x_sala,objeto.posicion.y_sala)
    }

    /**
     * Elimina múltiples objetos del array de objetos
     */
    fun eliminarObjetos(objetos: ArrayList<Objeto>) {
        this.objetos.removeAll(objetos)
    }


    // ELIMINACION DE ORBES
    /**
     * Elimina un orbe de la lista
     */
    fun eliminarOrbe(orbe: Orbe) {
        this.orbes.remove(orbe)
        liberarPosicion(orbe.posicion.x_sala,orbe.posicion.y_sala)
    }

    /**
     * Elimina multiples orbes de la lista
     */
    fun eliminarOrbes(orbes: ArrayList<Orbe>) {
        this.orbes.removeAll(orbes)
    }

    // ELIMINAR PUERTAS
    /**
     * Elimina una puerta de la sala
     */
    fun eliminarPuerta(puerta: Puerta) {
        // Elimino la puerta de la lista
        this.puertas.remove(puerta)
        // La elimino de la matriz, la substituyo por un muro
        matrixSala[puerta.posicion.x_sala][puerta.posicion.y_sala] =
            Muro(Dimension.muro.height, Dimension.muro.width, puerta.posicion)
    }

    // MÉTODOS PARA TRATAR CON LA MATRIZ AVALIBLE

    /**
     * Genera una la matriz de espacios libres a partir de matrixSala
     */
    private fun createAvalibleMatrix(){
        for(j in (0..(matrixSala.size-1))){
            for(i in (0..(matrixSala[j].size-1))){
                // Si es un suelo es posible que esté libre
                if(matrixSala[j][i] is Suelo){
                    matrixAvalible[j][i] = true
                }
            }
        }
    }

    /**
     * Bloqua la posibilidad de colocar un objeto en esa posición de la matriz
     */
    private fun bloquearPosicion(j:Int,i:Int){
        matrixAvalible[j][i]= false
    }

    /**
     * Libera una posición de la matriz, para poder colocar objetos y orbes
     */
    private fun liberarPosicion(j:Int,i:Int){
        matrixAvalible[j][i]= true
    }


    /**
     * Devuelve en forma de lista las posiciones libres
     */
    fun getPosicionesLibres(): ArrayList<List<Int>>{
        // Creo la lista
        var list: ArrayList<List<Int>> = ArrayList<List<Int>>()
        // Busco las posiciones libres y las entro en la lista
        for(j in (0..(matrixAvalible.size-1))){
            for(i in (0..(matrixAvalible[j].size-1))){
                // Si es un suelo es posible que esté libre
                if(matrixAvalible[j][i]){
                    list.add(listOf(j,i))
                }
            }
        }
        return list
    }

    // MÉTODOS PARA EL DIBUJADO DE LA SALA
    /**
     * Método para dibujar todos los objetos contenidos en la sala
     */
    fun draw(canvas: Canvas) {
        // Dibujamos suelos muros y puertas
        for (j in 0..matrixSala.size) {
            for (i in 0..matrixSala[j].size) {
                //getObjetofromSala(i, j).draw(canvas)
            }
        }
        // Dibujamos objetos en la sala
        for (objeto: Objeto in this.objetos) {
            //objeto.draw(canvas)
        }
        // Dibujamos los orbes
        for (orbe: Orbe in this.orbes) {
            //orbe.draw(canvas)
        }
    }

    // MÉTODOS PARA TESTING
    /**
     * Hace un print de la matriz
     */
    fun printMatriz(): ArrayList<String> {
        var arrayStrings: ArrayList<String> = ArrayList()
        for (j in 0..(matrixSala.size - 1)) {
            for (i in 0..(matrixSala[j].size - 1)) {
                var objeto: Objeto = matrixSala[j][i]!!

                if (objeto is Puerta) {
                    print("P,")
                    arrayStrings.add("P,")
                } else if (objeto is Suelo) {
                    print("_,")
                    arrayStrings.add("_,")
                } else if (objeto is Muro) {
                    print("M,")
                    arrayStrings.add("M,")
                }
            }
            print("\n")
        }
        return arrayStrings
    }
}