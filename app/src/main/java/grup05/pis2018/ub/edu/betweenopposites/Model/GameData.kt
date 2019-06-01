package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.Canvas

/**
 * Contiene la información que se maneja durante una partida, todos los datos necesarios para
 * hacer funcionar el Juego.
 * El conjunto de niveles, el Lobo...
 */
class GameData(contexto: Context) {
    // Tiene una referencia directa a la salaActiva en este momento
    lateinit var salaActiva: Sala

    // Tiene la referencia del nivel en el que estamos actualmente
    lateinit var nivelActivo: Nivel

    // Mantiene los niveles
    var conjuntoNiveles: ConjuntoNiveles

    // Apuntadores a la primera y la última puerta
    var primeraPuerta: Puerta? = null
    var ultimaPuerta: Puerta? = null

    // Tiene una referencia directa al objeto Lobo
    lateinit var lobo: Lobo

    init{
        /**
         * Crea el conjunto de niveles y carga como sala inicial la primera del primer nivel.
         */
        // Creamos el conjunto de niveles
        conjuntoNiveles = ConjuntoNiveles(contexto)

        // Crea el objeto lobo y lo posiciona frente a la primera puerta de todas
        // Antes de eso guardamos cual és la primera puerta y la última para saber si cuando
        // las atravesamos son estas y poder tratarlo, y poder hacer que el lobo aparezca en el
        // Spawnpoint de la primera de las pueretas
        cargarPrimeraYUltimaPuerta()

        // Cojo la instancia y le cambio la posición al spawnpoint de la primera puerta
        lobo = Lobo.instance

        // Cargamos la primera de las salas como la activa y colocamos al lobo en el punto de spawn
        traspasarPuerta(primeraPuerta!!)

    }

    /**
     * Carga la sala en indicada en el nivel indicado. Método de soporte
     */
    private fun cargarSala(id_nivel: Int, id_sala: Int){
        // Ponemos como sala activa la que queremos cargar
        nivelActivo=conjuntoNiveles.getNivel(id_nivel)
        salaActiva=nivelActivo.getSala(id_sala)
    }

    /**
     * Carga la primera y la última puerta de todo el conjunto
     */
    private fun cargarPrimeraYUltimaPuerta(){
        primeraPuerta =conjuntoNiveles.getNivel(1).getSala(1).getPuerta(1)
        ultimaPuerta = conjuntoNiveles.getNivel(-1).getSala(-1).getPuerta(-1)
    }

    /**
     * Al atravesar una puerta se llama a este método, que se encarga
     * de cargar la sala y nivel objetivo.
     */
    fun traspasarPuerta(puerta : Puerta){
        val id_nivel_destino: Int = puerta.id_nivel_destino
        val id_sala_destino: Int = puerta.id_sala_destino
        var spawnpoint_destino: Posicion? = puerta.getPosicionDestino()

        if(spawnpoint_destino==null){
            spawnpoint_destino=puerta.spawn_point

        }
        // CARGAMOS LA SALA DE DESTINO
        cargarSala(id_nivel_destino  ,id_sala_destino  )

        //Cambio la posición del lobo al destino
        lobo.posicion = spawnpoint_destino

    }

    fun update(fps:Long){
        salaActiva.update(fps)
        Facade.mapa=salaActiva.id_sala
        Facade.nivel=nivelActivo.id_nivel
    }

    fun draw(canvas: Canvas, contexto: Context){
        salaActiva.draw(canvas,contexto)
    }
}