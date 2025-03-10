@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Border texts")
@file:ParentTitle("Typography")
@file:Order("300")
@file:URL("typography/borderText")

package docs.`30_Typography`

import org.openrndr.application
import org.openrndr.dokgen.annotations.*
import org.openrndr.draw.isolated
import org.openrndr.draw.loadFont
import org.openrndr.extra.textwriter.writer
import kotlin.math.PI
import kotlin.math.cos


fun main() {
    @Text
    """
    # Writing in the borders
    """

    @Text
    """
    ## Simple border text
    
    In this example we show how to write text in borders of the screen. We use a 
    function `drawBorderTexts()` that takes 4 strings, 4 anchors and a margin parameter.    
    
    """.trimIndent()

    @Media.Video "../media/border-text-001.mp4"

    @Application
    @ProduceVideo("media/border-text-001.mp4")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {

            // draw border texts function, paste this in your program {}
            fun drawBorderTexts(
                topText: String,
                bottomText: String,
                leftText: String,
                rightText: String,
                topAnchor: Double = 0.5,
                bottomAnchor: Double = 0.5,
                leftAnchor: Double = 0.5,
                rightAnchor: Double = 0.5,
                margin: Double = 50.0,
            ) {
                // draw the top text
                drawer.isolated {
                    val tw = writer {
                        textWidth(topText)
                    }
                    drawer.text(
                        topText,
                        margin + (width - 2 * margin - tw) * topAnchor,
                        drawer.fontMap!!.height + margin
                    )
                }

                // draw the bottom text
                drawer.isolated {
                    val tw = writer {
                        textWidth(bottomText)
                    }
                    drawer.text(bottomText, margin + (width - 2 * margin - tw) * bottomAnchor, height - margin)
                }

                // draw the left text
                drawer.isolated {
                    val tw = writer {
                        textWidth(leftText)
                    }
                    drawer.translate(margin, margin + (height - 2 * margin - tw) * leftAnchor)
                    drawer.rotate(90.0)
                    drawer.text(leftText)
                }

                // draw the right text
                drawer.isolated {
                    val tw = writer {
                        textWidth(rightText)
                    }
                    drawer.translate(
                        width - margin,
                        margin + (height - 2 * margin) * (1.0 - rightAnchor) + tw * (rightAnchor)
                    )
                    drawer.rotate(-90.0)
                    drawer.text(rightText)
                }
            }

            extend {
                val topText = "This is at the top."
                val bottomText = "This is at the bottom."
                val leftText = "This is on the left."
                val rightText = "The is on the right."

                // set the font
                drawer.fontMap = loadFont("data/fonts/default.otf", 32.0)

                // determine anchor points, to center set a = 0.5
                val a = cos(seconds * 2 * PI * 0.1) * 0.5 + 0.5

                // exercise use: for loops here
                for (i in 0 until 10) {
                    drawBorderTexts(
                        topText, bottomText, leftText, rightText,
                        a, 1.0 - a, 1.0 - a, 1.0 - a,
                        margin = 10.0
                    )
                }
            }
        }
    }
}
