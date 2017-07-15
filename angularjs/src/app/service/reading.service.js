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

        // Ajax call to get readings from server for the last 30 minutes
        function get30minsReadings(vehicleId) {
            return $http.get(CONFIG.API_HOST+'readings/'+vehicleId+'/MINUTES/30')
                .then(successFn,errorFn);
        }

        // Ajax call to get readings from server for the specified time given i.e., DAYS,MINUTES,HOURS
        function getVehicleHistory(vehicleId,timeType,time) {
            return $http.get(CONFIG.API_HOST+'readings/'+vehicleId+'/'+timeType+'/'+time)
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