/**
 * Created by barmin on 12.01.2017.
 */
'use strict';

/**
 * InvStatusController
 * @constructor
 */

(function () {
    var modul = angular.module("invstatuses", ["ngSanitize", "angularUtils.directives.dirPagination", "ui.bootstrap" ]);

    modul.controller("InvStatusController", function ($scope, $http, $uibModal, invStatusService) {
        var _this = this;
        $scope.pageSize = 11;

        invStatusService.loadList()
            .success(function(InvStatusList){
                $scope.invStats = invStatusService.getList();
            }).error(function () {
            $scope.invStats = invStatusService.getList();
        });

        $scope.sort = function (keyname) {
            $scope.sortKey=keyname;
            $scope.reverse = !$scope.reverse;
        };

        $scope.isSortKey = function(keyname) {
            return $scope.sortKey == keyname;
        }

        $scope.addNewStatus = function (editRec) {
            $http.post('invstats/add', editRec).success(function () {
                $scope.loadList();
            }).error(function () {
                console.log("Error sending insert request!");
            });
        };

        $scope.updateStatus = function (editRec) {
            $http.put('invstats/update', editRec).success(function () {
                $scope.loadList();
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
                templateUrl: 'updateInvStat.html',
                controller: 'UpdateInvStatusController',
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

    modul.directive("invstatusesList", function () {
        return {
            templateUrl: "invstats/layout.html"
        }
    });

    modul.controller('UpdateInvStatusController', ['$scope','$uibModalInstance', 'editRecord', function ($scope, uibModalInstance, editRec) {
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


