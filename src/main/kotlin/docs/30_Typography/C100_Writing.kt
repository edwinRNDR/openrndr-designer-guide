@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Writing")
@file:ParentTitle("Typography")
@file:Order("100")
@file:URL("typography/writing")

package docs.`30_Typography`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.color.rgb
import org.openrndr.dokgen.annotations.*
import org.openrndr.draw.loadFont
import org.openrndr.extra.textwriter.writer
import org.openrndr.shape.Rectangle


fun main() {
    @Text
    """
    # Writing in OPENRNDR
    """

    @Text
    """
    ## Text in a box
    """.trimIndent()

    @Media.Image "../media/writing-001.png"

    @Application
    @ProduceScreenshot("media/writing-001.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val r = Rectangle(40.0, 40.0, 300.0, 300.0)
                drawer.fill = null
                drawer.stroke = ColorRGBa.PINK

                // preview the rectangle
                drawer.rectangle(r)

                drawer.fill = ColorRGBa.WHITE

                // set the font
                drawer.fontMap = loadFont("data/fonts/default.otf", 32.0)
                writer {
                    // set the box to our previously created rectangle r
                    // add some additional margins
                    box = r.offsetEdges(-10.0)
                    newLine()
                    text("Here is a text that should not be able to leave this box. Let's just ramble on for a bit. Ok then.")
                }
            }
        }
    }

    @Media.Image "../media/writing-002.png"

    @Text
    """
    ## Tracking and leading

    Tracking controls the horizontal spacing between characters. Leading controls the vertical spacing between lines.
    """.trimIndent()

    @Application
    @ProduceScreenshot("media/writing-002.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {

                // set the font
                drawer.fontMap = loadFont("data/fonts/default.otf", 32.0)
                writer {
                    // set the box
                    box = drawer.bounds.offsetEdges(-100.0)
                    leading = 10.0
                    tracking = 15.0
                    text("Here is a text that should not be able to leave this box. Let's just ramble on for a bit. Ok then.")
                    newLine()
                    text("Here is another line of text.")
                }
            }
        }
    }


    @Text
    """
    ## Multiple fonts

    Tracking controls the horizontal spacing between characters. Leading controls the vertical spacing between lines.
    """.trimIndent()
    @Media.Image "../media/writing-003.png"

    @Application
    @ProduceScreenshot("media/writing-003.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {

            val large = loadFont("data/fonts/default.otf", 64.0)
            val medium = loadFont("data/fonts/default.otf", 32.0)
            val small = loadFont("data/fonts/default.otf", 16.0)


            extend {

                // set the font

                writer {
                    // set the box
                    box = drawer.bounds.offsetEdges(-100.0)
                    drawer.fontMap = large
                    text("I am a large sized font")
                    newLine()

                    drawer.fontMap = medium
                    text("I am a medium sized font, well well well, kinda")
                    newLine()

                    drawer.fontMap = small
                    text("I am a small sized font. so smol.")
                    newLine()
                }
            }
        }
    }
}
