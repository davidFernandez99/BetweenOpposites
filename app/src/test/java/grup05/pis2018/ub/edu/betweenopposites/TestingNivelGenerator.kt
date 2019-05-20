package grup05.pis2018.ub.edu.betweenopposites

import grup05.pis2018.ub.edu.betweenopposites.Model.FactoryNiveles
import grup05.pis2018.ub.edu.betweenopposites.Model.Nivel
import grup05.pis2018.ub.edu.betweenopposites.Model.Plantilla
import org.junit.Test

import kotlin.system.measureTimeMillis

class TestingNivelGenerator {

    @Test
    fun tiempo(){
        val tiempo_final= measureTimeMillis{testingCreacionDeNiveles()}
        println("Tiempo: ${tiempo_final} ms")
    }

    @Test
    fun testingCreacionDeNiveles(){
        // Creo un nivel
        var nivel: Nivel = FactoryNiveles.crearNivelfromTXT(1,5,2, Plantilla.salaBasica.listaPlantillas,Plantilla.salaEspecial.listaPlantillas,Plantilla.salaFinal.listaPlantillas)
        println(nivel.printNivel())
    }

}