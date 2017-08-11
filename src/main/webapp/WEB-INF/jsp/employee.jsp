<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>
<div class="container">
    <h2>Employee Page</h2>

    <button onclick="newEmployee()" class="btn btn-default">Add New Employee</button>

    <table id="employee-table" class="table table-striped table-hover">
        <thead>
            <th>ID</th>
            <th>Name</th>
            <th>Background</th>
            <th>Projects</th>
            <th>Edit</th>
            <th>Delete</th>
        </thead>
        <tbody>
            <%--JQuery data to be included here--%>
        </tbody>
    </table>

    <div id="employeeModal" class="modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Employee Details</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <fieldset>

                            <input type="hidden" id="employeeId" />
                            <input type="hidden" id="employeeVersion" />

                            <div class="form-group">
                                <label for="inputFirstName" class="col-lg-2 control-label">First Name</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="inputFirstName" placehoder="First Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputLastName" class="col-lg-2 control-label">Last Name</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="inputLastName" placehoder="Last Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputBackground" class="col-lg-2 control-label">Background</label>
                                <div class="col-lg-10">
                                    <textarea class="form-control" rows="3" id="inputBackground"></textarea>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="saveEmployee()">Save Employee</button>
                </div>
            </div>
        </div>
    </div>

    <div id="confirmDeleteModal" class="modal">
        <div class="modal-dialog">
            <div class="alert alert-warning">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4>Confirm Delete</h4>
                <p>Are you sure you want to delete employee?  This cannot be undone</p>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-danger btn-ok" id="confirmDelete" >Confirm</button>
            </div>
        </div>
    </div>

</div>

<c:url value="/static/js/employee.js" var="employeeJs"/>
<script src="${employeeJs}"></script>
<script>
    $(document).ready(function() {
        buildTable();
        deleteModal();
    })
</script>


<%@include file="includes/footer.jsp"%>