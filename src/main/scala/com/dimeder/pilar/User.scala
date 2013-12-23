package com.dimeder.pilar

import akka.actor.Actor
import com.dimeder.pilar.engine.GraphUser

/**
 * User: Miguel A. Iglesias
 * Date: 12/9/13
 * Time: 6:16 PM
 */


class User(he: GraphUser.DrawUser) extends Actor with GraphUser {


  def receive = {

    case Room.EmptyDoor(door) => ???

    case Room.MovementX(distance) => he movesX distance

    case Room.MovementY(distance) => he movesY distance
  }

}
