package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Clase fachada del modelo, que crea u objeto único (Singletone) para poder proporcionar los servicios
 * del modelo de forma simple a aquellos que quieresn acceder a este, en este caso solo los Presenter.
 * Contiene todas las funciones visibles del modelo desde el exteriór, englobando multiples funciones que deben ser
 * ejecutadas cuando se produce una acción o ocurre un fenomeno especifico.
 * Implementa el Patrón de Diseño Singleton que procura la creaciń de una única instancia para utilizar la facade
 */
class Facade : Model {

    /**
     * Contiene la única instancia Facade con la cual debemos trabajar.
     * Es lo mismo que crear un campo estatico en Java
     * De esta forma podemos tener una única instancia para esta clase.
     */
    companion object {
        val uniqueFacade: Facade=Facade()

    }

    /**
     * Crea el objeto único Facade al ser llamado por primera vez y siempre es devuelto el mismo.
     */

    /**
     * Funcion que devuelve la instancia única de la Facade
     */
    public fun getInstance(): Facade {
        return uniqueFacade
    }


    override fun setLocalData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLocalData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun uploadServerData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun downloadServerData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

 }