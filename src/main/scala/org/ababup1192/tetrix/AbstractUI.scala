package org.ababup1192.tetrix

class AbstractUI {
  private[this] val stage = new Stage((10, 20))

  def left(): Unit = {
    stage.moveLeft()
  }

  def right(): Unit = {
    stage.moveRight()
  }

  def up() {
  }

  def down() {
  }

  def space(): Unit = {
    stage.rotateCW()
  }

  def view: GameView = stage.view
}
