package org.ababup1192.tetrix


import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class AbstractUI {
  private[this] val stage = new Stage((10, 20))

  def left() {
    stage.moveLeft()
  }

  def right() {
    stage.moveRight()
  }

  def up() {
  }

  def down() {
  }

  def space() {
  }

  def view: GameView = stage.view
}

object Main extends JFXApp {
  val bluishGray = Color.rgb(48, 99, 99)
  val bluishLigherGray = Color.rgb(79, 130, 130)
  val bluishSilver = Color.rgb(210, 255, 255)
  val bluishEvenLigher = Color.rgb(145, 196, 196)

  val blockSize = 16
  val blockMargin = 1

  val ui = new AbstractUI


  stage = new PrimaryStage {
    title.value = "tetrix"
    width = 700
    height = 400

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

        val view = ui.view

        def buildRect(pos: (Int, Int), color: Color): Rectangle = {
          val rect = Rectangle(pos._1 * (blockSize + blockMargin),
            (view.gridSize._2 - pos._2 - 1) * (blockSize + blockMargin),
            blockSize, blockSize)
          rect.fill = color
          rect
        }

        def drawEmptyGrid = {
          for {
            x <- 0 to view.gridSize._1 - 1
            y <- 0 to view.gridSize._2 - 2
            pos = (x, y)
          } yield buildRect(pos, bluishLigherGray)
        }

        drawEmptyGrid.foreach(b => content.add(b))
        view.blocks.foreach(b => content.add(buildRect(b.pos, bluishEvenLigher)))
        view.current.foreach(b => content.add(buildRect(b.pos, bluishSilver)))
      }
    }
  }
}
