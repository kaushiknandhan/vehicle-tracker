/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .filter('priorityFilter',priorityFilter);

    function priorityFilter() {
        return function (inputArr,priority) {
            if(priority === "" || priority === undefined){
                return inputArr;
            }
            else{
                return inputArr.filter(function(alert){
                    return alert.priority === priority;
                });
            }
        }
    }
})();