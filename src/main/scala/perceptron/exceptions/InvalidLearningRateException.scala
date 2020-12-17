package perceptron.exceptions

final case class InvalidLearningRateException(private val message: String = "Invalid Learning Rate",
											  private val cause: Throwable = None.orNull)
  												extends Exception(message, cause)
