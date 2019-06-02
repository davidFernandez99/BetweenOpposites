package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import grup05.pis2018.ub.edu.betweenopposites.R


/**
 * Los objetos de esta clase se encuantran por la sala y suman a la puntuación del Jugador en la partida
 */
class Sumador(
    valor: Int,
    height: Float,
    width: Float,
    posicion: Posicion
) :
    Objeto(height, width, posicion) {


    //Valor que se suma a la puntuación (sin tener el cuanta los multiplicadores recogidos)
    var valor: Int = valor

    /**
     * Al colisionar el Lobo con el objeto se debe aumente su puntuación.
     */
    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {
            var lobo: Lobo = objeto as Lobo
            es_visible=false
            if(Lobo.instance.puntuacion.puntuacion==0){
                Lobo.instance.cambioBando()
            }
            lobo.sumarPuntuacion(valor)

        }
    }




}