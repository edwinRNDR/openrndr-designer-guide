@file:Suppress("UNUSED_EXPRESSION")
@file:Title("What is OPENRNDR?")
@file:Order("0")
@file:URL("/index")

package docs

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.dokgen.annotations.*
import kotlin.math.abs
import kotlin.math.cos

fun main() {
    @Text
    """
    # A simple OPENRNDR program
    Here we show a very simple program written using OPENRNDR.
    """

    @Media.Video "media/what-is-001.mp4"

    @Application
    @ProduceVideo("media/what-is-001.mp4", 6.28318)
    @Code
    application {
        @Exclude
        configure {
            width = 770
            height = 568
        }
        program {
            extend {
                drawer.clear(ColorRGBa.PINK)
                drawer.fill = ColorRGBa.WHITE
                drawer.circle(
                    drawer.bounds.center,
                    abs(cos(seconds)) * height * 0.51
                )
            }
        }
    }
}