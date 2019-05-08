package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Bitmap

/**
 * Objeto en el juego que se encuentra en las salas especiales, cuando chocamos con ella nos da la oportunidad de
 * ganar un objeto, sumadro, multiplicador, o objeto activo, tras adivinar una pregunta
 * ¿Cual crees que es tu puntuación?.
 * La parte matematica del juego te obliga a llevar la cuenta de tu puntuación para poder ganar objetos extras y
 * poder sacar mayores puntuaciones.
 * La maquina da opciones para posibles respuestas y si se acierta se obtiene la recompensa.
 */
class Maquina(height: Float, width: Float, posicionInicial: Posicion,posicion:Posicion, image: Bitmap) :
    Objeto(height, width, posicionInicial,posicion, image) {

    // Contiene la recompensa que se da al jugador en caso de que se adivine la respuesta correcta
    lateinit var recompensa: Objeto
    // Flag que nos dice si debe devolverse el Objeto o no. Inicialmente a "false".
    var dar_recompensa: Boolean = false

    /**
     * En el caso de que se detecte la colisión con el Lobo, se trata la colisión de forma que se dan las opciones
     * y en el caso de acierto se da el premio.
     * La colision TODO: ENTENDER COMO VA A SER EL PROCESO DE CREAR LAS OPCIONES Y ENTREGAR EL OBJETO RECOMPENSA
     */
    fun darOpciones() {
    }

    override fun tratarColision(objeto: Objeto) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}