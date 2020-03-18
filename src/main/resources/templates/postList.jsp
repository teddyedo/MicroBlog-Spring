<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Lista dei post</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

        <link
            rel="stylesheet"
            type="text/css"
            href="vendor/bootstrap/css/bootstrap.min.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            href="fonts/font-awesome-4.7.0/css/font-awesome.min.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            href="fonts/iconic/css/material-design-iconic-font.min.css"
            />
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css" />
        <link
            rel="stylesheet"
            type="text/css"
            href="vendor/css-hamburgers/hamburgers.min.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            href="vendor/animsition/css/animsition.min.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            href="vendor/select2/select2.min.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            href="vendor/daterangepicker/daterangepicker.css"
            />
        <link rel="stylesheet" type="text/css" href="css/util.css" />
        <link rel="stylesheet" type="text/css" href="css/main.css" />

    </head>
    <body>
        


        <div class="limiter">
          
            <div class="container-login100">
                <div class="wrap-login100 p-t-85 p-b-20">
                    <span class="login100-form-title p-b-30">
                        Benvenuto nella lista dei post
                    </span>
                    <form action="ControllaCreatorPost">
                        <button type="submit" class="login100-form-btn" id="button3">
                            Crea nuovo post
                        </button>
                    </form>
                    <br />
                    <p
                        style="font-size: 18px; font-family: Poppins-Regular; text-align: center;"
                        >
                        oppure
                    </p>
                    <br />
                    <button
                        type="submit"
                        class="login100-form-btn"
                        id="button3"
                        onclick="location.href = '#firstComment';"
                        >
                        Vedi i post già creati
                    </button>
                </div>
            </div>

            <a id="firstComment"></a>
            <div class="container-login100 row-cols-4">
                <%@ page import="entity.*" %> 
                <%@ page import="DAO.*" %> 
                <%@ page import="java.util.List" %>

                <% List<Post> postList = (List<Post>) request.getAttribute("listaPost"); %>

                <% for (int i = 0; i < postList.size(); i++) { %>  
                <% Post p = postList.get(i); %>

                <div class="wrap-login100 p-t-85 p-b-20 p-r-20 m-r-50 m-t-40">
                    <h1 class="titoloCard"><% out.print(p.getTitolo()); %></h1>
                    <br />
                    <p class="testoCard"> <% out.print(p.getUtente().getUsername() + " " + p.getDataOra().toString()); %></p>
                    <p class="testoCard"> <% out.print(p.getTesto()); %> </p>
                    <br /><br />
                    <hr />
                    <br />
                    <h4 class="commentoCard">Commenti</h4>
                    <br />
                    <div class="commenti">

                        <% List<Commento> commentiList = (List<Commento>) CommentoJpaController.findCommentoByPost(p);%>
                        <% for (int j = 0; j < commentiList.size(); j++) { %>
                        <% Commento c = commentiList.get(j); %>
                        <div class="commento">
                            <h5 class="titoloCommento"><% out.print(c.getTitolo()); %></h5>
                            <br />
                            <p class="testoCommento"> <% out.print(c.getUtente().getUsername() + " " + c.getDataOra().toString()); %></p>
                            <p class="testoCommento"> <% out.print(c.getTesto()); %></p>
                            <br />
                            <hr />
                            <br />
                        </div>
                        <% } %>
                    </div>
                    <form action="ControllaCreatorCommento">
                    <button
                        type="submit"
                        class="login100-form-btn"
                        name="<% out.print(p.getId()); %>"
                        >
                        Commenta
                    </button>
                    </form>    
                </div>

                <% }%>


            </div>
        </div>

        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendor/animsition/js/animsition.min.js"></script>
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="vendor/select2/select2.min.js"></script>
        <script src="vendor/daterangepicker/moment.min.js"></script>
        <script src="vendor/daterangepicker/daterangepicker.js"></script>
        <script src="vendor/countdowntime/countdowntime.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
