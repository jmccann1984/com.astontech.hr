var rowId = 0;

function buildContactData(){

    $.getJSON('http://localhost:8080/api/employee/', {ajax: 'true'},
    function(data){
        // console.log(data);
        $.each(data, function(index, employee){
            $('#employee')
                .append($('<div>').attr('class', 'col-sm-12 row list-group-item').attr('data-toggle', 'collapse').attr('data-target', '#employee'+employee.id)
                    .append($('<div>').attr('class', 'container-fluid row').text(employee.firstName + ' ' + employee.lastName))
                )
                .append($('<div>').attr('class', 'col-sm-12 row collapse in').attr('id', 'employee'+employee.id)
                    .append($('<div>').attr('class', 'container-fluid row').attr('id', 'nullDataDiv')
                        .append($('<div>').attr('class', 'container-fluid row list-group-item').text('No Contact Data'))
                    )
                );
            $('#employeeSelect')
                .append($('<option>').attr('value', employee.id).text(employee.firstName+' '+employee.lastName).attr('id', 'employeeNum'+employee.id));
        });

        $.getJSON('http://localhost:8080/api/contact/', {ajax: 'true'},
            function(data){
                // console.log(data);
                $.each(data, function(index, single){
                    $('#employee'+single.employee.id+'>#nullDataDiv').remove();
                    // console.log(single.addressList);
                    $('#employee'+single.employee.id)
                        .append($('<div>').attr('class', 'container-fluid row list-group-item')
                            .append($('<div>').attr('class', 'container-fluid row').attr('data-toggle', 'collapse').attr('data-target', '#'+single.id)
                                .append($('<div>').attr('class', 'col-sm-10').text('Addresses'))
                                .append($('<div>').attr('class', 'col-sm-1').append($('<button>').attr('class', 'btn btn-default').attr('onclick', 'editContact(' + single.id + ')').text('Edit')))
                                .append($('<div>').attr('class', 'col-sm-1').append($('<button>').attr('class', 'btn btn-default').attr('onclick', 'deleteContact(' + single.id + ')').text('Delete')))
                            )
                            .append($('<div>').attr('class', 'container-fluid row collapse in').attr('id', single.id)
                                .append($('<table>').attr('class', 'table table-striped table-hover').attr('id', 'addressList'+single.id)
                                    .append($('<thead>')
                                        .append($('<tr>')
                                            .append($('<th>').attr('class', 'col-sm-2').text('Num'))
                                            .append($('<th>').attr('class', 'col-sm-4').text('Street'))
                                            .append($('<th>').attr('class', 'col-sm-2').text('City'))
                                            .append($('<th>').attr('class', 'col-sm-2').text('State'))
                                            .append($('<th>').attr('class', 'col-sm-2').text('Country'))
                                        )
                                    )
                                    .append($('<tbody>'))
                                )
                            )
                        );

                    $.each(single.addressList, function(index, address){
                        // console.log(address);
                        var streetAddress = $.isEmptyObject(address.street02)?address.street:address.street + ' ' + address.street02;
                        $('#addressList'+single.id).find('tbody')
                            .append($('<tr>')
                                .append($('<td>').text(address.addressNumber))
                                .append($('<td>').text(streetAddress))
                                .append($('<td>').text(address.city))
                                .append($('<td>').text(address.state))
                                .append($('<td>').text(address.country))
                            )
                    });
                });
            });
    });
}

function deleteContact(id){
    $.ajax({
        type: 'delete',
        url:'/api/contact/' + id,
        async: true,
        dataType: 'json',
        success: function() {
            window.location.reload();
        }
    });
}

