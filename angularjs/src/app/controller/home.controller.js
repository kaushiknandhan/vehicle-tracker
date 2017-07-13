/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .controller('homeController',homeController);

    homeController.$inject = [];
    function homeController() {
        var homeVm = this;
        homeVm.vehicles = [];

        init();
        function init() {
            console.log('init function called');
        }
    }
})();