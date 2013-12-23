package com.dimeder.pilar.engine

import scalafx.animation.TranslateTransition
import scalafx.util.Duration
import scalafx.scene.Node
import scalafx.Includes._

/**
 * User: Miguel A. Iglesias
 * Date: 12/9/13
 * Time: 10:51 PM
 */
object GraphUser {

  case class DrawUser(node: Node) extends GraphRoom.NodeContainer

}

trait GraphUser {

  implicit def movement(he:GraphUser.DrawUser) = new  {
    def movesX(mv: Double) = {
      move(he) {
        transition =>
          transition.byX = mv
          transition.play()
      }
    }

    def movesY(mv: Double) = {
      move(he) {
        transition =>
          transition.byY = mv
          transition.play()
      }
    }
  }

  def move(he: GraphUser.DrawUser)(action: TranslateTransition => Unit) = he match {
    case GraphUser.DrawUser(u) =>
      val translateTransition = new TranslateTransition {
        duration = Duration(300)
        node = u
      }
      action(translateTransition)
    case _ => ()
  }
}
