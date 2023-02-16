// Path: '/room'
import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import { RootState } from '@/redux/store';
import { setReserv } from '@/redux/store/reservSlice';
import { setSessionId } from '@/redux/store/sessionIdSlice';
import { getCookie } from '@/util/cookie';

import RoomHeader from '@/components/room/RoomHeader';
import MainScreen from '@/components/room/MainScreen';
import MyScreen from '@/components/room/MyScreen';
import RoomFooter from '@/components/room/RoomFooter';
import Loading from '@/components/room/Loading';
import SingerScreen from '@/components/room/SingerScreen';

import styles from '@/styles/Room.module.scss';

function Index() {
  const dispatch = useDispatch();
  // username
  const [myUserName, setMyUserName] = useState('');
  const storeUser = useSelector((state: RootState) => state.user);
  useEffect(() => {
    setMyUserName(storeUser.nickname);
  }, [storeUser]);

  // sessionId (Redux 값받아오기)
  const [sessionVal, setSessionVal] = useState<string>('');
  const [roomInfo, setRoomInfo] = useState<any>();
  // const [isHost, setIsHost] = useState(false);
  const isHost = false;
  const storeSessionId = useSelector((state: RootState) => state.sessionId);
  useEffect(() => {
    setSessionVal(storeSessionId.sessionId);
  }, [storeSessionId]);

  // token (Redux 값 받아오기)
  const [sessiontoken, setSessionToken] = useState<string>('');
  useEffect(() => {
    setSessionToken(storeSessionId.token);
  }, [storeSessionId]);

  // OV
  const [screenOV, setScreenOV] = useState<any>(undefined);
  const [session, setSession] = useState<any>(undefined);
  const [screenSession, setScreenSession] = useState<any>(undefined);

  // 화면
  const [publisher, setPublisher] = useState<any[]>([]);
  const [subscribers, setSubscribers] = useState<any[]>([]);
  const [screenPublisher, setScreenPublisher] = useState<any>();
  const [singer, setSinger] = useState<any[]>([]);

  // 초기 상태값
  const [loading, setLoading] = useState(true);

  // 테마모드
  const storeTheme = useSelector((state: RootState) => state.theme);
  useEffect(() => {
    document.body.dataset.theme = storeTheme.theme || 'light';
  }, [storeTheme]);

  // api
  // async function getToken() {
  //   let host;
  //   let data;

  //   if (isHost) {
  //     host = '/host';
  //     data = roomInfo;
  //   } else {
  //     const roomDetail = await axios({
  //       method: 'GET',
  //       url: `api/v1/room/${sessionVal}`,
  //       headers: {
  //         Authorization: `${getCookie('Authorization')}`,
  //         refreshToken: `${getCookie('refreshToken')}`,
  //       },
  //     });
  //     setRoomInfo(roomDetail.data);
  //     host = '';
  //     data = null;
  //   }

  //   console.log(data);
  //   const token = await axios({
  //     method: 'POST',
  //     url: `api/v1/room/connection/${sessionVal}${host}`,
  //     data,
  //     headers: {
  //       Authorization: `${getCookie('Authorization')}`,
  //       refreshToken: `${getCookie('refreshToken')}`,
  //     },
  //   });
  //   console.log('token1 입니다', token.data);
  //   return token.data;
  // }

  async function getRoomDetail() {
    const roomDetail = await axios({
      method: 'GET',
      url: `api/v1/room/${sessionVal}`,
      headers: {
        Authorization: `${getCookie('Authorization')}`,
        refreshToken: `${getCookie('refreshToken')}`,
      },
    });
    setRoomInfo(roomDetail.data);
  }

  // api screen
  async function getToken2() {
    const response2 = await axios.post(
      `https://i8b302.p.ssafy.io/openvidu/api/sessions/${sessionVal}/connection`,
      {},
      {
        headers: { Authorization: 'Basic T1BFTlZJRFVBUFA6c3NhZnk=' },
      },
    );
    console.log(response2.data.token);
    return response2.data.token;
  }

  // singer 화면 위치 바꾸기
  const changeSinger = (from: any) => {
    const nextsinger = from.stream.streamManager;
    if (singer[0] === nextsinger) {
      console.log('똑같은 사람이 부름');
    } else if (nextsinger === publisher[0]) {
      if (singer[0] !== undefined) {
        subscribers.push(singer.pop());
        setSubscribers([...subscribers]);
      }
      singer.push(nextsinger);
      publisher.pop();
      setSinger([...singer]);
      setPublisher([...publisher]);
    } else if (publisher[0] === undefined) {
      publisher.push(singer.pop());
      publisher[0].publishAudio(true);
      setPublisher([...publisher]);
      const singerIdx = subscribers.indexOf(nextsinger, 0);
      if (singerIdx > -1) {
        singer.push(subscribers.splice(singerIdx, 1)[0]);
        setSinger([...singer]);
        setSubscribers([...subscribers]);
      }
    } else {
      const singerIdx = subscribers.indexOf(nextsinger, 0);
      singer.push(subscribers.splice(singerIdx, 1)[0]);
      if (singer.length > 1) {
        subscribers.push(singer.shift());
      }
      setSinger([...singer]);
      setSubscribers([...subscribers]);
    }
  };

  // 화면 공유 끄기
  const leaveScreen = () => {
    screenSession.unpublish(screenPublisher);
  };

  // 다른 참가자가 떠날때
  const deleteSubscriber = (streamManager: any) => {
    const newsubscribers = subscribers;
    const index = subscribers.indexOf(streamManager, 0);
    if (index > -1) {
      newsubscribers.splice(index, 1);
      setSubscribers(newsubscribers);
    }
  };

  // api delete session
  async function deleteSession() {
    axios({
      method: 'DELETE',
      url: `api/v1/room/${sessionVal}`,
      headers: {
        Authorization: `${getCookie('Authorization')}`,
        refreshToken: `${getCookie('refreshToken')}`,
      },
    }).then(() => {
      leaveScreen();
      setScreenOV(null);
      setSession(undefined);
      setScreenSession(undefined);
      setScreenPublisher(undefined);
      setPublisher([]);
      setSubscribers([]);
      setSinger([]);
      dispatch(setReserv([]));
      window.close();
    });
  }

  // 사용자가 떠날때
  const leaveSession = async () => {
    const mySession = session;
    if (mySession) {
      mySession.disconnect();
    }

    if (isHost) {
      deleteSession();
    }

    leaveScreen();
    setScreenOV(null);
    setSession(undefined);
    setScreenSession(undefined);
    setScreenPublisher(undefined);
    setPublisher([]);
    setSubscribers([]);
    setSinger([]);
    dispatch(setReserv([]));
    dispatch(setSessionId(''));
    window.close();
  };

  const doNotRefresh = (e: any) => {
    if (
      (e.ctrlKey && (e.keyCode === 78 || e.keyCode === 82)) ||
      e.keyCode === 116
    ) {
      e.preventDefault();
      alert('새로고침을 할 수 없습니다.');
    }
  };
  document.onkeydown = doNotRefresh;

  const bodyElt = document.querySelector('body');
  bodyElt?.setAttribute('oncontextmenu', 'return false;');

  window.addEventListener('beforeunload', e => {
    e.preventDefault();
    e.returnValue = '';
    leaveSession();
  });

  // connect 진행!!!!
  const joinSession = () => {
    const newOV = new OpenVidu();
    const mySession = newOV.initSession();
    setSession(mySession);

    const newScreenOV = new OpenVidu();
    const myScreen = newScreenOV.initSession();
    setScreenOV(newScreenOV);
    setScreenSession(myScreen);

    // 다른사람 캠 추가
    mySession.on('streamCreated', (event: any) => {
      if (event.stream.typeOfVideo === 'CAMERA') {
        const subscriber = mySession.subscribe(event.stream, undefined);
        const newsubscribers = subscribers;
        newsubscribers.push(subscriber);
        setSubscribers([...newsubscribers]);
      }
    });

    // 참가자가 떠날때
    mySession.on('streamDestroyed', (event: any) => {
      deleteSubscriber(event.stream.streamManager);
    });

    // 세션이 없어졌을 때
    mySession.on('sessionDisconnected', () => {
      leaveSession();
    });

    // 다음 singer
    mySession.on('signal:nextSinger', (event: any) => {
      changeSinger(event.from);
    });

    // 내 캠 connect
    console.log('1. 내캠 커넥트');
    mySession
      .connect(sessiontoken, { clientdata: myUserName })
      .then(async () => {
        const newpublisher = await newOV.initPublisherAsync(undefined, {
          audioSource: undefined,
          videoSource: undefined,
          publishAudio: true,
          publishVideo: true,
          resolution: '480x480',
          frameRate: 30,
          insertMode: 'APPEND',
          mirror: false,
        });

        mySession.publish(newpublisher);
        publisher.push(newpublisher);
        setPublisher([...publisher]);
      });

    // 화면 공유 connect
    console.log('2. 화면 공유 커넥트');
    getToken2().then((tokenScreen: any) => {
      myScreen.connect(tokenScreen, { cliendData: myUserName });
      setLoading(false);
    });
  };

  // 세션 아이디 얻으면 연결 시작
  useEffect(() => {
    if (sessiontoken !== '') {
      joinSession();
    }
  }, [sessiontoken]);

  // 세션 id 얻으면 방 정보 받아오기
  useEffect(() => {
    if (sessionVal !== '') {
      getRoomDetail();
    }
  }, [sessionVal]);

  // 임의로 mode 선언
  useEffect(() => {
    console.log('방 정보', roomInfo);
  }, [roomInfo]);

  // 로딩중 return
  if (loading)
    return (
      <div className={styles.container}>
        <Loading />
      </div>
    );

  // 로딩끝 return
  return (
    <div className={styles.container}>
      {/* <RoomController /> */}
      <RoomHeader leaveRoom={leaveSession} session={session} />
      <div className={styles.screen}>
        <div className={styles.mainScreen}>
          {/* {singer.map(person => {
            return <MyScreen key={person.id} streamManager={person} />;
          })} */}
          <SingerScreen
            streamManager={singer.length ? singer[0] : undefined}
            session={session}
          />
          <div className={styles.singScreen}>
            <MainScreen
              singMode={roomInfo.mode}
              screenOV={screenOV}
              session={session}
              screenSession={screenSession}
              publisher={publisher}
            />
          </div>
        </div>
        <div className={styles.otherScreen}>
          {publisher.map(pub => {
            return <MyScreen streamManager={pub} key={pub.id} />;
          })}
          {subscribers.map(sub => {
            return <MyScreen streamManager={sub} key={sub.stream.streamId} />;
          })}
        </div>
      </div>
      <RoomFooter session={session} publisher={publisher} />
    </div>
  );
}

export default Index;
