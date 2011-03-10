package com.xebia.model

import java.util.Date
import javax.persistence._
import org.scala_libs.jpa.ScalaEntityManager
import net.liftweb.common._

@Entity
@NamedQueries(Array(
  new NamedQuery(name = "Position.findAllForUser", query = "SELECT p FROM Position p WHERE p.user.id = :userId"),
  new NamedQuery(name = "Position.findLatestDateForUser", query = "SELECT MAX(p2.recorded) FROM Position p2 where p2.user.id = :userId"),
  new NamedQuery(name = "Position.findLatestForUser", query = "SELECT p FROM Position p WHERE p.user.id = :userId and p.recorded = (SELECT MAX(p2.recorded) FROM Position p2 where p2.user.id = :userId)")))
class Position {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = _

  @Column(nullable = false)
  var lng: Double = _

  @Column(nullable = false)
  var lat: Double = _

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = true)
  var recorded: Date = new Date()

  @ManyToOne
  @JoinColumn(name = "USER_ID", nullable = false, updatable = false, insertable = false)
  var user: User = _


  override def toString = String.format("%s id=%d lng=%f lat=%f recorded=%s", getClass.getName, id.asInstanceOf[AnyRef], lng.asInstanceOf[AnyRef], lat.asInstanceOf[AnyRef], recorded)

}

object Position extends Persistent {

  var sem: ScalaEntityManager = Model

  def apply(user: User, lat: Double , lng: Double , recorded: Date): Position = {
    val t = new Position
    t.lng = lng;
    t.lat = lat;
    t.recorded = recorded
    t.user = user
    t
  }

  def apply(user: User, lat: Double, lng: Double): Position = {
    apply(user, lat, lng, new Date())
  }

  def findLatestForUser(user: User): Box[Position] = {
    try {
      Full(sem.createNamedQuery[Position]("Position.findLatestForUser", "userId" -> user.id).getSingleResult)
    }
    catch {
      case e: javax.persistence.NoResultException => Empty
    }
  }

  def findAllForUser(user: User): List[Position] = {
    sem.createNamedQuery[Position]("Position.findAllForUser", "userId" -> user.id).getResultList.toList
  }

  def findLatestDateForUser(user:User):Date = {
    sem.createNamedQuery[Date]("Position.findLatestDateForUser", "userId" -> user.id).getSingleResult
  }

}