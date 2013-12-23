package com.dimeder.pilar.engine

import scalafx.scene.Scene
import scalafx.scene.shape.Circle
import scalafx.scene.paint.Color
import scalafx.application.Platform
import com.dimeder.pilar.engine.GraphRoom.NodeContainer

/**
 * User: Miguel A. Iglesias
 * Date: 12/9/13
 * Time: 10:32 PM
 */

object GraphRoom {

  trait NodeContainer



}

trait GraphRoom {


  def drawUser(id: String, scene: Scene): NodeContainer = {
    val me = new Circle {
      radius = 10
      fill = Color.WHITE
      centerX = 150
      centerY = 125
    }
    Platform.runLater {
      scene.content = me
    }
    GraphUser.DrawUser(me)
  }
}
