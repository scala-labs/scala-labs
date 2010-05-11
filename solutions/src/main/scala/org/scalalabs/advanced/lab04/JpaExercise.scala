package org.scalalabs.advanced.lab04

import javax.persistence._
                 
import java.util.Date
import org.joda.time.DateTime

object JpaExercise {

  implicit def convertJodaDateTimeToJavaDate(jodaDate:DateTime) = new Date(jodaDate.getMillis)
  implicit def convertJavaDateToJodaDate(date:DateTime) = new DateTime(date.getTime)
  

  def persistDirector(d:Director):Director = {
    return doWithEM {
      Repository.persist(d)
      d
    }
  }

  def removeDirector(d:Director) = {
    doWithEM {
      Repository.remove(Repository.getReference(classOf[Director],d.id))
    }
  }

  def persistDirectorWithMovies(d:Director):Director = {
    persistDirector(d);
  }

  def findMoviesByDirector(d:Director):List[Movie] = {
    return doWithEM {
      Repository.findAll[Movie]("findMoviesByDirector", "id" -> d.id)
    }
  }

  def findMoviesByDate(start:Date, end:Date):List[Movie] = {
    return doWithEM {
      Repository.findAll[Movie]("findMoviesByDate", "startDate" -> start, "endDate" -> end)
    }
  }

  def doWithEM[T](f: => T): T = {
    if(!Repository.isOpen) Repository.newEM
    try {
    return f
    } finally {
      Repository.cleanup
    }
  }

}