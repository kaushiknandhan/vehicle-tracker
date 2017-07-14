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
            .state("alerts",{
                url:"/alerts",
                templateUrl:"app/template/alerts.template.html",
                controller:"alertsController",
                controllerAs:"alertsVm"
            })
            .state("alerts.highalerts",{
                url:"/highalerts/:id",
                templateUrl:"app/template/highalerts.template.html",
                controller:"highalertsController",
                controllerAs:"highalertsVm"
            })
        $urlRouterProvider.otherwise('/home');
    }
})();