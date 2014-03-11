<%@page session="false"%>
<%@page import="java.net.*,java.io.*" %>
<%
    if("GET".equals(request.getMethod())){
        //execute the GET
        String serverUrl= org.apache.commons.httpclient.util.URIUtil.decode(request.getParameter("url"));
        java.net.URL url= new java.net.URL(serverUrl);
        if (!"http".equals(url.getProtocol())) {
            throw new javax.servlet.ServletException(
                    "only use HTTP Url's, please don't hack this server!");
        }
        java.io.InputStream in= url.openStream();

        response.setContentType("text/xml");
        byte[] buff= new byte[1024];
        int count;
        java.io.OutputStream o= response.getOutputStream();
        while ((count= in.read(buff)) > -1) {
            o.write(buff, 0, count);
        }
        o.flush();
        o.close();
    }else{
        //execute the POST
        try {
            // Transfer bytes from in to out
            java.io.PrintWriter o= response.getWriter();
            java.io.InputStream in= request.getInputStream();

            String serverUrl= org.apache.commons.httpclient.util.URIUtil.decode(request.getParameter("url"));

            org.apache.commons.httpclient.HttpClient client= new org.apache.commons.httpclient.HttpClient();
            org.apache.commons.httpclient.methods.PostMethod httppost=
                new org.apache.commons.httpclient.methods.PostMethod(serverUrl);

            String referrer= request.getHeader("referer");
            if (referrer!=null) {
                org.apache.commons.httpclient.Header h= new org.apache.commons.httpclient.Header("referer", referrer);
                httppost.setRequestHeader("referer", referrer);
            }
            String ct= request.getHeader("Content-Type");
            if (ct==null) {
                ct= "application/xml";
            }
            httppost.setRequestHeader("Content-Type", ct);

            httppost.setRequestEntity(new org.apache.commons.httpclient.methods.InputStreamRequestEntity(in));
            String proxyHost = System.getProperty("http.proxyHost");
            String proxyPort = System.getProperty("http.proxyPort");

            client.getHostConfiguration().setProxy(proxyHost,Integer.parseInt(proxyPort));
            client.executeMethod(httppost);

            int code= httppost.getStatusCode();
            if ((code >= org.apache.commons.httpclient.HttpStatus.SC_OK &&
                  code <  org.apache.commons.httpclient.HttpStatus.SC_MULTIPLE_CHOICES)) {
                response.setContentType("text/xml");
                String responseBody= httppost.getResponseBodyAsString();
                response.setContentLength(responseBody.length());
                o.print( responseBody );
            } else {
                throw new javax.servlet.ServletException("Unexpected failure: " + httppost.getStatusLine().toString());
            }
            httppost.releaseConnection();
            o.flush();
            o.close();
        } catch (java.io.IOException e) {
            throw new javax.servlet.ServletException(e);
        }
    }
%>