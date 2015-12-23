package org.ababup1192.tetrix


import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, Text}

class AbstractUI {
  private[this] var lastKey: String = ""

  def left() {
    lastKey = "left"
  }

  def right() {
    lastKey = "right"
  }

  def up() {
    lastKey = "up"
  }

  def down() {
    lastKey = "down"
  }

  def space() {
    lastKey = "space"
  }

  def last: String = lastKey
}

object Main extends JFXApp {
  val bluishGray = Color.rgb(48, 99, 99)
  val bluishSilver = Color.rgb(210, 255, 255)
  val ui = new AbstractUI

  stage = new PrimaryStage {
    title.value = "tetrix"
    width = 400
    height = 700

    def onKeyPress(e: KeyEvent) = {
      e.code match {
        case KeyCode.UP => ui.up()
        case KeyCode.DOWN => ui.down()
        case KeyCode.LEFT => ui.left()
        case KeyCode.RIGHT => ui.right()
        case KeyCode.SPACE => ui.space()
        case _ =>
      }
    }

    scene = new Scene {

      fill = bluishGray

      import scalafx.Includes._

      onKeyPressed = (e: KeyEvent) => {
        onKeyPress(e)
        content = new HBox {
          children = new Text(ui.last) {
            font = Font("times", 50)
            fill = Color.White
          }
        }
      }
    }
  }
}
