package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import grup05.pis2018.ub.edu.betweenopposites.R


/**
 * Objeto que hace Invisible/Indetectable por los orbes enemigos al LObo por un tiempo limitado cuando se activa
 */
class Invisibilidad(
    height: Float,
    width: Float,
    posicion: Posicion
) :
    ObjetoActivable(height, width, posicion) {

    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {//Si colisiona con un objeto lobo añadirá ese objeto a sus objetos activables
            var lobo: Lobo = objeto as Lobo
            lobo.objetoActivable = this
        }
    }


    /**
     * Cambia el estado del lobo de visible a invisible, y lo hace durante un tiempo
     */
    override fun activarEfecto(lobo: Lobo) {
        lobo.es_visible = false

    }


}