package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import grup05.pis2018.ub.edu.betweenopposites.R


class Muro(
    height: Float,
    width: Float,
    posicion: Posicion
) : Objeto(height, width, posicion) {


    /**
     * Un Actor puede colisionar con un muro. CUando lo haga depende de el objeto harà un cambio u otro.
     */
    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {

            var lobo: Lobo = objeto as Lobo
            lobo.puede_moverse=false
            lobo.direccionChoque=lobo.direccion

            while(comprobarColision(lobo)==true){
                lobo.returnPosicion()
            }
            var i:Int=0
            for (i in 0..5){
                lobo.returnPosicion()
            }
            lobo.returnPosicion()
            lobo.velocidad=0f
            lobo.puede_moverse=true
        }
        if(objeto is Orbe){
            var orbe: Orbe = objeto as Orbe
            orbe.canviarDireccion()
        }
    }
    fun comprobarColision(objeto:Objeto):Boolean{

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
    override fun draw(canvas: Canvas, context: Context){
        this.bitmap= BitmapFactory.decodeResource(context.resources, R.drawable.muro)
        canvas.drawBitmap(this.bitmap,this.posicion.x,this.posicion.y,this.paint)
    }

}