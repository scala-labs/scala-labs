package com.xebia.model

import javax.persistence._
import org.scala_libs.jpa.ScalaEntityManager
import net.liftweb.common._

@Entity
@NamedQueries(Array(
  new NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
  new NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :id"),
  new NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name")))
class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = _

  @Column(nullable = false, unique = true)
  var name: String = _
}

object Role extends Persistent {


  var sem: ScalaEntityManager = Model

  def apply(name: String) = {
    val r = new Role
    r.name = name
    r

  }

  def findAll(): List[Role] = sem.createNamedQuery("Role.findAll").findAll.toList

  def findByName(name: String): Box[Role] = sem.createNamedQuery[Role]("Role.findByName", "name" -> name).findAll.toList match {
    case List(r) => Full(r)
    case List(r, _*) => Full(r)
    case _ => Empty
  }

  def getDefaultRole() = findByName("User") match {
    case Full(r) => r
    case _ => throw new IllegalArgumentException("Roles are not initialized properly")
  }

   def initRoles(roles: String*) = {
     val sem = Model.newEM
     val persistedRoles = findAll.map(r => r.name)
      for (role <- roles) {
        if (!persistedRoles.contains(role)) {
           println("persist roles: " + role)
          sem.persist(Role(role));
        }
      }
      sem.flush
      println("available roles: " + sem.createNamedQuery("Role.findAll").findAll.toList)
      sem.close
    }
}