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
            lobo.velocidad=0f
            lobo.direccionChoque=lobo.direccion

            while(comprobarColision(lobo)==true){
                lobo.returnPosicion()
            }
            lobo.returnPosicion()
            lobo.direccion=Actor.Direccion.PARADO
        }
        if(objeto is Orbe){
            var orbe: Orbe = objeto as Orbe
            orbe.velocidad=0f
            orbe.direccionChoque=orbe.direccion

            while(comprobarColision(orbe)==true){
                orbe.returnPosicion()
            }
            orbe.restaurarVelocidad()
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


}