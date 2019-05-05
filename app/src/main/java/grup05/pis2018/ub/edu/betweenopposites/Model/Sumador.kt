package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Los objetos de esta clase se encuantran por la sala y suman a la puntuación del Jugador en la partida
 */
class Sumador(valor: Int, posicionInicial: Posicion) : Objeto(posicionInicial) {

    //Valor que se suma a la puntuación (sin tener el cuanta los multiplicadores recogidos)
    var valor: Int = 0

    /**
     * Al colisionar el Lobo con el objeto se debe notificar al lobo para que aumente su puntuación.
     */
    override fun tratarColision() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}