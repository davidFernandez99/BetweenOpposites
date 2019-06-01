package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.Canvas
import grup05.pis2018.ub.edu.betweenopposites.Game.GameEngine

class SalaEspecial(id_sala: Int, matrixSala: Array<Array<Objeto?>>) : Sala(id_sala, matrixSala) {
    var maquina: Maquina

    init{
        // AL crearse la sala especial crea una maquina en el centro
        maquina = Maquina(Dimension.maquina.height,Dimension.maquina.width,Posicion(
            x_sala=(Dimension.sala.height_en_bloques/2).toInt(),y_sala=(Dimension.sala.width_en_bloques/4).toInt()))
        anadirObjeto(maquina)
    }
    override fun update(fps:Long){
        Lobo.instance!!.mover(fps)
        for (objeto: Objeto in this.objetos) {
            if(objeto is Maquina){
                var maquina:Maquina= objeto as Maquina
                maquinaSala=maquina
                comprobar_colision_maquina=maquina!!.detectarColision(Lobo.instance!!)
                if(comprobar_colision_maquina==true){
                    //Abrir opciones si no se ha hecho antes
                    if(maquina!!.dar_opciones==true){
                        Facade.dando_opciones=true
                        Facade.opciones=maquina!!.darOpciones(Lobo.instance!!)

                    }

                }
            }
        }
        if(Facade.comprobar_opcion==true){

            comprobar_opcion_corecta=Maquina.comprobarRespuestaMaquina(Facade.opciones!!.get(Facade.opcion))
            if(comprobar_opcion_corecta==true){
                objetoMaquina=maquinaSala!!.darRecompensa()
                Facade.comprobar_opcion=false

            }
            else if(comprobar_opcion_corecta==false){
                Facade.fallar=true
                Facade.comprobar_opcion=false
            }
        }
        if(objetoMaquina!=null){
            if(objetoMaquina!!.es_visible==true){
                objetoMaquina!!.detectarColision(Lobo.instance!!)
            }

        }
        for (puerta:Puerta in this.puertas){
            puerta.detectarColision(Lobo.instance)
        }
        for (muro:Muro in this.muros) {
            muro.detectarColision(Lobo.instance)
        }
    }
     override fun draw(canvas: Canvas, contexto: Context){
        //Dibujamos muros y suelos
         for (j in 0..matrixSala.size-1) {
             for (i in 0..matrixSala[j].size-1) {
                 var objeto:Objeto=getObjetofromSala(i,j)
                 if(objeto is Muro){
                     objeto.draw(canvas, GameEngine.bitmapMuro!!)
                 }
                 if(objeto is Suelo){
                     objeto.draw(canvas, GameEngine.bitmapSuelo!!)
                 }

             }
         }
         // Dibujamos objetos en la sala
         for (objeto: Objeto in this.objetos) {

             if(objeto is Maquina){
                 objeto.draw(canvas , GameEngine.bitmapMaquina!!)
             }


         }

         if(objetoMaquina!=null){
             if(objetoMaquina is AumentarVelocidad){
                 if(objetoMaquina!!.es_visible==true){
                     objetoMaquina!!.draw(canvas!!, GameEngine.bitmapAumentoVel!!)
                 }

             }
             else if (objetoMaquina is Invisibilidad){
                 if(objetoMaquina!!.es_visible==true){
                     objetoMaquina!!.draw(canvas!!, GameEngine.bitmapInv!!)
                 }
             }
             else{
                 if(objetoMaquina!!.es_visible==true){
                     objetoMaquina!!.draw(canvas!!, GameEngine.bitmapCambio!!)
                 }

             }

         }
         for(puerta:Puerta in this.puertas){
             puerta.draw(canvas, GameEngine.bitmapPuerta!!)
         }
         Lobo.instance.draw(canvas, GameEngine.bitmapOrbeRaro!!)
    }

}