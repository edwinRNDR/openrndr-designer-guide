@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Coordinate system")
@file:ParentTitle("Programming basics")
@file:Order("200")
@file:URL("programming-basics/Coordinate_system")

package docs.`00_Programming_basics`

import org.openrndr.application
import org.openrndr.dokgen.annotations.*


fun main() {
    @Text
    """
    # Coordinate system
    """

    @Text
    """
    ## Understanding the coordinate system
    
    Let's quickly explore what the coordinate system is like. Usually the first question you will have when
    exploring a coordinate system is "where is the origin?". Let's find the origin by placing a circle on it.
     
    Make a file called `Instruction02.kt` and copy paste the following code into it.     
    
    """
    @Media.Image "../media/coordinate-system-001.jpg"
    @Application
    @ProduceScreenshot("media/coordinate-system-001.jpg")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.circle(0.0, 0.0, 20.0)
            }
        }
    }

    @Text
    """
    Now let's draw circles at the other corners.        
    """

    @Media.Image "../media/coordinate-system-002.jpg"
    @Application
    @ProduceScreenshot("media/coordinate-system-002.jpg")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.circle(0.0, 0.0, 20.0)
                drawer.circle(720.0, 0.0, 20.0)
                drawer.circle(720.0, 720.0, 20.0)
                drawer.circle(0.0, 720.0, 20.0)
            }
        }
    }

    @Text
    """
    So we have learned that top-left is at (0.0, 0.0), top-right at (720.0, 0.0), bottom-right at (720.0, 720.0)
    and bottom-left at (0.0, 720.0).
            
    Simple exercises:
    - Where is the center of the screen?
    - Change the radii of the circles
    - What happens if you change the size of the window?
    """

}