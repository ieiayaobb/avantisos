var menuServices = angular.module('menuServices', ['ngResource']);

// Service that provides persons operations
menuServices.factory('menu',  ['$resource',
    function ($resource) {
        return $resource(window._contextPath + '/sys/menu/treeData', {}, {
            get:    {method: 'GET'},
            update: {method: 'POST'}
        });
    }
]);