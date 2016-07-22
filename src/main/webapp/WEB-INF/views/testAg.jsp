<%--
  Created by IntelliJ IDEA.
  User: ZhaoTao
  Date: 2016/5/25
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://libs.useso.com/js/angular.js/1.2.5/angular.min.js"></script>
<script src="http://libs.useso.com/js/angular.js/1.2.5/angular-route.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div ng-app="testApp">
    <div>
        <ul>
            <li><a href="#/TABone">TABone</a></li>
            <li><a href="#/TABTwo">TABTwo</a></li>
        </ul>
    </div>
    <div id="show"  ng-view>
    </div>
</div>
</body>

<script>
    var app = angular.module('testApp', ['ngRoute']);
    function routeConfig($routeProvider){
        $routeProvider.
        when('/', {
            controller: 'OneController',
            templateUrl: '../html/index.html'
        }).
        when('/TABone', {
            controller: 'OneController',
            templateUrl: '../html/404.html'
        }).
        when('/TABTwo', {
            controller: 'TwoController',
            template : '<button ng-click="add()"> aaaa</button>'
        })
        .otherwise({
            redirectTo: '/'
        });
    };
    app.config(routeConfig);

    app.controller('OneController',function($scope){

       /* $scope.ttt = "ttttttttttttttttttONe"*/
    });
    app.controller('TwoController',function($scope){
        $scope.add = function(){
            alert(1)
        }
       /* $scope.ttt = "ttttttttttttttttttwo"*/
    });

</script>



</html>
