package ar.edu.unahur.obj2.caralibro

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
    describe("Caralibro") {
        val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
        val fotoEnCuzco = Foto(768, 1024)

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
        }
        // con el test del espacio que ocupan sus publicaciones se cumple el primer punto
        describe("Un usuario") {
            it("puede calcular el espacio que ocupan sus publicaciones") {
                val juana = Usuario()
                juana.agregarPublicacion(fotoEnCuzco)
                juana.agregarPublicacion(saludoCumpleanios)
                juana.espacioDePublicaciones().shouldBe(550548)
            }
        }

        describe("Un usuario puede dar like"){
            it("Pepe le da me gusta a Raquel"){
                val pepe = Usuario()
                val raquel = Usuario()
                val fotitoPreciosa = Foto(120,120)
                raquel.agregarPublicacion(fotitoPreciosa)
                pepe.darMeGusta(fotitoPreciosa)
            }
            it("Pepe se emociona, y le vuelve a dar like."){
                val pepe = Usuario()
                val raquel = Usuario()
                val fotitoPreciosa = Foto(120,120)
                raquel.agregarPublicacion(fotitoPreciosa)
                pepe.darMeGusta(fotitoPreciosa)
                shouldThrow<Exception> { pepe.darMeGusta(fotitoPreciosa)}
            }

        }
        describe("Un usuario tiene amigos"){
            it("Raquel se asusta, pepe ya no puede ver las fotos."){

            }
        }

    }
})
