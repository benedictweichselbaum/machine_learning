package perceptron

import perceptron.exceptions.{InvalidInputVector, InvalidLearningRateException, InvalidPerceptronClassException}

class Perceptron(inputDimension: Int, initLearningRate: Double, val classOne: String, val classTwo: String) {

	private final val defaultWeight = 1.0

	final val dimension: Int = inputDimension
	var learningRate: Double = initLearningRate(initLearningRate)

	val weights: Array[Double] = initWeights(inputDimension)

	def usePerceptron (inputVector: Array[Double]): String = {
		val input: Array[Double] = validateCompleteInputVector(inputVector)
		mapNumberToClassName(calculateClassNumber(input))
	}

	def learnPerceptron (inputVector: Array[Double], clazz: String): Unit = {
		val input: Array[Double] = validateCompleteInputVector(inputVector)
		val desiredClass = mapClassNameToNumber(clazz)
		val gottenClass = calculateClassNumber(input)
		if (gottenClass != desiredClass) {
			updateWeights(input, desiredClass, gottenClass)
		}
	}

	private def calculateClassNumber (fullInputVector: Array[Double]): Int = {
		val product = dotProduct(fullInputVector)
		if (product > 0) 1
		else 0
	}

	private def updateWeights (fullInputVector: Array[Double], desiredClass: Int, gottenClass: Int): Unit = {
		val d = desiredClass - gottenClass
		for (index <- 0 to dimension) {
			weights.update(index, weights.apply(index) + learningRate * d * fullInputVector.apply(index))
		}
	}

	private def dotProduct (fullInputVector: Array[Double]): Double = {
		var product: Double = 0
		for (index <- 0 to dimension) {
			product += (weights.apply(index) * fullInputVector.apply(index))
		}
		product
	}

	private def validateCompleteInputVector (inputVector: Array[Double]): Array[Double] = {
		if (inputVector.length != dimension) throw InvalidInputVector()
		val finalVector: Array[Double] = new Array[Double](dimension + 1)
		finalVector.update(0, 1) // Bias
		for (index <- 1 to dimension) {
			finalVector.update(index, inputVector.apply(index - 1))
		}
		finalVector
	}

	private def mapNumberToClassName (number: Int): String = {
		if (number == 0) classOne
		else if (number == 1) classTwo
		else throw InvalidPerceptronClassException("Invalid class number")
	}

	private def mapClassNameToNumber (clazz: String): Int = {
		if (clazz.equals(classOne)) 0
		else if (clazz.equals(classTwo)) 1
		else throw InvalidPerceptronClassException("Invalid class name")
	}

	private def initWeights (dimension: Int): Array[Double] = {
		val weights: Array[Double] = new Array[Double](dimension + 1)
		for (index <- 0 to dimension) {
			weights.update(index, defaultWeight)
		}
		weights
	}

	private def initLearningRate (learningRate: Double): Double = {
		if (learningRate >= 0 && learningRate <= 1) {
			learningRate
		} else throw InvalidLearningRateException()
	}
}
