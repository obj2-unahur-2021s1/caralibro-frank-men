package ar.edu.unahur.obj2.caralibro

import java.time.Duration
import kotlin.math.ceil
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer


//--------------------------------------------PUBLICACION-BASE---/
abstract class Publicacion(usuario: Usuario) {
    abstract fun espacioQueOcupa(): Int
    lateinit var permisoPublicacion: Permiso
    var usuariosDeMeGusta = mutableListOf<Usuario>()
    fun cambiarPermiso(nuevoPermiso: Permiso){
        permisoPublicacion = nuevoPermiso
    }

    fun admiteAUsuario(usuario: Usuario) = permisoPublicacion.contieneAUsuario(usuario)

    fun recibirMeGusta(usuario: Usuario){
        if (!(usuariosDeMeGusta.contains(usuario))){
            usuariosDeMeGusta.add(usuario)
        }
        else{
            throw Exception("El usuario ya ha dado like.")
        }
    }

    fun cantidadDeMeGusta() = usuariosDeMeGusta.size
}

//----------------------------------PERMISOS-------------------/
abstract class Permiso {
    abstract fun quienPuedeVer(publicacion: Publicacion): List<Usuario>
    fun contieneAUsuario(usuario: Usuario) = this.quienPuedeVer().contains(usuario)
}

//----------------------permisitos---/
class publico(): Permiso(){
    override fun quienPuedeVer(publicacion: Publicacion): List<Usuario> {

    }
}
class soloAmigos(): Permiso(){}
class privadoConPermitidos(): Permiso(){}
class privadoConExcluidos(): Permiso(){}



//--------------------------FACTOR-COMPRESION
object FactorDeCompresion{
    var coeficiente = 0.7
    fun modificarFactor(nuevoFactor: Double) {
        coeficiente = nuevoFactor
    }
}
//----------------------------SUBCLASE-FOTO
class Foto(val alto: Int, val ancho: Int) : Publicacion() {

    override fun espacioQueOcupa() = ceil(alto * ancho * FactorDeCompresion.coeficiente).toInt()
}
//----------------------------SUBCLASE-TEXTO
class Texto(val contenido: String) : Publicacion() {

    override fun espacioQueOcupa() = contenido.length
}

//----------------------------SUBCLASE-VIDEO
class Video(val duracion: Int, var calidad: String): Publicacion(){
    var calidadVideo :Calidades = SD(this)
    init {
        if (calidad.toLowerCase() == "hd720"){
            calidadVideo = HD720(this)
        }
        if (calidad.toLowerCase() == "hd1080"){
            calidadVideo = HD1080(this)
        }
    }
    fun cambiarCalidad(nuevaCalidad: String){
        if (nuevaCalidad.toLowerCase() == "sd")       {calidadVideo = SD(this) }
        if (nuevaCalidad.toLowerCase() == "hd720")    {calidadVideo = HD720(this)}
        if (nuevaCalidad.toLowerCase() == "hd1080")   {calidadVideo = HD1080(this)}
    }
    override fun espacioQueOcupa() = calidadVideo.devolverCalidad()
    }

//--------calidad abstracta-----------------------------------//

abstract class Calidades(){
    abstract fun devolverCalidad(): Int
}

//----------------------calidades-en-si---------//
class SD(var video: Video): Calidades()     { override fun devolverCalidad() = video.duracion }
class HD720(var video: Video): Calidades()  { override fun devolverCalidad() = video.duracion * 3 }
class HD1080(var video: Video): Calidades() { override fun devolverCalidad() = video.duracion * 6 }
//-----------------------------------------------------------//





