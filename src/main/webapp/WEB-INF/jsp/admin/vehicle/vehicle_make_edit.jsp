<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<%@include file="../../includes/subnav_admin.jsp"%>

<div class="wrapper">

    <%@include file="vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">

            <c:set var="vmId" value="0" scope="page" />
            <form:form class="form-horizontal" id="vehicleMake" modelAttribute="vehicleMake" action="/admin/vehicle/make/update" method="post">
                <form:hidden path="id"/>
                <form:hidden path="version"/>

                <div class="row">
                    <div class="form-group">
                        <label for="inputVehicleMakeName"  class="col-sm-2 control-label">Vehicle Type</label>
                        <div class="col-sm-8">
                            <form:input id="inputVehicleMakeName" path="vehicleMakeName" type="text" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-11">
                        <hr>
                    </div>
                </div>

                <c:forEach var="vehicleModel" items="${vehicleMake.vehicleModelList}">
                    <form:hidden path="vehicleModelList[${vmId}].id"/>
                    <form:hidden path="vehicleModelList[${vmId}].version"/>
                    <form:hidden path="vehicleModelList[${vmId}].vehicleList"/>
                    <div class="row">
                        <label for="inputVehicleModel${vmId}" class="col-sm-3 control-label">Vehicle Model</label>
                        <div class="input-group col-sm-7">
                            <form:input path="vehicleModelList[${vmId}].vehicleModelName" class="form-control" id="inputVehicleModel${vmId}"/>
                            <span class="input-group-btn">
                                <button name="${vmId}" class="btn btn-default remove_button" type="button">Remove</button>
                            </span>
                        </div>
                    </div>

                    <c:set var="vmId" value="${vmId + 1}" scope="page"/>
                </c:forEach>

                <div class="row">
                    <label for="newVehicleModel" class="col-sm-3 control-label">New Model</label>
                    <div class="input-group col-sm-7">
                        <input class="form-control" name="newVehicleModel" id="newVehicleModel"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-11">
                        <hr>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-3">
                        <form:button type="reset" value="cancle" class="btn btn-default">Cancle</form:button>
                        <form:button type="submit" value="save" class="btn btn-primary">Save</form:button>
                    </div>
                    <div class="col-sm-1">
                        <a href="/admin/vehicle/make/del/${vehicleMake.id}" class="btn btn-warning">Delete</a>
                    </div>
                </div>
            </form:form>
        </div>
        <div class="col-sm-4">
            <%--SUCCESS ALERT--%>
            <div class="${successAlert == null ? 'hidden' : successAlert}" id="successAlert">
                <div class="alert alert-dismissible alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Well done!</strong> You successfully read <a href="#" class="alert-link">this important alert message</a>.
                </div>
            </div>

            <%--WARNING ALERT--%>
            <div class="${warningAlert == null ? 'hidden' : warningAlert}" id="warningAlert">
                <div class="alert alert-dismissible alert-warning">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <h4>Warning!</h4>
                    <p>Best check yo self, you're not looking too good. Nulla vitae elit libero, a pharetra augue. Praesent commodo cursus magna, <a href="#" class="alert-link">vel scelerisque nisl consectetur et</a>.</p>
                </div>
            </div>

            <%--ERROR ALERT--%>
            <div class="${errorAlert == null ? 'hidden' : errorAlert}" id="errorAlert">
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Oh snap!</strong> <a href="#" class="alert-link">Change a few things up</a> and try submitting again.
                </div>
            </div>

        </div>
    </div>

</div>

<script>
    $(document).ready(function() {
        //attach an on click function to the remove buttons
        $('.remove_button').click(function(){
            //log name button and contents of associated text box
            console.log(this.name);
            console.log($('#inputVehicleModel' + this.name).val());

            //clear the value/contents of the textbox
            $('#inputVehicleModel' + this.name).val("");

            //submit the form
            $('#vehicleMake').submit();
        })
    })
</script>

<%@include file="../../includes/footer.jsp"%>