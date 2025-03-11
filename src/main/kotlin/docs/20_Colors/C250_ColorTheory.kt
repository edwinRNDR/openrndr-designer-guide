@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Color theory")
@file:ParentTitle("Colors")
@file:Order("250")
@file:URL("colors/colorTheory")

package docs.`20_Colors`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.dokgen.annotations.*
import org.openrndr.extra.color.palettes.analogous
import org.openrndr.extra.color.palettes.splitComplementary
import org.openrndr.extra.color.palettes.tetradic
import org.openrndr.extra.color.palettes.triadic
import org.openrndr.extra.color.presets.LIGHT_GREEN
import org.openrndr.extra.color.presets.ORANGE_RED
import org.openrndr.extra.color.spaces.ColorOKHSVa
import org.openrndr.extra.color.tools.shiftHue
import org.openrndr.extra.shapes.primitives.grid


fun main() {

    @Text
    """
    # Color theory
            
                
    """.trimIndent()

    @Text
    """
    ## Analogous colors
    
    """.trimIndent()

    @Media.Image "../media/color-theory-001.png"

    @Application
    @ProduceScreenshot("media/color-theory-001.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(8, 12, 10.0, 10.0, 10.0, 10.0)
                for ((index, row) in grid.withIndex()) {
                    val palette = ColorRGBa.PINK.analogous<ColorOKHSVa>((index + 1.0) * (360.0/12), 8)
                    for ((x,cell) in row.withIndex()) {
                        drawer.fill = palette[x]
                        drawer.rectangle(cell)
                    }
                }
            }
        }
    }

    @Text
    """
    ## Triadic colors
    
    """.trimIndent()

    @Media.Image "../media/color-theory-002.png"

    @Application
    @ProduceScreenshot("media/color-theory-002.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(3, 12, 10.0, 10.0, 10.0, 10.0)
                for ((index, row) in grid.withIndex()) {
                    val palette = ColorRGBa.RED.shiftHue<ColorOKHSVa>(index * (120.0/12.0)).triadic<ColorOKHSVa>()
                    for ((x,cell) in row.withIndex()) {
                        drawer.fill = palette[x]
                        drawer.rectangle(cell)
                    }
                }
            }
        }
    }

    @Text
    """
    ## Tetradic colors
    
    """.trimIndent()

    @Media.Image "../media/color-theory-003.png"

    @Application
    @ProduceScreenshot("media/color-theory-003.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(4, 12, 10.0, 10.0, 10.0, 10.0)
                for ((index, row) in grid.withIndex()) {
                    val palette = ColorRGBa.ORANGE_RED.tetradic<ColorOKHSVa>(aspectRatio = 0.1 + index * 0.2)
                    for ((x,cell) in row.withIndex()) {
                        drawer.fill = palette[x]
                        drawer.rectangle(cell)
                    }
                }
            }
        }
    }

    @Text
    """
    ## Split complementary colors
    
    """.trimIndent()

    @Media.Image "../media/color-theory-004.png"

    @Application
    @ProduceScreenshot("media/color-theory-004.png")
    @Code
    application {
        configure {
            width = 720
            height = 720
        }
        program {
            extend {
                val grid = drawer.bounds.grid(5, 12, 10.0, 10.0, 10.0, 10.0)
                for ((index, row) in grid.withIndex()) {
                    val palette = ColorRGBa.LIGHT_GREEN
                        .splitComplementary<ColorOKHSVa>((index+1.0) * 1.0/12.0, double = true)

                    for ((x,cell) in row.withIndex()) {
                        drawer.fill = palette[x]
                        drawer.rectangle(cell)
                    }
                }
            }
        }
    }
}