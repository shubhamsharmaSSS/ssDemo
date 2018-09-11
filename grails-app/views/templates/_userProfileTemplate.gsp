<div class="col-sm-12 box" >
    <div class="col-lg-3" style="padding-left: 2px">
        <g:if test="${user?.photo}">
            <g:img dir="images" file="${user?.UID}.jpg" height="130px" width="110px"/>
        </g:if>
        <g:else>
            <g:img dir="images" file="default.jpg" height="130px" width="110px"/>
        </g:else>
    </div>
    <div class="col-lg-9" style="border-left: 1px solid gray ">
        <div class="col-lg-12">
            <label style="font-size: xx-large">${user?.getFullName()}</label>
        </div>
        <div class="col-lg-12">
            <label style="font-size: medium">@${user?.username}</label>
        </div>
        <div class="col-lg-6" style="margin-top: 5%">
            <label style="font-size: medium">Subscription</label>
        </div>
        <div class="col-lg-6" style="margin-top: 5%">
            <label style="font-size: medium">Topics</label>
        </div>
        <div class="col-lg-6" style="padding-left: 10%">
            <label style="font-size: medium;color: deepskyblue">${noOfSubscriptions}</label>
        </div>
        <div class="col-lg-6" style="padding-left: 10%">
            <label style="font-size: medium;color: deepskyblue">${noOfTopics}</label>
        </div>

    </div>
</div>