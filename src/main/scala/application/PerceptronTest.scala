package application

import perceptron.Perceptron
import perceptron.helper.PerceptronDataTuple

object PerceptronTest {
	val data: List[PerceptronDataTuple] = List(
		new PerceptronDataTuple(Array(2, 4.5), "Triangle"),
		new PerceptronDataTuple(Array(5.5, 5), "Triangle"),
		new PerceptronDataTuple(Array(3.5, 3), "Triangle"),
		new PerceptronDataTuple(Array(2, 1.5), "Triangle"),
		new PerceptronDataTuple(Array(4, 1.5), "Triangle"),
		new PerceptronDataTuple(Array(1.5, -1.5), "Cross"),
		new PerceptronDataTuple(Array(-1, -1.5), "Cross"),
		new PerceptronDataTuple(Array(-2.5, -0.5), "Cross"),
		new PerceptronDataTuple(Array(-1, 1), "Cross"),
		new PerceptronDataTuple(Array(-2, 2.5), "Cross"),
		new PerceptronDataTuple(Array(-1, 3.5), "Cross")
	)

	def main(args: Array[String]): Unit = {
		println("Initializing Perceptron")
		val perceptron: Perceptron = new Perceptron(2, 0.2, "Triangle", "Cross")
		println("Start learning Perceptron")
		for (singleData <- data) {
			perceptron.learnPerceptron(singleData.input, singleData.clazz)
		}
		println("Finished Learning!")
		while (true) {
			println("Input first Number:")
			val number1 = scala.io.StdIn.readDouble()
			println("Input second Number:")
			val number2 = scala.io.StdIn.readDouble()
			val inputVector: Array[Double] = Array(number1, number2)
			println("Predicted Class: " + perceptron.usePerceptron(inputVector))
		}
	}
}
