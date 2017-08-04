<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<%@include file="../../includes/subnav_admin.jsp"%>

<div class="wrapper">

    <%@include file="vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">

            <c:set var="vId" value="0" scope="page" />
            <form:form class="form-horizontal" id="vehicleModel" modelAttribute="vehicleModel" action="/admin/vehicle/model/update" method="post">
                <select class="form-control" name="vMIdSel">
                    <c:forEach var="vehicleMake" items="${vehicleMakeList}">
                        <c:choose>
                            <c:when test="${selectedMake.id.equals(vehicleMake.id)}">
                                <option value="${vehicleMake.id}" selected>${vehicleMake.vehicleMakeName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${vehicleMake.id}">${vehicleMake.vehicleMakeName}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <form:hidden path="id"/>
                <form:hidden path="version"/>

                <div class="row">
                    <div class="form-group">
                        <label for="inputVehicleModelName"  class="col-sm-2 control-label">Vehicle Model</label>
                        <div class="col-sm-8">
                            <form:input id="inputVehicleModelName" path="vehicleModelName" type="text" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-11">
                        <hr>
                    </div>
                </div>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th class="col-sm-1">Id</th>
                            <th class="col-sm-2">Plate</th>
                            <th class="col-sm-5">Vin</th>
                            <th class="col-sm-1">Year</th>
                            <th class="col-sm-2">Color</th>
                            <th class="col-sm-1">Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="vehicle" items="${vehicleModel.vehicleList}">
                            <form:hidden path="vehicleList[${vId}].id"/>
                            <form:hidden path="vehicleList[${vId}].version"/>
                            <tr>
                                <td>
                                    <label id="rowId${vehicle.id}" class="control-label">${vehicle.id}</label>
                                </td>
                                <td>
                                    <form:input path="vehicleList[${vId}].plate" class="form-control" id="inputVehiclePlate${vId}"/>
                                </td>
                                <td>
                                    <form:input path="vehicleList[${vId}].vin" class="form-control" id="inputVehicleVin${vId}"/>
                                </td>
                                <td>
                                    <form:input type="number" path="vehicleList[${vId}].year" class="form-control" id="inputVehicleYear${vId}"/>
                                </td>
                                <td>
                                    <form:input path="vehicleList[${vId}].color" class="form-control" id="inputVehicleColor${vId}"/>
                                </td>
                                <td><span class="input-group-btn">
                                        <button name="${vId}" class="btn btn-default remove_button" type="button">Remove</button>
                                    </span>
                                </td>
                            </tr>

                            <c:set var="vId" value="${vId + 1}" scope="page"/>
                        </c:forEach>
                        <tr>
                            <td>
                                <label id="newVehicle" class="control-label">New</label>
                            </td>
                            <td>
                                <input name="inputNewVehicle" class="form-control" id="inputNewVehiclePlate"/>
                            </td>
                            <td>
                                <input name="inputNewVehicle" class="form-control" id="inputNewVehicleVin"/>
                            </td>
                            <td>
                                <input type="number" name="inputNewVehicle" class="form-control" id="inputNewVehicleYear"/>
                            </td>
                            <td>
                                <input name="inputNewVehicle" class="form-control" id="inputNewVehicleColor"/>
                            </td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>

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
                        <a href="/admin/vehicle/model/del/${vehicleModel.id}" class="btn btn-warning">Delete</a>
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
        })
    })
</script>

<%@include file="../../includes/footer.jsp"%>