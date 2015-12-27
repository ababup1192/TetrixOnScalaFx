package org.ababup1192.tetrix

import org.scalatest._

class StageSpec extends FlatSpec with Matchers {
  def stage = new Stage((10, 20))

  "Moving to the left the current piece" should "change the blocks" in {
    stage.moveLeft().view.blocks.map(_.pos) should contain allOf((0, 0), (3, 17), (4, 17), (5, 17), (4, 18))
  }

  "Moving to the right the current piece" should "change the blocks" in {
    stage.moveRight().view.blocks.map(_.pos) should contain allOf((0, 0), (5, 17), (6, 17), (7, 17), (6, 18))
  }

  "Moving to the left the current piece" should "not hit blocks" in {
    stage.moveLeft().moveLeft().moveLeft().moveLeft().moveLeft().
      view.blocks.map(_.pos) should contain allOf((0, 0), (0, 17), (1, 17), (2, 17), (1, 18))
  }
 "Moving to the right the current piece" should "not hit blocks" in {
    stage.moveRight().moveRight().moveRight().moveRight().moveRight().
      view.blocks.map(_.pos) should contain allOf((0, 0), (7, 17), (8, 17), (9, 17), (8, 18))
  }

  "Roting to the current piece" should "change the blocks" in {
    stage.rotateCW().
      view.blocks.map(_.pos) should contain allOf((0, 0), (5, 18), (5, 17), (5, 16), (6, 17))
  }

}
