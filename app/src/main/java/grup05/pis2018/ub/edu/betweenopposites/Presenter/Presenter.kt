package grup05.pis2018.ub.edu.betweenopposites.Presenter

import grup05.pis2018.ub.edu.betweenopposites.Model.Facade

interface Presenter {
    var facade : Facade
    fun update()
    fun changeView()
}