var WebSocketStompClient = function(topicName, onMessage) {
	this.serverEndpoint = "stomp";
	this.topicName = topicName;
	this.onMessage = onMessage;
	this.stompClient = null;
}

WebSocketStompClient.prototype.connect = function() {
	var socket = new SockJS(getContextRoot() + "/" + this.serverEndpoint);
	this.stompClient = Stomp.over(socket);
	var caller = this;
	this.stompClient.connect({}, function(frame) {
		console.log("websocket - Connected ? " + frame);
		caller.stompClient.subscribe("/topic/" + caller.topicName, caller.onMessage);
	});
}

WebSocketStompClient.prototype.disconnect = function() {
	if (this.stompClient != null) {
		this.stompClient.disconnect();
	}
	console.log("websocket - Disconnected");
}

WebSocketStompClient.prototype.sendMessage = function(msg) {
	this.stompClient.send("/app/" + this.topicName, {}, msg);
}
