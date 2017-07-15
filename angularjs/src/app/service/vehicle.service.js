/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .service('vehicleService',vehicleService);

    vehicleService.$inject = ['$http','$q','CONFIG'];
    function vehicleService($http,$q,CONFIG) {
        var vehicleVm = this;
        vehicleVm.getVehicles = getVehicles;
        vehicleVm.getVehicleInfo = getVehicleInfo;

        // Ajax call to get All the vehicles preset from the database
        function getVehicles() {
            return $http.get(CONFIG.API_HOST+'vehicles')
                .then(successFn,errorFn);
        }

        // Ajax call to get vehicle Information from sever
        function getVehicleInfo(vechileId) {
            return $http.get(CONFIG.API_HOST+'/vehicles/'+vechileId)
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