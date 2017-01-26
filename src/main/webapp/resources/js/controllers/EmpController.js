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
        $scope.sendEmail = function (email) {
            $http.post('mail/send/',email).success(function () {});
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



    _this.openEditor = function (templ) {
        var editRec = {};
        editRec.email=templ.eMail;
        var uibModalInstance = $uibModal.open({
            animation: true,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: 'updateNotifTemplate.html',
            controller: 'updateNotifTemplateController',
            resolve: {
                editRecord: function () {
                    return editRec;
                }
            }
        });

        uibModalInstance.result.then(function (editRec) {
            //modal ok
            if (editRec) {
                $scope.sendEmail(editRec);
            }
            else {
                $scope.sendEmail(editRec);
                // TODO: Call insert function()
            }
        }, function () {
            // modal cancel
        });
    };

    $scope.openUpdateEditor = function (templ) {
        _this.openEditor(templ);
    };
    });

    app.controller('updateNotifTemplateController', ['$scope','$uibModalInstance', 'editRecord', function ($scope, uibModalInstance, editRec) {
        $scope.editRecord = editRec;

        $scope.ok = function () {
            // if (validation)
            uibModalInstance.close($scope.editRecord);
            // else
            //   show error msg
        };

        $scope.close = function () {
            uibModalInstance.dismiss('cancel');
        };
    }]);

    app.directive("employeesList", function () {
        return {
            templateUrl: "employees/layout.html"
        }
    });

})();