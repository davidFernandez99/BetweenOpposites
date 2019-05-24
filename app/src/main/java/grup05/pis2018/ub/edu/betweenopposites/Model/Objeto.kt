package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import java.nio.channels.FileLock


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
    fun draw(canvas: Canvas, image:Bitmap){
        canvas.drawBitmap(image,this.posicion.x,this.posicion.y,paint)
    }

    fun printObjeto() {
        val tipo : String = when{ this is Trampa -> "Trampa"
            this is Sumador -> "Sumador"
            this is Multiplicador -> "Multiplicador"
            else -> "Impossible"}
        println("Objeto $tipo POSICION: [${posicion.x_sala},${posicion.y_sala}]")
    }


    /**
     * Substituye la posicon del objeto por la posición pasada por parametro.
     * No es necesario poner todos los parametros.
     *  Parametros:
     *      x = "Posicion x en la pantalla" = 0 por defecto
     *      y = "Poscion y en la pantalla" = 0 por defecto
     *      x_sala= "Posicion en x dentro de la matriz de la sala" = 0 por defecto
     *      y_sala= "Posicion en y dentro de la matriz de la sala" = 0 por defecto
     */
    fun setPosicion(x: Float?=null, y: Float?=null, x_sala: Int?=null,y_sala: Int?= null){
        this.posicion.setPosicion(x,y,x_sala,y_sala)
    }

}