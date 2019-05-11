package grup05.pis2018.ub.edu.betweenopposites.Model


/**
 * Los objetos de esta clase se encuantran por la sala y suman a la puntuación del Jugador en la partida
 */
class Sumador(
    valor: Int,
    height: Float,
    width: Float,
    posicion: Posicion
) :
    Objeto(height, width, posicion) {


    //Valor que se suma a la puntuación (sin tener el cuanta los multiplicadores recogidos)
    var valor: Int = valor

    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {
            var lobo: Lobo = objeto as Lobo
            lobo.sumarPuntuacion(valor)
        }
    }


    /**
     * Al colisionar el Lobo con el objeto se debe notificar al lobo para que aumente su puntuación.
     */

}