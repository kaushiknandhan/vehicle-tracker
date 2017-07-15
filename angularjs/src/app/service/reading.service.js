/**
 * Created by kaushik nandhan on 7/14/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .service('readingService',readingService);

    readingService.$inject = ['$http','$q','CONFIG'];
    function readingService($http,$q,CONFIG) {
        var readingVm = this;
        readingVm.get30minsReadings = get30minsReadings;
        readingVm.getVehicleHistory = getVehicleHistory;

        function get30minsReadings(vehicleId) {
            return $http.get(CONFIG.API_HOST+'readings/'+vehicleId+'/MINUTES/30')
                .then(successFn,errorFn);
        }

        function getVehicleHistory(vehicleId,timeType,time) {
            return $http.get(CONFIG.API_HOST+'readings/'+vehicleId+'/'+timeType+'/'+time)
                .then(successFn,errorFn);
        }


        function successFn(response) {
            return response.data;
        }
        function errorFn(response) {
            return $q.reject('error is: '+response.statusText);
        }
    }
})();