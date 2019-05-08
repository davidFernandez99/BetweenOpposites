package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Bitmap

/**
 * Objeto Cambio de bando que se encarga de tratar la colision entre este y el Lobo para que cambie de Bando
 */
class CambioBando(height: Float, width: Float, posicionInicial: Posicion,posicion: Posicion, image: Bitmap?) :
    ObjetoActivable(height, width, posicionInicial,posicion, image) {
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
        var bando: Int = lobo.bando //Cogeremos el bando del lobo y le cambiaremos al contrario
        if (bando == 0) {
            lobo.bando = 1
        } else {
            lobo.bando = 0
        }
    }


}