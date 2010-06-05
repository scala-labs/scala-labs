package org.scalalabs.advanced.lab04

import org.scala_libs.jpa.ScalaEntityManager

/**
 * Interface of a generic dao with basic persistency
 * methods
 */
trait GenericDao[T <: { var id:Long}] {
   def findAll() : List[T]
   def save(entity:T) :T
   def remove(entity:T)(implicit m: Manifest[T]):Unit
   def findById(id:Any)(implicit m: Manifest[T]) : T
}

/**
 * Implement an abstract, type independent Dao that
 * extends the GenericDao trait and implements the following methods:
 * - findById
 * - save
 * - remove
 * In order to access the ScalaEntityManager make use of
 * the ScalaEntityManagerFactory trait
 */
abstract class GenericDaoImpl[T <: { var id:Long}] (val semf:ScalaEntityManagerFactory) extends GenericDao[T] {

  def findById(id:Any)(implicit m: Manifest[T]):  T = {
    sem.find(m.erasure, id).asInstanceOf[T]
  }

  def save(entity:T) :T = {
    sem.persist(entity.asInstanceOf[AnyRef])
     entity
  }

  def remove(entity:T)(implicit m: Manifest[T]) = {
    sem.remove(sem.getReference(m.erasure,entity.id).asInstanceOf[AnyRef]);
  }

  def sem = {
    semf.sem
  }

}

/**
 * Implement a concrete Dao for the Director entity that
 * extends from the GenericDaoImpl. In addition, implement
 * the findAll() method
 */
class DirectorDao(semf:ScalaEntityManagerFactory) extends GenericDaoImpl[Director](semf) {

  def findAll():List[Director] = {
    sem.findAll("findAllDirectors")
  }
}

/**
 * Implement a concrete Dao for the Movie entity that
 * extends from the GenericDaoImpl. In addition, implement
 * the findAll() and findByTitle() method
 */
class MovieDao(semf:ScalaEntityManagerFactory) extends GenericDaoImpl[Movie](semf) {

  def findAll():List[Movie] = {
    sem.findAll("findAllMovies")
  }

  def findByTitle(title:String):List[Movie] = {
    sem.findAll("findMoviesByTitle", "title" -> title.toLowerCase)

  }
}
