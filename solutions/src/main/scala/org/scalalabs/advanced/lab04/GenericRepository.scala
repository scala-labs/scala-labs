package org.scalalabs.advanced.lab04

import collection.mutable.Buffer
import scala.reflect.ClassTag
import scala.reflect.ClassTag._
import scala.reflect._
import scala.language.reflectiveCalls

/**
 * Interface of a generic dao with basic persistency
 * methods
 */
trait GenericDao[T <: { var id:Long}] {
   def findAll() : Buffer[T]
   def save(entity:T) :T
   def remove(entity:T):Unit
   def findById(id:Any) : T
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
abstract class GenericDaoImpl[T <: { var id:Long} : ClassTag] (val semf:ScalaEntityManagerFactory) extends GenericDao[T] {

  private def t = classTag[T]
  def findById(id:Any):  T = {
    sem.find(t.runtimeClass, id).asInstanceOf[T] 
  }

  def save(entity:T) :T = {
    sem.persist(entity.asInstanceOf[AnyRef])
     entity
  }

  def remove(entity:T) = {
    sem.remove(sem.getReference(t.runtimeClass,entity.id).asInstanceOf[AnyRef]);
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

  def findAll():Buffer[Director] = {
    sem.findAll("findAllDirectors")
  }
}

/**
 * Implement a concrete Dao for the Movie entity that
 * extends from the GenericDaoImpl. In addition, implement
 * the findAll() and findByTitle() method
 */
class MovieDao(semf:ScalaEntityManagerFactory) extends GenericDaoImpl[Movie](semf) {

  def findAll():Buffer[Movie] = {
    sem.findAll("findAllMovies")
  }

  def findByTitle(title:String):Buffer[Movie] = {
    sem.findAll("findMoviesByTitle", "title" -> title.toLowerCase)

  }
}
