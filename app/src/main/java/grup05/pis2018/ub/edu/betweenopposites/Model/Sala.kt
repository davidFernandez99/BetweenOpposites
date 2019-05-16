package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Canvas

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


    // MÉTODOS PPARA COGER Y MODIFICAR POSICIONES EN LA MATRIZ
    /**
     * Devuelve un objeto guardado en una posición en concreto de la sala.
     */
    fun getObjetofromSala(i: Int, j: Int): Objeto {
        val objeto: Objeto = matrixSala[i][j]!!
        return objeto
    }

    /**
     * Introduce un objeto a la sala.
     */
    fun setObjetoinSala(objeto: Objeto){
        // Distribuye las diferentes formas de guardar un objeto
        if(objeto is Muro || objeto is Suelo) {
            matrixSala[i][j] = objeto
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
        // Sincroniza las puertas que hay en la matriz con las se la lista
        syncPuertas()
    }

    /**
     * Se encarga de sincronizar las puertas que hay en matriz con las que hay en la lista
     */
    private fun syncPuertas() : Boolean{
        // Recogemos las puertas que hay en la matriz
        var diferentes: Boolean = false

        var puertasRecogidas : ArrayList<Puerta> = ArrayList()

        for(j in 0..matrixSala.size){
            for(i in 0..matrixSala[j].size){
                var objetoRecogido = matrixSala[i][j]
                if(objetoRecogido is Puerta){
                    puertasRecogidas.add(objetoRecogido)
                }
            }
        }

        //Compruevo que todas las puertas recogidas se encuentren en la lista y viceversa
        // EN EL CASO DE QUE NO COINCIDAN NOS QUEDAMOS CON LAS QUE HAY EN LA LISTA DE PUERTAS
        if(puertasRecogidas.size != this.puertas.size){
            diferentes=true
        }else{
            // Compruevo que sean las mismas puertas
            for (puerta:Puerta in this.puertas) {
                if (!(puerta in puertasRecogidas)) {
                    diferentes = true
                }
            }
        }

        // Si son difernetes substituimos las que hay en la lista por las de la matriz
        if(diferentes){
            //Substituyo las puertas que tenia por suelos
            for(puerta in puertasRecogidas){
                setObjetoinSala(puerta.posicion.x_sala,puerta.posicion.y_sala,Suelo(Dimension.suelo.height,Dimension.suelo.width,puerta.posicion))
            }
            // Pongo las que tengo en la lista a la matriz
            for(puerta in this.puertas){
                setObjetoinSala(puerta.posicion.x_sala,puerta.posicion.y_sala,puerta)
            }
        }

        //Devolvemos si ha habido cambios
        return diferentes
    }

    /**
     * Coloca multiples puertas
     */
    fun anadirPuertas(lista_puertas: ArrayList<Puerta>) {
        //No solo se añade a la lista sino también a la matriz
        this.puertas.addAll(lista_puertas)
        // Sincroniza el numero de puertas en matriz con las de la lista
        syncPuertas()
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

    // MÉTODOS PARA ELIMINAR OBJETOS, ORBES Y PUERTAS

    // ELIMINACION DE OBJETOS

    /**
     * ELimina un objeto de array que lo almazena
     */
    fun eliminarObjeto(objeto: Objeto){
        this.objetos.remove(objeto)
    }

    /**
     * Elimina múltiples objetos del array de objetos
     */
    fun eliminarObjetos(objetos:ArrayList<Objeto>){
        this.objetos.removeAll(objetos)
    }


    // ELIMINACION DE ORBES
    /**
     * Elimina un orbe de la lista
     */
    fun eliminarOrbe(orbe: Orbe){
        this.orbes.remove(orbe)
    }

    /**
     * Elimina multiples orbes de la lista
     */
    fun eliminarOrbes(orbes:ArrayList<Orbe>){
        this.orbes.removeAll(orbes)
    }

    // ELIMINAR PUERTAS
    /**
     * Elimina una puerta de la sala
     */


    /**
     * Método para dibujar todos los objetos contenidos en la sala
     */
    fun draw(canvas: Canvas){
        // Dibujamos suelos muros y puertas
        for(j in 0..matrixSala.size){
            for(i in 0..matrixSala[j].size){
                getObjetofromSala(i,j).draw(canvas)
            }
        }
        // Dibujamos objetos en la sala
        for(objeto: Objeto in this.objetos){
            objeto.draw(canvas)
        }
        // Dibujamos los orbes
        for(orbe: Orbe in this.orbes){
            orbe.draw(canvas)
        }
    }
}