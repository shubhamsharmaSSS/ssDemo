package com.ssDemo

class Subscription {

    User user
    Topic topic
    com.ssDemo.Enums.Seriousness seriousness

    static constraints = {
        user(blank: false)
        topic(blank: false)
        seriousness(blank: false)
    }
}
