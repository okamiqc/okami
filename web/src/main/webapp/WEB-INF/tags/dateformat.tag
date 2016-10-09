<%@ tag import="java.util.Date" %>
<%@ tag import="java.text.SimpleDateFormat" %>
<%@ tag pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="d" type="java.lang.Long" required="true" %>
<%@attribute name="p" type="java.lang.String" required="false" %>
<%
    String s = "";
    if(d!=0&&d!=null&&!"".equals(d)){
        if(p==null){
            SimpleDateFormat sdf =  new SimpleDateFormat("yyyy年M月d日");
            s = sdf.format(new Date(d*1000));
        }else{
            SimpleDateFormat sdf =  new SimpleDateFormat(p);
            s = sdf.format(new Date(d*1000));
        }
    }
    request.setAttribute("s",s);
%>
${s}