package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil
import kotlin.math.roundToInt

abstract class Publicacion {
    abstract fun espacioQueOcupa(): Int
    abstract fun meGusta()
    abstract fun cantidadDeMeGusta(): Int

}

class Foto(val alto: Int, val ancho: Int) : Publicacion() {
    val factorDeCompresion = 0.7
    var cantidadDeMeGusta = 0

    override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()
    override fun meGusta() {
        cantidadDeMeGusta += 1
    }
    override fun cantidadDeMeGusta() = cantidadDeMeGusta
}

class Texto(val contenido: String) : Publicacion() {
    var cantidadDeMeGusta = 0

    override fun espacioQueOcupa() = contenido.length
    override fun meGusta() {
        cantidadDeMeGusta += 1
    }
    override fun cantidadDeMeGusta() = cantidadDeMeGusta
}