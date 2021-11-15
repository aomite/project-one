class Account() {
    private var adminUserName: String = "admin"
    private var adminPassword: String = "root"
    private var basicUserName: String = "maria"
    private var basicUserPassword: String = "maria"
    var loginStatus: Boolean = false; 

    def setAdminUserName(newUser: String): Unit = {
        adminUserName = newUser
        println("Username changed successfully.")
    }

    def setAdminPassword(newPw: String): Unit = {
        adminPassword = newPw
        println("Password changed successfully.")
    }

    def getAdminUserName(): String = {
        adminUserName
    }

    def setBasicUserName(newUser: String): Unit = {
        basicUserName = newUser;
        println("Username changed successfully.")
    }
       
    def setBasicUserPassword(newPw: String): Unit = {
        this.basicUserPassword = newPw
        println("Password changed successfully.")
    }

    def getBasicUserName(): String = {
        basicUserName
    }

    def changeUserName(){
        println("Please type in your current Username.")
        var currentUserName = scala.io.StdIn.readLine()
        if(currentUserName.toLowerCase == adminUserName){
            println("Please type your password to authorize Username change.")
            var passwordCheck = scala.io.StdIn.readLine()
            if(passwordCheck.toLowerCase == adminPassword){
                println("Authorization approved. Please type a new Username.")
                var newUserName = scala.io.StdIn.readLine()
                setAdminUserName(newUserName)
            } else {
                println("Password is incorrect. Username change could not be authorized.")
            }
        } else if(currentUserName.toLowerCase == basicUserName){
            println("Please type your password to authorize Username change.")
            var passwordCheck = scala.io.StdIn.readLine()
            if(passwordCheck.toLowerCase == basicUserPassword){
                println("Authorization approved. Please type a new Username.")
                var newUserName = scala.io.StdIn.readLine()
                setBasicUserName(newUserName)
            } else {
                println("Password is incorrect. Username change could not be authorized.")
            }
        } else {
            println("Username not found. Please try again.")
        }
    }

    def changePassword(){
        println("Please type your username.")
        var currentUserName = scala.io.StdIn.readLine()
        if(currentUserName.toLowerCase == adminUserName){ 
            println("Please type your current password.")
            var currentPassword = scala.io.StdIn.readLine()
            if(currentPassword.toLowerCase == adminPassword){
                println("Please type your new password")
                var newPassword: String = scala.io.StdIn.readLine() 
                setAdminPassword(newPassword)
            } else {
                println("Error! Password could not be changed. Please try again.")
            }
        } else if(currentUserName.toLowerCase == basicUserName){   
            println("Please type your current password.")
            var currentPassword = scala.io.StdIn.readLine()
            if(currentPassword.toLowerCase == basicUserPassword){
                println("Please type your new password")
                var newPassword: String = scala.io.StdIn.readLine() 
                setBasicUserPassword(newPassword)
            } else {
                println("Error! Password could not be changed. Please try again.")
            }
        } else {
            println("Username not found. Your password could not be changed. Please try again.")
        }
    }

    def userLogin(){
        if(this.loginStatus == false){
            println("Please type in your current Username.")
            var currentUserName = scala.io.StdIn.readLine()
            if(currentUserName.toLowerCase == adminUserName){
                println("Please type your password.")
                var passwordCheck = scala.io.StdIn.readLine()
                
                if(passwordCheck.toLowerCase == adminPassword){
                    println("Login Successful!")
                    this.loginStatus = true;
                } else {
                    println("Username or Password is incorrect.")
                }
            } else if(currentUserName.toLowerCase == basicUserName){
                println("Please type your password.")
                var passwordCheck = scala.io.StdIn.readLine()
                
                if(passwordCheck.toLowerCase == basicUserPassword){
                    println("Login Successful!")
                    this.loginStatus = true; 
                } else {
                    println("Username or Password is incorrect.")
                }
            } else {
                println("Username not found. Please try again");
            }
        } else if(this.loginStatus == true){
            this.loginStatus = false; 
        }
    }
}