package com.example.jeudechecs

import android.content.Context
import android.graphics.*

class roi(nom : String, position : case, couleur : String, joueur: Joueur): pièce(nom, position, couleur,joueur){
    override var point = 10
    override fun deplacement(nouvelleposition : case, liste_de_case : MutableList<MutableList<case>>): Boolean{
        if (position.absysse-nouvelleposition.absysse < -1 || position.absysse-nouvelleposition.absysse > 1){
            println("Le roi ne se déplace pas ainsi")
            return false
        }
        else if (position.ordonnee-nouvelleposition.ordonnee < -1 || position.ordonnee-nouvelleposition.ordonnee > 1){
            println("Le roi ne se déplace pas ainsi")
            return false
        }
        else {
            if (nouvelleposition.occupant?.couleur == couleur){
                return false
            }
            else {
                super.deplacement(nouvelleposition, liste_de_case)
                return true
            }
        }
    }

    override fun draw(canvas: Canvas, X1: Float, Y1: Float, X2: Float, Y2: Float, context: Context){
        val r = RectF(X1, Y2, X2, Y1)
        var bitmap: Bitmap

        if (this.couleur == "blanc"){
            bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.roi_b)
        }
        else {
            bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.roi_n)
        }
        canvas.drawBitmap(bitmap, null, r, null)
    }

    override fun deplacement2(nouvelleposition : case, liste_de_case : MutableList<MutableList<case>>): Boolean{
        if (position.absysse-nouvelleposition.absysse < -1 || position.absysse-nouvelleposition.absysse > 1){
            println("Le roi ne se déplace pas ainsi")
            return false
        }
        else if (position.ordonnee-nouvelleposition.ordonnee < -1 || position.ordonnee-nouvelleposition.ordonnee > 1){
            println("Le roi ne se déplace pas ainsi")
            return false
        }
        else {
            if (nouvelleposition.occupant?.couleur == couleur){
                return false
            }
            else {
                return true
            }
        }
    }
}