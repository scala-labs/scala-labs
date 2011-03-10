package com.xebia.model

import javax.persistence._
import net.liftweb.common.{Failure, Empty, Box, Full}
import org.scala_libs.jpa.ScalaEntityManager
import _root_.java.util.Date

@Entity(name = "Users")
@NamedQueries(Array(
  new NamedQuery(name = "User.findAll", query = "SELECT u FROM Users u"),
  new NamedQuery(name = "User.findFuzzyInFirstAndLastName", query = "SELECT u FROM Users u WHERE UPPER(u.firstName) LIKE :keyword OR UPPER(u.lastName) LIKE :keyword ORDER BY u.firstName"),
  new NamedQuery(name = "User.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
  new NamedQuery(name = "User.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
  new NamedQuery(name = "User.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName"),
  new NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName")))
class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id: Long = _

  @Column(nullable = false)
  var firstName: String = _

  @Column(nullable = false)
  var lastName: String = _

  @Column(nullable = false, unique = true)
  var email: String = _

  @Column(nullable = false)
  var password: String = _

  @Column
  @Temporal(TemporalType.DATE)
  var birthdate: Date = _

  @Lob()
  @Column(name = "ITEM_IMAGE", length = 1048576)
  var photo: Array[Byte] = _

  @Column
  var mimeType: String = _

  @ManyToMany
  @JoinTable(name = "USER_LINKS", joinColumns = Array(new JoinColumn(name = "USER_ID")), inverseJoinColumns = Array(new JoinColumn(name = "LINKED_USER_ID")))
  var linkees: _root_.java.util.Set[User] = new _root_.java.util.HashSet[User]()


  @ManyToOne
  @JoinColumn(name = "ROLE_ID")
  var role: Role = _

  @OneToMany(cascade = Array(CascadeType.ALL))
  @JoinColumn(name = "USER_ID", nullable = false)
  var positions: _root_.java.util.Set[Position] = new _root_.java.util.HashSet[Position]()

  def hasPhoto() = mimeType != null && photo != null

  override def toString = String.format("%s id=%d firstname=%s, lastname=%s, email=%s, smimeType=%s", getClass.getName, id.asInstanceOf[AnyRef], firstName, lastName, email, mimeType)

}

object User extends Persistent {
  var sem: ScalaEntityManager = Model

  def apply(firstName: String, lastName: String, email: String, password: String, birthdate: Date): User = {
    val u = new User
    u.firstName = firstName;
    u.lastName = lastName
    u.email = email
    u.password = password
    u.birthdate = birthdate
    u
  }

  def apply(firstName: String, lastName: String, email: String, password: String): User = {
    apply(firstName, lastName, email, password, null)
  }


  def findFuzzyInFirstAndLastName(keyword: String, maxResults: Int):List[User] = {
    sem.createNamedQuery[User]("User.findFuzzyInFirstAndLastName", "keyword" -> ("%" + keyword.toUpperCase() + "%")).setMaxResults(maxResults).findAll.toList
  }

  def removeById(id: Long) {
    val u = findById(id) match {
      case Some(u) => sem.removeAndFlush(u)
      case _ =>
    }
  }


  def findAll(): List[User] = {
    sem.createNamedQuery[User]("User.findAll").findAll.toList
  }

  def findByEmail(email: String): Box[User] = {
    sem.createNamedQuery[User]("User.findByEmail", "email" -> email).findAll.toList match {
      case Nil => Empty
      case List(u) => Full(u)
      case List(_, _*) => Failure("more than one result found")
    }
  }

  def findById(id: Long): Option[User] = {
    sem.find[User](classOf[User], id)
  }

}