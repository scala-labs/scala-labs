package org.scalalabs.advanced.lab04

import javax.persistence._
                 
import java.util.Date
import org.joda.time.DateTime

/**
 * The JPA exercises let experiment with Scala and JPA,
 * Java's official persistency framework. The exercises
 * are based on a JPA utility framework for Scala, which
 * is nothing more than a thin wrapper around JPA's EntityManager
 * with handy conversion methods for Scala.
 * For more information about the Scala JPA utility framework have
 * a look at: http://scala-tools.org/mvnsites-snapshots/scalajpa/
 * It's noteworthy to tell that this framework is used in lift in case
 * JPA is chosen as persistency technology.
 * This exercise contains two main sections. The first section
 * let you experiment directly with the ScalaEntityManger of Scala JPA.
 * In the second section you will implement DAOs (Data Access Object),
 * which delegate persistency operations to the Scala JPA utility API.
 */
object JpaExercise {

  /*************************************************************************
   * Exercises with the Scala JPA API
   * This API is used in Lift in case JPA is used for persistency
   * instead of Lift's proprietary database mapper framework
   * The Scaladocs of the Scala JPA API can be found here:
   * http://scala-tools.org/mvnsites/scalajpa/scaladocs/index.html
   * Detailed information about the API usage can be found here:
   * http://wiki.liftweb.net/index.php/Lift_and_JPA_%28javax.persistence%29
   * For these exercises you can use the Repository object, which gives
   * you direct access to the ScalaEntityManager
   * @see Repository 
   *************************************************************************/

  /**
   * Use the Repository object to
   * persist a new director entity
   */
  def persistDirector(d:Director):Director = {
    return doWithEM {
      Repository.persist(d)
      d
    }
  }

  /**
   * Use the Repository object to
   * persist a new director entity with movies
   */
  def persistDirectorWithMovies(d:Director):Director = {
    //the implementation for this method is the same
    //as the persistDirector method because
    //the Director entity is configured in such a way
    //that it fully manages the relationship
    //to movies (cascade all)
    persistDirector(d);
  }


  /**
   * Use the Repository object to
   * remove a persisted director entity
   */
  def removeDirector(d:Director) = {
    doWithEM {
      Repository.remove(Repository.getReference(classOf[Director],d.id))
    }
  }


  /**
   * Use the Repository object to
   * implement the finder method:
   * Find movies by director
   * Complete the named query: findMoviesByDirector
   * in the META-INF/orm.xml
   */
  def findMoviesByDirector(d:Director):List[Movie] = {
    return doWithEM {
      Repository.findAll[Movie]("findMoviesByDirector", "id" -> d.id)
    }
  }


  implicit def convertJodaDateTimeToJavaDate(jodaDate:DateTime) = new Date(jodaDate.getMillis)
  implicit def convertJavaDateToJodaDate(date:DateTime) = new DateTime(date.getTime)
  

  /**
   * Use the Repository object to
   * implement the finder method:
   * Find movies by date
   * Use the named query findMoviesByDate: findMoviesByDirector
   * in the META-INF/orm.xml
   * In addition implement an implicit conversion definition
   * that converts org.joda.time.DateTime to a java.util.Date
   */
  def findMoviesByDate(start:Date, end:Date):List[Movie] = {
    return doWithEM {
      Repository.findAll[Movie]("findMoviesByDate", "startDate" -> start, "endDate" -> end)
    }
  }

  /*************************************************************************
   * Exercises with Dao's
   * Take a look at the GenericDao trait.
   * Follow the instructions given in the GenericDao trait in
   * order to implement a generic Dao as well as
   * one for the Director and Movie entity
   *************************************************************************/


  val directorDao = new DirectorDao(Repository)
  val movieDao = new MovieDao(Repository)

  /**
   * Use the DirectorDao to
   * persist a new director entity 
   */
  def persistDirectorWithDao(d:Director):Director = {
    doWithEM {
      directorDao.save(d)
    }
  }

  /**
   * Use the DirectorDao to
   * remove a persisted director entity
   */
  def removeDirectorWithDao(d:Director) = {
    doWithEM {
      directorDao.remove(d)
    }
  }

  /**
   * Use the DirectorDao to
   * find all directors
   */
  def findAllDirectorsWithDao() = directorDao.findAll

  /**
   * Use the MovieDao to
   * find all movies
   */
  def findAllMoviesWithDao() = movieDao.findAll

  /**
   * Use the MovieDao to
   * find all movies based on title
   */
  def findMoviesByTitleWithDao(title:String) = movieDao.findByTitle(title)

  /**
   * Use the MovieDao to
   * remove a movie
   */
  def removeMovieWithDao(m:Movie) = {
    doWithEM {
      movieDao.remove(m)
    }
  }

  /**
   * Helper method
   */
  def doWithEM[T](perform: => T): T = {
    //adds the ScalaEntityManger to ThreadLocal if needed
    Repository.sem
    try {
    return perform
    } finally {
      //Commits and closes the EntityManager
      Repository.cleanup
    }
  }

}