<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="stc" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Vue 测试实例</title>
    <link rel="stylesheet" type="text/css" href="${stc}/css/test.css" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/test.css"/> " />
    <script type="text/javascript" src="${stc}/js/vue.min.js" ></script>
    <script src="${stc}/js/axios.min.js"></script>
</head>
<body>
fffffffffffff
<h2>${msg}</h2>
<div id="app">
    {{ info.success }}
</div>

<script type = "text/javascript">
    new Vue({
        el: '#app',
        data () {
            return {
                info: null
            }
        },
        mounted () {
            axios
                .get('rk')
                .then(response => (this.info = response.data))
                .catch(function (error) { // 请求失败处理
                    console.log(error);
                });
        }
    })
</script>
</body>
</html>