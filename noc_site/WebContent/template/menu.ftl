[#ftl/]
[#macro typeinfo tp]
    [#if tp.name?matches("noc.lang[.].*")]
       &lt; ${tp.name?replace("noc.lang.", "")}
    [#else]
        &lt; <a href="${contextPath}/${tp.name?replace(".", "/")}/">${tp.name}<a>
    [/#if]
[/#macro]

<html><head>
</head><body class="nav">
<div class="navbox"> 
<ul class="nav">
    <#list data as item><li>
        <a href="${r"${item.indentify}"}">${r"${item."+ type.primaryKeyField.name + "}"}</a>
       </li>
    </#list> 
</ul>   
</div>
</body></html>