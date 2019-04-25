package grup05.pis2018.ub.edu.betweenopposites.Model

interface Model {
    var interactor : Interactor
    fun setLocalData()
    fun getLocalData()
    fun uploadServerData()
    fun downloadServerData()
}