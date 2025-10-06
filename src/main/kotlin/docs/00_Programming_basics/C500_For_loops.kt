@file:Suppress("UNUSED_EXPRESSION")
@file:Title("For-loops")
@file:ParentTitle("Programming basics")
@file:Order("500")
@file:URL("programming-basics/For_loops")

package docs.`00_Programming_basics`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.dokgen.annotations.*


fun main() {
    @Text
    """
    # For-loops
    
    For-loops are the essential way of repeating a block of code over a range of values. The Kotlin programming language
    offers a simple but powerful syntax for creating for-loops.
    
    ```kotlin
    for (i in 0 until 10) {
        doSomething(i)
    }
    ```

    Let's see that in action. Copy the following code into a file `Instruction06.kt` and run it. 
    """
    @Media.Image "../media/for-loops-001.jpg"
    @Application
    @ProduceScreenshot("media/for-loops-001.jpg")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                for (i in 0 until 10) {
                    drawer.circle(i * 72.0 + 36.0, 360.0, 10.0)
                }
            }
        }
    }

    @Text
    """
    ## Nested for-loops
            
    For-loops can be nested to create multi-dimensional loops.
    
    ```kotlin
    for (j in 0 until 20) {
        for (i in 0 until 10) {
            doSomething(i, j)
        }
    }
    ```
    """
    @Media.Image "../media/for-loops-002.jpg"
    @Application
    @ProduceScreenshot("media/for-loops-002.jpg")
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
                        drawer.circle(i * 72.0 + 36.0, j * 72.0 + 36.0, 10.0)
                    }
                }
            }
        }
    }
    @Text
    """
    ## Mixing loops and conditionals
            
    """
    @Media.Image "../media/for-loops-003.jpg"
    @Application
    @ProduceScreenshot("media/for-loops-003.jpg")
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
                        if (i > j) {
                            drawer.fill = ColorRGBa.PINK
                        } else {
                            drawer.fill = ColorRGBa.BLUE
                        }
                        drawer.circle(i * 72.0 + 36.0, j * 72.0 + 36.0, 10.0)
                    }
                }
            }
        }
    }
}