/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .controller('vehicleOverviewController',vehicleOverviewController);

    vehicleOverviewController.$inject = ['vehicleService','$stateParams'];
    function vehicleOverviewController(vehicleService,$stateParams) {
        var vehicleOverviewVm = this;
        vehicleOverviewVm.overview = {};

        init();

        function init() {
            vehicleService.getVehicleInfo($stateParams.id)
                .then(function (vehicle) {
                    vehicleOverviewVm.overview = vehicle;
                },function (error) {
                    console.log('some error: '+error);
                });
        }
    }
})();