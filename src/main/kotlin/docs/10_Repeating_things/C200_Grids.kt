@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Grids")
@file:ParentTitle("Repeating things")
@file:Order("200")
@file:URL("repeating-things/grids")

package docs.`10_Repeating_things`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.dokgen.annotations.*
import org.openrndr.extra.shapes.primitives.grid
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random


fun main() {
    @Text
    """
    # Working with grids in OPENRNDR
    
    Grids are used to repeat things in two dimensions. Let's walk through some examples of drawing grids.
    """

    @Text
    """
    ## Drawing a grid with for loops
    
    Here we create a simple 10x10 grid using nested for loops in which we draw rectangles.
    
    """.trimIndent()

    @Media.Image "../media/grids-001.png"

    @Application
    @ProduceScreenshot("media/grids-001.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                for (j in 0 until 10) {
                    for (i in 0 until 10) {
                        val cellWidth = width / 10.0
                        val cellHeight = height / 10.0
                        val y = j * cellHeight
                        val x = i * cellWidth
                        drawer.fill = ColorRGBa.PINK
                        drawer.stroke = ColorRGBa.WHITE
                        drawer.rectangle(x, y, cellWidth, cellHeight)
                    }
                }
            }
        }
    }

    ////

    @Text
    """
    ## Varying the grid
    
    The same grid as before but this time the fill color is calculated (indirectly) from the loop
    counters `i` and `j`.
    
    """.trimIndent()

    @Media.Image "../media/grids-002.png"

    @Application
    @ProduceScreenshot("media/grids-002.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                // disable the stroke
                drawer.stroke = null

                for (j in 0 until 10) {
                    for (i in 0 until 10) {
                        val cellWidth = width / 10.0
                        val cellHeight = height / 10.0
                        val y = j * cellHeight
                        val x = i * cellWidth

                        val u = i / 9.0
                        val v = j / 9.0

                        drawer.fill = ColorRGBa.PINK.shade((u + v).mod(1.0))
                        drawer.rectangle(x, y, cellWidth, cellHeight)
                    }
                }
            }
        }
    }

    ////

    @Text
    """
    ## Grid of circles
    
    Instead of rectangles we draw circles. We need to take some care to center the circles properly.
    
    
    """.trimIndent()

    @Media.Image "../media/grids-003.png"

    @Application
    @ProduceScreenshot("media/grids-003.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                // disable the stroke
                drawer.stroke = null

                for (j in 0 until 10) {
                    for (i in 0 until 10) {
                        val cellWidth = width / 10.0
                        val cellHeight = height / 10.0
                        val y = j * cellHeight
                        val x = i * cellWidth

                        val u = i / 9.0
                        val v = j / 9.0

                        drawer.fill = ColorRGBa.PINK.shade((u + v).mod(1.0))
                        drawer.circle(x + cellWidth / 2.0, y + cellHeight / 2.0, cellWidth / 2.0)
                    }
                }
            }
        }
    }

    ////

    @Text
    """
    ## Grid of moving circles
    
    Let's introduce some simple animation to grid of circles. 
    
    """.trimIndent()

    @Media.Video "../media/grids-004.mp4"

    @Application
    @ProduceVideo("media/grids-004.mp4")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                // disable the stroke
                drawer.stroke = null

                for (j in 0 until 10) {
                    for (i in 0 until 10) {
                        val cellWidth = width / 10.0
                        val cellHeight = height / 10.0
                        val y = j * cellHeight
                        val x = i * cellWidth

                        val u = i / 9.0
                        val v = j / 9.0

                        // exercise: see what happens when you change 0.1
                        drawer.fill = ColorRGBa.PINK.shade((seconds * 0.1 + u + v).mod(1.0))

                        // exercise: see what happens when you change the numbers 13.0, 0.5 and 20.0
                        val cx = x + cellWidth / 2.0 + cos(v * 13.0 + seconds * 0.5) * 20.0
                        val cy = y + cellHeight / 2.0 + cos(u * 13.0 + seconds * 0.5) * 20.0

                        drawer.circle(cx, cy, cellWidth / 2.0)
                    }
                }
            }
        }
    }


    ////

    @Text
    """
    ## Smarter grids
    
    There is a smarter way to draw grids, instead of using nested for loops and calculating the position and size of 
    the grid cells we can use the `grid()` function of `Rectangle`. The added bonus is that we get things like gutters and 
    margins without much effort.
    
    `drawer` has a `bounds` property which is a `Rectangle` value representing the drawable area.
    
    The `grid()` function returns a `List<List<Rectangle>>`, a list containing lists of rectangles. We need not think
    about such complexities at this point so we invoke `flatten()` to reduce to a single list of rectangles.
    
    """.trimIndent()

    @Media.Image "../media/grids-005.png"

    @Application
    @ProduceScreenshot("media/grids-005.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.stroke = ColorRGBa.WHITE
                val cells = drawer.bounds.grid(10, 10, marginX = 10.0, marginY = 10.0, gutterX = 10.0, gutterY = 10.0).flatten()

                for (cell in cells) {
                    drawer.rectangle(cell)
                }
            }
        }
    }

    ////

    @Text
    """
    ## Smarter grids with moving circles 
    
    So let's see how we can use our smarter grids with circles.
    
    """.trimIndent()

    @Media.Video "../media/grids-006.mp4"

    @Application
    @ProduceVideo("media/grids-006.mp4")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.stroke = ColorRGBa.WHITE
                drawer.fill = null
                // exercise: see how simple it is to change the grid properties
                val cells = drawer.bounds.grid(10, 10, marginX = 10.0, marginY = 10.0, gutterX = 10.0, gutterY = 10.0).flatten()

                // first draw our cells
                for (cell in cells) {
                    drawer.rectangle(cell)
                }

                // we will be drawing black circles
                drawer.fill = ColorRGBa.BLACK

                // now draw circles inside cells
                for (cell in cells) {

                    val x = cos(seconds * 0.1 + cell.center.y * 0.1) * 20.0 + cell.center.x
                    val y = sin(seconds * 0.1 + cell.center.x * 0.1) * 20.0 + cell.center.y

                    drawer.circle(x, y, cell.width / 2.0)
                }
            }
        }
    }
}
