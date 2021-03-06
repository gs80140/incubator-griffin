package org.apache.griffin.measure.batch.rule.expr


trait StatementExpr extends Expr with AnalyzableExpr {
  def valid(values: Map[String, Any]): Boolean = true
  override def cacheUnit: Boolean = true
}

case class SimpleStatementExpr(expr: LogicalExpr) extends StatementExpr {
  def calculateOnly(values: Map[String, Any]): Option[Any] = expr.calculate(values)
  val desc: String = expr.desc
  val dataSources: Set[String] = expr.dataSources
  override def getSubCacheExprs(ds: String): Iterable[Expr] = {
    expr.getCacheExprs(ds)
  }
  override def getSubFinalCacheExprs(ds: String): Iterable[Expr] = {
    expr.getFinalCacheExprs(ds)
  }
  override def getSubPersistExprs(ds: String): Iterable[Expr] = {
    expr.getPersistExprs(ds)
  }

  override def getGroupbyExprPairs(dsPair: (String, String)): Seq[(MathExpr, MathExpr)] = expr.getGroupbyExprPairs(dsPair)
}

case class WhenClauseStatementExpr(expr: LogicalExpr, whenExpr: LogicalExpr) extends StatementExpr {
  def calculateOnly(values: Map[String, Any]): Option[Any] = expr.calculate(values)
  val desc: String = s"${expr.desc} when ${whenExpr.desc}"

  override def valid(values: Map[String, Any]): Boolean = {
    whenExpr.calculate(values) match {
      case Some(r: Boolean) => r
      case _ => false
    }
  }

  val dataSources: Set[String] = expr.dataSources ++ whenExpr.dataSources
  override def getSubCacheExprs(ds: String): Iterable[Expr] = {
    expr.getCacheExprs(ds) ++ whenExpr.getCacheExprs(ds)
  }
  override def getSubFinalCacheExprs(ds: String): Iterable[Expr] = {
    expr.getFinalCacheExprs(ds) ++ whenExpr.getFinalCacheExprs(ds)
  }
  override def getSubPersistExprs(ds: String): Iterable[Expr] = {
    expr.getPersistExprs(ds) ++ whenExpr.getPersistExprs(ds)
  }

  override def getGroupbyExprPairs(dsPair: (String, String)): Seq[(MathExpr, MathExpr)] = {
    expr.getGroupbyExprPairs(dsPair) ++ whenExpr.getGroupbyExprPairs(dsPair)
  }
  override def getWhenClauseExpr(): Option[LogicalExpr] = Some(whenExpr)
}