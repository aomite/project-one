import java.io.PrintWriter
import java.io.File
import java.io.IOException
import java.sql.DriverManager
import java.sql.Statement
import java.sql.Connection
import java.sql.ResultSet

object DataRequest {
    
    def getData(): Unit = {
      val url = "https://newsapi.org/v2/everything?q=%27stock%20exchange%27&language=en&sortBy=relevancy&pageSize=100&apiKey=f5e4a3c438d1424db6b515cff50fddd9"
      val apiResult = scala.io.Source.fromURL(url).mkString
      saveData(apiResult);
    }

    // Stores data in a file on VM
    def saveData(jsonData: String): Unit = {
      val filepath = "/tmp/freshstocknews.json"
      val writer = new PrintWriter(new File(filepath))
      writer.write(jsonData)
      writer.close()
      println(s"File(s) successfully created")
      loadData()
    }

    def loadData(): Unit = {
      var con: java.sql.Connection = null; 
	  var driverName = "org.apache.hive.jdbc.HiveDriver"; 
	  val conStr = "jdbc:hive2://sandbox-hdp.hortonworks.com:10000/default"; 
	  Class.forName(driverName); 
	  con = DriverManager.getConnection(conStr, "", "");
	  val stmt = con.createStatement();
      var res = stmt.execute("LOAD DATA LOCAL INPATH '/tmp/freshstocknews.json' OVERWRITE INTO TABLE stocknews");
    }

}