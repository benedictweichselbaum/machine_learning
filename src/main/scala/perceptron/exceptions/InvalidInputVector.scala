package perceptron.exceptions

final case class InvalidInputVector(private val message: String = "Invalid Learning Rate",
									private val cause: Throwable = None.orNull)
  extends Exception(message, cause)
