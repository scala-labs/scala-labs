package org.scalalabs.basic.lab02

/** This Lab contains exercises where the usage of higher order collection
  * methods can be rehearsed.
  */
object CollectionExercise01 {

  /** Taken from: <a
    * href="http://code.google.com/codejam/contest/1460488/dashboard">Problem A.
    * Speaking in Tongues</a>
    *
    * Problem The aim of this task is to translate a language into a new
    * language called Googlerese. To translate we take any message and replace
    * each English letter with another English letter. This mapping is
    * one-to-one and onto, which means that the same input letter always gets
    * replaced with the same output letter, and different input letters always
    * get replaced with different output letters. A letter may be replaced by
    * itself. Spaces are left as-is.
    *
    * For example (and here is a hint!), the translation algorithm includes the
    * following three mappings: 'a' -> 'y', 'o' -> 'e', and 'z' -> 'q'. This
    * means that "a zoo" will become "y qee".
    *
    * Sample Input/Output Input: Case 1: ejp mysljylc kd kxveddknmc re
    * jsicpdrysi Case 2: rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd Case 3: de
    * kr kd eoya kw aej tysr re ujdr lkgc jv
    *
    * Output: Case 1: our language is impossible to understand Case 2: there are
    * twenty six factorial possibilities Case 3: so it is okay if you want to
    * just give up
    */
  def googleCodeJamGooglerese(lines: String*): Seq[String] = {

    // figure out missing character mapping
    val input =
      "ejp mysljylc kd kxveddknmc re jsicpdrysi rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd de kr kd eoya kw aej tysr re ujdr lkgc jv " filterNot (_ == ' ')
    val output =
      "our language is impossible to understand there are twenty six factorial possibilities so it is okay if you want to just give up" filterNot (_ == ' ')

    val alphabet = 'a' to 'z'
    val missingIn = alphabet filterNot (input contains _)
    alphabet diff input

    val missingOut = alphabet filterNot (output contains _)
    alphabet diff output
    // z and q, hint says z -> q, so remaining is: q -> z

    // visualize missing chars in alphabet
    val existingCharsSorted = input.toSet.toList.sorted.mkString
    val visualMissingChars = alphabet
      .map(c => if (existingCharsSorted.contains(c)) c else ' ')
      .mkString

    // compute mapping
    val initialMapping = (input zip output).toSet
    // ensure 1 to 1 mapping
    initialMapping.groupBy(_._1).values.forall(_.size == 1)

    val mapper = Map('z' -> 'q', 'q' -> 'z', ' ' -> ' ').withDefaultValue(
      '?'
    ) ++ initialMapping

    lines.map(_ map mapper)
  }
}
/*========================================================== */

object CollectionExercise02 {

  class Person(val age: Int, val name: String)

  /** Take a look at the java class: [[ImperativeSample]]. The
    * groupAdultsPerAgeGroup is implemented using an imperative programming
    * style. In this exercise you will rewrite the groupAdultsPerAgeGroup method
    * using a functional approach. The method does the following:
    *   1. filter out all adults (>= 18) of the list of persons 2. sort the list
    *      by name 3. group each person by their age group, e.g. 30 -> Seq(duke,
    *      jeniffer)
    */
  def groupAdultsPerAgeGroup(persons: Seq[Person]): Map[Int, Seq[Person]] = {
    persons
      .filter(_.age >= 18)
      .sortBy(_.name)
      .groupBy(_.age / 10 * 10)
  }

  def groupAdultsCountPerAgeGroup(persons: Seq[Person]): Map[Int, Int] = {
    persons
      .filter(_.age >= 18)
      .sortBy(_.name)
      .groupBy(_.age / 10 * 10)
      .map { case (ageGroup, persons) => ageGroup -> persons.size }
  }

}

/*========================================================== */

object CollectionExercise03 {

  /** Create a method that checks that each subsequent value is greater than the
    * previous one. E.g.: checkValuesIncrease(Seq(1,2,3)) == true
    * checkValuesIncrease(Seq(1,2,2)) == false
    */
  def checkValuesIncrease[T](
    seq: Seq[T]
  )(implicit ordering: Ordering[T]): Boolean =
    if (seq.size > 1) seq.sliding(2).forall(l => ordering.lt(l(0), l(1)))
    else true
}
/*========================================================== */

object CollectionExercise04 {

  /** Calculate the length of the longest word in a list of sentences. To keep
    * it simple it's ok to use String.split to extract all words of a sentence.
    */
  def calcLengthLongestWord(lines: String*): Int = {
    lines.map(_.split(" ").map(_.length).max).max
    // or:
    lines.flatMap(_.split(" ").map(_.length)).max
  }
}

object CollectionExercise05 {

  /** Filter all even numbers of the given sequence using foldLeft. E.g.
    * Seq(1,2,3) is Seq(2)
    */
  def filterWithFoldLeft(seq: Seq[Int]): Seq[Int] = {
    seq.foldLeft(Seq.empty[Int])((cum, i) => if (i % 2 == 0) cum :+ i else cum)
  }

  /** Group all numbers based on whether they are even or odd using foldLeft.
    * For even use 'true' for odd use 'false'. E.g: Seq(1,2,3) is Map(true ->
    * Seq(2), false -> Seq(1,3))
    */
  def groupByWithFoldLeft(seq: Seq[Int]): Map[Boolean, Seq[Int]] = {
    seq.foldLeft(Map[Boolean, Seq[Int]]()) { (map, next) =>
      val key = next % 2 == 0
      map + (key -> (map.getOrElse(key, Seq()) :+ next))
    }
    // simpler
    seq.foldLeft(Map[Boolean, Seq[Int]]().withDefaultValue(Seq.empty[Int])) {
      (map, next) =>
        val key = next % 2 == 0
        map + ((key, (map(key) :+ next)))
    }

  }
}
