package com.ssDemo

import CommandObjects.UserCO
import grails.transaction.Transactional

@Transactional
class UserService {

    def serviceMethod() {

    }

    def bootstrapUser(User user,Role role){
        if (!user.save()) {
            user.errors.allErrors.each {
                println(it)
            }
        } else {
            if (role) {
                UserRole.create(user, role).save()
            } else {
                println("Unable to find Role")
            }
            println("User Created with Role " + role?.authority)
        }
    }

    User createUserWithRole(UserCO userCO,Role role,String path){
        println("Create user")
        User user = new User()
        println("User = "+ user)
        user.properties = userCO.properties
        println("User = "+user)
        if(path){
            println("in if")
            user.photo = true
            println(user?.photo)
        }
        if(user.save()){
            UserRole.create(user,role).save()
            return user
        } else{
            return null
        }
    }

}
