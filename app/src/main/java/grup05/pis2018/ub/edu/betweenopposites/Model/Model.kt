package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Define las funcionalidades básicas que necesita el modelo (implementadas en Facade) entre ellas la posibilidad de
 * guardar y cargar datos de forma local y online.
 */
interface Model {
    // Necesitaremos un Interactor para tratar con los datos online
    /**
     * Define cuales tienen que ser los datos a guardar localmente
     */
    fun setLocalData()

    /**
     * Recoge los datos que tenemos guardados localmente
     */
    fun getLocalData()

    /**
     * Sube la información local a online
     */
    fun uploadServerData()

    /**
     * Descarga la información remota
     */
    fun downloadServerData()
}