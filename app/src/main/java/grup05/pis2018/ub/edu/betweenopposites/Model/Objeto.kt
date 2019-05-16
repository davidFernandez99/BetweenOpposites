package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint


/**
 * Clase madre de todos los objetos creados incluido los Actores
 */
abstract class Objeto(
    height: Float,
    width: Float,
    posicion: Posicion
) {
    var posicion: Posicion = posicion
    var height: Float = height
    var width: Float = width
    var bitmap:Bitmap?=null
    var paint:Paint= Paint()
    /**
     * Se encarga de detectar la colision con el lobo.
     * Implementación basica valida para la mayoria de objetos, especialmente aquellos que son fijos
     * y 32x32 en dimensiones
     */
    open fun detectarColision(objeto: Objeto): Boolean {
        var colisio: Boolean = false
        if (this.posicion.x - this.width < objeto.posicion.x + objeto.width
            && this.posicion.x + this.width > objeto.posicion.x - objeto.width
            && this.posicion.y - this.height < objeto.posicion.y + objeto.height
            && this.posicion.y + this.height > objeto.posicion.y - objeto.height
        ) {
            tratarColision(objeto)
            colisio = true
        }
        //Devuelve si ha colisionado o no con ese objeto
        return colisio

    }

    /**
     * Se encarga de tratar las colisiones entre el objeto y el Lobo.
     * Cada objeto tiene un efecto diferente sobre el Lobo
     */
    abstract fun tratarColision(objeto: Objeto)

    /**
     * Funcion que dibuja al objeto
     */
    abstract fun draw(canvas: Canvas, context: Context)



}