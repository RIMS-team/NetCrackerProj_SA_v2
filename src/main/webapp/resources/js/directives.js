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
    return {
        restrict: 'A',
        require: 'ngModel,^form',
        link: function (scope, element, attrs, ngModel) {
            element.bind('blur', function (e) {
                ngModel.$loading = true;
                $http.get("/user/checkEmail/" + element.val()).success(function(data) {
                    ngModel.$loading = false;
                    ngModel.$setValidity('unique', !data);
                });
            });
        }
    };
})