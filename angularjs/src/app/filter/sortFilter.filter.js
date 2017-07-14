/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .filter('sortFilter', sortFilter);

    function sortFilter() {
        return function (alertsList,sortType) {
            if(sortType === 'desc'){
                alertsList.sort(function (a,b) {
                    return b.alertCount - a.alertCount;
                })
                return alertsList;
            }else {
                alertsList.sort(function (a, b) {
                    return a.alertCount - b.alertCount;
                })
                return alertsList;
            }
        }
    }
})();