@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Rectangles")
@file:ParentTitle("Placing things")
@file:Order("100")
@file:URL("placing-things/rectangles")

package docs.`40_Placing_things`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.dokgen.annotations.*
import org.openrndr.draw.isolated
import org.openrndr.draw.loadFont
import org.openrndr.extra.shapes.primitives.grid
import org.openrndr.extra.textwriter.writer
import org.openrndr.shape.Rectangle


fun main() {
    @Text
    """
    # Rectangles
    
    Rectangles are great for placing things on the screen. In this chapter we show how to
    create rectangles and how to query their properties. We show how to perform common
    computations using rectangles to determine their position and size.
    """

    @Text
    """
    ## Creating rectangles
    """.trimIndent()

    @Media.Image "../media/rectangles-001.png"

    @Application
    @ProduceScreenshot("media/rectangles-001.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                // create our first rectangle
                // top-left corner is at 40, 40
                // width is 100.0 and height is 120.0
                val r0 = Rectangle(40.0, 40.0, 100.0, 120.0)

                // draw r0
                drawer.rectangle(r0)

                // create our second rectangle, from the center
                // center is at drawer.bounds.center
                // width is 120.0 and height is 100.0
                val r1 = Rectangle.fromCenter(drawer.bounds.center, 120.0, 100.0)

                // draw r1
                drawer.rectangle(r1)
            }
        }
    }

    @Text
    """
    ## Scaling rectangles
    
    Using the `scaledBy` function we can create a scaled version of a rectangle. By default the rectangle is scaled 
    around its center.
    """.trimIndent()

    @Media.Image "../media/rectangles-002.png"

    @Application
    @ProduceScreenshot("media/rectangles-002.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val r = Rectangle.fromCenter(drawer.bounds.center, 120.0, 100.0)

                // turn off fill
                drawer.fill = null
                drawer.stroke = ColorRGBa.WHITE

                // draw r1
                drawer.rectangle(r)

                for (i in 1 until 10) {
                    val rs = r.scaledBy(1.0 + i * 0.1)
                    drawer.rectangle(rs)
                }
            }
        }
    }

    @Text
    """
    ## Scaling rectangles using anchor points
    
    """.trimIndent()

    @Media.Image "../media/rectangles-003.png"

    @Application
    @ProduceScreenshot("media/rectangles-003.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val columns = 5
                val rows = 5

                // create a grid of rectangles
                val grid = drawer.bounds.grid(columns, rows)

                drawer.fill = null
                drawer.stroke = ColorRGBa.WHITE

                for ((y, row) in grid.withIndex()) {
                    for ((x, cell) in row.withIndex()) {
                        // first make a small version of cell
                        val r = cell.scaledBy(0.25)
                        drawer.rectangle(r)

                        // remove this block if you don't want the numbers
                        drawer.isolated {
                            drawer.fill = ColorRGBa.WHITE
                            drawer.fontMap = loadFont("data/fonts/default.otf", 12.0)

                            writer {
                                box = r.offsetEdges(-5.0)
                                newLine()
                                val uAnchor = x / (columns - 1.0)
                                val vAnchor = y / (rows - 1.0)
                                text("$uAnchor $vAnchor")
                            }
                        }

                        // draw 9 larger copies using varying anchor points
                        for (i in 1 until 10) {
                            val rs = r.scaledBy(1 + i * 0.25, uAnchor = x / (columns - 1.0), vAnchor = y / (rows - 1.0))
                            drawer.rectangle(rs)
                        }
                    }
                }
            }
        }
    }
}