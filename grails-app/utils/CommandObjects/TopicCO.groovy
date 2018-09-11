package CommandObjects

import com.ssDemo.Enums
import com.ssDemo.User
import grails.validation.Validateable

class TopicCO implements Validateable{

    String topicName
    Enums.Visibility visibility
    User createdBy

    static constraints = {
        topicName(blank:false, nullable: false)
        visibility(blank:false, nullable: false)
        createdBy(blank: false, nullable: false)
    }

}