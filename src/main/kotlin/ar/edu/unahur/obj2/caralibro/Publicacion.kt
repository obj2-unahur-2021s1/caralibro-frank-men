package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil
import kotlin.math.roundToInt

abstract class Publicacion {
    abstract fun pesoPublicacion(): Int
}

class Texto(val textoPublicacion: String): Publicacion() {

    override fun pesoPublicacion() = this.textoPublicacion.length
}

class Foto(val alto: Int,val ancho: Int,val factorDeCompresion: Double = 0.7): Publicacion(){

    override fun pesoPublicacion(): Int = Math.ceil(
        alto * ancho * factorDeCompresion
    ).toInt()
}
