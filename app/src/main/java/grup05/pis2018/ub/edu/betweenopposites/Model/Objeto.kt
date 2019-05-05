package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Clase madre de todos los objetos creados incluido los Actores
 */
abstract class Objeto(posicionInicial: Posicion) {
    var posicion: Posicion = Posicion(0, 0)
    var posicionInicial: Posicion = Posicion(0, 0)


    /**
     * Se encarga de detectar la colision con el lobo.
     * Implementación basica valida para la mayoria de objetos, especialmente aquellos que son fijos
     * y 32x32 en dimensiones
     */
    protected fun detectarColision(posicionLobo: Posicion) {
        //TODO: Not implemented
    }

    /**
     * Se encarga de tratar las colisiones entre el objeto y el Lobo.
     * Cada objeto tiene un efecto diferente sobre el Lobo
     */
    abstract fun tratarColision()
}