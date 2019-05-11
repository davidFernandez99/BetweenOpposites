package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Bitmap
import android.graphics.Canvas
import java.util.*
import kotlin.random.Random.Default.nextInt

class Orbe(
    bando: Bando,
    height: Float,
    width: Float,
    velocidad: Float,
    direccion: Direccion,
    posicion: Posicion

) : Actor(height, width, velocidad, direccion, posicion) {

    var darPuntuacion:Int=10
    var bando: Bando = bando; //Esto habra que hacerlo aleatorio

    override fun mover(fps:Long) {
        if(this.direccion==Direccion.ABAJO){
            if(this.posicion.y+this.height>=1900){
                //canviarDireccion()
                this.velocidad=0f
            }
            else{
                this.posicion.y+=this.velocidad/fps
            }

        }
        if(this.direccion==Direccion.ARRIBA){
            if(this.posicion.y-this.height==0f){
                //canviarDireccion()
                this.velocidad=0f
            }
            else{
                this.posicion.y-=velocidad/fps
            }

        }

        if(this.direccion==Direccion.IZQUIERDA ){
            if(this.posicion.x-this.width==0f){
                //canviarDireccion()
                this.direccion=Direccion.DERECHA
            }
            else{
                this.posicion.x-=this.velocidad/fps
            }

        }
        if(this.direccion==Direccion.DERECHA){
            if(this.posicion.x==1920f-this.width){
                //canviarDireccion()
                this.velocidad=0f
            }
            else{
                this.posicion.x+=this.velocidad/fps
            }

        }
    }

    /**
     *
     * Método con tal de detectar si el orbe está en colision conun objeto tanto si colisiona con un muro como si colisiona con un objeto Lobo
     * Si el orbe es edel mismo lado del  lobo seguirá un patrón de movimiento de: DiagonaArribalIzquierda, DiagonalAbajoIzquierda,DigonalAbajoDerecha y DiagonalArribaDerecha
     * Esos cuatro movimiento del orbe irán cambiando cuando colisione con alún objeto muro
     *
     * Si el orbe colisiona con un objeto Lobo ,si es del bando contrario a éste le quitara una vida a éste y desaparecerá este orbe
     * Si el orbe es del mismo bando del lobo le augmentará puntos según el multiplicador que lleve en ese momento
     */
    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {
            var lobo: Lobo = objeto as Lobo
            if (lobo.bando == this.bando) {
                //Le da puntos al lobo
                lobo.sumarPuntuacion(darPuntuacion)
            } else {
                //Le quita puntos al lobo
                lobo.quitarPuntuacion(darPuntuacion)
            }
        }
        if (objeto is Muro) {
            //Canviará dirección del propio orbe para que este siempre en movimiento
            canviarDireccion()
        }
    }
    fun canviarDireccion(){
        var direccionNueva:Int=0
        if(direccion==Direccion.DERECHA){
            direccionNueva=(0..1).random()
            if(direccionNueva==0){
                this.direccion=Direccion.ABAJO
            }
            else{
                this.direccion=Direccion.ARRIBA
            }

        }
        if(direccion==Direccion.IZQUIERDA){
            direccionNueva=(0..1).random()
            if(direccionNueva==0){
                this.direccion=Direccion.ABAJO
            }
            else{
                this.direccion=Direccion.ARRIBA
            }
        }
        if(direccion==Direccion.ARRIBA){
            direccionNueva=(0..1).random()
            if(direccionNueva==0){
                this.direccion=Direccion.IZQUIERDA
            }
            else{
                this.direccion=Direccion.DERECHA
            }
        }
        if(direccion==Direccion.ABAJO){
            direccionNueva=(0..1).random()
            if(direccionNueva==0){
                this.direccion=Direccion.IZQUIERDA
            }
            else{
                this.direccion=Direccion.DERECHA
            }
        }
    }

}