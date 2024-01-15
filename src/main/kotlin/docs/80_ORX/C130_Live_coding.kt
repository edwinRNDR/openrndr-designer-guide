@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Live coding")
@file:ParentTitle("ORX")
@file:Order("130")
@file:URL("ORX/liveCoding")

package docs.`10_ORX`

import org.openrndr.Program
import org.openrndr.application
import org.openrndr.dokgen.annotations.*
import org.openrndr.draw.persistent
import org.openrndr.extra.olive.Olive
import org.openrndr.extra.olive.Once
import org.openrndr.extra.olive.oliveProgram
import org.openrndr.extra.camera.Orbital
import org.openrndr.ffmpeg.VideoPlayerFFMPEG

fun main() {
    @Text """
    # Live coding with orx-olive
    
    By using Kotlin's ability to run script files we can build a live coding 
    environment. The `orx-olive` library 
    simplifies the work to be done to set up a live coding environment. Code 
    and additional documentation for the library can be found in the 
    [Github repository](https://github.com/openrndr/orx/tree/master/orx-jvm/orx-olive).
    
    ## Prerequisites
    
    Assuming you are working on an 
    [`openrndr-template`](https://github.com/openrndr/openrndr-template) based
    project, all you have to do is enable `orx-olive` in the `orxFeatures`
    set in `build.gradle.kts` and reimport the gradle project.
    
    ## Basic example
    """

    @Code
    application {
        configure {
            width = 768
            height = 576
        }
        program {
            extend(Olive<Program>())
        }
    }

    @Text 
    """
    When running this script you will see a file called `live.kts` appear 
    in `src/main/kotlin`. When you edit
    this file you will see that changes are automatically detected 
    (after save) and that the program reloads. 
    
    ## Interaction with extensions
    
    The Olive extension works well together with other extensions, but only 
    those which are installed before the Olive extension. In the following 
    example we see the use of `Orbital` in combination with `Olive`.
    """

    @Code
    application {
        program {
            extend(Orbital())
            extend(Olive<Program>())
        }
    }

    @Text """
    ## Adding script drag/drop support
    
    
    A simple trick to turn your live coding host program into a versatile 
    live coding environment is to add file drop support. With this enabled 
    one can drag a .kts file onto the window and drop it to load the script file.
    """

    @Code
    application {
        program {
            extend(Olive<Program>()) {
                this@program.window.drop.listen {
                    this.script = it.files.first()
                }
            }
        }
    }

    @Text """
    ## Adding persistent state
    
    Sometimes you want to keep parts of your application persistent, that 
    means its state will survive a script reload.
    In the following example we show how you can prepare the host program 
    to contain a persistent camera device.
    """

    @Code
    application {
        oliveProgram {
            val camera by Once {
                persistent {
                    VideoPlayerFFMPEG.fromDevice()
                }
            }
            camera.play()
            extend {
                camera.colorBuffer?.let {
                    drawer.image(it,0.0, 0.0, 128.0, 96.0)
                }
            }
        }
    }

    @Text 
    """
    Note that when you create a custom host program you also have to adjust 
    script files to include the program type. For example `live.kts` would 
    become like this.

    ```kotlin
    @file:Suppress("UNUSED_LAMBDA_EXPRESSION")
    import org.openrndr.color.ColorRGBa
    import org.openrndr.draw.*
    
    { program: PersistentProgram ->
        program.apply {
            extend {
                camera.next()
                drawer.drawStyle.colorMatrix = tint(ColorRGBa.GREEN) * grayscale(0.0, 0.0, 1.0)
                camera.draw(drawer)
            }
        }
    }         
    ```         
    """
}
