<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<%@include file="../../includes/subnav_admin.jsp"%>

<div class="wrapper">

    <%@include file="vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
    </div>

</div>

<script>
    $(document).ready(function(){
        $("#navbar-collapse-3").attr("aria-expanded", "true").attr("class", "collapse in");

    })
</script>

<%@include file="../../includes/footer.jsp"%>