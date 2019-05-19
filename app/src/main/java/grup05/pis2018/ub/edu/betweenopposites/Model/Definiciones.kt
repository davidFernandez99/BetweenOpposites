package grup05.pis2018.ub.edu.betweenopposites.Model

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
    muro(1, 1, 64f, 64f),
    puerta(1, 1, 64f, 64f),
    suelo(1, 1, 64f, 64f),
    sala(10, 20, 640f, 1280f),
    orbe(1, 1, 64f, 64f),
    trampa(1, 1, 64f, 64f),
    sumador(1,1,64f,64f),
    multiplicador(1,1,64f,64f)
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
    salaBasica_01(""),
    salaBasica_02(""),
    salaBasica_03(""),
    salaBasica_04(""),
    salaBasica_05(""),
    salaEspecial_01(""),
    salaFinal_01("")
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
                NombreFicheros.salaBasica_01.filename,
                NombreFicheros.salaBasica_02.filename,
                NombreFicheros.salaBasica_03.filename,
                NombreFicheros.salaBasica_04.filename,
                NombreFicheros.salaBasica_05.filename
            )
        )
    ),
    salaEspecial(ArrayList(listOf(NombreFicheros.salaEspecial_01.filename))),
    salaFinal(ArrayList(listOf(NombreFicheros.salaFinal_01.filename)))
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
    baja(50f, (2..3), (0..1),(0..2),(1..2),(0..1),(1..1)),
    media(75f, (3..4), (1..2),(1..3),(1..4),(0..2),(1..1)),
    alta(100f, (5..6), (2..3),(2..4),(1..5),(0..2),(1..1))
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
    Blanco, Negro
}