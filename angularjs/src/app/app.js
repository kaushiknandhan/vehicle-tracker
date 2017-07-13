/**
 * Created by kaushik nandhan on 7/11/2017.
 */
(function () {
    'use strict';
    angular.module('tracker',["ui.router"])
        .config(moduleConfig);

    function moduleConfig($stateProvider,$urlRouterProvider) {
        $stateProvider
            .state("home",{
                url:"/home",
                templateUrl:"app/template/home.template.html",
                controller:"homeController",
                controllerAs:"homeVm"
            })
        $urlRouterProvider.otherwise('/home');
    }
})();