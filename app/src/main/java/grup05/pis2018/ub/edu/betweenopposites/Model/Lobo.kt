package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Bitmap

/**
 *
 */
class Lobo(
    var vida: Vida,
    bando: Int,
    height: Float,
    width: Float,
    velocidad: Float,
    direccion: Direccion,
    posicionInicial: Posicion,
    posicion: Posicion,
    image: Bitmap?
) : Actor(height, width, velocidad, direccion, posicionInicial,posicion, image) {
    // Bando al que pertenece el Lobo (Blanco, Negro)
    var bando: Int = 0; //TODO: EN PRINCIPIO AL PRINCIPIO PODEMOS ESCOGER EL BANDO EN EL QUE ESTA EL LOBO,
                        //TODO: EN CASO DE QUE SE COMPLIQUE, ES MEJOR HACERLO RANDOM.


    enum class Bando(val id: Int){
        Blanco(0),
        Negro(1)
    }

    var objetoActivable: ObjetoActivable? = null
    var visible: Boolean = true
    var multiplicador: Int = 1

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
     * EL lobo no deberia ser notificado nunca para detectar una colision, si fuese así seria diferente al resto de
     * objetos.
    */
    override fun tratarColision(objeto: Objeto) {
        //Este método desde la clase Lobo nunca será llamado
    }

}