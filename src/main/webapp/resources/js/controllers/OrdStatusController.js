/**
 * Created by barmin on 13.01.2017.
 */
'use strict';

/**
 * OrdStatusController
 * @constructor
 */

(function () {
    var modul = angular.module("ordstatuses", ["ngSanitize", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);
    //////    var app = angular.module("ordstatuses", []);

    //   var OrdStatusController = function($scope, $http) {
    modul.controller("OrdStatusController", function ($scope, $http, $modal, ordStatusService) {
        var _this = this;

        ordStatusService.loadList()
            .success(function(OrdStatusList){
                $scope.ordStats = ordStatusService.getList();
            }).error(function () {
             $scope.ordStats = ordStatusService.getList();
        });
    });

    modul.directive("ordstatusesList", function () {
        return {
            templateUrl: "ordstats/layout.html"
        }
    });

})();


// var OrdStatusController = function($scope, $http) {
//     $scope.fetchOrdStatusList = function() {
//         $http.get('ordstats/ordstatlist.json').success(function(OrdStatusList){
//             $scope.ordStats = OrdStatusList;
//         });
//     };
//
//     $scope.fetchOrdStatusList();
// };