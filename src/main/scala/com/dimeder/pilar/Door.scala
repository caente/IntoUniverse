package com.dimeder.pilar

import akka.actor.{ActorRef, Actor}

/**
 * User: Miguel A. Iglesias
 * Date: 12/9/13
 * Time: 7:58 PM
 */

object Door {

  case class Knock(user:ActorRef)

  case class Empty(user:ActorRef)

}

class Door(here: ActorRef) extends Actor {

  var theOtherSide: ActorRef = self

  def receive = {
    case Door.Knock(user) if sender == self => here ! Door.Empty(user)
    case Door.Knock(user) => theOtherSide ! Door.Knock(user)
  }
}
