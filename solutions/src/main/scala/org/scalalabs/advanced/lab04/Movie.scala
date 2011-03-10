package org.scalalabs.advanced.lab04


import javax.persistence._
import java.util.Date

@Entity                                            
class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var id : Long = _

  @Column(unique = true, nullable = false)
  var title : String = ""

  @Column(unique = true, nullable = false)
  var description : String = ""

  @Temporal(TemporalType.DATE)
  @Column(nullable = false)
  var released : Date = new Date()

  @ManyToOne(optional = false)
  var director : Director = _

}

object Movie {

  def apply(title:String, desc:String, released:Date, director:Director):Movie = {
    val m = new Movie
    m.title = title
    m.description = desc
    m.released = released
    m.director = director
    director.movies += m
    m
  }
  
}