package myfunlaby

import com.funlabyrinthe.core.*
import com.funlabyrinthe.mazes.*
import com.funlabyrinthe.mazes.std.*

import user.sjrd.levelledground.*

object MursEtMurets extends Module:
  override protected def createComponents()(using Universe): Unit =
    val allTimePlugin = new AllTimePlugin
    val stepLadderPlugin = new StepLadderPlugin
    val stepLadder = new StepLadder
  end createComponents
  
  def allTimePlugin(using Universe): AllTimePlugin =
    myComponentByID("allTimePlugin")
  def stepLadderPlugin(using Universe): StepLadderPlugin =
    myComponentByID("stepLadderPlugin")
  def stepLadder(using Universe): StepLadder =
    myComponentByID("stepLadder")
end MursEtMurets

export MursEtMurets.*

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

class StepLadder(using ComponentInit) extends Effect derives Reflector:
  var messageShown: Boolean = false

  painter += "Ladders/StepLadder"

  override def reflect() = autoReflect[StepLadder]

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