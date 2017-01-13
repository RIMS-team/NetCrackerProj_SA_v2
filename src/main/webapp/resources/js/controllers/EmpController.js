'use strict';

/**
 * EmpController
 * @constructor
 */

(function () {
    var app = angular.module("employees", ["ngSanitize", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);

    app.controller("EmpController", function ($scope, $http, $modal) {
        var _this = this;


        $scope.fetchEmpsList = function () {
            $http.get('employees/empoyeelist.json').success(function (empList) {
                $scope.emps = empList;
            });
        };

        $scope.fetchEmpsList();


    });


    app.directive("employeesList", function () {
        return {
            templateUrl: "employees/layout.html"
        }
    });

})();