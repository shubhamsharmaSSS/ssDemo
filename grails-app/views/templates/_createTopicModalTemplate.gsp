<div class="modal fade" id="createTopicModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">



            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Create Topic</h4>
            </div>

            <div class="modal-body">
                <form action="${createLink(controller:'user', action: 'createTopic')}" id="createTopicForm">
                    <div class=" form-group">
                        <label class="control-label" for="topicName">Topic Name</label>
                        <div id="topicNameError">
                            <span style="color:red"></span>
                        </div>
                        <input type="text" name="topicName" id="topicName" class="form-control"
                               placeholder="Enter Topic Name" value="${topicCO.topicName}">
                    </div>

                    <div class=" form-group">
                        <label class="control-label" for="visibility">Visibility</label>
                        <div id="visibilityError">
                            <span style="color:red"></span>
                        </div>
                        <g:select from="${com.ssDemo.Enums.Visibility}" name="visibility" id="visibility"
                                  noSelection="['': 'Select Visibility']" class="form-control" value="${topicCO.visibility}"/>
                    </div>

                    <input type="hidden" name="createdBy" id="createdBy" value="${user.id}" />

                    <div class="form-group col-sm-offset-9" >
                            <input type="button" name="topicSubmitBtn" id="topicSubmitBtn" value="Create" class="btn btn-default" onclick="createTopic()">
                            <input type="reset" value="reset" class="btn btn-default">
                    </div>

                </form>
            </div>


            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>



        </div>
    </div>
</div>

<script>
    function createTopic() {
        var topicName = document.getElementById("topicName").value;
        var visibility = document.getElementById("visibility").value;
        if(validateValues(topicName,visibility)){
            var form = document.getElementById("createTopicForm");
            $(form).submit();
        }
    }

    function validateValues(topicName,visibility) {
        var topicName = topicName;
        var visibility = visibility;
        alert(topicName);
        alert(visibility);
        var flag = true;
        if(!topicName){
            $("#topicNameError").children().html("Please Enter Topic Name");
            flag = false;
        } else{
            $("#topicNameError").children().html(" ");
        }
        if(!visibility){
            $("#visibilityError").children().html("Please Enter Visibility");
            flag = false;
        } else{
            $("#visibilityError").children().html(" ");
        }
        return flag;
    }
</script>