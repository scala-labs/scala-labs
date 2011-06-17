package duse12.gui

import java.awt.event.{ActionEvent, ActionListener}

object GUIUtils {

  /**
   * Implict definition to convert an ActionListener to a function
   */
  implicit def convertActionListenerToFunction[T](func: (ActionEvent) => T) = {
    new ActionListener {
      def actionPerformed(e: ActionEvent) = func(e)
    }
  }
}