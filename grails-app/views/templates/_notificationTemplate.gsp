<a data-toggle="dropdown" id="notificationDropDownDiv" type="button"
   class="a  dropdown-toggle ${com.ssDemo.Invite.countByInvitationToAndStatus(user, com.ssDemo.Enums.InviteStatus.INVITED) ? 'notification' : ''}">
    <i class="fas fa-globe">

    </i><sup
        style="color: white;">${com.ssDemo.Invite.countByInvitationToAndStatus(user, com.ssDemo.Enums.InviteStatus.INVITED) ?: ''}</sup>
    <ul id="dropdownList" class="dropdown-menu dropdown-menu-right scrollable-menu" role="menu" aria-labelledby="notificationDropDownDiv">
        <g:render template="/templates/notificationDropDownTemplate" model="[user:user]"/>
    </ul>
</a>

<script>
    $(document).click(function () {
        $("#dropdownList").slideUp("fast");
    });

    $("#notificationDropDownDiv").click(function () {
        $("#dropdownList").slideToggle("fast");
    });
</script>