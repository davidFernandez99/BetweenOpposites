package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Bitmap


/**
 * Objeto que permite el movimiento entre salas para el Lobo.
 */
class Puerta(
    height: Float,
    width: Float,
    posicion: Posicion,
    posicionInicial: Posicion,
    nivel_de_destino: Nivel,
    sala_destino: Sala,
    posicion_destino: Posicion,
    image: Bitmap
) :
    Objeto(height, width, posicionInicial, posicion, image) {


    // Nivel al que lleva la puerta
    lateinit var nivel_de_destino: Nivel
    //Sala a la que lleva la puerta en ese nivel
    lateinit var sala_destino: Sala
    //Posicion a la que lleva la puerta en esa sala
    lateinit var posicion_destino: Posicion

    /**
     * Se encarga de detectar la colision con el lobo. Lleva al Lobo a la posicion de destino
     */
    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {
            TODO("si el lobo colisiona con una puerta hará pasar a la siguiente sala o nivel en caso que fuese la sala final")
            TODO("si es la sala final del último nivel llamara al método endGame()")
        }
    }
}