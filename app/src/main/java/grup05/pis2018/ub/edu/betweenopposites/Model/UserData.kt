package grup05.pis2018.ub.edu.betweenopposites.Model


class UserData( userID:String,userName:String, userE:String, puntuacion: Int) {
    var userID:String=userID
    var userName:String=userName
    var userE:String=userE
    var puntuacion:Int=puntuacion
    constructor() : this("","","", 0 ){

    }
    companion object {

        var instance :UserData= UserData("","","",0)

    }
}