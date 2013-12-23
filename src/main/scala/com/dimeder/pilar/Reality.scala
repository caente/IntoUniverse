package com.dimeder.pilar

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import javafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import javafx.event.EventHandler
import akka.actor.{Props, ActorSystem}


object Reality extends JFXApp {

  val id = "1"

  val system = ActorSystem("Dimeder")


  stage = new PrimaryStage {
    title = "Dimeder"
    width = 300
    height = 250

  }



  def moveUser(move: Room.MoveUser): Unit = room ! move


  override def stopApp(): Unit = {
    super.stopApp()
    system.shutdown()
  }

  val scene = new Scene {
    fill = Color.BLACK
    onKeyPressed = new EventHandler[KeyEvent] {
      def handle(keyEvent: KeyEvent) = {
        keyEvent.getCode match {
          case KeyCode.UP => moveUser(Room.MoveUserY(id, -10))
          case KeyCode.DOWN => moveUser(Room.MoveUserY(id, 10))
          case KeyCode.LEFT => moveUser(Room.MoveUserX(id, -10))
          case KeyCode.RIGHT => moveUser(Room.MoveUserX(id, 10))
          case _ => ()
        }
      }
    }
  }

  stage.scene = scene
  val room = system.actorOf(Props[Room], s"room-$id")
  room ! Room.Start(scene)
  room ! Room.UserEnter(id)

}
