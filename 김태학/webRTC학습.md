## Openvidu Hello world 코드 해석

```js
// 변수 선언
var OV;
var session;

// 첫 화면에서 join 버튼으로 joinSession() 실행
function joinSession() {
    
    // 첫 화면에서 입력한 session값 mySessionId에 할당
	var mySessionId = document.getElementById("sessionId").value;
    
    // OpenVidu 객체 생성 , 세션시작
	OV = new OpenVidu();
	session = OV.initSession();
	
    // 세션에서 발생하는 이벤트 구독
	session.on("streamCreated", function (event) {
		session.subscribe(event.stream, "subscriber");
	});
	
    // 토큰 얻는 함수 (맨 아래에 선언되어 있음)
	getToken(mySessionId).then(token => {
		
        // 얻은 토큰으로 연결
		session.connect(token)
        
			.then(() => {
            	
            	// 화면 변경
				document.getElementById("session-header").innerText = mySessionId;
				document.getElementById("join").style.display = "none";
				document.getElementById("session").style.display = "block";

				// 웹캠을 키고 화면에 보여줌
				var publisher = OV.initPublisher("publisher");
            	
            	// 세션에 연결된 유저들의 streamCreated 이벤트를 발생시키고 내 웹캠을 보여줌
				session.publish(publisher);
			})
			.catch(error => {
				console.log("There was an error connecting to the session:", error.code, error.message);
			});
	});

}

function leaveSession() {
    
    // 연결해제
	session.disconnect();
	document.getElementById("join").style.display = "block";
	document.getElementById("session").style.display = "none";
}

// 창을 꺼버릴 경우에 연결해제
window.onbeforeunload = function () {
	if (session) session.disconnect();
};


/**
 * --------------------------------------------
 * GETTING A TOKEN FROM YOUR APPLICATION SERVER
 * --------------------------------------------
 * The methods below request the creation of a Session and a Token to
 * your application server. This keeps your OpenVidu deployment secure.
 * 
 * In this sample code, there is no user control at all. Anybody could
 * access your application server endpoints! In a real production
 * environment, your application server must identify the user to allow
 * access to the endpoints.
 * 
 * Visit https://docs.openvidu.io/en/stable/application-server to learn
 * more about the integration of OpenVidu in your application server.
 */

var APPLICATION_SERVER_URL = "http://localhost:5000/";

function getToken(mySessionId) {
	return createSession(mySessionId).then(sessionId => createToken(sessionId));
}

function createSession(sessionId) {
	return new Promise((resolve, reject) => {
		$.ajax({
			type: "POST",
			url: APPLICATION_SERVER_URL + "api/sessions",
			data: JSON.stringify({ customSessionId: sessionId }),
			headers: { "Content-Type": "application/json" },
			success: response => resolve(response), // The sessionId 를 얻게된다
			error: (error) => reject(error)
		});
	});
}

function createToken(sessionId) {
	return new Promise((resolve, reject) => {
		$.ajax({
			type: 'POST',
			url: APPLICATION_SERVER_URL + 'api/sessions/' + sessionId + '/connections',
			data: JSON.stringify({}),
			headers: { "Content-Type": "application/json" },
			success: (response) => resolve(response), // The token 를 얻게된다
			error: (error) => reject(error)
		});
	});
}
```

