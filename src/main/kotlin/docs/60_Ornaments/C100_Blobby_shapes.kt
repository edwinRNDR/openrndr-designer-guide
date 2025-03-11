@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Blobby shapes")
@file:ParentTitle("Ornaments")
@file:Order("100")
@file:URL("ornaments/blobbyShapes")

package docs.`60_Ornaments`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.dokgen.annotations.*
import org.openrndr.extra.camera.Camera2D
import org.openrndr.extra.color.presets.SALMON
import org.openrndr.extra.noise.phrases.fhash12
import org.openrndr.extra.noise.scatter
import org.openrndr.extra.shadestyles.fills.gradients.gradient
import org.openrndr.extra.shapes.hobbycurve.hobbyCurve
import org.openrndr.extra.shapes.ordering.hilbertOrder
import org.openrndr.extra.shapes.ordering.mortonOrder
import org.openrndr.extra.shapes.primitives.subtract

fun main() {

    @Text
    """
    # Blobby shapes
    
    ## Blobby shapes from Hobby curves 
    
    """.trimIndent()

    @Media.Image "../media/blobby-shapes-0010.png"

    @Application
    @ProduceScreenshot("media/blobby-shapes-0010.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val points = drawer.bounds.offsetEdges(-100.0)
                .scatter(50.0)
                .hilbertOrder()
            val curve = hobbyCurve(points, true)

            extend(Camera2D())
            extend {
                drawer.clear(ColorRGBa.WHITE)
                drawer.stroke = null
                drawer.shadeStyle = gradient<ColorRGBa> {
                    stops[0.0] = ColorRGBa.BLACK
                    stops[0.5] = ColorRGBa.PINK
                    stops[1.0] = ColorRGBa.WHITE
                    levelWarpFunction = """$fhash12
                        float levelWarp(vec2 p, float l) {
                            float n = fhash12(p) - 0.5;
                            return l + n * 0.5;
                     }
                    """.trimIndent()
                    radial {

                    }
                }
                drawer.contour(curve)
            }
        }
    }

    @Text
    """
    ## Blobby shapes with circles 
    
    A continuation from the previous example. Circles with gradient are drawn at the same position as the points used 
    to create the Hobby curve.
    
    """.trimIndent()

    @Media.Image "../media/blobby-shapes-0020.png"

    @Application
    @ProduceScreenshot("media/blobby-shapes-0020.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val points = drawer.bounds.offsetEdges(-100.0)
                .scatter(25.0)
                .mortonOrder()
            val curve = hobbyCurve(points, true)

            extend {
                drawer.clear(ColorRGBa.WHITE)
                drawer.stroke = null
                drawer.shadeStyle = gradient<ColorRGBa> {
                    stops[0.0] = ColorRGBa.BLACK
                    stops[0.5] = ColorRGBa.SALMON
                    stops[1.0] = ColorRGBa.WHITE
                    levelWarpFunction = """$fhash12
                        float levelWarp(vec2 p, float l) {
                            float c = fhash12(p + vec2(3021.0, 121.0));
                            float n = fhash12(p) - 0.5;
                            return l + n * 0.5;
                     }
                    """.trimIndent()
                    radial {

                    }
                }
                drawer.fill = ColorRGBa.WHITE
                drawer.stroke = null
                drawer.circles(points, 50.0)

                drawer.fill = null
                drawer.stroke = ColorRGBa.BLACK
                drawer.contour(curve)

                // add a white border
                drawer.shadeStyle = null
                drawer.stroke = null
                drawer.fill = ColorRGBa.WHITE
                drawer.rectangles(drawer.bounds.subtract(drawer.bounds.offsetEdges(-20.0)))
            }
        }
    }

}