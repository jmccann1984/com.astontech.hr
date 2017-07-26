<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<%@include file="../../includes/subnav_admin.jsp"%>

<div class="wrapper">

    <%@include file="element_sidebar.jsp"%>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">

            <c:set var="idx" value="0" scope="page" />
            <form:form class="form-horizontal" id="elementType" modelAttribute="elementType" action="/admin/element/update" method="post">
                <form:hidden path="id"/>
                <form:hidden path="version"/>

                <div class="row">
                    <div class="form-group">
                        <label for="inputElementTypeName" class="col-sm-2 control-label">Element Type</label>
                        <div class="col-sm-10">
                            <form:input id="inputElementTypeName" path="elementTypeName" type="text" class="form-control"></form:input>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <hr>
                    </div>
                </div>

                <c:forEach items="${elementType.elementList}" var="element">
                    <form:hidden path="elementList[${idx}].id"/>
                    <form:hidden path="elementList[${idx}].version"/>
                    <div class="row">
                        <label for="inputElement${idx}" class="col-sm-3 control-label">Element</label>
                        <div class="col-sm-7">
                            <div class="input-group">
                                <form:input id="inputElement${idx}" path="elementList[${idx}].elementName" class="form-control"/>
                                <span class="input-group-btn">
                                    <button name="${idx}" class="btn btn-default remove_button" type="button">Remove</button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <c:set var="idx" value="${idx + 1}" scope="page"/>
                </c:forEach>

                <div class="row">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" for="inputNewElement">Add New Element</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputNewElement" name="inputNewElement"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <button class="btn btn-primary">Update</button>
                </div>
            </form:form>

        </div>
        <div class="col-sm-4">
            <%--ALERTS--%>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        //attach an on click function to the remove buttons
        $('.remove_button').click(function(){
            //log name button and contents of associated text box
            console.log(this.name);
            console.log($('#inputElement' + this.name).val());

            //clear the value/contents of the textbox
            $('#inputElement' + this.name).val("");

            //submit the form
            $('#elementType').submit();
        })
    })
</script>