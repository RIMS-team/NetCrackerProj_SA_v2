/**
 * Created by Elina on 11.01.2017.
 */
'use strict';

/**
 * OrderController
 * @constructor
 */

// var OrderController = function($scope, $http) {
//
//     $scope.fetchOrderList = function() {
//         $http.get('order/all').success(function(orderList){
//             $scope.order = orderList;
//         });
//     };
//
//     $scope.fetchOrderList();
// };


(function () {
    var app = angular.module("orders", ["ngSanitize", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);
    //////    var app = angular.module("orders", []);

    app.controller("OrderController", function ($scope, $http, $modal) {
        var _this = this;

        // _this.isRowSelected = false;
        // _this.maxSize = 5;
        // _this.totalPages = 1;
        // _this.size = 22;
        // _this.totalElements = 0;
        // _this.currentPage = 1;
        // _this.sort = null;
        // _this.isLast = false;
        // _this.elementsOnPage = 0;
        //
        // $scope.items = [];

        $scope.fetchOrderList = function() {
            $http.get('order/all').success(function(orderList){
                $scope.order = orderList;
            });
        };

        $scope.fetchOrderList();

    });

    app.directive("ordersList", function () {
        return {
            //restrict: "E",
            templateUrl: "order/layout.html"
        }
    });

})();