<html>
<head>
    <asset:stylesheet href="comman.css"/>
    <asset:stylesheet href="bootstrap.min.css"/>
    <asset:javascript src="jquery.min.js"/>
    <asset:javascript src="bootstrap.min.js"/>
    <asset:javascript src="notify.min.js"/>
    <title>Profile : ${user.fullName}</title>
    <meta name="layout" content="linkSharingLayout"/>
</head>

<body>
<div class="col-sm-1">
    <g:render template="/templates/leftNav" model="[user: user]"/>
</div>

<div class="col-sm-10" style="padding-left: 5%; ">
    <div class="col-sm-12" id="profileDiv" style="margin-top: 5%">
        <g:render template="/templates/profileTemplate"
                  model="[user: user]"/>
    </div>
</div>
</body>
</html>