'use strict';

/**
 * EmpController
 * @constructor
 */


(function () {
    var app = angular.module("employees", ["ngSanitize",
                "angularUtils.directives.dirPagination", "ui.bootstrap"]);

    app.controller("EmpController", function ($scope, $http, $uibModal) {
        var _this = this;
        $scope.pageSize = 11;

        $scope.fetchEmpsList = function () {
            $http.get('employees/empoyeelist.json').success(function (empList) {
                $scope.emps = empList;
            });
        };

        $scope.removeEmp = function (id) {
            $http.delete('accesscards/remove/' + id).success(function () {
                $scope.fetchCardsList();
                $scope.card.id = '';
                $scope.card.statusId = '';
                $scope.card.statusName = '';
                $scope.card.inventoryNum = '';
            });
        };

        $scope.sort = function (keyname) {
            $scope.sortKey=keyname;
            $scope.reverse=!$scope.reverse;
        };

        $scope.isSortKey = function(keyname) {
            return $scope.sortKey == keyname;
        }


        $scope.fetchEmpsList();
    });


    app.directive("employeesList", function () {
        return {
            templateUrl: "employees/layout.html"
        }
    });

})();