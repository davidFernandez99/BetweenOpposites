package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import grup05.pis2018.ub.edu.betweenopposites.R
import kotlinx.android.synthetic.main.activity_ranking.view.*
import org.w3c.dom.Text

class UserAdapter(val ctx : Context, val layoutResId: Int, val userList : List<UserData>)
    : ArrayAdapter<UserData>(ctx, layoutResId, userList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(ctx)
        val view : View = layoutInflater.inflate(layoutResId, null)

        val textViewName = view.findViewById<TextView>(R.id.nombreJugador_rank)
        //val textViewPuntuacion  = view.findViewById<TextView>(R.id.txt_puntuacion)
        //val textPunts = view.findViewById<TextView>(R.id.txt_palabraPuntos)

        val user = userList[position]

        textViewName.text=user.userName
        //textViewPuntuacion.text = user.puntuacion.toString()
        //textPunts.text="punts"

        return view
    }
}