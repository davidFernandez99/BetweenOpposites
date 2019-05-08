package grup05.pis2018.ub.edu.betweenopposites.Model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.RectF
import grup05.pis2018.ub.edu.betweenopposites.R

class Lobo(context: Context, posX: Float, posY: Float, bando: Int,
           height: Float, width: Float, velocidad: Float, direccion: Direccion,
           posicionInicial: Posicion) : Actor(height,width,velocidad, direccion, posicionInicial) {

    var bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.suelo_Basico)

    //Medidas lobo
    val width = posX / 20f
    private val height = posY / 20f

    //Mantiene la posicion del lobo
     val position = RectF(
        posX / 2f,
        posY-height,
        posX/2 + width,
        posY.toFloat())

    //This will hold the pixels per second speed that the wolf will move
    private val speed = 300f

    // This data is accessible using ClassName.propertyName
    companion object {
        // Which ways can the wolf move
        const val stopped = 0
        const val left = 1
        const val right = 2
    }

    // Is the wolf moving and in which direction
    // Start off stopped
    var moving = stopped

    init{
        // stretch the bitmap to a size
        // appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap,
            width.toInt() ,
            height.toInt() ,
            false)
    }

    // This update method will be called from update in
    // KotlinInvadersView It determines if the player's
    // wolf needs to move and changes the coordinates
    fun update(fps: Long) {
        // Move as long as it doesn't try and leave the screen
        if (moving == left && position.left > 0) {
            position.left -= speed / fps
        }

        else if (moving == right && position.left < posX - width) {
            position.left += speed / fps
        }

        position.right = position.left + width
    }

}
