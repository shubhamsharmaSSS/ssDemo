<asset:stylesheet src="comman.css"/>
<g:if test="${com.ssDemo.Invite.findByInvitationTo(user)}">
    <g:each in="${com.ssDemo.Invite.findAllByInvitationTo(user,[sort: "status"])}" var="invitation">
        <g:if test="${invitation.status == com.ssDemo.Enums.InviteStatus.INVITED}">
            <li role="presentation">
                <a role="menuitem" tabindex="-1">
                    <div class="h4 col-sm-6">Invite For Topic</div>

                    <div class="col-sm-6" style="text-align: right">${invitation?.getStringDateCreated()}</div>

                    <div class="col-sm-12">
                        <b>${invitation?.invitedBy?.firstName}</b> has invited you to subscribe <b>${invitation?.forTopic?.topicName}</b>.
                    </div>

                    <div class="col-sm-4"><input type="button" class="btn btn-primary" value="Mark Read" onclick="notificationOperation('READ',${invitation?.id})"></div>

                    <div class="col-sm-4"><input type="button" class="btn btn-success" value="Accept" onclick="notificationOperation('ACCEPT',${invitation?.id})"></div>

                    <div class="col-sm-4"><input type="button" class="btn btn-danger" value="Reject" onclick="notificationOperation('REJECT',${invitation?.id})"></div>

                    <div class="col-sm-12 divider"></div>

                </a>
            </li>
        </g:if>
        <g:else>
            <a role="menuitem" tabindex="-1">
                <div class="h4 col-sm-6">Invite For Topic</div>

                <div class="col-sm-6" style="text-align: right">${invitation?.getStringDateCreated()}</div>

                <div class="col-sm-12">
                    <b>${invitation?.invitedBy?.firstName}</b> has invited you to subscribe <b>${invitation?.forTopic?.topicName}</b>.
                </div>

                <g:if test="${invitation.status == com.ssDemo.Enums.InviteStatus.PENDING}">
                    <div class="col-sm-4 col-sm-offset-4"><input type="button" class="btn btn-success" value="Accept" onclick="notificationOperation('ACCEPT',${invitation?.id})">
                    </div>

                    <div class="col-sm-4"><input type="button" class="btn btn-danger" value="Reject" onclick="notificationOperation('REJECT',${invitation?.id})"></div>
                </g:if>
                <div class="col-sm-12 divider"></div>

            </a>
        </g:else>
    </g:each>

</g:if>
<g:else>
    <div class="divider"></div>

    <div class="col-sm-12">
        <a role="menuitem" style="text-decoration: none">No Notification</a>
    </div>
</g:else>

<script>
    function notificationOperation(status,invitationId){
        var status = status;
        var invitationId = invitationId;
        var headerDiv = document.getElementById("notificationTemplateDiv");

        $.ajax({
            url: "${createLink(controller: 'user', action: 'readNotification')}",
            data: {status:status, invitationId: invitationId},
            success: function (data) {
                if(data.status=="200"){
                    $(headerDiv).html(data.template);
                    $.notify(data.msg,"success");
                    $("#notificationDropDownDiv").click();
                    $(document).click();
                } else{
                    $.notify("Error","error");
                    $("#notificationDropDownDiv").click();
                    $(document).click();
                }
            },
            error: function(){
                $.notify("Error","error");
                $("#notificationDropDownDiv").click();
                $(document).click();
            }
        });

    }
</script>
