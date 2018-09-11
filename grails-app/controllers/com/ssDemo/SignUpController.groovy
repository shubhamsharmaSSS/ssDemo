package com.ssDemo

import CommandObjects.UserCO
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.apache.tomcat.util.http.fileupload.FileUploadBase
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.multipart.MultipartFile

import javax.servlet.ServletException

@Secured(['permitAll'])
class SignUpController {

    def userService
    def springSecurityService

    def index() {}

    @Secured(['permitAll'])
    def openSignUpForm() {
        UserCO userCO = new UserCO()
        Map result = [:]
        result.template = g.render(template: '/templates/signUpTemplate', model: [userCO: userCO])
        render result as JSON
    }

    def userSignUp(UserCO userCO) {
        println("Controller: signUp, Action: userSignUp")
        if (userCO.validate()) {
            println("CO Validated")
            MultipartFile file = request.getFile('photo')
            println("File = "+file)
            String path=file.originalFilename
            println("Path = "+path)
            Role role= Role.findByAuthority("ROLE_USER")
            println("Role = "+role)
            User user = userService.createUserWithRole(userCO,role,path)
            if (user) {
                if(path){
                    file.transferTo(new File("${grailsApplication.config.uploadFolder}" + user.UID + ".jpg"))
                }
                try {
                    request.login(user.username, user.password);
                } catch (ServletException e) {
                    println("Error While login")
                    println(e.getMessage())
                }
                redirect(controller: 'user', action:'dashboard')
            }
        } else {
            println("Not Validated")
            userCO.errors.allErrors.each {
                println(it)
            }
        }
        List<Topic> topicList = new PublicController().getMostSubscribedTopics()
        render(view: '/login/auth', model: [isSignUp: true, userCO: userCO, topicList: topicList])
    }
}