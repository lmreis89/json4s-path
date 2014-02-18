package org.adelio

import org.json4s.JsonAST.JValue

/**
 * @author adelio
 * @since 18/02/14
 */
package object json4spath {

  trait JValueSimpleExtractor[Out] {
    def simpleExtract(jv: JValue): Option[Out] = Option(jv.values).flatMap(v => if (v == None) None else Some(v)).map(_.asInstanceOf[Out])
  }

  implicit def stringExtractor  = new JValueSimpleExtractor[String] {}
  implicit def booleanExtractor = new JValueSimpleExtractor[Boolean] {}
  implicit def doubleExtractor  = new JValueSimpleExtractor[Double] {}
  implicit def bigdecExtractor  = new JValueSimpleExtractor[BigDecimal] {}
  implicit def intExtractor     = new JValueSimpleExtractor[Int] {
    override def simpleExtract(jv: JValue) = Option(jv.values).map(_.asInstanceOf[BigInt].toInt)
  }

}