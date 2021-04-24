package ar.edu.unahur.obj2.caralibro

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldNotBeTypeOf

class UsuarioTest : DescribeSpec({
    describe("Caralibro") {
        // Usuarios de CaraLibro
        val juana = Usuario()
        val pepe = Usuario()
        val raquel = Usuario()
        // Publicaciones
        val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
        val fotoEnCuzco = Foto(768, 1024)
        val videoPlaya = Video(550,"hd720")
        val fotitoPreciosa = Foto(120,120)
        // Asignacion de publicaciones
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        pepe.agregarPublicacion(videoPlaya)
        raquel.agregarPublicacion(fotitoPreciosa)
        // Cambio de permisos
        fotoEnCuzco.cambiarPermiso("SoloAmigos")
        videoPlaya.cambiarPermiso("PrivadoConPermitidos")
        fotitoPreciosa.cambiarPermiso("PublicoConExcluidos")
        // Designacion de amigos
        // Juana es amiga de Pepe y Raquel (y viseversa), pero Pepe y Raquel no son amigos
        pepe.agregarAmigo(juana)
        raquel.agregarAmigo(juana)
        juana.agregarAmigo(pepe)
        juana.agregarAmigo(raquel)

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
                    saludoCumpleanios.permisoPublicacion.shouldBe("Publico")// por solucionar tema string
                }
            }
        }
        describe("Un usuario") {
            describe("de espacio") {
                it("puede calcular el espacio que ocupan sus publicaciones") {
                    juana.espacioDePublicaciones().shouldBe(550548)
                }
            }
            describe("puede dar me gusta"){
                it("pepe puede dar me gusta a publicacion de raquela") {
                    val pepito = Usuario()
                    val raquela = Usuario()
                    val publiCancun = Foto(1280,720)
                    raquela.agregarPublicacion(publiCancun)
                    publiCancun.cambiarPermiso("publico")
                    shouldNotThrow<Exception> {pepito.darMeGusta(publiCancun)}
                }
                it("raquela cambia los permisos y pepe ya no puede."){
                    val pepito = Usuario()
                    val raquela = Usuario()
                    val publiCancun = Foto(1280,720)
                    raquela.agregarPublicacion(publiCancun)
                    publiCancun.cambiarPermiso("solo amigos")
                    shouldThrow<Exception> { pepito.darMeGusta(publiCancun) }

                }
                it("pepe, ahora amigo de raquela, puede volver a darle like."){
                    val pepito = Usuario()
                    val raquela = Usuario()
                    val publiCancun = Foto(1280,720)
                    raquela.agregarPublicacion(publiCancun)
                    publiCancun.cambiarPermiso("solo amigos")
                    raquela.agregarAmigo(pepito)
                    shouldNotThrow<Exception> {pepito.darMeGusta(publiCancun)}

                }
            }
        }
    }
})
