package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Se encarga de generar niveles,
 * Es una clase estatica.
 */
object FactoryNiveles {

    // DImensiones de la matriz creada para la estructura del nivel
    val DIMENSION_X: Int = 6
    val DIMENSION_Y: Int = 6
    /**
     * Método que crea un nivel y lo devuelve, en función de la dificultad, numero de salas en nivel, numero de salas especiales
     * La dificultad del nivel se identifica con el numero de nivel.
     */
    /*
    fun crearNivel(dificultad: Int, num_salas_basicas: Int, num_salas_especiales: Int): Nivel {

        /**
         * Para empezar genera un camino en una matriz que representa las relaciones entre salas
         * Crea una matriz 6x6 de Sala donde de forma aleatoria crea las relaciones que hay entre las salas del nivel.
         * Tambien creamos ya la lista de salas que contendrá el Nivel en su creacion.
         */
        var matrizEstructuraSalas: Array<Array<Sala?>> = Array<Array<Sala?>>(6, { Array(6, { null }) })
        var listaSalas: ArrayList<Sala> = ArrayList()
        //Se utilizan para accedér a posiciones en concreto de la matriz
        var i: Int
        var j: Int

        // Genero una posicion random en la matriz, donde se colocará la sala inicial que creada
        val random_x: Int = (0..DIMENSION_X - 1).random()
        val random_y: Int = (0..DIMENSION_Y - 1).random()

        // Pido la salaBasica al generador con los valores correspondientes y la cargo en la posición.
        matrizEstructuraSalas[random_x][random_y] = FactorySala.crearSalaBasica(dificultad)

        //Utilizo una pila para poder acceder posteriormente a estas salas y unirlas entre ellas tras
        // haber generado la matriz TODO


        // Me muevo por la matriz para colocar el resto de salasBasicas TODO


        //Coloco la sala o salas especiales y la sala final. TODO


        //Hago un backtrace en la matriz a partir de la pila para relacionar las diferentes salas TODO


        //Genero el nivel a partir de la matriz estructural y la lista de salas que contiene todas las Salas. TODO

        return Nivel()
    }
