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
                var resultArr = [];
                console.log('entered filter');
                for(var i=0;i<inputArr.length;i++){
                    if(inputArr[i].priority === priority){
                        resultArr.push(inputArr[i]);
                    }
                }
                return resultArr;
            }
        }
    }
})();