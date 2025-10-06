@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Conditionals")
@file:ParentTitle("Programming basics")
@file:Order("400")
@file:URL("programming-basics/Conditionals")

package docs.`00_Programming_basics`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.dokgen.annotations.*


fun main() {
    @Text
    """
    # Conditionals
    """

    @Text
    """
    ## Introduction
    
    In kotlin a conditional is expressed with the `if` statement.
    
    ```kotlin
    if (condition) {
        do_something()
    }
    ```
    
    Where `condition` can be any boolean expression.
    
    * `1 == 1` evaluates to `true`
    * `1 == 2` evaluates to `false`
    * `"hello" == "hello"` evaluates to `true`
    
    
    ## A simple program with a conditional expression
    
    Copy the following into a file named `Instruction05.kt`                
    """
    @Media.Image "../media/conditionals-001.jpg"
    @Application
    @ProduceScreenshot("media/conditionals-001.jpg")
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

                if (x > 720.0) {
                    x = 0.0
                }
            }
        }
    }

    """
    ## If-else statements        
    """
    @Media.Image "../media/conditionals-002.jpg"
    @Application
    @ProduceScreenshot("media/conditionals-002.jpg")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            var x = 0.0
            extend {
                if (x < 360.0) {
                    drawer.clear(ColorRGBa.PINK)
                } else {
                    drawer.clear(ColorRGBa.BLUE)
                }
                drawer.circle(x, 360.0, 20.0)
                x = x + 1.0 // re-assign x to the current value of x plus 1.0

                if (x > 720.0) {
                    x = 0.0
                }
            }
        }
    }

}