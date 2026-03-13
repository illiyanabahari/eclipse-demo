<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8" />
 <title>RabbitMQ Live Messages (JSP + WebSocket)</title>
 <style>
   body { font-family: Arial, sans-serif; margin: 20px; }
   #log { border: 1px solid #ccc; padding: 10px; height: 300px; overflow: auto; white-space: pre-wrap; }
   .row { margin-bottom: 10px; }
   input[type=text] { width: 420px; padding: 8px; }
   button { padding: 8px 12px; }
 </style>
</head>
<body>
 <h2>RabbitMQ Live Messages</h2>
 <div class="row">
   <input id="txtMessage" type="text" placeholder="Type a message and click Publish..." />
   <button onclick="publishMsg()">Publish</button>
 </div>
 <div class="row">
   <strong>Status:</strong> <span id="status">Connecting...</span>
 </div>
 <div id="log"></div>
 <script>
   const log = document.getElementById("log");
   const statusEl = document.getElementById("status");
   function appendLine(text) {
     log.textContent += text + "\n";
     log.scrollTop = log.scrollHeight;
   }
   // Build WebSocket URL: ws://host:port/<context>/live
   const ctx = "<%= request.getContextPath() %>";
   const wsUrl = (location.protocol === "https:" ? "wss://" : "ws://") + location.host + ctx + "/live";
   const ws = new WebSocket(wsUrl);
   ws.onopen = () => {
     statusEl.textContent = "Connected (" + wsUrl + ")";
     appendLine("[System] WebSocket connected");
   };
   ws.onmessage = (event) => {
     appendLine(event.data);
   };
   ws.onclose = () => {
     statusEl.textContent = "Disconnected";
     appendLine("[System] WebSocket disconnected");
   };
   ws.onerror = () => {
     statusEl.textContent = "Error";
     appendLine("[System] WebSocket error occurred");
   };
   async function publishMsg() {
     const txt = document.getElementById("txtMessage");
     const message = txt.value.trim();
     if (!message) return;
     const form = new URLSearchParams();
     form.append("message", message);
     const res = await fetch(ctx + "/publish", {
       method: "POST",
       headers: { "Content-Type": "application/x-www-form-urlencoded" },
       body: form.toString()
     });
     if (res.ok) {
       txt.value = "";
     } else {
       const err = await res.text();
       appendLine("[Publish Error] " + err);
     }
   }
 </script>
</body>
</html>
