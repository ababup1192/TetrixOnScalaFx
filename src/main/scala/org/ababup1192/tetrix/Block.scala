package org.ababup1192.tetrix

case class Block(pos: (Int, Int), kind: PieceKind)

case class GameView(blocks: Seq[Block], gridSize: (Int, Int), current: Seq[Block])

case class Piece(pos: (Double, Double), kind: PieceKind, locals: Seq[(Double, Double)]) {
  def current: Seq[Block] =
    locals map { case (x, y) =>
      Block((math.floor(x + pos._1).toInt, math.floor(y + pos._2).toInt), kind)
    }

  def moveBy(delta: (Double, Double)): Piece =
    copy(pos = (pos._1 + delta._1, pos._2 + delta._2))
}

case object Piece {
  def apply(pos: (Double, Double), kind: PieceKind): Piece =
    kind match {
      case TKind => Piece(pos, kind, Seq((-1.0, 0.0), (0.0, 0.0), (1.0, 0.0), (0.0, 1.0)))
    }
}