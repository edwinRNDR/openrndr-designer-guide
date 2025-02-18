@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Layered graphics")
@file:ParentTitle("Compose")
@file:Order("100")
@file:URL("compose/layeredGraphics")

package docs.`60_Compose`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.color.rgb
import org.openrndr.dokgen.annotations.*
import org.openrndr.draw.loadFont
import org.openrndr.extra.compositor.*
import org.openrndr.extra.fx.blend.Multiply
import org.openrndr.extra.fx.patterns.Checkers
import org.openrndr.extra.textwriter.writer
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle


fun main() {
    @Text
    """
    # Layered graphics in OPENRNDR
    """

    @Text
    """
    ## Simple composition



    """.trimIndent()

    @Media.Image "../media/layered-graphics-001.png"

    @Application
    @ProduceScreenshot("media/layered-graphics-001.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {

            val c = compose {
                layer {

                    post(Checkers())

                    layer {

                        draw {
                            drawer.fill = ColorRGBa.RED
                            drawer.circle(drawer.bounds.center, 100.0)
                        }
                    }
                    layer {
                        draw {
                            drawer.fill = ColorRGBa.BLUE
                            drawer.circle(drawer.bounds.center + Vector2(25.0, 25.0), 100.0)
                        }
                        blend(Multiply())
                    }
                }
            }

            extend {
                c.draw(drawer)

            }
        }
    }

}
