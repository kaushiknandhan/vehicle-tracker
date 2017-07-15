/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .controller('highalertsController',highalertsController);

    highalertsController.$inject = ['alertService','$stateParams'];
    function highalertsController(alertService,$stateParams) {
        var highalertsVm = this;

        init();
        // Get high priority alerts
        function init() {
            highalertsVm.alerts = [];
            alertService.getVehicleAlerts($stateParams.id,'HIGHALERTS')
                .then(function (highAlerts) {
                    highalertsVm.alerts = highAlerts;
                },function (error) {
                    console.log('some error '+error);
                });
        }
    }
})();