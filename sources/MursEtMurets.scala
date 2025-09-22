package myfunlaby

import com.funlabyrinthe.core.*
import com.funlabyrinthe.mazes.*
import com.funlabyrinthe.mazes.std.*

import user.sjrd.levelledground.*

object MursEtMurets extends Module

@definition def allTimePlugin(using Universe) = new AllTimePlugin
@definition def stepLadderPlugin(using Universe) = new StepLadderPlugin
@definition def stepLadder(using Universe) = new StepLadder

class AllTimePlugin(using ComponentInit) extends PlayerPlugin:
  override def perform(player: CorePlayer) = {
    case FallLevelDown(1) => ()
  }
end AllTimePlugin

class StepLadderPlugin(using ComponentInit) extends PlayerPlugin:
  override def perform(player: CorePlayer) = {
    case ClimbLevelUp(1) => ()
  }
end StepLadderPlugin

class StepLadder(using ComponentInit) extends Effect:
  var messageShown: Boolean = false

  painter += "Ladders/StepLadder"

  override def execute(context: MoveContext): Unit = {
    import context.*
    super.execute(context)
    if !messageShown then
      player.showMessage("Ceci est un escabeau")
      messageShown = true
  }

  override def entered(context: MoveContext): Unit = {
    context.player.plugins += stepLadderPlugin
  }

  override def exited(context: MoveContext): Unit = {
    context.player.plugins -= stepLadderPlugin
  }
end StepLadder
