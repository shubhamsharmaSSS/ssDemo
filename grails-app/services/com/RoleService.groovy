package com.ssDemo

import grails.transaction.Transactional

@Transactional
class RoleService {

    def serviceMethod() {

    }

    def bootstrapRole(){
        /* Enums.Role.values().each {
             if(!Role.findByAuthority(it)){
                 println("********* Creating Role for  = "+it)
                 Role role=new Role()
                 role.authority = it
                 if(!role.save()){
                     role.errors.allErrors.each {
                         println(it)
                     }
                 }
                 else{
                     println("Role Created For = "+it)
                 }
             } else{
                 println("********* For Role "+it+" Entry already exists")
             }

         }*/
        if(!Role.findAllWhere(authority: "ROLE_ADMIN")){
            new Role(authority: "ROLE_ADMIN").save()
        } else{
            println("ROLE_ADMIN Already Exists.... No Need To Create")
        }
        if(!Role.findAllWhere(authority: "ROLE_USER")){
            new Role(authority: "ROLE_USER").save()
        } else{
            println("ROLE_USER Already Exists.... No Need To Create")
        }

    }
}
