package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import grup05.pis2018.ub.edu.betweenopposites.R

class AumentarVelocidad(
    height: Float,
    width: Float,
    posicion: Posicion
) :
    ObjetoActivable(height, width, posicion) {
    // Maximo tiempo en segundos de objeto activo
    val AUMENTO_DE_VELOCIDAD: Int = 10 //Constante para determinar cuanto aumenta la velocidad cuando colisiona el Lobo con este objeto

    override fun tratarColision(objeto: Objeto) { //Método para aumentar la Velocidad del lobo cuando colisiona con este y que no sea visible este
        if (objeto is Lobo) { //Si colisiona con un objeto lobo añadirá ese objeto a sus objetos activables
            var lobo: Lobo = objeto as Lobo
            lobo.objetoActivable = this
            es_visible=false
        }
    }


    /**
     * Aumenta la velocidad del lobo en AUMENTO_DE_VELOCIDAD.
     */

    override fun activarEfecto(lobo: Lobo) { //Aumenta la velocidad del lobo durante un tiempo
        lobo.velocidad += AUMENTO_DE_VELOCIDAD
        lobo.velocidadCambiada += AUMENTO_DE_VELOCIDAD

    }

}