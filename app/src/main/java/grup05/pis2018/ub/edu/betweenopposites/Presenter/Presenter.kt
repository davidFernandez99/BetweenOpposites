package grup05.pis2018.ub.edu.betweenopposites.Presenter

import grup05.pis2018.ub.edu.betweenopposites.Model.Facade

/**
 * Hemos hecho esta clase abstracta Presenter está destinada a definir los mètodos que necesitan implementar los diferentes Presenter
 * que observan a su View correspondiente y trata con el modelo a través de la facade.
 * De forma que el Presenter es un Observer de la View (Observable)
 */
abstract class Presenter {

    /**
     * Método al cual llama el Observable en el momento que realize el notify
     */
    abstract fun update()

    /**
     * Función que actualiza la información que tiene la vista, seria el camino de vuelta tras haber recivido
     *
     */
    fun changeView() {

    }

    /**
     * Función que coge el objeto Facade para poder comunicarse con el modelo.
     */
    fun getFacade() {

    }
}