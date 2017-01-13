/**
 * Created by Elina on 11.01.2017.
 */
'use strict';

/**
 * OrderController
 * @constructor
 */


(function () {
    var app = angular.module("orders", ["ngSanitize", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);

    app.controller("OrderController", function ($scope, $http, $modal) {
        var _this = this;

        $scope.fetchOrderList = function() {
            $http.get('order/all').success(function(orderList){
                $scope.order = orderList;
            });
        };

        $scope.fetchOrderList();

    });

    app.directive("ordersList", function () {
        return {
            templateUrl: "order/layout.html"
        }
    });

})();