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

        function init() {
            alertService.getHighAlerts()
                .then(function (highAlerts) {
                    alertsVm.highAlerts = highAlerts;
                },function (error) {
                    console.log('some error: '+error);
                });
        }

        function isLengthZero() {
         return (alertsVm.highAlerts.length == 0)?true:false;
        }

        function sortList() {
            // if(alertsVm.sortType === 'desc'){
            //     alertsVm.sortType = 'asc';
            // }else{
            //     alertsVm.sortType = 'desc';
            // }
            (alertsVm.sortType === 'desc')?alertsVm.sortType = 'asc':alertsVm.sortType = 'desc';

        }
    }
})();