function editContact(id) {
    $.getJSON('/api/contact/'+id, {ajax: true}, function(contact){
        $('#employeeSelect').val(contact.employee.id);
        $('#contactId').val(contact.id);
        $('#contactVersion').val(contact.version);
        $.each(contact.addressList, function(index, address){
            addRow();
            address.id===null?$('#addyId'+(rowId-1)).val(''):$('#addyId'+(rowId-1)).val(address.id);
            address.version===null?$('#addyVersion'+(rowId-1)).val(''):$('#addyVersion'+(rowId-1)).val(address.version);
            $('#num'+(rowId-1)).val(address.addressNumber==="null"?'':address.addressNumber);
            $('#street'+(rowId-1)).val(address.street);
            $('#street02'+(rowId-1)).val(address.street02);
            $('#city'+(rowId-1)).val(address.city);
            $('#state'+(rowId-1)).val(address.state);
            $('#zip'+(rowId-1)).val(address.zip==="null"?'':address.zip);
            $('#country'+(rowId-1)).val(address.country);
        });
        $('#contactModal').modal('show');
    });
}

function newContact() {
    $('#contactId').val('');
    $('#contactVersion').val('');
    $('#addressList').val('');
    $('#contactModal').modal('show');
}

function saveContact() {
    var id = $('#contactId').val();
    var version = $('#contactVersion').val();
    var employeeId = $('#employeeSelect').val();
    var addressList;
    var newAddressList = [];
    for(var i=0 ; i<rowId; i++){

        var address = {
            id: $('#addyId' + i).val(),
            version: $('#addyVersion' + i).val(),
            addressNumber: $('#num' + i).val(),
            street: $('#street' + i).val(),
            street02: $('#street02' + i).val(),
            city: $('#city' + i).val(),
            state: $('#state' + i).val(),
            zip: $('#zip' + i).val(),
            country: $('#country' + i).val()
        };
        newAddressList.push(address);
    }
    // console.log(addressList);

    removeAllRows();

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'post',
        data: JSON.stringify(newAddressList),
        url: '/api/address/m/',
        async: true,
        dataType: 'json',
        success: function (result) {
            console.log('successfully saved addressList');
            addressList = result;
            console.log(addressList);
            console.log(result);

            $.ajax({
                type: 'get',
                url: '/api/employee/' + employeeId,
                async: true,
                dataType: 'json',
                success: function (result) {
                    // console.log(result);
                    var contact = {
                        id: id,
                        version: version,
                        addressList: addressList,
                        employee: result
                    };

                    console.log(contact);

                    $.ajax({
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        type: 'post',
                        data: JSON.stringify(contact),
                        url: '/api/contact/',
                        async: true,
                        dataType: 'json',
                        success: function () {
                            // console.log('successfully saved contact');

                            window.location.reload();
                        }
                    })
                }
            });
        }

    });
}

function addRow(){
    $('#add').append($('<tr>').attr('id', 'row'+rowId)
        .append($('<td>')
            .append($('<input>').attr('type', 'number').attr('class', 'form-control').attr('id', 'num' + rowId).val(''))
        )
        .append($('<td>')
            .append($('<input>').attr('class', 'form-control').attr('id', 'street' + rowId).val(''))
        )
        .append($('<td>')
            .append($('<input>').attr('class', 'form-control').attr('id', 'street02' + rowId).val(''))
        )
        .append($('<td>')
            .append($('<input>').attr('class', 'form-control').attr('id', 'city' + rowId).val(''))
        )
        .append($('<td>')
            .append($('<input>').attr('class', 'form-control').attr('id', 'state' + rowId).val(''))
        )
        .append($('<td>')
            .append($('<input>').attr('type', 'number').attr('class', 'form-control').attr('id', 'zip' + rowId).val(''))
        )
        .append($('<td>')
            .append($('<input>').attr('class', 'form-control').attr('id', 'country' + rowId).val(''))
        )
        .append($('<hidden>').attr('id', 'addyId'+rowId).val(''))
        .append($('<hidden>').attr('id', 'addyVersion'+rowId).val(''))
    );

    rowId++;
}

function subRow(){
    if(rowId>0) {
        rowId--;
        $('#row' + rowId).remove();
    }
}

function removeAllRows(){
    $('#add').find('tr').remove();
    $('#employeeSelect').val(0);
    rowId=0;
}