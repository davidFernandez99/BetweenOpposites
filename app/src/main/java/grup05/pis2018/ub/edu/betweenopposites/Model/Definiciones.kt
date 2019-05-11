package grup05.pis2018.ub.edu.betweenopposites.Model

import android.util.Half.toFloat

/**
 * Contiene dimensiones de objetos, salas...
 */
enum class Dimension(
    val height_en_bloques: Int,
    val width_en_bloques: Int,
    val height: Float = (height_en_bloques * 32).toFloat(),
    val width: Float = width_en_bloques.toFloat()
) {
    bloque(1, 1),
    muro(1, 1),
    puerta(1, 1),
    suelo(1, 1),
    sala(10, 20)
}

/**
 * Defino un enum que identifica
 */
enum class Descifrar(val char: String) {
    separacion(","),
    muro("M"),
    suelo("_"),
    puerta("P")
}