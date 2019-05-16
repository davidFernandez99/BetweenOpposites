package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import grup05.pis2018.ub.edu.betweenopposites.R

class Suelo(
    height: Float,
    width: Float,
    posicion: Posicion
) : Objeto(height, width, posicion) {
    override fun tratarColision(objeto: Objeto) {

    }
    override fun draw(canvas: Canvas, context: Context){
        this.bitmap= BitmapFactory.decodeResource(context.resources, R.drawable.suelo)
        canvas.drawBitmap(this.bitmap,this.posicion.x,this.posicion.y,this.paint)
    }
}