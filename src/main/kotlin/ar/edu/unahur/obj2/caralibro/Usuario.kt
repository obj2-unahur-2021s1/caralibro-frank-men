package ar.edu.unahur.obj2.caralibro

class Usuario(val nombreUsuario: String){

    var listaDePublicaciones = mutableListOf<Publicacion>()

    fun realizarPublicacion(publicacion: Publicacion) {
        listaDePublicaciones.add(publicacion)
    }

}
