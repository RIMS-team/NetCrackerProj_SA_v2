/**
 * Created by barmin on 13.01.2017.
 */
'use strict';

/**
 * InvStatusController
 * @constructor
 */

(function () {
    var app = angular.module("ordstatuses", ["ngSanitize", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);
    //////    var app = angular.module("ordstatuses", []);

    //   var OrdStatusController = function($scope, $http) {
    app.controller("OrdStatusController", function ($scope, $http, $modal) {
        var _this = this;

        $scope.fetchOrdStatusList = function() {
            $http.get('ordstats/ordstatlist.json').success(function(OrdStatusList){
                $scope.ordStats = OrdStatusList;
            });
        };

        $scope.fetchOrdStatusList();
    });

    app.directive("ordstatusesList", function () {
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