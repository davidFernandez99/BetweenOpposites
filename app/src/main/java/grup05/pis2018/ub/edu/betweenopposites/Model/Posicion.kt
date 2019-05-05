package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Clase que se encarga de tratar la posicion de los Objetos en pantalla
 */
class Posicion(x: Int, y: Int) {

    //Variables que contienen la posición
    //Las inicializo inicialmente a 0
    var x: Int = 0
    var y: Int = 0

    /**
     * Funcion para asignar una posicion (x,y)
     */
    fun setPosicion(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

}