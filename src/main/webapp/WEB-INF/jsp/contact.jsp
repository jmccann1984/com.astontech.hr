<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>
<div id="main-wrapper" class="container">
    <div class="container-fluid row">
        <h2>Contact Page</h2>

        <button onclick="newContact()" class="btn btn-default">Add New Contact</button>
    </div>

    <div id="employee" class="container-fluid row list-group"></div>
</div>

<div id="contactModal" class="modal container-fluid">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Contact Details</h4>
            </div>
            <div class="modal-body col-sm-12">
                <form class="form-horizontal">
                    <fieldset>

                        <input type="hidden" id="contactId" />
                        <input type="hidden" id="contactVersion" />
                        <input type="hidden" id="addressList" />

                        <select class="form-control" id="employeeSelect">
                            <option value="0">(Select One)</option>

                        </select>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th class="col-sm-2">Num</th>
                                    <th class="col-sm-3">Street</th>
                                    <th class="col-sm-2">Street02</th>
                                    <th class="col-sm-2">City</th>
                                    <th class="col-sm-1">State</th>
                                    <th class="col-sm-1">Zip</th>
                                    <th class="col-sm-1">Country</th>
                                </tr>
                            </thead>
                            <tbody id="add">
                            </tbody>
                        </table>
                        <span class="btn btn-default" onclick="addRow()">+</span>
                        <span class="btn btn-default" onclick="subRow()">-</span>
                    </fieldset>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="removeAllRows()">Close</button>
                <button type="button" class="btn btn-primary" onclick="saveContact()">Save Contact</button>
            </div>
        </div>
    </div>
</div>

<c:url value="/static/js/contact.js" var="contactJs"/>
<script src="${contactJs}"></script>
<script>
    $(document).ready(function() {
        buildContactData();
        //deleteModal();
    })
</script>


<%@include file="includes/footer.jsp"%>