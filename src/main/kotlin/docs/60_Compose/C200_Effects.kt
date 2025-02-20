@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Effects")
@file:ParentTitle("Compose")
@file:Order("200")
@file:URL("compose/effects")

package docs.`60_Compose`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.color.rgb
import org.openrndr.dokgen.annotations.*
import org.openrndr.draw.loadFont
import org.openrndr.draw.loadImage
import org.openrndr.extra.compositor.*
import org.openrndr.extra.fx.blend.Multiply
import org.openrndr.extra.fx.blur.ApproximateGaussianBlur
import org.openrndr.extra.fx.distort.Perturb
import org.openrndr.extra.fx.patterns.Checkers
import org.openrndr.extra.imageFit.imageFit
import org.openrndr.extra.textwriter.writer
import org.openrndr.math.Vector2
import org.openrndr.shape.Rectangle


fun main() {
    @Text
    """
    # Effects
    """

    @Text
    """
    ## Blurred text

    """.trimIndent()

    @Media.Image "../media/effects-001.png"

    @Application
    @ProduceScreenshot("media/effects-001.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {

            val c = compose {

                layer {
                    val image = loadImage("data/images/cheeta.jpg")
                    draw {
                        drawer.imageFit(image, drawer.bounds)
                    }
                }
                // a layer on top of the base layer
                layer {
                    val font = loadFont("data/fonts/default.otf", 128.0)
                    draw {
                        drawer.fill = ColorRGBa.WHITE
                        drawer.fontMap = font
                        drawer.text("CAT", drawer.bounds.center.x - 200.0, drawer.bounds.center.y)
                    }
                    // enable effects
                    post(ApproximateGaussianBlur()) {
                        window = 25
                        sigma = 15.0
                    }
                }
            }

            extend {
                c.draw(drawer)
            }
        }
    }

    //

    @Text
    """
    ## Blurred image

    """.trimIndent()

    @Media.Image "../media/effects-002.png"

    @Application
    @ProduceScreenshot("media/effects-002.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            val c = compose {

                layer {
                    val image = loadImage("data/images/cheeta.jpg")
                    draw {
                        drawer.imageFit(image, drawer.bounds)
                    }
                    // enable effects
                    post(ApproximateGaussianBlur()) {
                        window = 25
                        sigma = 15.0
                    }
                }
                // a layer on top of the base layer
                layer {
                    val font = loadFont("data/fonts/default.otf", 128.0)
                    draw {
                        drawer.fill = ColorRGBa.WHITE
                        drawer.fontMap = font
                        drawer.text("CAT", drawer.bounds.center.x - 200.0, drawer.bounds.center.y)
                    }
                }
            }

            extend {
                c.draw(drawer)
            }
        }
    }


    @Text
    """
    ## Distorted text

    """.trimIndent()

    @Media.Image "../media/effects-003.png"

    @Application
    @ProduceScreenshot("media/effects-003.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {

            val c = compose {

                layer {
                    val image = loadImage("data/images/cheeta.jpg")
                    draw {
                        drawer.imageFit(image, drawer.bounds)
                    }
                }
                // a layer on top of the base layer
                layer {
                    val font = loadFont("data/fonts/default.otf", 128.0)
                    draw {
                        drawer.fill = ColorRGBa.WHITE
                        drawer.fontMap = font
                        drawer.text("CAT", drawer.bounds.center.x - 200.0, drawer.bounds.center.y)
                    }
                    // enable effects
                    post(Perturb()) {
                        gain = 0.05
                    }
                }
            }

            extend {
                c.draw(drawer)
            }
        }
    }
}
