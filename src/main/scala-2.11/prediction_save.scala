import org.apache.spark.mllib.recommendation.{ALS, Rating}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Siori on 2015/12/13.
  */
// http://spark.apache.org/docs/latest/mllib-collaborative-filtering.html
object prediction_save {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Ar Application")
    conf.setMaster("local[*]")
    val sc = new SparkContext(conf)

    // Load and parse the data
    val data = sc.textFile(args(0))
    val ratings = data.map(_.split(',') match { case Array(user, item, rate) =>
      Rating(user.toInt, item.toInt, rate.toDouble)
    })

    // Build the recommendation model using ALS
    val rank = 10
    val numIterations = 10
    val model = ALS.train(ratings, rank, numIterations, 0.01)

    // Evaluate the model on rating data
    val usersProducts = ratings.map { case Rating(user, product, rate) =>
      (user, product)
    }

    model.predict(usersProducts).foreach{ case Rating(user, product, rate) =>
      println(user.toString + ","  + product.toString + "," + rate.toString)
    }

    sc.stop()

  }
}
