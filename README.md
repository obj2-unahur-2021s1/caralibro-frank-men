# Caralibro

![Portada](assets/portada.jpg)

## Antes de empezar: algunos consejos

El enunciado tiene **mucha** información, van a necesitar leerlo varias veces. La sugerencia es que lo lean entero una vez (para tener una idea general) y luego vuelvan a consultarlo las veces que hagan falta.

Concentrensé en los requerimientos y, excepto que se traben mucho, respeten el orden sugerido. No es necesario que hagan TDD, pero sí sería interesante que vayan creando las distintas clases y métodos a medida que resuelven cada requerimiento y no antes. 

En otras palabras: trabajen completando cada requerimiento antes de pasar al siguiente, con los tests que aseguran que funciona incluidos. Si al avanzar en los requerimientos les parece necesario refactorizar, adelante, van a tener los tests que garantizan que no rompieron nada. :smirk: 

## Descripción del dominio

Un grupo de inversores, que no quiere dar la cara, nos contrató para llevar a cabo una red social. Caralibro permite a los usuarios publicar diferentes tipos de contenidos, los cuales incluyen texto, fotos y videos. 

Por cuestiones de almacenamiento en los servidores, nos interesará saber cuánto espacio ocupa cada publicación, en Bytes:

* Las **fotos** tienen un alto y ancho dado en pixels; el espacio que ocupan se calcula como `alto * ancho * factor de compresión`. El factor de compresión actual para las fotos es de 0.7, pero debe poder modificarse (para todas las fotos a la vez). Como de esta cuenta muy probablemente resulte un número con coma, vamos a redondear para arriba en todos los casos (ejemplo: `99.85` se volvería `100`, y lo mismo para `99.12`).
* Las publicaciones de **texto** son mucho más fáciles de calcular, ya que el espacio que ocupan es igual a la cantidad de caracteres que tienen.
* Los **videos** tienen un tamaño que depende de la calidad con la cual el usuario elija publicarlo. Para la calidad SD, el tamaño es igual a la duración del video en segundos. Para los videos HD 720p el tamaño es igual al triple de la duración en segundos del video y para los videos de HD 1080p el tamaño es el doble de los HD 720p. Debe poder modificarse la calidad sin tener que volver a hacer la publicación.

Los usuarios que pueden ver una publicación pueden indicar que esa publicación les gusta, aumentando el número de _me gustas_ de la misma. A nuestra aplicación le importa tanto la cantidad de _me gustas_ que recibió una publicación, como saber quiénes le dieron _me gusta_. No es posible que una misma persona le de _me gusta_ más de una vez.

Nuestros usuarios tienen **amigos**, pero no quieren compartir todas sus publicaciones con todos ellos. Para satisfacer esa necesidad, cada publicación tiene asignado un permiso, que puede ser:

* **público**: cualquier usuario puede ver la publicación,
* **sólo amigos**: sólo los amigos pueden verla,
* **privado con lista de permitidos**: el usuario configura una lista que vale para esa publicación, y solo los usuarios que pertenezcan a esa lista pueden verla,
* **público con lista de excluidos**: similar al anterior, pero en este caso todos pueden ver la publicación excepto quienes están en la lista.

Cada publicación tiene uno de estos permisos asociados, que debe poder modificarse en cualquier momento. Sea cual sea la configuración, un usuario siempre puede ver sus propias publicaciones.

## Requerimientos

Se pide implementar la solución a este problema en Kotlin, junto con los tests que prueben cada uno de los requerimientos.

1. Saber cuánto espacio ocupa el total de las publicaciones de un usuario.
2. Poder darle _me gusta_ a una publicación, y conocer cuántas veces fue votada de esta forma.
3. Saber si un usuario es más amistoso que otro: esto ocurre cuando el primero tiene más amigos.
4. Saber si un usuario puede ver una cierta publicacion.
5. Determinar los mejores amigos de un usuario: el conjunto de sus amigos que pueden ver todas sus publicaciones.
6. Saber cual es el amigo más popular que tiene un usuario. Es decir, el amigo que tiene mas _me gusta_ entre todas sus publicaciones.
7. Saber si un usuario stalkea a otro. Lo cual ocurre si el stalker le dio _me gusta_ a más del 90% de sus publicaciones.

## Créditos

Enunciado original creado por Carlos Lombardi y Leonardo Gassman para UNQ. Transformado a Markdown, reformateado, reorganizado el texto y agregados nuevos requerimientos por Federico Aloi para UNaHur.

[![CC BY-SA 4.0][cc-by-sa-image]][cc-by-sa]

Esta obra está bajo una [Licencia Creative Commons Atribución-CompartirIgual 4.0 Internacional][cc-by-sa].

[cc-by-sa]: https://creativecommons.org/licenses/by-sa/4.0/deed.es
[cc-by-sa-image]: https://licensebuttons.net/l/by-sa/4.0/88x31.png


ok, vamos a establecer las necesidades de caralibro, para tener un panorama
de que es lo que debemos realizar, en favor de facilitar el TDD y el desarrollo en cuestion.

RED SOCIAL:es una interfaz, la cual trabaja internamente con USUARIOS. los usuarios son capaces de generar acciones dentro de la red social, en general diremos que es subir texto fotos y video.

PUBLICACIONES: Probablemente una clase A B S T R A C T A, es el resultado de la interaccion entre la red social y el usuario.
tipos de publicacion:

fotos: para calcular su tamaño se usa la siguiente formula: alto * ancho * factor de compresion; el factor de compresion debe ser configurable. el numero ha de ser redondeado hacia arriba.

texto: el espacio que ocupan es igual a la cantidad de caracteres.

videos: poseen un atributo CALIDAD, estas son:
sd = tamaño igual a segundos de duracion.
hd720 = tamaño igual a sd x 3
hd1080 = hd720 x 2
se debe poder modificar la calidad del video sin realizar una nueva publicacion.
//-----ideas sobre el video
| el video tiene que tener una funcion cambiarCalidad(calidad)
| que realize el calculo del peso.

PUBLICACIONES PT2: los USUARIOS pueden dar me gusta a una publicacion, lo cual significa que publicacion tiene un atributo, y una funcion, la funcion debe saber de quien es el like, y tirar error si es de uno que ya dio.

USUARIOS PT2: los USUARIOS tienen amigos (set de usuarios), pero no todos los amigos pueden ver todas las publicaciones, por lo cual la PUBLICACION debe contar con PERMISOS...
tipo de permisos:
publico: todos (default)
solo amigos: solo amigos
privado con permitidos: nadie menos los de la lista.
publico con lista de excluidos: todos menos los de la lista.

sea la configuracion que sea, un usuario siempre puede ver la public.