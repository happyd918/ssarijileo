// Path: front/pages/room.tsx
import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { OpenVidu } from 'openvidu-browser';
import { useRouter } from 'next/router';
import styles from '@/styles/Room.module.scss';

// import PerfectScore from '@/components/room/PerfectScore';
import RoomHeader from '@/components/room/RoomHeader';
import MainScreen from '@/components/room/MainScreen';
import MyScreen from '@/components/room/MyScreen';
// import OtherScreen from '@/components/room/OtherScreen';
import RoomFooter from '@/components/room/RoomFooter';
import Loading from '@/components/room/Loading';

const APPLICATION_SERVER_URL = 'http://localhost:5000/';

function Room() {
  // query로 전달받은 값
  const roomRouter = useRouter();
  const title = roomRouter.query.customTitle;
  console.log('title', title);

  // loading
  const mySessionId = 'sampleid';
  const myUserName = 'samplename';
  const [OV, setOV] = useState<any>(undefined);
  const [session, setSession] = useState<any>(undefined);
  const [mainStreamManager, setMainStreamManager] = useState<any>();
  const [publisher, setPublisher] = useState<any>(undefined);
  const [subscribers, setSubscribers] = useState<any[]>([]);
  const [currentVideoDevice, setCurrentVideoDevice] = useState<any>();
  const [loading, setLoading] = useState(true);
  const [init, setInit] = useState(false);
  console.log(mainStreamManager, currentVideoDevice);

  // api
  async function createSession(sessionId: string) {
    const response = await axios.post(
      `${APPLICATION_SERVER_URL}api/sessions`,
      { customSessionId: sessionId },
      {
        headers: { 'Content-Type': 'application/json' },
      },
    );
    return response.data; // The sessionId
  }

  async function createToken(sessionId: string) {
    const response = await axios.post(
      `${APPLICATION_SERVER_URL}api/sessions/${sessionId}/connections`,
      {},
      {
        headers: { 'Content-Type': 'application/json' },
      },
    );
    return response.data; // The token
  }

  async function getToken() {
    const sessionId = await createSession(mySessionId);
    const token = await createToken(sessionId);
    return token;
  }

  // joinsession
  const joinsession = () => {
    const newOV = new OpenVidu();
    const mysession = newOV.initSession();
    setOV(newOV);
    setSession(mysession);
    setInit(true);
  };

  // 참가자가 떠날때
  const deleteSubscriber = (streamManager: any) => {
    const newsubscribers = subscribers;
    const index = subscribers.indexOf(streamManager, 0);
    if (index > -1) {
      newsubscribers.splice(index, 1);
      setSubscribers(newsubscribers);
    }
  };

  // 참가자 하이라이트 화면(노래부르는 사람)
  // const handleMainVideoStream = (stream: any) => {
  //   if (mainStreamManager !== stream) {
  //     setMainStreamManager(stream);
  //   }
  // };

  // useEffect(() => {
  //   if (session !== undefined) {
  //     console.log('initsession 완료', session);
  //     const mySession = session;
  //     // subscribers에 참가자 추가
  //     mySession.on('streamCreated', (event: any) => {
  //       const subscriber = mySession.subscribe(event.stream, undefined);
  //       const newsubscribers = subscribers.push(subscriber);
  //       setSubscribers(newsubscribers);
  //       console.log('newsubs', newsubscribers);
  //     });

  //     // 참가
  //     getToken().then(token => {
  //       mySession
  //         .connect(token, { clientdata: myUserName })
  //         .then(async () => {
  //           // --- 5) Get your own camera stream ---
  //           // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
  //           // element: we will manage it on our own) and with the desired properties
  //           const newpublisher = await OV.initPublisherAsync(undefined, {
  //             audioSource: undefined, // The source of audio. If undefined default microphone
  //             videoSource: undefined, // The source of video. If undefined default webcam
  //             publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
  //             publishVideo: true, // Whether you want to start publishing with your video enabled or not
  //             resolution: '640x480', // The resolution of your video
  //             frameRate: 30, // The frame rate of your video
  //             insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
  //             mirror: false, // Whether to mirror your local video or not
  //           });

  //           // --- 6) Publish your stream ---
  //           mySession.publish(newpublisher);
  //           console.log('pub su', newpublisher);

  //           // Obtain the current video device in use
  //           const devices = OV.getDevices();
  //           const videoDevices = devices.filter(
  //             (device: any) => device.kind === 'videoinput',
  //           );
  //           const currentVideoDeviceId = newpublisher.stream
  //             .getMediaStream()
  //             .getVideoTracks()[0]
  //             .getSettings().deviceId;
  //           const newcurrentVideoDevice = videoDevices.find(
  //             (device: any) => device.deviceId === currentVideoDeviceId,
  //           );

  //           // Set the main video in the page to display our webcam and store our Publisher
  //           setCurrentVideoDevice(newcurrentVideoDevice);
  //           setMainStreamManager(newpublisher);
  //           setPublisher(newpublisher);
  //           console.log('done##');
  //         })
  //         .catch((error: any) => {
  //           console.log(
  //             'There was an error connecting to the session:',
  //             error.code,
  //             error.message,
  //           );
  //         });

  //       // 참가자가 떠날때
  //       mySession.on('streamDestroyed', (event: any) => {
  //         // Remove the stream from 'subscribers' array
  //         deleteSubscriber(event.stream.streamManager);
  //       });
  //     });
  //   }
  // }, [session]);

  useEffect(() => {
    if (init) {
      const mySession = session;
      // subscribers에 참가자 추가
      mySession.on('streamCreated', (event: any) => {
        const subscriber = mySession.subscribe(event.stream, undefined);
        const newsubscribers = subscribers;
        newsubscribers.push(subscriber);
        // setSubscribers([...subscribers, subscriber]);
        setSubscribers([...newsubscribers]);
      });

      // 참가
      getToken().then(token => {
        mySession
          .connect(token, { clientdata: myUserName })
          .then(async () => {
            // --- 5) Get your own camera stream ---
            // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
            // element: we will manage it on our own) and with the desired properties
            const newpublisher = await OV.initPublisherAsync(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: '640x480', // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            // --- 6) Publish your stream ---
            mySession.publish(newpublisher);

            // Obtain the current video device in use
            const devices = await OV.getDevices();
            const videoDevices = devices.filter(
              (device: any) => device.kind === 'videoinput',
            );
            const currentVideoDeviceId = newpublisher.stream
              .getMediaStream()
              .getVideoTracks()[0]
              .getSettings().deviceId;
            const newcurrentVideoDevice = videoDevices.find(
              (device: any) => device.deviceId === currentVideoDeviceId,
            );

            // Set the main video in the page to display our webcam and store our Publisher
            setCurrentVideoDevice(newcurrentVideoDevice);
            setMainStreamManager(newpublisher);
            setPublisher(newpublisher);
            setLoading(false);
            console.log('done##');
          })
          .catch((error: any) => {
            console.log(
              'There was an error connecting to the session:',
              error.code,
              error.message,
              error,
            );
          });

        // 참가자가 떠날때
        mySession.on('streamDestroyed', (event: any) => {
          // Remove the stream from 'subscribers' array
          deleteSubscriber(event.stream.streamManager);
        });
      });
    }
  }, [init]);

  useEffect(() => {
    setLoading(true);
    joinsession();
  }, []);

  // 로딩중 return
  if (loading)
    return (
      <div className={styles.container}>
        <Loading />
      </div>
    );

  // 참가자 수만큼 생성
  // const member = 5;
  return (
    <div className={styles.container}>
      {/* <RoomController /> */}
      <RoomHeader />
      {/* <PerfectScore /> */}
      <div className={styles.screen}>
        <div className={styles.mainScreen}>
          <MainScreen />
          <div className={styles.singScreen}>노래방화면!!!</div>
        </div>
        <div className={styles.otherScreen}>
          <MyScreen streamManager={publisher} />
          {subscribers.map(sub => {
            return <MyScreen streamManager={sub} />;
          })}
        </div>
      </div>
      <RoomFooter />
    </div>
  );
}

export default Room;
