package com.example.jeudechecs

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0, var joueur1: Joueur = Joueur("", 0), var joueur2: Joueur = Joueur("", 0)): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback, Runnable {

    var jou1 : Joueur = joueur1
    var jou2 : Joueur = joueur2

    lateinit var canvas: Canvas
    lateinit var thread: Thread
    val backgroundPaint = Paint()
    var drawing: Boolean = true
    lateinit var plateau1 : plateau
    lateinit var liste_de_case : MutableList<MutableList<case>>
    var case_selec : case? = null
    val selecpaint = Paint()
    var ancientpaint = Paint()
    val truepaint1 = Paint()
    val truepaint2 = Paint()
    var couleur_de_jeu : String = "blanc"
    val couleur_text = Paint()


    init {
        backgroundPaint.color = Color.WHITE
        selecpaint.color = Color.RED
        truepaint1.color = Color.rgb(168, 50, 50)
        truepaint2.color = Color.rgb(245, 66, 66)
        couleur_text.color = Color.BLACK
        couleur_text.textSize = 50F

    }


    override fun onSizeChanged(w: Int,h: Int,oldw: Int,oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val canvasW = (w).toFloat()
        //val canvasH = (h).toFloat()
        plateau1 = plateau(8, 8, 0f, 0f + 200f, canvasW, canvasW + 200f, jou1, jou2)
        liste_de_case = plateau1.creer_liste_de_case()
    }

    fun pause() {
        drawing = false
        thread.join()
    }
    fun resume() {
        drawing = true
        thread = Thread(this)
        thread.start()

    }

    fun draw() {
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawRect(0F, 0F, canvas.getWidth()*1F,
                canvas.getHeight()*1F, backgroundPaint)
            plateau1.draw(canvas, liste_de_case, context)
            holder.unlockCanvasAndPost(canvas)

        }
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        var num = 0
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                val x = e.rawX.toInt() - 0
                val y = e.rawY.toInt() - 100
                if (case_selec == null){
                    for (i in liste_de_case){
                        for (j in i){
                            if (j.r.top >= y && j.r.bottom <= y){
                                if (j.r.left <= x && j.r.right >= x){
                                    if (j.libre == false && j.occupant?.couleur == couleur_de_jeu) {
                                        case_selec = j
                                        ancientpaint = j.couleur
                                        j.couleur = selecpaint
                                        for (k in liste_de_case){
                                            for (l in k){
                                                if (case_selec?.occupant?.deplacement2(l, liste_de_case) == true){
                                                    if ((l.absysse+l.ordonnee)%2==0){
                                                        l.couleur = truepaint1
                                                    }
                                                    else {
                                                        l.couleur = truepaint2
                                                    }
                                                }
                                            }
                                            num += 1
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else if (case_selec != null){
                    for (i in liste_de_case){
                        for (j in i){
                            j.vraicouleur()
                            if (j.r.top >= y && j.r.bottom <= y){
                                if (j.r.left <= x && j.r.right >= x){
                                    if (case_selec?.occupant?.deplacement(j, liste_de_case) == true){
                                        if (couleur_de_jeu == "blanc"){
                                            couleur_de_jeu = "noir"
                                        }
                                        else if (couleur_de_jeu == "noir"){
                                            couleur_de_jeu = "blanc"
                                        }
                                    }
                                    case_selec?.occupant?.deplacement(j, liste_de_case)
                                    case_selec?.couleur = ancientpaint
                                    case_selec = null

                                }
                            }
                        }
                    }
                }
            }
        }
        return true
    }

    override fun run() {
        while (drawing) {
            draw()
        }
    }

    override fun surfaceChanged(
        holder: SurfaceHolder, format: Int,
        width: Int, height: Int) {
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread = Thread(this)
        thread.start()
    }
    override fun surfaceDestroyed(holder: SurfaceHolder) {
        thread.join()
    }

}