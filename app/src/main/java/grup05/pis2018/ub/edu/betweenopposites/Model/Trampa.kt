package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import grup05.pis2018.ub.edu.betweenopposites.R


/**
 * Los objetos Trampa de esta clase se encuantran colocados por las salas y provocan daños al Lobo,
 * haciendo que pierda un corazón.
 */
class Trampa(
    height: Float,
    width: Float,
    posicion: Posicion
) :
    Objeto(height, width, posicion) {

    val DANO_EN_CORAZONES = 1

    /**
     * Quita DANO_EN_CORAZONES a la vida del Lobo, tras colisionar con el Lobo.
     */
    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {
            var lobo: Lobo = Lobo.instance
            if (lobo.vulnerable == true) {
                lobo.quitarVida()

            }
        }
    }
    fun comprobarColision(objeto:Objeto):Boolean{ //Método para detectar si hay o no colision entre un muro y un actor

        if (this.posicion.x - this.width < objeto.posicion.x + objeto.width
            && this.posicion.x + this.width > objeto.posicion.x - objeto.width
            && this.posicion.y - this.height < objeto.posicion.y + objeto.height
            && this.posicion.y + this.height > objeto.posicion.y - objeto.height
        ) {
            return true
        }
        //Devuelve si ha colisionado o no con ese objeto
        return false
    }
}