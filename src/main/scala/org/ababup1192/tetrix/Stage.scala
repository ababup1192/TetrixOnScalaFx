package org.ababup1192.tetrix

case class GameView(blocks: Seq[Block], gridSize: (Int, Int), current: Seq[Block])

class Stage(size: (Int, Int)) {
  private[this] def dropOffPos = (size._1 / 2.0, size._2 - 3.0)

  private[this] var currentPiece = Piece(dropOffPos, TKind)
  private[this] var blocks = Block((0, 0), TKind) +: currentPiece.current

  def view: GameView = GameView(blocks, size, currentPiece.current)

  def moveLeft() = moveBy(-1.0, 0.0)

  def moveRight() = moveBy(1.0, 0.0)

  def rotateCW() = rotateBy(-math.Pi / 2.0)

  private[this] def rotateBy(theta: Double): Stage = {
    validate(
      currentPiece.rotateBy(theta),
      unload(currentPiece, blocks)) foreach { case (moved, unloaded) =>
      blocks = load(moved, unloaded)
      currentPiece = moved
    }
    this
  }

  private[this] def moveBy(delta: (Double, Double)): Stage = {
    validate(currentPiece.moveBy(delta),
      unload(currentPiece, blocks)).foreach { case (moved, unloaded) =>
      blocks = load(moved, unloaded)
      currentPiece = moved
    }
    this
  }

  private[this] def validate(piece: Piece, blocks: Seq[Block]): Option[(Piece, Seq[Block])] = {
    if (piece.current.map(_.pos).forall(inBounds)) Some(piece, blocks)
    else None
  }

  private[this] def inBounds(pos: (Int, Int)): Boolean = {
    (pos._1 >= 0) && (pos._1 < size._1) && (pos._2 >= 0) && (pos._2 < size._2)
  }

  private[this] def unload(piece: Piece, blocks: Seq[Block]): Seq[Block] = {
    val currentPoss = piece.current.map(_.pos)

    blocks.filterNot(block => currentPoss.contains(block.pos))
  }

  private[this] def load(piece: Piece, blocks: Seq[Block]): Seq[Block] =
    blocks ++ piece.current
}
