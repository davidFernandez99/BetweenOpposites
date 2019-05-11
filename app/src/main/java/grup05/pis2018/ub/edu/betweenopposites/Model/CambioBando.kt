package grup05.pis2018.ub.edu.betweenopposites.Model


/**
 * Objeto Cambio de bando que se encarga de tratar la colision entre este y el Lobo para que cambie de Bando
 */
class CambioBando(
    height: Float,
    width: Float,
    posicion: Posicion) :
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


}