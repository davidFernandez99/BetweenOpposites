package grup05.pis2018.ub.edu.betweenopposites.Model

class GameData {
    // Tiene una referencia directa a la salaActiva en este momento
    lateinit var salaActiva: Sala

    // Mantiene los niveles
    lateinit var conjuntoNiveles: ConjuntoNiveles

    // Tiene una referencia directa al objeto Lobo
    lateinit var lobo: Lobo
}