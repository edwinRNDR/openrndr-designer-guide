@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Gradients")
@file:ParentTitle("Colors")
@file:Order("300")
@file:URL("colors/gradients")

package docs.`20_Colors`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.dokgen.annotations.*
import org.openrndr.draw.loadFont
import org.openrndr.extra.color.presets.*
import org.openrndr.extra.color.spaces.OKHSV
import org.openrndr.extra.color.tools.saturate
import org.openrndr.extra.color.tools.shiftHue
import org.openrndr.extra.shadestyles.fills.FillFit
import org.openrndr.extra.shadestyles.fills.FillUnits
import org.openrndr.extra.shadestyles.fills.SpreadMethod
import org.openrndr.extra.shadestyles.fills.gradients.gradient
import org.openrndr.extra.shapes.primitives.contour
import org.openrndr.extra.shapes.primitives.grid
import org.openrndr.extra.shapes.rectify.rectified
import org.openrndr.extra.textoncontour.textOnContour
import org.openrndr.extra.textwriter.writer
import org.openrndr.math.Vector2
import org.openrndr.math.asDegrees
import org.openrndr.math.transforms.buildTransform
import org.openrndr.shape.Circle
import kotlin.math.atan2
import kotlin.math.pow

fun main() {
    @Text
    """
    # Gradients in OPENRNDR
    
    In OPENRNDR gradients are implemented using shade styles. The [orx-shade-styles](https://github.com/openrndr/orx/tree/master/orx-shade-styles)) 
    library implements a wide variety of gradients.
    """

    @Text
    """
    ## Introduction to gradients
    
    A simple linear gradient can be created by setting the gradient type to `linear` in the gradient builder. 
    The colors of the gradient are specified using `stops`. Here we set `stops[0.0]` to `ColorRGBa.BLACK` and `stops[1.0]` 
    to `ColorRGBa.RED` to create a black to red gradient. 
    
    """.trimIndent()

    @Media.Image "../media/gradients-001.png"

    @Application
    @ProduceScreenshot("media/gradients-001.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.shadeStyle = gradient<ColorRGBa> {
                    stops[0.0] = ColorRGBa.BLACK
                    stops[1.0] = ColorRGBa.RED

                    linear {
                        start = Vector2(0.25, 0.25)
                        end = Vector2(0.75, 0.75)
                    }
                }
                drawer.circle(drawer.bounds.center, 200.0)
            }
        }
    }

    @Text
    """
    More colors can be added to the gradient by adding more `stops`. Here we add an additional color at `0.5` using `ColorRGBa.WHITE`.
    The resulting gradient is a mix of black, white and red.            
    """.trimIndent()

    @Media.Image "../media/gradients-001-b.png"

    @Application
    @ProduceScreenshot("media/gradients-001-b.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.shadeStyle = gradient<ColorRGBa> {
                    stops[0.0] = ColorRGBa.BLACK
                    stops[0.5] = ColorRGBa.WHITE
                    stops[1.0] = ColorRGBa.RED

                    linear {
                        start = Vector2(0.25, 0.25)
                        end = Vector2(0.75, 0.75)
                    }
                }
                drawer.circle(drawer.bounds.center, 200.0)
            }
        }
    }

    @Text
    """## Gradient units
        Let's have a look at the gradient units. By default the gradients are defined in relative coordinates, 
        these coordinates are relative to the bounds of the shapes drawn. This is best demonstrated by drawing multiple
        shapes.
        
        In the image below we draw a grid of circles. Each circle has the gradient applied relative to its own bounds.
                
    """.trimIndent()

    @Media.Image "../media/gradients-001-c.png"

    @Application
    @ProduceScreenshot("media/gradients-001-c.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.shadeStyle = gradient<ColorRGBa> {
                    stops[0.0] = ColorRGBa.WHITE
                    stops[1.0] = ColorRGBa.BLACK

                    linear {
                        start = Vector2(0.25, 0.25)
                        end = Vector2(0.75, 0.75)
                    }
                }
                val grid = drawer.bounds.grid(3, 3)
                for (cell in grid.flatten()) {
                    drawer.circle(cell.center, cell.width * 0.45)
                }
            }
        }
    }


    @Text
    """Alternatively we can can configure the gradient to be defined in world space units. We now
    define the `start` and `end` points relative to the world space (which coincides with the view space when the view is not adjusted).
    """.trimIndent()

    @Media.Image "../media/gradients-001-d.png"

    @Application
    @ProduceScreenshot("media/gradients-001-d.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.shadeStyle = gradient<ColorRGBa> {
                    stops[0.0] = ColorRGBa.WHITE
                    stops[1.0] = ColorRGBa.BLACK

                    fillUnits = FillUnits.WORLD

                    linear {
                        start = drawer.bounds.position(0.25, 0.25)
                        end = drawer.bounds.position(0.75, 0.75)
                    }
                }
                val grid = drawer.bounds.grid(3, 3)
                for (cell in grid.flatten()) {
                    drawer.circle(cell.center, cell.width * 0.45)
                }
            }
        }
    }

    @Text
    """
    ## Gradient types
    
    ### Linear gradients    
    
    Linear gradients are created by setting the gradient type to `linear` in the gradient builder. Linear gradients have two
    properties that can be configured: `start` and `end`. These properties are vectors that define the start and end points
    of the gradient line. For the following image we show a grid of linear gradients with varying orientations.
    """.trimIndent()

    @Media.Image "../media/gradients-001-e.png"

    @Application
    @ProduceScreenshot("media/gradients-001-e.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(3, 3).flatten()

                for ((index, cell) in grid.withIndex()) {
                    drawer.shadeStyle = gradient<ColorRGBa> {
                        stops[0.0] = ColorRGBa.WHITE
                        stops[1.0] = ColorRGBa.BLACK

                        linear {
                            start =
                                Vector2(0.25, 0.25).rotate(360.0 * index / (grid.size.toDouble()), Vector2(0.5, 0.5))
                            end = Vector2(0.75, 0.75).rotate(360.0 * index / (grid.size.toDouble()), Vector2(0.5, 0.5))
                        }
                    }
                    drawer.rectangle(cell.offsetEdges(-20.0))
                }
            }
        }
    }

    @Text
    """
    In the next image vary the distance between the start and end points.         
    """.trimIndent()

    @Media.Image "../media/gradients-001-f.png"

    @Application
    @ProduceScreenshot("media/gradients-001-f.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(3, 3).flatten()

                for ((index, cell) in grid.withIndex()) {
                    drawer.shadeStyle = gradient<ColorRGBa> {
                        stops[0.0] = ColorRGBa.PINK
                        stops[1.0] = ColorRGBa.WHITE

                        linear {
                            start = Vector2(0.0, 0.5 * index / 9.0)
                            end = Vector2(0.0, 1.0 - 0.5 * index / 9.0)

                        }
                    }
                    drawer.rectangle(cell.offsetEdges(-20.0))
                }
            }
        }
    }


    @Text
    """
    ### Radial gradients        
    
    Radial gradients are created by setting the gradient type to `radial` in the gradient builder.
    Radial gradients have two properties that can be configured: `radius` and `center`.
    """.trimIndent()

    @Media.Image "../media/gradients-002.png"

    @Application
    @ProduceScreenshot("media/gradients-002.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.shadeStyle = gradient<ColorRGBa> {
                    stops[0.0] = ColorRGBa.PINK
                    stops[1.0] = ColorRGBa.BLUE

                    radial {
                        radius = 0.5
                        center = Vector2(0.5, 0.5)
                    }
                }
                drawer.rectangle(drawer.bounds.offsetEdges(-100.0))
            }
        }
    }

    @Text
    """In the next image we vary the center of the radial gradient."""

    @Media.Image "../media/gradients-002-a.png"

    @Application
    @ProduceScreenshot("media/gradients-002-a.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(5, 5, 10.0, 10.0, 10.0, 10.0)

                for ((y, row) in grid.withIndex()) {
                    for ((x, cell) in row.withIndex()) {
                        drawer.shadeStyle = gradient<ColorRGBa> {
                            stops[0.0] = ColorRGBa.PINK
                            stops[1.0] = ColorRGBa.BLUE

                            radial {
                                radius = 0.5
                                center = Vector2(x / 4.0, y / 4.0)
                            }
                        }
                        drawer.rectangle(cell)
                    }
                }
            }
        }
    }

    @Text
    """Here are the results of varying the `radius` of the radial gradient."""

    @Media.Image "../media/gradients-002-b.png"

    @Application
    @ProduceScreenshot("media/gradients-002-b.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(5, 5, 10.0, 10.0, 10.0, 10.0).flatten()

                for ((index, cell) in grid.withIndex()) {
                    drawer.shadeStyle = gradient<ColorRGBa> {
                        stops[0.0] = ColorRGBa.PINK
                        stops[1.0] = ColorRGBa.BLUE

                        radial {
                            radius = index / 24.0
                        }
                    }
                    drawer.rectangle(cell)
                }
            }
        }
    }

    @Text
    """### Conic gradients
    
    Conic gradients are created by setting the gradient type to `conic` in the gradient builder. Conic
    gradients have three properties that can be configured: `center`, `rotation` and `angle`.
    """.trimIndent()

    @Media.Image "../media/gradients-002-c.png"

    @Application
    @ProduceScreenshot("media/gradients-002-c.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(1, 1, 10.0, 10.0, 10.0, 10.0).flatten()

                for ((index, cell) in grid.withIndex()) {
                    drawer.shadeStyle = gradient<ColorRGBa> {
                        stops[0.0] = ColorRGBa.ORANGE_RED
                        stops[0.5] = ColorRGBa.BLUE_STEEL
                        stops[1.0] = ColorRGBa.OLIVE

                        conic {
                            angle = 360.0
                            center = Vector2(0.5, 0.5)
                        }
                    }
                    drawer.rectangle(cell)
                }
            }
        }
    }

    @Text
    """Let's vary the `rotation` of the conic gradient.
    """.trimIndent()

    @Media.Image "../media/gradients-002-d.png"

    @Application
    @ProduceScreenshot("media/gradients-002-d.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(5, 5, 10.0, 10.0, 10.0, 10.0).flatten()

                for ((index, cell) in grid.withIndex()) {
                    drawer.shadeStyle = gradient<ColorRGBa> {
                        stops[0.0] = ColorRGBa.ORANGE_RED
                        stops[0.5] = ColorRGBa.BLUE_STEEL
                        stops[1.0] = ColorRGBa.OLIVE

                        conic {
                            rotation = 360.0 * index / 25.00
                        }
                    }
                    drawer.rectangle(cell)
                }
            }
        }
    }

    @Text
    """Let's vary the `angle` of the conic gradient.
    """.trimIndent()

    @Media.Image "../media/gradients-002-e.png"

    @Application
    @ProduceScreenshot("media/gradients-002-e.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(5, 5, 10.0, 10.0, 10.0, 10.0).flatten()

                for ((index, cell) in grid.withIndex()) {
                    drawer.shadeStyle = gradient<ColorRGBa> {
                        stops[0.0] = ColorRGBa.ORANGE_RED
                        stops[0.5] = ColorRGBa.BLUE_STEEL
                        stops[1.0] = ColorRGBa.OLIVE

                        conic {
                            angle = (index + 1.0) * 360.0 / 25.0
                        }
                    }
                    drawer.rectangle(cell)
                }
            }
        }
    }

    @Text
    """And finally let's vary the `center` of the conic gradient.
    """.trimIndent()

    @Media.Image "../media/gradients-002-f.png"

    @Application
    @ProduceScreenshot("media/gradients-002-f.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(5, 5, 10.0, 10.0, 10.0, 10.0)

                for ((y, row) in grid.withIndex()) {
                    for ((x, cell) in row.withIndex()) {
                        drawer.shadeStyle = gradient<ColorRGBa> {
                            stops[0.0] = ColorRGBa.ORANGE_RED
                            stops[0.5] = ColorRGBa.BLUE_STEEL
                            stops[1.0] = ColorRGBa.OLIVE

                            conic {
                                center = Vector2(x / 4.0, y / 4.0)
                            }
                        }
                        drawer.rectangle(cell)
                    }
                }
            }
        }
    }

    @Text
    """### Stellar gradients
    
    Stellar gradients are star shaped gradients, they can be used by setting the gradient type to `stellar`. Stellar gradients
    have `rotation`, `center`, `sharpness`, `sides` and `radius` properties.                 
        
    """.trimIndent()

    @Media.Image "../media/gradients-002-g.png"

    @Application
    @ProduceScreenshot("media/gradients-002-g.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(1, 1, 10.0, 10.0, 10.0, 10.0)

                for ((y, row) in grid.withIndex()) {
                    for ((x, cell) in row.withIndex()) {
                        drawer.shadeStyle = gradient<ColorRGBa> {
                            stops[0.0] = ColorRGBa.PEACH_PUFF
                            stops[0.5] = ColorRGBa.LIGHT_SKY_BLUE.opacify(0.5)
                            stops[1.0] = ColorRGBa.BLACK

                            stellar {
                                radius = 0.25
                            }
                        }
                        drawer.rectangle(cell)
                    }
                }
            }
        }
    }

    @Text
    """Let's vary the `sides` horizontally and `sharpness` vertically of the stellar gradient.
        
    """.trimIndent()

    @Media.Image "../media/gradients-002-h.png"

    @Application
    @ProduceScreenshot("media/gradients-002-h.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(7, 7, 10.0, 10.0, 10.0, 10.0)

                for ((y, row) in grid.withIndex()) {
                    for ((x, cell) in row.withIndex()) {
                        drawer.shadeStyle = gradient<ColorRGBa> {
                            stops[0.0] = ColorRGBa.PEACH_PUFF
                            stops[0.5] = ColorRGBa.LIGHT_SKY_BLUE.opacify(0.5)
                            stops[1.0] = ColorRGBa.BLACK

                            stellar {
                                sides = 3 + x
                                sharpness = y / 6.0
                            }
                        }
                        drawer.rectangle(cell)
                    }
                }
            }
        }
    }

    @Text
    """### Elliptical gradients
    
    Elliptical gradients are created by setting the gradient type to `elliptic` in the gradient builder. Elliptical gradients
    have `rotation`, `center`, `radiusX` and `radiusY` properties.
    """.trimIndent()

    @Media.Image "../media/gradients-002-i.png"

    @Application
    @ProduceScreenshot("media/gradients-002-i.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(1, 1, 10.0, 10.0, 10.0, 10.0)

                for ((y, row) in grid.withIndex()) {
                    for ((x, cell) in row.withIndex()) {
                        drawer.shadeStyle = gradient<ColorRGBa> {
                            stops[0.0] = ColorRGBa.CRIMSON
                            stops[0.5] = ColorRGBa.DODGER_BLUE
                            stops[1.0] = ColorRGBa.LIME_GREEN

                            elliptic {
                                radiusX = 0.25
                                radiusY = 0.5
                            }
                        }
                        drawer.rectangle(cell)
                    }
                }
            }
        }
    }
    @Text
    """Let's vary the `radiusX`, `radiusY` and `rotation` of the elliptical gradient.`
    """.trimIndent()

    @Media.Image "../media/gradients-002-j.png"

    @Application
    @ProduceScreenshot("media/gradients-002-j.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(13, 13)
                drawer.stroke = null

                for ((y, row) in grid.withIndex()) {
                    for ((x, cell) in row.withIndex()) {
                        drawer.shadeStyle = gradient<ColorRGBa> {
                            stops[0.0] = ColorRGBa.CRIMSON
                            stops[0.5] = ColorRGBa.DODGER_BLUE
                            stops[1.0] = ColorRGBa.LIME_GREEN

                            spreadMethod = SpreadMethod.REPEAT
                            elliptic {
                                val v = Vector2(x - 6.0, y - 6.0)
                                rotation = atan2(y - 6.0, x - 6.0).asDegrees + 180.0
                                radiusX = 1.0
                                radiusY = 1.0 / (1.0 + v.length * 0.25)
                            }
                        }
                        drawer.rectangle(cell)
                    }
                }
            }
        }
    }
    @Text
    """## Gradient spread method
    
    The spread method controls how the gradient is distributed across the area it covers. The available options are:
    `PAD`, `REFLECT`, `REPEAT`. The default is `PAD`.
    
    In the image below we show all three methods side by side.
    """.trimIndent()

    @Media.Image "../media/gradients-002-k.png"

    @Application
    @ProduceScreenshot("media/gradients-002-k.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(3, 1, 10.0, 10.0, 10.0, 10.0).flatten()
                drawer.stroke = null

                for ((x, cell) in grid.withIndex()) {
                    drawer.shadeStyle = gradient<ColorRGBa> {
                        stops[0.0] = ColorRGBa.CORAL
                        stops[0.5] = ColorRGBa.SKY_BLUE
                        stops[1.0] = ColorRGBa.DARK_OLIVE_GREEN

                        spreadMethod = when (x) {
                            0 -> SpreadMethod.PAD
                            1 -> SpreadMethod.REFLECT
                            2 -> SpreadMethod.REPEAT
                            else -> error("nothing else")
                        }
                        linear {
                            start = Vector2(0.5, 1.0 / 3.0)
                            end = Vector2(0.5, 2.0 / 3.0)

                        }
                    }
                    drawer.rectangle(cell)
                }
            }
        }
    }

    @Text
    """## Gradient quantization
    
    We can quantize the output levels of the gradient by setting `quantization` to the desired number of
    quantization levels. The default is `0`.
    
    """.trimIndent()

    @Media.Image "../media/gradients-002-l.png"

    @Application
    @ProduceScreenshot("media/gradients-002-l.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(15, 1, 10.0, 10.0, 0.0, 0.0).flatten()
                drawer.stroke = null
                for ((x, cell) in grid.withIndex()) {
                    drawer.shadeStyle = gradient<ColorRGBa> {
                        fillUnits = FillUnits.WORLD
                        stops[0.0] = ColorRGBa.PINK
                        stops[0.5] = ColorRGBa.DARK_CYAN
                        stops[1.0] = ColorRGBa.DARK_SEA_GREEN
                        quantization = 2 + x
                        linear {
                            start = drawer.bounds.position(0.5, 0.0)
                            end = drawer.bounds.position(0.5, 1.0)
                        }
                    }
                    drawer.rectangle(cell)
                }
            }
        }
    }


    @Text
    """
    ## Designing with gradients
    
    ### Gradients and typography
    
    Here are some tips and examples for designing with type and gradients.
   
    ### Demonstration 1  

    This title screen is designed with a linear gradient in the background. The type is drawn 
    with an elliptic gradient.
    """.trimIndent()

    @Media.Image "../media/gradients-003-a.png"

    @Application
    @ProduceScreenshot("media/gradients-003-a.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {

                drawer.shadeStyle = gradient<ColorRGBa> {
                    stops[0.0] = ColorRGBa.BLACK
                    stops[0.5] = ColorRGBa.BLUE
                    stops[1.0] = ColorRGBa.BLACK
                    linear {

                    }
                    quantization = 16
                }
                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))

                drawer.shadeStyle = gradient<ColorRGBa> {
                    stops[0.0] = ColorRGBa.PINK
                    stops[1.0] = ColorRGBa.BLUE.opacify(0.0)

                    fillFit = FillFit.CONTAIN
                    elliptic {
                        radiusY = 0.75
                        radiusX = 0.5
                        rotation = 45.0
                    }
                    quantization = 8
                }

                drawer.fontMap = loadFont("data/fonts/default.otf", 128.0)
                writer {
                    box = drawer.bounds
                    horizontalAlign = 0.5
                    verticalAlign = 0.5
                    text("IN A LAND BEFORE TIME")
                }
            }
        }
    }

    @Text
    """### Demonstration 2

    This title screen is designed with a linear rainbow gradient in the background. The type is drawn
    with a similar but more vibrant gradient with quantization set to 32.
    """.trimIndent()


    @Media.Image "../media/gradients-003-b.png"
    @Application
    @ProduceScreenshot("media/gradients-003-b.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.shadeStyle = gradient<ColorRGBa> {
                    (0 .. 10).forEach {
                        stops[it / 10.0] = ColorRGBa.RED.saturate<OKHSV>(0.30).shiftHue<OKHSV>(it * 36.0)
                    }
                    fillUnits = FillUnits.WORLD
                    linear {
                        start = drawer.bounds.position(0.5, 0.0)
                        end =  drawer.bounds.position(0.5, 1.0)
                    }
                }
                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))

                drawer.shadeStyle = gradient<ColorRGBa> {
                    (0 .. 10).forEach {
                        stops[it / 10.0] = ColorRGBa.RED.saturate<OKHSV>(1.0).shiftHue<OKHSV>(it * 36.0)
                    }
                    fillUnits = FillUnits.WORLD
                    linear {
                        start = drawer.bounds.position(0.5, 0.0)
                        end =  drawer.bounds.position(0.5, 1.0)
                    }
                    quantization = 32
                }

                drawer.fontMap = loadFont("data/fonts/default.otf", 128.0)
                writer {
                    box = drawer.bounds
                    horizontalAlign = 0.5
                    verticalAlign = 0.5
                    text("RETURN TO A LAND BEFORE TIME")
                }
            }
        }
    }

    @Text
    """### Demonstration 3

    This title screen is designed with a 4 sided stellar gradient in the background. The type is drawn
    with a similar gradient but with the color slightly offset.
    """.trimIndent()

    @Media.Image "../media/gradients-003-c.png"

    @Application
    @ProduceScreenshot("media/gradients-003-c.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.shadeStyle = gradient<ColorRGBa> {
                    (0 .. 10).forEach {
                        stops[it / 10.0] = ColorRGBa.RED.saturate<OKHSV>(0.30).shiftHue<OKHSV>(it * 36.0)
                        stops[(it+0.5) / 10.0] = ColorRGBa.BLACK
                    }
                    fillUnits = FillUnits.WORLD
                    spreadMethod = SpreadMethod.REPEAT
                    quantization = 19
                    stellar {
                        center = drawer.bounds.position(0.5, 0.25)
                        radius = drawer.bounds.width / 2.0
                        rotation = 45.0
                        sides = 4
                        sharpness = 0.0
                    }
                }
                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))

                drawer.shadeStyle = gradient<ColorRGBa> {
                    (0 .. 10).forEach {
                        stops[it / 10.0] = ColorRGBa.RED.saturate<OKHSV>(0.30).shiftHue<OKHSV>((it+1) * 36.0)
                        stops[(it+0.5) / 10.0] = ColorRGBa.BLACK
                    }
                    fillUnits = FillUnits.WORLD
                    spreadMethod = SpreadMethod.REPEAT
                    quantization = 19 * 4
                    stellar {
                        center = drawer.bounds.position(0.5, 0.25)
                        radius = drawer.bounds.width / 2.0
                        rotation = 45.0
                        sides = 4
                        sharpness = 0.0
                    }
                }

                drawer.fontMap = loadFont("data/fonts/default.otf", 164.0)
                writer {
                    box = drawer.bounds.offsetEdges(-20.0)
                    horizontalAlign = 0.5
                    verticalAlign = 1.0
                    text("SON OF THE LAND BEFORE TIME")
                }
            }
        }
    }

    @Text
    """### Demonstration 4

    This title screen is made by drawing the text a 100 times. Each copy has a slightly different gradient,
    each copy uses different text alignment configuration.
    """.trimIndent()

    @Media.Image "../media/gradients-003-d.png"

    @Application
    @ProduceScreenshot("media/gradients-003-d.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                for (i in 0 until 100) {
                    drawer.fill = ColorRGBa.WHITE.shade(i / 99.0)
                    drawer.shadeStyle = gradient<ColorRGBa> {
                        (0..10).forEach {
                            stops[it / 10.0] = ColorRGBa.RED.saturate<OKHSV>(0.0).shiftHue<OKHSV>(it * 36.0)
                            stops[(it + 0.9) / 10.0] = ColorRGBa.BLACK.opacify(0.0)
                        }
                        fillUnits = FillUnits.WORLD
                        spreadMethod = SpreadMethod.REPEAT
                        //quantization = 19
                        stellar {
                            center = drawer.bounds.position(i/99.0, i/99.0)
                            radius = drawer.bounds.width / 8.0
                            rotation = 45.0
                            sides = 8
                            sharpness = 0.5
                        }
                    }

                    drawer.fontMap = loadFont("data/fonts/default.otf", 164.0)
                    writer {
                        box = drawer.bounds.offsetEdges(-20.0)
                        horizontalAlign = i/99.0
                        verticalAlign = i/99.0
                        text("BEFORE THE LAND BEFORE TIME")
                    }
                }
            }
        }
    }

    @Text
    """### Demonstration 5

    This title screen is made by drawing the text a 100 times. Each copy has a slightly different gradient,
    each copy uses different text alignment configuration.
    """.trimIndent()


    @Media.Image "../media/gradients-003-e.png"

    @Application
    @ProduceScreenshot("media/gradients-003-e.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val gs = gradient<ColorRGBa> {
                    stops[0.0] = ColorRGBa.LIGHT_CORAL
                    stops[0.5] = ColorRGBa.ORANGE
                    stops[1.0] = ColorRGBa.OLIVE

                    fillUnits = FillUnits.WORLD
                    spreadMethod = SpreadMethod.REPEAT
                    radial {
                        radius = drawer.bounds.width / 8.0
                    }
                }
                drawer.shadeStyle = gs
                drawer.stroke = null
                drawer.rectangle(drawer.bounds)

                drawer.shadeStyle = null
                drawer.fill = ColorRGBa.WHITE.opacify(0.9)
                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))

                drawer.fill = ColorRGBa.WHITE
                drawer.fontMap = loadFont("data/fonts/default.otf", 164.0)
                drawer.shadeStyle = gs
                writer {
                    horizontalAlign = 0.5
                    verticalAlign = 0.5
                    box = drawer.bounds.offsetEdges(-20.0)
                    leading = -50.0
                    tracking = 10.0
                    text("RETURN OF THE SON OF THE LAND BEFORE TIME")
                }
            }
        }
    }

    @Text
    """### Demonstration 6

    This title screen is made by drawing the text a 100 times. Each copy has a slightly different gradient,
    each copy uses different text alignment configuration.
    """.trimIndent()


    @Media.Image "../media/gradients-003-f.png"

    @Application
    @ProduceScreenshot("media/gradients-003-f.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val gs = gradient<ColorRGBa> {
                    stops[0.0] = ColorRGBa.LIGHT_CORAL
                    stops[0.49] = ColorRGBa.LIGHT_CORAL.opacify(0.0)
                    stops[0.5] = ColorRGBa.ORANGE
                    stops[1.0] = ColorRGBa.OLIVE

                    fillUnits = FillUnits.WORLD
                    spreadMethod = SpreadMethod.REPEAT
                    radial {
                        radius = drawer.bounds.width / 32.0
                        center = drawer.bounds.center
                    }
                }
                drawer.shadeStyle = gs

                for (i in 1 until 12) {
                    val scale = 1.2.pow(-i.toDouble())
                    drawer.fontMap = loadFont("data/fonts/default.otf", 92.0 * scale)

                    val contour = Circle(drawer.bounds.center, 360.0 * scale).contour.transform(buildTransform {
                        translate(drawer.bounds.center)
                        rotate(i * 360.0/12.0)
                        translate(-drawer.bounds.center)
                    }).rectified()
                    drawer.textOnContour("THE RETURN OF THE SON TO BEFORE THE LAND BEFORE TIME.", contour)

                }
            }
        }
    }



    @Media.Image "../media/gradients-003.png"

    @Application
    @ProduceScreenshot("media/gradients-003.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.stroke = null
                for (i in 9 downTo 0) {
                    drawer.shadeStyle = gradient<ColorRGBa> {
                        stops[0.0] = ColorRGBa.PINK
                        stops[1.0] = ColorRGBa.MAGENTA

                        linear {
                            start = Vector2(0.5, 0.0).rotate(i * 36.0, Vector2(0.5, 0.5))
                            end = Vector2(0.5, 1.0).rotate(i * 36.0, Vector2(0.5, 0.5))
                        }
                    }
                    drawer.circle(drawer.bounds.center, 200.0 + i * 15.0)
                }
            }
        }
    }

    @Text
    """
    ## Linear gradients with animated rotation
    
    Simply by including a `seconds` value in the gradient builder the gradient will rotate over time.
    
    """.trimIndent()

    @Media.Video "../media/gradients-004.mp4"

    @Application
    @ProduceVideo("media/gradients-004.mp4")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.stroke = null
                for (i in 9 downTo 0) {
                    drawer.shadeStyle = gradient<ColorRGBa> {
                        stops[0.0] = ColorRGBa.PINK
                        stops[1.0] = ColorRGBa.MAGENTA

                        linear {
                            start = Vector2(0.5, 0.0).rotate(seconds * 36.0 + i * 36.0, Vector2(0.5, 0.5))
                            end = Vector2(0.5, 1.0).rotate(seconds * 36.0 + i * 36.0, Vector2(0.5, 0.5))
                        }
                    }
                    drawer.circle(drawer.bounds.center, 200.0 + i * 15.0)
                }
            }
        }
    }
}
