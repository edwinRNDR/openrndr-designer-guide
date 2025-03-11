@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Scattering")
@file:ParentTitle("Ornaments")
@file:Order("200")
@file:URL("ornaments/scattering")

package docs.`60_Ornaments`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.dokgen.annotations.*
import org.openrndr.extra.color.presets.PALE_TURQUOISE
import org.openrndr.extra.noise.phrases.fhash12
import org.openrndr.extra.noise.scatter
import org.openrndr.extra.shadestyles.fills.gradients.gradient
import org.openrndr.extra.shapes.ordering.hilbertOrder
import org.openrndr.shape.Ellipse

fun main() {

    """
    # Scattering
    
    Drawing ornaments and decorations based on scattered points.
    
    ## Scattered points with gradients 
    
    """.trimIndent()
    @Media.Image "../media/scattering-0010.png"

    @Application
    @ProduceScreenshot("media/scattering-0010.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val points = Ellipse(
                drawer.bounds.center.x,
                drawer.bounds.center.y,
                drawer.bounds.dimensions.x / 2.3,
                drawer.bounds.dimensions.y / 2.3
            )
                .scatter(10.0)
                .hilbertOrder(scale = 1.0)

            extend {
                drawer.clear(ColorRGBa.WHITE)
                drawer.stroke = null
                drawer.shadeStyle = gradient<ColorRGBa> {
                    stops[0.0] = ColorRGBa.BLACK
                    stops[0.5] = ColorRGBa.PALE_TURQUOISE
                    stops[1.0] = ColorRGBa.WHITE
                    levelWarpFunction = """$fhash12
                        float levelWarp(vec2 p, float l) {
                            float c = fhash12(p + vec2(3021.0, 121.0));
                            float n = fhash12(p) - 0.5;
                            return l + n * 0.5;
                     }
                    """.trimIndent()
                    stellar {
                        sides = 12
                        radius = 0.40
                        sharpness = 0.4
                    }
                }
                drawer.fill = ColorRGBa.WHITE
                drawer.stroke = null
                drawer.circles(points, points.map { it.distanceTo(drawer.bounds.center) * 0.05 })
            }
        }
    }
}