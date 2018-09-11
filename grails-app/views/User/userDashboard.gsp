<%@ page import="com.ssDemo.Topic" %>
<html>
<head>
    <asset:stylesheet href="comman.css" />
    <asset:stylesheet href="bootstrap.min.css" />
    <asset:javascript src="jquery.min.js" />
    <asset:javascript src="bootstrap.min.js" />
    <asset:javascript src="notify.min.js" />
    <title>User Dashboard</title>
    <meta name="layout" content="linkSharingLayout" />
</head>
<body>
<div class="col-sm-1">
    <g:render template="/templates/leftNav" model="[user:user]" />
</div>
<div class="col-sm-5" style="padding-left: 5%">
    <div class="col-sm-12" id="userProfileDiv" style="margin-top:5%">
        <g:render template="/templates/userProfileTemplate" model="[user: user,noOfSubscriptions: noOfSubscriptions, noOfTopics: noOfTopics]"/>
    </div>

    <div class="col-sm-12" id="subscribedTopicDiv" style="margin-top:5%">
        <g:render template="/templates/subscribedTopicTemplate" model="[user:user, subscribedTopicList: subscribedTopicList]" />
    </div>
</div>

<div class="col-sm-5">
    <div class="col-sm-12" id="UnsubscribedTopicListDiv" style="margin-top:5%">
        <g:render template="/templates/unsubscribedTopicTemplate" model="[user:user, unsubscribedTopicList: unsubscribedTopicList ]" />
    </div>
</div>
</body>
</html>