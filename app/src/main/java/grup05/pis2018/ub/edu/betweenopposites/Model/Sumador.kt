package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Bitmap

/**
 * Los objetos de esta clase se encuantran por la sala y suman a la puntuación del Jugador en la partida
 */
class Sumador(valor: Int, height: Float, width: Float, posicionInicial: Posicion,posicion:Posicion, image: Bitmap?) :
    Objeto(height, width, posicionInicial,posicion, image) {

    //Valor que se suma a la puntuación (sin tener el cuanta los multiplicadores recogidos)
    var valor: Int = 0

    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {
            TODO("sumará a puntuación el contenido que tenga en valoe")
        }
    }


    /**
     * Al colisionar el Lobo con el objeto se debe notificar al lobo para que aumente su puntuación.
     */

}