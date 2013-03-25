package com.ubiregi.commons

import scala.reflect.macros.Context
import scala.util.matching.Regex
import java.util.regex.PatternSyntaxException

object Macros{

  def r(s: String): Regex = macro regexImpl

  def regexImpl(c: Context)(s: c.Expr[String]): c.Expr[Regex] = {
    import c.universe._
    s.tree match {
      case Literal(Constant(string: String)) =>
        try{
          string.r
        }catch{
          case e: PatternSyntaxException => c.abort(c.enclosingPosition, e.toString)
        }
        c.universe.reify(s.splice.r)
    }
  }

  def assertEqual(act: Any, exp: Any): Unit = macro assertEqualImpl

  def assertNotEqual(act: Any, exp: Any): Unit = macro assertNotEqualImpl

  def assertNotEqualImpl(c: Context)(act: c.Expr[Any],exp: c.Expr[Any]): c.Expr[Unit] = {
    import c.universe._
    def treeString[A](expr: c.Expr[A]) = {
      c.Expr[String](Literal(Constant(show(expr.tree))))
    }
    reify({
      if(act.splice == exp.splice) {
        sys.error(s"AssertionError: ${treeString(act).splice} [${act.splice}] is equal to ${treeString(exp).splice} [${exp.splice}]")
      }
    })
  }

  def assertEqualImpl(c: Context)(act: c.Expr[Any],exp: c.Expr[Any]): c.Expr[Unit] = {
    import c.universe._
    def treeString[A](expr: c.Expr[A]) = {
      c.Expr[String](Literal(Constant(show(expr.tree))))
    }
    reify({
      if(act.splice!=exp.splice) {
        sys.error(s"AssertionError: ${treeString(act).splice} [${act.splice}] is not equal to ${treeString(exp).splice} [${exp.splice}]")
      }
    })
  }

}
