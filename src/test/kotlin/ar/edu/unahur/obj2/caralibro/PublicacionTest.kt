package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty

class PublicacionTest: DescribeSpec ({

    describe("Crear Texto") {
        val textito = Texto("Hoy me clave un pancho")
        //textito.textoPublicacion.shouldBe("Hoy me clave un pancho")
    }
    describe("Crear Foto") {
        val fotito = Foto(1280, 720)
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
        describe("Creo una publicacion, la asigno a usuario y le cambio permisos."){
            val textito = Texto("Hola amigos") //creo la publicacion.
            val juancito = Usuario() // Usuario dueño de la publicacion
            val pepito = Usuario() //Usuario ajeno
            juancito.agregarPublicacion(textito)
            //agregarPublicacion deberia mapear la publicacion, agregandole como valor extra al usuario en cuestion.
            textito.cambiarPermiso("solo amigos")
            juancito.puedeVerPublicacion(textito).shouldBe(true)
            pepito.puedeVerPublicacion(textito).shouldBe(false)
            textito.cambiarPermiso("publico")
            pepito.puedeVerPublicacion(textito).shouldBe(true)
        }
    }
})


