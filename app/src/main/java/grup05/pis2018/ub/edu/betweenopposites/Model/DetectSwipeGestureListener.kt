package com.dev2qa.gestureexample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.GestureDetector
import android.view.MotionEvent
import grup05.pis2018.ub.edu.betweenopposites.Model.Actor
import grup05.pis2018.ub.edu.betweenopposites.Model.Lobo
import grup05.pis2018.ub.edu.betweenopposites.Model.Posicion
import grup05.pis2018.ub.edu.betweenopposites.Model.Vida
import grup05.pis2018.ub.edu.betweenopposites.R
import grup05.pis2018.ub.edu.betweenopposites.View.UnJugador

/**
 * Created by Jerry on 4/18/2018.
 */

class DetectSwipeGestureListener : GestureDetector.SimpleOnGestureListener() {

    var lobo: Lobo = Lobo.instance


    //Método que se llamará cuando detecte los swipes
    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

        // Get swipe delta value in x axis.
        val deltaX = e1.x - e2.x

        // Get swipe delta value in y axis.
        val deltaY = e1.y - e2.y

        // Get absolute value.
        val deltaXAbs = Math.abs(deltaX)
        val deltaYAbs = Math.abs(deltaY)

        // Solo cuando la distáncia detectada del swipe es mayor a la minima establecida y menor a la maxima
        if (deltaXAbs >= MIN_SWIPE_DISTANCE_X && deltaXAbs <= MAX_SWIPE_DISTANCE_X) {
            if (lobo.velocidad == 0f) {
                lobo.restarurarVelocidad()
                //Método para restaurar la velocidad cuando deja de colisionar con el muro para que si colisionamos con un muro con el aumento de velocidad siga con ese aumento hasta que pase cierto tiempo
            }
            if (deltaX > 0) {
                if(lobo.direccionIvalida!= Actor.Direccion.IZQUIERDA){
                    lobo.direccion = Actor.Direccion.IZQUIERDA
                }

            } else {
                if(lobo.direccionIvalida!= Actor.Direccion.DERECHA){
                    lobo.direccion = Actor.Direccion.DERECHA
                }
            }
        }

        if (deltaYAbs >= MIN_SWIPE_DISTANCE_Y && deltaYAbs <= MAX_SWIPE_DISTANCE_Y) {
            if (lobo.velocidad == 0f) {
                lobo.restarurarVelocidad()
                //Método para restaurar la velocidad cuando deja de colisionar con el muro para que si colisionamos con un muro con el aumento de velocidad siga con ese aumento hasta que pase cierto tiempo
            }
            if (deltaY > 0) {
                if(lobo.direccionIvalida!= Actor.Direccion.ARRIBA){
                    lobo.direccion = Actor.Direccion.ARRIBA
                }
            } else {
                if(lobo.direccionIvalida!= Actor.Direccion.ABAJO){
                    lobo.direccion = Actor.Direccion.ABAJO
                }
            }
        }


        return true
    }

    // Invoked when single tap screen.


    companion object {

        // Minimal x and y axis swipe distance.
        private val MIN_SWIPE_DISTANCE_X = 100
        private val MIN_SWIPE_DISTANCE_Y = 100

        // Maximal x and y axis swipe distance.
        private val MAX_SWIPE_DISTANCE_X = 1000
        private val MAX_SWIPE_DISTANCE_Y = 1000
    }
}