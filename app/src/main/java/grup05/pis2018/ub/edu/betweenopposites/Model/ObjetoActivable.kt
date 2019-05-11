package grup05.pis2018.ub.edu.betweenopposites.Model

abstract class ObjetoActivable(
    height: Float,
    width: Float,
    posicion: Posicion
) :
    Objeto(height, width, posicion) {

    /**
     * Método abstracto que se encarga de activar el efecto caracteristico de cada objeto.
     * Debe encargarse de hacer los cambios necesarios en el estado del lobo
     * Para hacer los cambios en el Lobo debe pasarse a si mismo.
     */
    abstract fun activarEfecto(lobo: Lobo)


    /**
     * Implementamos el método tratarColision en esta clase ya que en las hijas siempre serà igual
     */

}