package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Clase que se encarga de tratar la posicion de los Objetos en pantalla
 */

class Posicion(x: Float = 0f, y: Float = 0f, x_sala: Int = 0, y_sala: Int = 0) {


    //Variables que contienen la posición
    // Mantengo la posición del objeto en la matriz de la sala
    var x_sala: Int = x_sala
    var y_sala: Int = y_sala

    //Contiene la posición real en pantalla
    var x: Float = x
    var y: Float = y

    init {
        if (x == 0f && y == 0f) {
            this.x = (x_sala * Dimension.bloque.height) + (Dimension.bloque.height / 2)
            this.y = (y_sala * Dimension.bloque.width) + (Dimension.bloque.width / 2)
        } else {
            this.x = x
            this.y = y
        }
    }

    /**
     * Funcion para asignar una posicion (x,y)
     */
    fun setPosicion(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

}