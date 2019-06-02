package grup05.pis2018.ub.edu.betweenopposites.Model

import android.os.Environment

/**
 * Contiene dimensiones de objetos, salas...
 */
enum class Dimension(
    val height_en_bloques: Int,
    val width_en_bloques: Int,
    val height: Float = (height_en_bloques * 64).toFloat(),
    val width: Float = width_en_bloques* 64.toFloat()
) {
    bloque(1, 1, 64f, 64f),
    muro(1, 1, 32f, 32f),
    puerta(1, 1, 32f, 32f),
    suelo(1, 1, 32f, 32f),
    sala(15, 30, 640f, 1280f),
    orbe(1, 1, 32f, 32f),
    trampa(1, 1, 32f, 32f),
    sumador(1,1,16f,16f),
    multiplicador(1,1,16f,16f),
    maquina(4,4,64f,40f),
    lobo(1,1,30f,30f)
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

/**
 * Defino un enumm donde se ecuentran los nobres de las plantillas.
 */
enum class NombreFicheros(val filename: String) {
    salaBasica_01("plantillas/SALA_BASICA_1.txt"),
    salaEspecial_01("plantillas/SALA_ESPECIAL.txt"),
    salaFinal_01("plantillas/SALA_FINAL.txt"),
    salaFinal_02("plantillas/SALA_FINAL_2.txt"),
    salaFinal_03("plantillas/SALA_FINAL_3.txt"),
    salaFinal_04("plantillas/SALA_FINAL_4.txt"),
    salaFinal_05("plantillas/SALA_FINAL_5.txt"),
    salaFinal_06("plantillas/SALA_FINAL_6.txt"),
    salaFinal_07("plantillas/SALA_FINAL_7.txt"),
    salaFinal_08("plantillas/SALA_FINAL_8.txt")


}

/**
 * Guarda los ficheros en tres listas:
 *  plantillasSalaBasica
 *  plantillasSalaEspecial
 *  plantillasSalaFinal
 */
enum class Plantilla(val listaPlantillas: ArrayList<String>) {
    salaBasica(
        ArrayList(
            listOf(
                NombreFicheros.salaBasica_01.filename
            )
        )
    ),
    salaEspecial(ArrayList(listOf(NombreFicheros.salaEspecial_01.filename))),
    salaFinal(ArrayList(listOf(
        NombreFicheros.salaFinal_01.filename,
        NombreFicheros.salaFinal_02.filename,
        NombreFicheros.salaFinal_03.filename,
        NombreFicheros.salaFinal_04.filename,
        NombreFicheros.salaFinal_05.filename,
        NombreFicheros.salaFinal_06.filename,
        NombreFicheros.salaFinal_07.filename,
        NombreFicheros.salaFinal_08.filename)))
}


/**
 * Contiene un las dificultades
 */
enum class Dificultad(
    var velocidad_orbes: Float,
    var rango_numero_orbes: IntRange,
    var rango_num_trampas: IntRange,
    var rango_num_sumadores :IntRange,
    var rango_valores_sumador:IntRange,
    var rango_num_multiplicadores :IntRange,
    var rango_valores_multiplicador:IntRange
) {
    baja(100f, (4..6), (2..3),(2..4),(10..12),(0..1),(1..1)),
    media(125f, (6..10), (3..5),(4..6),(12..15),(0..2),(1..1)),
    alta(150f, (10..15), (5..7),(6..8),(15..17),(0..2),(1..1))
}

/**
 * Direcciones posibles para los actores
 */
enum class Direccion {
    ARRIBA, ABAJO, DERECHA, IZQUIERDA, PARADO
}

/**
 * Bando posible para Orbes y Lobo
 */
enum class Bando {
    Blanco, Negro, Neutro
}