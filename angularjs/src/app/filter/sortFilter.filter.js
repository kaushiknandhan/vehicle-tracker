/**
 * Filter to sort the list based on the alert count
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .filter('sortFilter', sortFilter);

    function sortFilter() {
        return function (alertsList,sortType) {
            // Sort the array list in decending order of alert count if sort type is descending else sort the array in ascending
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