package ar.edu.unahur.obj2.caralibro

class Usuario {
    var publicaciones = mutableListOf<Publicacion>()
    var amigos = mutableListOf<Usuario>()
    var listaDePermitidos = mutableListOf<Usuario>()
    var listaDeExcluidos = mutableListOf<Usuario>()
    var registroDeMeGusta: MutableMap<Publicacion, Usuario> = mutableMapOf()

    fun agregarPublicacion(publicacion: Publicacion) {
        publicaciones.add(publicacion)
    }
    fun agregarAmigo(amigo: Usuario) {
        amigos.add(amigo)
    }
    fun agregarPermitido(amigo: Usuario){
        check(!listaDePermitidos.contains(amigo)){
            listaDePermitidos.add((amigo))
        }
    }
    fun agregarExcluido(amigo: Usuario){
        check(!listaDeExcluidos.contains(amigo)){
            listaDeExcluidos.add((amigo))
        }
    }
    fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }

    fun puedeDarMeGusta(publicacion: Publicacion, usuario: Usuario) =
        (amigos.contains(usuario) && this.estaPublicacionYTienePermisos(publicacion,usuario )) || usuario==this

    fun meGusta(publicacion: Publicacion, usuario: Usuario) {
        check(!puedeDarMeGusta(publicacion,usuario)){
            this.registrarMeGusta(publicacion,usuario)
        }

    }
    private fun registrarMeGusta(publicacion: Publicacion, usuario: Usuario) {
        //TODO verificar en el registro si ya existe clave-valor
        //registroDeMeGusta[publicacion] = usuario
    }



    // validaciones y funciones accesorias
    private fun estaPublicacionYTienePermisos(publicacion: Publicacion, usuario: Usuario) =
        usuario.contiene(publicacion) && this.validarPermiso(usuario, publicacion)
    private fun contiene(publicacion: Publicacion) = publicaciones.contains(publicacion)

    //validaciones de usuario y publicacion
    private fun validarPermiso(usuario: Usuario, publicacion: Publicacion) =
        this.publico(publicacion) || this.soloAmigos(publicacion,usuario) ||
                this.privadaConListaDePermitidos(publicacion,usuario) || this.publicoConListaDeExcluidos(publicacion,usuario)

    // verificacion del estado de la publicacion
    private fun publico(publicacion: Publicacion) = (publicacion.permisoPublicacion == "Publico")
    private fun soloAmigos(publicacion: Publicacion, usuario: Usuario) =
        (publicacion.permisoPublicacion == "SoloAmigos") && (amigos.contains(usuario))
    private fun privadaConListaDePermitidos(publicacion: Publicacion, usuario: Usuario) =
        publicacion.permisoPublicacion == "PrivadaConPermitidos" && this.estaEnListaDePermitidos(usuario)
    private fun publicoConListaDeExcluidos(publicacion: Publicacion, usuario: Usuario) =
        publicacion.permisoPublicacion == "PublicoConExcluidos" && !this.estaEnListaDeExcluidos(usuario)

    private fun estaEnListaDePermitidos(usuario: Usuario) = listaDePermitidos.contains(usuario)
    private fun estaEnListaDeExcluidos(usuario: Usuario) = listaDeExcluidos.contains(usuario)





}