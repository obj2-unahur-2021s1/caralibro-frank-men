package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil

abstract class Publicacion {
    abstract fun espacioQueOcupa(): Int
    abstract fun meGusta()
    abstract fun cantidadDeMeGusta(): Int

}
object FactorDeCompresion{
    var coeficiente = 0.7
    fun modificarFactor(nuevoFactor: Double) {
        coeficiente = nuevoFactor
    }
}

class Foto(val alto: Int, val ancho: Int) : Publicacion() {
    var cantidadDeMeGusta = 0

    override fun espacioQueOcupa() = ceil(alto * ancho * FactorDeCompresion.coeficiente).toInt()
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