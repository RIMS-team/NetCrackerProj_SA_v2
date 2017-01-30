'use strict';

/**
 * NotebookController
 * @constructor
 */


(function () {
    var app = angular.module("notifications", ["ngSanitize","angularUtils.directives.dirPagination", "ui.bootstrap"]);

    app.controller("NotificationController", function ($scope, $http, $uibModal, ordNotifyService) {
        var _this = this;

        //ordNotifyService.loadNotifiTempList();

        $scope.pageSize = 11;
        $scope.editRecord;
        // var settings={}
        // settings.notif_num=1;
        // settings.user_id=0;
        // settings.template='';
        // $scope.temp_1;
        // ordNotifyService.findNotifiTemp1(settings).success(function () {
        //     $scope.temp_1=ordNotifyService.getNotifi();
        // });
        // $scope.id;
        // settings.notif_num=2;
        // ordNotifyService.findNotifiTemp1(settings).success(function () {
        //     $scope.id=ordNotifyService.getNotifi();
        // });
        // $scope.num;
        // settings.notif_num=3;
        // ordNotifyService.findNotifiTemp1(settings).success(function () {
        //     $scope.num=ordNotifyService.getNotifi();
        // });


        ordNotifyService.loadUserList();

        $scope.fetchNotificationList = function() {
            $http.get('notification/all').success(function(notificationList){
                $scope.notifications = notificationList;
            });
        };

        $scope.submitUpdate = function(notifi) {
            $scope.updateNotification(notifi);
        };

        $scope.updateNotification = function(notifi) {
            $http.put('notification/update', notifi).success(function() {
                var editRec={}
                var settings1={}
                settings1.notif_num=1;
                settings1.user_id=0;
                settings1.template='';
                var settings2={}
                settings2.notif_num=2;
                settings2.user_id=0;
                settings2.template='';
                var settings3={}
                settings3.notif_num=3;
                settings3.user_id=0;
                settings3.template='';
                ordNotifyService.findNotifiTemp1(settings1).success(function () {
                    editRec.temp_1='Dear !name! , '+ordNotifyService.getNotifi()+
                        ' inventory: !number inventory!  !type inventory! date: !dd/mm/yy!';
                });
                ordNotifyService.findNotifiTemp1(settings2).success(function () {
                    editRec.id='Dear !name! , '+ordNotifyService.getNotifi()+
                        ' inventory: !number inventory!  !type inventory! date: !dd/mm/yy!';
                });
                ordNotifyService.findNotifiTemp1(settings3).success(function () {
                    editRec.num='Dear !name! , '+ordNotifyService.getNotifi()+
                        ' inventory: !number inventory!  !type inventory! date: !dd/mm/yy!';
                });
                $scope.editRecord=editRec;

            }).error(function() {
                console.log("Error sending update request");
            });
        };

        $scope.fetchNotificationList();

        $scope.insertNewTemplete = function (editRec) {
            $http.post('notification/insert', editRec).success(function () {
            }).error(function () {
                console.log("Error sending insert request!");
            });
        };

        $scope.sort = function (keyname) {
            $scope.sortKey=keyname;
            $scope.reverse=!$scope.reverse;
        };

        $scope.isSortKey = function(keyname) {
            return $scope.sortKey == keyname;
        };

        $scope.startsWith = function (actual, expected) {
            var lowerStr = (actual + "").toLowerCase();
            return lowerStr.indexOf(expected.toLowerCase()) === 0;
        };

        _this.openEditor = function (templ,temp_id) {
            var editRec = {};
            var settings = {};
            if (templ) {
                editRec.notif_num = temp_id;
                editRec.user_id = ordNotifyService.getEmployeeIdByOrderId(templ.order.id);
                editRec.name =templ.name;
                editRec.inv_num = templ.inv_num;
                editRec.inv_type = templ.inv_type;
                editRec.dueDate = templ.dueDate;
                settings.notif_num=editRec.notif_num;
                settings.user_id=editRec.user_id;
                settings.template='';
                ordNotifyService.findNotifiTemp1(settings).success(function () {
                    editRec.template ='Dear '+templ.name+', '+ordNotifyService.getNotifi()+' inventory: '+
                        templ.inv_num+' '+templ.inv_type+' date: '+templ.dueDate;
                });
                //alert(ordNotifyService.getEmployeeIdByOrderId(templ.order.id));
            }

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
                    $scope.insertNewTemplete(editRec);
                }
                else {
                    $scope.insertNewTemplete(editRec);
                    // TODO: Call insert function()
                }
            }, function () {
                // modal cancel
            });
        };

        _this.openEditorDef = function () {
            var editRec={}
            var settings1={}
            settings1.notif_num=1;
            settings1.user_id=0;
            settings1.template='';
            var settings2={}
            settings2.notif_num=2;
            settings2.user_id=0;
            settings2.template='';
            var settings3={}
            settings3.notif_num=3;
            settings3.user_id=0;
            settings3.template='';
            ordNotifyService.findNotifiTemp1(settings1).success(function () {
                editRec.temp_1='Dear !name! , '+ordNotifyService.getNotifi()+
                    ' inventory: !number inventory!  !type inventory! date: !dd/mm/yy!';
            });
            ordNotifyService.findNotifiTemp1(settings2).success(function () {
                editRec.id='Dear !name! , '+ordNotifyService.getNotifi()+
                    ' inventory: !number inventory!  !type inventory! date: !dd/mm/yy!';
            });
            ordNotifyService.findNotifiTemp1(settings3).success(function () {
                editRec.num='Dear !name! , '+ordNotifyService.getNotifi()+
                    ' inventory: !number inventory!  !type inventory! date: !dd/mm/yy!';
            });


            var uibModalInstance = $uibModal.open({
                animation: true,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'updateNotifTemplateDef.html',
                controller: 'updateNotifTemplateControllerTemp',
                resolve: {
                    editRecord: function () {
                        return editRec;
                    }
                }
            });

            uibModalInstance.result.then(function (editRec) {
                //modal ok
                    $scope.updateNotification(editRec);
            }, function () {
                // modal cancel
            });
        };

        $scope.openUpdateEditor = function (templ,temp_id) {
            _this.openEditor(templ,temp_id);
        };
        $scope.openUpdateEditorDef = function () {
            _this.openEditorDef();
        };

    });


    app.directive("notificationsList", function () {
        return {
            //restrict: "E",
            templateUrl: "notification/layout.html"
        }
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

    app.controller('updateNotifTemplateControllerTemp', ['$scope','$uibModalInstance', 'editRecord', function ($scope, uibModalInstance, editRec) {
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


