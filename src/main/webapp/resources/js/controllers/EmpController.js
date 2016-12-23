'use strict';

/**
 * CarController
 * @constructor
 */
var EmpController = function($scope, $http) {
    $scope.fetchEmpsList = function() {
        $http.get('employees/empoyeelist.json').success(function(empList){
            $scope.emps = empList;
        });
    };
    
    $scope.fetchEmpsList();
};