/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .controller('vehicleAlertController',vehicleAlertController);

    vehicleAlertController.$inject = ['alertService','$stateParams'];
    function vehicleAlertController(alertService,$stateParams) {
        var vehicleAlertVm = this;
        vehicleAlertVm.alerts = [];
        vehicleAlertVm.highCount = 0;
        vehicleAlertVm.mediumCount = 0;
        vehicleAlertVm.lowCount = 0;
        vehicleAlertVm.selectedPriority = "";
        vehicleAlertVm.filterOnPriority = filterOnPriority;

        init();

        function init() {
            highCount();
            mediumCount();
            lowCount();
            alertService.getVehicleAlerts($stateParams.id,'All')
                .then(function (alerts) {
                    vehicleAlertVm.alerts = alerts;
                },function (error) {
                    console.log('some text: '+error);
                });
        }

        function filterOnPriority(priority) {
            vehicleAlertVm.selectedPriority = priority;
        }

        function highCount() {
            alertService.getTotalAlertCount($stateParams.id,'HIGH')
                .then(function (count) {
                    vehicleAlertVm.highCount = count;
                },function (error) {
                    console.log('some text: '+error);
                });
        }

        function mediumCount() {
            alertService.getTotalAlertCount($stateParams.id,'MEDIUM')
                .then(function (count) {
                    vehicleAlertVm.mediumCount = count;
                },function (error) {
                    console.log('some text: '+error);
                });
        }

        function lowCount() {
            alertService.getTotalAlertCount($stateParams.id,'Low')
                .then(function (count) {
                    vehicleAlertVm.lowCount = count;
                },function (error) {
                    console.log('some text: '+error);
                });
        }
    }

})();