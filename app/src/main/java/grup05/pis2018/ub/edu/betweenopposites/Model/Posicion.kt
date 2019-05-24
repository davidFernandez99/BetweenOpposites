package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Clase que se encarga de tratar la posicion de los Objetos en pantalla
 */

class Posicion(x: Float = -1f, y: Float = -1f, x_sala: Int = -1, y_sala: Int = -1) {


    //Variables que contienen la posición
    // Mantengo la posición del objeto en la matriz de la sala
    var x_sala: Int = x_sala
    var y_sala: Int = y_sala

    //Contiene la posición real en pantalla
    var x: Float = x
    var y: Float = y

    init {
        setPosicion(x,y,x_sala,y_sala)
    }

    /**
     * Funcion para asignar una posicion (x,y)
     */
    fun setPosicion(x: Float, y: Float, x_sala:Int, y_sala:Int) {

        if (x == -1.toFloat() && y == -1.toFloat() && x_sala != -1 && y_sala!=-1) {
            this.x = (x_sala * Dimension.bloque.height) + (Dimension.bloque.height / 2)
            this.y = (y_sala * Dimension.bloque.width) + (Dimension.bloque.width / 2)
        } else if(x != -1.toFloat() && y != -1.toFloat() && x_sala == -1 && y_sala==-1){
            this.x_sala = (this.x.toInt() % Dimension.bloque.height - (Dimension.bloque.height / 2)).toInt()
            this.y_sala = (this.y.toInt() % Dimension.bloque.width - (Dimension.bloque.width / 2)).toInt()
        }else{
            throw Exception("Los valores de posición introducidos no són correctos")
        }
    }

}