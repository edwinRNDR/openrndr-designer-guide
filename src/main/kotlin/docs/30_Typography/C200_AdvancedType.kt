@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Advanced type")
@file:ParentTitle("Typography")
@file:Order("200")
@file:URL("typography/advancedType")

package docs.`30_Typography`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.color.rgb
import org.openrndr.dokgen.annotations.*
import org.openrndr.draw.font.loadFace
import org.openrndr.draw.loadFont
import org.openrndr.extra.shapes.text.shapesFromText
import org.openrndr.extra.textwriter.writer
import org.openrndr.shape.Rectangle


fun main() {
    @Text
    """
    # Advanced type in OPENRNDR
    """

    @Text
    """
    ## Contour of a text

    """.trimIndent()

    @Media.Video "../media/advanced-type-001.mp4"

    @Application
    @ProduceVideo("media/advanced-type-001.mp4")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            val face = loadFace("data/fonts/default.otf")
            val shapes = shapesFromText(face, "OPENRNDR", 150.0)
            val contours = shapes.flatMap { it.contours }

            extend {
                drawer.fill = null
                drawer.stroke = ColorRGBa.PINK
                drawer.translate(width/16.0, height/2.0)
                for (contour in contours) {
                    drawer.contour(contour.sub(seconds * 0.1, seconds * 0.1 + 0.5))
                }
            }
        }
    }

    @Text
    """
    ## Points on text

    """.trimIndent()

    @Media.Image "../media/advanced-type-002.png"

    @Application
    @ProduceScreenshot("media/advanced-type-002.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            val face = loadFace("data/fonts/default.otf")
            val shapes = shapesFromText(face, "OPENRNDR", 150.0)
            val contours = shapes.flatMap { it.contours }

            extend {
                drawer.stroke = null
                drawer.fill = ColorRGBa.PINK
                drawer.translate(width/16.0, height/2.0)
                for (contour in contours) {
                    // exercise: change the number 10.0 here
                    val pts = contour.equidistantPositions((contour.length / 10.0).toInt())
                    drawer.circles(pts, 3.0)
                }
            }
        }
    }
}
