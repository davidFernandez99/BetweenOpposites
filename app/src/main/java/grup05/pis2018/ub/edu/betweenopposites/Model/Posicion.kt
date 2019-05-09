package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Clase que se encarga de tratar la posicion de los Objetos en pantalla
 */
class Posicion(x: Float, y: Float) {

    //Variables que contienen la posición
    //Las inicializo inicialmente a 0
    var x: Float = x
    var y: Float = y

    /**
     * Funcion para asignar una posicion (x,y)
     */
    fun setPosicion(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

}