package grup05.pis2018.ub.edu.betweenopposites.Model

class SalaEspecial(id_sala: Int, matrixSala: Array<Array<Objeto?>>) : Sala(id_sala, matrixSala) {
    lateinit var maquina: Maquina
}