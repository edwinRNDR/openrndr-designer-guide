@file:Suppress("UNUSED_EXPRESSION")
@file:Title("HSV color")
@file:ParentTitle("Colors")
@file:Order("200")
@file:URL("colors/HSVColor")

package docs.`20_Colors`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.color.hsv
import org.openrndr.color.rgb
import org.openrndr.dokgen.annotations.*


fun main() {
    @Text
    """
    # HSV color in OPENRNDR
    """

    @Text
    """
    ## Representations of HSV colors
    
    """.trimIndent()

    @Media.Image "../media/hsv-color-001.png"

    @Application
    @ProduceScreenshot("media/hsv-color-001.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                for (j in 0 until 10) {
                    for (i in 0 until 36) {
                        val c = hsv(i * 10.0, j / 9.0, 1.0)
                        drawer.fill = c.toRGBa()
                        drawer.rectangle(i * (width / 36.0), j * (height / 10.0), width / 36.0, height / 10.0)
                    }
                }
            }
        }
    }

    @Text
    """
    ## Animating HSV colors
    
    We take the same color swatches we used previously but we add some simple time based animations.
        
    """.trimIndent()

    @Media.Video "../media/hsv-color-002.mp4"

    @Application
    @ProduceVideo("media/hsv-color-002.mp4")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                for (j in 0 until 10) {
                    for (i in 0 until 36) {
                        val c = hsv(seconds*36.0 + i * 10.0, j / 9.0, 1.0)
                        drawer.fill = c.toRGBa()
                        drawer.rectangle(i * (width / 36.0), j * (height / 10.0), width / 36.0, height / 10.0)
                    }
                }
            }
        }
    }
}
