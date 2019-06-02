package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Vibrator
import android.support.v4.content.ContextCompat.getSystemService
import grup05.pis2018.ub.edu.betweenopposites.Game.GameEngine
import android.content.Context.VIBRATOR_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import grup05.pis2018.ub.edu.betweenopposites.Game.DisplayThread


abstract class Sala(id_sala: Int, matrixSala: Array<Array<Objeto?>>) {

    // Mantiene un identificador de la sala en el nivel
    var id_sala: Int = id_sala

    //Define una matriz de objetos donde mantiene la información de la sala y to*o lo que contiene (Objetos).
    // Crea inicialmente una matriz de nulls
    protected var matrixSala: Array<Array<Objeto?>> = matrixSala

    //Tiene un array de orbes que deben aparecer en la sala en el momento de crearse
    private var orbes: ArrayList<Orbe> = ArrayList<Orbe>()

    // Tiene un array de puertas para acceder mas facilmente a estas
    protected var puertas: ArrayList<Puerta> = ArrayList<Puerta>()

    // Tiene un array de muros para acceder facilmente a estos
    protected var muros: ArrayList<Muro> = ArrayList<Muro>()

    // Tiene un array de objetos para acceder a estos
    protected var objetos: ArrayList<Objeto> = ArrayList<Objeto>()

    //Mantiene una matriz que nos dice si esa posición està ocupada
    private var matrixAvalible: Array<Array<Boolean>> = Array<Array<Boolean>>(matrixSala.size,{Array(matrixSala[0].size,{false})})

    private var ultimaTrampaColisionada:Trampa?=null

    //Variables para comprobar funciones maquina en sala especial
    var comprobar_colision_maquina:Boolean=false
    var comprobar_opcion_corecta:Boolean=false
    var objetoMaquina:ObjetoActivable?=null
    var maquinaSala:Maquina?=null

