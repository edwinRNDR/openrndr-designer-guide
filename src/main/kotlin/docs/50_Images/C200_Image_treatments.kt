@file:Suppress("UNUSED_EXPRESSION")
@file:Title("Image treatments")
@file:ParentTitle("images")
@file:Order("200")
@file:URL("images/imageTreatments")

package docs.`50_Images`

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.dokgen.annotations.*
import org.openrndr.draw.loadImage
import org.openrndr.extra.camera.Camera2D
import org.openrndr.extra.color.presets.BLUE_STEEL
import org.openrndr.extra.color.presets.CRIMSON
import org.openrndr.extra.color.presets.OLIVE
import org.openrndr.extra.color.presets.SALMON
import org.openrndr.extra.imageFit.imageFit
import org.openrndr.extra.noise.phrases.fhash12
import org.openrndr.extra.shadestyles.fills.FillFit
import org.openrndr.extra.shadestyles.fills.SpreadMethod
import org.openrndr.extra.shadestyles.fills.gradients.gradient
import org.openrndr.extra.shadestyles.fills.image.imageFill
import org.openrndr.extra.shadestyles.fills.patterns.pattern
import org.openrndr.math.Vector2

fun main() {
    @Text
    """
    # Image treatments
    
    What follows is a collection of image manipulation techniques.
   
    """.trimIndent()
    @Text
    """
    ## Treatment 1
    
    Use a pattern shade style to create a bubble effect
    """.trimIndent()

    @Media.Image "../media/image-treatment-0010.png"

    @Application
    @ProduceScreenshot("media/image-treatment-0010.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                // draw something to demonstrate the transparency effects
                drawer.shadeStyle = pattern {
                    scale = 100.0
                    patternFit = FillFit.COVER
                    foregroundColor = ColorRGBa.WHITE.shade(0.8)
                    backgroundColor = ColorRGBa.WHITE.shade(0.6)
                    checkers {
                    }
                }
                drawer.rectangle(drawer.bounds)

                drawer.shadeStyle = pattern {
                    this.patternFit = FillFit.COVER
                    scale = 2.5
                    foregroundColor = ColorRGBa.WHITE
                    backgroundColor = ColorRGBa.WHITE.opacify(0.0)
                    dots {
                        dotSize = 0.5
                        rotation = 45.0
                    }
                }
                drawer.imageFit(image, drawer.bounds)
            }
        }
    }

    @Text
    """
    ## Treatment 2
    
    Use a pattern shade style to create a rings effect, combine with a luma gradient to recolor the image.
    """.trimIndent()

    @Media.Image "../media/image-treatment-0020.png"

    @Application
    @ProduceScreenshot("media/image-treatment-0020.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                drawer.shadeStyle = pattern {
                    scale = 100.0
                    patternFit = FillFit.COVER
                    foregroundColor = ColorRGBa.WHITE.shade(0.8)
                    backgroundColor = ColorRGBa.WHITE.shade(0.6)
                    checkers {
                    }
                }
                drawer.rectangle(drawer.bounds)

                drawer.shadeStyle = gradient<ColorRGBa> {
                    stops[0.0] = ColorRGBa.BLACK
                    stops[0.4] = ColorRGBa.OLIVE
                    stops[0.6] = ColorRGBa.SALMON
                    stops[1.0] = ColorRGBa.WHITE
                    quantization = 4
                    levelWarpFunction = """
                    $fhash12
                    float levelWarp(vec2 p, float l) {
                        float n = fhash12(p);
                        return l + n * 0.15;                                            
                    }
                """.trimIndent()
                    luma {

                    }
                } + pattern {
                    this.patternFit = FillFit.COVER
                    scale = 3.5
                    foregroundColor = ColorRGBa.WHITE
                    backgroundColor = ColorRGBa.WHITE.opacify(0.0)
                    dots {
                        strokeWeight = 0.3
                        dotSize = 0.5
                        rotation = 30.0
                    }
                }
                drawer.imageFit(image, drawer.bounds.offsetEdges(-10.0))
            }
        }
    }

    @Text
    """
    ## Treatment 3
    
    Use a pattern shade style to create a line effect, combine with a luma gradient to recolor the image.
    """.trimIndent()

    @Media.Image "../media/image-treatment-0030.png"

    @Application
    @ProduceScreenshot("media/image-treatment-0030.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val image = loadImage("data/images/cheeta.jpg")
            extend {
                drawer.clear(ColorRGBa.WHITE)

                drawer.shadeStyle = gradient<ColorRGBa> {
                    stops[0.0] = ColorRGBa.BLACK
                    stops[0.4] = ColorRGBa.BLUE_STEEL
                    stops[0.6] = ColorRGBa.CRIMSON
                    stops[1.0] = ColorRGBa.WHITE
                    quantization = 16
                    levelWarpFunction = """
                    $fhash12
                    float levelWarp(vec2 p, float l) {
                        float n = fhash12(p);
                        return l + n * 0.15;                                            
                    }
                """.trimIndent()
                    luma {

                    }
                } + pattern {
                    this.patternFit = FillFit.COVER
                    scale = 5.0
                    foregroundColor = ColorRGBa.WHITE
                    backgroundColor = ColorRGBa.WHITE.opacify(0.0)
                    boxes {
                        strokeWeight = 0.3

                        width = 1.0
                        height = 0.5
                        rotation = 30.0
                    }
                }
                drawer.imageFit(image, drawer.bounds.offsetEdges(-10.0))
            }
        }
    }

    @Text
    """
    ## Treatment 4
    
    Use an image fill shade style to create a warped image effect.
    """.trimIndent()

    @Media.Image "../media/image-treatment-0040.png"

    @Application
    @ProduceScreenshot("media/image-treatment-0040.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val image = loadImage("data/images/cheeta.jpg")
            extend(Camera2D())
            extend {
                drawer.clear(ColorRGBa.WHITE)
                drawer.shadeStyle = imageFill {
                    this.image = image
                    fillFit = FillFit.COVER
                    // change this numbers for different warp effects
                    parameter("frequency", Vector2(20.0, 20.0))
                    parameter("amplitude", Vector2(0.1, 0.05))
                    parameter("phase", Vector2(seconds, seconds))

                    spreadMethodX = SpreadMethod.REFLECT
                    spreadMethodY = SpreadMethod.REFLECT
                    domainWarpFunction = """
                    vec2 if_domainWarp(vec2 p) {
                        vec2 r = p;
                        r.x += cos(r.y * p_frequency.x + p_phase.x) * p_amplitude.x;
                        r.y += sin(r.y * p_frequency.y + p_phase.y) * p_amplitude.y;
                        return r;                    
                    }
                """.trimIndent()
                }
                drawer.stroke = null

                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))

            }
        }
    }

    @Text
    """
    ## Treatment 5
    
    Use an image fill shade style to create a warped mosaic image effect.
    """.trimIndent()

    @Media.Image "../media/image-treatment-0050.png"

    @Application
    @ProduceScreenshot("media/image-treatment-0050.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val image = loadImage("data/images/cheeta.jpg")
            extend(Camera2D())
            extend {
                drawer.clear(ColorRGBa.WHITE)
                drawer.shadeStyle = imageFill {
                    this.image = image
                    fillFit = FillFit.COVER
                    spreadMethodX = SpreadMethod.REFLECT
                    spreadMethodY = SpreadMethod.REFLECT

                    parameter("frequency", Vector2(20.0, 20.0))
                    parameter("amplitude", Vector2(0.1, 0.05))
                    parameter("phase", Vector2(seconds, seconds))
                    parameter("quantization", Vector2(128.0, 128.0))
                    domainWarpFunction = """
                    $fhash12 
                    vec2 if_domainWarp(vec2 p) {
                        vec2 q = p;
                        q.x += cos(p.y * p_frequency.x + p_phase.x) * p_amplitude.x;
                        q.y += sin(p.x * p_frequency.y + p_phase.y) * p_amplitude.y;
                        q = floor(q * p_quantization);
                        q /= p_quantization - 1.0;
                        q.x -= cos(p.y * p_frequency.x + p_phase.x) * p_amplitude.x;
                        q.y -= sin(p.x * p_frequency.y + p_phase.y) * p_amplitude.y;
                        return q;
                    }""".trimIndent()
                }
                drawer.stroke = null
                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))
            }
        }
    }

    @Text
    """
    ## Treatment 6
    
    Use an image fill shade style to create a skewed pixelated image effect.
    """.trimIndent()

    @Media.Image "../media/image-treatment-0060.png"

    @Application
    @ProduceScreenshot("media/image-treatment-0060.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val image = loadImage("data/images/cheeta.jpg")
            extend(Camera2D())
            extend {
                drawer.clear(ColorRGBa.WHITE)
                drawer.shadeStyle = imageFill {
                    this.image = image
                    fillFit = FillFit.COVER
                    spreadMethodX = SpreadMethod.REFLECT
                    spreadMethodY = SpreadMethod.REFLECT

                    parameter("skew", Vector2(1.0, 0.0))
                    parameter("quantization", Vector2(8.0, 32.0))
                    domainWarpFunction = """
                    $fhash12 
                    vec2 if_domainWarp(vec2 p) {
                        vec2 q = p;
                        q.x += p.y * p_skew.x;
                        q.y += p.x * p_skew.y;
                        q = floor(q * p_quantization);
                        q /= p_quantization - 1.0;
                        q.x -= p.y * p_skew.x;
                        q.y -= p.x * p_skew.y;
                        
                        return q;
                    }""".trimIndent()
                }
                drawer.stroke = null
                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))
            }
        }
    }

    @Text
    """
    ## Treatment 7
    
    Use an image fill shade style to create a animated pixelated and dithered image effect.
    """.trimIndent()

    @Media.Image "../media/image-treatment-0070.png"

    @Application
    @ProduceScreenshot("media/image-treatment-0070.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val image = loadImage("data/images/cheeta.jpg")
            extend(Camera2D())
            extend {
                drawer.clear(ColorRGBa.WHITE)
                drawer.shadeStyle = imageFill {
                    this.image = image
                    fillFit = FillFit.COVER
                    spreadMethodX = SpreadMethod.REFLECT
                    spreadMethodY = SpreadMethod.REFLECT

                    parameter("amplitude", Vector2(1.0, 1.0))
                    parameter("xorQuantization", Vector2(128.0, 128.0))
                    parameter("quantization", Vector2(32.0, 32.0))
                    parameter("time", seconds * 12.0)
                    domainWarpFunction = """
                    $fhash12 
                    vec2 if_domainWarp(vec2 p) {
                        vec2 q = p;
                        ivec2 ip = ivec2(p * p_xorQuantization + floor(p_time*1.0)/1.0);
                        int x = (ip.x^ip.y) % 9;
                        int y = (ip.x^-ip.y) % 13 % 9;
                        float fx = (float(x) / 8.0 - 0.5) * p_amplitude.x;
                        float fy = (float(y) / 8.0 - 0.5) * p_amplitude.y;
                        q.x += fx;
                        q.y += fy;
                        q = floor(q * p_quantization);
                        q /= p_quantization - 1.0;
                        q.x -= fx;
                        q.y -= fy;
                        
                        return q;
                    }""".trimIndent()
                }
                drawer.stroke = null
                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))
            }
        }
    }


    @Text
    """
    ## Treatment 8
    
    Use an image fill shade style to create a noisy blur effect.
    """.trimIndent()
    @Media.Image "../media/image-treatment-0080.png"

    @Application
    @ProduceScreenshot("media/image-treatment-0080.png")
    @Code
    application {
        configure {
            width = 720
            height = 960
        }
        program {
            val image = loadImage("data/images/cheeta.jpg")
            extend(Camera2D())
            extend {
                drawer.clear(ColorRGBa.WHITE)
                drawer.shadeStyle = imageFill {
                    this.image = image
                    fillFit = FillFit.COVER
                    spreadMethodX = SpreadMethod.REFLECT
                    spreadMethodY = SpreadMethod.REFLECT

                    parameter("amplitude", Vector2(1.0, 1.0))
                    parameter("quantization", Vector2(32.0, 32.0))
                    parameter("time", seconds*12.0)
                    domainWarpFunction = """
                    $fhash12 
                    vec2 if_domainWarp(vec2 p) {
                       float r = fhash12(p) * (1.0-p.y)*0.1;
                       float phi = fhash12(p.yx) * 3.14159265358979323846 * 2.0;
                        
                        return p + vec2(cos(phi), sin(phi)) * r; 
                    }""".trimIndent()
                }
                drawer.stroke = null
                drawer.rectangle(drawer.bounds.offsetEdges(-10.0))
            }
        }
    }
}