package fosd.net.bdd

import scala.collection.immutable.Queue
import scala.collection.mutable
import scala.ref.WeakReference

class BDDFactory {


  val TRUE = new BDD(-1, null, null, this) {
    override def toString = "true"
    override def isTautology(): Boolean = true
  }
  val FALSE = new BDD(-2, null, null, this) {
    override def toString = "false"
    override def isSatisfiable() = false
    override def isContradiction(): Boolean = true
  }

  private val bddTable: mutable.WeakHashMap[BDD, WeakReference[BDD]] = new mutable.WeakHashMap()
  private val opCache: mutable.WeakHashMap[(Op, BDD, BDD), WeakReference[BDD]] = new mutable.WeakHashMap()
  private val notCache: mutable.WeakHashMap[BDD, WeakReference[BDD]] = new mutable.WeakHashMap()


  private var features: mutable.Map[String, Int] = mutable.Map()
  def varNum = features.size

  private def getFeatureId(s: String) =
    features.getOrElseUpdate(s, features.size)


  def feature(s: String) = mk(getFeatureId(s), FALSE, TRUE)

  def mk(v: Int, low: BDD, high: BDD): BDD =
    if (low eq high)
      low
    else {
      assert(v < features.size, "unknown variable id")
      val newNode = new BDD(v, low, high, this)
      lookupCache(bddTable, newNode, newNode)
    }

  private def isTerminal(bdd: BDD) = (bdd eq TRUE) || (bdd eq FALSE)


  trait Op {
    def apply(a: Boolean, b: Boolean): Boolean
  }

  val AND = new Op {
    override def apply(a: Boolean, b: Boolean) = a && b
  }
  val OR = new Op {
    override def apply(a: Boolean, b: Boolean) = a || b
  }

  private def lookupCache[K, V<:AnyRef](cache: mutable.WeakHashMap[K, WeakReference[V]], k: K, gen: => V): V = {
    val v = cache.get(k).flatMap(_.get)
    if (v.isDefined) v.get
    else {
      val x: V = gen
      cache.put(k, WeakReference.apply(x))
      x
    }
  }

  def apply(op: Op, u1: BDD, u2: BDD) = lookupCache(opCache, (op, u1, u2), {
    var cache: Map[(BDD, BDD), BDD] = Map()

    def app(u1: BDD, u2: BDD): BDD = {
      val cached = cache.get((u1, u2))
      if (cached.nonEmpty)
        return cached.get

      val u =
        if (isTerminal(u1) && isTerminal(u2))
          if (op(u1 eq TRUE, u2 eq TRUE)) TRUE else FALSE
        else if (isTerminal(u2))
          mk(u1.v, app(u1.low, u2), app(u1.high, u2))
        else if (isTerminal(u1))
          mk(u2.v, app(u1, u2.low), app(u1, u2.high))
        else if (u1.v < u2.v)
          mk(u1.v, app(u1.low, u2), app(u1.high, u2))
        else if (u1.v > u2.v)
          mk(u2.v, app(u1, u2.low), app(u1, u2.high))
        else //if (u1.v==u2.v)
          mk(u1.v, app(u1.low, u2.low), app(u1.high, u2.high))

      cache += ((u1, u2) -> u)
      u
    }

    app(u1, u2)
  })





  def build(t: BDD) = {
    build_(t, 0)
  }

  private def build_(t: BDD, v: Int): BDD =
    if ((v >= features.size) || (t eq TRUE) || (t eq FALSE)) t
    else {
      val b0 = build_(sub(t, v, false), v + 1)
      val b1 = build_(sub(t, v, true), v + 1)
      mk(v, b0, b1)
    }

  def sub(bdd: BDD, v: Int, byTrue: Boolean): BDD = if ((bdd eq TRUE) || (bdd eq FALSE)) bdd
  else if (bdd.v == v) {
    if (byTrue) bdd.high else bdd.low
  } else mk(bdd.v, sub(bdd.low, v, byTrue), sub(bdd.high, v, byTrue))


  def not(bdd: BDD): BDD = lookupCache(notCache, bdd, {
    var rewritten: Map[BDD, BDD] = Map()

    def not_(bdd: BDD): BDD =
      if (bdd eq TRUE) FALSE
      else if (bdd eq FALSE) TRUE
      else if (rewritten contains bdd) rewritten(bdd)
      else {
        val negbdd = mk(bdd.v, not_(bdd.low), not_(bdd.high))
        rewritten += (bdd -> negbdd)
        negbdd
      }

    not_(bdd)
  })

  def printDot(bdd: BDD): Unit = {

    def nodeId(b: BDD) =
      if (b == TRUE) 1 else if (b == FALSE) 0 else Math.abs(b.hashCode)

    println("digraph G {")
    println("0 [shape=box, label=\"FALSE\", style=filled, shape=box, height=0.3, width=0.3];")
    println("1 [shape=box, label=\"TRUE\", style=filled, shape=box, height=0.3, width=0.3];")
    var seen: Set[BDD] = Set()
    var queue: Queue[BDD] = Queue() enqueue bdd

    while (queue.nonEmpty) {
      val (b, newQueue) = queue.dequeue
      queue = newQueue
      if (!(seen contains b) && (b != TRUE) && (b != FALSE)) {
        seen = seen + b
        println(nodeId(b) + " [label=\"v" + features.find(_._2 == b.v).map(_._1).getOrElse("unknown_" + b.v) + "\"];")
        println(nodeId(b) + " -> " + nodeId(b.low) + " [style=dotted];")
        println(nodeId(b) + " -> " + nodeId(b.high) + " [style=filled];")
        queue = queue enqueue b.low enqueue b.high
      }

    }

    println("}")
  }


}
