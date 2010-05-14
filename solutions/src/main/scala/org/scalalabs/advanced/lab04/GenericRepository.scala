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
 * */
abstract class GenericDaoImpl[T <: { var id:Long}] (val sem:ScalaEntityManager) extends GenericDao[T] {

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

}

/**
 * Implement a concrete Dao for the Director entity that
 * extends from the GenericDaoImpl. In addition, implement
 * the findAll() method
 */
class DirectorDao(sem:ScalaEntityManager) extends GenericDaoImpl[Director](sem) {

  def findAll():List[Director] = {
    sem.findAll("findAllDirectors")
  }
}

/**
 * Implement a concrete Dao for the Movie entity that
 * extends from the GenericDaoImpl. In addition, implement
 * the findAll() and findByTitle() method
 */
class MovieDao(sem:ScalaEntityManager) extends GenericDaoImpl[Movie](sem) {

  def findAll():List[Movie] = {
    sem.findAll("findAllMovies")
  }

  def findByTitle(title:String):List[Movie] = {
    sem.findAll("findMoviesByTitle", "title" -> title.toLowerCase)

  }
}
