package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty

class PublicacionTest: DescribeSpec ({

    describe("Crear Texto"){
        val textito = Texto("Hoy me clave un pancho")
        textito.textoPublicacion.shouldBe("Hoy me clave un pancho")
    }
    describe("Crear Foto"){
        val fotito = Foto(1280,720)
        fotito.pesoPublicacion().shouldBe(645120)
    }
    describe("Crear Video"){

    }
})