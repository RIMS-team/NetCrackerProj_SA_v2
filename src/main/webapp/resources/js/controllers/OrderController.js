/**
 * Created by Elina on 11.01.2017.
 */
'use strict';

/**
 * OrderController
 * @constructor
 */
var OrderController = function($scope, $http) {

    $scope.fetchOrderList = function() {
        $http.get('order/all').success(function(orderList){
            $scope.order = orderList;
        });
    };

    $scope.fetchOrderList();
};