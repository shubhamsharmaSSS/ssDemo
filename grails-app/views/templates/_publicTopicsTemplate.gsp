<%@ page import="com.ssDemo.User" %>
<div class="col-sm-10 col-sm-offset-1 ">
    <div class="col-sm-11 circleHead ">
        Recent Topics
    </div>


    <g:if test="${topicList}">
        <g:each in="${topicList}" var="topic">
            <g:if test="${topic?.topicName}">
                <div class="col-sm-11 box-without-top-bottom">
                    <div class="col-sm-3" style="padding: 5px">
                        <g:if test="${topic?.createdBy?.photo}">
                            <g:img dir="images" file="${topic?.createdBy?.UID}.jpg" height="90px" width="90px"/>
                        </g:if>
                        <g:else>
                            <g:img dir="images" file="default.jpg" height="130px" width="110px"/>
                        </g:else>
                    </div>

                    <div class="col-sm-9" style="border-left: 1px solid gray;">
                        <div class="col-sm-12">
                            <label style="font-size: x-large">${topic.topicName}</label>
                        </div>
                        <div class="col-sm-12">
                            <label style="font-size: medium"> @${topic?.createdBy?.username}</label>
                        </div>

                        <div class="col-sm-6 " style="margin-top: 5%;padding-left: 10%">
                            Subscriptions
                        </div>

                        <div class="col-sm-6 " style="margin-top: 5%">
                            ${com.ssDemo.Subscription.countByTopic(topic)}
                        </div>
                    </div>

                </div>
            </g:if>
         </g:each>

    </g:if>

</div>