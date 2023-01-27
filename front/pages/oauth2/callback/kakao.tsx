import React, { useEffect } from 'react';
import axios from 'axios';
import Spinner from '@/components/common/Spinner';

function kakao() {
  // 인가 코드
  const code = new URL(window.location.href).searchParams.get('code');

  useEffect(() => {
    async function callbackCode() {
      await axios({
        method: 'GET',
        url: `http://192.168.31.64:8090/login/oauth2/code/kakao?code=${code}`,
      })
        .then(res => {
          console.log(res);
          // 토큰이 넘어올 것임
          const ACCESS_TOKEN = res.data.accessToken;
          //예시로 로컬에 저장함
          localStorage.setItem('token', ACCESS_TOKEN);
          console.log('성공');
          // 토큰 받았았고 로그인됐으니 화면 전환시켜줌(메인으로)
          window.alert('로그인에 성공하였습니다.');
        })
        .catch(err => {
          console.log('소셜로그인 에러', err);
          // 로그인 실패하면 로그인화면으로 돌려보냄
          window.alert('로그인에 실패하였습니다.');
        });
    }
    callbackCode();
  }, []);
  return <Spinner />;
}

export default kakao;
