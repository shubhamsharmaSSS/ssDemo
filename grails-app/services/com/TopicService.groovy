package com.ssDemo
import CommandObjects.TopicCO
import com.ssDemo.Enums.Seriousness
import grails.transaction.Transactional

@Transactional
class TopicService {

    def serviceMethod() {

    }

    def bootstrapTopic(TopicCO topicCO){
        Topic topic= new Topic(topicCO)
        if(!topic.save()){
            topic.errors.allErrors.each {
                println(it)
            }
        } else{
            Subscription subscription = new Subscription()
            subscription.topic = topic
            subscription.user = topic.createdBy
            subscription.seriousness = com.ssDemo.Enums.Seriousness.CASUAL
            subscription.save()
            println("Topic "+topic.topicName+" Created By "+topic.createdBy)
        }
    }

    List<Topic> getSubscribedTopicsList(User user){
        List<Topic> subscribedTopicList = Subscription.createCriteria().list() {
            eq('user',user)
            projections{
                property('topic')
            }
        }
        return subscribedTopicList
    }

    Topic createTopic(TopicCO topicCO){
        Topic topic = new Topic()
        topic.properties = topicCO.properties
        if(topic.save()){
            Subscription subscription = new Subscription()
            subscription.user = topic.createdBy
            subscription.topic = topic
            subscription.seriousness = com.ssDemo.Enums.Seriousness.VERY_SERIOUS
            subscription.save()
            return topic
        } else{
            return null
        }

    }

    Subscription subscribeToTopic(User user,Topic topic,Seriousness seriousness){
        Subscription subscription = new Subscription()
        subscription.topic = topic
        subscription.user = user
        subscription.seriousness = seriousness
        if(subscription.save()){
            return subscription
        } else{
            return null
        }
    }

    Invite inviteUser(User user,User targetUser,Topic topic){
        Invite invite = new Invite()
        invite.invitedBy = user
        invite.invitationTo = targetUser
        invite.forTopic = topic
        invite.status = com.ssDemo.Enums.InviteStatus.INVITED
        if(invite.save()){
            return invite
        } else{
            return null
        }
    }
}
