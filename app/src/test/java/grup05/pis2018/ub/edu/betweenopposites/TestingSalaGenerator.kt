package grup05.pis2018.ub.edu.betweenopposites

import android.util.Log
import grup05.pis2018.ub.edu.betweenopposites.Model.FactorySala
import grup05.pis2018.ub.edu.betweenopposites.Model.Sala
import grup05.pis2018.ub.edu.betweenopposites.Model.SalaBasica
import org.junit.Test
import kotlin.system.measureTimeMillis

class TestingSalaGenerator {

    /*@Test
    fun comprovarMatriz() {
        // Comprueva si la matriz se está creando correctamante a partir del fichero
        var sala: Sala = FactorySala.crearSalaBasicadesdeTXT(1, 1, "\\Users\\david\\Desktop\\MAZMORRA.txt")
        println("SE HA CREADO LA SALA")
        sala.printMatriz()
        print("SE HA ACABADO DE PRINTEAR LA MATRIZ")
    }*/


    @Test
    fun time(){
        for(i in (0..100)){
        val tiempo_final= measureTimeMillis{FactorySala.crearSalaBasicaAutomaticamente(1,i,2)}
        println(tiempo_final)
        }
    }

    @Test
    fun comprovarMatrizAutogenerada(){
        for(i in (0..50)){
            var sala: SalaBasica = FactorySala.crearSalaBasicaAutomaticamente(1,i,2)
            sala.printMatriz()
        }
    }
}