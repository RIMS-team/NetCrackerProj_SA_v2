/**
 * Created by barmin on 13.01.2017.
 */
'use strict';

/**
 * OrdStatusController
 * @constructor
 */

(function () {
    var modul = angular.module("ordstatuses", ["ngSanitize", "angularUtils.directives.dirPagination", "ui.bootstrap"]);

    modul.controller("OrdStatusController", function ($scope, $http, $uibModal, ordStatusService) {
        var _this = this;
        $scope.pageSize = 11;

        ordStatusService.loadList()
            .success(function(OrdStatusList){
                $scope.ordStats = ordStatusService.getList();
            }).error(function () {
                $scope.ordStats = ordStatusService.getList();
        });

        $scope.sort = function (keyname) {
            $scope.sortKey=keyname;
            $scope.reverse = !$scope.reverse;
        };

        $scope.isSortKey = function(keyname) {
            return $scope.sortKey == keyname;
        }


        $scope.addNewStatus = function (editRec) {
            $http.post('ordstats/add', editRec).success(function () {
                $scope.ordStats = ordStatusService.getList();
            }).error(function () {
                console.log("Error sending insert request!");
            });
        };

        $scope.updateStatus = function (editRec) {
            $http.put('ordstats/update', editRec).success(function () {
                $scope.ordStats = ordStatusService.getList();
            }).error(function () {
                console.log("Error sending update request!");
            });
        };



        _this.openEditor = function (status) {
            var editRec = {};
            if (status) {
                editRec.id = status.id;
                editRec.code = status.code;
                editRec.name = status.name;
            }

            var uibModalInstance = $uibModal.open({
                animation: true,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'updateOrdStat.html',
                controller: 'UpdateOrdStatusController',
                resolve: {
                    editRecord: function () {
                        return editRec;
                    }
                }
            });

            uibModalInstance.result.then(function (editRec) {
                //modal ok
                if (status) {
                    $scope.updateStatus(editRec);
                }
                else {
                    $scope.addNewStatus(editRec);
                }
            }, function () {
                // modal cancel
            });
        };

        $scope.openUpdateEditor = function (status) {
            _this.openEditor(status);
        };

        $scope.openInsertEditor = function () {
            _this.openEditor(null);
        };

    });

    modul.directive("ordstatusesList", function () {
        return {
            templateUrl: "ordstats/layout.html"
        }
    });

    modul.controller('UpdateOrdStatusController', ['$scope','$uibModalInstance', 'editRecord', function ($scope, uibModalInstance, editRec) {
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

})();

