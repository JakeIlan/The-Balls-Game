package simple

import java.awt.Color
import java.lang.Math.sqrt

class Ball(var x: Double, var y: Double,
           private var dx: Double, private var dy: Double,
           var radius: Double, val color: Color) {

    fun step(xLimit: Double, yLimit: Double) {
        x += dx
        y += dy
        if (x >= xLimit - radius) {
            x = xLimit - radius - 1
            dx = -dx
        }
        if (y >= yLimit - radius) {
            y = yLimit - radius - 1
            dy = -dy
        }
        if (x < radius) {
            x = radius
            dx = -dx
        }
        if (y < radius) {
            y = radius
            dy = -dy
        }
    }

    fun stop() {
        dx = 0.0
        dy = 0.0
        if (radius > 1) radius /= 1.07
    }

    fun inTouch(other: Ball): Boolean = this.distance(other) < this.radius + other.radius


    fun intX(): Int = x.toInt()

    fun intY():Int = y.toInt()

    fun intRadius():Int = radius.toInt()

    fun inside(other: Ball) = this.distance(other) < other.radius


    fun distance(other: Ball): Double = sqrt(sqr(this.x - other.x) + sqr(this.y - other.y))
}

fun sqr(x: Double): Double = x * x

