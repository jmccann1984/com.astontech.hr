<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<%@include file="../../includes/subnav_admin.jsp"%>

<div class="wrapper">

    <%@include file="vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">
            <form:form cssClass="form-horizontal" modelAttribute="VehicleMakeVO" action="/admin/vehicle/make/add" method="post">
                <legend>Element Management</legend>
                <div class="form-group">
                    <label for="inputNewVehicleMakeName" class="col-lg-2 control-label">Element Type</label>
                    <div class="col-lg-10">
                        <form:input path="newVehicleMakeName" type="text" class="form-control" id="inputNewVehicleMakeName" placeholder="Vehicle Make"></form:input>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputNewVehicleModesl" class="col-lg-2 control-label">Vehicle Models</label>
                    <div class="col-lg-10">
                        <form:textarea path="newVehicleModels" rows="3" class="form-control" id="inputNewVehicleModesl" placeholder=""></form:textarea>
                        <span class="help-block">Enter each new Element on a new Line.</span>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <form:button type="reset" value="cancel" class="btn btn-default">Cancel</form:button>
                        <form:button type="submit" value="save" class="btn btn-primary">Save</form:button>
                    </div>
                </div>
            </form:form>
        </div>
        <div class="col-sm-4">
            <%--SUCCESS ALERT--%>
            <div class="${successAlert == null ? 'hidden' : successAlert}" id="successAlert">
                <div class="alert alert-dismissible alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Element added successfully to the database!</strong></a>.
                </div>
            </div>

            <%--WARNING ALERT--%>
            <div class="${warningAlert == null ? 'hidden' : warningAlert}" id="warningAlert">
                <div class="alert alert-dismissible alert-warning">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <h4>Be Advised!</h4>
                    <p>All fields required.  Please enter an Element Type and associated Element sepearted by a new line.</p>
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
    $(document).ready(function(){
        $('#errorAlert, #warningAlert, #successAlert').delay(8000).fadeOut(2000);
    });
</script>

<%@include file="../../includes/footer.jsp"%>