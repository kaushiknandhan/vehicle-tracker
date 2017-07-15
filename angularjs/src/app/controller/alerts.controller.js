/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .controller('alertsController',alertsController);

    alertsController.$inject = ['alertService'];
    function alertsController(alertService) {
        var alertsVm = this;
        alertsVm.highAlerts = [];
        alertsVm.sortType = "desc";
        alertsVm.isLengthZero = isLengthZero;
        alertsVm.sortList = sortList;

        init();

        // initialize high alert when the controller is loaded
        function init() {
            alertService.getHighAlerts()
                .then(function (highAlerts) {
                    alertsVm.highAlerts = highAlerts;
                },function (error) {
                    console.log('some error: '+error);
                });
        }

        // check if the highAlerts array is empty or not
        function isLengthZero() {
         return (alertsVm.highAlerts.length == 0)?true:false;
        }

        // Assign the sort Type variable to desc or asc
        function sortList() {
            (alertsVm.sortType === 'desc')?alertsVm.sortType = 'asc':alertsVm.sortType = 'desc';
        }
    }
})();