package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Se encarga de generar objetos que se piden desde otras clases
 */
object FactoryObjetos {


    // MÉTODOS PRINCIPALES QUE SE ENCARGAN DE GENERAR LAS LISTAS DE OBJETOS.


    /**
     * Se encarga de generar los objetos (Orbes, trampas, sumadroes y multiplicadores)
     * Tiene en cuanta la dificultad de la sala donde pertenecen estos objetos
     * y las posiciones libres
     */
    fun generarObjetos(valor_dificultad: Int, avaliblePositions: List<List<Int>>): ArrayList<Objeto> {
        // Para empezar traducimos el valor de la dificultad a la clase Dificultad
        val dificultad : Dificultad = getDificultad(valor_dificultad)

        //Generamos los diferentes objetos en función de la dificultad y los metemos en posiciones posibles
        // TODO TODO TODO
    }


    // MÉTODOS PARA GENERAR LOS DIFERENTES OBJETOS

    /**
     * Crea un muro a partir de los parametros pasados
     */
    fun crearMuro(posicionMatriz: Posicion): Muro {
        return Muro(Dimension.muro.height, Dimension.muro.width, posicionMatriz)
    }

    /**
     * Crea una puerta a partir de los parametros pasados
     */
    fun crearPuerta(posicionMatriz: Posicion): Puerta {
        return Puerta(Dimension.puerta.height, Dimension.puerta.width, posicionMatriz)
    }

    /**
     * Crea un suelo a partir de los parametros dados.
     */
    fun crarSuelo(posicionMatriz: Posicion): Suelo {
        return Suelo(Dimension.suelo.height, Dimension.suelo.width, posicionMatriz)
    }

    /**
     * Crea un orbe automaticamente y le proporciona una posición posible en la sala
     * Parametros:
     *      avaliblePositions: lista de posibles posiciones donde se puede colocar el orbe
     */
    fun crearOrbe(dificultad: Dificultad, avaliblePositions: List<List<Int>>): Orbe {

        // Escojo una posición en la que pueda poner al orbe
        val posicion: Posicion = escogerPosicion(avaliblePositions)

        // La velocidad del orbe dependerá de la dificultad del nivel
        return Orbe(Bando.values()[(0..(Bando.values().size)).random()],Dimension.orbe.height,Dimension.orbe.width,dificultad.velocidad_orbes,Direccion.ABAJO,posicion)
    }

    /**
     * Se encarga de crear una trampa
     */
    fun crearTrampa(avaliblePositions: List<List<Int>>): Trampa{
        // Escoge una posición vacia
        val posicion :Posicion = escogerPosicion(avaliblePositions)

        //Creo la trampa
        return Trampa(Dimension.trampa.height,Dimension.trampa.width,posicion)
    }

    /**
     * Genera un sumador
     */
    fun crearSumador(dificultad: Dificultad,avaliblePositions: List<List<Int>>): Sumador{
        // Escoge una posición
        val posicion :Posicion = escogerPosicion(avaliblePositions)
        // Genero un valor del sumador
        val valor: Int = dificultad.rango_valores_sumador.random()
        // Crea el sumador
        return Sumador(valor,Dimension.sumador.height,Dimension.sumador.width,posicion)
    }

    /**
     * Genera un multiplicador
     */
    fun crearMultiplicador(dificultad: Dificultad,avaliblePositions: List<List<Int>>): Multiplicador{
        // Escoge una posición
        val posicion :Posicion = escogerPosicion(avaliblePositions)
        // Genero el valor del multiplicador
        val valor: Int = dificultad.rango_valores_sumador.random()
        // Crea el multiplicador
        return Multiplicador(valor,Dimension.multiplicador.height,Dimension.multiplicador.width,posicion)
    }


    // MÉTODOS DE SOPORTE
    /**
     * A partir de una lista de pares (x,y) esta función escoge una de estas parejas y devuelve un objeto Posicion
     */
    private fun escogerPosicion(lista: List<List<Int>>): Posicion {

        // Escojo un numero random en la longitud de la lista
        // y extraigo los valores x,y para crear el objeto
        val randomInt: Int = (0..(lista.size - 1)).random()

        //Cojo lo que hay en esa posición
        val x_sala = lista[randomInt][0]
        val y_sala = lista[randomInt][1]

        //Devuelvo la posicion en la matriz a través de un objeto
        return Posicion(x_sala = x_sala, y_sala = y_sala)

    }

    /**
     * Dependiendo del nivel en el que estemos, se encontrará en una dificultad mas alta o mas baja,
     * Rangos:
     *      1-3 -> Dificultad baja
     *      4-6 -> Dificultad media
     *      7-8 -> Dificultad alta
     */
    private fun getDificultad(dificultad: Int): Dificultad {
        when (dificultad) {
            1, 2, 3 -> return Dificultad.baja
            4, 5, 6 -> return Dificultad.media
            7, 8 -> return Dificultad.alta
            else -> throw Exception("EL valor entrado como dificultad no es posible")
        }
    }

}
