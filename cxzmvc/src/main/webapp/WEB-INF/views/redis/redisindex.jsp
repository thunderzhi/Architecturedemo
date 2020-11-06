<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="stc" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${stc}/css/test.css" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/test.css"/> " />
    <script type="text/javascript" src="${stc}/js/vue.min.js" ></script>
    <script src="${stc}/js/jquery.min.js"></script>
    <script src="${stc}/js/axios.min.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui@2.10.1/lib/index.js"></script>

</head>
<h2>redisIndex</h2>
<div id = "app">
    <p>input 元素：</p>
    <input v-model="message" placeholder="编辑我……">
    <el-input v-model="input" placeholder="请输入内容"></el-input>
    <el-row>
        <el-button @click="btn1" >默认按钮</el-button>

    </el-row>
    {{info}}
</div>
<body>
<p>redisIndex success!</p>
<script>
    new Vue({
        el: '#app',
        data: {
            message: 'red',
            input: 'redddd',
            info:''
        },
        methods:{
            btn1:function(){
                axios
                    .get('${stc}/rk')
                    .then(response => (this.info = response.data))
                    .catch(function (error) { // 请求失败处理
                        console.log(error);
                    });
              console.log("dddd");
            }
        }
    })
</script>
</body>
</html>