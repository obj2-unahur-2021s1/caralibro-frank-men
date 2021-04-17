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

    override fun pesoPublicacion(): Int = ceil((alto * ancho * factorDeCompresion)).toInt()
}


val fotito = Foto(1280,720)
println(fotito.pesoPublicacion())