<%-- 
    Document   : index
    Created on : 29/08/2010, 16:35:47
    Author     : Mike
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <link rel="stylesheet" type="text/css" media="screen" href="agGraph.css" />
        <script type='text/javascript' src='prototype.js'></script>
        <script type='text/javascript' src='graphM.prototype.js'></script>

        <script type='text/javascript'>

            var graph1={
                divID:'resultDiv1',
                ShowNote:'yes',
                NoteTitle:'Note',
                NoteText:'Little note about this graph goes here...'
            }

            var graph2={
                divID:'resultDiv2',
                ShowNote:'yes',
                NoteTitle:'Note',
                NoteText:'Now with multiple graphs :)'
            }

        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Markowitz Solver</title>
    </head>
    <body>
        <h1>Index Page</h1>
        <input type="button" value="Generate Graph" id="startButton" onclick="draw(graph1);" />
        <div id="resultContainer">

		<h2 style="margin:10px 0px 40px 0px; border-bottom:solid 1px #414141;">Graph 1:</h2>
		<div id="resultDiv1">

		</div>


		<h2 style="margin:10px 0px 40px 0px; border-bottom:solid 1px #414141;">Graph 2:</h2>
		<div id="resultDiv2">

		</div>

	</div> 
    </body>
</html>
