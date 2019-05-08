package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Bitmap

class Orbe(
    bando: Int,
    height: Float,
    width: Float,
    velocidad: Float,
    direccion: Direccion,
    posicionInicial: Posicion,
    posicion: Posicion,
    image: Bitmap?
) : Actor(height, width, velocidad, direccion, posicionInicial, posicion, image) {
    override fun draw() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var bando: Int = 0; //Esto habra que hacerlo aleatorio
    override fun mover(): Posicion {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
                TODO("darle puntos al lobo")
            } else {
                //Le quita puntos al lobo
                TODO("quitarle puntos al lobo")
            }
        }
        if (objeto is Muro) {
            var muro: Muro = objeto as Muro
            //Canviará dirección del propio orbe para que este siempre en movimiento
            TODO("cambiar dirección del orbe")
        }
    }

}