package ar.edu.unahur.obj2.caralibro

class Usuario {
    var publicaciones = mutableListOf<Publicacion>()
    var amigos = mutableListOf<Usuario>()
    var listaDePermitidos = mutableListOf<Usuario>()
    var listaDeExcluidos = mutableListOf<Usuario>()
    // var registroDeMeGusta: MutableMap<Usuario,Publicacion> = mutableMapOf() es idea no implementada

    fun agregarPublicacion(publicacion: Publicacion) {
        publicaciones.add(publicacion)
    }
    fun agregarAmigo(amigo: Usuario) {
        amigos.add(amigo)
    }
    fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }

    fun puedeDarMeGusta(publicacion: Publicacion, usuario: Usuario) =
        (amigos.contains(usuario) && this.estaPublicacionYTienePermisos(publicacion,usuario )) || usuario==this

    private fun estaPublicacionYTienePermisos(publicacion: Publicacion, usuario: Usuario) =
        usuario.contiene(publicacion) && this.validarPermiso(usuario, publicacion)
    private fun contiene(publicacion: Publicacion) = publicaciones.contains(publicacion)

    private fun validarPermiso(usuario: Usuario, publicacion: Publicacion) =
        this.publico(publicacion) || this.soloAmigos(publicacion,usuario) ||
                this.privadaConListaDePermitidos(publicacion,usuario) || this.publicoConListaDeExcluidos(publicacion,usuario)

    private fun publico(publicacion: Publicacion) = (publicacion.permisoPublicacion == "Publico")
    private fun soloAmigos(publicacion: Publicacion, usuario: Usuario) =
        (publicacion.permisoPublicacion == "SoloAmigos") && (amigos.contains(usuario))
    private fun privadaConListaDePermitidos(publicacion: Publicacion, usuario: Usuario) =
        publicacion.permisoPublicacion == "PrivadaConPermitidos" && this.estaEnListaDePermitidos(usuario)
    private fun publicoConListaDeExcluidos(publicacion: Publicacion, usuario: Usuario) =
        publicacion.permisoPublicacion == "PublicoConExcluidos" && !this.estaEnListaDeExcluidos(usuario)

    private fun estaEnListaDePermitidos(usuario: Usuario) = listaDePermitidos.contains(usuario)
    private fun estaEnListaDeExcluidos(usuario: Usuario) = listaDeExcluidos.contains(usuario)


//    fun crearPublicacion(tipoPublicacion: Publicacion){
//    }
/*
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

 */


}