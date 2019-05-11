package grup05.pis2018.ub.edu.betweenopposites.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import grup05.pis2018.ub.edu.betweenopposites.Presenter.Presenter
import grup05.pis2018.ub.edu.betweenopposites.R

class Ranking : AppCompatActivity(), View {

    lateinit var observers: ArrayList<Presenter>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
    }

    override fun addObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteObserver(presenter: Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun notifyObservers(fuente: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
