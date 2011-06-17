package duse12.gui

import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.IOException
import duse12.{LANE, LightSwitch}
import LANE._
import swing._
import javax.swing.Timer
import java.awt.event.ActionEvent
import java.awt.{BasicStroke, Color, Rectangle, Dimension}
import java.awt.geom.Line2D
import java.util.Random
import scala.math._
import GUIUtils._
import collection.mutable.{Buffer, HashMap}
import akka.actor.ActorRef
import akka.actor.Actor._
import duse12.messages.{MsgUtils, VehicleQueued, ControlTraffic, VehicleDetected}

//================================
// GUI widgets
//================================
/**
 * Registry for trafficlight and sensor widget
 */
object WidgetRegistry {
  val registry = new HashMap[HEADING, (TrafficLightWidget, SensorButton)]()
}


/**
 * Null Layout, which allows for absolute positioning.
 * Such a panel is not available withing scala.swing
 */
class NullLayoutPanel extends Panel {
  peer.setLayout(null)

  protected def add(comp: Component) {
    comp.peer.setBounds(comp.bounds)
    peer.add(comp.peer)
  }
}

abstract class AbstractTimerButton(x: Int, y: Int, btnText: String = "") extends Button {
  val timer: Timer
  private val startText = "Start " + btnText
  private val stopText = "Stop " + btnText

  var active = false
  action = Action(startText) {
    if (!active) {
      timer.start
      active = true
      text = stopText
    }
    else {
      timer.stop
      active = false
      text = startText
    }
  }

  override def bounds = new Rectangle(x, y, preferredSize.getWidth.toInt, preferredSize.getHeight.toInt)
}

/**
 * ImagePanel that displays an image
 */
class ImagePanel(imagePath: String, comps: Component*) extends NullLayoutPanel {
  val image = loadImage(imagePath)
  comps.foreach(add(_))

  private def loadImage(imagePath: String): BufferedImage = {
    try {
      ImageIO.read(getClass.getResourceAsStream(imagePath))
    } catch {
      case ex: IOException => {
        throw new RuntimeException("Image not found for path " + imagePath)
      }
    }
  }

  preferredSize = new Dimension(image.getWidth(null), image.getHeight(null))

  override def paintComponent(g: Graphics2D): Unit = {
    g.drawImage(image, 0, 0, null)
  }
}


/**
 * Button to activate junction conrol
 */
class JunctionControlButton(x: Int, y: Int, junction: ActorRef, controlInverval: Int = 2000) extends AbstractTimerButton(x, y, "junction control") {

  val timer: Timer = new Timer(controlInverval, (e: ActionEvent) => {
    junction ! ControlTraffic()
  })
}


//==============================================
// Sensor
//==============================================
/**
 * Button to active a sensor
 */
class SensorButton(lane: HEADING, x: Int, y: Int, val sensor: ActorRef) extends Button {

  private val isVertical = LANE.isVertical(lane)
  private val width = if (isVertical) 30 else 150
  private val height = if (isVertical) 150 else 30

  override def bounds = new Rectangle(x, y, width, height)

  action = Action(createText("0")) {
    (sensor !! VehicleDetected(MsgUtils.genNextId, false)).as[Int] match {
      case Some(queueCount) => refresh(queueCount.toString)
      case _ => refresh("?")
    }
  }

   def refresh(count: String) = synchronized {
      text = (createText(count))
   }

  private def createText(text: String): String = {
    return "<html>" + text + "</html>"
  }
}

/**
 * Random sensor activator button
 */
class SensorRandomizerButton(x: Int, y: Int, sensorButtons: List[SensorButton], incrementInverval: Int = 200) extends AbstractTimerButton(x, y, "random queuing") {
  val timer: Timer = new Timer(incrementInverval, (e: ActionEvent) => {
    var randomIndex: Int = abs(new Random().nextInt % sensorButtons.length)
    sensorButtons(randomIndex).doClick()
  })
}


//==============================================
// Trafficlight
//==============================================


/**
 * Factory for TrafficLightWidgets
 */
object TrafficLightWidget {

  def apply(heading: HEADING, x: Int, y: Int, sensorBtn: SensorButton) = {
    val (rows, cols) = defineRowAndCols(heading)
    new TrafficLightWidget(heading, rows, cols, x, y)(sensorBtn)
  }

