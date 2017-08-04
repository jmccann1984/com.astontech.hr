<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<%@include file="../../includes/subnav_admin.jsp"%>

<div class="wrapper">

    <%@include file="vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">

            <c:set var="vId" value="0" scope="page" />
            <form:form class="form-horizontal" id="vehicle" modelAttribute="vehicle" action="/admin/vehicle/${doThis}" method="post">

                <form:hidden path="id"/>
                <form:hidden path="version"/>

                <div class="row">
                    <div class="form-group">
                        <label for="inputVehiclePlate"  class="col-sm-1 control-label">Plate</label>
                        <div class="col-sm-11">
                            <form:input id="inputVehiclePlate" path="plate" type="text" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label for="inputVehicleVin"  class="col-sm-1 control-label">VIN</label>
                        <div class="col-sm-11">
                            <form:input id="inputVehicleVin" path="vin" type="text" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label for="inputVehicleYear"  class="col-sm-1 control-label">Year</label>
                        <div class="col-sm-11">
                            <form:input id="inputVehicleYear" path="year" type="number" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label for="inputVehicleColor"  class="col-sm-1 control-label">Color</label>
                        <div class="col-sm-11">
                            <form:input id="inputVehicleColor" path="color" type="text" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label for="seleMake" class="col-sm-1 control-label">Make</label>
                        <div class="col-sm-11">
                            <select id="seleMake" class="form-control model_hide">
                                <option class="defaultSel visible" value="0">{Select One}</option>
                                <c:forEach var="vehicleMake" items="${vehicleMakeList}">
                                    <c:choose>
                                        <c:when test="${selectedMake.id.equals(vehicleMake.id)}">
                                            <option  value="${vehicleMake.id}" selected>${vehicleMake.vehicleMakeName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option  value="${vehicleMake.id}">${vehicleMake.vehicleMakeName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label for="selModel" class="col-sm-1 control-label">Model</label>
                        <div class="col-sm-11">
                            <select id="selModel" class="form-control" name="vMIdSel">
                                <option class="defaultSel visible" value="0">{Select One}</option>
                                <c:forEach var="vehicleModel" items="${vehicleModelList}">
                                    <c:choose>
                                        <c:when test="${selectedModel.id.equals(vehicleModel.id)}">
                                            <option  class="${vehicleModel.vehicleMake.id}" value="${vehicleModel.id}" selected>${vehicleModel.vehicleModelName}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option class="${vehicleModel.vehicleMake.id}" value="${vehicleModel.id}">${vehicleModel.vehicleModelName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
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
                    <a href="/admin/vehicle/del/${vehicle.id}" class="btn btn-warning">Delete</a>
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
            console.log($('#inputVehicleVin' + this.name).val());

            //clear the value/contents of the textbox
            $('#inputVehicleVin' + this.name).val("");

            //submit the form
            $('#vehicleModel').submit();
        });

        //JQuery model limiter by VehicleMake
        //  Hide all VehicleModel options
        $('#selModel > option').hide();
        //  Show default option and all options containing the class from the vehicleMakeId.
        $('.defaultSel').show();
        $('#selModel > option.' + $('.model_hide').val()).show();

        //When a new vehicleMake is selected, do the same thing as above.
        $('.model_hide').change(function(){
            $('#selModel > option').hide();
            $('.defaultSel').show();
            $('#selModel > option.' + this.value).show();
        });
    })
</script>

<%@include file="../../includes/footer.jsp"%>