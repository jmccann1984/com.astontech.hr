<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>



<c:url value="/static/js/contact.js" var="contactJs"/>
<script src="${contactJs}"></script>
<script>
    $(document).ready(function() {
        buildTable();
        deleteModal();
    })
</script>


<%@include file="includes/footer.jsp"%>