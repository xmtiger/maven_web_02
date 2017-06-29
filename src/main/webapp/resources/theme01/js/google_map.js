/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function initMap() {
                
                /*var lat = '';
                var lng = '';
                var address = {T6R3M2};
                geocoder.geocode( { 'address': address}, 
                    function(results, status) {
                        if (status == google.maps.GeocoderStatus.OK) {
                            lat = results[0].geometry.location.lat();
                            lng = results[0].geometry.location.lng();
                        }else {
                            alert("Geocode was not successful for the following reason: " + status);
                        }
                    }; 
                alert('Latitude: ' + lat + ' Logitude: ' + lng); 
                var myLocation = {lat: lat, lng: lng};*/   
                
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
