@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Writing OPENRNDR Programs")
@file:ParentTitle("Programming basics")
@file:Order("100")
@file:URL("programming-basics/OPENRNDR_Program")

package docs.`00_Programming_basics`

import org.openrndr.application
import org.openrndr.dokgen.annotations.*


fun main() {
    @Text
    """
    # Writing programs
    """

    @Text
    """
    ## Creating the program structure
    
    I assume this is your first time working with Kotlin. At this point you have cloned a copy of 
    the OPENRNDR template. (Likely by following instructions from the tutor).
    
    Here we will go over the basic structure of a OPENRNDR program. The most minimal program looks as follows.
    

    ```kotlin
    application {
        program {

        }
    }
    ```
    """
    @Text
    """
    You can go ahead and create a new file called Instruction01.kt in the src/main/kotlin folder. 
    Copy the contents of the example program into that file.
    
    We see a couple of thins here.
     * A line that says `import org.openrndr.application`, which tells us that we are going to be importing a 
      function from the OPENRNDR library.
     * A `fun main()` function, which is the entry point of the program.
     * An `application` block, which contains a `program` block.
    
    When running the program we see a window pop up. The window is empty.
         
    ## Changing the window size
                    
    Let's change our program a tiny bit. We want to change the window size to be larger.

    ```kotlin
    application {
        configure {
            width = 720
            height = 720
        }
        program {

        }
    }
    ```
    """

    @Text
    """
    As you can see a `configure {}` block has been added. Here `{` denotes the start of the configure block and '}' the 
    end. We have added a `width` and `height` property to the configure block. Note that the `=` symbol should be read
    as "assign a value to".
     
    When we run the program now, we should see a window that is 720 pixels wide and 720 pixels high.
    
    ## A very first drawing
    
    Let's change our little program to actually do something. Let's make it draw a circle.
    """

    @Media.Image "../media/programming-basics-001.jpg"
    @Application
    @ProduceScreenshot("media/programming-basics-001.jpg")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                drawer.circle(360.0, 360.0, 100.0)
            }
        }
    }

    @Text
    """
    You'll see that we've added an `extend` block to the program. The `extend` block is where the actual drawing 
    happens. Inside the `extend` block we can call any OPENRNDR function. In this case we called the `drawer.circle` 
    function. The `drawer` is an object that is provided by OPENRNDR that allows us to interact with the drawing 
    surface. In this case we used the `circle` function to draw a circle.
    
    The circle is drawn in the center of the window. The first parameter is the x-coordinate of the center, the 
    second parameter is the y-coordinate of the center and the third parameter is the radius. Note that all the 
    numbers use a fractional format. If we were to use integer numbers such as `360, 360, 100` then the program
    would not run.
    """
}