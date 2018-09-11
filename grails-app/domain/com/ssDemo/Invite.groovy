package com.ssDemo

class Invite {

    User invitedBy
    User invitationTo
    Topic forTopic
    com.ssDemo.Enums.InviteStatus status
    Date dateCreated
    Date lastUpdated
    String uuid = UUID.randomUUID()

    static constraints = {
    }

    static transients = ['stringDateCreated']

    String getStringDateCreated(){
        this.dateCreated.format("MMM/dd hh:mm")
    }
}
