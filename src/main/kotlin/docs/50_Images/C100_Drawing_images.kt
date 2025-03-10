@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Drawing images")
@file:ParentTitle("Images")
@file:Order("100")
@file:URL("images/images")

package docs.`50_Images`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.dokgen.annotations.*
import org.openrndr.draw.isolated
import org.openrndr.draw.loadImage
import org.openrndr.extra.color.colormatrix.colorMatrix
import org.openrndr.extra.imageFit.FitMethod
import org.openrndr.extra.imageFit.imageFit
import org.openrndr.extra.imageFit.imageFitSub
import org.openrndr.extra.noise.shapes.uniformSub
import org.openrndr.extra.shapes.primitives.grid
import kotlin.random.Random

fun main() {
    @Text
    """
    # Drawing images
    
    In this chapter we give a quick overview of how to draw images.
   
    """.trimIndent()
    @Text
    """
    ## Basic drawing
    
    Using `drawer.image` is as simple as it gets. We can load an image and draw it anywhere on the screen.
    """.trimIndent()

    @Media.Image "../media/image-drawing-0010.png"

    @Application
    @ProduceScreenshot("media/image-drawing-0010.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            // load an image
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                drawer.clear(ColorRGBa.GRAY)
                drawer.fill = null
                drawer.stroke = ColorRGBa.BLACK.opacify(0.5)
                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))
                drawer.image(image, 10.0, 10.0)
            }
        }
    }

    @Text
    """
    We can also provide dimensions for the image. However, this will stretch the image to fit the dimensions.
    """.trimIndent()

    @Media.Image "../media/image-drawing-0020.png"

    @Application
    @ProduceScreenshot("media/image-drawing-0020.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            // load an image
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                drawer.clear(ColorRGBa.GRAY)
                drawer.image(image, 10.0, 10.0, width - 20.0, height - 20.0)
            }
        }
    }

    @Text
    """
    To simplify image drawing we can use `drawer.imageFit` which will scale the image to fit the provided dimensions with 
    respect to the image's aspect ratio.
    """.trimIndent()

    @Media.Image "../media/image-drawing-0030.png"

    @Application
    @ProduceScreenshot("media/image-drawing-0030.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            // load an image
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                drawer.clear(ColorRGBa.GRAY)
                drawer.imageFit(image, drawer.bounds.offsetEdges(-10.0))
            }
        }
    }

    @Text
    """
    Alternative we can use `drawer.imageFit` with a `fitMethod` set to `FitMethod.CONTAIN` to make sure the entire image is drawn.
    """.trimIndent()

    @Media.Image "../media/image-drawing-0040.png"

    @Application
    @ProduceScreenshot("media/image-drawing-0040.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            // load an image
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                drawer.clear(ColorRGBa.GRAY)
                drawer.fill = null
                drawer.stroke = ColorRGBa.BLACK.opacify(0.5)
                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))
                drawer.imageFit(image, drawer.bounds.offsetEdges(-10.0), fitMethod = FitMethod.Contain)
            }
        }
    }

    @Text
    """
    Using the `verticalPosition` and `horizontalPosition` we can also control the vertical and horizontal placement of the image.
    """.trimIndent()

    @Media.Image "../media/image-drawing-0050.png"

    @Application
    @ProduceScreenshot("media/image-drawing-0050.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            // load an image
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                val grid = drawer.bounds.grid(3, 1, 10.0, 10.0, 10.0, 10.0)
                drawer.clear(ColorRGBa.GRAY)
                drawer.fill = null
                drawer.stroke = ColorRGBa.BLACK.opacify(0.5)

                for ((index, cell) in grid.flatten().withIndex()) {
                    drawer.rectangle(cell)
                    drawer.imageFit(
                        image,
                        cell,
                        fitMethod = FitMethod.Contain,
                        verticalPosition = index - 1.0
                    )
                }
            }
        }
    }

    @Media.Image "../media/image-drawing-0060.png"

    @Application
    @ProduceScreenshot("media/image-drawing-0060.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            // load an image
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                val grid = drawer.bounds.grid(1, 3, 10.0, 10.0, 10.0, 10.0)
                drawer.clear(ColorRGBa.GRAY)
                drawer.fill = null
                drawer.stroke = ColorRGBa.BLACK.opacify(0.5)

                for ((index, cell) in grid.flatten().withIndex()) {
                    drawer.rectangle(cell)
                    drawer.imageFit(
                        image,
                        cell,
                        fitMethod = FitMethod.Contain,
                        horizontalPosition = index - 1.0
                    )
                }
            }
        }
    }

    @Text
    """
    ## Rotating images
            
    Rotating images is a bit tricky but here is some code that will do the trick.                
    """.trimIndent()
    @Media.Image "../media/image-drawing-0070.png"

    @Application
    @ProduceScreenshot("media/image-drawing-0070.png")
    @Code

    application {
        configure {
            width = 720
            height = 960
        }
        program {
            // load an image
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                drawer.clear(ColorRGBa.GRAY)
                drawer.fill = null
                drawer.stroke = ColorRGBa.BLACK.opacify(0.5)

                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))

                drawer.isolated {
                    val cell = drawer.bounds.offsetEdges(-10.0)
                    // first move the cell such that its center is at the origin
                    val cellAroundOrigin = cell.movedTo(-cell.dimensions / 2.0)

                    // set up the drawer to move things back where they came from
                    drawer.translate(cell.center)
                    drawer.rotate(45.0)

                    // draw the image at the origin
                    drawer.imageFit(
                        image,
                        cellAroundOrigin,
                        fitMethod = FitMethod.Contain,
                    )
                }
            }
        }
    }


    @Media.Image "../media/image-drawing-0080.png"

    @Application
    @ProduceScreenshot("media/image-drawing-0080.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            // load an image
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                val grid = drawer.bounds.grid(3, 3, 10.0, 10.0, 10.0, 10.0)
                drawer.clear(ColorRGBa.GRAY)
                drawer.fill = null
                drawer.stroke = ColorRGBa.BLACK.opacify(0.5)

                for ((index, cell) in grid.flatten().withIndex()) {
                    drawer.rectangle(cell)

                    drawer.isolated {
                        // first move the cell such that its center is at the origin
                        val cellAroundOrigin = cell.movedTo(-cell.dimensions / 2.0)

                        drawer.translate(cell.center)
                        drawer.rotate(360.0 * index / 9.0)
                        drawer.imageFit(
                            image,
                            cellAroundOrigin,
                            fitMethod = FitMethod.Contain,
                        )
                    }
                }
            }
        }
    }

    @Text
    """
    ## Drawing parts of images 
                
    """.trimIndent()
    @Media.Image "../media/image-drawing-0090.png"

    @Application
    @ProduceScreenshot("media/image-drawing-0090.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                val grid = drawer.bounds.grid(3, 5, 10.0, 10.0, 10.0, 10.0)
                drawer.clear(ColorRGBa.GRAY)
                drawer.fill = null
                drawer.stroke = ColorRGBa.BLACK.opacify(0.5)

                val rng = Random(seconds.toInt())
                for ((index, cell) in grid.flatten().withIndex()) {
                    drawer.rectangle(cell)
                    drawer.imageFitSub(
                        image,
                        image.bounds.uniformSub(
                            minWidth = 0.25,
                            minHeight = 0.25,
                            random = rng), // random sub-rectangle
                        cell,
                        fitMethod = FitMethod.Contain,
                    )
                }
            }
        }
    }

    @Text
    """
    ## Changing the image appearance 
                
    """.trimIndent()
    @Media.Image "../media/image-drawing-0090.png"

    @Application
    @ProduceScreenshot("media/image-drawing-0090.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                drawer.clear(ColorRGBa.GRAY)
                drawer.fill = null
                drawer.stroke = ColorRGBa.BLACK.opacify(0.5)
                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))

                drawer.drawStyle.colorMatrix = colorMatrix {
                    tint(ColorRGBa.WHITE.opacify(0.5))
                }
                drawer.imageFit(image, drawer.bounds.offsetEdges(-10.0))
            }
        }
    }

    @Media.Image "../media/image-drawing-0100.png"

    @Application
    @ProduceScreenshot("media/image-drawing-0100.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                drawer.clear(ColorRGBa.GRAY)
                drawer.fill = null
                drawer.stroke = ColorRGBa.BLACK.opacify(0.5)
                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))

                drawer.drawStyle.colorMatrix = colorMatrix {
                    grayscale()
                }
                drawer.imageFit(image, drawer.bounds.offsetEdges(-10.0))
            }
        }
    }

    @Media.Image "../media/image-drawing-0110.png"

    @Application
    @ProduceScreenshot("media/image-drawing-0110.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                drawer.clear(ColorRGBa.GRAY)
                drawer.fill = null
                drawer.stroke = ColorRGBa.BLACK.opacify(0.5)
                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))

                drawer.drawStyle.colorMatrix = colorMatrix {
                    grayscale()
                    invert()
                }
                drawer.imageFit(image, drawer.bounds.offsetEdges(-10.0))
            }
        }
    }
}