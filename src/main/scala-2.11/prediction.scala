import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel
import org.apache.spark.mllib.recommendation.Rating

/**
  * Created by Siori on 2015/12/13.
  */
// http://spark.apache.org/docs/latest/mllib-collaborative-filtering.html
object prediction {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Ar Application")
    conf.setMaster("local[*]")
    val sc = new SparkContext(conf)

    val model = MatrixFactorizationModel.load(sc, "./model.data")

    val user_id = args(1).toInt
/*
    println(model.predict(user_id,239))
    println(model.predict(user_id,240))
    println(model.predict(user_id,241))
    println(model.predict(user_id,242))
    println(model.predict(user_id,243))
    println(model.predict(user_id,244))
    println(model.predict(user_id,245))
    println(model.predict(user_id,246))
*/
    sc.stop()

  }
}
