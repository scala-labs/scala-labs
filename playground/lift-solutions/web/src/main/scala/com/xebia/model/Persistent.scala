package com.xebia.model

import org.scala_libs.jpa.ScalaEntityManager


trait Persistent {
   def sem:ScalaEntityManager

  def persistAndFlush(entity:AnyRef) = sem.persistAndFlush(entity)
  def persist(entity:AnyRef) = sem.persist(entity)
  def remove(entity:AnyRef) = sem.remove(entity)
  def removeAndFlush(entity:AnyRef) = sem.removeAndFlush(entity)
  def merge(entity:AnyRef) = sem.merge(entity)
  def mergeAndFlush(entity:AnyRef) = sem.mergeAndFlush(entity)
  def refresh(entity:AnyRef) = sem.refresh(entity)
  def flush() = sem.flush

}