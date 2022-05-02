package com.example.jeudechecs

import android.content.Context
import android.graphics.*

class tour(nom : String, position : case, couleur : String, joueur: Joueur): pièce(nom, position, couleur,joueur){
    override var point = 5
    override fun deplacement(nouvelleposition : case, liste_de_case : MutableList<MutableList<case>>): Boolean{
        if (nouvelleposition.ordonnee == position.ordonnee){
            for (i in minOf(position.absysse, nouvelleposition.absysse)+1..maxOf(position.absysse, nouvelleposition.absysse)-1){
                if (liste_de_case[i][position.ordonnee].libre == false){
                    println("${liste_de_case[i][position.ordonnee].occupant?.nom} dans le passage")
                    return false
                }
            }
            if (nouvelleposition.occupant?.couleur == couleur){
                return false
            }
            else {
                super.deplacement(nouvelleposition, liste_de_case)
                return true
            }
        }
        else if (nouvelleposition.absysse == position.absysse){
            for (i in minOf(position.ordonnee, nouvelleposition.ordonnee)+1..maxOf(position.ordonnee, nouvelleposition.ordonnee)-1){
                if (liste_de_case[position.absysse][i].libre == false){
                    println("${liste_de_case[position.absysse][i].occupant?.nom} dans le passage")
                    return false
                }
            }
            if (nouvelleposition.occupant?.couleur == couleur){
                return false
            }
            else {
                super.deplacement(nouvelleposition, liste_de_case)
                return true
            }
        }
        else {
            println("La tour ne se déplace pas en diagonale")
            return false
        }
    }

    override fun draw(canvas: Canvas, X1: Float, Y1: Float, X2: Float, Y2: Float, context: Context){
        val r = RectF(X1 - 20, Y2, X2, Y1)
        var bitmap: Bitmap

        if (this.couleur == "blanc"){
            bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.tour_b)
        }
        else {
            bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.tour_n)
        }
        canvas.drawBitmap(bitmap, null, r, null)
    }

    override fun deplacement2(nouvelleposition : case, liste_de_case : MutableList<MutableList<case>>): Boolean{
        if (nouvelleposition.ordonnee == position.ordonnee){
            for (i in minOf(position.absysse, nouvelleposition.absysse)+1..maxOf(position.absysse, nouvelleposition.absysse)-1){
                if (liste_de_case[i][position.ordonnee].libre == false){
                    println("${liste_de_case[i][position.ordonnee].occupant?.nom} dans le passage")
                    return false
                }
            }
            if (nouvelleposition.occupant?.couleur == couleur){
                return false
            }
            else {
                return true
            }
        }
        else if (nouvelleposition.absysse == position.absysse){
            for (i in minOf(position.ordonnee, nouvelleposition.ordonnee)+1..maxOf(position.ordonnee, nouvelleposition.ordonnee)-1){
                if (liste_de_case[position.absysse][i].libre == false){
                    println("${liste_de_case[position.absysse][i].occupant?.nom} dans le passage")
                    return false
                }
            }
            if (nouvelleposition.occupant?.couleur == couleur){
                return false
            }
            else {
                return true
            }
        }
        else {
            println("La tour ne se déplace pas en diagonale")
            return false
        }
    }
}