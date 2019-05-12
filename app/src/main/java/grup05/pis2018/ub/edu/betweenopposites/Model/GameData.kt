package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Contiene la información que se maneja durante una partida, todos los datos necesarios para
 * hacer funcionar el Juego.
 * El conjunto de niveles, el Lobo...
 */
class GameData {
    // Tiene una referencia directa a la salaActiva en este momento
    lateinit var salaActiva: Sala

    // Mantiene los niveles
    lateinit var conjuntoNiveles: ConjuntoNiveles

    // Tiene una referencia directa al objeto Lobo
    lateinit var lobo: Lobo

    /**
     * Crea el conjunto de niveles
     */
    fun crearConjuntoNiveles() {
        // Creamos el conjunto de niveles
        conjuntoNiveles = ConjuntoNiveles()
    }
}