package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

//TODO: YO HARIA ESTA CLASE HEREDAR DE OBJETO PARA QUE TENGA TMB UNA POSICIÓN, BITMAP, METODO DRAW...
class Vida( ) {
    var numVide: Int = 3
    fun quitarVida(){
        numVide-=1
    }

    fun draw(canvas: Canvas, x:Float, y:Float,image: Bitmap?) {
        val paint = Paint()
        canvas.drawBitmap(image, x,
            y, paint)
    }
}