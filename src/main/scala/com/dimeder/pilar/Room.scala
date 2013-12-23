package com.dimeder.pilar

import akka.actor.{ActorContext, ActorRef, Props, Actor}
import scalafx.scene.Scene
import com.dimeder.pilar.engine.GraphRoom

/**
 * User: Miguel A. Iglesias
 * Date: 12/9/13
 * Time: 6:29 PM
 */
object Room {

  case class EmptyDoor(door: ActorRef)

  case class Start(scene: Scene)

  case class MovementX(mv: Double)

  case class MovementY(mv: Double)

  trait MoveUser

  case class MoveUserX(id: String, mv: Double) extends MoveUser

  case class MoveUserY(id: String, mv: Double) extends MoveUser

  case class UserEnter(id: String)

  case class UserLeaves(id: String)

}

class Room extends Actor with GraphRoom {



  val north, south, east, west = context.actorOf(Props(classOf[Door], self))

  var users: Map[String, ActorRef] = Map[String, ActorRef]()


  def empty: Receive = {
    case Room.Start(scene) => context become alive(scene)
  }

  def alive(scene: Scene): Receive = {
    case Room.UserEnter(id) =>
      val user = context.actorOf(Props(classOf[User], drawUser(id, scene)), id)
      users += (id -> user)
    case Room.UserLeaves(id) => users -= id
    case Room.MoveUserX(id, mv) => users(id) ! Room.MovementX(mv)
    case Room.MoveUserY(id, mv) => users(id) ! Room.MovementY(mv)
    case Door.Knock(user) => ???
    case Door.Empty(user) => user ! Room.EmptyDoor(sender)
  }

  def receive = empty
}
