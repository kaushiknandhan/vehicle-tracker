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

        // Get all the Vehicle alerts from the server
        function init() {
            getCount('HIGH');
            getCount('MEDIUM');
            getCount('Low');
            alertService.getVehicleAlerts($stateParams.id, 'All')
                .then(function (alerts) {
                    vehicleAlertVm.alerts = alerts;
                }, function (error) {
                    console.log('some text: ' + error);
                });
        }

        // Assign the variable according to the oriority selected
        function filterOnPriority(priority) {
            vehicleAlertVm.selectedPriority = priority;
        }

        // Get the count of the alerts present for a particular vehicle
        function getCount(priority) {
            alertService.getTotalAlertCount($stateParams.id, priority)
                .then(function (count) {
                    if (priority === 'HIGH')
                        vehicleAlertVm.highCount = count;
                    else if (priority === 'MEDIUM')
                        vehicleAlertVm.mediumCount = count;
                    else
                        vehicleAlertVm.lowCount = count;
                }, function (error) {
                    console.log('some text: ' + error);
                });
        }

    }

})();