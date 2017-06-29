<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <hr>
    <footer>
        <p>&copy; Yamax.com 2017</p>
    </footer>
</div>

<script src="http://ajax.gooleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<spring:url value="/resources/core/js/hello.js" var="coreJs" />
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>