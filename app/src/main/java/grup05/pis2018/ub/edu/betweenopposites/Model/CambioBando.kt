package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import grup05.pis2018.ub.edu.betweenopposites.R


/**
 * Objeto Cambio de bando que se encarga de tratar la colision entre este y el Lobo para que cambie de Bando
 */
class CambioBando(
    height: Float,
    width: Float,
    posicion: Posicion
) :
    ObjetoActivable(height, width, posicion) {


    /**
     * Se guarda en la posicion de objeto activable que tiene la Clase Lobo
     */
    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) { //Si colisiona con un objeto lobo añadirá ese objeto a sus objetos activables
            var lobo: Lobo = objeto as Lobo
            lobo.objetoActivable = this
        }
    }

    /**
     * Se encarga de cambiar al lobo de bando. De forma que
     */
    override fun activarEfecto(lobo: Lobo) {
        var bando: Actor.Bando = lobo.bando //Cogeremos el bando del lobo y le cambiaremos al contrario
        if (bando == Actor.Bando.Blanco) {
            lobo.bando = Actor.Bando.Negro
        } else {
            lobo.bando = Actor.Bando.Blanco
        }
    }
    override fun draw(canvas: Canvas, context: Context){
        this.bitmap= BitmapFactory.decodeResource(context.resources, R.drawable.objeto_cambiobando)
        canvas.drawBitmap(this.bitmap,this.posicion.x,this.posicion.y,this.paint)
    }

}