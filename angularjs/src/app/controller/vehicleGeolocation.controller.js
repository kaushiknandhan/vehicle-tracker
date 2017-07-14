/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .controller('vehicleGeolocationController',vehicleGeolocationController);

    vehicleGeolocationController.$inject = ['vehicleService'];
    function vehicleGeolocationController(vehicleService) {
        var vehicleGeolocationVm = this;


        init();
        function init() {
           console.log('vehicle geolocation');
        }
    }
})();