    init{

        // Al crear la sala se crea la matriz de lugares desocupados
        createAvalibleMatrix()

        // Al crear la sala tenemos que meter las puertas de la matriz en la lista y los muros en otra
        // También hay que crear el spawnpoint de las puertas
        syncPuertasyMuros()
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
            matrixSala[objeto.posicion.y_sala][objeto.posicion.x_sala] = objeto
            bloquearPosicion(objeto.posicion.y_sala,objeto.posicion.x_sala)

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
        matrixSala[puerta.posicion.y_sala][puerta.posicion.x_sala] = puerta
        bloquearPosicion(puerta.posicion.y_sala,puerta.posicion.x_sala)

        // Mezcla el contenido de las puertas para que el orden en la lista varie
        //puertas.shuffle()

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
     * Define cual es la salida de una de las puertas que tiene la sala.
     */
    fun definirSalida(posicion_puerta_en_lista: Int, id_nivel:Int,id_sala:Int,puertaDestino:Puerta?){
        val id_puerta: Int = posicion_puerta_en_lista-1
        if(id_puerta>=puertas.size || id_puerta<0){
            throw Exception("La id de la puerta no es correcta. La id de la primera puerta = 1")
        }else{
            // Asigno como salida de la puerta la puerta pasada por parametro
            val this_puerta = puertas[id_puerta]
            this_puerta.setDestino(puertaDestino,id_sala,id_nivel)
        }
    }

    /**
     * Devuelve la puerta colocada en esa posición de la lista
     */
    fun getPuerta(id_puerta: Int): Puerta{

        try {
            if(id_puerta<-1 || id_puerta>puertas.size){
                throw ArrayIndexOutOfBoundsException("La posición no es correcta")
            }else if(id_puerta==0 ){
                return puertas[0]
            }

            if(id_puerta == -1){
                return puertas.last()
            }

            val puerta: Puerta? = puertas.get(id_puerta - 1)
            if (puerta != null) {
                return puerta
            } else {
                throw Exception("Puerta = null")
            }
        } catch (e: Exception) {
            throw e
        }
    }


    /**
     * Se encarga de sincronizar las puertas que hay en matriz con las que hay en la lista
     */
    fun syncPuertasyMuros(){
        // Recogemos las puertas que hay en la matriz

        var puertasRecogidas: ArrayList<Puerta> = ArrayList()
        var murosRecogidos: ArrayList<Muro> = ArrayList()

        for (j in 0..matrixSala.size-1) {
            for (i in 0..matrixSala[j].size-1) {
                var objetoRecogido : Objeto= matrixSala[j][i]!!
                if (objetoRecogido is Puerta) {
                    puertasRecogidas.add(objetoRecogido)
                }else if(objetoRecogido is Muro){
                    murosRecogidos.add(objetoRecogido)
                }
            }
        }

        // PASAMOS LA LISTA DE PUERTAS RECOGIDAS A LA LISTA
        this.puertas= puertasRecogidas
        // PASAMOS LA LISTA DE MUROS RECOGIDOS A LA LISTA
        this.muros= murosRecogidos

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

            // Escojo una posición random para el spawn
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
        bloquearPosicion(orbe.posicion.y_sala,orbe.posicion.x_sala)
    }

    /**
     * Coloca multiples puertas
     */
    fun anadirOrbes(lista_orbes: ArrayList<Orbe>) {
        for (orbe in lista_orbes){
            anadirOrbe(orbe)
        }
    }

    //      AÑADIR OBJETOS
    /**
     * Añade una puerta al array de puertas
     */
    fun anadirObjeto(objeto: Objeto) {
        this.objetos.add(objeto)
        bloquearPosicion(objeto.posicion.y_sala,objeto.posicion.x_sala)
    }

    /**
     * Coloca multiples puertas
     */
    fun anadirObjetos(lista_objetos: ArrayList<Objeto>) {
        for (objeto in lista_objetos){
            anadirObjeto(objeto)
        }
    }

    // MÉTODOS PARA ELIMINAR OBJETOS, ORBES Y PUERTAS

    // ELIMINACION DE OBJETOS

    /**
     * ELimina un objeto de array que lo almazena
     */
    fun eliminarObjeto(objeto: Objeto) {
        this.objetos.remove(objeto)
        liberarPosicion(objeto.posicion.y_sala,objeto.posicion.x_sala)
    }

    /**
     * Elimina múltiples objetos del array de objetos
     */
    fun eliminarObjetos(objetos: ArrayList<Objeto>) {
        for (objeto in objetos){
            eliminarObjeto(objeto)
        }
    }


    // ELIMINACION DE ORBES
    /**
     * Elimina un orbe de la lista
     */
    fun eliminarOrbe(orbe: Orbe) {
        this.orbes.remove(orbe)
        liberarPosicion(orbe.posicion.y_sala,orbe.posicion.x_sala)
    }

    /**
     * Elimina multiples orbes de la lista
     */
    fun eliminarOrbes(orbes: ArrayList<Orbe>) {
        for (orbe in orbes){
            eliminarObjeto(orbe)
        }
    }

    // ELIMINAR PUERTAS
    /**
     * Elimina una puerta de la sala
     */
    fun eliminarPuerta(puerta: Puerta) {
        // Elimino la puerta de la lista
        this.puertas.remove(puerta)
        // La elimino de la matriz, la substituyo por un muro
        matrixSala[puerta.posicion.y_sala][puerta.posicion.x_sala] =
            Muro(Dimension.muro.height, Dimension.muro.width, puerta.posicion)

    }

    // MÉTODOS PARA TRATAR CON LA MATRIZ AVALIBLE

    /**
     * Genera una la matriz de espacios libres a partir de matrixSala
     */
    fun createAvalibleMatrix(){
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
                    list.add(listOf(i,j))
                }
            }
        }
        return list
    }


    // MÉTODOS PARA TESTING
    /**
     * Hace un print de la matriz
     */
    fun printMatriz(): ArrayList<String> {

        // Pintamos la id de la sala
        println("SALA ${this.id_sala}")


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

        // Hago un print también de los objetos, orbes y puertas que contiene
        print("\n\n**** ORBES ****\n\n")
        for(orbe: Orbe in orbes){
            orbe.printOrbe()
        }

        print("\n\n**** OBJETOS ****\n\n")
        for(objeto: Objeto in objetos){
            objeto.printObjeto()
        }

        print("\n\n**** PUERTAS ****\n\n")
        for(puerta: Puerta in puertas){
            puerta.printPuerta()
        }

        return arrayStrings
    }


    open fun update(fps:Long){
        var colision:Boolean=false
        // Dibujamos objetos en la sala
        Lobo.instance!!.mover(fps)
        // Dibujamos los orbes
        for (orbe: Orbe in this.orbes) {
            orbe.mover(fps)
            orbe.canviarDireccion()
            if(orbe.es_visible==true){
                orbe.detectarColision(Lobo.instance)
            }
        }

        for (objeto: Objeto in this.objetos) {
            if(objeto is Trampa){
                if(Lobo.instance.vulnerable==true){
                    colision=objeto.detectarColision(Lobo.instance)
                    if(colision==true){
                        ultimaTrampaColisionada=objeto
                        Lobo.instance.vulnerable=false
                    }
                }

            }

            else{
                if(objeto.es_visible==true){
                    objeto.detectarColision(Lobo.instance)
                }
            }
        }

        if(ultimaTrampaColisionada!= null){
            Lobo.instance.setVulnerabilidad(ultimaTrampaColisionada!!)
        }
        for (muro:Muro in this.muros){
            muro.detectarColision(Lobo.instance)
            for (orbe:Orbe in this.orbes){
                muro.detectarColision(orbe)
            }
        }

        for (puerta:Puerta in this.puertas){
            puerta.detectarColision(Lobo.instance)
            for (orbe:Orbe in this.orbes){
                puerta.detectarColision(orbe)
            }
        }

    }
    // MÉTODOS PARA EL DIBUJADO DE LA SALA
    /**
     * Método para dibujar todos los objetos contenidos en la sala
     */
    open fun draw(canvas:Canvas,contexto: Context){
        //Dibujamos muros y suelos
        for (j in 0..matrixSala.size-1) {
            for (i in 0..matrixSala[j].size-1) {
                var objeto:Objeto=getObjetofromSala(i,j)
                if(objeto is Muro){
                    objeto.draw(canvas, GameEngine.bitmapMuro!!)
                }
                if(objeto is Suelo){
                    objeto.draw(canvas, GameEngine.bitmapSuelo!!)
                }

            }
        }
        // Dibujamos objetos en la sala
        for (objeto: Objeto in this.objetos) {
            if(objeto is Trampa){
                objeto.draw(canvas, GameEngine.bitmapTrampa!!)
            }
            else{
                if(objeto.es_visible==true){
                    if(objeto is Multiplicador){
                        objeto.draw(canvas, GameEngine.bitmapMultiplicador!!)
                    }
                    if(objeto is Sumador){
                        objeto.draw(canvas, GameEngine.bitmapSumador!!)
                    }

                }
            }
        }


        // Dibujamos los orbes
        for (orbe: Orbe in this.orbes) {
            if(orbe.es_visible==true){
                if(orbe.bando==Bando.Blanco){
                    orbe.draw(canvas, GameEngine.bitmapOrbeBlanco!!)
                }
                else{
                    orbe.draw(canvas, GameEngine.bitmapOrbeNegro!!)
                }

            }
        }
        for(puerta:Puerta in this.puertas){
            puerta.draw(canvas, GameEngine.bitmapPuerta!!)
        }
        if(Lobo.instance.bando==Bando.Neutro && Lobo.instance.es_visible==true){
            Lobo.instance.draw(canvas, GameEngine.bitmapLoboNeutro!!)
        }
        else if(Lobo.instance.bando==Bando.Negro&& Lobo.instance.es_visible==true){
            Lobo.instance.draw(canvas, GameEngine.bitmapLoboOscuro!!)
        }
        else if(Lobo.instance.bando==Bando.Blanco&& Lobo.instance.es_visible==true){
            Lobo.instance.draw(canvas, GameEngine.bitmapLoboLuz!!)
        }
        if(Lobo.instance.bando==Bando.Neutro&& Lobo.instance.es_visible==false){
            Lobo.instance.draw(canvas, GameEngine.bitmapLoboNeutroInv!!)
        }
        else if(Lobo.instance.bando==Bando.Negro&& Lobo.instance.es_visible==false){
            Lobo.instance.draw(canvas, GameEngine.bitmapLoboOscuroInv!!)
        }
        else if(Lobo.instance.bando==Bando.Blanco&& Lobo.instance.es_visible==false){
            Lobo.instance.draw(canvas, GameEngine.bitmapLoboLuzInv!!)
        }

    }


}