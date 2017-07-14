/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .service('alertService',alertService);

    alertService.$inject = ['$http','$q'];
    function alertService($http,$q) {
        var alertVm = this;
        alertVm.getHighAlerts = getHighAlerts;
        alertVm.getVehicleAlerts = getVehicleAlerts;



        // Ajax call to get High Alerts from database for the last two horus
        function getHighAlerts() {
            return $http.get('http://localhost:9009/api/'+'alerts/highalerts')
                .then(successFn,errorFn);
        }

        // Ajax call to get High Alerts from database for the last two horus
        function getVehicleAlerts(vechileId,type) {
            return $http.get('http://localhost:9009/api/'+'/alerts/'+vechileId+'/'+type)
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