'use strict';

/* Directives */

var AppDirectives = angular.module('AngularSpringApp.directives', []);

AppDirectives.directive('appVersion', ['version', function (version) {
    return function (scope, elm, attrs) {
        elm.text(version);
    };
}]);

AppDirectives.directive("matchPassword", function () {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=matchPassword"
        },
        link: function(scope, element, attributes, ngModel) {

            ngModel.$validators.matchPassword = function(modelValue) {
                return modelValue == scope.otherModelValue;
            };

            scope.$watch("otherModelValue", function() {
                ngModel.$validate();
            });
        }
    };
});

AppDirectives.directive('uniqueEmail', function($http) {
    var toId;
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, elem, attr, ctrl) {
            scope.$watch(attr.ngModel, function(value) {
                if(toId) clearTimeout(toId);
                toId = setTimeout(function(){
                    $http.get('/user/checkEmail?email=' + value).success(function(data) {
                        ctrl.$setValidity('uniqueEmail', !data);
                    });
                }, 1000);
            })
        }
    }
});

AppDirectives.directive('uniqueUpdateEmail', function($http) {
    var toId;
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, elem, attr, ctrl) {
            scope.$watch(attr.ngModel, function(value) {
                var oldValue = $('#update-user-old-email').val();
                if (oldValue != value) {
                    if(toId) clearTimeout(toId);
                    toId = setTimeout(function(){
                        $http.get('/user/checkEmail?email=' + value).success(function(data) {
                            ctrl.$setValidity('uniqueUpdateEmail', !data);
                        });
                    }, 1000);
                }
            })
        }
    }
});