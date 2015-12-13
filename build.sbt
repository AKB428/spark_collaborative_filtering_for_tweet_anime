name := "spark_collaborative_filtering_for_tweet_anime"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  ("org.apache.spark" %% "spark-core" % "1.5.2").
    exclude("org.mortbay.jetty", "servlet-api").
    exclude("com.google.guava","guava").
    exclude("org.apache.hadoop","hadoop-yarn-api").
    exclude("commons-beanutils", "commons-beanutils-core").
    exclude("commons-collections", "commons-collections").
    exclude("commons-logging", "commons-logging").
    exclude("org.spark-project.spark", "unused").
    exclude("com.esotericsoftware.minlog", "minlog").
    exclude("javax.xml.bind", "jsr173_api") //javax.xml.bind/jsr173_api/jars/jsr173_api-1.0.jar
)

libraryDependencies ++= Seq(
  ("org.apache.spark" %% "spark-mllib" % "1.5.2").
    exclude("org.mortbay.jetty", "servlet-api").
    exclude("com.google.guava","guava").
    exclude("org.apache.hadoop","hadoop-yarn-api").
    exclude("commons-beanutils", "commons-beanutils-core").
    exclude("commons-collections", "commons-collections").
    exclude("commons-logging", "commons-logging").
    exclude("org.spark-project.spark", "unused").
    exclude("com.esotericsoftware.minlog", "minlog").
    exclude("javax.xml.bind", "jsr173_api") //javax.xml.bind/jsr173_api/jars/jsr173_api-1.0.jar
)

mainClass in assembly := Some("prediction_save")