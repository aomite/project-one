// Java Libraries
import java.io.IOException
import java.sql.DriverManager
import java.sql.Statement
import java.sql.Connection

// Scala Libraries
import scala.util.Try
import io.StdIn._

// My Libraries

object MainProgram {
  def main(args: Array[String]){
    try{
	    // User Experience -- Start
	    val mainAccount = new Account;
		println("WELCOME! This app provides information on recent stock news trends.");
		mainMenu(mainAccount); 

	} catch {
	    case ex: Throwable => {
	      ex.printStackTrace(); 
		  throw new Exception(s"{ex.getMessage}");
	    }
	}// finally {
	//     try{ 
	// 	    if(con != null) {
	// 	      con.close();
	// 	    }
	// 	  } catch {
	// 	      case ex: Throwable => {
	// 		      ex.printStackTrace();
	// 		      throw new Exception(s"{ex.getMessage}");
	// 		    }
	// 	  }
	//   }
  }

  def mainMenu(mainAccount: Account){
	try {
		println("Main Menu:");
		println("1) Most popular sites");
		println("2) Least popular sites");
		println("3) Most recent article titles");
		println("4) Most popular posting times (by hour)");
		println("5) Least popular posting times (by hour)");
		println("6) Number of total results");
		println("7) Settings");
		println("8) Exit App");

		var userResponse: Int = readInt();
		if(userResponse <= 0 || userResponse >= 9){
		  println("Incorrect number detected! Please enter a number between 1 and 8.");
		  mainMenu(mainAccount);
		} else if(userResponse == 1){
			var con: java.sql.Connection = null; 
			var driverName = "org.apache.hive.jdbc.HiveDriver"; 
			val conStr = "jdbc:hive2://sandbox-hdp.hortonworks.com:10000/default"; 
			Class.forName(driverName); 
			con = DriverManager.getConnection(conStr, "", "");
			val stmt = con.createStatement();

			var res = stmt.executeQuery("SELECT SITE, COUNT(*) AS Publications FROM stocknews LATERAL VIEW EXPLODE(SPLIT(get_json_object(json,'$.articles.source.name'),',')) VirTable AS SITE GROUP BY SITE ORDER BY Publications DESC LIMIT 5");
			while(res.next()){
			println(s"${res.getString(1)}, ${res.getInt(2)}"); 
			}
			mainMenu(mainAccount);
		} else if(userResponse == 2){
			var con: java.sql.Connection = null; 
			var driverName = "org.apache.hive.jdbc.HiveDriver"; 
			val conStr = "jdbc:hive2://sandbox-hdp.hortonworks.com:10000/default"; 
			Class.forName(driverName); 
			con = DriverManager.getConnection(conStr, "", "");
			val stmt = con.createStatement();

			var res = stmt.executeQuery("SELECT SITE, COUNT(*) AS Publications FROM stocknews LATERAL VIEW EXPLODE(SPLIT(get_json_object(json,'$.articles.source.name'), ',')) VirTable AS SITE GROUP BY SITE ORDER BY Publications ASC LIMIT 5");
			while(res.next()){
			println(s"${res.getString(1)}, ${res.getInt(2)}"); 
			}
			mainMenu(mainAccount);
		} else if(userResponse == 3){
			var con: java.sql.Connection = null; 
			var driverName = "org.apache.hive.jdbc.HiveDriver"; 
			val conStr = "jdbc:hive2://sandbox-hdp.hortonworks.com:10000/default"; 
			Class.forName(driverName); 
			con = DriverManager.getConnection(conStr, "", "");
			val stmt = con.createStatement();

			var res = stmt.executeQuery("SELECT Title FROM stocknews LATERAL VIEW EXPLODE(SPLIT(get_json_object(json,'$.articles.title'), ',')) VirTable AS Title LIMIT 10");
			while(res.next()){
			println(s"${res.getString(1)}"); 
			}
			mainMenu(mainAccount);
		} else if(userResponse == 4){
			var con: java.sql.Connection = null; 
			var driverName = "org.apache.hive.jdbc.HiveDriver"; 
			val conStr = "jdbc:hive2://sandbox-hdp.hortonworks.com:10000/default"; 
			Class.forName(driverName); 
			con = DriverManager.getConnection(conStr, "", "");
			val stmt = con.createStatement();

			var res = stmt.executeQuery("SELECT HOUR(SPLIT(TIME, 'T')[1]), COUNT(*) AS Articles_Published FROM stocknews LATERAL VIEW EXPLODE(SPLIT(get_json_object(json,'$.articles.publishedAt'), ',')) VirTable AS TIME GROUP BY (HOUR(SPLIT(TIME, 'T')[1])) ORDER BY Articles_Published DESC LIMIT 3");
			while(res.next()){
			println(s"${res.getString(1)}, ${res.getString(2)}");
			}
			mainMenu(mainAccount);
		} else if(userResponse == 5){
			var con: java.sql.Connection = null; 
			var driverName = "org.apache.hive.jdbc.HiveDriver"; 
			val conStr = "jdbc:hive2://sandbox-hdp.hortonworks.com:10000/default"; 
			Class.forName(driverName); 
			con = DriverManager.getConnection(conStr, "", "");
			val stmt = con.createStatement();

			var res = stmt.executeQuery("SELECT HOUR(SPLIT(TIME, 'T')[1]), COUNT(*) AS Articles_Published FROM stocknews LATERAL VIEW EXPLODE(SPLIT(get_json_object(json,'$.articles.publishedAt'), ',')) VirTable AS TIME GROUP BY (HOUR(SPLIT(TIME, 'T')[1])) ORDER BY Articles_Published ASC LIMIT 3");
			while(res.next()){
			println(s"${res.getInt(1)}, ${res.getInt(2)}");
			}
			mainMenu(mainAccount);
		} else if(userResponse == 6){
			var con: java.sql.Connection = null; 
			var driverName = "org.apache.hive.jdbc.HiveDriver"; 
			val conStr = "jdbc:hive2://sandbox-hdp.hortonworks.com:10000/default"; 
			Class.forName(driverName); 
			con = DriverManager.getConnection(conStr, "", "");
			val stmt = con.createStatement();

			var res = stmt.executeQuery("SELECT CONCAT('Retrieved 100 responses out of: ', get_json_object(json,'$.totalResults')) FROM stocknews");
			while(res.next()){
			println(s"${res.getString(1)}");
			}
			mainMenu(mainAccount);
		} else if(userResponse == 7){
			settingsMenu(mainAccount);
		} else if(userResponse == 8){
			println("Exiting App...");
			println("Goodbye."); 
		}
	} catch {
		case ex: Throwable => {
			println("Incorrect input detected. Please enter a number between 1 and 8.");
			mainMenu(mainAccount);
		}
	}
  }

  def settingsMenu(mainAccount: Account){
    try {
      println("Menu: Settings");
      if(mainAccount.loginStatus == false) println("1) Log-in") else println("1) Log-out");
      println("2) Change Username");
      println("3) Change Password");
      println("4) Load new information");
	  println("5) Return to Main Menu"); 

      var userResponseTwo: Int = readInt();
      if(userResponseTwo <= 0 || userResponseTwo >= 6){
		  println("Incorrect input. Please enter a number between 1 to 6");
		  settingsMenu(mainAccount); 
	  } else if(userResponseTwo == 1){
          mainAccount.userLogin();
		  settingsMenu(mainAccount);
      } else if (userResponseTwo == 2){
          mainAccount.changeUserName();
		  settingsMenu(mainAccount);
      } else if(userResponseTwo == 3){
          mainAccount.changePassword();
		  settingsMenu(mainAccount);
      } else if(userResponseTwo == 4){
          DataRequest.getData();
		  settingsMenu(mainAccount); 
      } else if(userResponseTwo == 5){
          mainMenu(mainAccount);
      }
    } catch {
		case ex: Throwable => {
          println("Incorrect input detected. Please enter a number between 1 and 5.");
          settingsMenu(mainAccount);
		} 
      }
    }
}