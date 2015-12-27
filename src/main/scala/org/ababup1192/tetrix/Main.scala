package org.ababup1192.tetrix

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

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
      // init paint
      fill = bluishGray
      paint()

      import scalafx.Includes._

      onKeyPressed = (e: KeyEvent) => {
        onKeyPress(e)
        // repaint
        paint()
      }

      def paint(): Unit = {
        val view = ui.view

        def buildFillRect(pos: (Int, Int), color: Color): Rectangle = {
          val rect = Rectangle(pos._1 * (blockSize + blockMargin),
            (view.gridSize._2 - pos._2 - 1) * (blockSize + blockMargin),
            blockSize, blockSize)
          rect.fill = color
          rect
        }

        def buildStrokeRect(pos: (Int, Int), color: Color): Rectangle = {
          val rect = Rectangle(pos._1 * (blockSize + blockMargin),
            (view.gridSize._2 - pos._2 - 1) * (blockSize + blockMargin),
            blockSize, blockSize)
          rect.fill = bluishGray
          rect.stroke = color
          rect
        }

        def drawEmptyGrid = {
          for {
            x <- 0 to view.gridSize._1 - 1
            y <- 0 to view.gridSize._2 - 2
            pos = (x, y)
          } yield buildStrokeRect(pos, bluishLigherGray)
        }

        drawEmptyGrid.foreach { grid =>
          content.add(grid)
        }

        view.blocks.foreach { block =>
          content.add(buildFillRect(block.pos, bluishEvenLigher))
        }

        view.current.foreach { block =>
          content.add(buildFillRect(block.pos, bluishSilver))
        }
      }
    }
  }

}
