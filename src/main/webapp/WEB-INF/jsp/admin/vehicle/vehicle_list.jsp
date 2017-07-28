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
                        <h4>${vehicleMake.vehicleMakeName}</h4>
                    </div>
                    <div class="col-sm-2 container">
                        <div class="col-lg-6">
                            <a href="/admin/vehicle/make/${vehicleMake.id}" class="btn">Edit</a>
                        </div>
                        <div class="col-lg-6">
                            <a href="/admin/vehicle/make/del/${vehicleMake.id}" class="btn">Del </a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12 row collapse" id="${vehicleMake.vehicleMakeName}">
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
                                    <a href="#" class="btn">Edit</a>
                                </div>
                                <div class="col-sm-6">
                                    <a href="/admin/vehicle/make${vehicleMake.id}/model/del/${vehicleModel.id}" class="btn">Del </a>
                                </div>
                            </div>
                        </div>
                        <div class="container-fluid row">
                            <div class="collapse" id="${vehicleModel.vehicleModelName}">
                                <table class="table table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th>Plate</th>
                                            <th>Vin</th>
                                            <th>Year</th>
                                            <th>Color</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="vehicle" items="${vehicleModel.vehicleList}">
                                            <tr>
                                                <td>${vehicle.plate}</td>
                                                <td>${vehicle.vin}</td>
                                                <td>${vehicle.year}</td>
                                                <td>${vehicle.color}</td>
                                                <td>Edit</td>
                                                <td>Delete</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
        </div>
    </div>

</div>

<%@include file="../../includes/footer.jsp"%>