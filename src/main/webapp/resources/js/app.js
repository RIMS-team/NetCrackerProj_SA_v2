'use strict';

var AngularSpringApp = {};

var App = angular.module('AngularSpringApp', ['AngularSpringApp.filters', 'AngularSpringApp.services', 'AngularSpringApp.directives']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/cars', {
        templateUrl: 'cars/layout',
        controller: CarController
    });

    $routeProvider.when('/employees', {
        templateUrl: 'employees/layout',
        controller: EmpController
    });

    $routeProvider.when('/trains', {
        templateUrl: 'trains/layout',
        controller: TrainController
    });
    
    $routeProvider.when('/railwaystations', {
        templateUrl: 'railwaystations/layout',
        controller: RailwayStationController
    });

    $routeProvider.when('/notebooks', {
        templateUrl: 'notebook/layout',
        controller: NotebookController
    });

    $routeProvider.when('/accesscards', {
        templateUrl: 'accesscards/layout',
        controller: AccessCardController
    });

    $routeProvider.when('/users', {
        templateUrl: 'user/layout',
        controller: UserController
    });

    $routeProvider.when('/orders', {
        templateUrl: 'order/layout',
        controller: OrderController
    });

    $routeProvider.otherwise({redirectTo: '/employees'});
}]);
