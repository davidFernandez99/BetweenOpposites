package grup05.pis2018.ub.edu.betweenopposites.Presenter

import grup05.pis2018.ub.edu.betweenopposites.Model.Facade

/**
 * Esta Interficie Presenter está destinada a definir los mètodos que necesitan implementar los diferentes Presenter
 * que observan a su View correspondiente y trata con el modelo a través de la facade.
 * De forma que el Presenter es un Observer de la View (Observable)
 */
interface Presenter {
    fun update()
    fun changeView()
    fun getFacade()
}