@file:Suppress("UNUSED_EXPRESSION")
@file:Title("RGB color")
@file:ParentTitle("Colors")
@file:Order("100")
@file:URL("colors/RGBColor")

package docs.`20_Colors`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.color.rgb
import org.openrndr.dokgen.annotations.*


fun main() {
    @Text
    """
    # RGB color in OPENRNDR
    """

    @Text
    """
    ## Representations of RGB colors
    
    Here we show a number of ways to refer to RGB colors. The first are OPENRNDR color presets (e.g. `ColorRGBa.PINK`),
    second is `rgb()` using hexadecimal rgb values, third is `rgb()` using decimal rgb values.
    """.trimIndent()

    @Media.Image "../media/rgb-color-001.png"

    @Application
    @ProduceScreenshot("media/rgb-color-001.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.fill = ColorRGBa.PINK
                drawer.rectangle(0.0, 0.0, 50.0, 50.0)

                drawer.fill = rgb("#4287f5")
                drawer.rectangle(50.0, 0.0, 50.0, 50.0)

                drawer.fill = rgb(1.0, 0.25, 0.25)
                drawer.rectangle(100.0, 0.0, 50.0, 50.0)
            }
        }
    }

    @Text
    """
    ## Shading RGB colors
    
    """.trimIndent()

    @Media.Image "../media/rgb-color-002.png"

    @Application
    @ProduceScreenshot("media/rgb-color-002.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                for (i in 0 until 10) {
                    drawer.fill = ColorRGBa.PINK.shade(i / 9.0)
                    drawer.rectangle(i * (width / 10.0), 0.0, width / 10.0, height * 1.0)
                }
            }
        }
    }

    @Text
    """
    ## Color transparency
    
    """.trimIndent()

    @Media.Image "../media/rgb-color-003.png"

    @Application
    @ProduceScreenshot("media/rgb-color-003.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.stroke = null
                drawer.fill = ColorRGBa.GRAY
                drawer.rectangle(0.0, 0.0, width * 1.0, height * 0.5)

                for (i in 0 until 10) {
                    drawer.fill = ColorRGBa.PINK.opacify(i / 9.0)
                    drawer.rectangle(i * (width / 10.0), 0.0, width / 10.0, height * 1.0)
                }
            }
        }
    }
}
