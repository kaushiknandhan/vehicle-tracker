/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .controller('vehicleInfoController',vehicleInfoController);

    vehicleInfoController.$inject = ['vehicleService','alertService','$stateParams'];
    function vehicleInfoController(vehicleService,alertService,$stateParams) {
        var vehicleInfoVm = this;
        vehicleInfoVm.vehicle = {};
        vehicleInfoVm.totalCount = 0;

        init();

        function init() {
            getAlertCount();
            vehicleService.getVehicleInfo($stateParams.id)
                .then(function (vehicleInfo) {
                    vehicleInfoVm.vehicle = vehicleInfo;
                },function (error) {
                    console.log('Some error: '+error);
                });
        }

        function getAlertCount() {
            alertService.getTotalAlertCount($stateParams.id,'All')
                .then(function (totalCount) {
                    vehicleInfoVm.totalCount = totalCount;
                },function (error) {
                    console.log('Some error: '+error);
                });
        }

    }
})();