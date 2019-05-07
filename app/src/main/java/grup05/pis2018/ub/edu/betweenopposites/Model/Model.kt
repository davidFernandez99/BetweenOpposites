package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Define las funcionalidades básicas que necesita el modelo (implementadas en Facade) entre ellas la posibilidad de
 * guardar y cargar datos de forma local y online.
 */
interface Model {
    // Necesitaremos un Interactor para tratar con los datos online
    fun setLocalData()
    fun getLocalData()
    fun uploadServerData()
    fun downloadServerData()
}