<template>
  <div id="ws">
    <div>
      <button class="connect" v-on:click="connect">connect</button>
      <button class="disconnect" v-on:click="disconnect">disconnect</button>
    </div>
    <div class="conversationDiv">
      <label>input Message:</label><input type="text" class="name"/>
      <button class="sendName" v-on:click="send">send</button>
      <p class="response"></p>
    </div>
  </div>
</template>
<script>
  import Vue from 'vue'

  var stompClient;
  export default {
    name : 'wsock',
    methods: {
      connect: function () {
        var socket = new SockJS('/endpointWisely');
        stompClient = Stomp.over(socket);
        stompClient.connect({},function(frame){
          setConnected(true);
          console.log('Connected:' + frame);
          stompClient.subscribe('/topic/getResponse',function (response) {
            showResponse(JSON.parse(response.body).responseMessage);
          })
        })
      },
      disconnect: function () {
        !!stompClient && stompClient.disconnect();
        setConnected(false);
      },
      send: function () {
        stompClient.send("/sendMessage",{},JSON.stringify({'name':$('.name').val()}))
      }
    }
  }

  function setConnected(flag){
    $('.connect')[0].disabled = flag;
    $('.disconnect')[0].disabled = !flag;
    $('.conversationDiv')[0].style.visibility = flag ? 'visible' : 'hidden';
    $('.response').html();
  }
  function showResponse(message) {
    $('.response').html(message);
  }
</script>
