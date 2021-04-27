package ar.edu.unahur.obj2.caralibro

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
    describe("Caralibro") {
        // Usuarios de CaraLibro
        val juana = Usuario()
        val pepe = Usuario()
        val raquel = Usuario()
        val pepito = Usuario()
        val raquela = Usuario()
        // Publicaciones
        val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
        val fotoEnCuzco = Foto(768, 1024)
        val videoPlaya = Video(550,"hd720")
        val fotitoPreciosa = Foto(120,120)
        val publiCancun = Foto(1280,720)
        val fotoFacha = Foto(1280, 1080)
        // Asignacion de publicaciones
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.agregarPublicacion(videoPlaya)
        raquel.agregarPublicacion(fotitoPreciosa)
        raquela.agregarPublicacion(publiCancun)
        pepe.agregarPublicacion(fotoFacha)
        // Cambio de permisos
        fotoEnCuzco.cambiarPermiso("solo amigos")
        videoPlaya.cambiarPermiso("privado con permitidos")
        fotitoPreciosa.cambiarPermiso("publico con excluidos")
        // Designacion de amigos
        pepe.agregarAmigo(juana)
        pepe.agregarAmigo(raquela)
        raquel.agregarAmigo(juana)
        juana.agregarAmigo(pepe)
        juana.agregarAmigo(raquel)
        juana.agregarAmigo(raquela)
        pepito.agregarAmigo(pepe)
        // listas de permitidos y excluidos
        juana.agregarPermitido(pepe)
        raquel.agregarExcluido(pepe)
        // me gustas
        raquel.darMeGusta(fotoFacha)
        raquela.darMeGusta(fotoFacha)
        juana.darMeGusta(fotoFacha)
        pepe.darMeGusta(fotoFacha)
        pepe.darMeGusta(fotoEnCuzco)
        raquela.darMeGusta(fotoEnCuzco)
        raquela.darMeGusta(fotitoPreciosa)

        describe("Una publicacion") {
            describe("de tipo foto") {
                it("ocupa ancho * alto * compresion bytes") {
                    fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
                }
            }
            describe("de tipo texto") {
                it("ocupa tantos bytes como su longitud") {
                    saludoCumpleanios.espacioQueOcupa().shouldBe(45)
                }
            }
            describe("de tipo video") {
                it("ocupa tantos bytes como su longitud") {
                    videoPlaya.espacioQueOcupa().shouldBe(1650)
                }
            }
            describe("permiso de publicacion") {
                it("es publico"){
                    saludoCumpleanios.permisoPublicacion.shouldBe("publico")
                }
            }
            describe("lista privada con permitidos"){
                it("juana comparte video privado"){
                    pepe.puedeVerPublicacion(videoPlaya).shouldBeTrue()
                }
                it("raquel excluye a pepe"){
                    pepe.puedeVerPublicacion(fotitoPreciosa).shouldBeFalse()
                }
            }
        }
        describe("Un usuario") {

            describe("de espacio") {
                it("puede calcular el espacio que ocupan sus publicaciones") {
                    juana.espacioDePublicaciones().shouldBe(552198)
                }
            }
            //REQ. 2: Puede dar me gusta.
            describe("puede dar me gusta"){
                it("pepe puede dar me gusta a publicacion de raquela") {
                    publiCancun.cambiarPermiso("publico")
                    shouldNotThrow<Exception> {pepito.darMeGusta(publiCancun)}
                }
                it("raquela cambia los permisos y pepe ya no puede."){
                    publiCancun.cambiarPermiso("solo amigos")
                    shouldThrow<Exception> { pepito.darMeGusta(publiCancun) }

                }
                it("pepe, ahora amigo de raquela, puede volver a darle like."){
                    publiCancun.cambiarPermiso("solo amigos")
                    raquela.agregarAmigo(pepito)
                    shouldNotThrow<Exception> {pepito.darMeGusta(publiCancun)}
                }
            }
            describe("Es mas amistoso"){
                it("Juana es mas amistosa que pepito"){
                    juana.esMasAmistosoQue_(pepito).shouldBeTrue()
                }
                it("Raquel NO es mas amistosa que juana"){
                    raquel.esMasAmistosoQue_(juana).shouldBeFalse()
                }
            }
            //REQ. 2: Cantidad de me gusta
            describe("cantidad de me gusta"){
                it("por usuario"){
                    pepe.cantidadDeMeGusta().shouldBe(4)
                }
            }
            describe("el mas popular"){
                it("de los amigos de juana"){
                    juana.amigoMasPopular().shouldBe(pepe)
                }
            }
            describe("mejores amigos"){
                it("mejores amigos de juana"){
                    juana.mejoresAmigos().shouldContainExactlyInAnyOrder(pepe)
                }
            }
            describe("stalkeo"){
                it("juana stalkea a pepe"){
                    juana.stalkea(pepe).shouldBeTrue()
                }
                it("raquel No stalkea a juana"){
                    raquel.stalkea(juana).shouldBeFalse()
                }
            }
        }
    }
})
