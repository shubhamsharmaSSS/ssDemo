package com.ssDemo

import CommandObjects.TopicCO
import com.ssDemo.User
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class UserController {

    def springSecurityService
    def topicService

    def index() {}

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def dashboard() {
        User user = springSecurityService.currentUser as User
        int noOfSubscriptions = Subscription.countByUser(user)
        int noOfTopics = Topic.countByCreatedBy(user)
        List<Topic> subscribedTopicList = topicService.getSubscribedTopicsList(user)
        List<Topic> unsubscribedTopicList = Topic.getPublicTopicList() - subscribedTopicList
        render(view: '/User/userDashboard', model: [user: user, noOfSubscriptions: noOfSubscriptions, noOfTopics: noOfTopics, subscribedTopicList: subscribedTopicList, unsubscribedTopicList: unsubscribedTopicList])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def createTopic(TopicCO topicCO) {
        println("Controller: User, Action: createTopic");
        topicCO.createdBy = User.findById(params.createdBy)
        topicCO.visibility = com.ssDemo.Enums.Visibility.valueOf(params.visibility)
        Topic topic = topicService.createTopic(topicCO)
        redirect(action: 'myTopic')

    }

    def myTopic() {
        User user = springSecurityService.currentUser as User
        List<Topic> myTopicList = Topic.findAllByCreatedBy(user)
        render(view: '/User/myTopics', model: [user: user, myTopicList: myTopicList])
    }

    def allTopic() {
        User user = springSecurityService.currentUser as User
        List<Topic> publicTopicList = Topic.getPublicTopicList()
        List<Topic> subscribedTopicList = topicService.getSubscribedTopicsList(user)
        List<Topic> privateTopicList = subscribedTopicList - publicTopicList
        List<Topic> allTopicList = publicTopicList + privateTopicList
        render(view: '/User/allTopics', model: [user: user, allTopicList: allTopicList, showStatus: true])

    }

    def profile(){
        println("Controller: User, Action: profile")
        User user = springSecurityService.currentUser as User
        render(view: '/User/profile', model: [user: user])
    }

    def updateProfile(){
        println("Controller: User, Action: updateProfile")
        User user = params?.userId ? User.findById(params.userId): springSecurityService.currentUser as User
        Map result=[:]
        Long userId = user.id
        String firstName = params.firstName
        String lastName = params.lastName
        User.executeUpdate("update User set firstName = ?, lastName = ? where id = ?",[firstName,lastName,userId])
        println("Executed Succesfully")
        result.template = g.render(template: '/templates/profileTemplate', model: [user:User.findById(userId)])
        result.status ="200"
        println("Rendered")
        render result as JSON
    }

    def openShareTopicModal(){
        println("Controller: User, Action: openShareTopicModal")
        User user = params?.userId ? User.findById(params?.userId) : springSecurityService.currentUser as User
        Map result = [:]
        Topic topic = Topic.findById(params.topicId)
        List<User> userList = User.list() - Subscription.findAllByTopic(topic).user - Invite.findByStatus(com.ssDemo.Enums.InviteStatus.REJECTED)?.invitationTo ?: []
        result.template = g.render(template: '/templates/shareTopicModalTemplate', model: [user:user,topic:topic,userList:userList])
        result.status = "200"
        render result as JSON
    }

    def readNotification(){
        println("Controller: User, Action: readNotification")
        User user = springSecurityService.currentUser as User
        Invite invite = Invite.findById(params?.invitationId)
        String status = params?.status
        Map result=[:]
        if(status == "READ"){
            invite.status = com.ssDemo.Enums.InviteStatus.PENDING
            invite.save(flush:true)
            result.msg = "Invitation Read"
            result.status = "200"
        } else if(status == "ACCEPT"){
            Subscription subscription = topicService.subscribeToTopic(invite.invitationTo,invite.forTopic,com.ssDemo.Enums.Seriousness.SERIOUS)
            if(subscription){
                invite.status = com.ssDemo.Enums.InviteStatus.ACCEPTED
                invite.save(flush:true)
                result.msg = "Invitation Accepted"
                result.status = "200"
            } else{
                result.status = "500"
            }

        } else if(status == "REJECT"){
            invite.status = com.ssDemo.Enums.InviteStatus.REJECTED
            invite.save(flush:true)
            result.msg = "Invitation Rejected"
            result.status = "200"
        }
        result.template = g.render(template: '/templates/notificationTemplate', model: [user: user])
        render result as JSON
    }
}
