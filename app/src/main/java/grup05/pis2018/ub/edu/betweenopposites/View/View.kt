package grup05.pis2018.ub.edu.betweenopposites.View

import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter

/**
 * Esta Interficie View es un Observable que notifica a los presenters que la observan.
 * Se encarga de notificar al presenter la interacción con los botones por el usuario,
 * para que trate con el modelo y haga los cambios necesarios de forma asincrona.
 */
// TODO: HAY QUE DECIDIR SI HACER ESTO UNA CLASE ABSTRACTA A UNA INTERFICIE YA QUE SIENDO ESTO UNA CLASE NO PODEMOS HEREDAR DE DOS CLASES DESDE LAS VIEWS
interface View {

    //Contiene los observadores de esta view
    //var observers: ArrayList<Presenter>? = null


    /**
     * Añade observadores a la view.
     */
    fun addObserver(presenter: Presenter) /*{
        //Si no se ha creado aun el array
        if (observers == null) {
            //Creamos
            observers = ArrayList<Presenter>()
        }
        // Comprovamos que este observer no esta ya en la lista
        if (presenter !in observers!!) {
            // En ese caso lo metemos
            observers!!.add(presenter)
        } else {
            throw Exception("El presentador ya se encuentra en la lista")
        }
    }*/

    /**
     * Elimina uno de los observadores
     */
    fun deleteObserver(presenter: Presenter) /*{
        //Si no se ha creado aun el array
        if (observers == null) {
            //Exception
            throw Exception("Aun no hay ningun observador añadido a la lista")
        } else {
            //Eliminamos el observador
            if (!observers!!.remove(presenter)) {
                //En caso de que no devuelva true es que no se ha eliminado porque no se ha encontrado
                throw Exception("El presentador no se ha encontrado en el array.")
            }
        }
    }*/

    /**
     * Notifica que ha habido cambios en la vista. Cada vista implementa su metodo teniendo en cuenta las diferentes
     * fuentes de las que puede procedér el cambio.
     */

    abstract fun notifyObservers(fuente: String)
}