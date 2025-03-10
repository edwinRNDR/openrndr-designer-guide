@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Lists")
@file:ParentTitle("Repeating things")
@file:Order("100")
@file:URL("repeating-things/lists")

package docs.`10_Repeating_things`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.dokgen.annotations.*
import kotlin.random.Random


fun main() {
    @Text
    """
    # Working with Lists in OPENRNDR
    """

    @Text
    """
    ## Drawing texts from a list
    
    Here we use a `for (item in texts)` style for loop to draw the texts.
    """.trimIndent()

    @Media.Image "../media/lists-001.jpg"

    @Application
    @ProduceScreenshot("media/lists-001.jpg")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            val texts = listOf("First", "Second", "Third")
            extend {
                drawer.translate(30.0, 200.0)
                for (text in texts) {
                    drawer.fill = ColorRGBa.PINK
                    drawer.text(text)
                    drawer.translate(0.0, 50.0)
                }
            }
        }
    }

    @Text
    """
    ## Drawing texts from a list using list indices
    
    Here we draw texts from a list using indices and a `for (index in texts.indices)` style for loop.
    Since we work with indices we can easily assign different colors to each text element by looking up from a list of colors.
    
    In this example positions are calculated explicitly, instead of using `drawer.translate`
    """.trimIndent()
    @Media.Image "../media/lists-002.jpg"

    @Application
    @ProduceScreenshot("media/lists-002.jpg")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            val texts = listOf("First", "Second", "Third")
            val colors = listOf(ColorRGBa.PINK, ColorRGBa.GREEN, ColorRGBa.BLUE)
            extend {
                for (i in texts.indices) {
                    val text = texts[i]
                    drawer.fill = colors[i]
                    drawer.text(text, 30.0, i * 50.0 + 200.0)
                }
            }
        }
    }


    @Text
    """
    ## Drawing random texts from a list
    
    """.trimIndent()
    @Media.Image "../media/lists-003.png"

    @Application
    @ProduceScreenshot("media/lists-003.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            val texts = listOf("First", "Second", "Third")
            val colors = listOf(ColorRGBa.PINK, ColorRGBa.GREEN, ColorRGBa.RED)
            extend {
                // create a random number generator with seed 0
                // this makes sure that we draw the exact same sequence every time
                val rng = Random(0)

                for (i in 0 until 10) {
                    val text = texts.random(rng)
                    drawer.fill = colors.random(rng)
                    drawer.text(text, 30.0, i * 30.0 + 50.0)
                }
            }
        }
    }
}
