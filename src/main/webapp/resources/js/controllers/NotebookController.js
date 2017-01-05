'use strict';

/**
 * NotebookController
 * @constructor
 */
var NotebookController = function($scope, $http) {

    $scope.fetchNotebookList = function() {
        $http.get('notebook/all').success(function(notebookList){
            $scope.notebook = notebookList;
        });
    };

    $scope.addNewCar = function(newCar) {
        $http.post('cars/addCar/' + newCar).success(function() {
            $scope.fetchCarsList();
        });
        $scope.carName = '';
    };

    $scope.removeCar = function(car) {
        $http.delete('cars/removeCar/' + car).success(function() {
            $scope.fetchCarsList();
        });
    };

    $scope.removeAllCars = function() {
        $http.delete('cars/removeAllCars').success(function() {
            $scope.fetchCarsList();
        });

    };

    $scope.fetchNotebookList();
};