var menuControllers = angular.module('menuControllers', []);

menuControllers.controller('menusListController', ['$scope', 'menu',
    function($scope, menu) {
        $scope.menus = menu.query();
    }]);