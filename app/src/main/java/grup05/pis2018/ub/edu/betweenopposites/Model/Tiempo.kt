package grup05.pis2018.ub.edu.betweenopposites.Model

import android.os.CountDownTimer

class Tiempo (time:Long,countdown:Long):CountDownTimer (time,countdown) {
    //Variable para determinar si el timer a llegado o no a su fin
    var finish:Boolean=false
    override fun onFinish() { //Al acabar el timer establece la variable finish como true para que se pueda detectar que ha terminado
        finish=true

    }

    override fun onTick(millisUntilFinished: Long) {

    }
}