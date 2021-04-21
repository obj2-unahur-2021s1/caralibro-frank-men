package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
    describe("Caralibro") {
        val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
        val fotoEnCuzco = Foto(768, 1024)
        fotoEnCuzco.meGusta()

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
        describe("me gusta") {
            describe("a una publicacion") {
                it("dar me gusta") {
                    fotoEnCuzco.cantidadDeMeGusta().shouldBe(1)
                }
            }
            describe("de una publicacion") {
                it("saber cuantos me gusta"){
                    saludoCumpleanios.meGusta()
                    saludoCumpleanios.meGusta()
                    saludoCumpleanios.cantidadDeMeGusta().shouldBe(2)
                }
            }
        }
    }
})