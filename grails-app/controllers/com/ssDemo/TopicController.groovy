package com.ssDemo

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER'])
class TopicController {

    def springSecurityService
    def topicService

    def index() {}

    def unsubscribeAjax() {
        println("Inside Unsubscribe Ajax Method")
        Map result = [:]
        User user = params.userId ? User.findById(params.userId) : springSecurityService.currentUser as User
        Topic topic = Topic.findById(params.topicId)
        Subscription subscription = Subscription.findByTopicAndUser(topic, user)
        println("Subscription " + subscription + " is Deleting")
        try {
            subscription.delete(flush:true)
            List<Topic> subscribedTopicList = topicService.getSubscribedTopicsList(user)
            List<Topic> unsubscribedTopicList = Topic.getPublicTopicList()-subscribedTopicList
            result.status = "200"
            result.subscribedTemplate = g.render(template: "/templates/subscribedTopicTemplate", model: [user: user, subscribedTopicList: subscribedTopicList])
            result.unsubscribedTemplate = g.render(template: "/templates/unsubscribedTopicTemplate", model: [user: user, unsubscribedTopicList: unsubscribedTopicList])
        }
        catch (Exception e) {
            result.status = "500"

        }
        render result as JSON
    }

    def subscribeAjax() {
        println("Inside Subscribe Ajax Method")
        Map result = [:]
        User user = params.userId ? User.findById(params.userId) : springSecurityService.currentUser as User
        com.ssDemo.Enums.Seriousness seriousness = com.ssDemo.Enums.Seriousness.valueOf(params.seriousness)
        Topic topic = Topic.findById(params.topicId)
        println("Getting Subscribed ")
        Subscription subscription = topicService.subscribeToTopic(user,topic,seriousness)
        if (subscription) {
            List<Topic> subscribedTopicList = topicService.getSubscribedTopicsList(user)
            List<Topic> unsubscribedTopicList = Topic.getPublicTopicList() - subscribedTopicList
            result.status = "200"
            result.subscribedTemplate = g.render(template: "/templates/subscribedTopicTemplate", model:[user:user, subscribedTopicList: subscribedTopicList])
            result.unsubscribedTemplate = g.render(template: "/templates/unsubscribedTopicTemplate", model: [user: user, unsubscribedTopicList: unsubscribedTopicList])
        } else {
            result.status = "500"
        }
        render result as JSON
    }

    def changeSeriousnessAjax() {
        println("Controller: Topic, Action: changeSeriousnessAjax")
        Map result = [:]
        User user= params.userId ? User.findById(params.userId) : springSecurityService.currentUser as User
        Topic topic = Topic.findById(params.topicId)
        com.ssDemo.Enums.Seriousness newSeriousness = com.ssDemo.Enums.Seriousness.valueOf(params.seriousness)
        Subscription subscription = Subscription.findByUserAndTopic(user,topic)
        com.ssDemo.Enums.Seriousness oldSeriousness = subscription.seriousness
        result.value = oldSeriousness
        if(oldSeriousness != newSeriousness){
            subscription.seriousness = newSeriousness
            subscription.save()
            result.values = subscription.seriousness
            result.status = "200"
        } else{
            result.value = subscription.seriousness
            result.status = "500"
        }
        render result as JSON
    }

    def subscribeFromAllTopicsAjax() {
        println("Controller: Topic, Action: subscribeFromAllTopicsAjax")
        Map result = [:]
        User user = params.userId ? User.findById(params.userId) : springSecurityService.currentUser as User
        com.ssDemo.Enums.Seriousness seriousness = com.ssDemo.Enums.Seriousness.valueOf(params?.seriousness)
        Topic topic = Topic.findById(params.topicId)
        println("Getting Subscribed ")
        Subscription subscription = topicService.subscribeToTopic(user,topic,seriousness)
        if (subscription) {
            List<Topic> publicTopicList = Topic.getPublicTopicList()
            List<Topic> subscribedTopicList = topicService.getSubscribedTopicsList(user)
            List<Topic> privateTopicList = subscribedTopicList - publicTopicList
            List<Topic> allTopicList = publicTopicList + privateTopicList
            result.status = "200"
            result.template = g.render(template: "/templates/commanTopicTemplate", model:[user:user,topicList:allTopicList])
        } else {
            result.status = "500"
        }
        render result as JSON
    }

    def inviteUser(){
        User user = springSecurityService.currentUser as User
        Map result = [:]
        Topic topic = Topic.findById(params?.topicId)
        User targetUser = User.findById(params?.userId)
        Invite invite = topicService.inviteUser(user,targetUser, topic)
        if(invite){
            result.status="200"
        }else{
            result.status="500"
        }
        render result as JSON
    }

}
