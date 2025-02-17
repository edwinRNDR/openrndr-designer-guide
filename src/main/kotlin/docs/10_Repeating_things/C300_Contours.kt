@file:Title("Contours")
@file:ParentTitle("Repeating things")
@file:URL("repeating-things/contours")

package docs.`10_Repeating_things`

import org.openrndr.application
import org.openrndr.dokgen.annotations.*
import org.openrndr.extra.shapes.primitives.regularPolygon
import org.openrndr.shape.Circle


fun main() {
    @Text
    """
    # Working with contours
    
    Contours in OPENRNDR are used to represent shapes, and are much like Illustrator's paths. We will be looking at contours
    primarily as a tool for repeating things.
     
    """

    //
    @Text
    """
    ## Drawing texts on a circle
    
    """.trimIndent()

    @Media.Image "../media/contours-001.png"

    @Application
    @ProduceScreenshot("media/contours-001.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val c = Circle(drawer.bounds.center, 200.0).contour
                // let's create a list of points
                val pts = c.equidistantPositions(40)
                for (p in pts) {
                    drawer.text("HELLO WORLD", p.x, p.y)
                }
            }
        }
    }

    @Text
    """
    ## Drawing texts on a hexagon
    
    Instead of `Circle` we use `regularPolygon` to create the contour to place text on. 
    
    """.trimIndent()

    @Media.Image "../media/contours-002.png"

    @Application
    @ProduceScreenshot("media/contours-002.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val c = regularPolygon(6, drawer.bounds.center, 200.0)
                // let's create a list of points
                val pts = c.equidistantPositions(40)
                for (p in pts) {
                    drawer.text("HELLO WORLD", p.x, p.y)
                }
            }
        }
    }
}