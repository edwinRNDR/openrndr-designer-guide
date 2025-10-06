@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Vars and vals")
@file:ParentTitle("Programming basics")
@file:Order("300")
@file:URL("programming-basics/Vars_and_vals")

package docs.`00_Programming_basics`

import org.openrndr.application
import org.openrndr.dokgen.annotations.*


fun main() {
    @Text
    """
    # Vars and vals
    """

    @Text
    """
    ## Introduction
    
    In kotlin vars and vals are used to declare variables and values. Variables can be reassigned, vals cannot.
    The type of a variable is inferred from context.
    
    * `val x = 1` will create a variable `x` with an integer value of `1`.
    * `val x = 1.0` will create a variable `x` with a floating point value of `1.0`.
    * `val x = "hello"` will create a variable `x` with a string value of `"hello"`.
    
    Let's see how we can use `val` in a simple OPENRNDR program. Copy the following code into a file called `Instruction03.kt`
    """
    @Media.Image "../media/var-and-vals-001.jpg"
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
                val x = 360.0
                val y = 360.0
                val radius = 40.0
                drawer.circle(x, y, radius)
            }
        }
    }

    @Text
    """
    ## A simple program with variables
    
    Copy the following into a file named `Instruction04.kt`                
    """
    @Media.Image "../media/var-and-vals-002.jpg"
    @Application
    @ProduceScreenshot("media/coordinate-system-002.jpg")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            var x = 0.0
            extend {
                drawer.circle(x, 360.0, 20.0)
                x = x + 1.0 // re-assign x to the current value of x plus 1.0
            }
        }
    }


}