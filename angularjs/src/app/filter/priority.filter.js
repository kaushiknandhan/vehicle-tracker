/**
 * Created by kaushik nandhan on 7/13/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .filter('priorityFilter',priorityFilter);

    function priorityFilter() {
        return function (inputArr,priority) {
            // return array if the priority is empty or not defined
            if(priority === "" || priority === undefined){
                return inputArr;
            }
            // Filter the input array according to the priority HIGH,MEDIUM,LOw
            else{
                return inputArr.filter(function(alert){
                    return alert.priority === priority;
                });
            }
        }
    }
})();