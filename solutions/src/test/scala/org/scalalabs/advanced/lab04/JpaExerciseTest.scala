package org.scalalabs.advanced.lab04

import org.junit.Test
import org.junit.Assert._

import org.joda.time.DateTime
import JpaExercise._
import org.scalatest.junit.JUnitSuite

/**
 * See @JpaExercise
 */
class JpaExerciseTest extends JUnitSuite {

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
    val movies = findMoviesByDate(new DateTime(2000, 1, 1, 0, 0, 0, 0), new DateTime(2011, 1, 1, 0, 0, 0, 0))
    assert(movies.size == 1)
    removeDirector(pd)
  }

  /**
   * See @JpaExercise
   */
  @Test
  def daoTestFindAllDirectors() = {
    val d = getDirectorWithMovies
    val pd = persistDirectorWithDao(d)
    val directors = findAllDirectorsWithDao
    assert(directors.size == 1)
    removeDirectorWithDao(pd)
  }

  @Test
  def daoTestFindAllMovies() = {
    val d = getDirectorWithMovies
    val pd = persistDirectorWithDao(d)
    var movies = findAllMoviesWithDao
    assert(movies.size == 2)
    removeDirectorWithDao(pd)
  }

  @Test
  def daoTestRemoveMovie() = {
    val d = getDirectorWithMovies
    val pd = persistDirectorWithDao(d)
    var movies = findAllMoviesWithDao
    assert(movies.size == 2)
    removeMovieWithDao(movies(0))
    movies = findAllMoviesWithDao
    assert(movies.size == 1)
    removeDirectorWithDao(pd)
  }

  @Test
  def daoTestFindMoviesByTitle() = {
    val d = getDirectorWithMovies
    val pd = persistDirectorWithDao(d)
    val movies = findMoviesByTitleWithDao("Shakespeare")
    assert(movies.size == 1)
    removeDirectorWithDao(pd)
  }

  def getDirector() = {
    Director("Steven Spielberg", new DateTime(1945, 1, 1, 0, 0, 0, 0).toDate)
  }

  def getDirectorWithMovies() = {
    val d = Director("John Madden", new DateTime(1960, 1, 1, 0, 0, 0, 0).toDate)
    Movie("Ocean's 13", "Ocean's 13", new DateTime(2010, 1, 1, 0, 0, 0, 0).toDate, d)
    Movie("Shakespeare in Love", "Shakespeare in Love", new DateTime(1996, 1, 1, 0, 0, 0, 0).toDate, d)
    d

  }

}