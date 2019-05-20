package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.Canvas

abstract class ObjetoActivable(
    height: Float,
    width: Float,
    posicion: Posicion
) :
    Objeto(height, width, posicion) {
    var es_visible:Boolean=true
    /**
     * Método abstracto que se encarga de activar el efecto caracteristico de cada objeto.
     * Debe encargarse de hacer los cambios necesarios en el estado del lobo
     * Para hacer los cambios en el Lobo debe pasarse a si mismo.
     */
    abstract fun activarEfecto(lobo: Lobo)


}