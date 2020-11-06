<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="stc" value="${pageContext.request.contextPath}"/>
<html>
<head>

    <%--    <link rel="stylesheet" type="text/css" href="${stc}/css/test.css" />--%>
    <%--    <link rel="stylesheet" type="text/css" href="<c:url value="/css/test.css"/> " />--%>
    <script type="text/javascript" src="${stc}/js/vue.min.js" ></script>
    <script src="${stc}/js/jquery.min.js"></script>
    <script src="${stc}/js/axios.min.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui@2.10.1/lib/index.js"></script>
    <style>
        .el-row {
            margin-bottom: 20px;
        &:last-child {
             margin-bottom: 0;
         }
        }
        .el-col {
            border-radius: 4px;
        }
        .bg-purple-dark {
            background: #99a9bf;
        }
        .bg-purple {
            background: #d3dce6;
        }
        .bg-purple-light {
            background: #e5e9f2;
        }
        .grid-content {
            border-radius: 4px;
            min-height: 36px;
        }
        .row-bg {
            padding: 10px 0;
            background-color: #f9fafc;
        }
    </style>
</head>


<h2>redisIndex</h2>
<div id = "app">
    <el-row>
        <el-col :span="24">
            <div class="grid-content ">
                <div class="panel-heading">
                    字符串操作
                </div>
                <p>input 元素：</p>
                <input v-model="message" placeholder="编辑我……">
                <el-input v-model="input" placeholder="请输入内容"></el-input>
                <el-row>
                    <el-button @click="btn1" >默认按钮</el-button>

                </el-row>
                <el-form :inline="true" :model="formInline" class="demo-form-inline">
                    <el-form-item label="SetKey">
                        <el-input v-model="formInline.key" placeholder="key"></el-input>
                    </el-form-item>
                    <%--                    <el-form-item label="活动区域">--%>
                    <%--                        <el-select v-model="formInline.region" placeholder="活动区域">--%>
                    <%--                            <el-option label="区域一" value="shanghai"></el-option>--%>
                    <%--                            <el-option label="区域二" value="beijing"></el-option>--%>
                    <%--                        </el-select>--%>
                    <%--                    </el-form-item>--%>
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">添加key</el-button>{{info}}
                    </el-form-item>
                </el-form>

            </div>
        </el-col>
    </el-row>

</div>
<body>
<p>redisIndex success!</p>
<script>
    new Vue({
        el: '#app',
        data: {
            message: 'red',
            input: 'redddd',
            info:'',
            formInline: {
                key: '',
                region: ''
            }
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
            },
            onSubmit() {
                if (this.formInline.key.length<=0){
                    console.log("empty");
                    return;
                }
                axios.default.headers.post['Content-Type']='application/x-www-form-urlencoded';
                axios({
                    method:'post',
                    url:'set',
                    data:{key:this.formInline.key}

                }).then(function(response){
                    this.info = response.data
                }).catch(function (error) {
                    console.log(error);
                });

                console.log('submit!');
            }
        }
    })
</script>

</body>

</html>