  private def defineRowAndCols(heading: HEADING): (Int, Int) = {
    if (heading == LANE.NORTH) (0, 1) else (0, 5)
  }

}

/**
 * TrafficLightWidget
 */
class TrafficLightWidget(val heading: HEADING = NORTH, rows: Int, cols: Int, x: Int, y: Int, dequeueInterval: Int = 300)(val sensorBtn: SensorButton) extends GridPanel(rows, cols) with LightSwitch {

  private val green = new TrafficLightLamp(Color.green)
  private val yellow = new TrafficLightLamp(Color.yellow)
  private val red = new TrafficLightLamp(Color.red)
  private val lock: AnyRef = new AnyRef
  /**the dequeueTimer automatically dequeues vehicles when the trafficlight has
   * switched to green */
  private val dequeueTimer = new Timer(dequeueInterval, (e: ActionEvent) => {
    (sensorBtn.sensor !! VehicleDetected(MsgUtils.genNextId, true)).as[Int] match {
      case Some(queueCount) => {
        sensorBtn.refresh(queueCount.toString)
        if(queueCount <= 0) (e.getSource.asInstanceOf[Timer]).stop
      }
      case _ => sensorBtn.refresh("?")
    }
  })

  init(heading)

  override def bounds = new Rectangle(x, y, preferredSize.getWidth.toInt, preferredSize.getHeight.toInt)

  private def init(heading: HEADING) = {
    if (heading == LANE.NORTH || heading == LANE.WEST) {
      contents ++= Buffer(red, yellow, green, new TrafficLightPost(heading), new TrafficLightPost(heading))
    } else {
      contents ++= Buffer(new TrafficLightPost(heading), new TrafficLightPost(heading), green, yellow, red)
    }
  }

  /**
   * Switches trafficlight to red in
   * an asynchronous fashion
   */
  def switchToRed: Unit = {
    new javax.swing.SwingWorker[Unit, AnyRef] {
      def doInBackground: Unit = {
        lock synchronized {
          green.turnOff
          yellow.turnOn
          Thread.sleep(500)
          yellow.turnOff
          red.turnOn
          dequeueTimer.stop
        }
      }
    }.execute
  }

  /**
   * Switches trafficlight to green.
   * During the green state the trafficlight dequeues
   * vehicles by calling the sensor decrement method
   * in intervals of 300 ms (default). When switched to red
   * the dequeuing is stopped.
   */
  def switchToGreen: Unit = {
    new javax.swing.SwingWorker[Unit, AnyRef] {
      def doInBackground: Unit = {
        lock synchronized {
          red.turnOff
          green.turnOn
          dequeueTimer.start
        }
      }
    }.execute
  }


  /**
   * Represents the post of the trafficlight
   */
  private class TrafficLightPost(heading: HEADING) extends Panel {

    val stroke = new BasicStroke(4.0f)
    opaque = true

    override def paint(g2: Graphics2D): Unit = {
      g2.setBackground(Color.WHITE)
      g2.setColor(Color.BLACK)
      g2.setStroke(stroke)
      if (heading == LANE.NORTH) {
        g2.draw(new Line2D.Double(size.width / 2, 0, size.width / 2, size.height))
      } else {
        g2.draw(new Line2D.Double(0, size.height / 2, size.width, size.height / 2))
      }
    }

  }

  /**
   * Represents a lamp of a trafficlight
   */
  private class TrafficLightLamp(color: Color) extends Panel {
    private val lampRadius = 20
    private val lampBorder = 3
    private val lampSize = (lampRadius + lampBorder) * 2
    private var active = false

    preferredSize = new Dimension(lampSize, lampSize)

    def turnOn = doBeforeRepaint(active = true)

    def turnOff = doBeforeRepaint(active = false)

    private def doBeforeRepaint(block: => Unit) = {
      block
      repaint
    }

    override def paint(g: Graphics2D): Unit = {
      g.setColor(Color.black)
      g.fillRect(0, 0, preferredSize.getWidth.toInt, preferredSize.getHeight.toInt)
      if (active) {
        g.setColor(color)
      }
      else {
        g.setColor(color.darker.darker.darker)
      }
      g.fillOval(lampBorder, lampBorder, 2 * lampRadius, 2 * lampRadius)
    }

  }

}
