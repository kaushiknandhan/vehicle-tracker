/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .controller('vehicleGeolocationController',vehicleGeolocationController);

    vehicleGeolocationController.$inject = ['readingService','$stateParams'];
    function vehicleGeolocationController(readingService,$stateParams) {
        var vehicleGeolocationVm = this;
        vehicleGeolocationVm.center = { latitude: 45, longitude: -73 };
        vehicleGeolocationVm.zoom = 2;
        vehicleGeolocationVm.geolocations  = [];

        init();
        function init() {
            readingService.get30minsReadings($stateParams.id)
                .then(function (geoReadings) {
                    vehicleGeolocationVm.geolocations = geoReadings;
                    if(vehicleGeolocationVm.geolocations.length != 0){
                        vehicleGeolocationVm.center.latitude = vehicleGeolocationVm.geolocations[0].latitude;
                        vehicleGeolocationVm.center.longitude = vehicleGeolocationVm.geolocations[0].longitude;
                    }
                },function (error) {
                    console.log('some error: '+error);
                });
        }
    }
})();