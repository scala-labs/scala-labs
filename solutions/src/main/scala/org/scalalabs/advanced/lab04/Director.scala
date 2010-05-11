package org.scalalabs.advanced.lab04

import _root_.javax.persistence._
import java.util.Date
import _root_.java.util.{Set => JSet}
import scala.collection.mutable.{Set => MSet}
import _root_.java.util.{HashSet => JHashSet}
import scala.collection.JavaConversions._

@Entity
class Director {    

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id : Long = _

  @Column(unique = true, nullable = false)
  var name : String = ""

  @Temporal(TemporalType.DATE)
  @Column(nullable = true)
  var dateOfBirth : Date = new Date()

  @OneToMany(mappedBy = "director", cascade = Array(CascadeType.ALL))  
  var movies: JSet[Movie] = new JHashSet[Movie]()

  def getMovies:MSet[Movie] = movies

  def addMovie(m:Movie) {
    movies += m
  }


}

object Director {

  def apply(name:String, dateOfBirth:Date):Director = {
    val d = new Director
    d.name = name
    d.dateOfBirth = dateOfBirth
    d
  }

  def apply(name:String, dateOfBirth:Date, movies:Seq[Movie]):Director = {
    val d:Director = apply(name, dateOfBirth)
    d.movies = MSet[Movie](movies : _*)
    d
  }

}