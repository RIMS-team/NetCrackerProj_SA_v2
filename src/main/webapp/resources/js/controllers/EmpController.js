'use strict';

/**
 * EmpController
 * @constructor
 */



// var EmpController = function($scope, $http) {
//     $scope.fetchEmpsList = function() {
//         $http.get('employees/empoyeelist.json').success(function(empList){
//             $scope.emps = empList;
//         });
//     };
//
//     $scope.fetchEmpsList();
// };




(function () {
    var app = angular.module("employees", ["ngSanitize","angularUtils.directives.dirPagination", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);
//////    var app = angular.module("employees", []);

    app.controller("EmpController", function ($scope, $http, $uibModal) {
        var _this = this;


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


        $scope.fetchEmpsList();
    });


    app.directive("employeesList", function () {
        return {
            templateUrl: "employees/layout.html"
        }
    });

})();