*/
    /**
     * Mètodo para crear niveles a partir de SalasBasicas, especiales y finales interpretadas a partir de ficheros TXT.
     * Parametros:
     *  dificultad => nivel en el que se encuentra la sala
     *  num_salas_basicas => Numero de salas basicas que queremos en este nivel
     *  num_salas_especiales => Numero de salas especiales que queremos en este nivel
     *  plantillasSalasBasicas => Lista de nombres de los archivos txt donde estan definidas plantillas de salas basicas
     *  plantillasSalaEspecial => Lista de nombres de los archivos txt donde estan definidas plantillas de salas especiales
     *  plantillasSalaFinal => Lista de nombres de los archivos txt donde estan definidas plantillas de salas finales
     */
    fun crearNivelfromTXT(
        dificultad: Int,
        num_salas_basicas: Int,
        num_salas_especiales: Int,
        plantillasSalasBasicas: ArrayList<String>,
        plantillasSalaEspecial: ArrayList<String>,
        plantillasSalaFinal: ArrayList<String>
    ): Nivel {

        /**
         * Creamos una lista de salas donde metemos num_salas_basicas de SalasBasica, num_salas_especiales de SalaEspecial,
         * y una SalaFinal.
         * La estructura del nivel es en este caso lineal, por lo que solo puede haber 2 puertas por plantilla.
         * Es algo que se comprueva y en el caso de que no sea cierto se cancelarà la creación del Nivel y se notificarà con una excepción.
         */

        // Lista de salas que guardarà el nivel
        var listaSalas: ArrayList<Sala> = ArrayList()

        //Generamos el numero de salasBasicas pedido para el Nivel
        var salas_basicas_generadas = 0
        var salas_especiales_generadas = 0

        //Genero una pila con las posiciones donde se colocarán las salas especiales
        var queueEspeciales: ArrayList<Int> = ArrayList()
        // Genero los numeros random donde deben quedar las salas especiales
        for (i in 1..num_salas_especiales) {
            val total_salas = num_salas_basicas + num_salas_especiales
            queueEspeciales.add((1..total_salas).random())
        }


        //Guardamos las plantillas de salas basiocas restantes que nos quedan
        var plantillas_basicas_restantes = plantillasSalasBasicas.size
        var plantillas_especiales_restantes = plantillasSalaEspecial.size
        var plantillas_finales_restantes = plantillasSalaFinal.size

        //Si no hay ninguna plantilla no podremos crear las salas
        if (plantillas_basicas_restantes <= 0 || plantillas_especiales_restantes <= 0 || plantillas_finales_restantes <= 0) {
            throw Exception("No hay plantillas para generar salas de todos los tipos")
        }

        /**
         * Como quiero generar SalasEspeciales entre las salasBasicas, tengo que tener en cuenta que:
         *      Una salaEspecial no puede generarse la primera
         *      No quiero generar dos salasEspeciales seguidas
         */

        //Generamos las salas hasta que tengamos el numero necesario

        while (plantillas_basicas_restantes != 0 && salas_basicas_generadas <= num_salas_basicas) {
            //Generamos salas con las diferentes plantillas y las vamos guardadando en la lista
            listaSalas.add(
                FactorySala.crearSalaBasicadesdeTXT(
                    dificultad, salas_basicas_generadas + salas_especiales_generadas + 1,
                    plantillasSalasBasicas[salas_basicas_generadas]
                )
            )
            salas_basicas_generadas++
            plantillas_basicas_restantes--


            //Ahora ya puedo generar una salaEspecial, cogiendo una plantilla random.
            val siguiente_sala_a_generar = salas_basicas_generadas + salas_especiales_generadas + 1
            if (siguiente_sala_a_generar in queueEspeciales) {
                //Añado una sala especial
                listaSalas.add(
                    FactorySala.crearSalaEspecial(
                        salas_basicas_generadas + salas_especiales_generadas + 1,
                        plantillasSalaEspecial[(0..plantillasSalaEspecial.size - 1).random()]
                    )
                )

                salas_especiales_generadas++

            }

        }

        /**
         * En el caso de que no se hayan generado todas las salas que queremos, generaremos las que quedan
         * cogiendo una plantilla random de entre todas las que nos proporcionan.
         */

        if (salas_basicas_generadas != num_salas_basicas) {
            val salas_restantes = num_salas_basicas - salas_basicas_generadas

            //Generamos las salas restantes
            for (i in 0..salas_restantes - 1) {
                //Generamos las salas con una plantilla random
                listaSalas.add(
                    FactorySala.crearSalaBasicadesdeTXT(
                        dificultad, salas_basicas_generadas + salas_especiales_generadas + 1,
                        plantillasSalasBasicas[(0..plantillasSalasBasicas.size - 1).random()]
                    )
                )
                salas_basicas_generadas++

                //Si ya hemos generado almenos una sala podemos generar especiales
                //Ahora ya puedo generar una salaEspecial, cogiendo una plantilla random.
                val siguiente_sala_a_generar = salas_basicas_generadas + salas_especiales_generadas + 1
                if (siguiente_sala_a_generar in queueEspeciales) {
                    //Añado una sala especial
                    listaSalas.add(
                        FactorySala.crearSalaEspecial(
                            salas_basicas_generadas + salas_especiales_generadas + 1,
                            plantillasSalaEspecial[(0..plantillasSalaEspecial.size - 1).random()]
                        )
                    )
                    salas_especiales_generadas++
                }
            }
        }

        // Creamos la sala final a través de
        listaSalas.add(
            FactorySala.crearSalaFinal(
                salas_basicas_generadas + salas_especiales_generadas + 1,
                plantillasSalaFinal[(0..plantillasSalaFinal.size - 1).random()]
            )
        )

        // Anidamos las puertas de forma lineal en el orden en el que se han creado las salas.
        val cantidad_total_de_salas = listaSalas.size
        for (i in 0..cantidad_total_de_salas - 2) {
            // Para cada sala la lista menos la última
            // Conectamos su segunda puerta con la primera de la siguiente Sala
            if (i + 1 < listaSalas.size) {
                //listaSalas[i].puertas[-1].setDestino(listaSalas[i + 1].puertas[0]) //TODO
            }

        }
        /**
         * Siempre que no sea el último nivel la puerta de la última sala nos enviará al siguiente Nivel
         * esto ya esta implementado en el momento que creamos una SalaFinal, que crea
         */

        return Nivel(listaSalas)
    }
}