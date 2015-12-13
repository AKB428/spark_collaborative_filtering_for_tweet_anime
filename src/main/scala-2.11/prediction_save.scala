import org.apache.spark.mllib.recommendation.{ALS, Rating}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Siori on 2015/12/13.
  *
  * run cf_data_file.csv start_user_id end_user_id
  *
  * [cf_data_file.csv]
  * user_id,product_id,rank
  * int,int,double[or int]
  */
// http://spark.apache.org/docs/latest/mllib-collaborative-filtering.html
object prediction_save {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Ar Application")
    //conf.setMaster("local[*]")
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
    val start_user_id = args(1).toInt // 796000
    val end_user_id = args(2).toInt // 876226
    val usersProducts =  (start_user_id to end_user_id).flatMap{ user =>
      // 238->280 2015年 4期のアニメ作品のID
      // http://api.moemoe.tokyo/anime/v1/master/2015
       (238 to 280).map { product =>
         (user, product)
       }
    }

    val predictionAllData = model.predict(sc.parallelize(usersProducts)).map{ case Rating(user, product, rate) =>
      // println(user.toString + ","  + product.toString + "," + rate.toString)
      (user, product, rate)
    }
    predictionAllData.saveAsTextFile("./prediction_rdd")

    sc.stop()

  }
}
