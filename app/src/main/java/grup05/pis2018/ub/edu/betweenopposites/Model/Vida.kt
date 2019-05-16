package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import grup05.pis2018.ub.edu.betweenopposites.R

//TODO: YO HARIA ESTA CLASE HEREDAR DE OBJETO PARA QUE TENGA TMB UNA POSICIÓN, BITMAP, METODO DRAW...
class Vida() {
    var bitmap:Bitmap?=null
    val paint = Paint()

    var numVide: Int = 3
    fun quitarVida() {
        numVide -= 1
    }

    fun draw(canvas: Canvas, x: Float, y: Float, context: Context) {
        this.bitmap=BitmapFactory.decodeResource(context.resources, R.drawable.corazon_activo)

        canvas.drawBitmap(
            this.bitmap, x,
            y, paint
        )
    }
}