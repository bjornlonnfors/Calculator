package CalcGUI
import Calc.Calculator
import scala.math._


import java.io.File
import javax.imageio.ImageIO

import scala.swing._
import scala.swing.event.ButtonClicked

/**
  * Created by bjornlonnfors on 23/12/16.
  */

object CalculatorGUI extends SimpleSwingApplication {

  // Things for the calculator

  // Numbers Buttons:

  val num1 = new Button("1")
  val num2 = new Button("2")
  val num3 = new Button("3")
  val num4 = new Button("4")
  val num5 = new Button("5")
  val num6 = new Button("6")
  val num7 = new Button("7")
  val num8 = new Button("8")
  val num9 = new Button("9")
  val num0 = new Button("0")
  val del = new Button("Del")
  val ac = new Button("AC")
  val sum = new Button("+")
  val minus = new Button("-")
  val product = new Button("*")
  val divide = new Button("/")
  val decimal = new Button(".")
  val sqrt1 = new Button("SQRT")
  val potens = new Button("x^2")
  val likamed = new Button("=")

  val numberVector = Vector(num1, num2, num3, del, ac, num4, num5, num6, sum, minus, num7, num8, num9, product, divide, num0, decimal, sqrt1, potens, likamed)

  // Textarea:

  val textPlace = new TextField(50)
    textPlace.editable = false
    textPlace.minimumSize = new Dimension(350,30)


  //Background pic

  val img = ImageIO.read(new File("src/main/resources/BALI.jpeg"))










  def top = new MainFrame {

    title = "Calculator"
    val calculator = new Calculator



    contents = new GridBagPanel{


      override def paintComponent(g: Graphics2D): Unit = {
        super.paintComponent(g)
        g.drawImage(img,-30,0,null)

      }


      val c = new Constraints

      c.gridx = 0
      c.gridy = 0
      c.gridwidth = 50
      layout(textPlace) = c
      c.gridy += 1
      c.gridwidth = 1




      for(a <- numberVector){
        layout(a) = c
        c.gridx += 1
        if(c.gridx == 5){
          c.gridx = 0
          c.gridy += 1

        }
      }

    }




    size = new Dimension(450,300)
    centerOnScreen()
  }

  this.listenTo(num1, num2, num3, del, ac, num4, num5, num6, sum, minus, num7, num8, num9, product, divide, num0, decimal, sqrt1, potens, likamed)

  var stored1: Option[Double] = None
  var middle: Option[String] = None

  this.reactions += {
    case a: ButtonClicked =>



      val pressed = a.source



        if (pressed == num1 || pressed == num2 || pressed == num3 || pressed == num4 || pressed == num5
        || pressed == num6 || pressed == num7 || pressed == num8 || pressed == num9 || pressed == num0) {
        textPlace.text += pressed.text


      } else if (pressed == del) {
        textPlace.text = textPlace.text.dropRight(1)
      } else if (pressed == ac) {
        textPlace.text = ""
        stored1 = None
      } else if (pressed == sqrt1) {
        val number = sqrt(textPlace.text.toDouble)
        textPlace.text = number.toString
      } else if (pressed == potens) {
        textPlace.text = (textPlace.text.toDouble * textPlace.text.toDouble).toString
      } else if (pressed == sum || pressed == product || pressed == minus || pressed == divide) {
          if (textPlace.text.isEmpty) {

          } else {
            middle = Some(pressed.text)
          if (stored1.isEmpty) {
            stored1 = Some(textPlace.text.toDouble)
          }
          textPlace.text = ""
        }

      } else if (pressed == decimal && !textPlace.text.contains('.')) {
        textPlace.text += decimal.text
      } else if (pressed == likamed) {
        if (stored1.isEmpty) {

        } else if (middle.get == "+") {
          textPlace.text = (textPlace.text.toDouble + stored1.get).toString
          middle = None
          stored1 = None
        } else if (middle.get == "/") {
          textPlace.text = (textPlace.text.toDouble / stored1.get).toString
          middle = None
          stored1 = None
        } else if (middle.get == "-") {
          textPlace.text = (textPlace.text.toDouble - stored1.get).toString
          middle = None
          stored1 = None
        } else if (middle.get == "*") {
          textPlace.text = (textPlace.text.toDouble * stored1.get).toString
          middle = None
          stored1 = None
        }
      }
  }






}
