package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

//TODO: YO HARIA ESTA CLASE HEREDAR DE OBJETO PARA QUE TENGA TMB UNA POSICIÓN, BITMAP, METODO DRAW...
class Vida(var image: Bitmap?) {
    /**
     * Quita una de las vidas
     */
    fun quitarVida() {

    }

    var numVide: Int = 3
    fun draw(canvas: Canvas, x:Float, y:Float) {
        val paint = Paint()
        canvas.drawBitmap(this.image, x,
            y, paint)
    }
}