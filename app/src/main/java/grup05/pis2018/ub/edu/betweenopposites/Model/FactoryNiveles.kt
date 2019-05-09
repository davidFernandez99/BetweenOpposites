package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Se encarga de generar niveles,
 * Es una clase estatica.
 */
object FactoryNiveles {

    // DImensiones de la matriz creada para la estructura del nivel
    val DIMENSION_X : Int = 6
    val DIMENSION_Y : Int = 6
    /**
     * Método que crea un nivel y lo devuelve, en función de la dificultad, numero de salas en nivel, numero de salas especiales
     * La dificultad del nivel se identifica con el numero de nivel.
     */
    fun crearNivel(dificultad: Int, num__salas_basicas: Int, num_salas_especiales: Int): Nivel {

        /**
         * Para empezar genera un camino en una matriz que representa las relaciones entre salas
         * Crea una matriz 6x6 de Sala donde de forma aleatoria crea las relaciones que hay entre las salas del nivel.
         */
        var matrizEstructuraSalas: Array<Array<Sala?>> = Array<Array<Sala?>>(6,{ Array(6,{ null}) })

        /**
         * Genero una primera sala y la coloco de forma random en la matriz. TODO
         */
        //Se utilizan para accedér a posiciones en concreto de la matriz
        var i:Int
        var j: Int

        // Genero una posicion random en la matriz
        val random_x : Int = (0..DIMENSION_X-1).random()
        val random_y : Int = (0..DIMENSION_Y-1).random()

        /**
         * Pido la sala con los valores correspondientes y la cargo en la posición.
         */
        matrizEstructuraSalas[random_x][random_y]= FactorySala.crearSalaBasica(dificultad)

        /**
         * Incluye las salas especiales y por último la sala final.
         */
        return Nivel()
    }

    /**
     * Mètodo para crear niveles a partir de SalasBasicas y especiales creadas en ficheros TXT.
     *
     */

}