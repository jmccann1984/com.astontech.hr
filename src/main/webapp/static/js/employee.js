function buildTable(){

    $.getJSON('http://localhost:8080/api/employee/', {ajax: 'true'},
    function (data) {
        console.log(data);
        $.each(data, function (index, single) {
            var fullName = single.firstName + ' ' + single.lastName;
            $('#employee-table').find('tbody')
                .append($('<tr>')
                    .append($('<td>').text(single.id))
                    .append($('<td>').text(fullName))
                    .append($('<td>').text(single.background))
                    .append($('<td>')
                        .append($('<select>').attr('class', 'form-control')
                            .append($('<option>').attr('value', '0').text('(Select One)'))
                        ))
                    .append($('<td>')
                        .append($('<button>').attr('onclick', 'editEmployee(' + single.id + ')').attr('class', 'btn btn-default').text('Edit'))
                    )
                    .append($('<td>')
                        .append($('<button>').attr('data-toggle', 'modal').attr('data-target', '#confirmDeleteModal').attr('data-record-id', single.id).attr('id', 'DeleteButtonForEmployee'+single.id).attr('class', 'btn btn-default').text('Delete'))
                    )
                );
        });
    });
}

function saveEmployee(){

    var firstName = $('#inputFirstName').val();
    var lastName = $('#inputLastName').val();
    var background = $('#inputBackground').val();
    var id = $('#employeeId').val();
    var version = $('#employeeVersion').val();

    var employee = {
        id: id,
        version: version,
        firstName: firstName,
        lastName: lastName,
        background: background,
        project: []
    };

    console.log(employee);

    $.ajax({
        type: "post",
        data: employee,
        url: "/api/employee/",
        async: true,
        dataType: "json",
        success: function() {
            window.location.reload();
        }

    })
}

function editEmployee(id) {
    $.getJSON('/api/employee/' + id, {ajax: 'true'}, function(employee){
        console.log(employee);
        $('#employeeId').val(employee.id);
        $('#employeeVersion').val(employee.version);
        $('#inputFirstName').attr('placeholder', employee.firstName);
        $('#inputFirstName').val(employee.firstName);
        $('#inputLastName').attr('placeholder', employee.lastName);
        $('#inputLastName').val(employee.lastName);
        $('#inputBackground').text(employee.background);
        $('#employeeModal').modal('show');
    })

}

function newEmployee() {
    $('#employeeId').val("");
    $('#employeeVersion').val("");
    $('#inputFirstName').attr('placeholder', "First Name");
    $('#inputFirstName').val("");
    $('#inputLastName').attr('placeholder', "Last Name");
    $('#inputLastName').val("");
    $('#inputBackground').text("");
    $('#employeeModal').modal('show');
}

function deleteEmployee(id) {
    $.ajax({
        type: "delete",
        url:"/api/employee/" + id,
        async:true,
        dataType: "json",
        success: function() {
            window.location.reload();
        }
    })
}

function deleteModal() {
    $('#confirmDeleteModal').on('click', '.btn-ok', function(e) {
        var id = $(this).data('recordId');

        console.log('this nodeName: ' + this.nodeName);
        console.log('this id: ' + $(this).attr('id'));

        $.ajax({
            type: "delete",
            url:"/api/employee/" + id,
            async:true,
            dataType: "json",
            success: function() {
                window.location.reload();
            }
        })
    });

    $('#confirmDeleteModal').on('show.bs.modal', function (e) {
        var data = $(e.relatedTarget).data();

        console.log("related nodeName: " + e.relatedTarget.nodeName);
        console.log("related id: " + $(e.relatedTarget).attr('id'));

        $('.btn-ok').data('recordId', data.recordId);
    });

}