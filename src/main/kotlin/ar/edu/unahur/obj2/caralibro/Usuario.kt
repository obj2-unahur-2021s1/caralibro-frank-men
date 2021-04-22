package ar.edu.unahur.obj2.caralibro

class Usuario {
    var publicaciones = mutableListOf<Publicacion>()
    var amigos = mutableListOf<Usuario>()


    fun crearPublicacion(tipoPublicacion: Publicacion){

    }

    fun puedeVerPublicacion(publicacion: Publicacion): Boolean{
        // para que un usuario pueda ver una publicacion, tiene que
        // estar incluido entre los usuarios del permiso de la publicacion.
        return publicacion.admiteAUsuario(this)
    }

    fun darMeGusta(publicacion: Publicacion){
        if (this.puedeVerPublicacion(publicacion)){
            publicacion.recibirMeGusta(this)
        }
    }

    fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }
}