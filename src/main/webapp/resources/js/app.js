// 'use strict';

(function () {
    var app = angular.module("AngularSpringApp", ["ui.bootstrap",
        'AngularSpringApp.filters', 'AngularSpringApp.services', 'AngularSpringApp.directives',
        "employees", "notebooks","usersettings", "users", "accesscards", "orders", "invstatuses", "ordstatuses"

    ]);



    app.controller("MainController", function ($rootScope, $scope, $compile, $element, $timeout, $http, $uibModal, $filter) {
        var store = this;

        /* tabs */
        store.addTab = function (name, caption, directive) {
            if ($element.find("#tabHeader_" + name).length === 0) {
                var headerHtml = '<li id="tabHeader_' + name + '"><a href="#tabContent_' + name + '" data-toggle="tab">' + caption +
                    '<button class="close" type="button" ng-click="mainCtrl.removeTab(\'' + name + '\')" title="Close Tab">x</button></a></li>';
                var headerElement = angular.element(headerHtml);
                $element.find("#pageTab").append(headerElement);
                $compile(headerElement)($scope);

                var bodyHtml = '<div style="width: 100%; height: 100%; padding: 10px" class="tab-pane" id="tabContent_' + name + '">' + '<'+directive +'></'+ directive + '>' + '</div>';
                console.log(bodyHtml);
                var bodyElement = angular.element(bodyHtml);
                $element.find("#pageTabContent").append(bodyElement);
                $compile(bodyElement)($scope);
            }
            $element.find("#tabHeader_" + name + " a").click();

            store.recalcTabContentHeight();
        };

        store.removeTab = function (name) {
            var previous;
            var currentHeader = $element.find("#tabHeader_" + name)[0];
            var isCurrentActive = $element.find("#pageTab li.active")[0] === currentHeader;
            if (isCurrentActive) {
                var allHeaders = $element.find("#pageTab li");
                for (var i = 1; i < allHeaders.size(); i++) {
                    if (allHeaders[i] === currentHeader) {
                        previous = allHeaders[i - 1];
                    }
                }
            }

            $element.find("#tabHeader_" + name).remove();
            $element.find("#tabContent_" + name).remove();

            if (isCurrentActive) {
                previous.firstChild.click();
            }

            store.recalcTabContentHeight();
        };

        store.recalcTabContentHeight = function () {
            var tabHeight = $element.find("#pageTab")[0].scrollHeight;
            $element.find("#pageTabContent").css("height", "calc(100% - " + tabHeight + "px)");
        };

    });




})();


// var AngularSpringApp = {};
//
// var App = angular.module('AngularSpringApp', ['AngularSpringApp.filters', 'AngularSpringApp.services', 'AngularSpringApp.directives']);
//
// App.config(['$routeProvider', function ($routeProvider) {
//     $routeProvider.when('/cars', {
//         templateUrl: 'cars/layout',
//         controller: CarController
//     });
//
//     $routeProvider.when('/employees', {
//         templateUrl: 'employees/layout',
//         controller: EmpController
//     });
//
//     $routeProvider.when('/trains', {
//         templateUrl: 'trains/layout',
//         controller: TrainController
//     });
//
//     $routeProvider.when('/railwaystations', {
//         templateUrl: 'railwaystations/layout',
//         controller: RailwayStationController
//     });
//
//     $routeProvider.when('/notebooks', {
//         templateUrl: 'notebook/layout',
//         controller: NotebookController
//     });
//
//     $routeProvider.when('/accesscards', {
//         templateUrl: 'accesscards/layout',
//         controller: AccessCardController
//     });
//
//     $routeProvider.when('/users', {
//         templateUrl: 'user/layout',
//         controller: UserController
//     });
//
//     $routeProvider.when('/orders', {
//         templateUrl: 'order/layout',
//         controller: OrderController
//     });

    // $routeProvider.when('/invstats', {
    //     templateUrl: 'invstats/layout',
    //     controller: InvStatusController
    // });
    //
    // $routeProvider.when('/ordstats', {
    //     templateUrl: 'ordstats/layout',
    //     controller: OrdStatusController
    // });

//
//     $routeProvider.otherwise({redirectTo: '/employees'});
// }]);
