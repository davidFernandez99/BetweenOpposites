package grup05.pis2018.ub.edu.betweenopposites.Model

import android.os.CountDownTimer
import java.util.*

class Tiempo (millisInFuture:Long, countDownInterval:Long):CountDownTimer (millisInFuture,countDownInterval){
    var finish: Boolean=false
    var time: Long = millisInFuture
    override fun onFinish() {
        finish=true
    }

    override fun onTick(millisUntilFinished: Long) {
        if(millisUntilFinished<=0){
            finish=true
        }
    }


}