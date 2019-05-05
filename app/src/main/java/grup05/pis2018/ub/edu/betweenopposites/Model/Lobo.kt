package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 *
 */
class Lobo(velocidad: Int, direccion: Direccion, posicionInicial: Posicion) :
    Actor(velocidad, direccion, posicionInicial) {
    fun getInsance(): Lobo {
        TODO("not implemented")
    }

    /**
     *
     */
    override fun mover(): Posicion {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * TODO: Método en principio no utilizado por Lobo
     * QUIZÀ PARA LOS MUROS
     */
    override fun tratarColision() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*
     * EL lobo no deberia ser notificado nunca para detectar una colision, si fuese así seria diferente al resto de
     * objetos.
     *
    override fun detectarColision(posicionLobo: Posicion) {

    }*/
}