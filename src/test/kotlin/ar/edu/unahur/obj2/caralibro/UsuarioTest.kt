package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
class UsuarioTest: DescribeSpec({
    describe("Testeos sobre el Usuario."){
        val juanito = Usuario("Juanito")
    }

    describe("Realizar publicacion TEXTO."){
        val juanito = Usuario("Juanito")
        val texto = Texto("Hoy me tire un pedo.")
        juanito.realizarPublicacion(texto)
    }

    describe("Realizar publicacion FOTO"){
        val juanito = Usuario("Juanito")
        val fotito = Foto(1280,720)
        juanito.realizarPublicacion(fotito)
    }

    describe("Realizar publicacion VIDEO"){
        val asd = 1
    }


})

/*usuarios:

*/


//publicacion: abstracta, subclases = texto,fotos video












/*class UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)

    describe("Una publicación") {
      describe("de tipo foto") {
        it("ocupa ancho * alto * compresion bytes") {
          fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
        }
      }
    }
  }
})*/
