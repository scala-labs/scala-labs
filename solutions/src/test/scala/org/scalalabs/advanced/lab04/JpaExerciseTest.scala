package org.scalalabs.advanced.lab04


import org.junit.Test
import org.junit.Assert._

import javax.persistence._
import java.util.Date
import org.joda.time.DateTime
import JpaExercise._


class JpaExerciseTest {

  @Test
  def testPersistDirector() = {
    val d = getDirector
    val pd = persistDirector(d)
    assert(pd.id != 0)
    removeDirector(pd)
  }

  @Test
  def testPersistDirectorWithMovies() = {
    val d = getDirectorWithMovies
    val pd = persistDirectorWithMovies(d)
    assert(pd.id != 0)
    assert(pd.movies.size == 2)
    removeDirector(pd)
  }

  @Test
  def testFindMoviesByDirector() = {
    val d = getDirectorWithMovies
    val pd = persistDirectorWithMovies(d)
    val movies = findMoviesByDirector(pd)
    assert(movies.size == 2)
    removeDirector(pd)
  }

  @Test
  def testFindMoviesByDate() = {
    val d = getDirectorWithMovies
    val pd = persistDirectorWithMovies(d)
    val movies = findMoviesByDate(new DateTime(2000,1,1,0,0,0,0), new DateTime(2011,1,1,0,0,0,0))
    assert(movies.size == 1)
    removeDirector(pd)
  }


  def getDirector() = {
    Director("Steven Spielberg", new DateTime(1945,1,1,0,0,0,0).toDate)
  }

  def getDirectorWithMovies() = {
    val d = Director("John Madden", new DateTime(1960,1,1,0,0,0,0).toDate)
    Movie("Ocean's 13", "Ocean's 13", new DateTime(2010,1,1,0,0,0,0).toDate, d)
    Movie("Shakespeare in Love", "Shakespeare in Love", new DateTime(1996,1,1,0,0,0,0).toDate, d)
    d

  }


}