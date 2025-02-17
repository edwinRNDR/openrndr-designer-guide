@file:Suppress("UNUSED_EXPRESSION")
@file:Title("HSV color")
@file:ParentTitle("Colors")
@file:Order("300")
@file:URL("colors/gradients")

package docs.`20_Colors`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.color.hsv
import org.openrndr.color.rgb
import org.openrndr.dokgen.annotations.*
import org.openrndr.extra.shadestyles.linearGradient
import org.openrndr.extra.shadestyles.radialGradient


fun main() {
    @Text
    """
    # Gradients in OPENRNDR
    
    In OPENRNDR gradients are implemented using shade styles.
    
    """

    @Text
    """
    ## Linear gradients
    
    """.trimIndent()

    @Media.Image "../media/gradients-001.png"

    @Application
    @ProduceScreenshot("media/gradients-001.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.shadeStyle = linearGradient(ColorRGBa.PINK, ColorRGBa.WHITE)
                drawer.circle(drawer.bounds.center, 200.0)
            }
        }
    }

    @Text
    """
    ## Radial gradients
    
    """.trimIndent()

    @Media.Image "../media/gradients-002.png"

    @Application
    @ProduceScreenshot("media/gradients-002.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.shadeStyle = radialGradient(ColorRGBa.PINK, ColorRGBa.MAGENTA)
                drawer.circle(drawer.bounds.center, 200.0)
            }
        }
    }

    @Text
    """
    ## Linear gradients with rotation
    
    """.trimIndent()

    @Media.Image "../media/gradients-003.png"

    @Application
    @ProduceScreenshot("media/gradients-003.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.stroke = null
                for (i in 9 downTo 0) {
                    drawer.shadeStyle = linearGradient(ColorRGBa.PINK, ColorRGBa.MAGENTA, rotation = i * 36.0)
                    drawer.circle(drawer.bounds.center, 200.0 + i * 15.0)
                }
            }
        }
    }

    @Text
    """
    ## Linear gradients with animated rotation
    
    """.trimIndent()

    @Media.Video "../media/gradients-004.mp4"

    @Application
    @ProduceVideo("media/gradients-004.mp4")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.stroke = null
                for (i in 9 downTo 0) {
                    drawer.shadeStyle = linearGradient(ColorRGBa.PINK, ColorRGBa.MAGENTA, rotation = seconds * 36.0 +  i * 36.0)
                    drawer.circle(drawer.bounds.center, 200.0 + i * 15.0)
                }
            }
        }
    }
}
