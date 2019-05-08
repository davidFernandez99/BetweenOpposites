package grup05.pis2018.ub.edu.betweenopposites.Model

import android.graphics.Bitmap

/**
 * Clase madre de todos los objetos creados incluido los Actores
 */
abstract class Objeto(height: Float, width: Float, posicionInicial: Posicion, posicion: Posicion, image: Bitmap?) {
    var posicion: Posicion = Posicion(0f, 0f)
    var posicionInicial: Posicion = Posicion(0f, 0f)
    var height: Float = 0f;
    var width: Float = 0f;

    /**
     * Se encarga de detectar la colision con el lobo.
     * Implementación basica valida para la mayoria de objetos, especialmente aquellos que son fijos
     * y 32x32 en dimensiones
     */
    protected fun detectarColision(objeto: Objeto): Boolean {
        var colisio: Boolean = false
        colisio = this.posicion.x < objeto.posicion.x + objeto.width &&
                this.posicion.x + this.width > objeto.posicion.x &&
                this.posicion.y < objeto.posicion.y + objeto.height &&
                this.posicion.y + this.height > objeto.posicion.y
        // Cada objeto trata la colision de froma diferente y dependiendo del objeto con el cual colisione
        tratarColision(objeto)

        //Devuelve si ha colisionado o no con ese objeto
        return colisio
    }

    /**
     * Se encarga de tratar las colisiones entre el objeto y el Lobo.
     * Cada objeto tiene un efecto diferente sobre el Lobo
     */
    abstract fun tratarColision(objeto: Objeto)

    abstract fun draw()
}