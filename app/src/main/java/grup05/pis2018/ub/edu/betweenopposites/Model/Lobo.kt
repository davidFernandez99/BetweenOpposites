package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Bitmap

/**
 *
 */
class Lobo(bando: Int,height:Float,width:Float,velocidad: Float, direccion: Direccion, posicionInicial: Posicion,image: Bitmap?) : Actor(height,width,velocidad, direccion, posicionInicial,image) {
    var bando : Int = 0; //Esto habra que hacerlo aleatorio

    fun getInsance(): Lobo {
        TODO("not implemented")
    }

    /**
     *
     */
    override fun mover(): Posicion {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * TODO: Método en principio no utilizado por Lobo
     * QUIZÀ PARA LOS MUROS
     */
    override fun tratarColision(objeto:Objeto) {
        //Este método desde la clase Lobo nunca será llamado
    }

    /**
     * EL lobo no deberia ser notificado nunca para detectar una colision, si fuese así seria diferente al resto de
     * objetos.
     */
    fun tratarColision(objeto: Objeto) {

    }
}