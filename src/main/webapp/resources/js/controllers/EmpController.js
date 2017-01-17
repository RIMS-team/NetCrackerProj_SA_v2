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
    var app = angular.module("employees", ["ngSanitize", "ui.bootstrap", "ui.grid", "ui.grid.selection", "ui.select", "ui.grid.autoResize"]);
//////    var app = angular.module("employees", []);

    app.controller("EmpController", function ($scope, $http, $modal) {
        var _this = this;


        $scope.fetchEmpsList = function () {
            $http.get('employees/empoyeelist.json').success(function (empList) {
                $scope.emps = empList;
            });
        };

        $scope.fetchEmpsList();


        $scope.removeEmp = function (id) {
            $http.delete('accesscards/remove/' + id).success(function () {
                $scope.fetchCardsList();
                $scope.card.id = '';
                $scope.card.statusId = '';
                $scope.card.statusName = '';
                $scope.card.inventoryNum = '';
            });
        };


    });


    app.directive("employeesList", function () {
        return {
            templateUrl: "employees/layout.html"
        }
    });

})();