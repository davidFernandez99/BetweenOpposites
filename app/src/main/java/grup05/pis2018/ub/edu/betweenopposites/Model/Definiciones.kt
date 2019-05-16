package grup05.pis2018.ub.edu.betweenopposites.Model

/**
 * Contiene dimensiones de objetos, salas...
 */
enum class Dimension(
    val height_en_bloques: Int,
    val width_en_bloques: Int,
    val height: Float = (height_en_bloques * 32).toFloat(),
    val width: Float = width_en_bloques.toFloat()
) {
    bloque(1, 1, 32f, 32f),
    muro(1, 1, 32f, 32f),
    puerta(1, 1, 32f, 32f),
    suelo(1, 1, 32f, 32f),
    sala(10, 20, 320f, 640f)
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