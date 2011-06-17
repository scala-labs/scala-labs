package duse12.gui

import scala.swing._
import akka.actor.Actor._
import duse12.{TrafficLight, LANE}

/**
 * Main method that launches the Junction GUI
 */
object JunctionGUIMain extends SimpleSwingApplication {
  def top =
    new MainFrame {
      title = "Scala Junction App"
      val junction = JunctionGUIAssembler.assemble

      contents = junction
    }
}

/**
 * GUI assembler
 */
object JunctionGUIAssembler {

  def assemble() = {
    val northLane = LANE.NORTH
    val eastLane = LANE.EAST
    val westLane = LANE.WEST

    //REMOTE ACTORS
    val junction = remote.actorFor("junction", "localhost", 2552)

    val westSens = remote.actorFor("sensor-West", "localhost", 2552)
    val eastSens = remote.actorFor("sensor-East", "localhost", 2552)
    val northSens = remote.actorFor("sensor-North", "localhost", 2552)

    //WIDGETS
    var northSensBtn = new SensorButton(northLane, 375, 530, northSens)
    val north = TrafficLightWidget(northLane, 570, 280, northSensBtn)
    WidgetRegistry.registry += (northLane -> (north, northSensBtn))

    var eastSensBtn = new SensorButton(eastLane, 20, 105, eastSens)
    val east = TrafficLightWidget(eastLane, 120, 280, eastSensBtn)
    WidgetRegistry.registry += (eastLane -> (east, eastSensBtn))

    var westSensBtn = new SensorButton(westLane, 840, 105, westSens)
    var west = TrafficLightWidget(westLane, 570, 40, westSensBtn)
    WidgetRegistry.registry += (westLane -> (west, westSensBtn))

    val westActor = actorOf(new TrafficLight(LANE.WEST,west))
    val northActor = actorOf(new TrafficLight(LANE.NORTH,north))
    val eastActor =actorOf(new TrafficLight(LANE.EAST,east))

    //ACTOR REGISTRATION
    remote.start("localhost",2553)

    //The application must be run with -Dakka.config=akka-gui.conf in order
    //for akka to read the configuration for the gui
    //remote.start()

    remote.register("trafficLight-West",westActor.start)
    remote.register("trafficLight-North",northActor.start)
    remote.register("trafficLight-East",eastActor.start)

    val sensorRandomizer = new SensorRandomizerButton(0, 0, List(northSensBtn, eastSensBtn, westSensBtn))
    val junctionControl = new JunctionControlButton(sensorRandomizer.preferredSize.getWidth.toInt + 10, 0, junction)
    new ImagePanel("/crossing.png", sensorRandomizer, junctionControl, north, east, west, northSensBtn, eastSensBtn, westSensBtn)
  }
}
