package grup05.pis2018.ub.edu.betweenopposites.Model

class SalaEspecial(id_sala: Int, matrixSala: Array<Array<Objeto?>>) : Sala(id_sala, matrixSala) {
    var maquina: Maquina

    init{
        // AL crearse la sala especial crea una maquina en el centro
        maquina = Maquina(Dimension.maquina.height,Dimension.maquina.width,Posicion(
            x_sala=(Dimension.sala.height_en_bloques/2).toInt(),y_sala=(Dimension.sala.width_en_bloques/2).toInt()))
    }

}