package grup05.pis2018.ub.edu.betweenopposites.Model


class Muro(
    height: Float,
    width: Float,
    posicion: Posicion
) : Objeto(height, width, posicion) {


    /**
     * Un Actor puede colisionar con un muro. CUando lo haga depende de el objeto harà un cambio u otro.
     */
    override fun tratarColision(objeto: Objeto) {
        if (objeto is Lobo) {
            var lobo: Lobo = objeto as Lobo
            lobo.velocidad = 0f
        }
        if(objeto is Orbe){
            var orbe: Orbe = objeto as Orbe
            orbe.canviarDireccion()
        }
    }

    /*override fun detectarColision(objeto: Objeto): Boolean {
        if(objeto is Lobo){
            var lobo: Lobo=objeto as Lobo
            if(lobo.direccion==Actor.Direccion.ARRIBA){

            }
            if(lobo.direccion==Actor.Direccion.ABAJO){

            }
            if(lobo.direccion==Actor.Direccion.IZQUIERDA){

            }
            if(lobo.direccion==Actor.Direccion.DERECHA){

            }
        }
        if(objeto is Orbe){
            var orbe:Orbe= objeto as Orbe
            if(orbe.direccion==Actor.Direccion.ARRIBA){

            }
            if(orbe.direccion==Actor.Direccion.ABAJO){

            }
            if(orbe.direccion==Actor.Direccion.IZQUIERDA){

            }
            if(orbe.direccion==Actor.Direccion.DERECHA){

            }
        }
        return false
    }*/



}