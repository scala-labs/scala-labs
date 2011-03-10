package com.xebia.model

/*
 * Copyright 2008 WorldWide Conferencing, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */

import org.junit.Test
import org.junit.Before
import org.junit.After
import org.junit.Assert._
import org.scala_libs.jpa.{ThreadLocalEM, LocalEMF}
import net.liftweb.common._
import _root_.java.util._

class ModelTest {


  object TestModel extends LocalEMF("jpawebtest") with ThreadLocalEM

  User.sem = TestModel
  Position.sem = TestModel
  Role.sem = TestModel

  @Before
  def initEMF() {

  }

  @After
  def closeEMF() {
  }

  @Test
  def saveUser() = {
    try {
      val email = "another@gmail.com"
      var u = User("Karl", "de Grote", email, "mypwd")
      User.persistAndFlush(u)

      User.findAll.foreach(u => println(u.email))
      u = User.findByEmail(email).get
      assertNotNull(u)

      u.positions.add(Position(u, 1, 2))
      u.positions.add(Position(u, 3, 4))
      User.merge(u)
      User.flush
      u = User.findByEmail(email).get //Model.findAll("User.findByFirstName", "firstName" -> firstName)
      assertEquals(2, u.positions.size)
    } finally {
      removeAll
    }

  }


  @Test
  def saveRole() = {
    val roleName = "Admin"
    val r = Role(roleName)
    val r2 = Role(roleName + "2")
    //    val s =  Model.newEM
    //    val tx = s.getTransaction
    //    //tx.begin
    //    s.persistAndFlush(u)
    //    tx.commit
    Role.persistAndFlush(r)
    Role.persistAndFlush(r2)

    val roles = Role.findAll
    assertEquals(2, roles.size)

  }

  @Test
  def findPositions() = {
    try {
      val email = "another@gmail.com"
      var u = User("Karl", "de Grote", email, "mypwd")
      User.persistAndFlush(u)
      u = User.findByEmail(email).get

      assertNotNull(u)
      var p = Position.findLatestForUser(u)
      assertEquals(p, Empty)

      //add positions
      u.positions.add(Position(u, 1, 2, new Date(System.currentTimeMillis - 1000)))
      u.positions.add(Position(u, 3, 4, new Date(System.currentTimeMillis)))
      User.merge(u)
      p = Position.findLatestForUser(u)
      println("=====================>" + Position.findLatestDateForUser(u))
      println("=====================>" + Position.findAllForUser(u))
      println("=====================>" + Position.findLatestForUser(u))
      assertEquals(4, p.get.lng.toInt)
    }
    finally {
      removeAll
    }
  }

  private def removeAll() = {
    User.findAll.foreach(User.remove(_))
    User.flush
    User.sem.getTransaction.commit
    assertEquals(0, User.findAll.size)
  }

}
