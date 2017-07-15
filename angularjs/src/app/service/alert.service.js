/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .service('alertService',alertService);

    alertService.$inject = ['$http','$q','CONFIG'];
    function alertService($http,$q,CONFIG) {
        var alertVm = this;
        alertVm.getHighAlerts = getHighAlerts;
        alertVm.getVehicleAlerts = getVehicleAlerts;
        alertVm.getTotalAlertCount = getTotalAlertCount;



        // Ajax call to get High Alerts from database for the last two horus
        function getHighAlerts() {
            return $http.get(CONFIG.API_HOST+'alerts/highalerts')
                .then(successFn,errorFn);
        }

        // Ajax call to get High Alerts from database for the last two horus
        function getVehicleAlerts(vechileId,type) {
            return $http.get(CONFIG.API_HOST+'/alerts/'+vechileId+'/'+type)
                .then(successFn,errorFn);
        }

        // Ajax call to get Total number Alerts of a Vehicle from sever
        function getTotalAlertCount(vehicleId,type) {
            return $http.get(CONFIG.API_HOST+'/alerts/totalcount/'+vehicleId+'/'+type)
                .then(successFn,errorFn);
        }

        // Call back functions for Success and Error Responses
        function successFn(response) {
            return response.data;
        }
        function errorFn(response) {
            return $q.reject('error is: '+response.statusText);
        }
    }
})();