package grup05.pis2018.ub.edu.betweenopposites

import grup05.pis2018.ub.edu.betweenopposites.Model.*
import org.junit.Test
import kotlin.system.measureTimeMillis

class TestingObjectsGenerator {

    @Test
    fun tiempo(){
        val tiempo_final= measureTimeMillis{comprovarObjetos()}
        println("Tiempo: ${tiempo_final} ms")
    }

    @Test
    fun comprovarObjetos() {
        // Creo una sala a partir de un fichero
        var sala: Sala = FactorySala.crearSalaBasicadesdeTXT(1, 1, "/home/stephen/Desktop/MAZMORRA_2.txt")
        println("SE HA CREADO LA SALA")
        sala.printMatriz()
        println("SE HA ACABADO DE PRINTEAR LA MATRIZ\n\n\n")

        var posicionesLibres = sala.getPosicionesLibres()
        println("INICIALMENTE")
        println("POSICIONES LIBRES: $posicionesLibres")

        var objetos: ArrayList<Objeto> = FactoryObjetos.generarObjetos(1,posicionesLibres)
        println("OBJETOS CREADOS")
        println("POSICIONES LIBRES: $posicionesLibres")
        var orbes: ArrayList<Orbe> = FactoryObjetos.generarOrbes(1,posicionesLibres)
        println("ORBES CREADOS")
        println("POSICIONES LIBRES: $posicionesLibres\n\n")

        // Hago un prit  con la información de los orbes y objetos
        for(i in 0..objetos.size-1){
            val objeto : Objeto = objetos[i]
            val tipo : String = when{ objeto is Trampa -> "Trampa"
                objeto is Sumador -> "Sumador"
            objeto is Multiplicador -> "Multiplicador"
            else -> "Impossible"}
            println("${i+1}: Objeto $tipo POSICION: [${objeto.posicion.x_sala},${objeto.posicion.y_sala}]")
        }
        println()
        for(j in 0..orbes.size-1){
            val orbe : Orbe = orbes[j]
            println("${j+1}: Orbe ${orbe.bando.name} POSICION: [${orbe.posicion.x_sala},${orbe.posicion.y_sala}]")
        }
        println()

        println("Objetos: $objetos")
        println("Orbes: $orbes")

        sala.anadirObjetos(objetos)
        sala.anadirOrbes(orbes)
        println("HE ACABADO")
    }

}