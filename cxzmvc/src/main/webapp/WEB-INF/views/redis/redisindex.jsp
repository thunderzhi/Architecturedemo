<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="stc" value="${pageContext.request.contextPath}"/>
<html>
<head>

    <%--    <link rel="stylesheet" type="text/css" href="${stc}/css/test.css" />--%>
    <link rel="stylesheet" type="text/css" href="${stc}/css/css2.css" />
    <%--    <link rel="stylesheet" type="text/css" href="<c:url value="/css/test.css"/> " />--%>
    <script type="text/javascript" src="${stc}/js/vue.min.js" ></script>
    <script src="${stc}/js/jquery.min.js"></script>
    <script src="${stc}/js/axios.min.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui@2.10.1/lib/index.js"></script>
    <style>

    </style>
</head>


<h2>redisIndex</h2>
<div id = "app">
    <el-row>
        <el-col :span="24">
            <div class="grid-content ">
                <div class="panel-heading" style="font-family: Arial;font-size:26px;">
                    字符串操作
                </div>
                <%--                <input v-model="message" placeholder="编辑我……">--%>
                <%--                <el-input v-model="input" placeholder="请输入内容"></el-input>--%>
                <%--                <el-row>--%>
                <%--                    <el-button @click="btn1" >默认按钮</el-button>--%>

                <%--                </el-row>--%>
                <el-form :inline="true" :model="form1" class="demo-form-inline">
                    <el-form-item label="SetKey">
                        <el-input v-model="form1.key" placeholder="key"></el-input>

                        <el-button type="primary" @click="addstr">添加key</el-button>{{info}}
                        <el-button type="primary" @click="getstr">获取key</el-button>{{info}}
                    </el-form-item>
                    <br>
                    <el-form-item>
                        <el-input v-model="form1.key2" placeholder="key2"></el-input>
                    </el-form-item>
                </el-form>
                <el-form :inline="true" :model="form2" class="demo-form-inline">
                    <el-form-item label="SetEXPIREKey">
                        <el-input v-model="form2.key" placeholder="key"></el-input>

                        <el-button type="primary" @click="addexpstr">添加key</el-button>{{info}}
                        <el-button type="primary" @click="getstr">获取key</el-button>{{info}}
                    </el-form-item>
                </el-form>
                <el-form :inline="true" :model="form3" class="demo-form-inline">
                    <el-form-item label="INCR_DECR_EKey">
                        <el-input v-model="form3.key" placeholder="key"></el-input>

                        <el-button type="primary" @click="incrment(true)">+1</el-button>{{info}}
                        <el-button type="primary" @click="incrment(false)">-1</el-button>{{info}}
                    </el-form-item>
                </el-form>
                <el-form :inline="true" :model="form4" class="demo-form-inline">
                    <el-form-item label="LOCK">
                        <el-input v-model="form4.key" placeholder="key"></el-input>

                        <el-button type="primary" @click="lock">lock</el-button>{{form4.lockres}}
                        <el-button type="primary" @click="unlock">unlock</el-button>{{form4.unlockres}}
                        <el-button type="primary" @click="exp">exp</el-button>TTL{{form4.ttl}}
                    </el-form-item>
                </el-form>
            </div>
        </el-col>
    </el-row>
    <el-row>
        <el-col :span="24">
            <div class="grid-content ">
                <div class="panel-heading" style="font-family: Arial;font-size:26px;">
                    List操作
                </div>
                <el-form :inline="true" :model="formListInline" class="demo-form-inline">
                    <el-form-item label="SetKey">
                        <el-input v-model="formListInline.key" placeholder="key"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="addstr2">添加key</el-button>{{info}}
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

            form1: {
                key: '',
                key2: '',
            },
            form2: {
                key: '',
                key2: '',
            },
            form3: {
                key: '',
            },
            form4: {
                key: '',
                ttl:'',
                lockres:'',
                unlockres:''
            },
            formListInline: {
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
            addstr() {
                if (this.form1.key.length<=0){
                    console.log("empty");
                    return;
                }
                var data1 = new FormData();
                data1.append("key",this.form1.key);// this is fine
                data1.append("key2",this.form1.key2);// this is fine
                axios
                    .post('set',data1
                        //{"key":this.form1.key}//this is error
                        //{ headers: { "Content-Type": "application/x-www-form-urlencoded" }   }

                    )
                    //  axios({
                    //     method:'post',
                    //     url:'set',
                    //     data:{key:this.formInline.key}
                    //
                    // })
                    .then(function(response){
                        this.info = response.data
                    }).catch(function (error) {
                    console.log(error);
                });

                console.log('submit!');
            },
            addexpstr() {
                if (this.form2.key.length<=0){
                    console.log("empty");
                    return;
                }
                var data = new FormData();
                data.append("key",this.form2.key);
                axios
                    .post('expstr',data)
                    .then(function(response){
                        this.info = response.data
                    }).catch(function (error) {
                    console.log(error);
                });
                console.log('submit!');
            },
            incrment(incr){
                if (this.form3.key.length<=0){
                    console.log("empty");
                    return;
                }
                var data = new FormData();
                data.append("key",this.form3.key);
                data.append("isincr",incr);
                axios
                    .post('incrment',data)
                    .then(function(response){
                        this.info = response.data
                    }).catch(function (error) {
                    console.log(error);
                });
                console.log('submit!');
            },
            addstr2() {
                if (this.formListInline.key.length<=0){
                    console.log("empty");
                    return;
                }

                //var data1 =JSON.stringify();
                $.ajax({
                    type: "POST",
                    url: "set",
                    contentType: "application/json", //必须有
                    //dataType: "json", //表示返回值类型，不必须
                    data: "{ 'key': '1' }",  //相当于 //data: "{'str1':'foovalue', 'str2':'barvalue'}",
                    success: function (jsonResult) {
                        alert(jsonResult);
                    }
                });
                // axios
                //     .post('set',
                //         data1
                //     )
                //     .then(function(response){
                //         this.info = response.data
                //     }).catch(function (error) {
                //     console.log(error);
                // });

                console.log('submit!');
            },
            getstr(){
                if (this.form1.key.length<=0){
                    console.log("empty");
                    return;
                }
                var key= this.form1.key;
                axios
                    .get('getkey?key='+key)
                    .then(response => (this.info = response.data))
                    .catch(function (error) { // 请求失败处理
                        console.log(error);
                    });
            },

            lock(){
                axios
                    .get('lock')
                    .then(response => (this.form4.lockres = response.data))
                    .catch(function (error) { // 请求失败处理
                        console.log(error);
                    });
            },
            unlock(){
                axios
                    .get('unlock')
                    .then(response => (this.form4.unlockres = response.data))
                    .catch(function (error) { // 请求失败处理
                        console.log(error);
                    });
            },

            exp(){
                axios
                    .get('getexp')
                    .then(response => (this.form4.ttl = response.data))
                    .catch(function (error) { // 请求失败处理
                        console.log(error);
                    });
            },
        }
    })
</script>

</body>

</html>