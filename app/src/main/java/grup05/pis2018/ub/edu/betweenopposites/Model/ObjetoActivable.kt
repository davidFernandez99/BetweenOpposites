package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Bitmap

abstract class ObjetoActivable(posicionInicial: Posicion,image: Bitmap?) : Objeto(posicionInicial,image) {

    /**
     * Método abstracto que se encarga de activar el efecto caracteristico de cada objeto.
     * Debe encargarse de hacer los cambios necesarios en el estado del lobo
     * Para hacer los cambios en el Lobo debe pasarse a si mismo.
     */
    abstract fun activarEfecto(lobo: Lobo)


    /**
     * Implementamos el método tratarColision en esta clase ya que en las hijas siempre serà igual
     */
    fun tratarColision() {
        TODO("Tratar colision con objetos activables") //To change body of created functions use File | Settings | File Templates.
    }
}