<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<%@include file="../../includes/subnav_admin.jsp"%>

<div class="wrapper">

    <%@include file="vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="list-group">
            <c:forEach var="vehicleMake" items="${vehicleMakeList}">
                <div class="col-sm-12 row list-group-item" data-toggle="collapse" data-target="#${vehicleMake.vehicleMakeName}">
                    <div class="col-sm-10">
                        <h5>${vehicleMake.vehicleMakeName}</h5>
                    </div>
                    <div class="col-sm-2 container">
                        <div class="col-lg-6">
                            <h5><a href="/admin/vehicle/make/${vehicleMake.id}">Edit</a></h5>
                        </div>
                        <div class="col-lg-6">
                            <h5><a href="/admin/vehicle/make/del/${vehicleMake.id}">Del</a></h5>
                        </div>
                    </div>
                    <c:choose><c:when test="${vehicleMake.vehicleModelList.size()!=0}">
                </div>
                <div class="col-sm-12 row collapse in" id="${vehicleMake.vehicleMakeName}">
                    <c:forEach var="vehicleModel" items="${vehicleMake.vehicleModelList}">
                        <div class="container-fluid row list-group-item" data-toggle="collapse" data-target="#${vehicleModel.vehicleModelName}">
                            <div class="col-sm-1">
                                <h6>${vehicleModel.id}</h6>
                            </div>
                            <div class="col-sm-9">
                                <h6>${vehicleModel.vehicleModelName}</h6>
                            </div>
                            <div class="col-sm-2 container-fluid">
                                <div class="col-sm-6">
                                    <h6><a href="/admin/vehicle/model/${vehicleModel.id}">Edit</a></h6>
                                </div>
                                <div class="col-sm-6">
                                    <h6><a href="/admin/vehicle/model/del/${vehicleModel.id}">Del </a></h6>
                                </div>
                            </div>
                            <c:choose><c:when test="${vehicleModel.vehicleList.size()!=0}">
                        </div>
                        <div class="container-fluid row">
                            <div class="collapse in" id="${vehicleModel.vehicleModelName}">
                                <table class="table table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th>Plate</th>
                                            <th>Vin</th>
                                            <th>Year</th>
                                            <th>Color</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="vehicle" items="${vehicleModel.vehicleList}">
                                            <tr>
                                                <td>${vehicle.plate}</td>
                                                <td>${vehicle.vin}</td>
                                                <td>${vehicle.year}</td>
                                                <td>${vehicle.color}</td>
                                                <td><a href="/admin/vehicle/${vehicle.id}">Edit</a></td>
                                                <td><a href="/admin/vehicle/del/${vehicle.id}">Delete</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            </c:when><c:otherwise></c:otherwise></c:choose>
                        </div>
                    </c:forEach>
                </c:when><c:otherwise></c:otherwise></c:choose>
                </div>
            </c:forEach>
        </div>
    </div>

</div>

<%@include file="../../includes/footer.jsp"%>