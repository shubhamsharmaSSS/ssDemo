import com.ssDemo.Role
import com.ssDemo.User
import com.ssDemo.UserRole
import com.ssDemo.Enums
import com.ssDemo.Topic
import CommandObjects.TopicCO
import CommandObjects.UserCO

class BootStrap {

    def userService
    def roleService
    def topicService

    def init = { servletContext ->
        createRoles()
        createUsers()
        createTopics()
    }
    def destroy = {
    }

    def createRoles(){
        roleService.bootstrapRole()
    }

    def createUsers(){
        if(!User.findAllWhere(username:"shubham")){
            println("Creating User : shubham")
            UserCO userCO=new UserCO(username: "shubham", email: "Shubham@rxlogix.com", password: "shubhamPassword", firstName: "Shubham", lastName: "Sharma")
            User user=new User()
            user.properties = userCO.properties
            Role role= Role.findByAuthority("ROLE_ADMIN")
            userService.bootstrapUser(user,role)
        } else{
            println("IAmShubham Found")
        }
        if(!User.findAllWhere(username: "satyam")){
            println("Creating User : satyam")
            UserCO userCO=new UserCO(username: "satyam", email: "Satyam@rxlogix.com", password: "satyamPassword", firstName: "Satyam", lastName: "Maheswari")
            User user=new User()
            user.properties = userCO.properties
            Role role= Role.findByAuthority("ROLE_USER")
            userService.bootstrapUser(user,role)
        } else{
            println("IAmSatyam Found")
        }
        if(!User.findAllWhere(username: "kapil")){
            println("Creating User : kapil")
            UserCO userCO=new UserCO(username: "kapil", email: "Kapil@rxlogix.com", password: "kapilPassword", firstName: "Kapil", lastName: "Pawar")
            User user=new User()
            user.properties = userCO.properties
            Role role= Role.findByAuthority("ROLE_USER")
            userService.bootstrapUser(user,role)
        } else{
            println("IAmKapil Found")
        }

    }

    def createTopics(){
        if(!Topic.findAllWhere(topicName: "Core Java")){
            println("Creating Topic : Core Java")
            User user=User.findByUsername("satyam")
            TopicCO topicCO=new TopicCO(topicName: "Core Java", visibility: Enums.Visibility.PUBLIC, createdBy: user)
            topicService.bootstrapTopic(topicCO)
        } else{
            println("Topic : Core Java Already Exists.... No Need To Create")
        }

        if(!Topic.findAllWhere(topicName: "Machine Learning")){
            println("Creating Topic : Machine Learning")
            User user=User.findByUsername("kapil")
            TopicCO topicCO=new TopicCO(topicName: "Machine Learning", visibility: Enums.Visibility.PUBLIC, createdBy: user)
            topicService.bootstrapTopic(topicCO)
        } else{
            println("Topic : Machine Learing Already Exists.... No Need To Create")
        }
    }
}
