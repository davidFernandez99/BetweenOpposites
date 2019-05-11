package grup05.pis2018.ub.edu.betweenopposites.Model

import android.os.CountDownTimer

class Tiempo (time:Long,countdown:Long):CountDownTimer (time,countdown) {
    var finish:Boolean=false
    override fun onFinish() {
        finish=true
    }

    override fun onTick(millisUntilFinished: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}