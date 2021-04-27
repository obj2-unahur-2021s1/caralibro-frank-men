package ar.edu.unahur.obj2.caralibro

import java.security.KeyStore
import kotlin.math.ceil


//--------------------------------------------PUBLICACION-BASE---/
abstract class Publicacion() {
    var permisoPublicacion: String = "publico"
    lateinit var usuarioPublicacion: Usuario
    var usuariosDeMeGusta = mutableListOf<Usuario>()

    abstract fun espacioQueOcupa(): Int

    fun recibirMeGusta(usuario: Usuario){
        //Si el usuario ya dio me gusta, tira error, sino, lo agrega.
        if (usuariosDeMeGusta.contains(usuario)){
            throw Exception("El usuario ya ha dado me gusta.")
        }
        usuariosDeMeGusta.add(usuario)
    }

    fun cantidadDeMeGusta() = usuariosDeMeGusta.size

    fun cambiarPermiso(nuevoPermiso: String) {
        //Asigna un nuevo permiso.
        permisoPublicacion = nuevoPermiso.toLowerCase()
    }

    fun puedeSerVistaPor(usuario: Usuario): Boolean {
        //Devuelve booleano, indica si un usuario determinado puede ver la publicacion.
        //esto lo hace utilizando los permisos.
        if (usuario == usuarioPublicacion) { return true }
        if (permisoPublicacion == "publico") { return permisoPublico(usuario) }
        if (permisoPublicacion == "solo amigos") { return permisoSoloAmigos(usuario) }
        if (permisoPublicacion == "privado con permitidos") { return permisoPrivadoConPermitidos(usuario) }
        if (permisoPublicacion == "publico con excluidos") { return permisoPublicoConExcluidos(usuario) }
        else { throw Exception("El tipo de permiso no es valido.") }
    }

    //----------------------PERMISOS----/
    fun permisoPublico(usuario: Usuario) = true
    fun permisoSoloAmigos(usuario: Usuario) = usuarioPublicacion.amigos.contains(usuario)
    fun permisoPrivadoConPermitidos(usuario: Usuario) = usuarioPublicacion.listaDePermitidos.contains(usuario)
    fun permisoPublicoConExcluidos(usuario: Usuario) = !usuarioPublicacion.listaDeExcluidos.contains(usuario)

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














