package CommandObjects

import com.ssDemo.User

import grails.validation.Validateable

class UserCO implements Validateable{
    String firstName
    String lastName
    String username
    String email
    String password

    static constraints ={

        firstName(blank:false, nullable:false)
        lastName(blank:false, nullable: false)
        username(blank:false, nullable: false, validator:{ val, object ->
            if(User.findByUsername(val)){
                return "CommandObjects.UserCO.userName.unique.error"
            }
        })
        email(blank: false, nullable: false, validator:{ val,object ->
            if(User.findByEmail(val)){
                return "CommandObjects.UserCO.email.unique.error"
            }
        })
        password(blank: false,nullable: false)
    }
}