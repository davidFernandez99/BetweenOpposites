package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Objeto que permite el movimiento entre salas para el Lobo.
 */
class Puerta(posicionInicial: Posicion, nivel_de_destino: Nivel, sala_destino: Sala, posicion_destino: Posicion) :
    Objeto(posicionInicial) {

    // Nivel al que lleva la puerta
    lateinit var nivel_de_destino: Nivel
    //Sala a la que lleva la puerta en ese nivel
    lateinit var sala_destino: Sala
    //Posicion a la que lleva la puerta en esa sala
    lateinit var posicion_destino: Posicion

    /**
     * Se encarga de detectar la colision con el lobo. Lleva al Lobo a la posicion de destino
     */
    override fun tratarColision() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}