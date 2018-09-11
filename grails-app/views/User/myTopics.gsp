<html>
<head>
    <asset:stylesheet href="comman.css" />
    <asset:stylesheet href="bootstrap.min.css" />
    <asset:javascript src="jquery.min.js" />
    <asset:javascript src="bootstrap.min.js" />
    <asset:javascript src="notify.min.js" />
    <title>My Topics</title>
    <meta name="layout" content="linkSharingLayout" />
</head>
<body>
<div class="col-sm-1">
    <g:render template="/templates/leftNav" model="[user:user]" />
</div>
<div class="col-sm-10" style="padding-left: 5%; ">
    <div class="col-sm-12" id="myTopicsDiv" style="margin-top: 5%">
        <g:render template="/templates/commanTopicTemplate" model="[user:user,topicList:myTopicList]" />
    </div>
</div>
</body>
</html>