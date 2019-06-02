package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import grup05.pis2018.ub.edu.betweenopposites.R


class Vida() {

    var bitmap:Bitmap?=null
    val paint = Paint()
    var numVide: Int = 3 //Numero de vidas que tendrá el jugador al comenzar
    fun quitarVida() { //Método para quitar vida cuando colisiona con una trampa
        numVide -= 1
    }

    fun draw(canvas: Canvas, x: Float, y: Float, context: Context) { //Método para dibujar las vidas en el canvas
        this.bitmap=BitmapFactory.decodeResource(context.resources, R.drawable.corazon_activo)
        canvas.drawBitmap(
            this.bitmap, x,
            y, paint
        )
    }
}