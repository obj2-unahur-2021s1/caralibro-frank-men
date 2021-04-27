package ar.edu.unahur.obj2.caralibro

class Usuario {
    var publicaciones = mutableListOf<Publicacion>()
    var amigos = mutableListOf<Usuario>()
    var listaDePermitidos = mutableListOf<Usuario>()
    var listaDeExcluidos = mutableListOf<Usuario>()

    fun agregarPublicacion(publicacion: Publicacion) {
        //Agregar publicacion tiene por objetivo no solo añadir la publicacion
        //a la lista, si no que tambien se encarga de asignarle el usuario
        //a la publicacion, para poder trabajar con permisos.
        publicacion.usuarioPublicacion = this
        publicaciones.add(publicacion)

    }
    fun agregarAmigo(amigo: Usuario) {
        //chequea si no esta agregado, y si no, lo agrega
        if (amigos.contains(amigo)){
            throw Exception("El usuario ya ha sido agregado")
        }
        amigos.add(amigo)
    }
    fun agregarPermitido(amigo: Usuario){
        //añade un usuario a la lista de permitidos.
        if (listaDePermitidos.contains(amigo)){
            throw Exception("El usuario ya se encuentra en la lista.")
        }
        listaDePermitidos.add(amigo)
    }
    fun agregarExcluido(amigo: Usuario){
        //agrega un usuario a la lista de excluidos.
        if (listaDeExcluidos.contains(amigo)){
            throw Exception("El usuario ya se encuentra en la lista.")
        }
        listaDeExcluidos.add(amigo)
    }

    fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }

    fun puedeVerPublicacion(publicacion: Publicacion) =
        //Envia puedeSerVistaPor a la publicacion, para realizar la logica alli.
        publicacion.puedeSerVistaPor(this)


    fun darMeGusta(publicacion: Publicacion){
        //Si el usuario puede ver la publicacion, manda la peticion a recibirMeGusta
        if (!(this.puedeVerPublicacion(publicacion))){
            throw Exception("No se puede dar me gusta.")
        }
        publicacion.recibirMeGusta(this)
    }
    fun cantidadDeMeGusta() = publicaciones.sumBy { it.cantidadDeMeGusta() }

    fun esMasAmistosoQue_(usuario: Usuario) = this.cantidadAmigos() > usuario.cantidadAmigos()

    fun amigoMasPopular() = amigos.maxByOrNull { it.cantidadDeMeGusta() }

    fun mejoresAmigos() = amigos.filter { ami -> this.puedeVerTodasLasPublicaciones(ami) }.toSet()

    private fun puedeVerTodasLasPublicaciones(usuario: Usuario) =
        this.puedeVerTodas(usuario) == publicaciones.size

    private fun puedeVerTodas(usuario: Usuario) = publicaciones.filter { it.puedeSerVistaPor(usuario) }.size

    private fun cantidadAmigos() = this.amigos.count()



}