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
     * Un Actor puede colisionar con un muro. Cuando lo haga depende de el objeto harà un cambio u otro.
     */
    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {

            var lobo: Lobo = objeto as Lobo
            lobo.velocidad=0f
            lobo.direccionChoque=lobo.direccion

            while(comprobarColision(lobo)==true){ //Si detecta una colision le hace retroceder hasta que deje de detectarla
                lobo.returnPosicion()
            }
            lobo.returnPosicion()
            lobo.direccion=Direccion.PARADO //Establece que la dirección al colisionar con el muro es PARADO
        }
        if(objeto is Orbe){
            var orbe: Orbe = objeto as Orbe
            orbe.direccionChoque=orbe.direccion

            while(comprobarColision(orbe)==true){ //Si detecta una colision le hace retroceder hasta que deje de detectarla
                orbe.returnPosicion()
            }
            orbe.returnPosicion()
            orbe.canviarDireccionMuro() //Cambia la dirección de este orbe para que deje de colisionar y no este parado

        }
    }
    fun comprobarColision(objeto:Objeto):Boolean{ //Método para detectar si hay o no colision entre un muro y un actor

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


}