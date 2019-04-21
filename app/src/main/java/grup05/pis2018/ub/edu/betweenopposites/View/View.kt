package grup05.pis2018.ub.edu.betweenopposites.View

import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter

interface View {
    var observers : ArrayList<Presenter>
    fun addObserver(presenter: Presenter)
    fun deleteObserver(presenter: Presenter)
    fun notifyObservers()
}