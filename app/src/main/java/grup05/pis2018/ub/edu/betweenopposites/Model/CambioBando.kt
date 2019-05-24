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
            es_visible=false
        }
    }

    /**
     * Se encarga de cambiar al lobo de bando. De forma que
     */
    override fun activarEfecto(lobo: Lobo) {
        lobo.cambioBando()
    }


}