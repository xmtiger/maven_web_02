<%-- 
    Document   : hello
    Created on : 18-Jun-2017, 9:54:35 PM
    Author     : xmtig
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <spring:url value="/resources/theme01/css/main.css" var="mainCss" />
	<spring:url value="/resources/theme01/js/jquery.1.10.2.min.js" var="jqueryJs" />
	<spring:url value="/resources/theme01/js/main.js" var="mainJs" />
        

	<link href="${mainCss}" rel="stylesheet" />
        <script src="${jqueryJs}"></script>
        <script src="${mainJs}"></script>
        
        <style>
            #map{
                height: 300px;
                width: 600px;
            }
        </style>
    
    </head>
    
    <body>
        <h1>1. Test CSS</h1>

        <h2>2. Test JS</h2>
        <div id="msg"></div>
        
        <h3>My Google Maps Location</h3>
        <div id="map"></div>
        
        <script>
            function initMap() {               
                
                var myLocation = {lat: 53.4573578, lng: -113.5624802};
                
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 15,
                    center: myLocation
                });
                var marker = new google.maps.Marker({
                    position: myLocation,
                    map: map
                });
            } 
            
        </script>
        <script async defer
                src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDkTeCHJ7rqupSGr3ALuOqMhgdjsioBTrk&callback=initMap">
                    
        </script>
        
        
        
    </body>
    
</html>

