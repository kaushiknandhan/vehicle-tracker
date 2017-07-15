/**
 * Created by kaushik nandhan on 7/14/2017.
 */
(function () {
    'use strict';
    angular.module('tracker')
        .controller('vehicleHistoryTypeController',vehicleHistoryTypeController);

    vehicleHistoryTypeController.$inject = ['readingService','$stateParams'];
    function vehicleHistoryTypeController(readingService,$stateParams) {
        var vehicleHistoryTypeVm = this;
        vehicleHistoryTypeVm.type = $stateParams.type;
        vehicleHistoryTypeVm.selectedTime = "DAYS";
        vehicleHistoryTypeVm.slidervalue = 5;
        vehicleHistoryTypeVm.sliderOptions = {
            floor: 1,
            ceil: 31
        }
        vehicleHistoryTypeVm.readings = [];
        vehicleHistoryTypeVm.speedData = [];
        vehicleHistoryTypeVm.engineRpm = [];
        vehicleHistoryTypeVm.engineHp = [];
        vehicleHistoryTypeVm.fuelVolume = [];
        vehicleHistoryTypeVm.time = [];
        vehicleHistoryTypeVm.timesStamps = ['DAYS','HOURS','MINUTES'];
        vehicleHistoryTypeVm.series = ['Series A']
        vehicleHistoryTypeVm.options= {
            scales: {
                yAxes: [
                    {
                        id: 'y-axis-1',
                        type: 'linear',
                        display: true,
                        position: 'left'
                    }
                ]
            }
        }
        vehicleHistoryTypeVm.datasetOverride = [{ yAxisID: 'y-axis-1' }]
        vehicleHistoryTypeVm.readingWithTime =readingWithTime;
        vehicleHistoryTypeVm.changeMaxMinValues = changeMaxMinValues;
        init();

        function init() {
            getReadingsWIthTime($stateParams.id,'DAYS',5);
        }

        function getReadingsWIthTime(id,timeStamp,time) {
            readingService.getVehicleHistory(id,timeStamp,time)
                .then(function (history) {
                    vehicleHistoryTypeVm.readings = history;
                    collectAllData(vehicleHistoryTypeVm.readings);

                },function (error) {
                    console.log('some error: '+error);
                });
        }
        function getFormatedTime(time) {
            var date = new Date(time);
            var formatedTime = [date.getMonth()+1,
                    date.getDate(),
                    date.getFullYear()].join('/')+' '+
                [date.getHours(),
                    date.getMinutes(),
                    date.getSeconds()].join(':');
            return formatedTime;
        }

        function collectAllData(vehicleData){
            for(var i = 0; i < vehicleData.length ; i++){
                vehicleHistoryTypeVm.speedData.push(vehicleData[i].speed);
                vehicleHistoryTypeVm.engineRpm.push(vehicleData[i].engineRpm);
                vehicleHistoryTypeVm.engineHp.push(vehicleData[i].engineHp);
                vehicleHistoryTypeVm.fuelVolume.push(vehicleData[i].fuelVolume);
                vehicleHistoryTypeVm.fuelVolume.push(vehicleData[i].fuelVolume);
                vehicleHistoryTypeVm.time.push(getFormatedTime(vehicleData[i].timestamp));
            }
        }

        function changeMaxMinValues() {
            if(vehicleHistoryTypeVm.selectedTime === "DAYS"){
                setOptionsSlider(1,31);
            }else if(vehicleHistoryTypeVm.selectedTime === "HOURS"){
                setOptionsSlider(1,24);
            }else {
                setOptionsSlider(1,60);
            }
        }

        function setOptionsSlider(min,max) {
            vehicleHistoryTypeVm.sliderOptions.floor = min;
            vehicleHistoryTypeVm.sliderOptions.ceil = max;
        }

        function readingWithTime(timeStamp,time) {
            cleanDataFromArrays();
            getReadingsWIthTime($stateParams.id,timeStamp,time);
        }
        function cleanDataFromArrays() {
            vehicleHistoryTypeVm.readings = [];
            vehicleHistoryTypeVm.speedData = [];
            vehicleHistoryTypeVm.engineRpm = [];
            vehicleHistoryTypeVm.engineHp = [];
            vehicleHistoryTypeVm.fuelVolume = [];
            vehicleHistoryTypeVm.time = [];
        }

    }
})();