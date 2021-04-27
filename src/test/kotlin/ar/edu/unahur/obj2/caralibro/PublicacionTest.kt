package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty

class PublicacionTest: DescribeSpec ({
    val textito = Texto("Hoy me clave un pancho")
    val fotito = Foto(1280, 720)

    describe("Crear Texto") {
       textito.contenido.shouldBe("Hoy me clave un pancho")
    }
    describe("Crear Foto") {
        fotito.espacioQueOcupa().shouldBe(645120)
    }

    describe("Crear video ") {
        it("Crear video SD") {
            val videitoSD = Video(100, "sd")
            videitoSD.espacioQueOcupa().shouldBe(100)
        }
        it("Crear video hd720") {
            val videitoHD720 = Video(100, "hd720")
            videitoHD720.espacioQueOcupa().shouldBe(300)
        }
        it("Crear video hd1080") {
            val videitoHD1080 = Video(100, "hd1080")
            videitoHD1080.espacioQueOcupa().shouldBe(600)
        }

        //REQ. 1: Cuanto ocupan las publicaciones de un usuario.
        it("Cuanto ocupan las publicaciones de pepe"){
            val pepe = Usuario()
            val publiFoto = Foto(100,10) //peso 700.
            val publiTexto = Texto("Hola amiguitos de facebok") //peso 25.
            val publiVideo = Video(75,"sd") //peso 75
            pepe.agregarPublicacion(publiFoto)
            pepe.agregarPublicacion(publiTexto)
            pepe.agregarPublicacion(publiVideo)
            pepe.espacioDePublicaciones().shouldBe(800)
        }
    }
    describe("Cambiar resoluciones") {
        it("Cambiar video SD a HD720") {
            val videito = Video(100, "sd")
            videito.espacioQueOcupa().shouldBe(100)
            videito.cambiarCalidad("hd720")
            videito.espacioQueOcupa().shouldBe(300)
        }
        it("Cambiar video HD1080 a SD") {
            val videote = Video(300, "HD1080") //con mayus porque no es case sensitive
            videote.espacioQueOcupa().shouldBe(1800)
            videote.cambiarCalidad("sD")
            videote.espacioQueOcupa().shouldBe(300)
        }
        it("Hd720 a SD y despues a 1080") {
            val videoDeMichis = Video(60, "hD720")
            videoDeMichis.espacioQueOcupa().shouldBe(180)
            videoDeMichis.cambiarCalidad("sd")
            videoDeMichis.espacioQueOcupa().shouldBe(60)
            videoDeMichis.cambiarCalidad("HD1080")
            videoDeMichis.espacioQueOcupa().shouldBe(360)
        }
    }
    describe("Cambio de permiso"){
        val textito2 = Texto("Hola amigos") //creo la publicacion.
        val juancito = Usuario() // Usuario dueño de la publicacion
        val pepito = Usuario() //Usuario ajeno
        juancito.agregarPublicacion(textito2)
        textito2.cambiarPermiso("solo amigos")
        juancito.puedeVerPublicacion(textito2).shouldBeTrue()
        pepito.puedeVerPublicacion(textito2).shouldBeFalse()
        textito2.cambiarPermiso("publico")
        pepito.puedeVerPublicacion(textito2).shouldBeTrue()
    }
})


