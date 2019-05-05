package grup05.pis2018.ub.edu.betweenopposites.View

import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter

/**
 * Esta Interficie View es un Observable que notifica a los presenters que la observan.
 * Se encarga de notificar al presenter la interacción con los botones por el usuario,
 * para que trate con el modelo y haga los cambios necesarios de forma asincrona.
 */
interface View {
    fun addObserver(presenter: Presenter)
    fun deleteObserver(presenter: Presenter)
    fun notifyObservers()
}