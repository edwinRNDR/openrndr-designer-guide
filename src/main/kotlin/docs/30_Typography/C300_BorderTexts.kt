@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Writing")
@file:ParentTitle("Typography")
@file:Order("300")
@file:URL("typography/borderText")

package docs.`30_Typography`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.color.rgb
import org.openrndr.dokgen.annotations.*
import org.openrndr.draw.isolated
import org.openrndr.draw.loadFont
import org.openrndr.extra.textwriter.writer
import org.openrndr.shape.Rectangle


fun main() {
    @Text
    """
    # Writing in the borders
    """

    @Text
    """
    ## Simple border text


    """.trimIndent()

    @Media.Image "../media/border-text-001.png"

    @Application
    @ProduceScreenshot("media/border-text-001.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            fun drawBorderTexts(topText: String, bottomText: String, leftText: String, rightText: String, margin: Double) {
                // draw the top text
                drawer.isolated {
                    val tw = writer {
                        textWidth(topText)
                    }
                    drawer.text(topText, width/2.0 - tw/2.0, drawer.fontMap!!.height + margin)
                }

                // draw the bottom text
                drawer.isolated {
                    val tw = writer {
                        textWidth(bottomText)
                    }
                    drawer.text(bottomText, width/2.0 - tw/2.0, height -  margin)
                }

                // draw the left text
                drawer.isolated {
                    val tw = writer {
                        textWidth(leftText)
                    }
                    drawer.translate(margin, height/2.0 - tw/2.0)
                    drawer.rotate(90.0)
                    drawer.text(leftText)
                }

                // draw the right text
                drawer.isolated {
                    val tw = writer {
                        textWidth(rightText)
                    }
                    drawer.translate(width - margin, height/2.0 + tw/2.0)
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

                val margin = 10.0

                // exercise use: for loops here
                drawBorderTexts(topText, bottomText, leftText, rightText, margin)
                //drawBorderTexts(topText, bottomText, leftText, rightText, margin * 2.0)
            }
        }
    }

}
