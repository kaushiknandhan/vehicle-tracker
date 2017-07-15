/**
 * Created by kaushik nandhan on 7/14/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .directive('trackerAlert',trackerAlert);

    trackerAlert.$inject = [];
    function trackerAlert() {
        return {
            templateUrl:"app/template/alert-box.template.html",
            scope:{
                alertBox: "="
            }
        }
    }
})();