package ar.edu.unahur.obj2.caralibro

import java.security.KeyStore
import kotlin.math.ceil


//--------------------------------------------PUBLICACION-BASE---/
abstract class Publicacion() {
    var usuariosDeMeGusta = mutableListOf<Usuario>()
    var permisoPublicacion: String = "Publico"

    abstract fun espacioQueOcupa(): Int

    fun cambiarPermiso(nuevoPermiso: String){
        permisoPublicacion = nuevoPermiso
    }
    fun permisoPublicacion(): String = permisoPublicacion

/*    fun admiteAUsuario(usuario: Usuario) = permisoPublicacion.contieneAUsuario(usuario)
    fun recibirMeGusta(usuario: Usuario){
        //usar check
        if (!(usuariosDeMeGusta.contains(usuario))){
            usuariosDeMeGusta.add(usuario)
        }
        else{
            throw Exception("El usuario ya ha dado like.")
        }
    }
    fun cantidadDeMeGusta() = usuariosDeMeGusta.size

 */
}




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
        if (nuevaCalidad.toLowerCase() == "sd")     {calidadVideo = SD(this) }
        if (nuevaCalidad.toLowerCase() == "hd720")  {calidadVideo = HD720(this)}
        if (nuevaCalidad.toLowerCase() == "hd1080") {calidadVideo = HD1080(this)}
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







//----------------------------------PERMISOS-------------------/














