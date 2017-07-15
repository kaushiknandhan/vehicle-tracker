/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .controller('homeController',homeController);

    homeController.$inject = ['vehicleService'];
    function homeController(vehicleService) {
        var homeVm = this;
        homeVm.vehicles = [];

        init();
        // get all vehicles from server
        function init() {
            vehicleService.getVehicles()
                .then(function (vehicles) {
                    homeVm.vehicles = vehicles;
                },function (error) {
                    console.log(error);
                })
        }
    }
})();